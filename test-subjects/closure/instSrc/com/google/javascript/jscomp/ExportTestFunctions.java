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
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import java.util.regex.Pattern;

/**
 * Generates goog.exportSymbol for test functions, so they can be recognized
 * by the test runner, even if the code is compiled.
 *
 */
class ExportTestFunctions implements CompilerPass {
  static {
    CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.ping();
  }


  private static final Pattern TEST_FUNCTIONS_NAME_PATTERN =
      Pattern.compile("^(?:((\\w+\\.)+prototype\\.)*" +
                      "(setUpPage|setUp|tearDown|tearDownPage|test\\w+))$");
  static {
    CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[1]++;
  }

  private AbstractCompiler compiler;
  private final String exportSymbolFunction;
  private final String exportPropertyFunction;

  /**
   * Creates a new export test functions compiler pass.
   * @param compiler
   * @param exportSymbolFunction The function name used to export symbols in JS.
   * @param exportPropertyFunction The function name used to export properties
   *     in JS.
   */
  ExportTestFunctions(AbstractCompiler compiler,
      String exportSymbolFunction, String exportPropertyFunction) {

    Preconditions.checkNotNull(compiler);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[2]++;
    this.compiler = compiler;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[3]++;
    this.exportSymbolFunction = exportSymbolFunction;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[4]++;
    this.exportPropertyFunction = exportPropertyFunction;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[5]++;
  }

  private class ExportTestFunctionsNodes extends
      NodeTraversal.AbstractShallowCallback {

    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;

      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[1]++;
        return;

      } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[2]++;}
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;

      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[3]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[5]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[9]++;
          // Check for a test function statement.
          String functionName = NodeUtil.getFunctionName(n);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isTestFunction(n, functionName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[7]++;
            exportTestFunctionAsSymbol(functionName, n, parent);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[11]++;

          } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[8]++;}

        } else {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[6]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[12]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isVarDeclaredFunction(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[9]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[13]++;
          // Check for a test function expression.
          Node functionNode = n.getFirstChild().getFirstChild();
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[14]++;
          String functionName = NodeUtil.getFunctionName(functionNode);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isTestFunction(functionNode, functionName)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[11]++;
            exportTestFunctionAsSymbol(functionName, n, parent);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[16]++;

          } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[10]++;}
}

      } else {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[4]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[17]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((NodeUtil.isExprAssign(parent)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.getLastChild().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[13]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[18]++;
        // Check for a test method assignment.
        Node grandparent = parent.getParent();
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((grandparent != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((grandparent.isScript()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[15]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[20]++;
          String functionName = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isTestFunction(n, functionName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[17]++;
            exportTestFunctionAsProperty(functionName, parent, n, grandparent);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[22]++;

          } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[16]++;}

      } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[14]++;}
}
    }

    /**
     * Whether node corresponds to a function expression declared with var,
     * which is of the form:
     * <pre>
     * var functionName = function() {
     *   // Implementation
     * };
     * </pre>
     * This has the AST structure VAR -> NAME -> FUNCTION
     * @param node
     */
    private boolean isVarDeclaredFunction(Node node) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[23]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((node.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[19]++;
        return false;

      } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[20]++;}
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[24]++;
      Node grandchild = node.getFirstChild().getFirstChild();
      return grandchild != null && grandchild.isFunction();
    }
  }

  @Override
  public void process(Node externs, Node root) {
    NodeTraversal.traverse(compiler, root, new ExportTestFunctionsNodes());
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[25]++;
  }

  // Adds exportSymbol(testFunctionName, testFunction);
  private void exportTestFunctionAsSymbol(String testFunctionName, Node node,
      Node scriptNode) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[26]++;

    Node exportCallTarget = NodeUtil.newQualifiedNameNode(
        compiler.getCodingConvention(),
        exportSymbolFunction, node, testFunctionName);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[27]++;
    Node call = IR.call( exportCallTarget);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((exportCallTarget.isName()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[21]++;
      call.putBooleanProp(Node.FREE_CALL, true);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[29]++;

    } else {
  CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.branches[22]++;}
    call.addChildToBack(IR.string(testFunctionName));
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[30]++;
    call.addChildToBack(NodeUtil.newQualifiedNameNode(
        compiler.getCodingConvention(),
        testFunctionName, node, testFunctionName));
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[31]++;
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[32]++;

    Node expression = IR.exprResult(call);

    scriptNode.addChildAfter(expression, node);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[33]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[34]++;
  }


  // Adds exportProperty() of the test function name on the prototype object
  private void exportTestFunctionAsProperty(String fullyQualifiedFunctionName,
      Node parent, Node node, Node scriptNode) {
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[35]++;

    String testFunctionName =
        NodeUtil.getPrototypePropertyName(node.getFirstChild());
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[36]++;
    String objectName = fullyQualifiedFunctionName.substring(0,
        fullyQualifiedFunctionName.lastIndexOf('.'));
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[37]++;
    String exportCallStr = String.format("%s(%s, '%s', %s);",
        exportPropertyFunction, objectName, testFunctionName,
        fullyQualifiedFunctionName);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[38]++;

    Node exportCall = this.compiler.parseSyntheticCode(exportCallStr)
        .removeChildren();
    exportCall.useSourceInfoFromForTree(scriptNode);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[39]++;

    scriptNode.addChildAfter(exportCall, parent);
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[40]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x.statements[41]++;
  }


  /**
   * Whether a function is recognized as a test function. We follow the JsUnit
   * convention for naming (functions should start with "test"), and we also
   * check if it has no parameters declared.
   *
   * @param n The function node
   * @param functionName The name of the function
   * @return {@code true} if the function is recognized as a test function.
   */
  private boolean isTestFunction(Node n, String functionName) {
    return !(functionName == null
        || !TEST_FUNCTIONS_NAME_PATTERN.matcher(functionName).matches());
  }
}

class CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x ());
  }
    public static long[] statements = new long[42];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ExportTestFunctions.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,2,1,1,1};
    for (int i = 1; i <= 11; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$g0es0e2z6nr86bk0e7uxasdr309mjgmbtf68x () {
    super("com.google.javascript.jscomp.ExportTestFunctions.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 41; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ExportTestFunctions.java");
      for (int i = 1; i <= 41; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 0; i++) {
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

