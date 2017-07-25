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

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.javascript.jscomp.DefinitionsRemover.Definition;
import com.google.javascript.jscomp.NodeTraversal.ScopedCallback;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.graph.DiGraph;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal;
import com.google.javascript.jscomp.graph.FixedPointGraphTraversal.EdgeCallback;
import com.google.javascript.jscomp.graph.LinkedDirectedGraph;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.jstype.FunctionType;
import com.google.javascript.rhino.jstype.JSType;
import com.google.javascript.rhino.jstype.JSTypeNative;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Compiler pass that computes function purity.  A function is pure if
 * it has no outside visible side effects, and the result of the
 * computation does not depend on external factors that are beyond the
 * control of the application; repeated calls to the function should
 * return the same value as long as global state hasn't changed.
 *
 * Date.now is an example of a function that has no side effects but
 * is not pure.
 *
 *
 * We will prevail, in peace and freedom from fear, and in true
 * health, through the purity and essence of our natural... fluids.
 *                                    - General Turgidson
 */
class PureFunctionIdentifier implements CompilerPass {
  static {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.ping();
  }

  static final DiagnosticType INVALID_NO_SIDE_EFFECT_ANNOTATION =
      DiagnosticType.error(
          "JSC_INVALID_NO_SIDE_EFFECT_ANNOTATION",
          "@nosideeffects may only appear in externs files.");
  static {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[1]++;
  }

  static final DiagnosticType INVALID_MODIFIES_ANNOTATION =
    DiagnosticType.error(
        "JSC_INVALID_MODIFIES_ANNOTATION",
        "@modifies may only appear in externs files.");
  static {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[2]++;
  }

  private final AbstractCompiler compiler;
  private final DefinitionProvider definitionProvider;

  // Function node -> function side effects map
  private final Map<Node, FunctionInformation> functionSideEffectMap;

  // List of all function call sites; used to iterate in markPureFunctionCalls.
  private final List<Node> allFunctionCalls;

  // Externs and ast tree root, for use in getDebugReport.  These two
  // fields are null until process is called.
  private Node externs;
  private Node root;

  public PureFunctionIdentifier(AbstractCompiler compiler,
                                DefinitionProvider definitionProvider) {
    this.compiler = compiler;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[3]++;
    this.definitionProvider = definitionProvider;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[4]++;
    this.functionSideEffectMap = Maps.newHashMap();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[5]++;
    this.allFunctionCalls = Lists.newArrayList();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[6]++;
    this.externs = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[7]++;
    this.root = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[8]++;
  }

  @Override
  public void process(Node externsAst, Node srcAst) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((externs != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[1]++;
      throw new IllegalStateException(
          "It is illegal to call PureFunctionIdentifier.process " +
          "twice the same instance.  Please use a new " +
          "PureFunctionIdentifier instance each time.");

    } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[2]++;}

    externs = externsAst;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[10]++;
    root = srcAst;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[11]++;

    NodeTraversal.traverse(compiler, externs, new FunctionAnalyzer(true));
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[12]++;
    NodeTraversal.traverse(compiler, root, new FunctionAnalyzer(false));
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[13]++;

    propagateSideEffects();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[14]++;

