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

import com.google.javascript.jscomp.CompilerOptions.Reach;

/**
 * A CompilationLevel represents the level of optimization that should be
 * applied when compiling JavaScript code.
 *
 * @author bolinfest@google.com (Michael Bolin)
 */
public enum CompilationLevel {

  /**
   * WHITESPACE_ONLY removes comments and extra whitespace in the input JS.
   */
  WHITESPACE_ONLY,

  /**
   * SIMPLE_OPTIMIZATIONS performs transformations to the input JS that do not
   * require any changes to JS that depend on the input JS. For example,
   * function arguments are renamed (which should not matter to code that
   * depends on the input JS), but functions themselves are not renamed (which
   * would otherwise require external code to change to use the renamed function
   * names).
   */
  SIMPLE_OPTIMIZATIONS,

  /**
   * ADVANCED_OPTIMIZATIONS aggressively reduces code size by renaming function
   * names and variables, removing code which is never called, etc.
   */
  ADVANCED_OPTIMIZATIONS,
  ;

  private CompilationLevel() {}

  public void setOptionsForCompilationLevel(CompilerOptions options) {
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[1]++;
    switch (this) {
      case WHITESPACE_ONLY:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[1]++;
        applyBasicCompilationOptions(options);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[2]++;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[3]++;
        break;
      case SIMPLE_OPTIMIZATIONS:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[2]++;
        applySafeCompilationOptions(options);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[4]++;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[5]++;
        break;
      case ADVANCED_OPTIMIZATIONS:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[3]++;
        applyFullCompilationOptions(options);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[6]++;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[7]++;
        break;
      default:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[4]++;
        throw new RuntimeException("Unknown compilation level.");
    }
  }

  public void setDebugOptionsForCompilationLevel(CompilerOptions options) {
    options.anonymousFunctionNaming = AnonymousFunctionNamingPolicy.UNMAPPED;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[8]++;
    options.generatePseudoNames = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[9]++;
    options.removeClosureAsserts = false;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[10]++;
    // Don't shadow variables as it is too confusing.
    options.shadowVariables = false;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[11]++;
  }

  /**
   * Gets options that only strip whitespace and comments.
   * @param options The CompilerOptions object to set the options on.
   */
  private static void applyBasicCompilationOptions(CompilerOptions options) {
    options.skipAllCompilerPasses();
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[12]++;
  }

  /**
   * Add options that are safe. Safe means options that won't break the
   * JavaScript code even if no symbols are exported and no coding convention
   * is used.
   * @param options The CompilerOptions object to set the options on.
   */
  private static void applySafeCompilationOptions(CompilerOptions options) {
    // ReplaceIdGenerators is on by default, but should run in simple mode.
    options.replaceIdGenerators = false;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[13]++;

    // Does not call applyBasicCompilationOptions(options) because the call to
    // skipAllCompilerPasses() cannot be easily undone.
    options.dependencyOptions.setDependencySorting(true);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[14]++;
    options.closurePass = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[15]++;
    options.setRenamingPolicy(
        VariableRenamingPolicy.LOCAL, PropertyRenamingPolicy.OFF);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[16]++;
    options.shadowVariables = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[17]++;
    options.setInlineVariables(Reach.LOCAL_ONLY);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[18]++;
    options.flowSensitiveInlineVariables = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[19]++;
    options.setInlineFunctions(Reach.LOCAL_ONLY);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[20]++;
    options.setAssumeClosuresOnlyCaptureReferences(false);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[21]++;
    options.checkGlobalThisLevel = CheckLevel.OFF;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[22]++;
    options.foldConstants = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[23]++;
    options.coalesceVariableNames = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[24]++;
    options.deadAssignmentElimination = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[25]++;
    options.collapseVariableDeclarations = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[26]++;
    options.convertToDottedProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[27]++;
    options.labelRenaming = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[28]++;
    options.removeDeadCode = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[29]++;
    options.optimizeArgumentsArray = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[30]++;
    options.setRemoveUnusedVariables(Reach.LOCAL_ONLY);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[31]++;
    options.collapseObjectLiterals = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[32]++;
    options.protectHiddenSideEffects = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[33]++;
  }

