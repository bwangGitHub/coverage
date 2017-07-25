/*
 * Copyright 2011 The Closure Compiler Authors.
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
import com.google.common.base.Supplier;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Behavior;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Reference;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceMap;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Using the infrastructure provided by {@link ReferenceCollectingCallback},
 * identify variables that are only ever assigned to object literals
 * and that are never used in entirety, and expand the objects into
 * individual variables.
 *
 * Based on the InlineVariables pass
 *
 */
class InlineObjectLiterals implements CompilerPass {
  static {
    CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.ping();
  }


  public static final String VAR_PREFIX = "JSCompiler_object_inline_";
  static {
    CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[1]++;
  }

  private final AbstractCompiler compiler;

  private final Supplier<String> safeNameIdSupplier;

  InlineObjectLiterals(
      AbstractCompiler compiler,
      Supplier<String> safeNameIdSupplier) {
    this.compiler = compiler;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[2]++;
    this.safeNameIdSupplier = safeNameIdSupplier;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[3]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[4]++;
    ReferenceCollectingCallback callback = new ReferenceCollectingCallback(
        compiler, new InliningBehavior());
    callback.process(externs, root);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[5]++;
  }

  /**
   * Builds up information about nodes in each scope. When exiting the
   * scope, inspects all variables in that scope, and inlines any
   * that we can.
   */
  private class InliningBehavior implements Behavior {

    /**
     * A list of variables that should not be inlined, because their
     * reference information is out of sync with the state of the AST.
     */
    private final Set<Var> staleVars = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[6]++;
  }

    @Override
    public void afterExitScope(NodeTraversal t, ReferenceMap referenceMap) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
      for (Iterator<Var> it = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[1]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[2]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[3]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[8]++;
        Var v = it.next();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isVarInlineForbidden(v)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[1]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[10]++;
          continue;

        } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[2]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[11]++;

        ReferenceCollection referenceInfo = referenceMap.getReferences(v);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isInlinableObject(referenceInfo.references)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[3]++;
          // Blacklist the object itself, as well as any other values
          // that it refers to, since they will have been moved around.
          staleVars.add(v);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[13]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[14]++;

          Reference init = referenceInfo.getInitializingReference();