    markPureFunctionCalls();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[15]++;
  }

  /**
   * Compute debug report that includes:
   *  - List of all pure functions.
   *  - Reasons we think the remaining functions have side effects.
   */
  String getDebugReport() {
    Preconditions.checkNotNull(externs);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[16]++;
    Preconditions.checkNotNull(root);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[17]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[18]++;

    StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[19]++;

    FunctionNames functionNames = new FunctionNames(compiler);
    functionNames.process(null, externs);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[20]++;
    functionNames.process(null, root);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[21]++;

    sb.append("Pure functions:\n");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[22]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[1]++;


    for (Map.Entry<Node, FunctionInformation> entry :
             functionSideEffectMap.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[1]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[2]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[3]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[24]++;
      Node function = entry.getKey();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[25]++;
      FunctionInformation functionInfo = entry.getValue();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[26]++;

      boolean isPure =
          functionInfo.mayBePure() && !functionInfo.mayHaveSideEffects();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[27]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isPure) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[3]++;
        sb.append("  " + functionNames.getFunctionName(function) + "\n");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[28]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[4]++;}
    }
    sb.append("\n");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[29]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[30]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[4]++;



    for (Map.Entry<Node, FunctionInformation> entry :
             functionSideEffectMap.entrySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[4]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[5]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[6]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[31]++;
      Node function = entry.getKey();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[32]++;
      FunctionInformation functionInfo = entry.getValue();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[33]++;

      Set<String> depFunctionNames = Sets.newHashSet();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[7]++;


      for (Node callSite : functionInfo.getCallsInFunctionBody()) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[7]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[8]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[9]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[35]++;
        Collection<Definition> defs =
            getCallableDefinitions(definitionProvider,
                                   callSite.getFirstChild());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[36]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((defs == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[5]++;
          depFunctionNames.add("<null def list>");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[37]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[38]++;
          continue;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[6]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[39]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[10]++;



        for (Definition def : defs) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[10]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[11]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[12]++;
}
          depFunctionNames.add(
              functionNames.getFunctionName(def.getRValue()));
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[40]++;
        }
      }

      sb.append(functionNames.getFunctionName(function) + " " +
                functionInfo.toString() +
                " Calls: " + depFunctionNames + "\n");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[41]++;
    }

    return sb.toString();
  }

  /**
   * Query the DefinitionProvider for the list of definitions that
   * correspond to a given qualified name subtree.  Return null if
   * DefinitionProvider does not contain an entry for a given name,
   * one or more of the values returned by getDeclarations is not
   * callable, or the "name" node is not a GETPROP or NAME.
   *
   * @param definitionProvider The name reference graph
   * @param name Query node
   * @return non-empty definition list or null
   */
  private static Collection<Definition> getCallableDefinitions(
      DefinitionProvider definitionProvider, Node name) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[42]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((name.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((name.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[7]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[43]++;
      List<Definition> result = Lists.newArrayList();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[44]++;

      Collection<Definition> decls =
          definitionProvider.getDefinitionsReferencedAt(name);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[45]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((decls == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[9]++;
        return null;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[10]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[46]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[13]++;



      for (Definition current : decls) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[13]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[14]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[15]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[47]++;
        Node rValue = current.getRValue();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[48]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((rValue != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((rValue.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[11]++;
          result.add(current);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[49]++;

        } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[12]++;
          return null;
        }
      }

      return result;

    } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[8]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[50]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((name.isOr()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((name.isHook()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[13]++;
      Node firstVal;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[51]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((name.isHook()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[15]++;
        firstVal = name.getFirstChild().getNext();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[52]++;

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[16]++;
        firstVal = name.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[53]++;
      }
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[54]++;

      Collection<Definition> defs1 = getCallableDefinitions(definitionProvider,
                                                            firstVal);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[55]++;
      Collection<Definition> defs2 = getCallableDefinitions(definitionProvider,
                                                            firstVal.getNext());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[56]++;
int CodeCoverConditionCoverageHelper_C9;
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((defs1 != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((defs2 != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[17]++;
        defs1.addAll(defs2);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[57]++;
        return defs1;

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[18]++;
        return null;
      }

    } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[14]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[58]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(name)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[19]++;
      // The anonymous function reference is also the definition.
      // TODO(user) Change SimpleDefinitionFinder so it is possible to query for
      // function expressions by function node.

      // isExtern is false in the call to the constructor for the
      // FunctionExpressionDefinition below because we know that
      // getCallableDefinitions() will only be called on the first
      // child of a call and thus the function expression
      // definition will never be an extern.
      return Lists.newArrayList(
          (Definition)
              new DefinitionsRemover.FunctionExpressionDefinition(name, false));

    } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[20]++;
      return null;
    }
}
}
  }

  /**
   * Propagate side effect information by building a graph based on
   * call site information stored in FunctionInformation and the
   * DefinitionProvider and then running GraphReachability to
   * determine the set of functions that have side effects.
   */
  private void propagateSideEffects() {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[59]++;
    // Nodes are function declarations; Edges are function call sites.
    DiGraph<FunctionInformation, Node> sideEffectGraph =
        LinkedDirectedGraph.createWithoutAnnotations();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[60]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[16]++;



    // create graph nodes
    for (FunctionInformation functionInfo : functionSideEffectMap.values()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[16]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[17]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[18]++;
}
      sideEffectGraph.createNode(functionInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[61]++;
    }
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[62]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[19]++;



    // add connections to called functions and side effect root.
    for (FunctionInformation functionInfo : functionSideEffectMap.values()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[19]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[20]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[21]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[63]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((functionInfo.mayHaveSideEffects()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[21]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[64]++;
        continue;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[22]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[65]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[22]++;



      for (Node callSite : functionInfo.getCallsInFunctionBody()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[22]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[23]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[24]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[66]++;
        Node callee = callSite.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[67]++;
        Collection<Definition> defs =
            getCallableDefinitions(definitionProvider, callee);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((defs == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[23]++;
          // Definition set is not complete or eligible.  Possible
          // causes include:
          //  * "callee" is not of type NAME or GETPROP.
          //  * One or more definitions are not functions.
          //  * One or more definitions are complex.
          //    (e.i. return value of a call that returns a function).
          functionInfo.setTaintsUnknown();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[69]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[70]++;
          break;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[24]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[71]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[25]++;



        for (Definition def : defs) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[25]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[26]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[27]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[72]++;
          Node defValue = def.getRValue();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[73]++;
          FunctionInformation dep = functionSideEffectMap.get(defValue);
          Preconditions.checkNotNull(dep);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[74]++;
          sideEffectGraph.connect(dep, callSite, functionInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[75]++;
        }
      }
    }

    // Propagate side effect information to a fixed point.
    FixedPointGraphTraversal.newTraversal(new SideEffectPropagationCallback())
        .computeFixedPoint(sideEffectGraph);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[76]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[77]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[28]++;



    // Mark remaining functions "pure".
    for (FunctionInformation functionInfo : functionSideEffectMap.values()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[28]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[29]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[30]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[78]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((functionInfo.mayBePure()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[25]++;
        functionInfo.setIsPure();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[79]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[26]++;}
    }
  }

  /**
   * Set no side effect property at pure-function call sites.
   */
  private void markPureFunctionCalls() {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[80]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[31]++;


    for (Node callNode : allFunctionCalls) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[31]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[32]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[33]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[81]++;
      Node name = callNode.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[82]++;
      Collection<Definition> defs =
          getCallableDefinitions(definitionProvider, name);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[83]++;
      // Default to side effects, non-local results
      Node.SideEffectFlags flags = new Node.SideEffectFlags();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[84]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((defs == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[27]++;
        flags.setMutatesGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[85]++;
        flags.setThrows();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[86]++;
        flags.setReturnsTainted();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[87]++;

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[28]++;
        flags.clearAllFlags();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[88]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[89]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[34]++;


        for (Definition def : defs) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[34]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[35]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[36]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[90]++;
          FunctionInformation functionInfo =
              functionSideEffectMap.get(def.getRValue());
          Preconditions.checkNotNull(functionInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[91]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[92]++;
int CodeCoverConditionCoverageHelper_C15;
          // TODO(johnlenz): set the arguments separately from the
          // global state flag.
          if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((functionInfo.mutatesGlobalState()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[29]++;
            flags.setMutatesGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[93]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[30]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[94]++;
int CodeCoverConditionCoverageHelper_C16;

          if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((functionInfo.functionThrows) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[31]++;
            flags.setThrows();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[95]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[32]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[96]++;
int CodeCoverConditionCoverageHelper_C17;

          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((callNode.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[33]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[97]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((functionInfo.taintsThis) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[35]++;
              flags.setMutatesThis();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[98]++;

            } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[36]++;}

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[34]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[99]++;
int CodeCoverConditionCoverageHelper_C19;

          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((functionInfo.taintsReturn) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[37]++;
            flags.setReturnsTainted();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[100]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[38]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[101]++;
int CodeCoverConditionCoverageHelper_C20;

          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((flags.areAllFlagsSet()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[39]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[102]++;
            break;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[40]++;}
        }
      }
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[103]++;
int CodeCoverConditionCoverageHelper_C21;

      // Handle special cases (Math, RegExp)
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((callNode.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[41]++;
        Preconditions.checkState(compiler != null);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[104]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[105]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((NodeUtil.functionCallHasSideEffects(callNode, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[43]++;
          flags.clearSideEffectFlags();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[106]++;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[44]++;}

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[42]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[107]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((callNode.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[45]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[108]++;
int CodeCoverConditionCoverageHelper_C24;
        // Handle known cases now (Object, Date, RegExp, etc)
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((NodeUtil.constructorCallHasSideEffects(callNode)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[47]++;
          flags.clearSideEffectFlags();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[109]++;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[48]++;}

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[46]++;}
}

      callNode.setSideEffectFlags(flags.valueOf());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[110]++;
    }
  }

  /**
   * Gather list of functions, functions with @nosideeffects
   * annotations, call sites, and functions that may mutate variables
   * not defined in the local scope.
   */
  private class FunctionAnalyzer implements ScopedCallback {
    private final boolean inExterns;

    FunctionAnalyzer(boolean inExterns) {
      this.inExterns = inExterns;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[111]++;
    }

    @Override
    public boolean shouldTraverse(NodeTraversal traversal,
                                  Node node,
                                  Node parent) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[112]++;
int CodeCoverConditionCoverageHelper_C25;

      // Functions need to be processed as part of pre-traversal so an
      // entry for the enclosing function exists in the
      // FunctionInformation map when processing assignments and calls
      // inside visit.
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((node.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[49]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[113]++;
        Node gramp = parent.getParent();
        visitFunction(traversal, node, parent, gramp);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[114]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[50]++;}

      return true;
    }

    @Override
    public void visit(NodeTraversal traversal, Node node, Node parent) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[115]++;
int CodeCoverConditionCoverageHelper_C26;

      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[51]++;
        return;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[52]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[116]++;
int CodeCoverConditionCoverageHelper_C27;

      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((NodeUtil.nodeTypeMayHaveSideEffects(node)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((node.isReturn()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[53]++;
        return;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[54]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[117]++;
int CodeCoverConditionCoverageHelper_C28;

      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((node.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((node.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[55]++;
        allFunctionCalls.add(node);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[118]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[56]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[119]++;

      Node enclosingFunction = traversal.getEnclosingFunction();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[120]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((enclosingFunction != null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[57]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[121]++;
        FunctionInformation sideEffectInfo =
            functionSideEffectMap.get(enclosingFunction);
        Preconditions.checkNotNull(sideEffectInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[122]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[123]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(node)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[59]++;
          visitAssignmentOrUnaryOperator(
              sideEffectInfo, traversal.getScope(),
              node, node.getFirstChild(), node.getLastChild());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[124]++;

        } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[60]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[125]++;
          switch(node.getType()) {
            case Token.CALL:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[61]++;
            case Token.NEW:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[62]++;
              visitCall(sideEffectInfo, node);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[126]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[127]++;
              break;
            case Token.DELPROP:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[63]++;
            case Token.DEC:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[64]++;
            case Token.INC:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[65]++;
              visitAssignmentOrUnaryOperator(
                  sideEffectInfo, traversal.getScope(),
                  node, node.getFirstChild(), null);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[128]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[129]++;
              break;
            case Token.NAME:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[66]++;
              // Variable definition are not side effects.
              // Just check that the name appears in the context of a
              // variable declaration.
              Preconditions.checkArgument(
                  NodeUtil.isVarDeclaration(node));
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[130]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[131]++;
              Node value = node.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[132]++;
int CodeCoverConditionCoverageHelper_C31;
              // Assignment to local, if the value isn't a safe local value,
              // new object creation or literal or known primitive result
              // value, add it to the local blacklist.
              if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((value != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((NodeUtil.evaluatesToLocalValue(value)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[67]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[133]++;
                Scope scope = traversal.getScope();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[134]++;
                Var var = scope.getVar(node.getString());
                sideEffectInfo.blacklistLocal(var);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[135]++;

              } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[68]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[136]++;
              break;
            case Token.THROW:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[69]++;
              visitThrow(sideEffectInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[137]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[138]++;
              break;
            case Token.RETURN:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[70]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[139]++;
int CodeCoverConditionCoverageHelper_C32;
              if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((node.hasChildren()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((NodeUtil.evaluatesToLocalValue(node.getFirstChild())) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[71]++;
                sideEffectInfo.setTaintsReturn();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[140]++;

              } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[72]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[141]++;
              break;
            default:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[73]++;
              throw new IllegalArgumentException(
                  "Unhandled side effect node type " +
                  Token.name(node.getType()));
          }
        }

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[58]++;}
    }

    @Override
    public void enterScope(NodeTraversal t) {
      // Nothing to do.
    }

    @Override
    public void exitScope(NodeTraversal t) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[142]++;
int CodeCoverConditionCoverageHelper_C33;
      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[74]++;
        return;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[75]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[143]++;

      // Handle deferred local variable modifications:
      //
      FunctionInformation sideEffectInfo =
        functionSideEffectMap.get(t.getScopeRoot());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[144]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((sideEffectInfo.mutatesGlobalState()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)){
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[76]++;
        sideEffectInfo.resetLocalVars();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[145]++;
        return;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[77]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[146]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[37]++;


int CodeCoverConditionCoverageHelper_C35;

      for (Iterator<Var> i = t.getScope().getVars();(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false);) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[37]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[38]--;
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.loops[39]++;
}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[147]++;
        Var v = i.next();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[148]++;
        boolean localVar = false;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[149]++;
int CodeCoverConditionCoverageHelper_C36;
        // Parameters and catch values come can from other scopes.
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((v.getParentNode().isVar()) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[78]++;
          // TODO(johnlenz): create a useful parameter list
          sideEffectInfo.knownLocals.add(v.getName());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[150]++;
          localVar = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[151]++;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[79]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[152]++;
int CodeCoverConditionCoverageHelper_C37;

        // Take care of locals that might have been tainted.
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((localVar) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((sideEffectInfo.blacklisted.contains(v)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[80]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[153]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((sideEffectInfo.taintedLocals.contains(v)) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[82]++;
            // If the function has global side-effects
            // don't bother with the local side-effects.
            sideEffectInfo.setTaintsUnknown();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[154]++;
            sideEffectInfo.resetLocalVars();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[155]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[156]++;
            break;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[83]++;}

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[81]++;}
      }

      sideEffectInfo.taintedLocals = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[157]++;
      sideEffectInfo.blacklisted = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[158]++;
    }


    /**
     * Record information about the side effects caused by an
     * assignment or mutating unary operator.
     *
     * If the operation modifies this or taints global state, mark the
     * enclosing function as having those side effects.
     * @param op operation being performed.
     * @param lhs The store location (name or get) being operated on.
     * @param rhs The right have value, if any.
     */
    private void visitAssignmentOrUnaryOperator(
        FunctionInformation sideEffectInfo,
        Scope scope, Node op, Node lhs, Node rhs) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[159]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((lhs.isName()) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[84]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[160]++;
        Var var = scope.getVar(lhs.getString());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[161]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((var.scope != scope) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[86]++;
          sideEffectInfo.setTaintsGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[162]++;

        } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[87]++;
          // Assignment to local, if the value isn't a safe local value,
          // a literal or new object creation, add it to the local blacklist.
          // parameter values depend on the caller.

          // Note: other ops result in the name or prop being assigned a local
          // value (x++ results in a number, for instance)
          Preconditions.checkState(
              NodeUtil.isAssignmentOp(op)
              || isIncDec(op) || op.isDelProp());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[163]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[164]++;
int CodeCoverConditionCoverageHelper_C41;
          if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (32)) == 0 || true) &&
 ((rhs != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((op.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((NodeUtil.evaluatesToLocalValue(rhs)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 3) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[88]++;
            sideEffectInfo.blacklistLocal(var);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[165]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[89]++;}
        }

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[85]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[166]++;
int CodeCoverConditionCoverageHelper_C42; if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(lhs)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[90]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[167]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((lhs.getFirstChild().isThis()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[92]++;
          sideEffectInfo.setTaintsThis();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[168]++;

        } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[93]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[169]++;
          Var var = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[170]++;
          Node objectNode = lhs.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[171]++;
int CodeCoverConditionCoverageHelper_C44;
          if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((objectNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[94]++;
            var = scope.getVar(objectNode.getString());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[172]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[95]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[173]++;
int CodeCoverConditionCoverageHelper_C45;
          if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((var == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((var.scope != scope) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[96]++;
            sideEffectInfo.setTaintsUnknown();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[174]++;

          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[97]++;
            // Maybe a local object modification.  We won't know for sure until
            // we exit the scope and can validate the value of the local.
            //
            sideEffectInfo.addTaintedLocalObject(var);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[175]++;
          }
        }

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[91]++;
        // TODO(johnlenz): track down what is inserting NULL on the LHS
        // of an assign.

        // The only valid LHS expressions are NAME, GETELEM, or GETPROP.
        // throw new IllegalStateException(
        //     "Unexpected LHS expression:" + lhs.toStringTree()
        //    + ", parent: " + op.toStringTree() );
        sideEffectInfo.setTaintsUnknown();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[176]++;
      }
}
    }

    /**
     * Record information about a call site.
     */
    private void visitCall(FunctionInformation sideEffectInfo, Node node) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[177]++;
int CodeCoverConditionCoverageHelper_C46;
      // Handle special cases (Math, RegExp)
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((node.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((NodeUtil.functionCallHasSideEffects(node, compiler)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[98]++;
        return;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[99]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[178]++;
int CodeCoverConditionCoverageHelper_C47;

      // Handle known cases now (Object, Date, RegExp, etc)
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((node.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((NodeUtil.constructorCallHasSideEffects(node)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[100]++;
        return;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[101]++;}

      sideEffectInfo.appendCall(node);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[179]++;
    }

    /**
     * Record function and check for @nosideeffects annotations.
     */
    private void visitFunction(NodeTraversal traversal,
                               Node node,
                               Node parent,
                               Node gramp) {
      Preconditions.checkArgument(!functionSideEffectMap.containsKey(node));
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[180]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[181]++;

      FunctionInformation sideEffectInfo = new FunctionInformation(inExterns);
      functionSideEffectMap.put(node, sideEffectInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[182]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[183]++;
int CodeCoverConditionCoverageHelper_C48;

      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[102]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[184]++;
        JSType jstype = node.getJSType();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[185]++;
        boolean knownLocalResult = false;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[186]++;
        FunctionType functionType = JSType.toMaybeFunctionType(jstype);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[187]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((functionType != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[104]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[188]++;
          JSType jstypeReturn = functionType.getReturnType();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[189]++;
int CodeCoverConditionCoverageHelper_C50;
          if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((isLocalValueType(jstypeReturn)) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[106]++;
            knownLocalResult = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[190]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[107]++;}

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[105]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[191]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((knownLocalResult) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[108]++;
          sideEffectInfo.setTaintsReturn();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[192]++;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[109]++;}

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[103]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[193]++;

      JSDocInfo info = getJSDocInfoForFunction(node, parent, gramp);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[194]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[110]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[195]++;
        boolean hasSpecificSideEffects = false;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[196]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((hasSideEffectsThisAnnotation(info)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[112]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[197]++;
int CodeCoverConditionCoverageHelper_C54;
          if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[114]++;
            hasSpecificSideEffects = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[198]++;
            sideEffectInfo.setTaintsThis();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[199]++;

          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[115]++;
            traversal.report(node, INVALID_MODIFIES_ANNOTATION);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[200]++;
          }

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[113]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[201]++;
int CodeCoverConditionCoverageHelper_C55;

        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((hasSideEffectsArgumentsAnnotation(info)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[116]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[202]++;
int CodeCoverConditionCoverageHelper_C56;
          if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[118]++;
            hasSpecificSideEffects = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[203]++;
            sideEffectInfo.setTaintsArguments();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[204]++;

          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[119]++;
            traversal.report(node, INVALID_MODIFIES_ANNOTATION);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[205]++;
          }

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[117]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[206]++;
int CodeCoverConditionCoverageHelper_C57;

        if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((info.getThrownTypes().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[120]++;
          hasSpecificSideEffects = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[207]++;
          sideEffectInfo.setFunctionThrows();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[208]++;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[121]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[209]++;
int CodeCoverConditionCoverageHelper_C58;

        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((hasSpecificSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[122]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[210]++;
int CodeCoverConditionCoverageHelper_C59;
          if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((hasNoSideEffectsAnnotation(info)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[124]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[211]++;
int CodeCoverConditionCoverageHelper_C60;
            if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[126]++;
              sideEffectInfo.setIsPure();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[212]++;

            } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[127]++;
              traversal.report(node, INVALID_NO_SIDE_EFFECT_ANNOTATION);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[213]++;
            }

          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[125]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[214]++;
int CodeCoverConditionCoverageHelper_C61; if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[128]++;
            sideEffectInfo.setTaintsGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[215]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[129]++;}
}

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[123]++;}

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[111]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[216]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((inExterns) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[130]++;
          sideEffectInfo.setTaintsGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[217]++;

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[131]++;}
      }
    }

    /**
     * @return Whether the jstype is something known to be a local value.
     */
    private boolean isLocalValueType(JSType jstype) {
      Preconditions.checkNotNull(jstype);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[218]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[219]++;
      JSType subtype =  jstype.getGreatestSubtype(
          compiler.getTypeRegistry().getNativeType(JSTypeNative.OBJECT_TYPE));
      // If the type includes anything related to a object type, don't assume
      // anything about the locality of the value.
      return subtype.isNoType();
    }

    /**
     * Record that the enclosing function throws.
     */
    private void visitThrow(FunctionInformation sideEffectInfo) {
      sideEffectInfo.setFunctionThrows();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[220]++;
    }

    /**
     * Get the doc info associated with the function.
     */
    private JSDocInfo getJSDocInfoForFunction(
        Node node, Node parent, Node gramp) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[221]++;
      JSDocInfo info = node.getJSDocInfo();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[222]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[132]++;
        return info;

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[133]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[223]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[134]++;
        return gramp.hasOneChild() ? gramp.getJSDocInfo() : null;

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[135]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[224]++;
int CodeCoverConditionCoverageHelper_C65; if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[136]++;
        return parent.getJSDocInfo();

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[137]++;
        return null;
      }
}
}
    }

    /**
     * Get the value of the @nosideeffects annotation stored in the
     * doc info.
     */
    private boolean hasNoSideEffectsAnnotation(JSDocInfo docInfo) {
      Preconditions.checkNotNull(docInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[225]++;
      return docInfo.isNoSideEffects();
    }

    /**
     * Get the value of the @modifies{this} annotation stored in the
     * doc info.
     */
    private boolean hasSideEffectsThisAnnotation(JSDocInfo docInfo) {
      Preconditions.checkNotNull(docInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[226]++;
      return (docInfo.getModifies().contains("this"));
    }

    /**
     * @returns Whether the @modifies annotation includes "arguments"
     * or any named parameters.
     */
    private boolean hasSideEffectsArgumentsAnnotation(JSDocInfo docInfo) {
      Preconditions.checkNotNull(docInfo);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[227]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[228]++;
      Set<String> modifies = docInfo.getModifies();
      // TODO(johnlenz): if we start tracking parameters individually
      // this should simply be a check for "arguments".
      return (modifies.size() > 1
          || (modifies.size() == 1 && !modifies.contains("this")));
    }
  }

  private static boolean isIncDec(Node n) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[229]++;
    int type = n.getType();
    return (type == Token.INC || type == Token.DEC);
  }

  /**
   * @return Whether the node is known to be a value that is not a reference
   *     outside the local scope.
   */
  @SuppressWarnings("unused")
  private static boolean isKnownLocalValue(final Node value) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[230]++;
    Predicate<Node> taintingPredicate = new Predicate<Node>() {
      @Override
      public boolean apply(Node value) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[231]++;
        switch (value.getType()) {
          case Token.ASSIGN:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[138]++;
            // The assignment might cause an alias, look at the LHS.
            return false;
          case Token.THIS:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[139]++;
            // TODO(johnlenz): maybe redirect this to be a tainting list for 'this'.
            return false;
          case Token.NAME:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[140]++;
            // TODO(johnlenz): add to local tainting list, if the NAME
            // is known to be a local.
            return false;
          case Token.GETELEM:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[141]++;
          case Token.GETPROP:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[142]++;
            // There is no information about the locality of object properties.
            return false;
          case Token.CALL:
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[143]++;
            // TODO(johnlenz): add to local tainting list, if the call result
            // is not known to be a local result.
            return false; default : CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[144]++;
        }
        return false;
      }
    };

    return NodeUtil.evaluatesToLocalValue(value, taintingPredicate);
  }

  /**
   * Callback that propagates side effect information across call sites.
   */
  private static class SideEffectPropagationCallback
      implements EdgeCallback<FunctionInformation, Node> {
    @Override
    public boolean traverseEdge(FunctionInformation callee,
                                Node callSite,
                                FunctionInformation caller) {
      Preconditions.checkArgument(callSite.isCall() ||
                                  callSite.isNew());
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[232]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[233]++;

      boolean changed = false;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[234]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C66 |= (8)) == 0 || true) &&
 ((caller.mutatesGlobalState()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((callee.mutatesGlobalState()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[145]++;
        caller.setTaintsGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[235]++;
        changed = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[236]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[146]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[237]++;
int CodeCoverConditionCoverageHelper_C67;

      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((caller.functionThrows()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((callee.functionThrows()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[147]++;
        caller.setFunctionThrows();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[238]++;
        changed = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[239]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[148]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[240]++;
int CodeCoverConditionCoverageHelper_C68;

      if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((callee.mutatesThis()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[149]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[241]++;
int CodeCoverConditionCoverageHelper_C69;
        // Side effects only propagate via regular calls.
        // Calling a constructor that modifies "this" has no side effects.
        if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((callSite.isNew()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[151]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[242]++;
          Node objectNode = getCallThisObject(callSite);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[243]++;
int CodeCoverConditionCoverageHelper_C70;
          if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (32)) == 0 || true) &&
 ((objectNode != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((objectNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((isCallOrApply(callSite)) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[153]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[244]++;
int CodeCoverConditionCoverageHelper_C71;
            // Exclude ".call" and ".apply" as the value may still be
            // null or undefined. We don't need to worry about this with a
            // direct method call because null and undefined don't have any
            // properties.

            // TODO(nicksantos): Turn this back on when locals-tracking
            // is fixed. See testLocalizedSideEffects11.
            //if (!caller.knownLocals.contains(name)) {
              if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((caller.mutatesGlobalState()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[155]++;
                caller.setTaintsGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[245]++;
                changed = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[246]++;

              } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[156]++;}

            //}
          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[154]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[247]++;
int CodeCoverConditionCoverageHelper_C72; if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (8)) == 0 || true) &&
 ((objectNode != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((objectNode.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[157]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[248]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((caller.mutatesThis()) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[159]++;
              caller.setTaintsThis();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[249]++;
              changed = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[250]++;

            } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[160]++;}

          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[158]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[251]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (32)) == 0 || true) &&
 ((objectNode != null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((NodeUtil.evaluatesToLocalValue(objectNode)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((isCallOrApply(callSite)) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 3) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 3) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[161]++;

            // Modifying 'this' on a known local object doesn't change any
            // significant state.
            // TODO(johnlenz): We can improve this by including literal values
            // that we know for sure are not null.
          } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[162]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[252]++;
int CodeCoverConditionCoverageHelper_C75; if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((caller.mutatesGlobalState()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[163]++;
            caller.setTaintsGlobalState();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[253]++;
            changed = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[254]++;

          } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[164]++;}
}
}
}

        } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[152]++;}

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[150]++;}

      return changed;
    }
  }

  /**
   * Analyze a call site and extract the node that will be act as
   * "this" inside the call, which is either the object part of the
   * qualified function name, the first argument to the call in the
   * case of ".call" and ".apply" or null if object is not specified
   * in either of those ways.
   *
   * @return node that will act as "this" for the call.
   */
  private static Node getCallThisObject(Node callSite) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[255]++;
    Node callTarget = callSite.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[256]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(callTarget)) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[165]++;

      // "this" is not specified explicitly; call modifies global "this".
      return null;

    } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[166]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[257]++;

    String propString = callTarget.getLastChild().getString();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[258]++;
int CodeCoverConditionCoverageHelper_C77;
    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (8)) == 0 || true) &&
 ((propString.equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((propString.equals("apply")) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[167]++;
      return callTarget.getNext();

    } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[168]++;
      return callTarget.getFirstChild();
    }
  }

  private static boolean isCallOrApply(Node callSite) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[259]++;
    Node callTarget = callSite.getFirstChild();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[260]++;
int CodeCoverConditionCoverageHelper_C78;
    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(callTarget)) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[169]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[261]++;
      String propString = callTarget.getLastChild().getString();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[262]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((propString.equals("call")) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((propString.equals("apply")) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[171]++;
        return true;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[172]++;}

    } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[170]++;}
    return false;
  }

  /**
   * Keeps track of a function's known side effects by type and the
   * list of calls that appear in a function's body.
   */
  private static class FunctionInformation {
    private final boolean extern;
    private final List<Node> callsInFunctionBody = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[263]++;
  }
    private Set<Var> blacklisted = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[264]++;
  }
    private Set<Var> taintedLocals = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[265]++;
  }
    private Set<String> knownLocals = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[266]++;
  }
    private boolean pureFunction = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[267]++;
  }
    private boolean functionThrows = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[268]++;
  }
    private boolean taintsGlobalState = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[269]++;
  }
    private boolean taintsThis = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[270]++;
  }
    private boolean taintsArguments = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[271]++;
  }
    private boolean taintsUnknown = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[272]++;
  }
    private boolean taintsReturn = false;
  {
    CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[273]++;
  }

    FunctionInformation(boolean extern) {
      this.extern = extern;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[274]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[275]++;
    }

    /**
     * @param var
     */
    void addTaintedLocalObject(Var var) {
      taintedLocals.add(var);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[276]++;
    }

    void resetLocalVars() {
      blacklisted = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[277]++;
      taintedLocals = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[278]++;
      knownLocals = Collections.emptySet();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[279]++;
    }

    /**
     * @param var
     */
    public void blacklistLocal(Var var) {
      blacklisted.add(var);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[280]++;
    }

    /**
     * @returns false if function known to have side effects.
     */
    boolean mayBePure() {
      return !(functionThrows ||
               taintsGlobalState ||
               taintsThis ||
               taintsArguments ||
               taintsUnknown);
    }

    /**
     * @returns false if function known to be pure.
     */
    boolean mayHaveSideEffects() {
      return !pureFunction;
    }

    /**
     * Mark the function as being pure.
     */
    void setIsPure() {
      pureFunction = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[281]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[282]++;
    }

    /**
     * Marks the function as having "modifies globals" side effects.
     */
    void setTaintsGlobalState() {
      taintsGlobalState = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[283]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[284]++;
    }

    /**
     * Marks the function as having "modifies this" side effects.
     */
    void setTaintsThis() {
      taintsThis = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[285]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[286]++;
    }

    /**
     * Marks the function as having "modifies arguments" side effects.
     */
    void setTaintsArguments() {
      taintsArguments = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[287]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[288]++;
    }

    /**
     * Marks the function as having "throw" side effects.
     */
    void setFunctionThrows() {
      functionThrows = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[289]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[290]++;
    }

    /**
     * Marks the function as having "complex" side effects that are
     * not otherwise explicitly tracked.
     */
    void setTaintsUnknown() {
      taintsUnknown = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[291]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[292]++;
    }

    /**
     * Marks the function as having non-local return result.
     */
    void setTaintsReturn() {
      taintsReturn = true;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[293]++;
      checkInvariant();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[294]++;
    }


    /**
     * Returns true if function mutates global state.
     */
    boolean mutatesGlobalState() {
      // TODO(johnlenz): track arguments separately.
      return taintsGlobalState || taintsArguments || taintsUnknown;
    }

    /**
     * Returns true if function mutates "this".
     */
    boolean mutatesThis() {
      return taintsThis;
    }

    /**
     * Returns true if function has an explicit "throw".
     */
    boolean functionThrows() {
      return functionThrows;
    }

    /**
     * Verify internal consistency.  Should be called at the end of
     * every method that mutates internal state.
     */
    private void checkInvariant() {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[295]++;
      boolean invariant = mayBePure() || mayHaveSideEffects();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[296]++;
int CodeCoverConditionCoverageHelper_C80;
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((invariant) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[173]++;
        throw new IllegalStateException("Invariant failed.  " + toString());

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[174]++;}
    }

    /**
     * Add a CALL or NEW node to the list of calls this function makes.
     */
    void appendCall(Node callNode) {
      callsInFunctionBody.add(callNode);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[297]++;
    }

    /**
     * Gets the list of CALL and NEW nodes.
     */
    List<Node> getCallsInFunctionBody() {
      return callsInFunctionBody;
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[298]++;
      List<String> status = Lists.newArrayList();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[299]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((extern) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[175]++;
        status.add("extern");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[300]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[176]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[301]++;
int CodeCoverConditionCoverageHelper_C82;

      if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((pureFunction) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[177]++;
        status.add("pure");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[302]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[178]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[303]++;
int CodeCoverConditionCoverageHelper_C83;

      if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((taintsThis) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[179]++;
        status.add("this");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[304]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[180]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[305]++;
int CodeCoverConditionCoverageHelper_C84;

      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((taintsGlobalState) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[181]++;
        status.add("global");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[306]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[182]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[307]++;
int CodeCoverConditionCoverageHelper_C85;

      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((functionThrows) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[183]++;
        status.add("throw");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[308]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[184]++;}
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[309]++;
int CodeCoverConditionCoverageHelper_C86;

      if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((taintsUnknown) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[185]++;
        status.add("complex");
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[310]++;

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[186]++;}

      return "Side effects: " + status.toString();
    }
  }

  /**
   * A compiler pass that constructs a reference graph and drives
   * the PureFunctionIdentifier across it.
   */
  static class Driver implements CompilerPass {
    private final AbstractCompiler compiler;
    private final String reportPath;
    private final boolean useNameReferenceGraph;

    Driver(AbstractCompiler compiler, String reportPath,
        boolean useNameReferenceGraph) {
      this.compiler = compiler;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[311]++;
      this.reportPath = reportPath;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[312]++;
      this.useNameReferenceGraph = useNameReferenceGraph;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[313]++;
    }

    @Override
    public void process(Node externs, Node root) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[314]++;
      DefinitionProvider definitionProvider = null;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[315]++;
int CodeCoverConditionCoverageHelper_C87;
      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((useNameReferenceGraph) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[187]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[316]++;
        NameReferenceGraphConstruction graphBuilder =
            new NameReferenceGraphConstruction(compiler);
        graphBuilder.process(externs, root);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[317]++;
        definitionProvider = graphBuilder.getNameReferenceGraph();
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[318]++;

      } else {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[188]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[319]++;
        SimpleDefinitionFinder defFinder = new SimpleDefinitionFinder(compiler);
        defFinder.process(externs, root);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[320]++;
        definitionProvider = defFinder;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[321]++;
      }
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[322]++;

      PureFunctionIdentifier pureFunctionIdentifier =
          new PureFunctionIdentifier(compiler, definitionProvider);
      pureFunctionIdentifier.process(externs, root);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[323]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[324]++;
int CodeCoverConditionCoverageHelper_C88;

      if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((reportPath != null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[189]++;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[325]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
          Files.write(pureFunctionIdentifier.getDebugReport(),
              new File(reportPath),
              Charsets.UTF_8);
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.statements[326]++;
        } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[192]++;
          throw new RuntimeException(e);
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[191]++;
}
  }

      } else {
  CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t.branches[190]++;}
    }
  }
}

class CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t ());
  }
    public static long[] statements = new long[327];
    public static long[] branches = new long[193];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[89];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PureFunctionIdentifier.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,2,1,2,2,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,2,2,1,1,1,1,2,1,1,2,3,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,2,1,1,3,1,2,1,3,1,1,2,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 88; i++) {
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

  public CodeCoverCoverageCounter$558aq39icmyxb7czhbuputizbgpnlai9h3nz2snd5t () {
    super("com.google.javascript.jscomp.PureFunctionIdentifier.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 326; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 192; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 88; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PureFunctionIdentifier.java");
      for (int i = 1; i <= 326; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 192; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 88; i++) {
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

