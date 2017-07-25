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

package com.google.javascript.jscomp.deps;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.DiagnosticType;
import com.google.javascript.jscomp.ErrorManager;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.SourceFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Generates deps.js files by scanning JavaScript files for
 * calls to goog.provide(), goog.require() and goog.addDependency().
 *
 * @author agrieve@google.com (Andrew Grieve)
 */
public class DepsGenerator {
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.ping();
  }


  public static enum InclusionStrategy {
    ALWAYS,
    WHEN_IN_SRCS,
    DO_NOT_DUPLICATE
  }

  private static Logger logger =
      Logger.getLogger(DepsGenerator.class.getName());
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[1]++;
  }

  // See the Flags in MakeJsDeps for descriptions of these.
  private final Collection<SourceFile> srcs;
  private final Collection<SourceFile> deps;
  private final String closurePathAbs;
  private final InclusionStrategy mergeStrategy;
  final ErrorManager errorManager;

  static final DiagnosticType SAME_FILE_WARNING = DiagnosticType.warning(
      "DEPS_SAME_FILE",
      "Namespace \"{0}\" is both required and provided in the same file.");
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[2]++;
  }

  static final DiagnosticType NEVER_PROVIDED_ERROR = DiagnosticType.error(
      "DEPS_NEVER_PROVIDED",
      "Namespace \"{0}\" is required but never provided.");
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[3]++;
  }

  static final DiagnosticType DUPE_PROVIDES_WARNING = DiagnosticType.warning(
      "DEPS_DUPE_PROVIDES",
      "Multiple calls to goog.provide(\"{0}\")");
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[4]++;
  }

  static final DiagnosticType MULTIPLE_PROVIDES_ERROR = DiagnosticType.error(
      "DEPS_DUPE_PROVIDES",
      "Namespace \"{0}\" is already provided in other file {1}");
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[5]++;
  }

  static final DiagnosticType DUPE_REQUIRE_WARNING = DiagnosticType.warning(
      "DEPS_DUPE_REQUIRES",
      "Namespace \"{0}\" is required multiple times");
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[6]++;
  }

  static final DiagnosticType NO_DEPS_WARNING = DiagnosticType.warning(
      "DEPS_NO_DEPS",
      "No dependencies found in file");
  static {
    CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[7]++;
  }

  /**
   * Creates a new DepsGenerator.
   */
  public DepsGenerator(
      Collection<SourceFile> deps,
      Collection<SourceFile> srcs,
      InclusionStrategy mergeStrategy,
      String closurePathAbs,
      ErrorManager errorManager) {
    this.deps = deps;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[8]++;
    this.srcs = srcs;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[9]++;
    this.mergeStrategy = mergeStrategy;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[10]++;
    this.closurePathAbs = closurePathAbs;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[11]++;
    this.errorManager = errorManager;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[12]++;
  }

  /**
   * Performs the parsing inputs and writing of outputs.
   * @throws IOException Occurs upon an IO error.
   * @return Returns a String of goog.addDependency calls that will build
   *     the dependency graph. Returns null if there was an error.
   */
  public String computeDependencyCalls() throws IOException {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[13]++;
    // Build a map of closure-relative path -> DepInfo.
    Map<String, DependencyInfo> depsFiles = parseDepsFiles();
    logger.fine("preparsedFiles: " + depsFiles);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[14]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[15]++;

    // Find all goog.provides & goog.requires in src files
    Map<String, DependencyInfo> jsFiles = parseSources(depsFiles.keySet());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;

    // Check if there were any parse errors.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorManager.getErrorCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[1]++;
      return null;

    } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[2]++;}

    cleanUpDuplicatedFiles(depsFiles, jsFiles);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[17]++;

    // Check for missing provides or other semantic inconsistencies.
    validateDependencies(depsFiles.values(), jsFiles.values());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[18]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((errorManager.getErrorCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[3]++;
      return null;

    } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[4]++;}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[20]++;

    ByteArrayOutputStream output = new ByteArrayOutputStream();
    writeDepsContent(depsFiles, jsFiles, new PrintStream(output));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[21]++;
    return new String(output.toByteArray());
  }

  /**
   * Removes duplicated depsInfo from jsFiles if this info already present in
   * some of the parsed deps.js
   *
   * @param depsFiles DepsInfo from deps.js dependencies
   * @param jsFiles DepsInfo from some of jsSources
   */
  protected void cleanUpDuplicatedFiles(Map<String, DependencyInfo> depsFiles,
      Map<String, DependencyInfo> jsFiles) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[22]++;
    Set<String> depsPathsCopy = Sets.newHashSet(depsFiles.keySet());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[23]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[1]++;


    for (String path : depsPathsCopy) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[1]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[2]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[3]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[24]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((mergeStrategy != InclusionStrategy.WHEN_IN_SRCS) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[5]++;
        jsFiles.remove(path);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[25]++;

      } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[6]++;}
    }
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[4]++;



    for (String path : jsFiles.keySet()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[4]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[5]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[6]++;
}
      // If a generated file appears in both the jsFiles and in depsFiles, then
      // remove it from depsFiles in order to get the full path the generated
      // file.
      depsFiles.remove(path);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[27]++;
    }
  }

  /**
   * Reports if there are any dependency problems with the given dependency
   * information. Reported problems include:
   *     - A namespace being provided more than once
   *     - A namespace being required multiple times from within one file
   *     - A namespace being provided and required in the same file
   *     - A namespace being required that is never provided
   * @param preparsedFileDepedencies Dependency information from existing
   *     deps.js files.
   * @param parsedFileDependencies Dependency information from parsed .js files.
   */
  private void validateDependencies(Iterable<DependencyInfo> preparsedFileDepedencies,
      Iterable<DependencyInfo> parsedFileDependencies) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[28]++;
    // Create a map of namespace -> file providing it.
    // Also report any duplicate provides.
    Map<String, DependencyInfo> providesMap = Maps.newHashMap();
    addToProvideMap(preparsedFileDepedencies, providesMap);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[29]++;
    addToProvideMap(parsedFileDependencies, providesMap);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[30]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[31]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[7]++;


    // For each require in the parsed sources:
    for (DependencyInfo depInfo : parsedFileDependencies) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[7]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[8]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[9]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[32]++;
      List<String> requires = Lists.newArrayList(depInfo.getRequires());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[33]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[10]++;


