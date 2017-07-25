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

import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ReferenceCollectingCallback.BasicBlock;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Behavior;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Reference;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceMap;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Checks variables to see if they are referenced before their declaration, or
 * if they are redeclared in a way that is suspicious (i.e. not dictated by
 * control structures). This is a more aggressive version of {@link VarCheck},
 * but it lacks the cross-module checks.
 *
 * @author kushal@google.com (Kushal Dave)
 */
class VariableReferenceCheck implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.ping();
  }


  static final DiagnosticType UNDECLARED_REFERENCE = DiagnosticType.warning(
      "JSC_REFERENCE_BEFORE_DECLARE",
      "Variable referenced before declaration: {0}");
  static {
    CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[1]++;
  }

  static final DiagnosticType REDECLARED_VARIABLE = DiagnosticType.warning(
      "JSC_REDECLARED_VARIABLE",
      "Redeclared variable: {0}");
  static {
    CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[2]++;
  }

  static final DiagnosticType AMBIGUOUS_FUNCTION_DECL =
    DiagnosticType.disabled("AMBIGUOUS_FUNCTION_DECL",
        "Ambiguous use of a named function: {0}.");
  static {
    CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[3]++;
  }

  private final AbstractCompiler compiler;
  private final CheckLevel checkLevel;

  // NOTE(nicksantos): It's a lot faster to use a shared Set that
  // we clear after each method call, because the Set never gets too big.
  private final Set<BasicBlock> blocksWithDeclarations = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[4]++;
  }

  public VariableReferenceCheck(AbstractCompiler compiler,
      CheckLevel checkLevel) {
    this.compiler = compiler;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[5]++;
    this.checkLevel = checkLevel;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[6]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[7]++;
    ReferenceCollectingCallback callback = new ReferenceCollectingCallback(
        compiler, new ReferenceCheckingBehavior());
    callback.process(externs, root);
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[8]++;
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[9]++;
    ReferenceCollectingCallback callback = new ReferenceCollectingCallback(
        compiler, new ReferenceCheckingBehavior());
    callback.hotSwapScript(scriptRoot, originalRoot);
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[10]++;
  }

  /**
   * Behavior that checks variables for redeclaration or early references
   * just after they go out of scope.
   */
  private class ReferenceCheckingBehavior implements Behavior {

    @Override
    public void afterExitScope(NodeTraversal t, ReferenceMap referenceMap) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
      // TODO(bashir) In hot-swap version this means that for global scope we
      // only go through all global variables accessed in the modified file not
      // all global variables. This should be fixed.

      // Check all vars after finishing a scope
      for (Iterator<Var> it = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[1]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[2]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[3]++;
}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[12]++;
        Var v = it.next();
        checkVar(v, referenceMap.getReferences(v).references);
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[13]++;
      }
    }

    /**
     * If the variable is declared more than once in a basic block, generate a
     * warning. Also check if a variable is used in a given scope before it is
     * declared, which suggest a likely error. Relies on the fact that
     * references is in parse-tree order.
     */
    private void checkVar(Var v, List<Reference> references) {
      blocksWithDeclarations.clear();
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[14]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[15]++;
      boolean isDeclaredInScope = false;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[16]++;
      boolean isUnhoistedNamedFunction = false;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[17]++;
      Reference hoistedFn = null;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[4]++;



      // Look for hoisted functions.
      for (Reference reference : references) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[4]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[5]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[6]++;
}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((reference.isHoistedFunction()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[1]++;
          blocksWithDeclarations.add(reference.getBasicBlock());
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[20]++;
          isDeclaredInScope = true;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[21]++;
          hoistedFn = reference;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[22]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[23]++;
          break;

        } else {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[2]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[24]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(
            reference.getNode().getParent())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[3]++;
          isUnhoistedNamedFunction = true;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[25]++;

        } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[4]++;}
}
      }
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[26]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[7]++;



      for (Reference reference : references) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[7]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[8]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[9]++;
}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((reference == hoistedFn) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[5]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[28]++;
          continue;

        } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[6]++;}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[29]++;

        BasicBlock basicBlock = reference.getBasicBlock();
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[30]++;
        boolean isDeclaration = reference.isDeclaration();
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[31]++;

        boolean allowDupe =
            SyntacticScopeCreator.hasDuplicateDeclarationSuppression(
                reference.getNode(), v);
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[32]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((isDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((allowDupe) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[7]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[33]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[10]++;


          // Look through all the declarations we've found so far, and
          // check if any of them are before this block.
          for (BasicBlock declaredBlock : blocksWithDeclarations) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[10]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[11]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[12]++;
}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[34]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((declaredBlock.provablyExecutesBefore(basicBlock)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[9]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[35]++;
              // TODO(johnlenz): Fix AST generating clients that so they would
              // have property StaticSourceFile attached at each node. Or
              // better yet, make sure the generated code never violates
              // the requirement to pass aggressive var check!
              String filename = NodeUtil.getSourceName(reference.getNode());
              compiler.report(
                  JSError.make(filename,
                      reference.getNode(),
                      checkLevel,
                      REDECLARED_VARIABLE, v.name));
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[36]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[37]++;
              break;

            } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[10]++;}
          }

        } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[8]++;}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[38]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((isUnhoistedNamedFunction) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((isDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isDeclaredInScope) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[11]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[39]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[13]++;


          // Only allow an unhoisted named function to be used within the
          // block it is declared.
          for (BasicBlock declaredBlock : blocksWithDeclarations) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[13]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[14]--;
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.loops[15]++;
}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[40]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((declaredBlock.provablyExecutesBefore(basicBlock)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[13]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[41]++;
              String filename = NodeUtil.getSourceName(reference.getNode());
              compiler.report(
                  JSError.make(filename,
                      reference.getNode(),
                      AMBIGUOUS_FUNCTION_DECL, v.name));
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[42]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[43]++;
              break;

            } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[14]++;}
          }

        } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[12]++;}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[44]++;
