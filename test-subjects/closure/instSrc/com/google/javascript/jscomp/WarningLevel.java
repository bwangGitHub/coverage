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

import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.CompilerOptions;

/**
 * Convert the warnings level to an Options object.
 *
 */
public enum WarningLevel {
  QUIET,

  DEFAULT,

  VERBOSE;

  public void setOptionsForWarningLevel(CompilerOptions options) {
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[1]++;
    switch (this) {
      case QUIET:
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.branches[1]++;
        silenceAllWarnings(options);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[2]++;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[3]++;
        break;
      case DEFAULT:
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.branches[2]++;
        addDefaultWarnings(options);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[4]++;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[5]++;
        break;
      case VERBOSE:
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.branches[3]++;
        addVerboseWarnings(options);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[6]++;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[7]++;
        break;
      default:
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.branches[4]++;
        throw new RuntimeException("Unknown warning level.");
    }
  }

  /**
   * Silence all non-essential warnings.
   */
  private static void silenceAllWarnings(CompilerOptions options) {
    // Just use a ShowByPath warnings guard, so that we don't have
    // to maintain a separate class of warnings guards for silencing warnings.
    options.addWarningsGuard(
        new ShowByPathWarningsGuard(
            "the_longest_path_that_cannot_be_expressed_as_a_string"));
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[8]++;

    // Allow passes that aren't going to report anything to be skipped.

    options.checkRequires = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[9]++;
    options.checkProvides = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[10]++;
    options.checkMissingGetCssNameLevel = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[11]++;
    options.aggressiveVarCheck = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[12]++;
    options.checkTypes = false;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[13]++;
    options.setWarningLevel(DiagnosticGroups.CHECK_TYPES, CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[14]++;
    options.checkUnreachableCode = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[15]++;
    options.checkMissingReturn = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[16]++;
    options.setWarningLevel(DiagnosticGroups.ACCESS_CONTROLS, CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[17]++;
    options.setWarningLevel(DiagnosticGroups.CONST, CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[18]++;
    options.setWarningLevel(DiagnosticGroups.CONSTANT_PROPERTY, CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[19]++;
    options.checkGlobalNamesLevel = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[20]++;
    options.checkSuspiciousCode = false;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[21]++;
    options.checkGlobalThisLevel = CheckLevel.OFF;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[22]++;
    options.setWarningLevel(DiagnosticGroups.GLOBAL_THIS, CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[23]++;
    options.setWarningLevel(DiagnosticGroups.ES5_STRICT, CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[24]++;
    options.checkCaja = false;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[25]++;

    // Allows annotations that are not standard.
    options.setWarningLevel(DiagnosticGroups.NON_STANDARD_JSDOC,
        CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[26]++;
  }

  /**
   * Add the default checking pass to the compilation options.
   * @param options The CompilerOptions object to set the options on.
   */
  private static void addDefaultWarnings(CompilerOptions options) {
    options.checkSuspiciousCode = true;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[27]++;
    options.checkUnreachableCode = CheckLevel.WARNING;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[28]++;
    options.checkControlStructures = true;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[29]++;

    // Allows annotations that are not standard.
    options.setWarningLevel(DiagnosticGroups.NON_STANDARD_JSDOC,
        CheckLevel.OFF);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[30]++;
  }

  /**
   * Add all the check pass that are possibly relevant to a non-googler.
   * @param options The CompilerOptions object to set the options on.
   */
  private static void addVerboseWarnings(CompilerOptions options) {
    addDefaultWarnings(options);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[31]++;

    // checkSuspiciousCode needs to be enabled for CheckGlobalThis to get run.
    options.checkSuspiciousCode = true;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[32]++;
    options.checkGlobalThisLevel = CheckLevel.WARNING;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[33]++;
    options.checkSymbols = true;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[34]++;
    options.checkMissingReturn = CheckLevel.WARNING;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[35]++;

    // checkTypes has the side-effect of asserting that the
    // correct number of arguments are passed to a function.
    // Because the CodingConvention used with the web service does not provide a
    // way for optional arguments to be specified, these warnings may result in
    // false positives.
    options.checkTypes = true;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[36]++;
    options.checkGlobalNamesLevel = CheckLevel.WARNING;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[37]++;
    options.aggressiveVarCheck = CheckLevel.WARNING;
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[38]++;
    options.setWarningLevel(
        DiagnosticGroups.MISSING_PROPERTIES, CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[39]++;
    options.setWarningLevel(
        DiagnosticGroups.DEPRECATED, CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[40]++;
    options.setWarningLevel(
        DiagnosticGroups.ES5_STRICT, CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[41]++;
    options.setWarningLevel(
        DiagnosticGroups.VISIBILITY, CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[42]++;
    options.setWarningLevel(
        DiagnosticGroups.CONST, CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[43]++;
    options.setWarningLevel(
        DiagnosticGroups.CHECK_REGEXP, CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[44]++;

    // Kindly tell the user that they have JsDocs that we don't understand.
    options.setWarningLevel(DiagnosticGroups.NON_STANDARD_JSDOC,
        CheckLevel.WARNING);
CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5.statements[45]++;
  }
}

class CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5 ());
  }
    public static long[] statements = new long[46];
    public static long[] branches = new long[5];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$10sbppx9v3qsdppp2s8n184wbj5 () {
    super("com.google.javascript.jscomp.WarningLevel.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 45; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.WarningLevel.java");
      for (int i = 1; i <= 45; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
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