  /**
   * Add the options that will work only if the user exported all the symbols
   * correctly.
   * @param options The CompilerOptions object to set the options on.
   */
  private static void applyFullCompilationOptions(CompilerOptions options) {
    // Do not call applySafeCompilationOptions(options) because the call can
    // create possible conflicts between multiple diagnostic groups.

    // All the safe optimizations.
    options.dependencyOptions.setDependencySorting(true);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[34]++;
    options.closurePass = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[35]++;
    options.foldConstants = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[36]++;
    options.coalesceVariableNames = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[37]++;
    options.deadAssignmentElimination = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[38]++;
    options.extractPrototypeMemberDeclarations = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[39]++;
    options.collapseVariableDeclarations = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[40]++;
    options.convertToDottedProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[41]++;
    options.rewriteFunctionExpressions = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[42]++;
    options.labelRenaming = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[43]++;
    options.removeDeadCode = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[44]++;
    options.optimizeArgumentsArray = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[45]++;
    options.collapseObjectLiterals = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[46]++;
    options.protectHiddenSideEffects = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[47]++;

    // All the advance optimizations.
    options.removeClosureAsserts = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[48]++;
    options.aliasKeywords = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[49]++;
    options.reserveRawExports = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[50]++;
    options.setRenamingPolicy(
        VariableRenamingPolicy.ALL, PropertyRenamingPolicy.ALL_UNQUOTED);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[51]++;
    options.shadowVariables = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[52]++;
    options.removeUnusedPrototypeProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[53]++;
    options.removeUnusedPrototypePropertiesInExterns = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[54]++;
    options.collapseAnonymousFunctions = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[55]++;
    options.collapseProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[56]++;
    options.checkGlobalThisLevel = CheckLevel.WARNING;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[57]++;
    options.rewriteFunctionExpressions = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[58]++;
    options.smartNameRemoval = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[59]++;
    options.inlineConstantVars = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[60]++;
    options.setInlineFunctions(Reach.ALL);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[61]++;
    options.setAssumeClosuresOnlyCaptureReferences(false);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[62]++;
    options.inlineGetters = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[63]++;
    options.setInlineVariables(Reach.ALL);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[64]++;
    options.flowSensitiveInlineVariables = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[65]++;
    options.computeFunctionSideEffects = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[66]++;

    // Remove unused vars also removes unused functions.
    options.setRemoveUnusedVariables(Reach.ALL);
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[67]++;

    // Move code around based on the defined modules.
    options.crossModuleCodeMotion = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[68]++;
    options.crossModuleMethodMotion = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[69]++;

    // Call optimizations
    options.devirtualizePrototypeMethods = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[70]++;
    options.optimizeParameters = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[71]++;
    options.optimizeReturns = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[72]++;
    options.optimizeCalls = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[73]++;
  }

  /**
   * Enable additional optimizations that use type information.
   * @param options The CompilerOptions object to set the options on.
   */
  public void setTypeBasedOptimizationOptions(CompilerOptions options) {
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[74]++;
    switch (this) {
      case ADVANCED_OPTIMIZATIONS:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[5]++;
        options.inferTypes = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[75]++;
        options.disambiguateProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[76]++;
        options.ambiguateProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[77]++;
        options.inlineProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[78]++;
        // TODO(johnlenz) :removeUnusedClassProperties isn't strictly a
        // type based pass, but add it here for now because I may have to
        // make it into one.
        options.removeUnusedClassProperties = true;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[79]++;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[80]++;
        break;
      case SIMPLE_OPTIMIZATIONS:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[6]++;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[81]++;
        // TODO(johnlenz): enable peephole type based optimization.
        break;
      case WHITESPACE_ONLY:
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[7]++;
CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.statements[82]++;
        break; default : CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip.branches[8]++;
    }
  }
}

class CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip ());
  }
    public static long[] statements = new long[83];
    public static long[] branches = new long[9];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1k0k6xjbtd5gx53f79dk21rihi97a7wip () {
    super("com.google.javascript.jscomp.CompilationLevel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 82; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CompilationLevel.java");
      for (int i = 1; i <= 82; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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