          // Split up the object into individual variables if the object
          // is never referenced directly in full.
          splitObject(v, init, referenceInfo);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[15]++;

        } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[4]++;}
      }
    }

    /**
     * If there are any variable references in the given node tree,
     * blacklist them to prevent the pass from trying to inline the
     * variable. Any code modifications will have potentially made the
     * ReferenceCollection invalid.
     */
    private void blacklistVarReferencesInTree(Node root, final Scope scope) {
      NodeUtil.visitPreOrder(root, new NodeUtil.Visitor() {
        @Override
        public void visit(Node node) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((node.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[5]++;
            staleVars.add(scope.getVar(node.getString()));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[18]++;

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[6]++;}
        }
      }, NodeUtil.MATCH_NOT_FUNCTION);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[16]++;
    }

    /**
     * Whether the given variable is forbidden from being inlined.
     */
    private boolean isVarInlineForbidden(Var var) {
      // A variable may not be inlined if:
      // 1) The variable is defined in the externs
      // 2) The variable is exported,
      // 3) Don't inline the special RENAME_PROPERTY_FUNCTION_NAME
      // 4) A reference to the variable has been inlined. We're downstream
      //    of the mechanism that creates variable references, so we don't
      //    have a good way to update the reference. Just punt on it.

      // Additionally, exclude global variables for now.

      return var.isGlobal()
          || var.isExtern()
          || compiler.getCodingConvention().isExported(var.name)
          || RenameProperties.RENAME_PROPERTY_FUNCTION_NAME.equals(var.name)
          || staleVars.contains(var);
    }

    /**
     * Counts the number of direct (full) references to an object.
     * Specifically, we check for references of the following type:
     * <pre>
     *   x;
     *   x.fn();
     * </pre>
     */
    private boolean isInlinableObject(List<Reference> refs) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[19]++;
      boolean ret = false;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[20]++;
      Set<String> validProperties = Sets.newHashSet();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[4]++;


      for (Reference ref : refs) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[4]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[5]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[6]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[22]++;
        Node name = ref.getNode();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[23]++;
        Node parent = ref.getParent();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[24]++;
        Node gramps = ref.getGrandparent();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;

        // Ignore most indirect references, like x.y (but not x.y(),
        // since the function referenced by y might reference 'this').
        //
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((parent.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[7]++;
          Preconditions.checkState(parent.getFirstChild() == name);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[26]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
          // A call target may be using the object as a 'this' value.
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((gramps.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((gramps.getFirstChild() == parent) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[9]++;
            return false;

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[10]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[28]++;
int CodeCoverConditionCoverageHelper_C7;

          // Deleting a property has different semantics from deleting
          // a variable, so deleted properties should not be inlined.
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((gramps.isDelProp()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[11]++;
            return false;

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[12]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[29]++;

          // NOTE(nicksantos): This pass's object-splitting algorithm has
          // a blind spot. It assumes that if a property isn't defined on an
          // object, then the value is undefined. This is not true, because
          // Object.prototype can have arbitrary properties on it.
          //
          // We short-circuit this problem by bailing out if we see a reference
          // to a property that isn't defined on the object literal. This
          // isn't a perfect algorithm, but it should catch most cases.
          String propName = parent.getLastChild().getString();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[30]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((validProperties.contains(propName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[13]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((NodeUtil.isVarOrSimpleAssignLhs(parent, gramps)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[15]++;
              validProperties.add(propName);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[32]++;

            } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[16]++;
              return false;
            }

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[14]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[33]++;
          continue;

        } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[8]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[34]++;
int CodeCoverConditionCoverageHelper_C10;

        // Only rewrite VAR declarations or simple assignment statements
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((isVarOrAssignExprLhs(name)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[17]++;
           return false;

        } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[18]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[35]++;

        Node val = ref.getAssignedValue();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[19]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[37]++;
          // A var with no assignment.
          continue;

        } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[20]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[38]++;
int CodeCoverConditionCoverageHelper_C12;

        // We're looking for object literal assignments only.
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((val.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[21]++;
          return false;

        } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[22]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[39]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[7]++;


int CodeCoverConditionCoverageHelper_C13;

        // Make sure that the value is not self-referential. IOW,
        // disallow things like x = {b: x.a}.
        //
        // TODO: Only exclude unorderable self-referential
        // assignments. i.e. x = {a: x.b, b: x.a} is not orderable,
        // but x = {a: 1, b: x.a} is.
        //
        // Also, ES5 getters/setters aren't handled by this pass.
        for (Node child = val.getFirstChild();(((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false);
             child = child.getNext()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[7]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[8]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[9]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[40]++;
int CodeCoverConditionCoverageHelper_C14;
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((child.isGetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((child.isSetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[23]++;
            // ES5 get/set not supported.
            return false;

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[24]++;}

          validProperties.add(child.getString());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[41]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[42]++;

          Node childVal = child.getFirstChild();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[43]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[10]++;


          // Check if childVal is the parent of any of the passed in
          // references, as that is how self-referential assignments
          // will happen.
          for (Reference t : refs) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[10]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[11]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[12]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[44]++;
            Node refNode = t.getParent();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[45]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
            while ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isStatementBlock(refNode)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[13]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[14]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[15]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[46]++;
int CodeCoverConditionCoverageHelper_C16;
              if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((refNode == childVal) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[25]++;
                // There's a self-referential assignment
                return false;

              } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[26]++;}
              refNode = refNode.getParent();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[47]++;
            }
          }
        }


        // We have found an acceptable object literal assignment. As
        // long as there are no other assignments that mess things up,
        // we can inline.
        ret = true;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[48]++;
      }
      return ret;
    }

    private boolean isVarOrAssignExprLhs(Node n) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[49]++;
      Node parent = n.getParent();
      return parent.isVar() ||
          (parent.isAssign()
              && parent.getFirstChild() == n
              && parent.getParent().isExprResult());
    }

    /**
     * Computes a list of ever-referenced keys in the object being
     * inlined, and returns a mapping of key name -> generated
     * variable name.
     */
    private Map<String, String> computeVarList(
        ReferenceCollection referenceInfo) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[50]++;
      Map<String, String> varmap = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[51]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[16]++;



      for (Reference ref : referenceInfo.references) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[16]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[17]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[18]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[52]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((ref.isLvalue()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ref.isInitializingDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[27]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[53]++;
          Node val = ref.getAssignedValue();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[54]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((val != null) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[29]++;
            Preconditions.checkState(val.isObjectLit());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[55]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[56]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[19]++;


int CodeCoverConditionCoverageHelper_C19;
            for (Node child = val.getFirstChild();(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false);
                 child = child.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[19]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[20]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[21]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[57]++;
              String varname = child.getString();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[58]++;
int CodeCoverConditionCoverageHelper_C20;
              if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((varmap.containsKey(varname)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[31]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[59]++;
                continue;

              } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[32]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[60]++;

              String var = VAR_PREFIX + varname + "_" +
                safeNameIdSupplier.get();
              varmap.put(varname, var);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[61]++;
            }

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[30]++;}

        } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[28]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[62]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((ref.getParent().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[33]++;

          // This is the var. There is no value.
        } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[34]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[63]++;
          Node getprop = ref.getParent();
          Preconditions.checkState(getprop.isGetProp());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[64]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[65]++;

          // The key being looked up in the original map.
          String varname = getprop.getLastChild().getString();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[66]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((varmap.containsKey(varname)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[35]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[67]++;
            continue;

          } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[36]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[68]++;

          String var = VAR_PREFIX + varname + "_" + safeNameIdSupplier.get();
          varmap.put(varname, var);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[69]++;
        }
}
      }

      return varmap;
    }

    /**
     * Populates a map of key names -> initial assigned values. The
     * object literal these are being pulled from is invalidated as
     * a result.
     */
    private void fillInitialValues(Reference init, Map<String, Node> initvals) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[70]++;
      Node object = init.getAssignedValue();
      Preconditions.checkState(object.isObjectLit());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[71]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[72]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[22]++;


int CodeCoverConditionCoverageHelper_C23;
      for (Node key = object.getFirstChild();(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false);
           key = key.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[22]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[23]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[24]++;
}
        initvals.put(key.getString(), key.removeFirstChild());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[73]++;
      }
    }

    /**
     * Replaces an assignment like x = {...} with t1=a,t2=b,t3=c,true.
     * Note that the resulting expression will always evaluate to
     * true, as would the x = {...} expression.
     */
    private void replaceAssignmentExpression(Var v, Reference ref,
                                             Map<String, String> varmap) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[74]++;
      // Compute all of the assignments necessary
      List<Node> nodes = Lists.newArrayList();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[75]++;
      Node val = ref.getAssignedValue();
      blacklistVarReferencesInTree(val, v.scope);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[76]++;
      Preconditions.checkState(val.isObjectLit());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[77]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[78]++;
      Set<String> all = Sets.newLinkedHashSet(varmap.keySet());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[79]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[25]++;


int CodeCoverConditionCoverageHelper_C24;
      for (Node key = val.getFirstChild();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false);
           key = key.getNext()) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[25]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[26]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[27]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[80]++;
        String var = key.getString();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[81]++;
        Node value = key.removeFirstChild();
        // TODO(user): Copy type information.
        nodes.add(
            IR.assign(
                IR.name(varmap.get(var)),
                value));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[82]++;
        all.remove(var);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[83]++;
      }
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[84]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[28]++;



      // TODO(user): Better source information.
      for (String var : all) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[28]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[29]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[30]++;
}
        nodes.add(
            IR.assign(
                IR.name(varmap.get(var)),
                NodeUtil.newUndefinedNode(null)));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[85]++;
      }

      Node replacement;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[86]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((nodes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[37]++;
        replacement = IR.trueNode();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[87]++;

      } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[38]++;
        // All assignments evaluate to true, so make sure that the
        // expr statement evaluates to true in case it matters.
        nodes.add(IR.trueNode());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[88]++;

        // Join these using COMMA.  A COMMA node must have 2 children, so we
        // create a tree. In the tree the first child be the COMMA to match
        // the parser, otherwise tree equality tests fail.
        nodes = Lists.reverse(nodes);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[89]++;
        replacement = new Node(Token.COMMA);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[90]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[91]++;
        Node cur = replacement;
        int i;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[92]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[31]++;


int CodeCoverConditionCoverageHelper_C26;
        for (i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < nodes.size() - 2) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[31]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[32]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[33]++;
}
          cur.addChildToFront(nodes.get(i));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[93]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[94]++;
          Node t = new Node(Token.COMMA);
          cur.addChildToFront(t);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[95]++;
          cur = t;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[96]++;
        }
        cur.addChildToFront(nodes.get(i));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[97]++;
        cur.addChildToFront(nodes.get(i + 1));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[98]++;
      }
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[99]++;

      Node replace = ref.getParent();
      replacement.copyInformationFromForTree(replace);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[100]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[101]++;
int CodeCoverConditionCoverageHelper_C27;

      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((replace.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[39]++;
        replace.getParent().replaceChild(
            replace, NodeUtil.newExpr(replacement));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[102]++;

      } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[40]++;
        replace.getParent().replaceChild(replace, replacement);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[103]++;
      }
    }

    /**
     * Splits up the object literal into individual variables, and
     * updates all uses.
     */
    private void splitObject(Var v, Reference init,
                             ReferenceCollection referenceInfo) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[104]++;
      // First figure out the FULL set of possible keys, so that they
      // can all be properly set as necessary.
      Map<String, String> varmap = computeVarList(referenceInfo);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[105]++;

      Map<String, Node> initvals = Maps.newHashMap();
      // Figure out the top-level of the var assign node. If it's a plain
      // ASSIGN, then there's an EXPR_STATEMENT above it, if it's a
      // VAR then it should be directly replaced.
      Node vnode;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[106]++;
      boolean defined = referenceInfo.isWellDefined() &&
          init.getParent().isVar();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[107]++;