int CodeCoverConditionCoverageHelper_C4;
      for (int i = 0, l = requires.size();(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < l) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[10]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[11]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[12]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[34]++;
        String namespace = requires.get(i);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[35]++;
int CodeCoverConditionCoverageHelper_C5;
        // Check for multiple requires.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((requires.subList(i + 1, l).contains(namespace)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[7]++;
          reportDuplicateRequire(namespace, depInfo);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[36]++;

        } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[8]++;}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[37]++;
        // Check for missing provides.
        DependencyInfo provider = providesMap.get(namespace);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[38]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((provider == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[9]++;
          reportUndefinedNamespace(namespace, depInfo);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[39]++;

        } else {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[10]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[40]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((provider == depInfo) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[11]++;
          reportSameFile(namespace, depInfo);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[41]++;

        } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[12]++;}
}
      }
    }
  }

  private void reportSameFile(String namespace, DependencyInfo depInfo) {
    errorManager.report(CheckLevel.WARNING,
        JSError.make(depInfo.getName(), -1, -1,
            SAME_FILE_WARNING, namespace));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[42]++;
  }

  private void reportUndefinedNamespace(
      String namespace, DependencyInfo depInfo) {
    errorManager.report(CheckLevel.ERROR,
        JSError.make(depInfo.getName(), -1, -1,
            NEVER_PROVIDED_ERROR, namespace));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[43]++;
  }

  private void reportDuplicateProvide(String namespace, DependencyInfo firstDep,
      DependencyInfo secondDep) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((firstDep == secondDep) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[13]++;
      errorManager.report(CheckLevel.WARNING,
          JSError.make(firstDep.getName(), -1, -1,
              DUPE_PROVIDES_WARNING, namespace));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[45]++;

    } else {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[14]++;
      errorManager.report(CheckLevel.ERROR,
          JSError.make(secondDep.getName(), -1, -1,
              MULTIPLE_PROVIDES_ERROR, namespace, firstDep.getName()));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[46]++;
    }
  }

  private void reportDuplicateRequire(
      String namespace, DependencyInfo depInfo) {
    errorManager.report(CheckLevel.WARNING,
        JSError.make(depInfo.getName(), -1, -1,
            DUPE_REQUIRE_WARNING, namespace));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[47]++;
  }

  private void reportNoDepsInDepsFile(String filePath) {
    errorManager.report(CheckLevel.WARNING,
        JSError.make(filePath, -1, -1, NO_DEPS_WARNING));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[48]++;
  }

  /**
   * Adds the given DependencyInfos to the given providesMap. Also checks for
   * and reports duplicate provides.
   */
  private void addToProvideMap(Iterable<DependencyInfo> depInfos,
      Map<String, DependencyInfo> providesMap) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[49]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[13]++;


    for (DependencyInfo depInfo : depInfos) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[13]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[14]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[15]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[50]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[16]++;


      for (String provide : depInfo.getProvides()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[16]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[17]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[18]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[51]++;
        DependencyInfo prevValue = providesMap.put(provide, depInfo);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[52]++;
int CodeCoverConditionCoverageHelper_C9;
        // Check for duplicate provides.
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((prevValue != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[15]++;
          reportDuplicateProvide(provide, prevValue, depInfo);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[53]++;

        } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[16]++;}
      }
    }
  }

  protected DepsFileParser createDepsFileParser() {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[54]++;
    DepsFileParser depsParser = new DepsFileParser(errorManager);
    depsParser.setShortcutMode(true);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[55]++;
    return depsParser;
  }

  /**
   * Returns whether we should ignore dependency info in the given deps file.
   */
  protected boolean shouldSkipDepsFile(SourceFile file) {
    return false;
  }

  /**
   * Parses all deps.js files in the deps list and creates a map of
   * closure-relative path -> DependencyInfo.
   */
  private Map<String, DependencyInfo> parseDepsFiles() throws IOException {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[56]++;
    DepsFileParser depsParser = createDepsFileParser();
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[57]++;
    Map<String, DependencyInfo> depsFiles = Maps.newHashMap();
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[58]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[19]++;


    for (SourceFile file : deps) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[19]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[20]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[21]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[59]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((shouldSkipDepsFile(file)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[17]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[60]++;
        List<DependencyInfo>
            depInfos = depsParser.parseFileReader(
                file.getName(), file.getCodeReader());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[61]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((depInfos.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[19]++;
          reportNoDepsInDepsFile(file.getName());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[62]++;

        } else {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[20]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[63]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[22]++;


          for (DependencyInfo info : depInfos) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[22]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[23]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[24]++;
}
            depsFiles.put(info.getPathRelativeToClosureBase(), info);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[64]++;
          }
        }

      } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[18]++;}
    }
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[65]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[25]++;



    // If a deps file also appears in srcs, our build tools will move it
    // into srcs.  So we need to scan all the src files for addDependency
    // calls as well.
    for (SourceFile src : srcs) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[25]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[26]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[27]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[66]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 (((new File(src.getName())).exists()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((shouldSkipDepsFile(src)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[21]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[67]++;
        List<DependencyInfo> srcInfos =
            depsParser.parseFileReader(src.getName(), src.getCodeReader());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[68]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[28]++;


        for (DependencyInfo info : srcInfos) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[28]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[29]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[30]++;
}
          depsFiles.put(info.getPathRelativeToClosureBase(), info);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[69]++;
        }

      } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[22]++;}
    }

    return depsFiles;
  }

  /**
   * Parses all source files for dependency information.
   * @param preparsedFiles A set of closure-relative paths.
   *     Files in this set are not parsed if they are encountered in srcs.
   * @return Returns a map of closure-relative paths -> DependencyInfo for the
   *     newly parsed files.
   * @throws IOException Occurs upon an IO error.
   */
  private Map<String, DependencyInfo> parseSources(
      Set<String> preparsedFiles) throws IOException {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[70]++;
    Map<String, DependencyInfo> parsedFiles = Maps.newHashMap();
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[71]++;
    JsFileParser jsParser = new JsFileParser(errorManager);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[72]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[31]++;



    for (SourceFile file : srcs) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[31]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[32]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[33]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[73]++;
      String closureRelativePath =
          PathUtil.makeRelative(
              closurePathAbs, PathUtil.makeAbsolute(file.getName()));
      logger.fine("Closure-relative path: " + closureRelativePath);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[74]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[75]++;
int CodeCoverConditionCoverageHelper_C13;

      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((InclusionStrategy.WHEN_IN_SRCS == mergeStrategy) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((preparsedFiles.contains(closureRelativePath)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[23]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[76]++;
        DependencyInfo depInfo =
            jsParser.parseFile(
                file.getName(), closureRelativePath,
                file.getCode());

        // Kick the source out of memory.
        file.clearCachedSource();
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[77]++;
        parsedFiles.put(closureRelativePath, depInfo);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[78]++;

      } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[24]++;}
    }

    return parsedFiles;
  }

  /**
   * Creates the content to put into the output deps.js file. If mergeDeps is
   * true, then all of the dependency information in the providedDeps will be
   * included in the output.
   * @throws IOException Occurs upon an IO error.
   */
  private void writeDepsContent(Map<String, DependencyInfo> depsFiles,
      Map<String, DependencyInfo> jsFiles, PrintStream out)
      throws IOException {
    // Print all dependencies extracted from srcs.
    writeDepInfos(out, jsFiles.values());
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[79]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[80]++;
int CodeCoverConditionCoverageHelper_C14;

    // Print all dependencies extracted from deps.
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((mergeStrategy == InclusionStrategy.ALWAYS) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[25]++;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[81]++;
      // This multimap is just for splitting DepsInfo objects by
      // it's definition deps.js file
      Multimap<String, DependencyInfo> infosIndex = Multimaps.index(
          depsFiles.values(),
          new Function<DependencyInfo, String>() {
            @Override
            public String apply(DependencyInfo from) {
              return from.getName();
            }
          });
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[82]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[34]++;



      for (String depsPath : infosIndex.keySet()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[34]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[35]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[36]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[83]++;
        String path = formatPathToDepsFile(depsPath);
        out.println("\n// Included from: " + path);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[84]++;
        writeDepInfos(out, infosIndex.get(depsPath));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[85]++;
      }

    } else {
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[26]++;}
  }

  /**
   * Format the deps file path so that it can be included in the output file.
   */
  protected String formatPathToDepsFile(String path) {
    return path;
  }

  /**
   * Writes goog.addDependency() lines for each DependencyInfo in depInfos.
   * @throws IOException Occurs upon an IO error.
   */
  private void writeDepInfos(PrintStream out,
      Collection<DependencyInfo> depInfos
  ) throws IOException {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[86]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[37]++;


    // Print dependencies.
    // Lines look like this:
    // goog.addDependency('../../path/to/file.js', ['goog.Delay'],
    //     ['goog.Disposable', 'goog.Timer']);
    for (DependencyInfo depInfo : depInfos) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[37]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[38]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[39]++;
}
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[87]++;
      Collection<String> provides = depInfo.getProvides();
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[88]++;
      Collection<String> requires = depInfo.getRequires();

      out.print("goog.addDependency('" +
          depInfo.getPathRelativeToClosureBase() + "', ");
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[89]++;
      writeJsArray(out, provides);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[90]++;
      out.print(", ");
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[91]++;
      writeJsArray(out, requires);
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[92]++;
      out.println(");");
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[93]++;
    }
  }

  /**
   * Prints a list of strings formatted as a JavaScript array of string
   * literals.
   */
  private static void writeJsArray(PrintStream out, Collection<String> values) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[94]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((values.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[27]++;
      out.print("[]");
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[95]++;

    } else {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.branches[28]++;
      out.print("['");
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[96]++;
      out.print(Joiner.on("', '").join(values));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[97]++;
      out.print("']");
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[98]++;
    }
  }

  static List<SourceFile> createSourceFilesFromPaths(
      Collection<String> paths) {
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[99]++;
    List<SourceFile> files = Lists.newArrayList();
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[100]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[40]++;


    for (String path : paths) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[40]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[41]--;
  CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.loops[42]++;
}
      files.add(SourceFile.fromFile(path));
CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld.statements[101]++;
    }
    return files;
  }

  static List<SourceFile> createSourceFilesFromPaths(
      String ... paths) {
    return createSourceFilesFromPaths(Arrays.asList(paths));
  }
}

class CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld ());
  }
    public static long[] statements = new long[102];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[16];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.DepsGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1};
    for (int i = 1; i <= 15; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$5ordwhec27k2qv5shh1w1pq5p2ld () {
    super("com.google.javascript.jscomp.deps.DepsGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 101; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.deps.DepsGenerator.java");
      for (int i = 1; i <= 101; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 15; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

