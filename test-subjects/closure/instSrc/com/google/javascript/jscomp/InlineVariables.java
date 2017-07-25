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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CodingConvention.SubclassRelationship;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Behavior;
import com.google.javascript.jscomp.ReferenceCollectingCallback.Reference;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceMap;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.rhino.Node;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Using the infrastructure provided by VariableReferencePass, identify
 * variables that are used only once and in a way that is safe to move, and then
 * inline them.
 *
 * This pass has two "modes." One mode only inlines variables declared as
 * constants, for legacy compiler clients. The second mode inlines any
 * variable that we can provably inline. Note that the second mode is a
 * superset of the first mode. We only support the first mode for
 * backwards-compatibility with compiler clients that don't want
 * --inline_variables.
 *
 * The approach of this pass is similar to {@link CrossModuleCodeMotion}
 *
 * @author kushal@google.com (Kushal Dave)
 * @author nicksantos@google.com (Nick Santos)
 */
class InlineVariables implements CompilerPass {
  static {
    CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.ping();
  }


  private final AbstractCompiler compiler;

  enum Mode {
    // Only inline things explicitly marked as constant.
    CONSTANTS_ONLY,
    // Locals only
    LOCALS_ONLY,
    ALL
  }

  private final Mode mode;

  // Inlines all strings, even if they increase the size of the gzipped binary.
  private final boolean inlineAllStrings;

  private final IdentifyConstants identifyConstants = new IdentifyConstants();
  {
    CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[1]++;
  }

  InlineVariables(
      AbstractCompiler compiler,
      Mode mode,
      boolean inlineAllStrings) {
    this.compiler = compiler;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[2]++;
    this.mode = mode;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[3]++;
    this.inlineAllStrings = inlineAllStrings;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[4]++;
  }

  @Override
  public void process(Node externs, Node root) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[5]++;
    ReferenceCollectingCallback callback = new ReferenceCollectingCallback(
        compiler, new InliningBehavior(), getFilterForMode());
    callback.process(externs, root);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[6]++;
  }

  private Predicate<Var> getFilterForMode() {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[7]++;
    switch (mode) {
      case ALL:
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[1]++;
        return Predicates.<Var>alwaysTrue();
      case LOCALS_ONLY:
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[2]++;
        return new IdentifyLocals();
      case CONSTANTS_ONLY:
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[3]++;
        return new IdentifyConstants();
      default:
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[4]++;
        throw new IllegalStateException();
    }
  }

  /**
   * Filters variables declared as "constant", and declares them in the outer
   * declaredConstants map.
   *
   * In Google coding conventions, this means anything declared with @const
   * or named in all caps, and initialized to an immutable value.
   * CheckConsts has already verified that these are truly constants.
   */
  private class IdentifyConstants implements Predicate<Var> {
    @Override
    public boolean apply(Var var) {
      return var.isConst();
    }
  }

  /**
   * Filters non-global variables.
   */
  private class IdentifyLocals implements Predicate<Var> {
    @Override
    public boolean apply(Var var) {
      return var.scope.isLocal();
    }
  }

  private static class AliasCandidate {
    private final Var alias;
    private final ReferenceCollection refInfo;

    AliasCandidate(Var alias, ReferenceCollection refInfo) {
      this.alias = alias;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[8]++;
      this.refInfo = refInfo;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[9]++;
    }
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
    CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[10]++;
  }

    /**
     * Stored possible aliases of variables that never change, with
     * all the reference info about those variables. Hashed by the NAME
     * node of the variable being aliased.
     */
    final Map<Node, AliasCandidate> aliasCandidates = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[11]++;
  }

    @Override
    public void afterExitScope(NodeTraversal t, ReferenceMap referenceMap) {
      collectAliasCandidates(t, referenceMap);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[12]++;
      doInlinesForScope(t, referenceMap);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[13]++;
    }

    /**
     * If any of the variables are well-defined and alias other variables,
     * mark them as aliasing candidates.
     */
    private void collectAliasCandidates(NodeTraversal t,
        ReferenceMap referenceMap) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[14]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((mode != Mode.CONSTANTS_ONLY) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[5]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (Iterator<Var> it = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false);) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[1]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[2]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[3]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[16]++;
          Var v = it.next();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[17]++;
          ReferenceCollection referenceInfo = referenceMap.getReferences(v);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[18]++;
