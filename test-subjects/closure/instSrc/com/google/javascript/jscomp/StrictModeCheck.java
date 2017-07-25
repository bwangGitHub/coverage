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
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Set;

/**
 * Checks that the code obeys the static restrictions of strict mode:
 * <ol>
 * <li> No use of "with".
 * <li> No deleting variables, functions, or arguments.
 * <li> No re-declarations or assignments of "eval" or arguments.
 * <li> No use of "eval" (optional check for Caja).
 * </ol>
 *
 */
class StrictModeCheck extends AbstractPostOrderCallback
    implements CompilerPass {
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.ping();
  }


  static final DiagnosticType UNKNOWN_VARIABLE = DiagnosticType.warning(
      "JSC_UNKNOWN_VARIABLE", "unknown variable {0}");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[1]++;
  }

  static final DiagnosticType EVAL_USE = DiagnosticType.error(
      "JSC_EVAL_USE", "\"eval\" cannot be used in Caja");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[2]++;
  }

  static final DiagnosticType EVAL_DECLARATION = DiagnosticType.warning(
      "JSC_EVAL_DECLARATION",
      "\"eval\" cannot be redeclared in ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[3]++;
  }

  static final DiagnosticType EVAL_ASSIGNMENT = DiagnosticType.warning(
      "JSC_EVAL_ASSIGNMENT",
      "the \"eval\" object cannot be reassigned in ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[4]++;
  }

  static final DiagnosticType ARGUMENTS_DECLARATION = DiagnosticType.warning(
      "JSC_ARGUMENTS_DECLARATION",
      "\"arguments\" cannot be redeclared in ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[5]++;
  }

  static final DiagnosticType ARGUMENTS_ASSIGNMENT = DiagnosticType.warning(
      "JSC_ARGUMENTS_ASSIGNMENT",
      "the \"arguments\" object cannot be reassigned in ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[6]++;
  }

  static final DiagnosticType DELETE_VARIABLE = DiagnosticType.warning(
      "JSC_DELETE_VARIABLE",
      "variables, functions, and arguments cannot be deleted in "
      + "ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[7]++;
  }

  static final DiagnosticType ILLEGAL_NAME = DiagnosticType.error(
      "JSC_ILLEGAL_NAME",
      "identifiers ending in '__' cannot be used in Caja");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[8]++;
  }

  static final DiagnosticType DUPLICATE_OBJECT_KEY = DiagnosticType.warning(
      "JSC_DUPLICATE_OBJECT_KEY",
      "object literals cannot contain duplicate keys in ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[9]++;
  }

  static final DiagnosticType BAD_FUNCTION_DECLARATION = DiagnosticType.error(
      "JSC_BAD_FUNCTION_DECLARATION",
      "functions can only be declared at top level or immediately within " +
      "another function in ES5 strict mode");
  static {
    CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[10]++;
  }

  private final AbstractCompiler compiler;
  private final boolean noVarCheck;
  private final boolean noCajaChecks;

  StrictModeCheck(AbstractCompiler compiler) {
    this(compiler, false, false);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[11]++;
  }

  StrictModeCheck(
      AbstractCompiler compiler, boolean noVarCheck, boolean noCajaChecks) {
    this.compiler = compiler;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[12]++;
    this.noVarCheck = noVarCheck;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[13]++;
    this.noCajaChecks = noCajaChecks;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[14]++;
  }

  @Override public void process(Node externs, Node root) {
    NodeTraversal.traverseRoots(
        compiler, Lists.newArrayList(externs, root), this);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[15]++;
    NodeTraversal.traverse(compiler, root, new NonExternChecks());
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[16]++;
  }

  @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[17]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[1]++;
      checkFunctionUse(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[18]++;

    } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[2]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[19]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[3]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[5]++;
        checkNameUse(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[21]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[6]++;}

    } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[4]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[22]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[7]++;
      checkAssignment(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[23]++;

    } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[8]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[24]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((n.isDelProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[9]++;
      checkDelete(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[25]++;

    } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[10]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[26]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[11]++;
      checkObjectLiteral(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[27]++;

    } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[12]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[28]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((n.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[13]++;
      checkLabel(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[29]++;

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[14]++;}
}
}
}
}
}
  }

  /** Checks that the function is used legally. */
  private void checkFunctionUse(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((NodeUtil.isHoistedFunctionDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[15]++;
      t.report(n, BAD_FUNCTION_DECLARATION);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[31]++;

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[16]++;}
  }

  /**
   * Determines if the given name is a declaration, which can be a declaration
   * of a variable, function, or argument.
   */
  private static boolean isDeclaration(Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[32]++;
    switch (n.getParent().getType()) {
      case Token.VAR:
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[17]++;
      case Token.FUNCTION:
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[18]++;
      case Token.CATCH:
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[19]++;
        return true;

      case Token.PARAM_LIST:
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[20]++;
        return n.getParent().getParent().isFunction();

      default:
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[21]++;
        return false;
    }
  }

  /** Checks that the given name is used legally. */
  private void checkNameUse(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[33]++;
    Var v = t.getScope().getVar(n.getString());
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[34]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((v == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[22]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[35]++;
int CodeCoverConditionCoverageHelper_C10;
      // In particular, this prevents creating a global variable by assigning
      // to it without a declaration.
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((noVarCheck) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[24]++;
        t.report(n, UNKNOWN_VARIABLE, n.getString());
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[36]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[25]++;}

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[23]++;}
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[37]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((noCajaChecks) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[26]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 (("eval".equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[28]++;
        t.report(n, EVAL_USE);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[39]++;

      } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[29]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[40]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n.getString().endsWith("__")) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[30]++;
        t.report(n, ILLEGAL_NAME);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[41]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[31]++;}
}

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[27]++;}
  }

  /** Checks that an assignment is not to the "arguments" object. */
  private void checkAssignment(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[42]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[32]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[43]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (("arguments".equals(n.getFirstChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[34]++;
        t.report(n, ARGUMENTS_ASSIGNMENT);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[44]++;

      } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[35]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[45]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (("eval".equals(n.getFirstChild().getString())) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[36]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[46]++;
int CodeCoverConditionCoverageHelper_C17;
        // Note that assignment to eval is already illegal because any use of
        // that name is illegal.
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((noCajaChecks) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[38]++;
          t.report(n, EVAL_ASSIGNMENT);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[47]++;

        } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[39]++;}

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[37]++;}
}

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[33]++;}
  }

  /** Checks that variables, functions, and arguments are not deleted. */
  private void checkDelete(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[48]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[40]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[49]++;
      Var v = t.getScope().getVar(n.getFirstChild().getString());
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[50]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((v != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[42]++;
        t.report(n, DELETE_VARIABLE);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[51]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[43]++;}

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[41]++;}
  }

  /** Checks that object literal keys are valid. */
  private void checkObjectLiteral(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[52]++;
    Set<String> getters = Sets.newHashSet();
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[53]++;
    Set<String> setters = Sets.newHashSet();
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[54]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C20;
    for (Node key = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false);
         key = key.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.loops[1]--;
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.loops[2]--;
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.loops[3]++;
}
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[55]++;
int CodeCoverConditionCoverageHelper_C21;
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((noCajaChecks) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((key.getString().endsWith("__")) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[44]++;
        t.report(key, ILLEGAL_NAME);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[56]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[45]++;}
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[57]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((key.isSetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[46]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[58]++;
int CodeCoverConditionCoverageHelper_C23;
        // normal property and getter cases
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((getters.contains(key.getString())) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[48]++;
          t.report(key, DUPLICATE_OBJECT_KEY);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[59]++;

        } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[49]++;
          getters.add(key.getString());
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[60]++;
        }

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[47]++;}
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[61]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((key.isGetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[50]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[62]++;
int CodeCoverConditionCoverageHelper_C25;
        // normal property and setter cases
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((setters.contains(key.getString())) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[52]++;
          t.report(key, DUPLICATE_OBJECT_KEY);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[63]++;

        } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[53]++;
          setters.add(key.getString());
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[64]++;
        }

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[51]++;}
    }
  }

  /** Checks that label names are valid. */
  private void checkLabel(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[65]++;
int CodeCoverConditionCoverageHelper_C26;
    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((n.getFirstChild().getString().endsWith("__")) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[54]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[66]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((noCajaChecks) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[56]++;
        t.report(n.getFirstChild(), ILLEGAL_NAME);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[67]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[57]++;}

    } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[55]++;}
  }

  /** Checks that are performed on non-extern code only. */
  private class NonExternChecks extends AbstractPostOrderCallback {
    @Override public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[68]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((isDeclaration(n)) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[58]++;
        checkDeclaration(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[69]++;

      } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[59]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[70]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((n.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[60]++;
        checkProperty(t, n);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[71]++;

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[61]++;}
}
    }

    /** Checks for illegal declarations. */
    private void checkDeclaration(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[72]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 (("eval".equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[62]++;
        t.report(n, EVAL_DECLARATION);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[73]++;

      } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[63]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[74]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 (("arguments".equals(n.getString())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[64]++;
        t.report(n, ARGUMENTS_DECLARATION);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[75]++;

      } else {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[65]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[76]++;
int CodeCoverConditionCoverageHelper_C32; if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((n.getString().endsWith("__")) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[66]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[77]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((noCajaChecks) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[68]++;
          t.report(n, ILLEGAL_NAME);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[78]++;

        } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[69]++;}

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[67]++;}
}
}
    }

    /** Checks for illegal property accesses. */
    private void checkProperty(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[79]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((n.getLastChild().getString().endsWith("__")) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[70]++;
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[80]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((noCajaChecks) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[72]++;
          t.report(n.getLastChild(), ILLEGAL_NAME);
CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.statements[81]++;

        } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[73]++;}

      } else {
  CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h.branches[71]++;}
    }
  }
}

class CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h ());
  }
    public static long[] statements = new long[82];
    public static long[] branches = new long[74];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[36];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.StrictModeCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 35; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$9qy054hetwlm4emfg6hpv16k18ztv8h () {
    super("com.google.javascript.jscomp.StrictModeCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 81; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 73; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 35; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.StrictModeCheck.java");
      for (int i = 1; i <= 81; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 73; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 35; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

