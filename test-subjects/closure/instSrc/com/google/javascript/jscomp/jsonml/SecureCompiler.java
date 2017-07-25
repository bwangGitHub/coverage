/*
 * Copyright 2010 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.jsonml;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerInput;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.CompilerOptions.Reach;
import com.google.javascript.jscomp.JSError;
import com.google.javascript.jscomp.JSModule;
import com.google.javascript.jscomp.Result;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.VariableRenamingPolicy;

import java.util.ArrayList;

/**
 * Compilation of JavaScript code which guarantees that all security
 * capabilities are preserved after the process. In particular, it can be
 * safely applied to cajoled source.
 *
 * JS Compiler is used for code analysis and optimization. It runs a series
 * of passes which try to improve the code.
 *
 * For safety reasons, only a subset of local passes, which are provided by
 * JS Compiler, are processed. Currently it includes:
 * - elimination of temporary variables
 *
 * Using SecureCompiler is quite straightforward. A user just needs to create
 * a new instance and call compile() method. Currently the only input which
 * is supported is JsonML.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class SecureCompiler {
  static {
    CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.ping();
  }


  private static final String COMPILATION_UNCOMPLETED_MSG =
      "No compilation has been completed yet.";
  static {
    CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[1]++;
  }

  private static final String COMPILATION_UNSUCCESSFUL_MSG =
      "The last compilation was not successful.";
  static {
    CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[2]++;
  }

  private static final String COMPILATION_ALREADY_COMPLETED_MSG =
      "This instance has already compiled one source.";
  static {
    CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[3]++;
  }

  private Compiler compiler;
  private CompilerOptions options;
  private JsonMLAst sourceAst;

  /** Report from the last compilation */
  private Report report;

  public SecureCompiler() {
    compiler = new Compiler();
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[4]++;
    options = getSecureCompilerOptions();
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[5]++;
  }

  /**
   * Returns compiled source in JsonML format.
   */
  public JsonML getJsonML() {
    Preconditions.checkState(report != null, COMPILATION_UNCOMPLETED_MSG);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[6]++;
    Preconditions.checkState(report.success, COMPILATION_UNSUCCESSFUL_MSG);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[7]++;
    return sourceAst.convertToJsonML();
  }

  /**
   * Returns compiled source as a JavaScript.
   */
  public String getString() {
    Preconditions.checkState(report != null, COMPILATION_UNCOMPLETED_MSG);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[8]++;
    Preconditions.checkState(report.success, COMPILATION_UNSUCCESSFUL_MSG);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[9]++;
    return compiler.toSource();
  }

  /**
   * Returns report from the last compilation.
   */
  public Report getReport() {
    Preconditions.checkState(report != null, COMPILATION_UNCOMPLETED_MSG);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[10]++;
    return report;
  }

  public void compile(JsonML source) {
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((report != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.branches[1]++;
      throw new IllegalStateException(COMPILATION_ALREADY_COMPLETED_MSG);

    } else {
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.branches[2]++;}

    sourceAst = new JsonMLAst(source);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[12]++;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[13]++;

    CompilerInput input = new CompilerInput(
        sourceAst, "[[jsonmlsource]]", false);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[14]++;

    JSModule module = new JSModule("[[jsonmlmodule]]");
    module.add(input);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[15]++;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[16]++;

    Result result = compiler.compileModules(
        ImmutableList.<SourceFile>of(),
        ImmutableList.of(module),
        options);

    report = generateReport(result);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[17]++;
  }

  /**
   * Returns compiler options which are safe for compilation of a cajoled
   * module. The set of options is similar to the one which is used by
   * CompilationLevel in simple mode. The main difference is that variable
   * renaming and closurePass options are turned off.
   */
  private CompilerOptions getSecureCompilerOptions() {
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[18]++;
    CompilerOptions options = new CompilerOptions();

    options.variableRenaming = VariableRenamingPolicy.OFF;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[19]++;
    options.setInlineVariables(Reach.LOCAL_ONLY);
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[20]++;
    options.inlineLocalFunctions = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[21]++;
    options.checkGlobalThisLevel = CheckLevel.OFF;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[22]++;
    options.coalesceVariableNames = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[23]++;
    options.deadAssignmentElimination = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[24]++;
    options.collapseVariableDeclarations = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[25]++;
    options.convertToDottedProperties = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[26]++;
    options.labelRenaming = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[27]++;
    options.removeDeadCode = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[28]++;
    options.optimizeArgumentsArray = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[29]++;
    options.removeUnusedVars = false;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[30]++;
    options.removeUnusedLocalVars = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[31]++;

    return options;
  }

  public void enableFoldConstant() {
    options.foldConstants = true;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[32]++;
  }

  Report generateReport(Result result) {
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[33]++;
int CodeCoverConditionCoverageHelper_C2;
    // a report may be generated only after actual compilation is complete
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((result == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.branches[3]++;
      return null;

    } else {
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.branches[4]++;}
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[34]++;

    ArrayList<JsonMLError> errors = Lists.newArrayList();
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[35]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[1]++;


    for (JSError error : result.errors) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[1]--;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[2]--;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[3]++;
}
      errors.add(JsonMLError.make(error, sourceAst));
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[36]++;
    }
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[37]++;

    ArrayList<JsonMLError> warnings = Lists.newArrayList();
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[38]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[4]++;


    for (JSError warning : result.warnings) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[4]--;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[5]--;
  CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.loops[6]++;
}
      warnings.add(JsonMLError.make(warning, sourceAst));
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[39]++;
    }

    return new Report(
        errors.toArray(new JsonMLError[0]),
        warnings.toArray(new JsonMLError[0]));
  }

  public class Report {
    private final boolean success;
    private final JsonMLError[] errors;
    private final JsonMLError[] warnings;

    private Report(JsonMLError[] errors, JsonMLError[] warnings) {
      this.success = errors.length == 0;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[40]++;
      this.errors = errors;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[41]++;
      this.warnings = warnings;
CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d.statements[42]++;
    }

    public boolean isSuccessful() {
      return success;
    }

    public JsonMLError[] getErrors() {
      return errors;
    }

    public JsonMLError[] getWarnings() {
      return warnings;
    }
  }

}

class CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d ());
  }
    public static long[] statements = new long[43];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.jsonml.SecureCompiler.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
    for (int i = 1; i <= 2; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$1dbekaz09mdh28tj8nif6z6dhab71d () {
    super("com.google.javascript.jscomp.jsonml.SecureCompiler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 42; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.jsonml.SecureCompiler.java");
      for (int i = 1; i <= 42; i++) {
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
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

