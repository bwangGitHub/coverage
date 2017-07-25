/*
 * Copyright 2006 The Closure Compiler Authors.
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
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;


/**
 * <p>The syntactic scope creator scans the parse tree to create a Scope object
 * containing all the variable declarations in that scope.</p>
 *
 * <p>This implementation is not thread-safe.</p>
 *
 */
class SyntacticScopeCreator implements ScopeCreator {
  static {
    CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.ping();
  }

  private final AbstractCompiler compiler;
  private Scope scope;
  private InputId inputId;
  private final RedeclarationHandler redeclarationHandler;

  // The arguments variable is special, in that it's declared in every local
  // scope, but not explicitly declared.
  private static final String ARGUMENTS = "arguments";
  static {
    CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[1]++;
  }

  public static final DiagnosticType VAR_MULTIPLY_DECLARED_ERROR =
      DiagnosticType.error(
          "JSC_VAR_MULTIPLY_DECLARED_ERROR",
          "Variable {0} first declared in {1}");
  static {
    CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[2]++;
  }

  public static final DiagnosticType VAR_ARGUMENTS_SHADOWED_ERROR =
    DiagnosticType.error(
        "JSC_VAR_ARGUMENTS_SHADOWED_ERROR",
        "Shadowing \"arguments\" is not allowed");
  static {
    CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[3]++;
  }

  /**
   * Creates a ScopeCreator.
   */
  SyntacticScopeCreator(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[4]++;
    this.redeclarationHandler = new DefaultRedeclarationHandler();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[5]++;
  }

  SyntacticScopeCreator(
      AbstractCompiler compiler, RedeclarationHandler redeclarationHandler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[6]++;
    this.redeclarationHandler = redeclarationHandler;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[7]++;
  }