int CodeCoverConditionCoverageHelper_C28;
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((defined) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[41]++;
        vnode = init.getParent();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[108]++;
        fillInitialValues(init, initvals);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[109]++;

      } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[42]++;
        // TODO(user): More test / rewrite this part.
        // Find the beginning of the function / script.
        vnode = v.getScope().getRootNode().getLastChild().getFirstChild();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[110]++;
      }
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[111]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[34]++;



      for (Map.Entry<String, String> entry : varmap.entrySet()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[34]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[35]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[36]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[112]++;
        Node val = initvals.get(entry.getKey());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[113]++;
        Node varnode = NodeUtil.newVarNode(entry.getValue(), val);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[114]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((val == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[43]++;
          // is this right?
          varnode.copyInformationFromForTree(vnode);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[115]++;

        } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[44]++;
          blacklistVarReferencesInTree(val, v.scope);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[116]++;
        }
        vnode.getParent().addChildBefore(varnode, vnode);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[117]++;
      }
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[118]++;
int CodeCoverConditionCoverageHelper_C30;

      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((defined) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[45]++;
        vnode.getParent().removeChild(vnode);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[119]++;

      } else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[46]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[120]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[37]++;



      for (Reference ref : referenceInfo.references) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[37]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[38]--;
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.loops[39]++;
}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[121]++;
int CodeCoverConditionCoverageHelper_C31;
        // The init/decl have already been converted.
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((defined) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ref == init) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[47]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[122]++; continue;
} else {
  CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[48]++;}
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[123]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((ref.isLvalue()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[49]++;
          // Assignments have to be handled specially, since they
          // expand out into multiple assignments.
          replaceAssignmentExpression(v, ref, varmap);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[124]++;

        } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[50]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[125]++;
int CodeCoverConditionCoverageHelper_C33; if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((ref.getParent().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[51]++;
          // The old variable declaration. It didn't have a
          // value. Remove it entirely as it should now be unused.
          ref.getGrandparent().removeChild(ref.getParent());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[126]++;

        } else {
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.branches[52]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[127]++;
          // Make sure that the reference is a GETPROP as we expect it to be.
          Node getprop = ref.getParent();
          Preconditions.checkState(getprop.isGetProp());
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[128]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[129]++;

          // The key being looked up in the original map.
          String var = getprop.getChildAtIndex(1).getString();

          // If the variable hasn't already been declared, add an empty
          // declaration near all the other declarations.
          Preconditions.checkState(varmap.containsKey(var));
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[130]++;
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[131]++;

          // Replace the GETPROP node with a NAME.
          Node replacement = IR.name(varmap.get(var));
          replacement.copyInformationFrom(getprop);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[132]++;
          ref.getGrandparent().replaceChild(ref.getParent(), replacement);
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[133]++;
        }
}
      }

      compiler.reportCodeChange();
CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx.statements[134]++;
    }
  }
}

class CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx ());
  }
    public static long[] statements = new long[135];
    public static long[] branches = new long[53];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[34];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InlineObjectLiterals.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 33; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$3ccmbkauwc7mvsl45i13ym3cor4hnm83f3tj4gx () {
    super("com.google.javascript.jscomp.InlineObjectLiterals.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 134; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 52; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InlineObjectLiterals.java");
      for (int i = 1; i <= 134; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 52; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 33; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