int CodeCoverConditionCoverageHelper_C3;

          // NOTE(nicksantos): Don't handle variables that are never used.
          // The tests are much easier to write if you don't, and there's
          // another pass that handles unused variables much more elegantly.
          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (128)) == 0 || true) &&
 ((referenceInfo != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (32)) == 0 || true) &&
 ((referenceInfo.references.size() >= 2) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((referenceInfo.isWellDefined()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((referenceInfo.isAssignedOnceInLifetime()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 4) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 4) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[7]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[19]++;
            Reference init = referenceInfo.getInitializingReference();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[20]++;
            Node value = init.getAssignedValue();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[9]++;
              aliasCandidates.put(value, new AliasCandidate(v, referenceInfo));
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[22]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[10]++;}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[8]++;}
        }

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[6]++;}
    }

    /**
     * For all variables in this scope, see if they are only used once.
     * If it looks safe to do so, inline them.
     */
    private void doInlinesForScope(NodeTraversal t, ReferenceMap referenceMap) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[23]++;

      boolean maybeModifiedArguments =
          maybeEscapedOrModifiedArguments(t.getScope(), referenceMap);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[24]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
      for (Iterator<Var> it = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false);) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[4]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[5]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[6]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[25]++;
        Var v = it.next();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[26]++;

        ReferenceCollection referenceInfo = referenceMap.getReferences(v);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;

        // referenceInfo will be null if we're in constants-only mode
        // and the variable is not a constant.
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((referenceInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isVarInlineForbidden(v)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[11]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[28]++;
          // Never try to inline exported variables or variables that
          // were not collected or variables that have already been inlined.
          continue;

        } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[12]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[29]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isInlineableDeclaredConstant(v, referenceInfo)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[13]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[30]++;
          Reference init = referenceInfo.getInitializingReferenceForConstants();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[31]++;
          Node value = init.getAssignedValue();
          inlineDeclaredConstant(v, value, referenceInfo.references);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[32]++;
          staleVars.add(v);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[33]++;

        } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[14]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[34]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((mode == Mode.CONSTANTS_ONLY) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[15]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[35]++;
          // If we're in constants-only mode, don't run more aggressive
          // inlining heuristics. See InlineConstantsTest.
          continue;

        } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[16]++;
          inlineNonConstants(v, referenceInfo, maybeModifiedArguments);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[36]++;
        }
}
}
      }
    }

    private boolean maybeEscapedOrModifiedArguments(
        Scope scope, ReferenceMap referenceMap) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[37]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((scope.isLocal()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[17]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[38]++;
        Var arguments = scope.getArgumentsVar();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[39]++;
        ReferenceCollection refs = referenceMap.getReferences(arguments);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[40]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((refs != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((refs.references.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[19]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[41]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[7]++;


          for (Reference ref : refs.references) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[7]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[8]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[9]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[42]++;
            Node refNode = ref.getNode();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[43]++;
            Node refParent = ref.getParent();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[44]++;
int CodeCoverConditionCoverageHelper_C11;
            // Any reference that is not a read of the arguments property
            // consider a escape of the arguments object.
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((NodeUtil.isGet(refParent)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((refNode == ref.getParent().getFirstChild()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isLValue(refParent)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[21]++;
              return true;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[22]++;}
          }

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[20]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[18]++;}
      return false;
    }

    private boolean isLValue(Node n) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[45]++;
      Node parent = n.getParent();
      return (parent.isInc()
          || parent.isDec()
          || (NodeUtil.isAssignmentOp(parent)
          && parent.getFirstChild() == n));
    }

    private void inlineNonConstants(
        Var v, ReferenceCollection referenceInfo,
        boolean maybeModifiedArguments) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[46]++;
      int refCount = referenceInfo.references.size();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[47]++;
      Reference declaration = referenceInfo.references.get(0);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[48]++;
      Reference init = referenceInfo.getInitializingReference();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[49]++;
      int firstRefAfterInit = (declaration == init) ? 2 : 3;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((refCount > 1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isImmutableAndWellDefinedVariable(v, referenceInfo)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[23]++;
        // if the variable is referenced more than once, we can only
        // inline it if it's immutable and never defined before referenced.
        Node value;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((init != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[25]++;
          value = init.getAssignedValue();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[52]++;

        } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[26]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[53]++;
          // Create a new node for variable that is never initialized.
          Node srcLocation = declaration.getNode();
          value = NodeUtil.newUndefinedNode(srcLocation);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[54]++;
        }
        Preconditions.checkNotNull(value);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[55]++;
        inlineWellDefinedVariable(v, value, referenceInfo.references);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[56]++;
        staleVars.add(v);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[57]++;

      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[24]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[58]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((refCount == firstRefAfterInit) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[27]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[59]++;
        // The variable likely only read once, try some more
        // complex inlining heuristics.
        Reference reference = referenceInfo.references.get(
            firstRefAfterInit - 1);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[60]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((canInline(declaration, init, reference)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[29]++;
          inline(v, declaration, init, reference);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[61]++;
          staleVars.add(v);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[62]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[30]++;}

      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[28]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[63]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((declaration != init) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((refCount == 2) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[31]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[64]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((isValidDeclaration(declaration)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((isValidInitialization(init)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[33]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[65]++;
          // The only reference is the initialization, remove the assignment and
          // the variable declaration.
          Node value = init.getAssignedValue();
          Preconditions.checkNotNull(value);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[66]++;
          inlineWellDefinedVariable(v, value, referenceInfo.references);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[67]++;
          staleVars.add(v);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[68]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[34]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[32]++;}
}
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;

      // If this variable was not inlined normally, check if we can
      // inline an alias of it. (If the variable was inlined, then the
      // reference data is out of sync. We're better off just waiting for
      // the next pass.)
      if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (128)) == 0 || true) &&
 ((maybeModifiedArguments) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C18 |= (32)) == 0 || true) &&
 ((staleVars.contains(v)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((referenceInfo.isWellDefined()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((referenceInfo.isAssignedOnceInLifetime()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 4) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[35]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[70]++;
        List<Reference> refs = referenceInfo.references;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[71]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 1 /* start from a read */;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i < refs.size()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[10]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[11]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[12]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[72]++;
          Node nameNode = refs.get(i).getNode();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[73]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((aliasCandidates.containsKey(nameNode)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[37]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[74]++;
            AliasCandidate candidate = aliasCandidates.get(nameNode);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[75]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((staleVars.contains(candidate.alias)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((isVarInlineForbidden(candidate.alias)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[39]++;
              Reference aliasInit;
              aliasInit = candidate.refInfo.getInitializingReference();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[76]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[77]++;
              Node value = aliasInit.getAssignedValue();
              Preconditions.checkNotNull(value);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[78]++;
              inlineWellDefinedVariable(candidate.alias,
                  value,
                  candidate.refInfo.references);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[79]++;
              staleVars.add(candidate.alias);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[80]++;

            } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[40]++;}

          } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[38]++;}
        }

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[36]++;}
    }

    /**
     * If there are any variable references in the given node tree, blacklist
     * them to prevent the pass from trying to inline the variable.
     */
    private void blacklistVarReferencesInTree(Node root, Scope scope) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[81]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[13]++;


int CodeCoverConditionCoverageHelper_C22;
      for (Node c = root.getFirstChild();(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[13]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[14]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[15]++;
}
        blacklistVarReferencesInTree(c, scope);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[82]++;
      }
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[83]++;
int CodeCoverConditionCoverageHelper_C23;

      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((root.isName()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[41]++;
        staleVars.add(scope.getVar(root.getString()));
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[84]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[42]++;}
    }

    /**
     * Whether the given variable is forbidden from being inlined.
     */
    private boolean isVarInlineForbidden(Var var) {
      // A variable may not be inlined if:
      // 1) The variable is exported,
      // 2) A reference to the variable has been inlined. We're downstream
      //    of the mechanism that creates variable references, so we don't
      //    have a good way to update the reference. Just punt on it.
      // 3) Don't inline the special RENAME_PROPERTY_FUNCTION_NAME
      return var.isExtern()
          || compiler.getCodingConvention().isExported(var.name)
          || RenameProperties.RENAME_PROPERTY_FUNCTION_NAME.equals(var.name)
          || staleVars.contains(var);
    }

    /**
     * Do the actual work of inlining a single declaration into a single
     * reference.
     */
    private void inline(Var v, Reference declaration,
                        Reference init, Reference reference) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[85]++;
      Node value = init.getAssignedValue();
      Preconditions.checkState(value != null);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[86]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[87]++;
      // Check for function declarations before the value is moved in the AST.
      boolean isFunctionDeclaration = NodeUtil.isFunctionDeclaration(value);

      inlineValue(v, reference, value.detachFromParent());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[88]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[89]++;
int CodeCoverConditionCoverageHelper_C24;
      if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((declaration != init) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[43]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[90]++;
        Node expressRoot = init.getGrandparent();
        Preconditions.checkState(expressRoot.isExprResult());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[91]++;
        NodeUtil.removeChild(expressRoot.getParent(), expressRoot);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[92]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[44]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[93]++;
int CodeCoverConditionCoverageHelper_C25;

      // Function declarations have already been removed.
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isFunctionDeclaration) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[45]++;
        removeDeclaration(declaration);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[94]++;

      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[46]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[95]++;
      }
    }

    /**
     * Inline an immutable variable into all of its references.
     */
    private void inlineWellDefinedVariable(Var v, Node value,
        List<Reference> refSet) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[96]++;
      Reference decl = refSet.get(0);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[97]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[16]++;


int CodeCoverConditionCoverageHelper_C26;
      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i < refSet.size()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[16]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[17]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[18]++;
}
        inlineValue(v, refSet.get(i), value.cloneTree());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[98]++;
      }
      removeDeclaration(decl);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[99]++;
    }

    /**
     * Inline a declared constant.
     */
    private void inlineDeclaredConstant(Var v, Node value,
        List<Reference> refSet) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[100]++;
      // Replace the references with the constant value
      Reference decl = null;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[101]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[19]++;



      for (Reference r : refSet) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[19]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[20]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[21]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[102]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((r.getNode() == v.getNameNode()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[47]++;
          decl = r;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[103]++;

        } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[48]++;
          inlineValue(v, r, value.cloneTree());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[104]++;
        }
      }

      removeDeclaration(decl);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[105]++;
    }

    /**
     * Remove the given VAR declaration.
     */
    private void removeDeclaration(Reference declaration) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[106]++;
      Node varNode = declaration.getParent();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[107]++;
      Node grandparent = declaration.getGrandparent();

      varNode.removeChild(declaration.getNode());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[108]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[109]++;
int CodeCoverConditionCoverageHelper_C28;

      // Remove var node if empty
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((varNode.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[49]++;
        Preconditions.checkState(varNode.isVar());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[110]++;
        NodeUtil.removeChild(grandparent, varNode);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[111]++;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[50]++;}

      compiler.reportCodeChange();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[112]++;
    }

    /**
     * Replace the given reference with the given value node.
     *
     * @param v The variable that's referenced.
     * @param ref The reference to replace.
     * @param value The node tree to replace it with. This tree should be safe
     *     to re-parent.
     */
    private void inlineValue(Var v, Reference ref, Node value) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[113]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((ref.isSimpleAssignmentToName()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[51]++;
        // This is the initial assignment.
        ref.getGrandparent().replaceChild(ref.getParent(), value);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[114]++;

      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[52]++;
        ref.getParent().replaceChild(ref.getNode(), value);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[115]++;
      }

      blacklistVarReferencesInTree(value, v.scope);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[116]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[117]++;
    }

    /**
     * Determines whether the given variable is declared as a constant
     * and may be inlined.
     */
    private boolean isInlineableDeclaredConstant(Var var,
        ReferenceCollection refInfo) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[118]++;
int CodeCoverConditionCoverageHelper_C30;
      if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((identifyConstants.apply(var)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[53]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[54]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[119]++;
int CodeCoverConditionCoverageHelper_C31;

      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((refInfo.isAssignedOnceInLifetime()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[55]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[56]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[120]++;

      Reference init = refInfo.getInitializingReferenceForConstants();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[121]++;
int CodeCoverConditionCoverageHelper_C32;
      if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((init == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[57]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[58]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[122]++;

      Node value = init.getAssignedValue();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[123]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[59]++;
        // This constant is either externally defined or initialized indirectly
        // (e.g. in an function expression used to hide
        // temporary variables), so the constant is ineligible for inlining.
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[60]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[124]++;
int CodeCoverConditionCoverageHelper_C34;

      // Is the constant's value immutable?
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((NodeUtil.isImmutableValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[61]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[62]++;}

      // Determine if we should really inline a String or not.
      return !value.isString() ||
          isStringWorthInlining(var, refInfo.references);
    }

    /**
     * Compute whether the given string is worth inlining.
     */
    private boolean isStringWorthInlining(Var var, List<Reference> refs) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[125]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((inlineAllStrings) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((var.isDefine()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[63]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[126]++;
        int len = var.getInitialValue().getString().length() + "''".length();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[127]++;

        // if not inlined: var xx="value"; .. xx .. xx ..
        // The 4 bytes per reference is just a heuristic:
        // 2 bytes per var name plus maybe 2 bytes if we don't inline, e.g.
        // in the case of "foo " + CONST + " bar"
        int noInlineBytes = "var xx=;".length() + len +
                            4 * (refs.size() - 1);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[128]++;

        // if inlined:
        // I'm going to assume that half of the quotes will be eliminated
        // thanks to constant folding, therefore I subtract 1 (2/2=1) from
        // the string length.
        int inlineBytes = (len - 1) * (refs.size() - 1);

        // Not inlining if doing so uses more bytes, or this constant is being
        // defined.
        return noInlineBytes >= inlineBytes;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[64]++;}

      return true;
    }

    /**
     * @return true if the provided reference and declaration can be safely
     *         inlined according to our criteria
     */
    private boolean canInline(
        Reference declaration,
        Reference initialization,
        Reference reference) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[129]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C36 |= (32)) == 0 || true) &&
 ((isValidDeclaration(declaration)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((isValidInitialization(initialization)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((isValidReference(reference)) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 3) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[65]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[66]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[130]++;
int CodeCoverConditionCoverageHelper_C37;

      // If the value is read more than once, skip it.
      // VAR declarations and EXPR_RESULT don't need the value, but other
      // ASSIGN expressions parents do.
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((declaration != initialization) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((initialization.getGrandparent().isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[67]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[68]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[131]++;
int CodeCoverConditionCoverageHelper_C38;

      // Be very conservative and do no cross control structures or
      // scope boundaries
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((declaration.getBasicBlock() != initialization.getBasicBlock()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((declaration.getBasicBlock() != reference.getBasicBlock()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[69]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[70]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[132]++;

      // Do not inline into a call node. This would change
      // the context in which it was being called. For example,
      //   var a = b.c;
      //   a();
      // should not be inlined, because it calls a in the context of b
      // rather than the context of the window.
      //   var a = b.c;
      //   f(a)
      // is OK.
      Node value = initialization.getAssignedValue();
      Preconditions.checkState(value != null);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[133]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[134]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (32)) == 0 || true) &&
 ((value.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((reference.getParent().isCall()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((reference.getParent().getFirstChild() == reference.getNode()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 3) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[71]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[72]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[135]++;
int CodeCoverConditionCoverageHelper_C40;

      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((value.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[73]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[136]++;
        Node callNode = reference.getParent();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[137]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((reference.getParent().isCall()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[75]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[138]++;
          CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[139]++;
          // Bug 2388531: Don't inline subclass definitions into class defining
          // calls as this confused class removing logic.
          SubclassRelationship relationship =
              convention.getClassesDefinedByCall(callNode);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[140]++;
int CodeCoverConditionCoverageHelper_C42;
          if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((relationship != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[77]++;
            return false;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[78]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[141]++;
int CodeCoverConditionCoverageHelper_C43;

          // issue 668: Don't inline singleton getter methods
          // calls as this confused class removing logic.
          if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((convention.getSingletonGetterClassName(callNode) != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[79]++;
            return false;

          } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[80]++;}

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[76]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[74]++;}

      return canMoveAggressively(value) ||
          canMoveModerately(initialization, reference);
    }

    /**
     * If the value is a literal, we can cross more boundaries to inline it.
     */
    private boolean canMoveAggressively(Node value) {
      // Function expressions and other mutable objects can move within
      // the same basic block.
      return NodeUtil.isLiteralValue(value, true)
          || value.isFunction();
    }

    /**
     * If the value of a variable is not constant, then it may read or modify
     * state. Therefore it cannot be moved past anything else that may modify
     * the value being read or read values that are modified.
     */
    private boolean canMoveModerately(
        Reference initialization,
        Reference reference) {
      // Check if declaration can be inlined without passing
      // any side-effect causing nodes.
      Iterator<Node> it;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[142]++;
int CodeCoverConditionCoverageHelper_C44;
      if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((initialization.getParent().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[81]++;
        it = NodeIterators.LocalVarMotion.forVar(
            initialization.getNode(),     // NAME
            initialization.getParent(),       // VAR
            initialization.getGrandparent());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[143]++;
 // VAR container
      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[82]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[144]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((initialization.getParent().isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[83]++;
        Preconditions.checkState(
            initialization.getGrandparent().isExprResult());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[145]++;
        it = NodeIterators.LocalVarMotion.forAssign(
            initialization.getNode(),     // NAME
            initialization.getParent(),       // ASSIGN
            initialization.getGrandparent(),  // EXPR_RESULT
            initialization.getGrandparent().getParent());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[146]++;
 // EXPR container
      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[84]++;
        throw new IllegalStateException("Unexpected initialization parent " +
            initialization.getParent().toStringTree());
      }
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[147]++;
      Node targetName = reference.getNode();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[148]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[22]++;


int CodeCoverConditionCoverageHelper_C46;
      while ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[22]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[23]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[24]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[149]++;
        Node curNode = it.next();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[150]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((curNode == targetName) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[85]++;
          return true;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[86]++;}
      }

      return false;
    }

    /**
     * @return true if the reference is a normal VAR or FUNCTION declaration.
     */
    private boolean isValidDeclaration(Reference declaration) {
      return (declaration.getParent().isVar()
          && !declaration.getGrandparent().isFor())
          || NodeUtil.isFunctionDeclaration(declaration.getParent());
    }

    /**
     * @return Whether there is a initial value.
     */
    private boolean isValidInitialization(Reference initialization) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[151]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((initialization == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[87]++;
        return false;

      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[88]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[152]++;
int CodeCoverConditionCoverageHelper_C49; if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((initialization.isDeclaration()) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[89]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[153]++;
int CodeCoverConditionCoverageHelper_C50;
        // The reference is a FUNCTION declaration or normal VAR declaration
        // with a value.
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(initialization.getParent())) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((initialization.getNode().getFirstChild() == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[91]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[92]++;}

      } else {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[90]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[154]++;
        Node parent = initialization.getParent();
        Preconditions.checkState(
            parent.isAssign()
            && parent.getFirstChild() == initialization.getNode());
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[155]++;
      }
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[156]++;

      Node n = initialization.getAssignedValue();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[157]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((n.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[93]++;
        return compiler.getCodingConvention().isInlinableFunction(n);

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[94]++;}

      return true;
    }

    /**
     * @return true if the reference is a candidate for inlining
     */
    private boolean isValidReference(Reference reference) {
      return !reference.isDeclaration() && !reference.isLvalue();
    }

    /**
     * Determines whether the reference collection describes a variable that
     * is initialized to an immutable value, never modified, and defined before
     * every reference.
     */
    private boolean isImmutableAndWellDefinedVariable(Var v,
        ReferenceCollection refInfo) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[158]++;
      List<Reference> refSet = refInfo.references;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[159]++;
      int startingReadRef = 1;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[160]++;
      Reference refDecl = refSet.get(0);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[161]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((isValidDeclaration(refDecl)) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[95]++;
        return false;

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[96]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[162]++;

      boolean isNeverAssigned = refInfo.isNeverAssigned();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[163]++;
int CodeCoverConditionCoverageHelper_C53;
      // For values that are never assigned, only the references need to be
      // checked.
      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((isNeverAssigned) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[97]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[164]++;
        Reference refInit = refInfo.getInitializingReference();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[165]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((isValidInitialization(refInit)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[99]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[100]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[166]++;
int CodeCoverConditionCoverageHelper_C55;

        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((refDecl != refInit) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[101]++;
          Preconditions.checkState(refInit == refSet.get(1));
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[167]++;
          startingReadRef = 2;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[168]++;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[102]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[169]++;
int CodeCoverConditionCoverageHelper_C56;

        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((refInfo.isWellDefined()) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[103]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[104]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[170]++;

        Node value = refInit.getAssignedValue();
        Preconditions.checkNotNull(value);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[171]++;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[172]++;

        boolean isImmutableValueWorthInlining =
            NodeUtil.isImmutableValue(value) &&
            (!value.isString() ||
                isStringWorthInlining(v, refInfo.references));
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[173]++;
        boolean isInlinableThisAlias =
            value.isThis() &&
            !refInfo.isEscaped();
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[174]++;
int CodeCoverConditionCoverageHelper_C57;
        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((isImmutableValueWorthInlining) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((isInlinableThisAlias) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[105]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[106]++;}

      } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[98]++;}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[175]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[25]++;


int CodeCoverConditionCoverageHelper_C58;

      for (int i = startingReadRef;(((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((i < refSet.size()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[25]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[26]--;
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.loops[27]++;
}
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[176]++;
        Reference ref = refSet.get(i);
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.statements[177]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((isValidReference(ref)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[107]++;
          return false;

        } else {
  CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29.branches[108]++;}
      }

      return true;
    }
  }
}

class CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29 ());
  }
    public static long[] statements = new long[178];
    public static long[] branches = new long[109];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[60];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.InlineVariables.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,3,2,1,2,1,1,1,2,3,2,1,1,1,2,2,3,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,2,2,3,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1};
    for (int i = 1; i <= 59; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[28];

  public CodeCoverCoverageCounter$8kskhxcvkfhn6dzuu6jswntfelq8h29 () {
    super("com.google.javascript.jscomp.InlineVariables.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 177; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 108; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 59; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 27; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.InlineVariables.java");
      for (int i = 1; i <= 177; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 108; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 59; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 9; i++) {
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

