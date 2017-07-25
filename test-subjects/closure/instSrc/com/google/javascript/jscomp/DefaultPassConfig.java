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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.javascript.jscomp.AbstractCompiler.LifeCycleStage;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;
import com.google.javascript.jscomp.ExtractPrototypeMemberDeclarations.Pattern;
import com.google.javascript.jscomp.NodeTraversal.Callback;
import com.google.javascript.jscomp.parsing.ParserRunner;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.Node;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Pass factories and meta-data for native JSCompiler passes.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
// TODO(nicksantos): This needs state for a variety of reasons. Some of it
// is to satisfy the existing API. Some of it is because passes really do
// need to share state in non-trivial ways. This should be audited and
// cleaned up.
public class DefaultPassConfig extends PassConfig {
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.ping();
  }


  /* For the --mark-as-compiled pass */
  private static final String COMPILED_CONSTANT_NAME = "COMPILED";
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[1]++;
  }

  /* Constant name for Closure's locale */
  private static final String CLOSURE_LOCALE_CONSTANT_NAME = "goog.LOCALE";
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[2]++;
  }

  // Compiler errors when invalid combinations of passes are run.
  static final DiagnosticType TIGHTEN_TYPES_WITHOUT_TYPE_CHECK =
      DiagnosticType.error("JSC_TIGHTEN_TYPES_WITHOUT_TYPE_CHECK",
          "TightenTypes requires type checking. Please use --check_types.");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[3]++;
  }

  static final DiagnosticType CANNOT_USE_PROTOTYPE_AND_VAR =
      DiagnosticType.error("JSC_CANNOT_USE_PROTOTYPE_AND_VAR",
          "Rename prototypes and inline variables cannot be used together");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[4]++;
  }

  // Miscellaneous errors.
  static final DiagnosticType REPORT_PATH_IO_ERROR =
      DiagnosticType.error("JSC_REPORT_PATH_IO_ERROR",
          "Error writing compiler report to {0}");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[5]++;
  }

  private static final DiagnosticType NAME_REF_GRAPH_FILE_ERROR =
      DiagnosticType.error("JSC_NAME_REF_GRAPH_FILE_ERROR",
          "Error \"{1}\" writing name reference graph to \"{0}\".");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[6]++;
  }

  private static final DiagnosticType NAME_REF_REPORT_FILE_ERROR =
      DiagnosticType.error("JSC_NAME_REF_REPORT_FILE_ERROR",
          "Error \"{1}\" writing name reference report to \"{0}\".");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[7]++;
  }

  private static final java.util.regex.Pattern GLOBAL_SYMBOL_NAMESPACE_PATTERN =
    java.util.regex.Pattern.compile("^[a-zA-Z0-9$_]+$");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[8]++;
  }

  /**
   * A global namespace to share across checking passes.
   */
  private GlobalNamespace namespaceForChecks = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[9]++;
  }

  /**
   * A symbol table for registering references that get removed during
   * preprocessing.
   */
  private PreprocessorSymbolTable preprocessorSymbolTable = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[10]++;
  }

  /**
   * A type-tightener to share across optimization passes.
   */
  private TightenTypes tightenTypes = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[11]++;
  }

  /** Names exported by goog.exportSymbol. */
  private Set<String> exportedNames = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[12]++;
  }

  /**
   * Ids for cross-module method stubbing, so that each method has
   * a unique id.
   */
  private CrossModuleMethodMotion.IdGenerator crossModuleIdGenerator =
      new CrossModuleMethodMotion.IdGenerator();
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[13]++;
  }

  /**
   * Keys are arguments passed to getCssName() found during compilation; values
   * are the number of times the key appeared as an argument to getCssName().
   */
  private Map<String, Integer> cssNames = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[14]++;
  }

  /** The variable renaming map */
  private VariableMap variableMap = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[15]++;
  }

  /** The property renaming map */
  private VariableMap propertyMap = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[16]++;
  }

  /** The naming map for anonymous functions */
  private VariableMap anonymousFunctionNameMap = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[17]++;
  }

  /** Fully qualified function names and globally unique ids */
  private FunctionNames functionNames = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[18]++;
  }

  /** String replacement map */
  private VariableMap stringMap = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[19]++;
  }

  /** Id generator map */
  private String idGeneratorMap = null;
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[20]++;
  }

  public DefaultPassConfig(CompilerOptions options) {
    super(options);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[21]++;
  }

  @Override
  protected State getIntermediateState() {
    return new State(
        cssNames == null ? null : Maps.newHashMap(cssNames),
        exportedNames == null ? null :
            Collections.unmodifiableSet(exportedNames),
        crossModuleIdGenerator, variableMap, propertyMap,
        anonymousFunctionNameMap, stringMap, functionNames, idGeneratorMap);
  }

  @Override
  protected void setIntermediateState(State state) {
    this.cssNames = state.cssNames == null ? null :
        Maps.newHashMap(state.cssNames);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[22]++;
    this.exportedNames = state.exportedNames == null ? null :
        Sets.newHashSet(state.exportedNames);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[23]++;
    this.crossModuleIdGenerator = state.crossModuleIdGenerator;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[24]++;
    this.variableMap = state.variableMap;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[25]++;
    this.propertyMap = state.propertyMap;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[26]++;
    this.anonymousFunctionNameMap = state.anonymousFunctionNameMap;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[27]++;
    this.stringMap = state.stringMap;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[28]++;
    this.functionNames = state.functionNames;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[29]++;
    this.idGeneratorMap = state.idGeneratorMap;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[30]++;
  }

  GlobalNamespace getGlobalNamespace() {
    return namespaceForChecks;
  }

  PreprocessorSymbolTable getPreprocessorSymbolTable() {
    return preprocessorSymbolTable;
  }

  void maybeInitializePreprocessorSymbolTable(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[31]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((options.ideMode) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[1]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[32]++;
      Node root = compiler.getRoot();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((preprocessorSymbolTable == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((preprocessorSymbolTable.getRootNode() != root) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[3]++;
        preprocessorSymbolTable = new PreprocessorSymbolTable(root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[34]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[2]++;}
  }

  @Override
  protected List<PassFactory> getChecks() {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[35]++;
    List<PassFactory> checks = Lists.newArrayList();

    checks.add(createEmptyPass("beforeStandardChecks"));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[36]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[37]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[5]++;
      checks.add(closureGoogScopeAliases);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[38]++;
      checks.add(closureRewriteGoogClass);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[39]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[6]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[40]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((options.nameAnonymousFunctionsOnly) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[7]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[41]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((options.anonymousFunctionNaming ==
          AnonymousFunctionNamingPolicy.MAPPED) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[9]++;
        checks.add(nameMappedAnonymousFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[42]++;

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[10]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[43]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((options.anonymousFunctionNaming ==
          AnonymousFunctionNamingPolicy.UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[11]++;
        checks.add(nameUnmappedAnonymousFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[44]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[12]++;}
}
      return checks;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[8]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[45]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((options.jqueryPass) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[13]++;
      checks.add(jqueryAliases);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[46]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[14]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[47]++;
int CodeCoverConditionCoverageHelper_C8;

    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((options.angularPass) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[15]++;
      checks.add(angularPass);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[48]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[16]++;}

    checks.add(checkSideEffects);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[49]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[50]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((options.checkSuspiciousCode) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.GLOBAL_THIS)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.DEBUGGER_STATEMENT_PRESENT)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[17]++;
      checks.add(suspiciousCode);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[51]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[18]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[52]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((options.checkControlStructures) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.ES5_STRICT)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false))  {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[19]++;
      checks.add(checkControlStructures);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[53]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[20]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[54]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((options.checkRequires.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[21]++;
      checks.add(checkRequires);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[55]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[22]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[56]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((options.checkProvides.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[23]++;
      checks.add(checkProvides);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[57]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[24]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[58]++;
int CodeCoverConditionCoverageHelper_C13;

    // The following passes are more like "preprocessor" passes.
    // It's important that they run before most checking passes.
    // Perhaps this method should be renamed?
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((options.generateExports) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[25]++;
      checks.add(generateExports);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[59]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[26]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[60]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((options.exportTestFunctions) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[27]++;
      checks.add(exportTestFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[61]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[28]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[29]++;
      checks.add(closurePrimitives);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[63]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[30]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((options.checkMissingGetCssNameLevel.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[31]++;
      checks.add(closureCheckGetCssName);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[65]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[32]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;

    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((options.syntheticBlockStartMarker != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[33]++;
      // This pass must run before the first fold constants pass.
      checks.add(createSyntheticBlocks);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[67]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[34]++;}

    checks.add(checkVars);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[68]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[69]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((options.computeFunctionSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[35]++;
      checks.add(checkRegExp);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[70]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[36]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;

    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((options.aggressiveVarCheck.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[37]++;
      checks.add(checkVariableReferences);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[72]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[38]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[73]++;
int CodeCoverConditionCoverageHelper_C20;

    // This pass should run before types are assigned.
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((options.processObjectPropertyString) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[39]++;
      checks.add(objectPropertyStringPreprocess);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[74]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[40]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[75]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((options.inferTypes) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[41]++;
      checks.add(resolveTypes);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[76]++;
      checks.add(inferTypes);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[77]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[78]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[43]++;
        checks.add(checkTypes);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[79]++;

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[44]++;
        checks.add(inferJsDocInfo);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[80]++;
      }
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[81]++;
int CodeCoverConditionCoverageHelper_C23;

      // We assume that only IDE-mode clients will try to query the
      // typed scope creator after the compile job.
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((options.ideMode) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((options.saveDataStructures) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[45]++;
        checks.add(clearTypedScopePass);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[82]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[46]++;}

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[42]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[83]++;
int CodeCoverConditionCoverageHelper_C24;

    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (32)) == 0 || true) &&
 ((options.checkUnreachableCode.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((options.checkMissingReturn.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[47]++;
      checks.add(checkControlFlow);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[84]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[48]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[85]++;
int CodeCoverConditionCoverageHelper_C25;

    // CheckAccessControls only works if check types is on.
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.ACCESS_CONTROLS)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.CONSTANT_PROPERTY)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[49]++;
      checks.add(checkAccessControls);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[86]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[50]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[87]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((options.checkGlobalNamesLevel.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[51]++;
      checks.add(checkGlobalNames);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[88]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[52]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[89]++;
int CodeCoverConditionCoverageHelper_C27;

    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.ES5_STRICT)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((options.checkCaja) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[53]++;
      checks.add(checkStrictMode);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[90]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[54]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[91]++;
int CodeCoverConditionCoverageHelper_C28;

    // Replace 'goog.getCssName' before processing defines but after the
    // other checks have been done.
    if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[55]++;
      checks.add(closureReplaceGetCssName);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[92]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[56]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[93]++;
int CodeCoverConditionCoverageHelper_C29;

    // i18n
    // If you want to customize the compiler to use a different i18n pass,
    // you can create a PassConfig that calls replacePassFactory
    // to replace this.
    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((options.replaceMessagesWithChromeI18n) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[57]++;
      checks.add(replaceMessagesForChrome);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[94]++;

    } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[58]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[95]++;
int CodeCoverConditionCoverageHelper_C30; if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((options.messageBundle != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[59]++;
      checks.add(replaceMessages);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[96]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[60]++;}
}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[97]++;
int CodeCoverConditionCoverageHelper_C31;

    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((options.getTweakProcessing().isOn()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[61]++;
      checks.add(processTweaks);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[98]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[62]++;}

    // Defines in code always need to be processed.
    checks.add(processDefines);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[99]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[100]++;
int CodeCoverConditionCoverageHelper_C32;

    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((options.instrumentationTemplate != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((options.recordFunctionInformation) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[63]++;
      checks.add(computeFunctionNames);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[101]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[64]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[102]++;
int CodeCoverConditionCoverageHelper_C33;

    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((options.nameReferenceGraphPath != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((options.nameReferenceGraphPath.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[65]++;
      checks.add(printNameReferenceGraph);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[103]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[66]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[104]++;
int CodeCoverConditionCoverageHelper_C34;

    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 ((options.nameReferenceReportPath != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((options.nameReferenceReportPath.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[67]++;
      checks.add(printNameReferenceReport);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[105]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[68]++;}

    checks.add(createEmptyPass("afterStandardChecks"));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[106]++;

    assertAllOneTimePasses(checks);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[107]++;
    return checks;
  }

  @Override
  protected List<PassFactory> getOptimizations() {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[108]++;
    List<PassFactory> passes = Lists.newArrayList();
    passes.add(garbageCollectChecks);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[109]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[110]++;
int CodeCoverConditionCoverageHelper_C35;

    // TODO(nicksantos): The order of these passes makes no sense, and needs
    // to be re-arranged.

    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((options.runtimeTypeCheck) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[69]++;
      passes.add(runtimeTypeCheck);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[111]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[70]++;}

    passes.add(createEmptyPass("beforeStandardOptimizations"));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[112]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[113]++;
int CodeCoverConditionCoverageHelper_C36;

    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((options.replaceIdGenerators) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[71]++;
      passes.add(replaceIdGenerators);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[114]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[72]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[115]++;
int CodeCoverConditionCoverageHelper_C37;

    // Optimizes references to the arguments variable.
    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((options.optimizeArgumentsArray) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[73]++;
      passes.add(optimizeArgumentsArray);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[116]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[74]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[117]++;
int CodeCoverConditionCoverageHelper_C38;

    // Abstract method removal works best on minimally modified code, and also
    // only needs to run once.
    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (32)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((options.removeAbstractMethods) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((options.removeClosureAsserts) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[75]++;
      passes.add(closureCodeRemoval);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[118]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[76]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[119]++;
int CodeCoverConditionCoverageHelper_C39;

    // Collapsing properties can undo constant inlining, so we do this before
    // the main optimization loop.
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((options.collapseProperties) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[77]++;
      passes.add(collapseProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[120]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[78]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[121]++;
int CodeCoverConditionCoverageHelper_C40;

    // ReplaceStrings runs after CollapseProperties in order to simplify
    // pulling in values of constants defined in enums structures.
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((options.replaceStringsFunctionDescriptions.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[79]++;
      passes.add(replaceStrings);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[122]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[80]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[123]++;
int CodeCoverConditionCoverageHelper_C41;

    // Tighten types based on actual usage.
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((options.tightenTypes) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[81]++;
      passes.add(tightenTypesBuilder);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[124]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[82]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[125]++;
int CodeCoverConditionCoverageHelper_C42;

    // Property disambiguation should only run once and needs to be done
    // soon after type checking, both so that it can make use of type
    // information and so that other passes can take advantage of the renamed
    // properties.
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((options.disambiguateProperties) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[83]++;
      passes.add(disambiguateProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[126]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[84]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[127]++;
int CodeCoverConditionCoverageHelper_C43;

    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((options.computeFunctionSideEffects) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[85]++;
      passes.add(markPureFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[128]++;

    } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[86]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[129]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((options.markNoSideEffectCalls) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[87]++;
      // TODO(user) The properties that this pass adds to CALL and NEW
      // AST nodes increase the AST's in-memory size.  Given that we are
      // already running close to our memory limits, we could run into
      // trouble if we end up using the @nosideeffects annotation a lot
      // or compute @nosideeffects annotations by looking at function
      // bodies.  It should be easy to propagate @nosideeffects
      // annotations as part of passes that depend on this property and
      // store the result outside the AST (which would allow garbage
      // collection once the pass is done).
      passes.add(markNoSideEffectCalls);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[130]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[88]++;}
}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[131]++;
int CodeCoverConditionCoverageHelper_C45;

    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((options.chainCalls) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[89]++;
      passes.add(chainCalls);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[132]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[90]++;}

    // Constant checking must be done after property collapsing because
    // property collapsing can introduce new constants (e.g. enum values).
    // TODO(johnlenz): make checkConsts namespace aware so it can be run
    // as during the checks phase.
    passes.add(checkConsts);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[133]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[134]++;
int CodeCoverConditionCoverageHelper_C46;

    // The Caja library adds properties to Object.prototype, which breaks
    // most for-in loops.  This adds a check to each loop that skips
    // any property matching /___$/.
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((options.ignoreCajaProperties) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[91]++;
      passes.add(ignoreCajaProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[135]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[92]++;}

    assertAllOneTimePasses(passes);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[136]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[137]++;
int CodeCoverConditionCoverageHelper_C47;

    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((options.smartNameRemoval) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((options.reportPath != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[93]++;
      passes.addAll(getCodeRemovingPasses());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[138]++;
      passes.add(smartNamePass);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[139]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[94]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[140]++;
int CodeCoverConditionCoverageHelper_C48;

    // This needs to come after the inline constants pass, which is run within
    // the code removing passes.
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[95]++;
      passes.add(closureOptimizePrimitives);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[141]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[96]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[142]++;
int CodeCoverConditionCoverageHelper_C49;

    // TODO(user): This forces a first crack at crossModuleCodeMotion
    // before devirtualization. Once certain functions are devirtualized,
    // it confuses crossModuleCodeMotion ability to recognized that
    // it is recursive.

    // TODO(user): This is meant for a temporary quick win.
    // In the future, we might want to improve our analysis in
    // CrossModuleCodeMotion so we don't need to do this.
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((options.crossModuleCodeMotion) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[97]++;
      passes.add(crossModuleCodeMotion);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[143]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[98]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[144]++;
int CodeCoverConditionCoverageHelper_C50;

    // Method devirtualization benefits from property disambiguation so
    // it should run after that pass but before passes that do
    // optimizations based on global names (like cross module code motion
    // and inline functions).  Smart Name Removal does better if run before
    // this pass.
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((options.devirtualizePrototypeMethods) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[99]++;
      passes.add(devirtualizePrototypeMethods);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[145]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[100]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[146]++;
int CodeCoverConditionCoverageHelper_C51;

    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((options.customPasses != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[101]++;
      passes.add(getCustomPasses(
          CustomPassExecutionTime.BEFORE_OPTIMIZATION_LOOP));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[147]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[102]++;}

    passes.add(createEmptyPass("beforeMainOptimizations"));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[148]++;

    passes.addAll(getMainOptimizationLoop());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[149]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[150]++;
int CodeCoverConditionCoverageHelper_C52;

    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((options.specializeInitialModule) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[103]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[151]++;
int CodeCoverConditionCoverageHelper_C53;
      // When specializing the initial module, we want our fixups to be
      // as lean as possible, so we run the entire optimization loop to a
      // fixed point before specializing, then specialize, and then run the
      // main optimization loop again.

      if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((options.crossModuleCodeMotion) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[105]++;
        passes.add(crossModuleCodeMotion);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[152]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[106]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[153]++;
int CodeCoverConditionCoverageHelper_C54;

      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((options.crossModuleMethodMotion) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[107]++;
        passes.add(crossModuleMethodMotion);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[154]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[108]++;}

      passes.add(specializeInitialModule);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[155]++;
      passes.addAll(getMainOptimizationLoop());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[156]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[104]++;}

    passes.add(createEmptyPass("beforeModuleMotion"));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[157]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[158]++;
int CodeCoverConditionCoverageHelper_C55;

    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((options.crossModuleCodeMotion) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[109]++;
      passes.add(crossModuleCodeMotion);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[159]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[110]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[160]++;
int CodeCoverConditionCoverageHelper_C56;

    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((options.crossModuleMethodMotion) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[111]++;
      passes.add(crossModuleMethodMotion);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[161]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[112]++;}

    passes.add(createEmptyPass("afterModuleMotion"));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[162]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[163]++;
int CodeCoverConditionCoverageHelper_C57;

    // Some optimizations belong outside the loop because running them more
    // than once would either have no benefit or be incorrect.
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((options.customPasses != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[113]++;
      passes.add(getCustomPasses(
          CustomPassExecutionTime.AFTER_OPTIMIZATION_LOOP));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[164]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[114]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[165]++;
int CodeCoverConditionCoverageHelper_C58;

    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((options.flowSensitiveInlineVariables) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[115]++;
      passes.add(flowSensitiveInlineVariables);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[166]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[167]++;
int CodeCoverConditionCoverageHelper_C59;

      // After inlining some of the variable uses, some variables are unused.
      // Re-run remove unused vars to clean it up.
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (8)) == 0 || true) &&
 ((options.removeUnusedVars) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((options.removeUnusedLocalVars) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[117]++;
        passes.add(removeUnusedVars);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[168]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[118]++;}

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[116]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[169]++;
int CodeCoverConditionCoverageHelper_C60;

    // Running this pass again is required to have goog.events compile down to
    // nothing when compiled on its own.
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((options.smartNameRemoval) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[119]++;
      passes.add(smartNamePass2);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[170]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[120]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[171]++;
int CodeCoverConditionCoverageHelper_C61;

    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((options.collapseAnonymousFunctions) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[121]++;
      passes.add(collapseAnonymousFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[172]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[122]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[173]++;
int CodeCoverConditionCoverageHelper_C62;

    // Move functions before extracting prototype member declarations.
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((options.moveFunctionDeclarations) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((// renamePrefixNamescape relies on moveFunctionDeclarations
        // to preserve semantics.
        options.renamePrefixNamespace != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[123]++;
      passes.add(moveFunctionDeclarations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[174]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[124]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[175]++;
int CodeCoverConditionCoverageHelper_C63;

    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((options.anonymousFunctionNaming ==
        AnonymousFunctionNamingPolicy.MAPPED) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[125]++;
      passes.add(nameMappedAnonymousFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[176]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[126]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[177]++;
int CodeCoverConditionCoverageHelper_C64;

    // The mapped name anonymous function pass makes use of information that
    // the extract prototype member declarations pass removes so the former
    // happens before the latter.
    //
    // Extracting prototype properties screws up the heuristic renaming
    // policies, so never run it when those policies are requested.
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (32)) == 0 || true) &&
 ((options.extractPrototypeMemberDeclarations) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((options.propertyRenaming != PropertyRenamingPolicy.HEURISTIC) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((options.propertyRenaming !=
            PropertyRenamingPolicy.AGGRESSIVE_HEURISTIC) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 3) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 3) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[127]++;
      passes.add(extractPrototypeMemberDeclarations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[178]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[128]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[179]++;
int CodeCoverConditionCoverageHelper_C65;

    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((options.ambiguateProperties) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((options.propertyRenaming == PropertyRenamingPolicy.ALL_UNQUOTED) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[129]++;
      passes.add(ambiguateProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[180]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[130]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[181]++;
int CodeCoverConditionCoverageHelper_C66;

    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((options.propertyRenaming != PropertyRenamingPolicy.OFF) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[131]++;
      passes.add(renameProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[182]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[132]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[183]++;
int CodeCoverConditionCoverageHelper_C67;

    // Reserve global names added to the "windows" object.
    if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((options.reserveRawExports) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[133]++;
      passes.add(gatherRawExports);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[184]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[134]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[185]++;
int CodeCoverConditionCoverageHelper_C68;

    // This comes after property renaming because quoted property names must
    // not be renamed.
    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((options.convertToDottedProperties) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[135]++;
      passes.add(convertToDottedProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[186]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[136]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[187]++;
int CodeCoverConditionCoverageHelper_C69;

    // Property renaming must happen before this pass runs since this
    // pass may convert dotted properties into quoted properties.  It
    // is beneficial to run before alias strings, alias keywords and
    // variable renaming.
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((options.rewriteFunctionExpressions) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[137]++;
      passes.add(rewriteFunctionExpressions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[188]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[138]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[189]++;
int CodeCoverConditionCoverageHelper_C70;

    // This comes after converting quoted property accesses to dotted property
    // accesses in order to avoid aliasing property names.
    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((options.aliasableStrings.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((options.aliasAllStrings) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[139]++;
      passes.add(aliasStrings);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[190]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[140]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[191]++;
int CodeCoverConditionCoverageHelper_C71;

    if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((options.aliasExternals) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[141]++;
      passes.add(aliasExternals);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[192]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[142]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[193]++;
int CodeCoverConditionCoverageHelper_C72;

    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((options.aliasKeywords) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[143]++;
      passes.add(aliasKeywords);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[194]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[144]++;}

    // Passes after this point can no longer depend on normalized AST
    // assumptions.
    passes.add(markUnnormalized);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[195]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[196]++;
int CodeCoverConditionCoverageHelper_C73;

    if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((options.coalesceVariableNames) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[145]++;
      passes.add(coalesceVariableNames);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[197]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[198]++;
int CodeCoverConditionCoverageHelper_C74;

      // coalesceVariables creates identity assignments and more redundant code
      // that can be removed, rerun the peephole optimizations to clean them
      // up.
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((options.foldConstants) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[147]++;
        passes.add(peepholeOptimizations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[199]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[148]++;}

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[146]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[200]++;
int CodeCoverConditionCoverageHelper_C75;

    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((options.collapseVariableDeclarations) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[149]++;
      passes.add(exploitAssign);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[201]++;
      passes.add(collapseVariableDeclarations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[202]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[150]++;}

    // This pass works best after collapseVariableDeclarations.
    passes.add(denormalize);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[203]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[204]++;
int CodeCoverConditionCoverageHelper_C76;

    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((options.instrumentationTemplate != null) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[151]++;
      passes.add(instrumentFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[205]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[152]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[206]++;
int CodeCoverConditionCoverageHelper_C77;

    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((options.variableRenaming != VariableRenamingPolicy.ALL) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[153]++;
      // If we're leaving some (or all) variables with their old names,
      // then we need to undo any of the markers we added for distinguishing
      // local variables ("$$1").
      passes.add(invertContextualRenaming);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[207]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[154]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[208]++;
int CodeCoverConditionCoverageHelper_C78;

    if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((options.variableRenaming != VariableRenamingPolicy.OFF) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[155]++;
      passes.add(renameVars);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[209]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[156]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[210]++;
int CodeCoverConditionCoverageHelper_C79;

    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((options.groupVariableDeclarations) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[157]++;
      passes.add(groupVariableDeclarations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[211]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[158]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[212]++;
int CodeCoverConditionCoverageHelper_C80;

    // This pass should run after names stop changing.
    if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((options.processObjectPropertyString) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[159]++;
      passes.add(objectPropertyStringPostprocess);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[213]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[160]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[214]++;
int CodeCoverConditionCoverageHelper_C81;

    if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((options.labelRenaming) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[161]++;
      passes.add(renameLabels);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[215]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[162]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[216]++;
int CodeCoverConditionCoverageHelper_C82;

    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((options.foldConstants) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[163]++;
      passes.add(latePeepholeOptimizations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[217]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[164]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[218]++;
int CodeCoverConditionCoverageHelper_C83;

    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((options.anonymousFunctionNaming ==
        AnonymousFunctionNamingPolicy.UNMAPPED) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[165]++;
      passes.add(nameUnmappedAnonymousFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[219]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[166]++;}

    passes.add(stripSideEffectProtection);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[220]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[221]++;
int CodeCoverConditionCoverageHelper_C84;

    if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((options.renamePrefixNamespace != null) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[167]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[222]++;
int CodeCoverConditionCoverageHelper_C85;
      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((GLOBAL_SYMBOL_NAMESPACE_PATTERN.matcher(
          options.renamePrefixNamespace).matches()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[169]++;
        throw new IllegalArgumentException(
            "Illegal character in renamePrefixNamespace name: "
            + options.renamePrefixNamespace);

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[170]++;}
      passes.add(rescopeGlobalSymbols);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[223]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[168]++;}

    // Safety checks
    passes.add(sanityCheckAst);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[224]++;
    passes.add(sanityCheckVars);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[225]++;

    return passes;
  }

  /** Creates the passes for the main optimization loop. */
  private List<PassFactory> getMainOptimizationLoop() {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[226]++;
    List<PassFactory> passes = Lists.newArrayList();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[227]++;
int CodeCoverConditionCoverageHelper_C86;
    if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((options.inlineGetters) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[171]++;
      passes.add(inlineSimpleMethods);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[228]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[172]++;}

    passes.addAll(getCodeRemovingPasses());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[229]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[230]++;
int CodeCoverConditionCoverageHelper_C87;

    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((options.inlineFunctions) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((options.inlineLocalFunctions) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[173]++;
      passes.add(inlineFunctions);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[231]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[174]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[232]++;
int CodeCoverConditionCoverageHelper_C88;

    if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((options.inlineProperties) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[175]++;
      passes.add(inlineProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[233]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[176]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[234]++;

    boolean runOptimizeCalls = options.optimizeCalls
        || options.optimizeParameters
        || options.optimizeReturns;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[235]++;
int CodeCoverConditionCoverageHelper_C89;

    if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (8)) == 0 || true) &&
 ((options.removeUnusedVars) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((options.removeUnusedLocalVars) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[177]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[236]++;
int CodeCoverConditionCoverageHelper_C90;
      if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((options.deadAssignmentElimination) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[179]++;
        passes.add(deadAssignmentsElimination);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[237]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[180]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[238]++;
int CodeCoverConditionCoverageHelper_C91;
      if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((runOptimizeCalls) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[181]++;
        passes.add(removeUnusedVars);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[239]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[182]++;}

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[178]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[240]++;
int CodeCoverConditionCoverageHelper_C92;
    if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((runOptimizeCalls) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[183]++;
      passes.add(optimizeCallsAndRemoveUnusedVars);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[241]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[184]++;}
    assertAllLoopablePasses(passes);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[242]++;
    return passes;
  }

  /** Creates several passes aimed at removing code. */
  private List<PassFactory> getCodeRemovingPasses() {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[243]++;
    List<PassFactory> passes = Lists.newArrayList();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[244]++;
int CodeCoverConditionCoverageHelper_C93;
    if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (8)) == 0 || true) &&
 ((options.collapseObjectLiterals) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((isInliningForbidden()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[185]++;
      passes.add(collapseObjectLiterals);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[245]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[186]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[246]++;
int CodeCoverConditionCoverageHelper_C94;

    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((options.inlineVariables) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((options.inlineLocalVariables) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[187]++;
      passes.add(inlineVariables);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[247]++;

    } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[188]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[248]++;
int CodeCoverConditionCoverageHelper_C95; if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((options.inlineConstantVars) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[189]++;
      passes.add(inlineConstants);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[249]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[190]++;}
}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[250]++;
int CodeCoverConditionCoverageHelper_C96;

    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((options.foldConstants) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[191]++;
      // These used to be one pass.
      passes.add(minimizeExitPoints);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[251]++;
      passes.add(peepholeOptimizations);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[252]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[192]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[253]++;
int CodeCoverConditionCoverageHelper_C97;

    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((options.removeDeadCode) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[193]++;
      passes.add(removeUnreachableCode);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[254]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[194]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[255]++;
int CodeCoverConditionCoverageHelper_C98;

    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((options.removeUnusedPrototypeProperties) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[195]++;
      passes.add(removeUnusedPrototypeProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[256]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[196]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[257]++;
int CodeCoverConditionCoverageHelper_C99;

    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (8)) == 0 || true) &&
 ((options.removeUnusedClassProperties) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((isInliningForbidden()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[197]++;
      passes.add(removeUnusedClassProperties);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[258]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[198]++;}

    assertAllLoopablePasses(passes);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[259]++;
    return passes;
  }

  /**
   * Checks for code that is probably wrong (such as stray expressions).
   */
  final HotSwapPassFactory checkSideEffects =
      new HotSwapPassFactory("checkSideEffects", true) {
    @Override
    protected HotSwapCompilerPass create(final AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[260]++;
      // The current approach to protecting "hidden" side-effects is to
      // wrap them in a function call that is stripped later, this shouldn't
      // be done in IDE mode where AST changes may be unexpected.
      boolean protectHiddenSideEffects =
          options.protectHiddenSideEffects && !options.ideMode;
      return new CheckSideEffects(compiler,
          options.checkSuspiciousCode ? CheckLevel.WARNING : CheckLevel.OFF,
              protectHiddenSideEffects);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[261]++;
  }

  /**
   * Checks for code that is probably wrong (such as stray expressions).
   */
  final PassFactory stripSideEffectProtection =
      new PassFactory("stripSideEffectProtection", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler
        compiler) {
      return new CheckSideEffects.StripProtection(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[262]++;
  }

  /**
   * Checks for code that is probably wrong (such as stray expressions).
   */
  final HotSwapPassFactory suspiciousCode =
      new HotSwapPassFactory("suspiciousCode", true) {
    @Override
    protected HotSwapCompilerPass create(final AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[263]++;
      List<Callback> sharedCallbacks = Lists.newArrayList();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[264]++;
int CodeCoverConditionCoverageHelper_C100;
      if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((options.checkSuspiciousCode) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[199]++;
        sharedCallbacks.add(new CheckSuspiciousCode());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[265]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[200]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[266]++;
int CodeCoverConditionCoverageHelper_C101;

      if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.GLOBAL_THIS)) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[201]++;
        sharedCallbacks.add(new CheckGlobalThis(compiler));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[267]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[202]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[268]++;
int CodeCoverConditionCoverageHelper_C102;

      if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.DEBUGGER_STATEMENT_PRESENT)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[203]++;
        sharedCallbacks.add(new CheckDebuggerStatement(compiler));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[269]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[204]++;}

      return combineChecks(compiler, sharedCallbacks);
    }

  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[270]++;
  }

  /** Verify that all the passes are one-time passes. */
  private void assertAllOneTimePasses(List<PassFactory> passes) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[271]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[1]++;


    for (PassFactory pass : passes) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[1]--;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[2]--;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[3]++;
}
      Preconditions.checkState(pass.isOneTimePass());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[272]++;
    }
  }

  /** Verify that all the passes are multi-run passes. */
  private void assertAllLoopablePasses(List<PassFactory> passes) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[273]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[4]++;


    for (PassFactory pass : passes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[4]--;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[5]--;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[6]++;
}
      Preconditions.checkState(!pass.isOneTimePass());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[274]++;
    }
  }

  /** Checks for validity of the control structures. */
  final HotSwapPassFactory checkControlStructures =
      new HotSwapPassFactory("checkControlStructures", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new ControlStructureCheck(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[275]++;
  }

  /** Checks that all constructed classes are goog.require()d. */
  final HotSwapPassFactory checkRequires =
      new HotSwapPassFactory("checkRequires", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new CheckRequiresForConstructors(compiler, options.checkRequires);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[276]++;
  }

  /** Makes sure @constructor is paired with goog.provides(). */
  final HotSwapPassFactory checkProvides =
      new HotSwapPassFactory("checkProvides", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new CheckProvides(compiler, options.checkProvides);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[277]++;
  }

  private static final DiagnosticType GENERATE_EXPORTS_ERROR =
      DiagnosticType.error(
          "JSC_GENERATE_EXPORTS_ERROR",
          "Exports can only be generated if export symbol/property " +
          "functions are set.");
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[278]++;
  }

  /** Generates exports for @export annotations. */
  final PassFactory generateExports = new PassFactory("generateExports", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[279]++;
      CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[280]++;
int CodeCoverConditionCoverageHelper_C103;
      if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (8)) == 0 || true) &&
 ((convention.getExportSymbolFunction() != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((convention.getExportPropertyFunction() != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[205]++;
        return new GenerateExports(compiler,
            convention.getExportSymbolFunction(),
            convention.getExportPropertyFunction());

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[206]++;
        return new ErrorPass(compiler, GENERATE_EXPORTS_ERROR);
      }
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[281]++;
  }

  /** Generates exports for functions associated with JsUnit. */
  final PassFactory exportTestFunctions =
      new PassFactory("exportTestFunctions", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[282]++;
      CodingConvention convention = compiler.getCodingConvention();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[283]++;
int CodeCoverConditionCoverageHelper_C104;
      if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((convention.getExportSymbolFunction() != null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[207]++;
        return new ExportTestFunctions(compiler,
            convention.getExportSymbolFunction(),
            convention.getExportPropertyFunction());

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[208]++;
        return new ErrorPass(compiler, GENERATE_EXPORTS_ERROR);
      }
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[284]++;
  }

  /** Raw exports processing pass. */
  final PassFactory gatherRawExports =
      new PassFactory("gatherRawExports", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[285]++;
      final GatherRawExports pass = new GatherRawExports(
          compiler);

      return new CompilerPass() {
        @Override
        public void process(Node externs, Node root) {
          pass.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[286]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[287]++;
int CodeCoverConditionCoverageHelper_C105;
          if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((exportedNames == null) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[209]++;
            exportedNames = Sets.newHashSet();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[288]++;

          } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[210]++;}
          exportedNames.addAll(pass.getExportedVariableNames());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[289]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[290]++;
  }

  /** Closure pre-processing pass. */
  @SuppressWarnings("deprecation")
  final HotSwapPassFactory closurePrimitives =
      new HotSwapPassFactory("closurePrimitives", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      maybeInitializePreprocessorSymbolTable(compiler);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[291]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[292]++;
      final ProcessClosurePrimitives pass = new ProcessClosurePrimitives(
          compiler,
          preprocessorSymbolTable,
          options.brokenClosureRequiresLevel);

      return new HotSwapCompilerPass() {
        @Override
        public void process(Node externs, Node root) {
          pass.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[293]++;
          exportedNames = pass.getExportedVariableNames();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[294]++;
        }
        @Override
        public void hotSwapScript(Node scriptRoot, Node originalRoot) {
          pass.hotSwapScript(scriptRoot, originalRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[295]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[296]++;
  }

  /** Expand jQuery Primitives and Aliases pass. */
  final PassFactory jqueryAliases = new PassFactory("jqueryAliases", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ExpandJqueryAliases(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[297]++;
  }

  /** Process AngularJS-specific annotations. */
  final PassFactory angularPass =
      new PassFactory("angularPass", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new AngularPass(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[298]++;
  }

  /**
   * The default i18n pass.
   * A lot of the options are not configurable, because ReplaceMessages
   * has a lot of legacy logic.
   */
  final PassFactory replaceMessages = new PassFactory("replaceMessages", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new ReplaceMessages(compiler,
          options.messageBundle,
          /* warn about message dupes */
          true,
          /* allow messages with goog.getMsg */
          JsMessage.Style.getFromParams(true, false),
          /* if we can't find a translation, don't worry about it. */
          false);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[299]++;
  }

  final PassFactory replaceMessagesForChrome =
      new PassFactory("replaceMessages", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new ReplaceMessagesForChrome(compiler,
          new GoogleJsMessageIdGenerator(options.tcProjectId),
          /* warn about message dupes */
          true,
          /* allow messages with goog.getMsg */
          JsMessage.Style.getFromParams(true, false));
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[300]++;
  }

  /** Applies aliases and inlines goog.scope. */
  final HotSwapPassFactory closureGoogScopeAliases =
      new HotSwapPassFactory("closureGoogScopeAliases", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      maybeInitializePreprocessorSymbolTable(compiler);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[301]++;
      return new ScopedAliases(
          compiler,
          preprocessorSymbolTable,
          options.getAliasTransformationHandler());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[302]++;
  }

  /** Rewrites goog.class */
  final HotSwapPassFactory closureRewriteGoogClass =
      new HotSwapPassFactory("closureRewriteGoogClass", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new ClosureRewriteClass(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[303]++;
  }

  /** Checks that CSS class names are wrapped in goog.getCssName */
  final PassFactory closureCheckGetCssName =
      new PassFactory("closureCheckGetCssName", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[304]++;
      String blacklist = options.checkMissingGetCssNameBlacklist;
      Preconditions.checkState(blacklist != null && !blacklist.isEmpty(),
          "Not checking use of goog.getCssName because of empty blacklist.");
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[305]++;
      return new CheckMissingGetCssName(
          compiler, options.checkMissingGetCssNameLevel, blacklist);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[306]++;
  }

  /**
   * Processes goog.getCssName.  The cssRenamingMap is used to lookup
   * replacement values for the classnames.  If null, the raw class names are
   * inlined.
   */
  final PassFactory closureReplaceGetCssName =
      new PassFactory("closureReplaceGetCssName", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[307]++;
          Map<String, Integer> newCssNames = null;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[308]++;
int CodeCoverConditionCoverageHelper_C106;
          if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((options.gatherCssNames) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[211]++;
            newCssNames = Maps.newHashMap();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[309]++;

          } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[212]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[310]++;
          ReplaceCssNames pass = new ReplaceCssNames(
              compiler,
              newCssNames,
              options.cssRenamingWhitelist);
          pass.process(externs, jsRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[311]++;
          cssNames = newCssNames;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[312]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[313]++;
  }

  /**
   * Creates synthetic blocks to prevent FoldConstants from moving code
   * past markers in the source.
   */
  final PassFactory createSyntheticBlocks =
      new PassFactory("createSyntheticBlocks", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CreateSyntheticBlocks(compiler,
          options.syntheticBlockStartMarker,
          options.syntheticBlockEndMarker);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[314]++;
  }

  /** Various peephole optimizations. */
  final PassFactory peepholeOptimizations =
      new PassFactory("peepholeOptimizations", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[315]++;
      final boolean late = false;
      return new PeepholeOptimizationsPass(compiler,
            new PeepholeSubstituteAlternateSyntax(late),
            new PeepholeReplaceKnownMethods(late),
            new PeepholeRemoveDeadCode(),
            new PeepholeFoldConstants(late),
            new PeepholeCollectPropertyAssignments());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[316]++;
  }

  /** Same as peepholeOptimizations but aggressively merges code together */
  final PassFactory latePeepholeOptimizations =
      new PassFactory("latePeepholeOptimizations", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[317]++;
      final boolean late = true;
      return new PeepholeOptimizationsPass(compiler,
            new StatementFusion(),
            new PeepholeRemoveDeadCode(),
            new PeepholeSubstituteAlternateSyntax(late),
            new PeepholeReplaceKnownMethods(late),
            new PeepholeFoldConstants(late),
            new ReorderConstantExpression());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[318]++;
  }

  /** Checks that all variables are defined. */
  final HotSwapPassFactory checkVars =
      new HotSwapPassFactory("checkVars", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new VarCheck(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[319]++;
  }

  /** Checks for RegExp references. */
  final PassFactory checkRegExp =
      new PassFactory("checkRegExp", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[320]++;
      final CheckRegExp pass = new CheckRegExp(compiler);

      return new CompilerPass() {
        @Override
        public void process(Node externs, Node root) {
          pass.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[321]++;
          compiler.setHasRegExpGlobalReferences(
              pass.isGlobalRegExpPropertiesUsed());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[322]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[323]++;
  }

  /** Checks that references to variables look reasonable. */
  final HotSwapPassFactory checkVariableReferences =
      new HotSwapPassFactory("checkVariableReferences", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new VariableReferenceCheck(
          compiler, options.aggressiveVarCheck);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[324]++;
  }

  /** Pre-process goog.testing.ObjectPropertyString. */
  final PassFactory objectPropertyStringPreprocess =
      new PassFactory("ObjectPropertyStringPreprocess", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ObjectPropertyStringPreprocess(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[325]++;
  }

  /** Creates a typed scope and adds types to the type registry. */
  final HotSwapPassFactory resolveTypes =
      new HotSwapPassFactory("resolveTypes", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new GlobalTypeResolver(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[326]++;
  }

  /** Clears the typed scope when we're done. */
  final PassFactory clearTypedScopePass =
      new PassFactory("clearTypedScopePass", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ClearTypedScope();
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[327]++;
  }

  /** Runs type inference. */
  final HotSwapPassFactory inferTypes =
      new HotSwapPassFactory("inferTypes", true) {
    @Override
    protected HotSwapCompilerPass create(final AbstractCompiler compiler) {
      return new HotSwapCompilerPass() {
        @Override
        public void process(Node externs, Node root) {
          Preconditions.checkNotNull(topScope);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[328]++;
          Preconditions.checkNotNull(getTypedScopeCreator());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[329]++;

          makeTypeInference(compiler).process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[330]++;
        }
        @Override
        public void hotSwapScript(Node scriptRoot, Node originalRoot) {
          makeTypeInference(compiler).inferAllScopes(scriptRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[331]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[332]++;
  }

  final HotSwapPassFactory inferJsDocInfo =
      new HotSwapPassFactory("inferJsDocInfo", true) {
  @Override
  protected HotSwapCompilerPass create(final AbstractCompiler compiler) {
    return new HotSwapCompilerPass() {
      @Override
      public void process(Node externs, Node root) {
        Preconditions.checkNotNull(topScope);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[333]++;
        Preconditions.checkNotNull(getTypedScopeCreator());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[334]++;

        makeInferJsDocInfo(compiler).process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[335]++;
      }
      @Override
      public void hotSwapScript(Node scriptRoot, Node originalRoot) {
        makeInferJsDocInfo(compiler).hotSwapScript(scriptRoot, originalRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[336]++;
      }
    };
  }
};
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[337]++;
  }

  /** Checks type usage */
  final HotSwapPassFactory checkTypes =
      new HotSwapPassFactory("checkTypes", true) {
    @Override
    protected HotSwapCompilerPass create(final AbstractCompiler compiler) {
      return new HotSwapCompilerPass() {
        @Override
        public void process(Node externs, Node root) {
          Preconditions.checkNotNull(topScope);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[338]++;
          Preconditions.checkNotNull(getTypedScopeCreator());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[339]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[340]++;

          TypeCheck check = makeTypeCheck(compiler);
          check.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[341]++;
          compiler.getErrorManager().setTypedPercent(check.getTypedPercent());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[342]++;
        }
        @Override
        public void hotSwapScript(Node scriptRoot, Node originalRoot) {
          makeTypeCheck(compiler).check(scriptRoot, false);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[343]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[344]++;
  }

  /**
   * Checks possible execution paths of the program for problems: missing return
   * statements and dead code.
   */
  final HotSwapPassFactory checkControlFlow =
      new HotSwapPassFactory("checkControlFlow", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[345]++;
      List<Callback> callbacks = Lists.newArrayList();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[346]++;
int CodeCoverConditionCoverageHelper_C107;
      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((options.checkUnreachableCode.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[213]++;
        callbacks.add(
            new CheckUnreachableCode(compiler, options.checkUnreachableCode));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[347]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[214]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[348]++;
int CodeCoverConditionCoverageHelper_C108;
      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (8)) == 0 || true) &&
 ((options.checkMissingReturn.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[215]++;
        callbacks.add(
            new CheckMissingReturn(compiler, options.checkMissingReturn));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[349]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[216]++;}
      return combineChecks(compiler, callbacks);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[350]++;
  }

  /** Checks access controls. Depends on type-inference. */
  final HotSwapPassFactory checkAccessControls =
      new HotSwapPassFactory("checkAccessControls", true) {
    @Override
    protected HotSwapCompilerPass create(AbstractCompiler compiler) {
      return new CheckAccessControls(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[351]++;
  }

  /** Executes the given callbacks with a {@link CombinedCompilerPass}. */
  private static HotSwapCompilerPass combineChecks(AbstractCompiler compiler,
      List<Callback> callbacks) {
    Preconditions.checkArgument(callbacks.size() > 0);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[352]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[353]++;
    Callback[] array = callbacks.toArray(new Callback[callbacks.size()]);
    return new CombinedCompilerPass(compiler, array);
  }

  /** A compiler pass that resolves types in the global scope. */
  class GlobalTypeResolver implements HotSwapCompilerPass {
    private final AbstractCompiler compiler;

    GlobalTypeResolver(AbstractCompiler compiler) {
      this.compiler = compiler;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[354]++;
    }

    @Override
    public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[355]++;
int CodeCoverConditionCoverageHelper_C109;
      if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((topScope == null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[217]++;
        regenerateGlobalTypedScope(compiler, root.getParent());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[356]++;

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[218]++;
        compiler.getTypeRegistry().resolveTypesInScope(topScope);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[357]++;
      }
    }
    @Override
    public void hotSwapScript(Node scriptRoot, Node originalRoot) {
      patchGlobalTypedScope(compiler, scriptRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[358]++;
    }
  }

  /** A compiler pass that clears the global scope. */
  class ClearTypedScope implements CompilerPass {
    @Override
    public void process(Node externs, Node root) {
      clearTypedScope();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[359]++;
    }
  }

  /** Checks global name usage. */
  final PassFactory checkGlobalNames =
      new PassFactory("checkGlobalNames", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
          // Create a global namespace for analysis by check passes.
          // Note that this class does all heavy computation lazily,
          // so it's OK to create it here.
          namespaceForChecks = new GlobalNamespace(compiler, externs, jsRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[360]++;
          new CheckGlobalNames(compiler, options.checkGlobalNamesLevel)
              .injectNamespace(namespaceForChecks).process(externs, jsRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[361]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[362]++;
  }

  /** Checks that the code is ES5 or Caja compliant. */
  final PassFactory checkStrictMode =
      new PassFactory("checkStrictMode", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new StrictModeCheck(compiler,
          !options.checkSymbols,  // don't check variables twice
          !options.checkCaja);    // disable eval check if not Caja
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[363]++;
  }

  /** Process goog.tweak.getTweak() calls. */
  final PassFactory processTweaks = new PassFactory("processTweaks", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
          new ProcessTweaks(compiler,
              options.getTweakProcessing().shouldStrip(),
              options.getTweakReplacements()).process(externs, jsRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[364]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[365]++;
  }

  /** Override @define-annotated constants. */
  final PassFactory processDefines = new PassFactory("processDefines", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[366]++;
          Map<String, Node> replacements = getAdditionalReplacements(options);
          replacements.putAll(options.getDefineReplacements());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[367]++;

          new ProcessDefines(compiler, replacements)
              .injectNamespace(namespaceForChecks).process(externs, jsRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[368]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[369]++;
  }

  /** Release references to data that is only needed during checks. */
  final PassFactory garbageCollectChecks =
      new HotSwapPassFactory("garbageCollectChecks", true) {
    @Override
    protected HotSwapCompilerPass create(final AbstractCompiler compiler) {
      return new HotSwapCompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
          // Kill the global namespace so that it can be garbage collected
          // after all passes are through with it.
          namespaceForChecks = null;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[370]++;
        }

        @Override
        public void hotSwapScript(Node scriptRoot, Node originalRoot) {
          process(null, null);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[371]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[372]++;
  }

  /** Checks that all constants are not modified */
  final PassFactory checkConsts = new PassFactory("checkConsts", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ConstCheck(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[373]++;
  }

  /** Computes the names of functions for later analysis. */
  final PassFactory computeFunctionNames =
      new PassFactory("computeFunctionNames", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return ((functionNames = new FunctionNames(compiler)));
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[374]++;
  }

  /** Skips Caja-private properties in for-in loops */
  final PassFactory ignoreCajaProperties =
      new PassFactory("ignoreCajaProperties", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new IgnoreCajaProperties(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[375]++;
  }

  /** Inserts run-time type assertions for debugging. */
  final PassFactory runtimeTypeCheck =
      new PassFactory("runtimeTypeCheck", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new RuntimeTypeCheck(compiler,
          options.runtimeTypeCheckLogFunction);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[376]++;
  }

  /** Generates unique ids. */
  final PassFactory replaceIdGenerators =
      new PassFactory("replaceIdGenerators", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[377]++;
          ReplaceIdGenerators pass =
              new ReplaceIdGenerators(
                  compiler, options.idGenerators, options.generatePseudoNames,
                  options.idGeneratorsMapSerialized);
          pass.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[378]++;
          idGeneratorMap = pass.getSerializedIdMappings();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[379]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[380]++;
  }

  /** Replace strings. */
  final PassFactory replaceStrings = new PassFactory("replaceStrings", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[381]++;
          ReplaceStrings pass = new ReplaceStrings(
              compiler,
              options.replaceStringsPlaceholderToken,
              options.replaceStringsFunctionDescriptions,
              options.replaceStringsReservedStrings,
              options.replaceStringsInputMap);
          pass.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[382]++;
          stringMap = pass.getStringMap();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[383]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[384]++;
  }

  /** Optimizes the "arguments" array. */
  final PassFactory optimizeArgumentsArray =
      new PassFactory("optimizeArgumentsArray", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new OptimizeArgumentsArray(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[385]++;
  }

  /** Remove variables set to goog.abstractMethod. */
  final PassFactory closureCodeRemoval =
      new PassFactory("closureCodeRemoval", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new ClosureCodeRemoval(compiler, options.removeAbstractMethods,
          options.removeClosureAsserts);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[386]++;
  }

  /** Special case optimizations for closure functions. */
  final PassFactory closureOptimizePrimitives =
      new PassFactory("closureOptimizePrimitives", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new ClosureOptimizePrimitives(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[387]++;
  }

  /** Puts global symbols into a single object. */
  final PassFactory rescopeGlobalSymbols =
      new PassFactory("rescopeGlobalSymbols", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new RescopeGlobalSymbols(compiler, options.renamePrefixNamespace);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[388]++;
  }

  /** Collapses names in the global scope. */
  final PassFactory collapseProperties =
      new PassFactory("collapseProperties", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CollapseProperties(
          compiler, options.collapsePropertiesOnExternTypes,
          !isInliningForbidden());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[389]++;
  }

  /** Rewrite properties as variables. */
  final PassFactory collapseObjectLiterals =
      new PassFactory("collapseObjectLiterals", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new InlineObjectLiterals(
          compiler, compiler.getUniqueNameIdSupplier());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[390]++;
  }

  /**
   * Try to infer the actual types, which may be narrower
   * than the declared types.
   */
  final PassFactory tightenTypesBuilder =
      new PassFactory("tightenTypes", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[391]++;
int CodeCoverConditionCoverageHelper_C110;
      if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[219]++;
        return new ErrorPass(compiler, TIGHTEN_TYPES_WITHOUT_TYPE_CHECK);

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[220]++;}
      tightenTypes = new TightenTypes(compiler);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[392]++;
      return tightenTypes;
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[393]++;
  }

  /** Devirtualize property names based on type information. */
  final PassFactory disambiguateProperties =
      new PassFactory("disambiguateProperties", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[394]++;
int CodeCoverConditionCoverageHelper_C111;
      if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((tightenTypes == null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[221]++;
        return DisambiguateProperties.forJSTypeSystem(compiler,
            options.propertyInvalidationErrors);

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[222]++;
        return DisambiguateProperties.forConcreteTypeSystem(
            compiler, tightenTypes, options.propertyInvalidationErrors);
      }
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[395]++;
  }

  /**
   * Chain calls to functions that return this.
   */
  final PassFactory chainCalls = new PassFactory("chainCalls", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ChainCalls(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[396]++;
  }

  /**
   * Rewrite instance methods as static methods, to make them easier
   * to inline.
   */
  final PassFactory devirtualizePrototypeMethods =
      new PassFactory("devirtualizePrototypeMethods", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new DevirtualizePrototypeMethods(compiler);
    }
  };
  static {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[397]++;
  }

  /**
   * Optimizes unused function arguments, unused return values, and inlines
   * constant parameters. Also runs RemoveUnusedVars.
   */
  final PassFactory optimizeCallsAndRemoveUnusedVars =
      new PassFactory("optimizeCalls_and_removeUnusedVars", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[398]++;
      OptimizeCalls passes = new OptimizeCalls(compiler);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[399]++;
int CodeCoverConditionCoverageHelper_C112;
      if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((options.optimizeReturns) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[223]++;
        // Remove unused return values.
        passes.addPass(new OptimizeReturns(compiler));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[400]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[224]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[401]++;
int CodeCoverConditionCoverageHelper_C113;

      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((options.optimizeParameters) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[225]++;
        // Remove all parameters that are constants or unused.
        passes.addPass(new OptimizeParameters(compiler));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[402]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[226]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[403]++;
int CodeCoverConditionCoverageHelper_C114;

      if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((options.optimizeCalls) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[227]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[404]++;
        boolean removeOnlyLocals = options.removeUnusedLocalVars
            && !options.removeUnusedVars;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[405]++;
        boolean preserveAnonymousFunctionNames =
            options.anonymousFunctionNaming !=
            AnonymousFunctionNamingPolicy.OFF;
        passes.addPass(
            new RemoveUnusedVars(compiler, !removeOnlyLocals,
                preserveAnonymousFunctionNames, true));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[406]++;

      } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[228]++;}
      return passes;
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[407]++;
  }

  /**
   * Look for function calls that are pure, and annotate them
   * that way.
   */
  final PassFactory markPureFunctions =
      new PassFactory("markPureFunctions", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new PureFunctionIdentifier.Driver(
          compiler, options.debugFunctionSideEffectsPath, false);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[408]++;
  }

  /**
   * Look for function calls that have no side effects, and annotate them
   * that way.
   */
  final PassFactory markNoSideEffectCalls =
      new PassFactory("markNoSideEffectCalls", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new MarkNoSideEffectCalls(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[409]++;
  }

  /** Inlines variables heuristically. */
  final PassFactory inlineVariables =
      new PassFactory("inlineVariables", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[410]++;
int CodeCoverConditionCoverageHelper_C115;
      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((isInliningForbidden()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[229]++;
        // In old renaming schemes, inlining a variable can change whether
        // or not a property is renamed. This is bad, and those old renaming
        // schemes need to die.
        return new ErrorPass(compiler, CANNOT_USE_PROTOTYPE_AND_VAR);

      } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[230]++;
        InlineVariables.Mode mode;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[411]++;
int CodeCoverConditionCoverageHelper_C116;
        if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((options.inlineVariables) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[231]++;
          mode = InlineVariables.Mode.ALL;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[412]++;

        } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[232]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[413]++;
int CodeCoverConditionCoverageHelper_C117; if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((options.inlineLocalVariables) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[233]++;
          mode = InlineVariables.Mode.LOCALS_ONLY;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[414]++;

        } else {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[234]++;
          throw new IllegalStateException("No variable inlining option set.");
        }
}

        return new InlineVariables(compiler, mode, true);
      }
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[415]++;
  }

  /** Inlines variables that are marked as constants. */
  final PassFactory inlineConstants =
      new PassFactory("inlineConstants", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new InlineVariables(
          compiler, InlineVariables.Mode.CONSTANTS_ONLY, true);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[416]++;
  }

  /**
   * Perform local control flow optimizations.
   */
  final PassFactory minimizeExitPoints =
      new PassFactory("minimizeExitPoints", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new MinimizeExitPoints(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[417]++;
  }

  /**
   * Use data flow analysis to remove dead branches.
   */
  final PassFactory removeUnreachableCode =
      new PassFactory("removeUnreachableCode", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new UnreachableCodeElimination(compiler, true);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[418]++;
  }

  /**
   * Remove prototype properties that do not appear to be used.
   */
  final PassFactory removeUnusedPrototypeProperties =
      new PassFactory("removeUnusedPrototypeProperties", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new RemoveUnusedPrototypeProperties(
          compiler, options.removeUnusedPrototypePropertiesInExterns,
          !options.removeUnusedVars);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[419]++;
  }

  /**
   * Remove prototype properties that do not appear to be used.
   */
  final PassFactory removeUnusedClassProperties =
      new PassFactory("removeUnusedClassProperties", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new RemoveUnusedClassProperties(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[420]++;
  }

  /**
   * Process smart name processing - removes unused classes and does referencing
   * starting with minimum set of names.
   */
  final PassFactory smartNamePass = new PassFactory("smartNamePass", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[421]++;
          NameAnalyzer na = new NameAnalyzer(compiler, false);
          na.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[422]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[423]++;

          String reportPath = options.reportPath;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[424]++;
int CodeCoverConditionCoverageHelper_C118;
          if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((reportPath != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[235]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[425]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
              Files.write(na.getHtmlReport(), new File(reportPath),
                  Charsets.UTF_8);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[426]++;
            } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[238]++;
              compiler.report(JSError.make(REPORT_PATH_IO_ERROR, reportPath));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[427]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[237]++;
}
  }

          } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[236]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[428]++;
int CodeCoverConditionCoverageHelper_C119;

          if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((options.smartNameRemoval) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[239]++;
            na.removeUnreferenced();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[429]++;

          } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[240]++;}
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[430]++;
  }

  /**
   * Process smart name processing - removes unused classes and does referencing
   * starting with minimum set of names.
   */
  final PassFactory smartNamePass2 = new PassFactory("smartNamePass", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[431]++;
          NameAnalyzer na = new NameAnalyzer(compiler, false);
          na.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[432]++;
          na.removeUnreferenced();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[433]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[434]++;
  }

  /** Inlines simple methods, like getters */
  final PassFactory inlineSimpleMethods =
      new PassFactory("inlineSimpleMethods", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new InlineSimpleMethods(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[435]++;
  }

  /** Kills dead assignments. */
  final PassFactory deadAssignmentsElimination =
      new PassFactory("deadAssignmentsElimination", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new DeadAssignmentsElimination(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[436]++;
  }

  /** Inlines function calls. */
  final PassFactory inlineFunctions =
      new PassFactory("inlineFunctions", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[437]++;
      boolean enableBlockInlining = !isInliningForbidden();
      return new InlineFunctions(
          compiler,
          compiler.getUniqueNameIdSupplier(),
          options.inlineFunctions,
          options.inlineLocalFunctions,
          enableBlockInlining,
          options.assumeStrictThis()
              || options.getLanguageIn() == LanguageMode.ECMASCRIPT5_STRICT,
          options.assumeClosuresOnlyCaptureReferences);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[438]++;
  }

  /** Inlines constant properties. */
  final PassFactory inlineProperties =
      new PassFactory("inlineProperties", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new InlineProperties(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[439]++;
  }

  /** Removes variables that are never used. */
  final PassFactory removeUnusedVars =
      new PassFactory("removeUnusedVars", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[440]++;
      boolean removeOnlyLocals = options.removeUnusedLocalVars
          && !options.removeUnusedVars;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[441]++;
      boolean preserveAnonymousFunctionNames =
          options.anonymousFunctionNaming != AnonymousFunctionNamingPolicy.OFF;
      return new RemoveUnusedVars(
          compiler,
          !removeOnlyLocals,
          preserveAnonymousFunctionNames,
          false);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[442]++;
  }

  /**
   * Move global symbols to a deeper common module
   */
  final PassFactory crossModuleCodeMotion =
      new PassFactory("crossModuleCodeMotion", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CrossModuleCodeMotion(compiler, compiler.getModuleGraph());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[443]++;
  }

  /**
   * Move methods to a deeper common module
   */
  final PassFactory crossModuleMethodMotion =
      new PassFactory("crossModuleMethodMotion", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CrossModuleMethodMotion(
          compiler, crossModuleIdGenerator,
          // Only move properties in externs if we're not treating
          // them as exports.
          options.removeUnusedPrototypePropertiesInExterns);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[444]++;
  }

  /**
   * Specialize the initial module at the cost of later modules
   */
  final PassFactory specializeInitialModule =
      new PassFactory("specializeInitialModule", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new SpecializeModule(compiler, devirtualizePrototypeMethods,
          inlineFunctions, removeUnusedPrototypeProperties);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[445]++;
  }

  /** A data-flow based variable inliner. */
  final PassFactory flowSensitiveInlineVariables =
      new PassFactory("flowSensitiveInlineVariables", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new FlowSensitiveInlineVariables(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[446]++;
  }

  /** Uses register-allocation algorithms to use fewer variables. */
  final PassFactory coalesceVariableNames =
      new PassFactory("coalesceVariableNames", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CoalesceVariableNames(compiler, options.generatePseudoNames);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[447]++;
  }

  /**
   * Some simple, local collapses (e.g., {@code var x; var y;} becomes
   * {@code var x,y;}.
   */
  final PassFactory exploitAssign = new PassFactory("exploitAssign", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new PeepholeOptimizationsPass(compiler,
          new ExploitAssigns());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[448]++;
  }

  /**
   * Some simple, local collapses (e.g., {@code var x; var y;} becomes
   * {@code var x,y;}.
   */
  final PassFactory collapseVariableDeclarations =
      new PassFactory("collapseVariableDeclarations", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CollapseVariableDeclarations(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[449]++;
  }

  /**
   * Simple global collapses of variable declarations.
   */
  final PassFactory groupVariableDeclarations =
      new PassFactory("groupVariableDeclarations", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new GroupVariableDeclarations(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[450]++;
  }

  /**
   * Extracts common sub-expressions.
   */
  final PassFactory extractPrototypeMemberDeclarations =
      new PassFactory("extractPrototypeMemberDeclarations", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ExtractPrototypeMemberDeclarations(
          compiler, Pattern.USE_GLOBAL_TEMP);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[451]++;
  }

  /** Rewrites common function definitions to be more compact. */
  final PassFactory rewriteFunctionExpressions =
      new PassFactory("rewriteFunctionExpressions", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new FunctionRewriter(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[452]++;
  }

  /** Collapses functions to not use the VAR keyword. */
  final PassFactory collapseAnonymousFunctions =
      new PassFactory("collapseAnonymousFunctions", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new CollapseAnonymousFunctions(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[453]++;
  }

  /** Moves function declarations to the top, to simulate actual hoisting. */
  final PassFactory moveFunctionDeclarations =
      new PassFactory("moveFunctionDeclarations", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new MoveFunctionDeclarations(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[454]++;
  }

  final PassFactory nameUnmappedAnonymousFunctions =
      new PassFactory("nameAnonymousFunctions", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new NameAnonymousFunctions(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[455]++;
  }

  final PassFactory nameMappedAnonymousFunctions =
      new PassFactory("nameAnonymousFunctions", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[456]++;
          NameAnonymousFunctionsMapped naf =
              new NameAnonymousFunctionsMapped(
                  compiler, options.inputAnonymousFunctionNamingMap);
          naf.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[457]++;
          anonymousFunctionNameMap = naf.getFunctionMap();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[458]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[459]++;
  }

  /** Alias external symbols. */
  final PassFactory aliasExternals = new PassFactory("aliasExternals", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new AliasExternals(compiler, compiler.getModuleGraph(),
          options.unaliasableGlobals, options.aliasableGlobals);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[460]++;
  }

  /**
   * Alias string literals with global variables, to avoid creating lots of
   * transient objects.
   */
  final PassFactory aliasStrings = new PassFactory("aliasStrings", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new AliasStrings(
          compiler,
          compiler.getModuleGraph(),
          options.aliasAllStrings ? null : options.aliasableStrings,
          options.aliasStringsBlacklist,
          options.outputJsStringUsage);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[461]++;
  }

  /** Aliases common keywords (true, false) */
  final PassFactory aliasKeywords = new PassFactory("aliasKeywords", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new AliasKeywords(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[462]++;
  }

  /** Handling for the ObjectPropertyString primitive. */
  final PassFactory objectPropertyStringPostprocess =
      new PassFactory("ObjectPropertyStringPostprocess", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ObjectPropertyStringPostprocess(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[463]++;
  }

  /**
   * Renames properties so that the two properties that never appear on
   * the same object get the same name.
   */
  final PassFactory ambiguateProperties =
      new PassFactory("ambiguateProperties", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new AmbiguateProperties(
          compiler, options.anonymousFunctionNaming.getReservedCharacters());
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[464]++;
  }

  /**
   * Mark the point at which the normalized AST assumptions no longer hold.
   */
  final PassFactory markUnnormalized =
      new PassFactory("markUnnormalized", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
          compiler.setLifeCycleStage(LifeCycleStage.RAW);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[465]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[466]++;
  }

  /** Denormalize the AST for code generation. */
  final PassFactory denormalize = new PassFactory("denormalize", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new Denormalize(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[467]++;
  }

  /** Inverting name normalization. */
  final PassFactory invertContextualRenaming =
      new PassFactory("invertContextualRenaming", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return MakeDeclaredNamesUnique.getContextualRenameInverter(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[468]++;
  }

  /**
   * Renames properties.
   */
  final PassFactory renameProperties =
      new PassFactory("renameProperties", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[469]++;
      final VariableMap prevPropertyMap = options.inputPropertyMap;
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
          propertyMap = runPropertyRenaming(
              compiler, prevPropertyMap, externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[470]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[471]++;
  }

  private VariableMap runPropertyRenaming(
      AbstractCompiler compiler, VariableMap prevPropertyMap,
      Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[472]++;
    char[] reservedChars =
        options.anonymousFunctionNaming.getReservedCharacters();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[473]++;
    switch (options.propertyRenaming) {
      case HEURISTIC:
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[241]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[474]++;
        RenamePrototypes rproto = new RenamePrototypes(compiler, false,
            reservedChars, prevPropertyMap);
        rproto.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[475]++;
        return rproto.getPropertyMap();

      case AGGRESSIVE_HEURISTIC:
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[242]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[476]++;
        RenamePrototypes rproto2 = new RenamePrototypes(compiler, true,
            reservedChars, prevPropertyMap);
        rproto2.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[477]++;
        return rproto2.getPropertyMap();

      case ALL_UNQUOTED:
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[243]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[478]++;
        RenameProperties rprop = new RenameProperties(
            compiler, options.propertyAffinity, options.generatePseudoNames,
            prevPropertyMap, reservedChars);
        rprop.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[479]++;
        return rprop.getPropertyMap();

      default:
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[244]++;
        throw new IllegalStateException(
            "Unrecognized property renaming policy");
    }
  }

  /** Renames variables. */
  final PassFactory renameVars = new PassFactory("renameVars", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[480]++;
      final VariableMap prevVariableMap = options.inputVariableMap;
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
          variableMap = runVariableRenaming(
              compiler, prevVariableMap, externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[481]++;
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[482]++;
  }

  private VariableMap runVariableRenaming(
      AbstractCompiler compiler, VariableMap prevVariableMap,
      Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[483]++;
    char[] reservedChars =
        options.anonymousFunctionNaming.getReservedCharacters();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[484]++;
    boolean preserveAnonymousFunctionNames =
        options.anonymousFunctionNaming != AnonymousFunctionNamingPolicy.OFF;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[485]++;
    Set<String> reservedNames = Sets.newHashSet();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[486]++;
int CodeCoverConditionCoverageHelper_C120;
    if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((exportedNames != null) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[245]++;
      reservedNames.addAll(exportedNames);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[487]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[246]++;}
    reservedNames.addAll(ParserRunner.getReservedVars());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[488]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[489]++;
    RenameVars rn = new RenameVars(
        compiler,
        options.renamePrefix,
        options.variableRenaming == VariableRenamingPolicy.LOCAL,
        preserveAnonymousFunctionNames,
        options.generatePseudoNames,
        options.shadowVariables,
        prevVariableMap,
        reservedChars,
        reservedNames);
    rn.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[490]++;
    return rn.getVariableMap();
  }

  /** Renames labels */
  final PassFactory renameLabels = new PassFactory("renameLabels", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new RenameLabels(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[491]++;
  }

  /** Convert bracket access to dot access */
  final PassFactory convertToDottedProperties =
      new PassFactory("convertToDottedProperties", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new ConvertToDottedProperties(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[492]++;
  }

  /** Checks that all variables are defined. */
  final PassFactory sanityCheckAst = new PassFactory("sanityCheckAst", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new AstValidator();
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[493]++;
  }

  /** Checks that all variables are defined. */
  final PassFactory sanityCheckVars = new PassFactory("sanityCheckVars", true) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new VarCheck(compiler, true);
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[494]++;
  }

  /** Adds instrumentations according to an instrumentation template. */
  final PassFactory instrumentFunctions =
      new PassFactory("instrumentFunctions", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[495]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
          try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[496]++;
            FileReader templateFile =
                new FileReader(options.instrumentationTemplate);
            (new InstrumentFunctions(
                compiler, functionNames,
                options.instrumentationTemplate,
                options.appNameStr,
                templateFile)).process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[497]++;
          } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[248]++;
            compiler.report(
                JSError.make(AbstractCompiler.READ_ERROR,
                    options.instrumentationTemplate));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[498]++;
          } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[247]++;
}
  }
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[499]++;
  }

  /**
   * Create a no-op pass that can only run once. Used to break up loops.
   */
  static PassFactory createEmptyPass(String name) {
    return new PassFactory(name, true) {
      @Override
      protected CompilerPass create(final AbstractCompiler compiler) {
        return runInSerial();
      }
    };
  }

  /**
   * Runs custom passes that are designated to run at a particular time.
   */
  private PassFactory getCustomPasses(
      final CustomPassExecutionTime executionTime) {
    return new PassFactory("runCustomPasses", true) {
      @Override
      protected CompilerPass create(final AbstractCompiler compiler) {
        return runInSerial(options.customPasses.get(executionTime));
      }
    };
  }

  /**
   * All inlining is forbidden in heuristic renaming mode, because inlining
   * will ruin the invariants that it depends on.
   */
  private boolean isInliningForbidden() {
    return options.propertyRenaming == PropertyRenamingPolicy.HEURISTIC ||
        options.propertyRenaming ==
            PropertyRenamingPolicy.AGGRESSIVE_HEURISTIC;
  }

  /** Create a compiler pass that runs the given passes in serial. */
  private static CompilerPass runInSerial(final CompilerPass ... passes) {
    return runInSerial(Lists.newArrayList(passes));
  }

  /** Create a compiler pass that runs the given passes in serial. */
  private static CompilerPass runInSerial(
      final Collection<CompilerPass> passes) {
    return new CompilerPass() {
      @Override public void process(Node externs, Node root) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[500]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[7]++;


        for (CompilerPass pass : passes) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[7]--;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[8]--;
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.loops[9]++;
}
          pass.process(externs, root);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[501]++;
        }
      }
    };
  }

  @VisibleForTesting
  static Map<String, Node> getAdditionalReplacements(
      CompilerOptions options) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[502]++;
    Map<String, Node> additionalReplacements = Maps.newHashMap();
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[503]++;
int CodeCoverConditionCoverageHelper_C121;

    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (8)) == 0 || true) &&
 ((options.markAsCompiled) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[249]++;
      additionalReplacements.put(COMPILED_CONSTANT_NAME, IR.trueNode());
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[504]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[250]++;}
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[505]++;
int CodeCoverConditionCoverageHelper_C122;

    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (8)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((options.locale != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) || true)) || (CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 2) && false)) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[251]++;
      additionalReplacements.put(CLOSURE_LOCALE_CONSTANT_NAME,
          IR.string(options.locale));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[506]++;

    } else {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[252]++;}

    return additionalReplacements;
  }

  final PassFactory printNameReferenceGraph =
    new PassFactory("printNameReferenceGraph", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[507]++;
          NameReferenceGraphConstruction gc =
              new NameReferenceGraphConstruction(compiler);
          gc.process(externs, jsRoot);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[508]++;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[509]++;
          String graphFileName = options.nameReferenceGraphPath;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[510]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
          try {
CodeCoverTryBranchHelper_Try3 = true;
            Files.write(DotFormatter.toDot(gc.getNameReferenceGraph()),
                new File(graphFileName),
                Charsets.UTF_8);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[511]++;
          } catch (IOException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[254]++;
            compiler.report(
                JSError.make(
                    NAME_REF_GRAPH_FILE_ERROR, e.getMessage(), graphFileName));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[512]++;
          } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[253]++;
}
  }
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[513]++;
  }

  final PassFactory printNameReferenceReport =
      new PassFactory("printNameReferenceReport", true) {
    @Override
    protected CompilerPass create(final AbstractCompiler compiler) {
      return new CompilerPass() {
        @Override
        public void process(Node externs, Node jsRoot) {
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[514]++;
          NameReferenceGraphConstruction gc =
              new NameReferenceGraphConstruction(compiler);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[515]++;
          String reportFileName = options.nameReferenceReportPath;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[516]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
          try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[517]++;
            NameReferenceGraphReport report =
                new NameReferenceGraphReport(gc.getNameReferenceGraph());
            Files.write(report.getHtmlReport(),
                new File(reportFileName),
                Charsets.UTF_8);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[518]++;
          } catch (IOException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[256]++;
            compiler.report(
                JSError.make(
                    NAME_REF_REPORT_FILE_ERROR,
                    e.getMessage(),
                    reportFileName));
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[519]++;
          } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.branches[255]++;
}
  }
        }
      };
    }
  };
  {
    CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[520]++;
  }

  /**
   * A pass-factory that is good for {@code HotSwapCompilerPass} passes.
   */
  abstract static class HotSwapPassFactory extends PassFactory {

    HotSwapPassFactory(String name, boolean isOneTimePass) {
      super(name, isOneTimePass);
CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl.statements[521]++;
    }

    @Override
    protected abstract HotSwapCompilerPass create(AbstractCompiler compiler);

    @Override
    HotSwapCompilerPass getHotSwapPass(AbstractCompiler compiler) {
      return this.create(compiler);
    }
  }

}

class CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl ());
  }
    public static long[] statements = new long[522];
    public static long[] branches = new long[257];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[123];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.DefaultPassConfig.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,3,2,1,1,1,1,1,2,1,1,1,1,2,1,2,3,3,1,2,1,1,1,1,2,2,2,1,1,1,3,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,1,3,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,2,1,1,1,2,2,1,1,1,1,2,1,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,2};
    for (int i = 1; i <= 122; i++) {
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

  public CodeCoverCoverageCounter$b80b2dtcd297gpf7mufmh61h71fseoi4bl () {
    super("com.google.javascript.jscomp.DefaultPassConfig.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 521; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 256; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 122; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DefaultPassConfig.java");
      for (int i = 1; i <= 521; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 256; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 122; i++) {
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

