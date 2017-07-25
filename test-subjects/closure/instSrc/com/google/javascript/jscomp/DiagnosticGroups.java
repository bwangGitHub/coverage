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
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Named groups of DiagnosticTypes exposed by Compiler.
 * @author nicksantos@google.com (Nick Santos)
 */
public class DiagnosticGroups {
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.ping();
  }

  static final DiagnosticType UNUSED =
      DiagnosticType.warning("JSC_UNUSED", "{0}");
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[1]++;
  }

  public DiagnosticGroups() {}

  private static final Map<String, DiagnosticGroup> groupsByName =
      Maps.newHashMap();
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[2]++;
  }

  static DiagnosticGroup registerDeprecatedGroup(String name) {
    return registerGroup(name, new DiagnosticGroup(name, UNUSED));
  }

  static DiagnosticGroup registerGroup(String name,
      DiagnosticGroup group) {
    groupsByName.put(name, group);
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[3]++;
    return group;
  }

  static DiagnosticGroup registerGroup(String name,
      DiagnosticType ... types) {
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[4]++;
    DiagnosticGroup group = new DiagnosticGroup(name, types);
    groupsByName.put(name, group);
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[5]++;
    return group;
  }

  static DiagnosticGroup registerGroup(String name,
      DiagnosticGroup ... groups) {
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[6]++;
    DiagnosticGroup group = new DiagnosticGroup(name, groups);
    groupsByName.put(name, group);
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[7]++;
    return group;
  }

  /** Get the registered diagnostic groups, indexed by name. */
  protected Map<String, DiagnosticGroup> getRegisteredGroups() {
    return ImmutableMap.copyOf(groupsByName);
  }

  /** Find the diagnostic group registered under the given name. */
  public DiagnosticGroup forName(String name) {
    return groupsByName.get(name);
  }

  // A bit of a hack to display the available groups on the command-line.
  // New groups should be added to this list if they are public and should
  // be listed on the command-line as an available option.
  //
  // If a group is suppressible on a per-file basis, it should be added
  // to parser/ParserConfig.properties
  static final String DIAGNOSTIC_GROUP_NAMES =
      "accessControls, ambiguousFunctionDecl, checkRegExp, " +
      "checkTypes, checkVars, const, constantProperty, deprecated, " +
      "duplicateMessage, " +
      "es5Strict, externsValidation, fileoverviewTags, globalThis, " +
      "internetExplorerChecks, invalidCasts, misplacedTypeAnnotation, " +
      "missingProperties, " +
      "nonStandardJsDocs, suspiciousCode, strictModuleDepCheck, " +
      "typeInvalidation, " +
      "undefinedNames, undefinedVars, unknownDefines, uselessCode, " +
      "visibility";
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[8]++;
  }

  public static final DiagnosticGroup GLOBAL_THIS =
      DiagnosticGroups.registerGroup("globalThis",
          CheckGlobalThis.GLOBAL_THIS);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[9]++;
  }

  public static final DiagnosticGroup DEPRECATED =
      DiagnosticGroups.registerGroup("deprecated",
          CheckAccessControls.DEPRECATED_NAME,
          CheckAccessControls.DEPRECATED_NAME_REASON,
          CheckAccessControls.DEPRECATED_PROP,
          CheckAccessControls.DEPRECATED_PROP_REASON,
          CheckAccessControls.DEPRECATED_CLASS,
          CheckAccessControls.DEPRECATED_CLASS_REASON);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[10]++;
  }

  public static final DiagnosticGroup VISIBILITY =
      DiagnosticGroups.registerGroup("visibility",
          CheckAccessControls.BAD_PRIVATE_GLOBAL_ACCESS,
          CheckAccessControls.BAD_PRIVATE_PROPERTY_ACCESS,
          CheckAccessControls.BAD_PROTECTED_PROPERTY_ACCESS,
          CheckAccessControls.PRIVATE_OVERRIDE,
          CheckAccessControls.VISIBILITY_MISMATCH);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[11]++;
  }

  public static final DiagnosticGroup CONSTANT_PROPERTY =
      DiagnosticGroups.registerGroup("constantProperty",
          CheckAccessControls.CONST_PROPERTY_DELETED,
          CheckAccessControls.CONST_PROPERTY_REASSIGNED_VALUE);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[12]++;
  }

  public static final DiagnosticGroup NON_STANDARD_JSDOC =
      DiagnosticGroups.registerGroup("nonStandardJsDocs",
          RhinoErrorReporter.BAD_JSDOC_ANNOTATION);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[13]++;
  }

  public static final DiagnosticGroup ACCESS_CONTROLS =
      DiagnosticGroups.registerGroup("accessControls",
          DEPRECATED, VISIBILITY);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[14]++;
  }

  public static final DiagnosticGroup INVALID_CASTS =
      DiagnosticGroups.registerGroup("invalidCasts",
          TypeValidator.INVALID_CAST);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[15]++;
  }

  public static final DiagnosticGroup FILEOVERVIEW_JSDOC =
      DiagnosticGroups.registerDeprecatedGroup("fileoverviewTags");
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[16]++;
  }

  public static final DiagnosticGroup STRICT_MODULE_DEP_CHECK =
      DiagnosticGroups.registerGroup("strictModuleDepCheck",
          VarCheck.STRICT_MODULE_DEP_ERROR,
          CheckGlobalNames.STRICT_MODULE_DEP_QNAME);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[17]++;
  }

  public static final DiagnosticGroup VIOLATED_MODULE_DEP =
      DiagnosticGroups.registerGroup("violatedModuleDep",
          VarCheck.VIOLATED_MODULE_DEP_ERROR);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[18]++;
  }

  public static final DiagnosticGroup EXTERNS_VALIDATION =
      DiagnosticGroups.registerGroup("externsValidation",
          VarCheck.NAME_REFERENCE_IN_EXTERNS_ERROR,
          VarCheck.UNDEFINED_EXTERN_VAR_ERROR);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[19]++;
  }

  public static final DiagnosticGroup AMBIGUOUS_FUNCTION_DECL =
      DiagnosticGroups.registerGroup("ambiguousFunctionDecl",
          VariableReferenceCheck.AMBIGUOUS_FUNCTION_DECL,
          StrictModeCheck.BAD_FUNCTION_DECLARATION);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[20]++;
  }

  public static final DiagnosticGroup UNKNOWN_DEFINES =
      DiagnosticGroups.registerGroup("unknownDefines",
          ProcessDefines.UNKNOWN_DEFINE_WARNING);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[21]++;
  }

  public static final DiagnosticGroup TWEAKS =
      DiagnosticGroups.registerGroup("tweakValidation",
          ProcessTweaks.INVALID_TWEAK_DEFAULT_VALUE_WARNING,
          ProcessTweaks.TWEAK_WRONG_GETTER_TYPE_WARNING,
          ProcessTweaks.UNKNOWN_TWEAK_WARNING);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[22]++;
  }

  public static final DiagnosticGroup MISSING_PROPERTIES =
      DiagnosticGroups.registerGroup("missingProperties",
          TypeCheck.INEXISTENT_PROPERTY);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[23]++;
  }

  public static final DiagnosticGroup INTERNET_EXPLORER_CHECKS =
      DiagnosticGroups.registerGroup("internetExplorerChecks",
          RhinoErrorReporter.TRAILING_COMMA);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[24]++;
  }

  public static final DiagnosticGroup UNDEFINED_VARIABLES =
      DiagnosticGroups.registerGroup("undefinedVars",
          VarCheck.UNDEFINED_VAR_ERROR);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[25]++;
  }

  public static final DiagnosticGroup UNDEFINED_NAMES =
      DiagnosticGroups.registerGroup("undefinedNames",
          CheckGlobalNames.UNDEFINED_NAME_WARNING);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[26]++;
  }

  public static final DiagnosticGroup DEBUGGER_STATEMENT_PRESENT =
      DiagnosticGroups.registerGroup("checkDebuggerStatement",
          CheckDebuggerStatement.DEBUGGER_STATEMENT_PRESENT);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[27]++;
  }

  public static final DiagnosticGroup CHECK_REGEXP =
      DiagnosticGroups.registerGroup("checkRegExp",
          CheckRegExp.REGEXP_REFERENCE,
          CheckRegExp.MALFORMED_REGEXP);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[28]++;
  }

  public static final DiagnosticGroup CHECK_TYPES =
      DiagnosticGroups.registerGroup("checkTypes",
          TypeValidator.ALL_DIAGNOSTICS,
          TypeCheck.ALL_DIAGNOSTICS);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[29]++;
  }

  public static final DiagnosticGroup CHECK_STRUCT_DICT_INHERITENCE =
      DiagnosticGroups.registerGroup("checkStructDictInheritence",
          TypeCheck.CONFLICTING_SHAPE_TYPE);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[30]++;
  }

  public static final DiagnosticGroup CHECK_VARIABLES =
      DiagnosticGroups.registerGroup("checkVars",
          VarCheck.UNDEFINED_VAR_ERROR,
          SyntacticScopeCreator.VAR_MULTIPLY_DECLARED_ERROR);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[31]++;
  }

  public static final DiagnosticGroup CHECK_USELESS_CODE =
      DiagnosticGroups.registerGroup("uselessCode",
          CheckSideEffects.USELESS_CODE_ERROR,
          CheckUnreachableCode.UNREACHABLE_CODE);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[32]++;
  }

  public static final DiagnosticGroup CONST =
      DiagnosticGroups.registerGroup("const",
          CheckAccessControls.CONST_PROPERTY_DELETED,
          CheckAccessControls.CONST_PROPERTY_REASSIGNED_VALUE,
          ConstCheck.CONST_REASSIGNED_VALUE_ERROR);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[33]++;
  }

  public static final DiagnosticGroup TYPE_INVALIDATION =
      DiagnosticGroups.registerGroup("typeInvalidation",
          DisambiguateProperties.Warnings.INVALIDATION,
          DisambiguateProperties.Warnings.INVALIDATION_ON_TYPE);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[34]++;
  }

  public static final DiagnosticGroup DUPLICATE_VARS =
      DiagnosticGroups.registerGroup("duplicate",
          SyntacticScopeCreator.VAR_MULTIPLY_DECLARED_ERROR,
          TypeValidator.DUP_VAR_DECLARATION);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[35]++;
  }

  public static final DiagnosticGroup ES5_STRICT =
      DiagnosticGroups.registerGroup("es5Strict",
          ControlStructureCheck.USE_OF_WITH,
          StrictModeCheck.UNKNOWN_VARIABLE,
          StrictModeCheck.EVAL_DECLARATION,
          StrictModeCheck.EVAL_ASSIGNMENT,
          StrictModeCheck.ARGUMENTS_DECLARATION,
          StrictModeCheck.ARGUMENTS_ASSIGNMENT,
          StrictModeCheck.DELETE_VARIABLE,
          StrictModeCheck.DUPLICATE_OBJECT_KEY,
          StrictModeCheck.BAD_FUNCTION_DECLARATION);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[36]++;
  }

  public static final DiagnosticGroup CHECK_PROVIDES =
      DiagnosticGroups.registerGroup("checkProvides",
          CheckProvides.MISSING_PROVIDE_WARNING);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[37]++;
  }

  public static final DiagnosticGroup DUPLICATE_MESSAGE =
      DiagnosticGroups.registerGroup("duplicateMessage",
          JsMessageVisitor.MESSAGE_DUPLICATE_KEY);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[38]++;
  }

  public static final DiagnosticGroup MISPLACED_TYPE_ANNOTATION =
      DiagnosticGroups.registerGroup("misplacedTypeAnnotation",
          RhinoErrorReporter.MISPLACED_TYPE_ANNOTATION);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[39]++;
  }

  public static final DiagnosticGroup SUSPICIOUS_CODE =
      DiagnosticGroups.registerGroup("suspiciousCode",
          CheckSuspiciousCode.SUSPICIOUS_SEMICOLON,
          CheckSuspiciousCode.SUSPICIOUS_COMPARISON_WITH_NAN);
  static {
    CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[40]++;
  }

  /**
   * Adds warning levels by name.
   */
  void setWarningLevel(CompilerOptions options,
      String name, CheckLevel level) {
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[41]++;
    DiagnosticGroup group = forName(name);
    Preconditions.checkNotNull(group, "No warning class for name: %s", name);
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[42]++;
    options.setWarningLevel(group, level);
CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x.statements[43]++;
  }
}

class CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x ());
  }
    public static long[] statements = new long[44];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1ktrammva523hr69z0n0wfiil66vca34x () {
    super("com.google.javascript.jscomp.DiagnosticGroups.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 43; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.DiagnosticGroups.java");
      for (int i = 1; i <= 43; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
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

