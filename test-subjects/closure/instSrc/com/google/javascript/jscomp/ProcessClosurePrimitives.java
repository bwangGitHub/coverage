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
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.NodeTraversal.AbstractPostOrderCallback;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.JSDocInfoBuilder;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

/**
 * Replaces goog.provide calls, removes goog.require calls, verifies that
 * goog.require has a corresponding goog.provide and some closure specific
 * simplifications.
 *
 */
class ProcessClosurePrimitives extends AbstractPostOrderCallback
    implements HotSwapCompilerPass {
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.ping();
  }


  static final DiagnosticType NULL_ARGUMENT_ERROR = DiagnosticType.error(
      "JSC_NULL_ARGUMENT_ERROR",
      "method \"{0}\" called without an argument");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[1]++;
  }

  static final DiagnosticType EXPECTED_OBJECTLIT_ERROR = DiagnosticType.error(
      "JSC_EXPECTED_OBJECTLIT_ERROR",
      "method \"{0}\" expected an object literal argument");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[2]++;
  }

  static final DiagnosticType EXPECTED_STRING_ERROR = DiagnosticType.error(
      "JSC_EXPECTED_STRING_ERROR",
      "method \"{0}\" expected an object string argument");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[3]++;
  }

  static final DiagnosticType INVALID_ARGUMENT_ERROR = DiagnosticType.error(
      "JSC_INVALID_ARGUMENT_ERROR",
      "method \"{0}\" called with invalid argument");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[4]++;
  }

  static final DiagnosticType INVALID_STYLE_ERROR = DiagnosticType.error(
      "JSC_INVALID_CSS_NAME_MAP_STYLE_ERROR",
      "Invalid CSS name map style {0}");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[5]++;
  }

  static final DiagnosticType TOO_MANY_ARGUMENTS_ERROR = DiagnosticType.error(
      "JSC_TOO_MANY_ARGUMENTS_ERROR",
      "method \"{0}\" called with more than one argument");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[6]++;
  }

  static final DiagnosticType DUPLICATE_NAMESPACE_ERROR = DiagnosticType.error(
      "JSC_DUPLICATE_NAMESPACE_ERROR",
      "namespace \"{0}\" cannot be provided twice");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[7]++;
  }

  static final DiagnosticType FUNCTION_NAMESPACE_ERROR = DiagnosticType.error(
      "JSC_FUNCTION_NAMESPACE_ERROR",
      "\"{0}\" cannot be both provided and declared as a function");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[8]++;
  }

  static final DiagnosticType MISSING_PROVIDE_ERROR = DiagnosticType.error(
      "JSC_MISSING_PROVIDE_ERROR",
      "required \"{0}\" namespace never provided");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[9]++;
  }

  static final DiagnosticType LATE_PROVIDE_ERROR = DiagnosticType.error(
      "JSC_LATE_PROVIDE_ERROR",
      "required \"{0}\" namespace not provided yet");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[10]++;
  }

  static final DiagnosticType INVALID_PROVIDE_ERROR = DiagnosticType.error(
      "JSC_INVALID_PROVIDE_ERROR",
      "\"{0}\" is not a valid JS property name");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[11]++;
  }

  static final DiagnosticType XMODULE_REQUIRE_ERROR = DiagnosticType.warning(
      "JSC_XMODULE_REQUIRE_ERROR",
      "namespace \"{0}\" provided in module {1} " +
      "but required in module {2}");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[12]++;
  }

  static final DiagnosticType NON_STRING_PASSED_TO_SET_CSS_NAME_MAPPING_ERROR =
      DiagnosticType.error(
          "JSC_NON_STRING_PASSED_TO_SET_CSS_NAME_MAPPING_ERROR",
      "goog.setCssNameMapping only takes an object literal with string values");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[13]++;
  }

  static final DiagnosticType INVALID_CSS_RENAMING_MAP = DiagnosticType.warning(
      "INVALID_CSS_RENAMING_MAP",
      "Invalid entries in css renaming map: {0}");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[14]++;
  }

  static final DiagnosticType BASE_CLASS_ERROR = DiagnosticType.error(
      "JSC_BASE_CLASS_ERROR",
      "incorrect use of goog.base: {0}");
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[15]++;
  }

  /** The root Closure namespace */
  static final String GOOG = "goog";
  static {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[16]++;
  }

  private final AbstractCompiler compiler;
  private final JSModuleGraph moduleGraph;

  // The goog.provides must be processed in a deterministic order.
  private final Map<String, ProvidedName> providedNames =
      Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[17]++;
  }

  private final List<UnrecognizedRequire> unrecognizedRequires =
      Lists.newArrayList();
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[18]++;
  }
  private final Set<String> exportedVariables = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[19]++;
  }
  private final CheckLevel requiresLevel;
  private final PreprocessorSymbolTable preprocessorSymbolTable;

  ProcessClosurePrimitives(AbstractCompiler compiler,
      @Nullable PreprocessorSymbolTable preprocessorSymbolTable,
      CheckLevel requiresLevel) {
    this.compiler = compiler;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[20]++;
    this.preprocessorSymbolTable = preprocessorSymbolTable;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[21]++;
    this.moduleGraph = compiler.getModuleGraph();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[22]++;
    this.requiresLevel = requiresLevel;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[23]++;

    // goog is special-cased because it is provided in Closure's base library.
    providedNames.put(GOOG,
        new ProvidedName(GOOG, null, null, false /* implicit */));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[24]++;
  }

  Set<String> getExportedVariableNames() {
    return exportedVariables;
  }

  @Override
  public void process(Node externs, Node root) {
    new NodeTraversal(compiler, this).traverse(root);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[25]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[26]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[1]++;



    for (ProvidedName pn : providedNames.values()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[1]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[2]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[3]++;
}
      pn.replace();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[27]++;
    }
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[28]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((requiresLevel.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[1]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[29]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[4]++;


      for (UnrecognizedRequire r : unrecognizedRequires) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[4]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[5]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[6]++;
}
        DiagnosticType error;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[30]++;
        ProvidedName expectedName = providedNames.get(r.namespace);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[31]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((expectedName != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((expectedName.firstNode != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[3]++;
          // The namespace ended up getting provided after it was required.
          error = LATE_PROVIDE_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[32]++;

        } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[4]++;
          error = MISSING_PROVIDE_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[33]++;
        }

        compiler.report(JSError.make(
            r.inputName, r.requireNode, requiresLevel, error, r.namespace));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[34]++;
      }

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[2]++;}
  }

  @Override
  public void hotSwapScript(Node scriptRoot, Node originalRoot) {
    // TODO(bashir): Implement a real hot-swap version instead and make it fully
    // consistent with the full version.
    this.compiler.process(this);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[35]++;
  }

  @Override
  public void visit(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[36]++;
    switch (n.getType()) {
      case Token.CALL:
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[5]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[37]++;
        boolean isExpr = parent.isExprResult();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[38]++;
        Node left = n.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[39]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((left.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[6]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[40]++;
          Node name = left.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[41]++;
int CodeCoverConditionCoverageHelper_C4;
          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((name.isName()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((GOOG.equals(name.getString())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[8]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[42]++;
            // For the sake of simplicity, we report code changes
            // when we see a provides/requires, and don't worry about
            // reporting the change when we actually do the replacement.
            String methodName = name.getNext().getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[43]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (("base".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[10]++;
              processBaseClassCall(t, n);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[44]++;

            } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[11]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[45]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isExpr) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[12]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[46]++;
              // All other methods must be called in an EXPR.
              break;

            } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[13]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[47]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (("require".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[14]++;
              processRequireCall(t, n, parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[48]++;

            } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[15]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[49]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 (("provide".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[16]++;
              processProvideCall(t, n, parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[50]++;

            } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[17]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[51]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 (("exportSymbol".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[18]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[52]++;
              Node arg = left.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[53]++;
int CodeCoverConditionCoverageHelper_C10;
              if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((arg.isString()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[20]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[54]++;
                int dot = arg.getString().indexOf('.');
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[55]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((dot == -1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[22]++;
                  exportedVariables.add(arg.getString());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[56]++;

                } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[23]++;
                  exportedVariables.add(arg.getString().substring(0, dot));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[57]++;
                }

              } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[21]++;}

            } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[19]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[58]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 (("addDependency".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[24]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[59]++;
              CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[60]++;
              List<String> typeDecls =
                  convention.identifyTypeDeclarationCall(n);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[61]++;
int CodeCoverConditionCoverageHelper_C13;
              if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((typeDecls != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[26]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[62]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[7]++;


                for (String typeDecl : typeDecls) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[7]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[8]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[9]++;
}
                  compiler.getTypeRegistry().forwardDeclareType(typeDecl);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[63]++;
                }

              } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[27]++;}

              // We can't modify parent, so just create a node that will
              // get compiled out.
              parent.replaceChild(n, IR.number(0));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[64]++;
              compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[65]++;

            } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[25]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[66]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 (("setCssNameMapping".equals(methodName)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[28]++;
              processSetCssNameMapping(t, n, parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[67]++;

            } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[29]++;}
}
}
}
}
}
}

          } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[9]++;}

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[7]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[68]++;
        break;

      case Token.ASSIGN:
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[30]++;
      case Token.NAME:
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[31]++;
        // If this is an assignment to a provided name, remove the provided
        // object.
        handleCandidateProvideDefinition(t, n, parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[69]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[70]++;
        break;

      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[32]++;
        handleTypedefDefinition(t, n);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[71]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[72]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[33]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[73]++;
int CodeCoverConditionCoverageHelper_C15;
        // If this is a declaration of a provided named function, this is an
        // error. Hoisted functions will explode if they're provided.
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionExpression(n)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[34]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[74]++;
          String name = n.getFirstChild().getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[75]++;
          ProvidedName pn = providedNames.get(name);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[76]++;
int CodeCoverConditionCoverageHelper_C16;
          if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((pn != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[36]++;
            compiler.report(t.makeError(n, FUNCTION_NAMESPACE_ERROR, name));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[77]++;

          } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[37]++;}

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[35]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[78]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[38]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[79]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (128)) == 0 || true) &&
 ((n.getFirstChild().isName()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((parent.isCall()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 (("goog.base".equals(n.getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 4) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 4) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[39]++;
          reportBadBaseClassUse(t, n, "May only be called directly.");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[80]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[40]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[81]++;
        break; default : CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[41]++;
    }
  }

  /**
   * Handles a goog.require call.
   */
  private void processRequireCall(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[82]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[83]++;
    Node arg = left.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[84]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((verifyArgument(t, left, arg)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[42]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[85]++;
      String ns = arg.getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[86]++;
      ProvidedName provided = providedNames.get(ns);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[87]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((provided == null) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((provided.isExplicitlyProvided()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[44]++;
        unrecognizedRequires.add(
            new UnrecognizedRequire(n, ns, t.getSourceName()));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[88]++;

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[45]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[89]++;
        JSModule providedModule = provided.explicitModule;

        // This must be non-null, because there was an explicit provide.
        Preconditions.checkNotNull(providedModule);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[90]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[91]++;

        JSModule module = t.getModule();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[92]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (32)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((module != providedModule) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((moduleGraph.dependsOn(module, providedModule)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 3) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[46]++;
          compiler.report(
              t.makeError(n, XMODULE_REQUIRE_ERROR, ns,
                  providedModule.getName(),
                  module.getName()));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[93]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[47]++;}
      }

      maybeAddToSymbolTable(left);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[94]++;
      maybeAddStringNodeToSymbolTable(arg);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[95]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[96]++;
int CodeCoverConditionCoverageHelper_C21;

      // Requires should be removed before runtime.  The one
      // exception is if the type has not been provided yet and
      // errors for broken requires are turned off, in which case,
      // we will be doing a later pass that may error, so we can
      // leave this here this time and let it error next time if it
      // is still not provided.
      if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((provided != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((requiresLevel.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[48]++;
        parent.detachFromParent();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[97]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[98]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[49]++;}

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[43]++;}
  }

  /**
   * Handles a goog.provide call.
   */
  private void processProvideCall(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[99]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[100]++;
    Node arg = left.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[101]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((verifyProvide(t, left, arg)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[50]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[102]++;
      String ns = arg.getString();

      maybeAddToSymbolTable(left);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[103]++;
      maybeAddStringNodeToSymbolTable(arg);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[104]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[105]++;
int CodeCoverConditionCoverageHelper_C23;

      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((providedNames.containsKey(ns)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[52]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[106]++;
        ProvidedName previouslyProvided = providedNames.get(ns);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[107]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((previouslyProvided.isExplicitlyProvided()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[54]++;
          previouslyProvided.addProvide(parent, t.getModule(), true);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[108]++;

        } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[55]++;
          compiler.report(
              t.makeError(n, DUPLICATE_NAMESPACE_ERROR, ns));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[109]++;
        }

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[53]++;
        registerAnyProvidedPrefixes(ns, parent, t.getModule());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[110]++;
        providedNames.put(
            ns, new ProvidedName(ns, parent, t.getModule(), true));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[111]++;
      }

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[51]++;}
  }

  /**
   * Handles a typedef definition for a goog.provided name.
   * @param n EXPR_RESULT node.
   */
  private void handleTypedefDefinition(
      NodeTraversal t, Node n) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[112]++;
    JSDocInfo info = n.getFirstChild().getJSDocInfo();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[113]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((info.hasTypedefType()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[56]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[114]++;
      String name = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[115]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[58]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[116]++;
        ProvidedName pn = providedNames.get(name);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[117]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((pn != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[60]++;
          pn.addDefinition(n, t.getModule());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[118]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[61]++;}

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[59]++;}

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[57]++;}
  }

  /**
   * Handles a candidate definition for a goog.provided name.
   */
  private void handleCandidateProvideDefinition(
      NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[119]++;
int CodeCoverConditionCoverageHelper_C28;
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((t.inGlobalScope()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[62]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[120]++;
      String name = null;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[121]++;
int CodeCoverConditionCoverageHelper_C29;
      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((n.isName()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((parent.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[64]++;
        name = n.getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[122]++;

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[65]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[123]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((n.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((parent.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[66]++;
        name = n.getFirstChild().getQualifiedName();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[124]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[67]++;}
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[125]++;
int CodeCoverConditionCoverageHelper_C31;

      if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((name != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[68]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[126]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((parent.getBooleanProp(Node.IS_NAMESPACE)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[70]++;
          processProvideFromPreviousPass(t, name, parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[127]++;

        } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[71]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[128]++;
          ProvidedName pn = providedNames.get(name);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[129]++;
int CodeCoverConditionCoverageHelper_C33;
          if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((pn != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[72]++;
            pn.addDefinition(parent, t.getModule());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[130]++;

          } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[73]++;}
        }

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[69]++;}

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[63]++;}
  }

  /**
   * Processes the base class call.
   */
  private void processBaseClassCall(NodeTraversal t, Node n) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[131]++;
    // Two things must hold for every goog.base call:
    // 1) We must be calling it on "this".
    // 2) We must be calling it on a prototype method of the same name as
    //    the one we're in, OR we must be calling it from a constructor.
    // If both of those things are true, then we can rewrite:
    // <pre>
    // function Foo() {
    //   goog.base(this);
    // }
    // goog.inherits(Foo, BaseFoo);
    // Foo.prototype.bar = function() {
    //   goog.base(this, 'bar', 1);
    // };
    // </pre>
    // as the easy-to-optimize:
    // <pre>
    // function Foo() {
    //   BaseFoo.call(this);
    // }
    // goog.inherits(Foo, BaseFoo);
    // Foo.prototype.bar = function() {
    //   Foo.superClass_.bar.call(this, 1);
    // };
    //
    // Most of the logic here is just to make sure the AST's
    // structure is what we expect it to be.

    Node callee = n.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[132]++;
    Node thisArg = callee.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[133]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((thisArg == null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((thisArg.isThis()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[74]++;
      reportBadBaseClassUse(t, n, "First argument must be 'this'.");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[134]++;
      return;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[75]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[135]++;

    Node enclosingFnNameNode = getEnclosingDeclNameNode(t);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[136]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((enclosingFnNameNode == null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[76]++;
      reportBadBaseClassUse(t, n, "Could not find enclosing method.");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[137]++;
      return;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[77]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[138]++;

    String enclosingQname = enclosingFnNameNode.getQualifiedName();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[139]++;
int CodeCoverConditionCoverageHelper_C36;
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((enclosingQname.indexOf(".prototype.") == -1) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[78]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[140]++;
      // Handle constructors.
      Node enclosingParent = enclosingFnNameNode.getParent();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[141]++;
      Node maybeInheritsExpr = (enclosingParent.isAssign() ?
          enclosingParent.getParent() : enclosingParent).getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[142]++;
      Node baseClassNode = null;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[143]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((maybeInheritsExpr != null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((maybeInheritsExpr.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((maybeInheritsExpr.getFirstChild().isCall()) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[80]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[144]++;
        Node callNode = maybeInheritsExpr.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[145]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 (("goog.inherits".equals(
                callNode.getFirstChild().getQualifiedName())) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((callNode.getLastChild().isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[82]++;
          baseClassNode = callNode.getLastChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[146]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[83]++;}

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[81]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[147]++;
int CodeCoverConditionCoverageHelper_C39;

      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((baseClassNode == null) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[84]++;
        reportBadBaseClassUse(
            t, n, "Could not find goog.inherits for base class");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[148]++;
        return;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[85]++;}

      // We're good to go.
      n.replaceChild(
          callee,
          NodeUtil.newQualifiedNameNode(
            compiler.getCodingConvention(),
            String.format("%s.call", baseClassNode.getQualifiedName()),
            callee, "goog.base"));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[149]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[150]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[79]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[151]++;
      // Handle methods.
      Node methodNameNode = thisArg.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[152]++;
int CodeCoverConditionCoverageHelper_C40;
      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((methodNameNode == null) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((methodNameNode.isString()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[86]++;
        reportBadBaseClassUse(t, n, "Second argument must name a method.");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[153]++;
        return;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[87]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[154]++;

      String methodName = methodNameNode.getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[155]++;
      String ending = ".prototype." + methodName;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[156]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((enclosingQname == null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((enclosingQname.endsWith(ending)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[88]++;
        reportBadBaseClassUse(
            t, n, "Enclosing method does not match " + methodName);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[157]++;
        return;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[89]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[158]++;

      // We're good to go.
      Node className =
          enclosingFnNameNode.getFirstChild().getFirstChild();
      n.replaceChild(
          callee,
          NodeUtil.newQualifiedNameNode(
            compiler.getCodingConvention(),
            String.format("%s.superClass_.%s.call",
                className.getQualifiedName(), methodName),
            callee, "goog.base"));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[159]++;
      n.removeChild(methodNameNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[160]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[161]++;
    }
  }

  /**
   * Returns the qualified name node of the function whose scope we're in,
   * or null if it cannot be found.
   */
  private Node getEnclosingDeclNameNode(NodeTraversal t) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[162]++;
    Node scopeRoot = t.getScopeRoot();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[163]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((NodeUtil.isFunctionDeclaration(scopeRoot)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[90]++;
      // function x() {...}
      return scopeRoot.getFirstChild();

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[91]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[164]++;
      Node parent = scopeRoot.getParent();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[165]++;
int CodeCoverConditionCoverageHelper_C43;
      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((parent != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[92]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[166]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (32)) == 0 || true) &&
 ((parent.isAssign()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((parent.getLastChild() == scopeRoot) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((parent.getFirstChild().isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 3) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 3) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[94]++;
          // x.y.z = function() {...};
          return parent.getFirstChild();

        } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[95]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[167]++;
int CodeCoverConditionCoverageHelper_C45; if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((parent.isName()) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[96]++;
          // var x = function() {...};
          return parent;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[97]++;}
}

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[93]++;}
    }

    return null;
  }

  /** Reports an incorrect use of super-method calling. */
  private void reportBadBaseClassUse(
      NodeTraversal t, Node n, String extraMessage) {
    compiler.report(t.makeError(n, BASE_CLASS_ERROR, extraMessage));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[168]++;
  }

  /**
   * Processes the output of processed-provide from a previous pass.  This will
   * update our data structures in the same manner as if the provide had been
   * processed in this pass.
   */
  private void processProvideFromPreviousPass(
      NodeTraversal t, String name, Node parent) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[169]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((providedNames.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[98]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[170]++;
      // Record this provide created on a previous pass, and create a dummy
      // EXPR node as a placeholder to simulate an explicit provide.
      Node expr = new Node(Token.EXPR_RESULT);
      expr.copyInformationFromForTree(parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[171]++;
      parent.getParent().addChildBefore(expr, parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[172]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[173]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[174]++;

      JSModule module = t.getModule();
      registerAnyProvidedPrefixes(name, expr, module);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[175]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[176]++;

      ProvidedName provided = new ProvidedName(name, expr, module, true);
      providedNames.put(name, provided);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[177]++;
      provided.addDefinition(parent, module);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[178]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[99]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[179]++;
int CodeCoverConditionCoverageHelper_C47;
      // Remove this provide if it came from a previous pass since we have an
      // replacement already.
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((isNamespacePlaceholder(parent)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[100]++;
        parent.getParent().removeChild(parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[180]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[181]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[101]++;}
    }
  }

  /**
   * Processes a call to goog.setCssNameMapping(). Either the argument to
   * goog.setCssNameMapping() is valid, in which case it will be used to create
   * a CssRenamingMap for the compiler of this CompilerPass, or it is invalid
   * and a JSCompiler error will be reported.
   * @see #visit(NodeTraversal, Node, Node)
   */
  private void processSetCssNameMapping(NodeTraversal t, Node n, Node parent) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[182]++;
    Node left = n.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[183]++;
    Node arg = left.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[184]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((verifySetCssNameMapping(t, left, arg)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[102]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[185]++;
      // Translate OBJECTLIT into SubstitutionMap. All keys and
      // values must be strings, or an error will be thrown.
      final Map<String, String> cssNames = Maps.newHashMap();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[186]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[10]++;


int CodeCoverConditionCoverageHelper_C49;

      for (Node key = arg.getFirstChild();(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false);
          key = key.getNext()) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[10]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[11]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[12]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[187]++;
        Node value = key.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[188]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (32)) == 0 || true) &&
 ((key.isStringKey()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((value.isString()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 3) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[104]++;
          compiler.report(
              t.makeError(n,
                  NON_STRING_PASSED_TO_SET_CSS_NAME_MAPPING_ERROR));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[189]++;
          return;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[105]++;}
        cssNames.put(key.getString(), value.getString());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[190]++;
      }
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[191]++;

      String styleStr = "BY_PART";
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[192]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((arg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[106]++;
        styleStr = arg.getNext().getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[193]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[107]++;}

      final CssRenamingMap.Style style;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[194]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        style = CssRenamingMap.Style.valueOf(styleStr);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[195]++;
      } catch (IllegalArgumentException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[109]++;
        compiler.report(
            t.makeError(n, INVALID_STYLE_ERROR, styleStr));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[196]++;
        return;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[108]++;
}
  }
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[197]++;
int CodeCoverConditionCoverageHelper_C52;

      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((style == CssRenamingMap.Style.BY_PART) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[110]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[198]++;
        // Make sure that no keys contain -'s
        List<String> errors = Lists.newArrayList();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[199]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[13]++;


        for (String key : cssNames.keySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[13]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[14]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[15]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[200]++;
int CodeCoverConditionCoverageHelper_C53;
          if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((key.contains("-")) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[112]++;
            errors.add(key);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[201]++;

          } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[113]++;}
        }
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[202]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((errors.size() != 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[114]++;
          compiler.report(
            t.makeError(n, INVALID_CSS_RENAMING_MAP, errors.toString()));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[203]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[115]++;}

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[111]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[204]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((style == CssRenamingMap.Style.BY_WHOLE) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[116]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[205]++;
        // Verifying things is a lot trickier here. We just do a quick
        // n^2 check over the map which makes sure that if "a-b" in
        // the map, then map(a-b) = map(a)-map(b).
        // To speed things up, only consider cases where len(b) <= 10
        List<String> errors = Lists.newArrayList();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[206]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[16]++;


        for (Map.Entry<String, String> b : cssNames.entrySet()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[16]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[17]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[18]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[207]++;
int CodeCoverConditionCoverageHelper_C56;
          if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((b.getKey().length() > 10) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[118]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[208]++; continue;
} else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[119]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[209]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[19]++;


          for (Map.Entry<String, String> a : cssNames.entrySet()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[19]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[20]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[21]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[210]++;
            String combined = cssNames.get(a.getKey() + "-" + b.getKey());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[211]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((combined != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((combined.equals(a.getValue() + "-" + b.getValue())) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[120]++;
              errors.add("map(" + a.getKey() + "-" + b.getKey() +") != map(" +
                         a.getKey() + ")-map(" + b.getKey() +")");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[212]++;

            } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[121]++;}
          }
        }
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[213]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((errors.size() != 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[122]++;
          compiler.report(
            t.makeError(n, INVALID_CSS_RENAMING_MAP, errors.toString()));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[214]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[123]++;}

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[117]++;}
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[215]++;

      CssRenamingMap cssRenamingMap = new CssRenamingMap() {
        @Override
        public String get(String value) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[216]++;
int CodeCoverConditionCoverageHelper_C59;
          if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((cssNames.containsKey(value)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[124]++;
            return cssNames.get(value);

          } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[125]++;
            return value;
          }
        }

        @Override
        public CssRenamingMap.Style getStyle() {
          return style;
        }
      };
      compiler.setCssRenamingMap(cssRenamingMap);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[217]++;
      parent.getParent().removeChild(parent);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[218]++;
      compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[219]++;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[103]++;}
  }

  /**
   * Verifies that a provide method call has exactly one argument,
   * and that it's a string literal and that the contents of the string are
   * valid JS tokens. Reports a compile error if it doesn't.
   *
   * @return Whether the argument checked out okay
   */
  private boolean verifyProvide(NodeTraversal t, Node methodName, Node arg) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[220]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((verifyArgument(t, methodName, arg)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[126]++;
      return false;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[127]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[221]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[22]++;



    for (String part : arg.getString().split("\\.")) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[22]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[23]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[24]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[222]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((NodeUtil.isValidPropertyName(part)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[128]++;
        compiler.report(t.makeError(arg, INVALID_PROVIDE_ERROR, part));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[223]++;
        return false;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[129]++;}
    }
    return true;
  }

  /**
   * Verifies that a method call has exactly one argument, and that it's a
   * string literal. Reports a compile error if it doesn't.
   *
   * @return Whether the argument checked out okay
   */
  private boolean verifyArgument(NodeTraversal t, Node methodName, Node arg) {
    return verifyArgument(t, methodName, arg, Token.STRING);
  }

  /**
   * Verifies that a method call has exactly one argument, and that it is of the
   * desired type. Reports a compile error if it doesn't.
   *
   * @return Whether the argument checked out okay
   */
  private boolean verifyArgument(NodeTraversal t, Node methodName, Node arg,
      int desiredType) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[224]++;
    DiagnosticType diagnostic = null;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[225]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((arg == null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[130]++;
      diagnostic = NULL_ARGUMENT_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[226]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[131]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[227]++;
int CodeCoverConditionCoverageHelper_C63; if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((arg.getType() != desiredType) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[132]++;
      diagnostic = INVALID_ARGUMENT_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[228]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[133]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[229]++;
int CodeCoverConditionCoverageHelper_C64; if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((arg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[134]++;
      diagnostic = TOO_MANY_ARGUMENTS_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[230]++;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[135]++;}
}
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[231]++;
int CodeCoverConditionCoverageHelper_C65;
    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((diagnostic != null) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[136]++;
      compiler.report(
          t.makeError(methodName,
              diagnostic, methodName.getQualifiedName()));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[232]++;
      return false;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[137]++;}
    return true;
  }

  /**
   * Verifies that setCssNameMapping is called with the correct methods.
   *
   * @return Whether the arguments checked out okay
   */
  private boolean verifySetCssNameMapping(NodeTraversal t, Node methodName,
      Node firstArg) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[233]++;
    DiagnosticType diagnostic = null;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[234]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((firstArg == null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[138]++;
      diagnostic = NULL_ARGUMENT_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[235]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[139]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[236]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((firstArg.isObjectLit()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[140]++;
      diagnostic = EXPECTED_OBJECTLIT_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[237]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[141]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[238]++;
int CodeCoverConditionCoverageHelper_C68; if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((firstArg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[142]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[239]++;
      Node secondArg = firstArg.getNext();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[240]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((secondArg.isString()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[144]++;
        diagnostic = EXPECTED_STRING_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[241]++;

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[145]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[242]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((secondArg.getNext() != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[146]++;
        diagnostic = TOO_MANY_ARGUMENTS_ERROR;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[243]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[147]++;}
}

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[143]++;}
}
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[244]++;
int CodeCoverConditionCoverageHelper_C71;
    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((diagnostic != null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[148]++;
      compiler.report(
          t.makeError(methodName,
              diagnostic, methodName.getQualifiedName()));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[245]++;
      return false;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[149]++;}
    return true;
  }

  /**
   * Registers ProvidedNames for prefix namespaces if they haven't
   * already been defined. The prefix namespaces must be registered in
   * order from shortest to longest.
   *
   * @param ns The namespace whose prefixes may need to be provided.
   * @param node The EXPR of the provide call.
   * @param module The current module.
   */
  private void registerAnyProvidedPrefixes(
      String ns, Node node, JSModule module) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[246]++;
    int pos = ns.indexOf('.');
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[247]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[25]++;


int CodeCoverConditionCoverageHelper_C72;
    while ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((pos != -1) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[25]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[26]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[27]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[248]++;
      String prefixNs = ns.substring(0, pos);
      pos = ns.indexOf('.', pos + 1);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[249]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[250]++;
int CodeCoverConditionCoverageHelper_C73;
      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((providedNames.containsKey(prefixNs)) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[150]++;
        providedNames.get(prefixNs).addProvide(
            node, module, false /* implicit */);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[251]++;

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[151]++;
        providedNames.put(
            prefixNs,
            new ProvidedName(prefixNs, node, module, false /* implicit */));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[252]++;
      }
    }
  }

  // -------------------------------------------------------------------------

  /**
   * Information required to replace a goog.provide call later in the traversal.
   */
  private class ProvidedName {
    private final String namespace;

    // The node and module where the call was explicitly or implicitly
    // goog.provided.
    private final Node firstNode;
    private final JSModule firstModule;

    // The node where the call was explicitly goog.provided. May be null
    // if the namespace is always provided implicitly.
    private Node explicitNode = null;
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[253]++;
  }
    private JSModule explicitModule = null;
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[254]++;
  }

    // The candidate definition.
    private Node candidateDefinition = null;
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[255]++;
  }

    // The minimum module where the provide must appear.
    private JSModule minimumModule = null;
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[256]++;
  }

    // The replacement declaration.
    private Node replacementNode = null;
  {
    CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[257]++;
  }

    ProvidedName(String namespace, Node node, JSModule module,
        boolean explicit) {
      Preconditions.checkArgument(
          node == null /* The base case */ ||
          node.isExprResult());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[258]++;
      this.namespace = namespace;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[259]++;
      this.firstNode = node;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[260]++;
      this.firstModule = module;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[261]++;

      addProvide(node, module, explicit);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[262]++;
    }

    /**
     * Add an implicit or explicit provide.
     */
    void addProvide(Node node, JSModule module, boolean explicit) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[263]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((explicit) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[152]++;
        Preconditions.checkState(explicitNode == null);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[264]++;
        Preconditions.checkArgument(node.isExprResult());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[265]++;
        explicitNode = node;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[266]++;
        explicitModule = module;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[267]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[153]++;}
      updateMinimumModule(module);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[268]++;
    }

    boolean isExplicitlyProvided() {
      return explicitNode != null;
    }

    /**
     * Record function declaration, variable declaration or assignment that
     * refers to the same name as the provide statement.  Give preference to
     * declarations; if no declaration exists, record a reference to an
     * assignment so it repurposed later.
     */
    void addDefinition(Node node, JSModule module) {
      Preconditions.checkArgument(node.isExprResult() || // assign
                                  node.isFunction() ||
                                  node.isVar());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[269]++;
      Preconditions.checkArgument(explicitNode != node);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[270]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[271]++;
int CodeCoverConditionCoverageHelper_C75;
      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C75 |= (8)) == 0 || true) &&
 ((candidateDefinition == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (4)) == 0 || true)))
) || !
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((node.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[154]++;
        candidateDefinition = node;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[272]++;
        updateMinimumModule(module);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[273]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[155]++;}
    }

    private void updateMinimumModule(JSModule newModule) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[274]++;
int CodeCoverConditionCoverageHelper_C76;
      if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((minimumModule == null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[156]++;
        minimumModule = newModule;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[275]++;

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[157]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[276]++;
int CodeCoverConditionCoverageHelper_C77; if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((moduleGraph != null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[158]++;
        minimumModule = moduleGraph.getDeepestCommonDependencyInclusive(
            minimumModule, newModule);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[277]++;

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[159]++;
        // If there is no module graph, then there must be exactly one
        // module in the program.
        Preconditions.checkState(newModule == minimumModule,
                                 "Missing module graph");
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[278]++;
      }
}
    }

    /**
     * Replace the provide statement.
     *
     * If we're providing a name with no definition, then create one.
     * If we're providing a name with a duplicate definition, then make sure
     * that definition becomes a declaration.
     */
    void replace() {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[279]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((firstNode == null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[160]++;
        // Don't touch the base case ('goog').
        replacementNode = candidateDefinition;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[280]++;
        return;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[161]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[281]++;
int CodeCoverConditionCoverageHelper_C79;

      // Handle the case where there is a duplicate definition for an explicitly
      // provided symbol.
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((candidateDefinition != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((explicitNode != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[162]++;
        explicitNode.detachFromParent();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[282]++;
        compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[283]++;

        // Does this need a VAR keyword?
        replacementNode = candidateDefinition;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[284]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[285]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (8)) == 0 || true) &&
 ((candidateDefinition.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((candidateDefinition.getFirstChild().isQualifiedName()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 2) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[164]++;
          candidateDefinition.putBooleanProp(Node.IS_NAMESPACE, true);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[286]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[287]++;
          Node assignNode = candidateDefinition.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[288]++;
          Node nameNode = assignNode.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[289]++;
int CodeCoverConditionCoverageHelper_C81;
          if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((nameNode.isName()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[166]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[290]++;
            // Need to convert this assign to a var declaration.
            Node valueNode = nameNode.getNext();
            assignNode.removeChild(nameNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[291]++;
            assignNode.removeChild(valueNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[292]++;
            nameNode.addChildToFront(valueNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[293]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[294]++;
            Node varNode = IR.var(nameNode);
            varNode.copyInformationFrom(candidateDefinition);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[295]++;
            candidateDefinition.getParent().replaceChild(
                candidateDefinition, varNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[296]++;
            nameNode.setJSDocInfo(assignNode.getJSDocInfo());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[297]++;
            compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[298]++;
            replacementNode = varNode;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[299]++;

          } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[167]++;}

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[165]++;}

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[163]++;
        // Handle the case where there's not a duplicate definition.
        replacementNode = createDeclarationNode();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[300]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[301]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((firstModule == minimumModule) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[168]++;
          firstNode.getParent().addChildBefore(replacementNode, firstNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[302]++;

        } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[169]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[303]++;
          // In this case, the name was implicitly provided by two independent
          // modules. We need to move this code up to a common module.
          int indexOfDot = namespace.lastIndexOf('.');
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[304]++;
int CodeCoverConditionCoverageHelper_C83;
          if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((indexOfDot == -1) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[170]++;
            // Any old place is fine.
            compiler.getNodeForCodeInsertion(minimumModule)
                .addChildToBack(replacementNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[305]++;

          } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[171]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[306]++;
            // Add it after the parent namespace.
            ProvidedName parentName =
                providedNames.get(namespace.substring(0, indexOfDot));
            Preconditions.checkNotNull(parentName);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[307]++;
            Preconditions.checkNotNull(parentName.replacementNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[308]++;
            parentName.replacementNode.getParent().addChildAfter(
                replacementNode, parentName.replacementNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[309]++;
          }
        }
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[310]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((explicitNode != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[172]++;
          explicitNode.detachFromParent();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[311]++;

        } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[173]++;}
        compiler.reportCodeChange();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[312]++;
      }
    }

    /**
     * Create the declaration node for this name, without inserting it
     * into the AST.
     */
    private Node createDeclarationNode() {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[313]++;
int CodeCoverConditionCoverageHelper_C85;
      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((namespace.indexOf('.') == -1) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[174]++;
        return makeVarDeclNode();

      } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[175]++;
        return makeAssignmentExprNode();
      }
    }

    /**
     * Creates a simple namespace variable declaration
     * (e.g. <code>var foo = {};</code>).
     */
    private Node makeVarDeclNode() {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[314]++;
      Node name = IR.name(namespace);
      name.addChildToFront(createNamespaceLiteral());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[315]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[316]++;

      Node decl = IR.var(name);
      decl.putBooleanProp(Node.IS_NAMESPACE, true);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[317]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[318]++;
int CodeCoverConditionCoverageHelper_C86;

      // TODO(nicksantos): ew ew ew. Create a mutator package.
      if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((compiler.getCodingConvention().isConstant(namespace)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[176]++;
        name.putBooleanProp(Node.IS_CONSTANT_NAME, true);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[319]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[177]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[320]++;
int CodeCoverConditionCoverageHelper_C87;
      if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((candidateDefinition == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[178]++;
        name.setJSDocInfo(createConstantJsDoc());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[321]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[179]++;}

      Preconditions.checkState(isNamespacePlaceholder(decl));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[322]++;
      setSourceInfo(decl);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[323]++;
      return decl;
    }

    /**
     * There are some special cases where clients of the compiler
     * do not run TypedScopeCreator after running this pass.
     * So always give the namespace literal a type.
     */
    private Node createNamespaceLiteral() {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[324]++;
      Node objlit = IR.objectlit();
      objlit.setJSType(
          compiler.getTypeRegistry().createAnonymousObjectType(null));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[325]++;
      return objlit;
    }

    /**
     * Creates a dotted namespace assignment expression
     * (e.g. <code>foo.bar = {};</code>).
     */
    private Node makeAssignmentExprNode() {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[326]++;
      Node decl = IR.exprResult(
          IR.assign(
              NodeUtil.newQualifiedNameNode(
                  compiler.getCodingConvention(), namespace,
                  firstNode /* real source info will be filled in below */,
                  namespace),
              createNamespaceLiteral()));
      decl.putBooleanProp(Node.IS_NAMESPACE, true);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[327]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[328]++;
int CodeCoverConditionCoverageHelper_C88;
      if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((candidateDefinition == null) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[180]++;
        decl.getFirstChild().setJSDocInfo(createConstantJsDoc());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[329]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[181]++;}
      Preconditions.checkState(isNamespacePlaceholder(decl));
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[330]++;
      setSourceInfo(decl);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[331]++;
      return decl;
    }

    private JSDocInfo createConstantJsDoc() {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[332]++;
      JSDocInfoBuilder builder = new JSDocInfoBuilder(false);
      builder.recordConstancy();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[333]++;
      return builder.build(null);
    }

    /**
     * Copy source info to the new node.
     */
    private void setSourceInfo(Node newNode) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[334]++;
      Node provideStringNode = getProvideStringNode();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[335]++;
      int offset = getSourceInfoOffset(provideStringNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[336]++;
      Node sourceInfoNode = provideStringNode == null
          ? firstNode : provideStringNode;
      newNode.copyInformationFromForTree(sourceInfoNode);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[337]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[338]++;
int CodeCoverConditionCoverageHelper_C89;
      if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((offset != 0) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[182]++;
        newNode.setSourceEncodedPositionForTree(
            sourceInfoNode.getSourcePosition() + offset);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[339]++;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[183]++;}
    }

    /**
     * Get the offset into the provide node where the symbol appears.
     */
    private int getSourceInfoOffset(Node provideStringNode) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[340]++;
int CodeCoverConditionCoverageHelper_C90;
      if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((provideStringNode == null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[184]++;
        return 0;

      } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[185]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[341]++;

      int indexOfLastDot = namespace.lastIndexOf('.');

      // +1 for the opening quote
      // +1 for the dot
      // if there's no dot, then the -1 index cancels it out
      // so elegant!
      return 2 + indexOfLastDot;
    }

    private Node getProvideStringNode() {
      return (firstNode.getFirstChild() != null &&
              NodeUtil.isExprCall(firstNode)) ?
          firstNode.getFirstChild().getLastChild() :
          null;
    }
  }

  /**
   * @return Whether the node is namespace placeholder.
   */
  private static boolean isNamespacePlaceholder(Node n) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[342]++;
int CodeCoverConditionCoverageHelper_C91;
    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((n.getBooleanProp(Node.IS_NAMESPACE)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[186]++;
      return false;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[187]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[343]++;

    Node value = null;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[344]++;
int CodeCoverConditionCoverageHelper_C92;
    if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((n.isExprResult()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[188]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[345]++;
      Node assign = n.getFirstChild();
      value = assign.getLastChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[346]++;

    } else {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[189]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[347]++;
int CodeCoverConditionCoverageHelper_C93; if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((n.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[190]++;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[348]++;
      Node name = n.getFirstChild();
      value = name.getFirstChild();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[349]++;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[191]++;}
}

    return value != null
      && value.isObjectLit()
      && !value.hasChildren();
  }

  /**
   * The string in {@code n} is a reference name. Create a synthetic
   * node for it with all the proper source info, and add it to the symbol
   * table.
   */
  private void maybeAddStringNodeToSymbolTable(Node n) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[350]++;
int CodeCoverConditionCoverageHelper_C94;
    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((preprocessorSymbolTable == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[192]++;
      return;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[193]++;}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[351]++;

    String name = n.getString();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[352]++;
    Node syntheticRef = NodeUtil.newQualifiedNameNode(
        compiler.getCodingConvention(), name,
        n /* real source offsets will be filled in below */,
        name);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[353]++;

    // Offsets to add to source. Named for documentation purposes.
    final int FOR_QUOTE = 1;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[354]++;
    final int FOR_DOT = 1;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[355]++;

    Node current = null;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[356]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[28]++;


int CodeCoverConditionCoverageHelper_C95;
    for (current = syntheticRef;(((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((current.isGetProp()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false);
         current = current.getFirstChild()) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[28]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[29]--;
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.loops[30]++;
}
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[357]++;
      int fullLen = current.getQualifiedName().length();
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[358]++;
      int namespaceLen = current.getFirstChild().getQualifiedName().length();

      current.setSourceEncodedPosition(n.getSourcePosition() + FOR_QUOTE);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[359]++;
      current.setLength(fullLen);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[360]++;

      current.getLastChild().setSourceEncodedPosition(
          n.getSourcePosition() + namespaceLen + FOR_QUOTE + FOR_DOT);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[361]++;
      current.getLastChild().setLength(
          current.getLastChild().getString().length());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[362]++;
    }

    current.setSourceEncodedPosition(n.getSourcePosition() + FOR_QUOTE);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[363]++;
    current.setLength(current.getString().length());
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[364]++;

    maybeAddToSymbolTable(syntheticRef);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[365]++;
  }

  /**
   * Add the given qualified name node to the symbol table.
   */
  private void maybeAddToSymbolTable(Node n) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[366]++;
int CodeCoverConditionCoverageHelper_C96;
    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((preprocessorSymbolTable != null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[194]++;
      preprocessorSymbolTable.addReference(n);
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[367]++;

    } else {
  CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.branches[195]++;}
  }

  // -------------------------------------------------------------------------

  /**
   * Information required to create a {@code MISSING_PROVIDE_ERROR} warning.
   */
  private class UnrecognizedRequire {
    final Node requireNode;
    final String namespace;
    final String inputName;

    UnrecognizedRequire(Node requireNode, String namespace, String inputName) {
      this.requireNode = requireNode;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[368]++;
      this.namespace = namespace;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[369]++;
      this.inputName = inputName;
CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl.statements[370]++;
    }
  }
}

class CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl ());
  }
    public static long[] statements = new long[371];
    public static long[] branches = new long[196];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[97];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.ProcessClosurePrimitives.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,2,1,1,1,1,1,1,1,1,1,1,2,1,3,1,2,3,2,1,1,1,3,1,1,1,2,2,1,1,1,2,1,1,3,2,1,2,2,1,1,3,1,1,1,1,1,3,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 96; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$785civwqia6b3erb9cp94xd3bu0x61c9bn2bqnct958bl () {
    super("com.google.javascript.jscomp.ProcessClosurePrimitives.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 370; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 195; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 96; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.ProcessClosurePrimitives.java");
      for (int i = 1; i <= 370; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 195; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 96; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