int CodeCoverConditionCoverageHelper_C9;

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((isDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((isDeclaredInScope) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[15]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
          // Don't check the order of refer in externs files.
          if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((reference.getNode().isFromExterns()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[17]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[46]++;
            // Special case to deal with var goog = goog || {}
            Node grandparent = reference.getGrandparent();
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[47]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((grandparent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((grandparent.getString() == v.name) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[19]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[48]++;
              continue;

            } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[20]++;}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[49]++;
int CodeCoverConditionCoverageHelper_C12;

            // Only generate warnings if the scopes do not match in order
            // to deal with possible forward declarations and recursion
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((reference.getScope() == v.scope) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[21]++;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[50]++;
              String filename = NodeUtil.getSourceName(reference.getNode());
              compiler.report(
                  JSError.make(filename,
                               reference.getNode(),
                               checkLevel,
                               UNDECLARED_REFERENCE, v.name));
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[51]++;

            } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[22]++;}

          } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[16]++;}
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[52]++;
int CodeCoverConditionCoverageHelper_C13;

        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((isDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[23]++;
          blocksWithDeclarations.add(basicBlock);
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[53]++;
          isDeclaredInScope = true;
CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.statements[54]++;

        } else {
  CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd.branches[24]++;}
      }
    }
  }
}

class CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd ());
  }
    public static long[] statements = new long[55];
    public static long[] branches = new long[25];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[14];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.VariableReferenceCheck.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,3,1,2,1,2,1,1};
    for (int i = 1; i <= 13; i++) {
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

  public CodeCoverCoverageCounter$5iv3dfak47y1pdqtqoid2tcrwkkn76tixlyiwhhrhd () {
    super("com.google.javascript.jscomp.VariableReferenceCheck.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 54; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 24; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 13; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.VariableReferenceCheck.java");
      for (int i = 1; i <= 54; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 24; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 13; i++) {
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

