/*
 * Copyright 2004 The Closure Compiler Authors.
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
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Set;

/**
 * Checks that all variables are declared, that file-private variables are
 * accessed only in the file that declares them, and that any var references
 * that cross module boundaries respect declared module dependencies.
 *
 */
class VarCheck extends AbstractPostOrderCallback implements
    HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.ping();
  }


  static final DiagnosticType UNDEFINED_VAR_ERROR = DiagnosticType.error(
      "JSC_UNDEFINED_VARIABLE",
      "variable {0} is undeclared");
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[1]++;
  }

  static final DiagnosticType VIOLATED_MODULE_DEP_ERROR = DiagnosticType.error(
      "JSC_VIOLATED_MODULE_DEPENDENCY",
      "module {0} cannot reference {2}, defined in " +
      "module {1}, since {1} loads after {0}");
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[2]++;
  }

  static final DiagnosticType MISSING_MODULE_DEP_ERROR = DiagnosticType.warning(
      "JSC_MISSING_MODULE_DEPENDENCY",
      "missing module dependency; module {0} should depend " +
      "on module {1} because it references {2}");
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[3]++;
  }

  static final DiagnosticType STRICT_MODULE_DEP_ERROR = DiagnosticType.disabled(
      "JSC_STRICT_MODULE_DEPENDENCY",
      "module {0} cannot reference {2}, defined in " +
      "module {1}");
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[4]++;
  }

  static final DiagnosticType NAME_REFERENCE_IN_EXTERNS_ERROR =
    DiagnosticType.warning(
      "JSC_NAME_REFERENCE_IN_EXTERNS",
      "accessing name {0} in externs has no effect");
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[5]++;
  }

  static final DiagnosticType UNDEFINED_EXTERN_VAR_ERROR =
    DiagnosticType.warning(
      "JSC_UNDEFINED_EXTERN_VAR_ERROR",
      "name {0} is not undefined in the externs.");
  static {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[6]++;
  }

  private Node synthesizedExternsRoot = null;
  {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[7]++;
  }

  // Vars that still need to be declared in externs. These will be declared
  // at the end of the pass, or when we see the equivalent var declared
  // in the normal code.
  private final Set<String> varsToDeclareInExterns = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[8]++;
  }

  private final AbstractCompiler compiler;

  // Whether this is the post-processing sanity check.
  private final boolean sanityCheck;

  // Whether extern checks emit error.
  private final boolean strictExternCheck;

  VarCheck(AbstractCompiler compiler) {
    this(compiler, false);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[9]++;
  }

  VarCheck(AbstractCompiler compiler, boolean sanityCheck) {
    this.compiler = compiler;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[10]++;
    this.strictExternCheck = compiler.getErrorLevel(
        JSError.make("", 0, 0, UNDEFINED_EXTERN_VAR_ERROR)) == CheckLevel.ERROR;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[11]++;
    this.sanityCheck = sanityCheck;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[12]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
    // Don't run externs-checking in sanity check mode. Normalization will
    // remove duplicate VAR declarations, which will make
    // externs look like they have assigns.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sanityCheck) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[1]++;
      NodeTraversal.traverse(compiler, externs, new NameRefInExternsCheck());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[14]++;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[2]++;}

    NodeTraversal.traverseRoots(
        compiler, Lists.newArrayList(externs, root), this);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[15]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[16]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.loops[1]++;


    for (String varName : varsToDeclareInExterns) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.loops[1]--;
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.loops[2]--;
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.loops[3]++;
}
      createSynthesizedExternVar(varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[17]++;
    }
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    Preconditions.checkState(scriptRoot.isScript());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[18]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[19]++;
    NodeTraversal t = new NodeTraversal(compiler, this);
    // Note we use the global scope to prevent wrong "undefined-var errors" on
    // variables that are defined in other JS files.
    t.traverseWithScope(scriptRoot,
        SyntacticScopeCreator.generateUntypedTopScope(compiler));
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[20]++;
    // TODO(bashir) Check if we need to createSynthesizedExternVar like process.
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[4]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[22]++;

    String varName = n.getString();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;

    // Only a function can have an empty name.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((varName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[5]++;
      Preconditions.checkState(parent.isFunction());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[24]++;
      Preconditions.checkState(NodeUtil.isFunctionExpression(parent));
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[25]++;
      return;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[6]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;

    // Check if this is a declaration for a var that has been declared
    // elsewhere. If so, mark it as a duplicate.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(parent)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((varsToDeclareInExterns.contains(varName)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[7]++;
      createSynthesizedExternVar(varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[27]++;

      n.addSuppression("duplicate");
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[28]++;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[8]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[29]++;

    // Check that the var has been declared.
    Scope scope = t.getScope();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[30]++;
    Scope.Var var = scope.getVar(varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[9]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(parent)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[11]++;

        // e.g. [ function foo() {} ], it's okay if "foo" isn't defined in the
        // current scope.
      } else {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[12]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
        // The extern checks are stricter, don't report a second error.
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((strictExternCheck) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((t.getInput().isExtern()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[13]++;
          t.report(n, UNDEFINED_VAR_ERROR, varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[34]++;

        } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[14]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((sanityCheck) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[15]++;
          throw new IllegalStateException("Unexpected variable " + varName);

        } else {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[16]++;
          createSynthesizedExternVar(varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[36]++;
          scope.getGlobalScope().declare(varName, n,
              null, getSynthesizedExternsInput());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[37]++;
        }
      }
      return;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[10]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[38]++;

    CompilerInput currInput = t.getInput();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[39]++;
    CompilerInput varInput = var.input;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[40]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((currInput == varInput) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((currInput == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((varInput == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[17]++;
      // The variable was defined in the same file. This is fine.
      return;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[18]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[41]++;

    // Check module dependencies.
    JSModule currModule = currInput.getModule();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[42]++;
    JSModule varModule = varInput.getModule();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[43]++;
    JSModuleGraph moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[44]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (128)) == 0 || true) &&
 ((sanityCheck) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((varModule != currModule) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((varModule != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((currModule != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 4) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[19]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[45]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(currModule, varModule)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[21]++;

        // The module dependency was properly declared.
      } else {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[22]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[46]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((scope.isGlobal()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[23]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[47]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(varModule, currModule)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[25]++;
            // The variable reference violates a declared module dependency.
            t.report(n, VIOLATED_MODULE_DEP_ERROR,
                     currModule.getName(), varModule.getName(), varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[48]++;

          } else {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[26]++;
            // The variable reference is between two modules that have no
            // dependency relationship. This should probably be considered an
            // error, but just issue a warning for now.
            t.report(n, MISSING_MODULE_DEP_ERROR,
                     currModule.getName(), varModule.getName(), varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[49]++;
          }

        } else {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[24]++;
          t.report(n, STRICT_MODULE_DEP_ERROR,
                   currModule.getName(), varModule.getName(), varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[50]++;
        }
      }

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[20]++;}
  }

  /**
   * Create a new variable in a synthetic script. This will prevent
   * subsequent compiler passes from crashing.
   */
  private void createSynthesizedExternVar(String varName) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[51]++;
    Node nameNode = IR.name(varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[52]++;
int CodeCoverConditionCoverageHelper_C14;

    // Mark the variable as constant if it matches the coding convention
    // for constant vars.
    // NOTE(nicksantos): honestly, I'm not sure how much this matters.
    // AFAIK, all people who use the CONST coding convention also
    // compile with undeclaredVars as errors. We have some test
    // cases for this configuration though, and it makes them happier.
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isConstant(varName)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[27]++;
      nameNode.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[53]++;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[28]++;}

    getSynthesizedExternsRoot().addChildToBack(
        IR.var(nameNode));
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[54]++;
    varsToDeclareInExterns.remove(varName);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[55]++;
    compiler.reportCodeChange();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[56]++;
  }

  /**
   * A check for name references in the externs inputs. These used to prevent
   * a variable from getting renamed, but no longer have any effect.
   */
  private class NameRefInExternsCheck extends AbstractPostOrderCallback {
    @Override
    public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[29]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[58]++;
        switch (parent.getType()) {
          case Token.VAR:
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[31]++;
          case Token.FUNCTION:
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[32]++;
          case Token.PARAM_LIST:
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[33]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[59]++;
            // These are okay.
            break;
          case Token.GETPROP:
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[34]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((n == parent.getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[35]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[61]++;
              Scope scope = t.getScope();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[62]++;
              Scope.Var var = scope.getVar(n.getString());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
              if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[37]++;
                t.report(n, UNDEFINED_EXTERN_VAR_ERROR, n.getString());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[64]++;
                varsToDeclareInExterns.add(n.getString());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[65]++;

              } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[38]++;}

            } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[36]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[66]++;
            break;
          default:
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[39]++;
            t.report(n, NAME_REFERENCE_IN_EXTERNS_ERROR, n.getString());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[67]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[68]++;

            Scope scope = t.getScope();
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[69]++;
            Scope.Var var = scope.getVar(n.getString());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[70]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[40]++;
              varsToDeclareInExterns.add(n.getString());
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[71]++;

            } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[41]++;}
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[72]++;
            break;
        }

      } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[30]++;}
    }
  }

  /** Lazily create a "new" externs input for undeclared variables. */
  private CompilerInput getSynthesizedExternsInput() {
    return compiler.getSynthesizedExternsInput();
  }

  /** Lazily create a "new" externs root for undeclared variables. */
  private Node getSynthesizedExternsRoot() {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[73]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((synthesizedExternsRoot == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[42]++;
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[74]++;
      CompilerInput synthesizedExterns = getSynthesizedExternsInput();
      synthesizedExternsRoot = synthesizedExterns.getAstRoot(compiler);
CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.statements[75]++;

    } else {
  CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9.branches[43]++;}
    return synthesizedExternsRoot;
  }
}

class CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9 ());
  }
    public static long[] statements = new long[76];
    public static long[] branches = new long[44];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[20];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.VarCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,1,1,2,1,3,3,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 19; i++) {
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

  public CodeCoverCoverageCounter$ifik0lc45uwcps1yzqe9 () {
    super("com.google.javascript.jscomp.VarCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 75; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 43; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 19; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.VarCheck.java");
      for (int i = 1; i <= 75; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 43; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 19; i++) {
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