  @Override
  public Scope createScope(Node n, Scope parent) {
    inputId = null;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[8]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((parent == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[1]++;
      scope = Scope.createGlobalScope(n);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[10]++;

    } else {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[2]++;
      scope = new Scope(parent, n);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[11]++;
    }

    scanRoot(n);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[12]++;

    inputId = null;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[13]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[14]++;
    Scope returnedScope = scope;
    scope = null;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[15]++;
    return returnedScope;
  }

  private void scanRoot(Node n) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[3]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((inputId == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[5]++;
        inputId = NodeUtil.getInputId(n);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[18]++;

        // TODO(johnlenz): inputId maybe null if the FUNCTION node is detached
        // from the AST.
        // Is it meaningful to build a scope for detached FUNCTION node?
      } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[6]++;}
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[19]++;

      final Node fnNameNode = n.getFirstChild();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[20]++;
      final Node args = fnNameNode.getNext();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[21]++;
      final Node body = args.getNext();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[22]++;

      // Bleed the function name into the scope, if it hasn't
      // been declared in the outer scope.
      String fnName = fnNameNode.getString();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((fnName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[7]++;
        declareVar(fnNameNode);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[24]++;

      } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[8]++;}

      // Args: Declare function variables
      Preconditions.checkState(args.isParamList());
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[25]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[26]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
      for (Node a = args.getFirstChild();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((a != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false);
           a = a.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[1]--;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[2]--;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[3]++;
}
        Preconditions.checkState(a.isName());
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[27]++;
        declareVar(a);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[28]++;
      }

      // Body
      scanVars(body);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[29]++;

    } else {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[4]++;
      // It's the global block
      Preconditions.checkState(scope.getParent() == null);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[30]++;
      scanVars(n);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[31]++;
    }
  }

  /**
   * Scans and gather variables declarations under a Node
   */
  private void scanVars(Node n) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[32]++;
    switch (n.getType()) {
      case Token.VAR:
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[9]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[33]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
        // Declare all variables. e.g. var x = 1, y, z;
        for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[4]--;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[5]--;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[6]++;
}
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[34]++;
          Node next = child.getNext();
          declareVar(child);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[35]++;
          child = next;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[36]++;
        }
        return;

      case Token.FUNCTION:
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[10]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[37]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[11]++;
          return;

        } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[12]++;}
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[38]++;

        String fnName = n.getFirstChild().getString();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[39]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((fnName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[13]++;
          // This is invalid, but allow it so the checks can catch it.
          return;

        } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[14]++;}
        declareVar(n.getFirstChild());
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[40]++;
        return;   // should not examine function's children

      case Token.CATCH:
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[15]++;
        Preconditions.checkState(n.getChildCount() == 2);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[41]++;
        Preconditions.checkState(n.getFirstChild().isName());
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[42]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[43]++;
        // the first child is the catch var and the third child
        // is the code block

        final Node var = n.getFirstChild();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[44]++;
        final Node block = var.getNext();

        declareVar(var);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[45]++;
        scanVars(block);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[46]++;
        return;  // only one child to scan

      case Token.SCRIPT:
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[16]++;
        inputId = n.getInputId();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[47]++;
        Preconditions.checkNotNull(inputId);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[48]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[49]++;
        break; default : CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[17]++;
    }
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;

    // Variables can only occur in statement-level nodes, so
    // we only need to traverse children in a couple special cases.
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((NodeUtil.isControlStructure(n)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(n)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[18]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[51]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
      for (Node child = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[7]--;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[8]--;
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.loops[9]++;
}
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[52]++;
        Node next = child.getNext();
        scanVars(child);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[53]++;
        child = next;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[54]++;
      }

    } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[19]++;}
  }

  /**
   * Interface for injectable duplicate handling.
   */
  interface RedeclarationHandler {
    void onRedeclaration(
        Scope s, String name, Node n, CompilerInput input);
  }

  /**
   * The default handler for duplicate declarations.
   */
  private class DefaultRedeclarationHandler implements RedeclarationHandler {
    @Override
    public void onRedeclaration(
        Scope s, String name, Node n, CompilerInput input) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[55]++;
      Node parent = n.getParent();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[56]++;
int CodeCoverConditionCoverageHelper_C11;

      // Don't allow multiple variables to be declared at the top-level scope
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[20]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[57]++;
        Scope.Var origVar = scope.getVar(name);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[58]++;
        Node origParent = origVar.getParentNode();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[59]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((origParent.isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((parent.isCatch()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[22]++;
          // Okay, both are 'catch(x)' variables.
          return;

        } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[23]++;}
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[60]++;

        boolean allowDupe = hasDuplicateDeclarationSuppression(n, origVar);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((allowDupe) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[24]++;
          compiler.report(
              JSError.make(NodeUtil.getSourceName(n), n,
                           VAR_MULTIPLY_DECLARED_ERROR,
                           name,
                           (origVar.input != null
                            ? origVar.input.getName()
                            : "??")));
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[62]++;

        } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[25]++;}

      } else {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[21]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[63]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((name.equals(ARGUMENTS)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[26]++;
        // Disallow shadowing "arguments" as we can't handle with our current
        // scope modeling.
        compiler.report(
            JSError.make(NodeUtil.getSourceName(n), n,
                VAR_ARGUMENTS_SHADOWED_ERROR));
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[64]++;

      } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[27]++;}
}
    }
  }

  /**
   * Declares a variable.
   *
   * @param n The node corresponding to the variable name.
   */
  private void declareVar(Node n) {
    Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[65]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[66]++;

    CompilerInput input = compiler.getInput(inputId);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[67]++;
    String name = n.getString();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[68]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (32)) == 0 || true) &&
 ((scope.isDeclared(name, false)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((scope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((name.equals(ARGUMENTS)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 3) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[28]++;
      redeclarationHandler.onRedeclaration(
          scope, name, n, input);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[69]++;

    } else {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[29]++;
      scope.declare(name, n, null, input);
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[70]++;
    }
  }


  /**
   * @param n The name node to check.
   * @param origVar The associated Var.
   * @return Whether duplicated declarations warnings should be suppressed
   *     for the given node.
   */
  static boolean hasDuplicateDeclarationSuppression(Node n, Scope.Var origVar) {
    Preconditions.checkState(n.isName());
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[71]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[72]++;
    Node parent = n.getParent();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[73]++;
    Node origParent = origVar.getParentNode();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[74]++;

    JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[75]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[30]++;
      info = parent.getJSDocInfo();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[76]++;

    } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[31]++;}
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[77]++;
int CodeCoverConditionCoverageHelper_C17;
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((info.getSuppressions().contains("duplicate")) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[32]++;
      return true;

    } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[33]++;}

    info = origVar.nameNode.getJSDocInfo();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[78]++;
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[79]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((info == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[34]++;
      info = origParent.getJSDocInfo();
CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.statements[80]++;

    } else {
  CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5.branches[35]++;}
    return (info != null && info.getSuppressions().contains("duplicate"));
  }

  /**
   * Generates an untyped global scope from the root of AST of compiler (which
   * includes externs).
   *
   * @param compiler The compiler for which the scope is generated.
   * @return The new untyped global scope generated as a result of this call.
   */
  static Scope generateUntypedTopScope(AbstractCompiler compiler) {
    return new SyntacticScopeCreator(compiler).createScope(compiler.getRoot(),
        null);
  }


}

class CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5 ());
  }
    public static long[] statements = new long[81];
    public static long[] branches = new long[36];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[19];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.SyntacticScopeCreator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,1,1,1,2,1,1,2,1,2,3,1,2,1};
    for (int i = 1; i <= 18; i++) {
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

  public CodeCoverCoverageCounter$r0vi6k01wqg4h9hje1tw3ysceaz0qgag2vm2hsv5 () {
    super("com.google.javascript.jscomp.SyntacticScopeCreator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 80; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 35; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 18; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.SyntacticScopeCreator.java");
      for (int i = 1; i <= 80; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 35; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 18; i++) {
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

