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
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.google.javascript.jscomp.CompilerOptions.TweakProcessing;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.TokenStream;
import com.google.protobuf.CodedOutputStream;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nullable;

/**
 * Implementations of AbstractCommandLineRunner translate flags into Java
 * API calls on the Compiler. AbstractCompiler contains common flags and logic
 * to make that happen.
 *
 * This class may be extended and used to create other Java classes
 * that behave the same as running the Compiler from the command line. Example:
 *
 * <pre>
 * class MyCommandLineRunner extends
 *     AbstractCommandLineRunner<MyCompiler, MyOptions> {
 *   MyCommandLineRunner(String[] args) {
 *     super(args);
 *   }
 *
 *   &#064;Override
 *   protected MyOptions createOptions() {
 *     MyOptions options = new MyOptions();
 *     CompilerFlagTranslator.setOptionsFromFlags(options);
 *     addMyCrazyCompilerPassThatOutputsAnExtraFile(options);
 *     return options;
 *   }
 *
 *   &#064;Override
 *   protected MyCompiler createCompiler() {
 *     return new MyCompiler();
 *   }
 *
 *   public static void main(String[] args) {
 *     (new MyCommandLineRunner(args)).run();
 *   }
 * }
 * </pre>
 *
 * @author bolinfest@google.com (Michael Bolin)
 */
abstract class AbstractCommandLineRunner<A extends Compiler,
    B extends CompilerOptions> {
  static {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.ping();
  }

  static final DiagnosticType OUTPUT_SAME_AS_INPUT_ERROR = DiagnosticType.error(
      "JSC_OUTPUT_SAME_AS_INPUT_ERROR",
      "Bad output file (already listed as input file): {0}");
  static {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[1]++;
  }

  private final CommandLineConfig config;

  private Appendable jsOutput;
  private final PrintStream err;
  private A compiler;

  private Charset inputCharset;

  // NOTE(nicksantos): JSCompiler has always used ASCII as the default
  // output charset. This was done to solve legacy problems with
  // bad proxies, etc. We are not sure if these issues are still problems,
  // and changing the encoding would require a big obnoxious migration plan.
  //
  // New outputs should use outputCharset2, which is how I would have
  // designed this if I had a time machine.
  private Charset outputCharset2;
  private String legacyOutputCharset;

  private boolean testMode = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[2]++;
  }
  private Supplier<List<SourceFile>> externsSupplierForTesting = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[3]++;
  }
  private Supplier<List<SourceFile>> inputsSupplierForTesting = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[4]++;
  }
  private Supplier<List<JSModule>> modulesSupplierForTesting = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[5]++;
  }
  private Function<Integer, Boolean> exitCodeReceiverForTesting = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[6]++;
  }
  private Map<String, String> rootRelativePathsMap = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[7]++;
  }

  private Map<String, String> parsedModuleWrappers = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[8]++;
  }

  // Bookkeeping to measure optimal phase orderings.
  private static final int NUM_RUNS_TO_DETERMINE_OPTIMAL_ORDER = 100;
  static {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[9]++;
  }

  private static final String OUTPUT_MARKER = "%output%";
  static {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[10]++;
  }
  private static final String OUTPUT_MARKER_JS_STRING = "%output|jsstring%";
  static {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[11]++;
  }

  private final RunTimeStats runTimeStats = new RunTimeStats();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[12]++;
  }

  AbstractCommandLineRunner() {
    this(System.out, System.err);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[13]++;
  }

  AbstractCommandLineRunner(PrintStream out, PrintStream err) {
    this.config = new CommandLineConfig();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[14]++;
    this.jsOutput = Preconditions.checkNotNull(out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[15]++;
    this.err = Preconditions.checkNotNull(err);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[16]++;
  }

  /**
   * Put the command line runner into test mode. In test mode,
   * all outputs will be blackholed.
   * @param externsSupplier A provider for externs.
   * @param inputsSupplier A provider for source inputs.
   * @param modulesSupplier A provider for modules. Only one of inputsSupplier
   *     and modulesSupplier may be non-null.
   * @param exitCodeReceiver A receiver for the status code that would
   *     have been passed to System.exit in non-test mode.
   */
  @VisibleForTesting
  void enableTestMode(
      Supplier<List<SourceFile>> externsSupplier,
      Supplier<List<SourceFile>> inputsSupplier,
      Supplier<List<JSModule>> modulesSupplier,
      Function<Integer, Boolean> exitCodeReceiver) {
    Preconditions.checkArgument(
        inputsSupplier == null ^ modulesSupplier == null);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[17]++;
    testMode = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[18]++;
    this.externsSupplierForTesting = externsSupplier;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[19]++;
    this.inputsSupplierForTesting = inputsSupplier;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[20]++;
    this.modulesSupplierForTesting = modulesSupplier;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[21]++;
    this.exitCodeReceiverForTesting = exitCodeReceiver;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[22]++;
  }

  /**
   * Returns whether we're in test mode.
   */
  protected boolean isInTestMode() {
    return testMode;
  }

  /**
   * Get the command line config, so that it can be initialized.
   */
  protected CommandLineConfig getCommandLineConfig() {
    return config;
  }

  /**
   * Returns the instance of the Compiler to use when {@link #run()} is
   * called.
   */
  protected abstract A createCompiler();

  /**
   * Returns the instance of the Options to use when {@link #run()} is called.
   * createCompiler() is called before createOptions(), so getCompiler()
   * will not return null when createOptions() is called.
   */
  protected abstract B createOptions();

  /**
   * The warning classes that are available from the command-line.
   */
  protected DiagnosticGroups getDiagnosticGroups() {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[23]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((compiler == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[1]++;
      return new DiagnosticGroups();

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[2]++;}
    return compiler.getDiagnosticGroups();
  }

  /**
   * A helper function for creating the dependency options object.
   */
  static DependencyOptions createDependencyOptions(
      boolean manageClosureDependencies,
      boolean onlyClosureDependencies,
      boolean processCommonJSModules,
      List<String> closureEntryPoints)
      throws FlagUsageException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[24]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((onlyClosureDependencies) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[3]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((closureEntryPoints.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[5]++;
        throw new FlagUsageException("When only_closure_dependencies is "
          + "on, you must specify at least one closure_entry_point");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[6]++;}

      return new DependencyOptions()
          .setDependencyPruning(true)
          .setDependencySorting(true)
          .setMoocherDropping(true)
          .setEntryPoints(closureEntryPoints);

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[4]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[26]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((processCommonJSModules) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[7]++;
      return new DependencyOptions()
        .setDependencyPruning(false)
        .setDependencySorting(true)
        .setMoocherDropping(false)
        .setEntryPoints(closureEntryPoints);

    }
    else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[8]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[27]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((manageClosureDependencies) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((closureEntryPoints.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[9]++;
      return new DependencyOptions()
          .setDependencyPruning(true)
          .setDependencySorting(true)
          .setMoocherDropping(false)
          .setEntryPoints(closureEntryPoints);

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[10]++;}
}
}
    return null;
  }

  /**
   * Sets options based on the configurations set flags API.
   * Called during the run() run() method.
   * If you want to ignore the flags API, or interpret flags your own way,
   * then you should override this method.
   */
  protected void setRunOptions(CompilerOptions options)
      throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[28]++;
    DiagnosticGroups diagnosticGroups = getDiagnosticGroups();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((config.warningGuards != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[11]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[30]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[1]++;


      for (WarningGuardSpec.Entry entry : config.warningGuards.entries) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[1]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[2]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[3]++;
}
        diagnosticGroups.setWarningLevel(options, entry.groupName, entry.level);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[31]++;
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[12]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((config.warningsWhitelistFile.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[13]++;
      options.addWarningsGuard(
          WhitelistWarningsGuard.fromFile(
              new File(config.warningsWhitelistFile)));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[33]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[14]++;}

    createDefineOrTweakReplacements(config.define, options, false);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[34]++;

    options.setTweakProcessing(config.tweakProcessing);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[35]++;
    createDefineOrTweakReplacements(config.tweak, options, true);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[36]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[37]++;

    DependencyOptions depOptions = createDependencyOptions(
        config.manageClosureDependencies,
        config.onlyClosureDependencies,
        config.processCommonJSModules,
        config.closureEntryPoints);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((depOptions != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[15]++;
      options.setDependencyOptions(depOptions);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[39]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[16]++;}

    options.devMode = config.jscompDevMode;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[40]++;
    options.setCodingConvention(config.codingConvention);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[41]++;
    options.setSummaryDetailLevel(config.summaryDetailLevel);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[42]++;
    options.setTrustedStrings(true);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[43]++;

    legacyOutputCharset = options.outputCharset = getLegacyOutputCharset();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[44]++;
    outputCharset2 = getOutputCharset2();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[45]++;
    inputCharset = getInputCharset();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[46]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[47]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((config.jsOutputFile.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[17]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((config.skipNormalOutputs) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[19]++;
        throw new FlagUsageException("skip_normal_outputs and js_output_file"
            + " cannot be used together.");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[20]++;}

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[18]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;

    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((config.skipNormalOutputs) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((config.printAst) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[21]++;
      throw new FlagUsageException("skip_normal_outputs and print_ast cannot"
          + " be used together.");

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[22]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((config.skipNormalOutputs) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((config.printTree) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[23]++;
      throw new FlagUsageException("skip_normal_outputs and print_tree cannot"
          + " be used together.");

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[24]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[51]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((config.createSourceMap.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[25]++;
      options.sourceMapOutputPath = config.createSourceMap;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[52]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[26]++;}
    options.sourceMapDetailLevel = config.sourceMapDetailLevel;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[53]++;
    options.sourceMapFormat = config.sourceMapFormat;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[54]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[55]++;
int CodeCoverConditionCoverageHelper_C14;

    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((config.variableMapInputFile.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[27]++;
      options.inputVariableMap =
          VariableMap.load(config.variableMapInputFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[56]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[28]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[57]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((config.propertyMapInputFile.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[29]++;
      options.inputPropertyMap =
          VariableMap.load(config.propertyMapInputFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[58]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[30]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[59]++;
int CodeCoverConditionCoverageHelper_C16;

    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((config.languageIn.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[31]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[60]++;
      CompilerOptions.LanguageMode languageMode =
          CompilerOptions.LanguageMode.fromString(config.languageIn);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[61]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((languageMode != null) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[33]++;
        options.setLanguageIn(languageMode);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[62]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[34]++;
        throw new FlagUsageException("Unknown language `" + config.languageIn +
                                     "' specified.");
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[32]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[63]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((config.outputManifests.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[35]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[64]++;
      Set<String> uniqueNames = Sets.newHashSet();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[65]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[4]++;


      for (String filename : config.outputManifests) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[4]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[5]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[6]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[66]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((uniqueNames.add(filename)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[37]++;
          throw new FlagUsageException("output_manifest flags specify " +
              "duplicate file names: " + filename);

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[38]++;}
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[36]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[67]++;
int CodeCoverConditionCoverageHelper_C20;

    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((config.outputBundles.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[39]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[68]++;
      Set<String> uniqueNames = Sets.newHashSet();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[69]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[7]++;


      for (String filename : config.outputBundles) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[7]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[8]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[9]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[70]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((uniqueNames.add(filename)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[41]++;
          throw new FlagUsageException("output_bundle flags specify " +
              "duplicate file names: " + filename);

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[42]++;}
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[40]++;}

    options.acceptConstKeyword = config.acceptConstKeyword;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[71]++;
    options.transformAMDToCJSModules = config.transformAMDToCJSModules;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[72]++;
    options.processCommonJSModules = config.processCommonJSModules;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[73]++;
    options.commonJSModulePathPrefix = config.commonJSModulePathPrefix;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[74]++;
    options.angularPass = config.angularPass;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[75]++;
  }

  final protected A getCompiler() {
    return compiler;
  }

  /**
   * Runs the Compiler and calls System.exit() with the exit status of the
   * compiler.
   */
  final public void run() {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[76]++;
    int result = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[77]++;
    int runs = 1;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[78]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((config.computePhaseOrdering) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[43]++;
      runs = NUM_RUNS_TO_DETERMINE_OPTIMAL_ORDER;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[79]++;
      PhaseOptimizer.randomizeLoops();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[80]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[44]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[81]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[82]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[10]++;


int CodeCoverConditionCoverageHelper_C23;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((i < runs) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((result == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[10]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[11]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[12]++;
}
        runTimeStats.recordStartRun();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[83]++;
        result = doRun();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[84]++;
        runTimeStats.recordEndRun();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[85]++;
      }
    } catch (AbstractCommandLineRunner.FlagUsageException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[46]++;
      System.err.println(e.getMessage());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[86]++;
      result = -1;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[87]++;
    } catch (Throwable t) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[47]++;
      t.printStackTrace();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[88]++;
      result = -2;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[89]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[45]++;
}
  }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[90]++;
int CodeCoverConditionCoverageHelper_C24;

    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((config.computePhaseOrdering) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[48]++;
      runTimeStats.outputBestPhaseOrdering();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[91]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[49]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[92]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

    try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[93]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((jsOutput instanceof Closeable) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[51]++;
        ((Closeable) jsOutput).close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[94]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[52]++;}
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[53]++;
      throw Throwables.propagate(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[50]++;
}
  }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[95]++;
int CodeCoverConditionCoverageHelper_C26;

    if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((testMode) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[54]++;
      exitCodeReceiverForTesting.apply(result);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[96]++;

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[55]++;
      System.exit(result);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[97]++;
    }
  }

  /**
   * Returns the PrintStream for writing errors associated with this
   * AbstractCommandLineRunner.
   */
  protected PrintStream getErrorPrintStream() {
    return err;
  }

  /**
   * An exception thrown when command-line flags are used incorrectly.
   */
  public static class FlagUsageException extends Exception {
    private static final long serialVersionUID = 1L;

    public FlagUsageException(String message) {
      super(message);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[98]++;
    }
  }

  /**
   * Creates inputs from a list of files.
   *
   * Can be overridden by subclasses who want to pull files from different
   * places.
   *
   * @param files A list of filenames
   * @param allowStdIn Whether '-' is allowed appear as a filename to represent
   *        stdin. If true, '-' is only allowed to appear once.
   * @return An array of inputs
   */
  protected List<SourceFile> createInputs(List<String> files,
      boolean allowStdIn) throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[99]++;
    List<SourceFile> inputs = new ArrayList<SourceFile>(files.size());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[100]++;
    boolean usingStdin = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[101]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[13]++;


    for (String filename : files) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[13]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[14]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[15]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[102]++;
int CodeCoverConditionCoverageHelper_C27;
      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 (("-".equals(filename)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[56]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[103]++;
        SourceFile newFile = SourceFile.fromFile(filename, inputCharset);
        inputs.add(newFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[104]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[57]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[105]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((allowStdIn) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[58]++;
          throw new FlagUsageException("Can't specify stdin.");

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[59]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[106]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((usingStdin) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[60]++;
          throw new FlagUsageException("Can't specify stdin twice.");

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[61]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[107]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((config.outputManifests.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[62]++;
          throw new FlagUsageException("Manifest files cannot be generated " +
              "when the input is from stdin.");

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[63]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[108]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((config.outputBundles.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[64]++;
          throw new FlagUsageException("Bundle files cannot be generated " +
              "when the input is from stdin.");

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[65]++;}
        inputs.add(SourceFile.fromInputStream("stdin", System.in));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[109]++;
        usingStdin = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[110]++;
      }
    }
    return inputs;
  }

  /**
   * Creates JS source code inputs from a list of files.
   */
  private List<SourceFile> createSourceInputs(List<String> files)
      throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[111]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((isInTestMode()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[66]++;
      return inputsSupplierForTesting.get();

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[67]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[112]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((files.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[68]++;
      files = Collections.singletonList("-");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[113]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[69]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[114]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
    try {
CodeCoverTryBranchHelper_Try3 = true;
      return createInputs(files, true);
    } catch (FlagUsageException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[71]++;
      throw new FlagUsageException("Bad --js flag. " + e.getMessage());
    } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[70]++;
}
  }
  }

  /**
   * Creates JS extern inputs from a list of files.
   */
  private List<SourceFile> createExternInputs(List<String> files)
      throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[115]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((files.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[72]++;
      return ImmutableList.of(SourceFile.fromCode("/dev/null", ""));

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[73]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[116]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
    try {
CodeCoverTryBranchHelper_Try4 = true;
      return createInputs(files, false);
    } catch (FlagUsageException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[75]++;
      throw new FlagUsageException("Bad --externs flag. " + e.getMessage());
    } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[74]++;
}
  }
  }

  /**
   * Creates module objects from a list of module specifications.
   *
   * @param specs A list of module specifications, not null or empty. The spec
   *        format is: <code>name:num-js-files[:[dep,...][:]]</code>. Module
   *        names must not contain the ':' character.
   * @param jsFiles A list of JS file paths, not null
   * @return An array of module objects
   */
  List<JSModule> createJsModules(
      List<String> specs, List<String> jsFiles)
      throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[117]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((isInTestMode()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[76]++;
      return modulesSupplierForTesting.get();

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[77]++;}

    Preconditions.checkState(specs != null);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[118]++;
    Preconditions.checkState(!specs.isEmpty());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[119]++;
    Preconditions.checkState(jsFiles != null);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[120]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[121]++;

    final int totalNumJsFiles = jsFiles.size();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[122]++;
    int nextJsFileIndex = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[123]++;

    Map<String, JSModule> modulesByName = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[124]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[16]++;


    for (String spec : specs) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[16]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[17]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[18]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[125]++;

      // Format is "<name>:<num-js-files>[:[<dep>,...][:]]".
      String[] parts = spec.split(":");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[126]++;
int CodeCoverConditionCoverageHelper_C36;
      if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((parts.length < 2) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((parts.length > 4) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[78]++;
        throw new FlagUsageException("Expected 2-4 colon-delimited parts in "
            + "module spec: " + spec);

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[79]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[127]++;

      // Parse module name.
      String name = parts[0];
      checkModuleName(name);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[128]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[129]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((modulesByName.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[80]++;
              throw new FlagUsageException("Duplicate module name: " + name);

          } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[81]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[130]++;
      JSModule module = new JSModule(name);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[131]++;

      // Parse module inputs.
      int numJsFiles = -1;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[132]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
      try {
CodeCoverTryBranchHelper_Try5 = true;
        numJsFiles = Integer.parseInt(parts[1]);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[133]++;
      } catch (NumberFormatException ignored) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[83]++;
        numJsFiles = -1;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[134]++;
      } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[82]++;
}
  }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[135]++;
int CodeCoverConditionCoverageHelper_C38;

      // We will allow modules of zero input.
      if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((numJsFiles < 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[84]++;
        throw new FlagUsageException("Invalid JS file count '" + parts[1]
            + "' for module: " + name);

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[85]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[136]++;
int CodeCoverConditionCoverageHelper_C39;
      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((nextJsFileIndex + numJsFiles > totalNumJsFiles) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[86]++;
        throw new FlagUsageException("Not enough JS files specified. Expected "
            + (nextJsFileIndex + numJsFiles - totalNumJsFiles)
            + " more in module:" + name);

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[87]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[137]++;
      List<String> moduleJsFiles =
          jsFiles.subList(nextJsFileIndex, nextJsFileIndex + numJsFiles);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[138]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[19]++;


      for (SourceFile input : createInputs(moduleJsFiles, false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[19]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[20]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[21]++;
}
        module.add(input);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[139]++;
      }
      nextJsFileIndex += numJsFiles;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[140]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[141]++;
int CodeCoverConditionCoverageHelper_C40;

      if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((parts.length > 2) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[88]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[142]++;
        // Parse module dependencies.
        String depList = parts[2];
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[143]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((depList.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[90]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[144]++;
          String[] deps = depList.split(",");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[145]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[22]++;


          for (String dep : deps) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[22]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[23]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[24]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[146]++;
            JSModule other = modulesByName.get(dep);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[147]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((other == null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[92]++;
              throw new FlagUsageException("Module '" + name
                  + "' depends on unknown module '" + dep
                  + "'. Be sure to list modules in dependency order.");

            } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[93]++;}
            module.addDependency(other);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[148]++;
          }

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[91]++;}

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[89]++;}

      modulesByName.put(name, module);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[149]++;
    }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[150]++;
int CodeCoverConditionCoverageHelper_C43;

    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((nextJsFileIndex < totalNumJsFiles) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[94]++;
      throw new FlagUsageException("Too many JS files specified. Expected "
          + nextJsFileIndex + " but found " + totalNumJsFiles);

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[95]++;}

    return Lists.newArrayList(modulesByName.values());
  }

  /**
   * Validates the module name. Can be overridden by subclasses.
   * @param name The module name
   * @throws FlagUsageException if the validation fails
   */
  protected void checkModuleName(String name)
      throws FlagUsageException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[151]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(name)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[96]++;
      throw new FlagUsageException("Invalid module name: '" + name + "'");

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[97]++;}
  }

  /**
   * Parses module wrapper specifications.
   *
   * @param specs A list of module wrapper specifications, not null. The spec
   *        format is: <code>name:wrapper</code>. Wrappers.
   * @param modules The JS modules whose wrappers are specified
   * @return A map from module name to module wrapper. Modules with no wrapper
   *         will have the empty string as their value in this map.
   */
  static Map<String, String> parseModuleWrappers(List<String> specs,
      List<JSModule> modules) throws FlagUsageException {
    Preconditions.checkState(specs != null);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[152]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[153]++;

    Map<String, String> wrappers =
        Maps.newHashMapWithExpectedSize(modules.size());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[154]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[25]++;



    // Prepopulate the map with module names.
    for (JSModule m : modules) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[25]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[26]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[27]++;
}
      wrappers.put(m.getName(), "");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[155]++;
    }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[156]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[28]++;



    for (String spec : specs) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[28]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[29]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[30]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[157]++;

      // Format is "<name>:<wrapper>".
      int pos = spec.indexOf(':');
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[158]++;
int CodeCoverConditionCoverageHelper_C45;
      if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((pos == -1) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[98]++;
        throw new FlagUsageException("Expected module wrapper to have "
            + "<name>:<wrapper> format: " + spec);

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[99]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[159]++;

      // Parse module name.
      String name = spec.substring(0, pos);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[160]++;
int CodeCoverConditionCoverageHelper_C46;
      if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((wrappers.containsKey(name)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[100]++;
        throw new FlagUsageException("Unknown module: '" + name + "'");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[101]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[161]++;
      String wrapper = spec.substring(pos + 1);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[162]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((wrapper.contains("%s")) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[102]++;
        throw new FlagUsageException("No %s placeholder in module wrapper: '"
            + wrapper + "'");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[103]++;}
      wrappers.put(name, wrapper);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[163]++;
    }
    return wrappers;
  }

  private String getModuleOutputFileName(JSModule m) {
    return config.moduleOutputPathPrefix + m.getName() + ".js";
  }

  @VisibleForTesting
  void writeModuleOutput(Appendable out, JSModule m)
      throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[164]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((parsedModuleWrappers == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[104]++;
      parsedModuleWrappers = parseModuleWrappers(
          config.moduleWrapper,
          Lists.newArrayList(
              compiler.getDegenerateModuleGraph().getAllModules()));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[165]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[105]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[166]++;

    String fileName = getModuleOutputFileName(m);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[167]++;
    String baseName = new File(fileName).getName();
    writeOutput(out, compiler, compiler.toSource(m),
        parsedModuleWrappers.get(m.getName()).replace("%basename%", baseName),
        "%s", null);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[168]++;
  }

  /**
   * Writes code to an output stream, optionally wrapping it in an arbitrary
   * wrapper that contains a placeholder where the code should be inserted.
   */
  static void writeOutput(Appendable out, Compiler compiler, String code,
      String wrapper, String codePlaceholder,
      @Nullable Function<String, String> escaper)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[169]++;
    int pos = wrapper.indexOf(codePlaceholder);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[170]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((pos != -1) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[106]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[171]++;
      String prefix = "";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[172]++;
int CodeCoverConditionCoverageHelper_C50;

      if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((pos > 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[108]++;
        prefix = wrapper.substring(0, pos);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[173]++;
        out.append(prefix);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[174]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[109]++;}

      out.append(escaper == null ? code : escaper.apply(code));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[175]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[176]++;

      int suffixStart = pos + codePlaceholder.length();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[177]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((suffixStart != wrapper.length()) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[110]++;
        // Something after placeholder?
        out.append(wrapper.substring(suffixStart));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[178]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[111]++;}
      // Make sure we always end output with a line feed.
      out.append('\n');
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[179]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[180]++;
int CodeCoverConditionCoverageHelper_C52;

      // If we have a source map, adjust its offsets to match
      // the code WITHIN the wrapper.
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((compiler != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((compiler.getSourceMap() != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[112]++;
        compiler.getSourceMap().setWrapperPrefix(prefix);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[181]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[113]++;}


    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[107]++;
      out.append(code);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[182]++;
      out.append('\n');
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[183]++;
    }
  }

  /**
   * Creates any directories necessary to write a file that will have a given
   * path prefix.
   */
  private static void maybeCreateDirsForPath(String pathPrefix) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[184]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((pathPrefix.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[114]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[185]++;
      String dirName =
          pathPrefix.charAt(pathPrefix.length() - 1) == File.separatorChar
              ? pathPrefix.substring(0, pathPrefix.length() - 1) : new File(
                  pathPrefix).getParent();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[186]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((dirName != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[116]++;
        new File(dirName).mkdirs();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[187]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[117]++;}

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[115]++;}
  }

  /**
   * Parses command-line arguments and runs the compiler.
   *
   * @return system exit status
   */
  protected int doRun() throws FlagUsageException, IOException {
    Compiler.setLoggingLevel(Level.parse(config.loggingLevel));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[188]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[189]++;

    List<SourceFile> externs = createExterns();

    compiler = createCompiler();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[190]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[191]++;
    B options = createOptions();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[192]++;

    List<JSModule> modules = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[193]++;
    Result result = null;

    setRunOptions(options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[194]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[195]++;

    boolean writeOutputToFile = !config.jsOutputFile.isEmpty();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[196]++;
    List<String> outputFileNames = Lists.newArrayList();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[197]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((writeOutputToFile) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[118]++;
      outputFileNames.add(config.jsOutputFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[198]++;
      jsOutput = fileNameToLegacyOutputWriter(config.jsOutputFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[199]++;

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[119]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[200]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((jsOutput instanceof OutputStream) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[120]++;
      jsOutput = streamToLegacyOutputWriter((OutputStream) jsOutput);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[201]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[121]++;}
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[202]++;

    List<String> jsFiles = config.js;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[203]++;
    List<String> moduleSpecs = config.module;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[204]++;

    boolean createCommonJsModules = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[205]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((options.processCommonJSModules) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[122]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[206]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((moduleSpecs.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 (("auto".equals(moduleSpecs.get(0))) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[124]++;
        createCommonJsModules = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[207]++;
        moduleSpecs.remove(0);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[208]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[125]++;}

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[123]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[209]++;
int CodeCoverConditionCoverageHelper_C59;
    if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((moduleSpecs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[126]++;
      modules = createJsModules(moduleSpecs, jsFiles);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[210]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[211]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[31]++;


      for (JSModule m : modules) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[31]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[32]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[33]++;
}
        outputFileNames.add(getModuleOutputFileName(m));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[212]++;
      }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[213]++;
int CodeCoverConditionCoverageHelper_C60;

      if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((config.skipNormalOutputs) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[128]++;
        compiler.initModules(externs, modules, options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[214]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[129]++;
        result = compiler.compileModules(externs, modules, options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[215]++;
      }

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[127]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[216]++;
      List<SourceFile> inputs = createSourceInputs(jsFiles);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[217]++;
int CodeCoverConditionCoverageHelper_C61;
      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((config.skipNormalOutputs) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[130]++;
        compiler.init(externs, inputs, options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[218]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[131]++;
        result = compiler.compile(externs, inputs, options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[219]++;
      }
    }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[220]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((createCommonJsModules) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[132]++;
      // For CommonJS modules construct modules from actual inputs.
      modules = Lists.newArrayList(compiler.getDegenerateModuleGraph()
          .getAllModules());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[221]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[222]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[34]++;


      for (JSModule m : modules) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[34]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[35]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[36]++;
}
        outputFileNames.add(getModuleOutputFileName(m));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[223]++;
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[133]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[224]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[37]++;



    for (String outputFileName : outputFileNames) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[37]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[38]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[39]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[225]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((compiler.getSourceFileByName(outputFileName) != null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[134]++;
        compiler.report(
            JSError.make(OUTPUT_SAME_AS_INPUT_ERROR, outputFileName));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[226]++;
        return 1;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[135]++;}
    }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[227]++;

    int errCode = processResults(result, modules, options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[228]++;
int CodeCoverConditionCoverageHelper_C64;
    // Flush the output if we are writing to a file.
    // We can't close yet, because we may need to write phase ordering
    // info to it later.
    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((jsOutput instanceof Flushable) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[136]++;
      ((Flushable) jsOutput).flush();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[229]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[137]++;}
    return errCode;
  }

  /**
   * Processes the results of the compile job, and returns an error code.
   */
  int processResults(Result result, List<JSModule> modules, B options)
       throws FlagUsageException, IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[230]++;
int CodeCoverConditionCoverageHelper_C65;
    if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((config.computePhaseOrdering) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[138]++;
      return 0;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[139]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[231]++;
int CodeCoverConditionCoverageHelper_C66;

    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((config.printPassGraph) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[140]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[232]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((compiler.getRoot() == null) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[142]++;
        return 1;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[143]++;
        jsOutput.append(
            DotFormatter.toDot(compiler.getPassConfig().getPassGraph()));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[233]++;
        jsOutput.append('\n');
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[234]++;
        return 0;
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[141]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[235]++;
int CodeCoverConditionCoverageHelper_C68;

    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((config.printAst) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[144]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[236]++;
int CodeCoverConditionCoverageHelper_C69;
      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((compiler.getRoot() == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[146]++;
        return 1;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[147]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[237]++;
        ControlFlowGraph<Node> cfg = compiler.computeCFG();
        DotFormatter.appendDot(
            compiler.getRoot().getLastChild(), cfg, jsOutput);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[238]++;
        jsOutput.append('\n');
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[239]++;
        return 0;
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[145]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[240]++;
int CodeCoverConditionCoverageHelper_C70;

    if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((config.printTree) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[148]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[241]++;
int CodeCoverConditionCoverageHelper_C71;
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((compiler.getRoot() == null) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[150]++;
        jsOutput.append("Code contains errors; no tree was generated.\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[242]++;
        return 1;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[151]++;
        compiler.getRoot().appendStringTree(jsOutput);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[243]++;
        jsOutput.append("\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[244]++;
        return 0;
      }

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[149]++;}

    rootRelativePathsMap = constructRootRelativePathsMap();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[245]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[246]++;
int CodeCoverConditionCoverageHelper_C72;

    if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((config.skipNormalOutputs) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[152]++;
      // Output the manifest and bundle files if requested.
      outputManifest();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[247]++;
      outputBundle();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[248]++;
      outputModuleGraphJson();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[249]++;
      return 0;

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[153]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[250]++;
int CodeCoverConditionCoverageHelper_C73; if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((result.success) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[154]++;
      outputModuleGraphJson();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[251]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[252]++;
int CodeCoverConditionCoverageHelper_C74;
      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((modules == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[156]++;
        outputSingleBinary();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[253]++;

        // Output the source map if requested.
        outputSourceMap(options, config.jsOutputFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[254]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[157]++;
        outputModuleBinaryAndSourceMaps(modules, options);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[255]++;
      }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[256]++;
int CodeCoverConditionCoverageHelper_C75;

      // Output the externs if required.
      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((options.externExportsPath != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[158]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[257]++;
        Writer eeOut =
            openExternExportsStream(options, config.jsOutputFile);
        eeOut.append(result.externExport);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[258]++;
        eeOut.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[259]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[159]++;}

      // Output the variable and property name maps if requested.
      outputNameMaps();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[260]++;

      // Output the manifest and bundle files if requested.
      outputManifest();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[261]++;
      outputBundle();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[262]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[155]++;}
}

    // return 0 if no errors, the error count otherwise
    return Math.min(result.errors.length, 0x7f);
  }

  Function<String, String> getJavascriptEscaper() {
    throw new UnsupportedOperationException(
        "SourceCodeEscapers is not in the standard release of Guava yet :(");
  }

  void outputSingleBinary() throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[263]++;
    Function<String, String> escaper = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[264]++;
    String marker = OUTPUT_MARKER;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[265]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((config.outputWrapper.contains(OUTPUT_MARKER_JS_STRING)) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[160]++;
      marker = OUTPUT_MARKER_JS_STRING;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[266]++;
      escaper = getJavascriptEscaper();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[267]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[161]++;}

    writeOutput(
        jsOutput, compiler, compiler.toSource(), config.outputWrapper,
        marker, escaper);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[268]++;
  }

  private void outputModuleBinaryAndSourceMaps(
      List<JSModule> modules, B options)
      throws FlagUsageException, IOException {
    parsedModuleWrappers = parseModuleWrappers(
        config.moduleWrapper, modules);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[269]++;
    maybeCreateDirsForPath(config.moduleOutputPathPrefix);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[270]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[271]++;

    // If the source map path is in fact a pattern for each
    // module, create a stream per-module. Otherwise, create
    // a single source map.
    Writer mapOut = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[272]++;
int CodeCoverConditionCoverageHelper_C77;

    if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((shouldGenerateMapPerModule(options)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[162]++;
      mapOut = fileNameToOutputWriter2(expandSourceMapPath(options, null));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[273]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[163]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[274]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[40]++;



    for (JSModule m : modules) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[40]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[41]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[42]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[275]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((shouldGenerateMapPerModule(options)) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[164]++;
        mapOut = fileNameToOutputWriter2(expandSourceMapPath(options, m));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[276]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[165]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[277]++;

      Writer writer =
          fileNameToLegacyOutputWriter(getModuleOutputFileName(m));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[278]++;
int CodeCoverConditionCoverageHelper_C79;

      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((options.sourceMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[166]++;
        compiler.getSourceMap().reset();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[279]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[167]++;}

      writeModuleOutput(writer, m);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[280]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[281]++;
int CodeCoverConditionCoverageHelper_C80;

      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((options.sourceMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[168]++;
        compiler.getSourceMap().appendTo(mapOut, m.getName());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[282]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[169]++;}

      writer.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[283]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[284]++;
int CodeCoverConditionCoverageHelper_C81;

      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((shouldGenerateMapPerModule(options)) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((mapOut != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[170]++;
        mapOut.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[285]++;
        mapOut = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[286]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[171]++;}
    }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[287]++;
int CodeCoverConditionCoverageHelper_C82;

    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((mapOut != null) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[172]++;
      mapOut.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[288]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[173]++;}
  }

  /**
   * Query the flag for the input charset, and return a Charset object
   * representing the selection.
   *
   * @return Charset to use when reading inputs
   * @throws FlagUsageException if flag is not a valid Charset name.
   */
  private Charset getInputCharset() throws FlagUsageException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[289]++;
int CodeCoverConditionCoverageHelper_C83;
    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((config.charset.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[174]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[290]++;
int CodeCoverConditionCoverageHelper_C84;
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((Charset.isSupported(config.charset)) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[176]++;
        throw new FlagUsageException(config.charset +
            " is not a valid charset name.");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[177]++;}
      return Charset.forName(config.charset);

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[175]++;}
    return Charsets.UTF_8;
  }

  /**
   * Query the flag for the output charset.
   *
   * Let the outputCharset be the same as the input charset... except if
   * we're reading in UTF-8 by default.  By tradition, we've always
   * output ASCII to avoid various hiccups with different browsers,
   * proxies and firewalls.
   *
   * @return Name of the charset to use when writing outputs. Guaranteed to
   *    be a supported charset.
   * @throws FlagUsageException if flag is not a valid Charset name.
   */
  private String getLegacyOutputCharset() throws FlagUsageException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[291]++;
int CodeCoverConditionCoverageHelper_C85;
    if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((config.charset.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[178]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[292]++;
int CodeCoverConditionCoverageHelper_C86;
      if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((Charset.isSupported(config.charset)) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[180]++;
        throw new FlagUsageException(config.charset +
            " is not a valid charset name.");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[181]++;}
      return config.charset;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[179]++;}
    return "US-ASCII";
  }

  /**
   * Query the flag for the output charset. Defaults to UTF-8.
   * @throws FlagUsageException if flag is not a valid Charset name.
   */
  private Charset getOutputCharset2() throws FlagUsageException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[293]++;
int CodeCoverConditionCoverageHelper_C87;
    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((config.charset.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[182]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[294]++;
int CodeCoverConditionCoverageHelper_C88;
      if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((Charset.isSupported(config.charset)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[184]++;
        throw new FlagUsageException(config.charset +
            " is not a valid charset name.");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[185]++;}
      return Charset.forName(config.charset);

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[183]++;}
    return Charsets.UTF_8;
  }

  protected List<SourceFile> createExterns() throws FlagUsageException,
      IOException {
    return isInTestMode() ? externsSupplierForTesting.get() :
        createExternInputs(config.externs);
  }

  /**
   * Returns true if and only if a source map file should be generated for each
   * module, as opposed to one unified map. This is specified by having the
   * source map pattern include the %outname% variable.
   */
  private boolean shouldGenerateMapPerModule(B options) {
    return options.sourceMapOutputPath != null
        && options.sourceMapOutputPath.contains("%outname%");
  }

  /**
   * Returns a stream for outputting the generated externs file.
   *
   * @param options The options to the Compiler.
   * @param path The path of the generated JS source file.
   *
   * @return The stream or null if no extern-ed exports are being generated.
   */
  private Writer openExternExportsStream(B options,
      String path) throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[295]++;
int CodeCoverConditionCoverageHelper_C89;
    if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((options.externExportsPath == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[186]++;
      return null;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[187]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[296]++;

    String exPath = options.externExportsPath;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[297]++;
int CodeCoverConditionCoverageHelper_C90;

    if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((exPath.contains(File.separator)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[188]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[298]++;
      File outputFile = new File(path);
      exPath = outputFile.getParent() + File.separatorChar + exPath;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[299]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[189]++;}

    return fileNameToOutputWriter2(exPath);
  }

  /**
   * Expand a file path specified on the command-line.
   *
   * Most file paths on the command-line allow an %outname% placeholder.
   * The placeholder will expand to a different value depending on
   * the current output mode. There are three scenarios:
   *
   * 1) Single JS output, single extra output: sub in jsOutputPath.
   * 2) Multiple JS output, single extra output: sub in the base module name.
   * 3) Multiple JS output, multiple extra output: sub in the module output
   *    file.
   *
   * Passing a JSModule to this function automatically triggers case #3.
   * Otherwise, we'll use strategy #1 or #2 based on the current output mode.
   */
  private String expandCommandLinePath(
      String path, JSModule forModule) {
    String sub;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[300]++;
int CodeCoverConditionCoverageHelper_C91;
    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((forModule != null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[190]++;
      sub = config.moduleOutputPathPrefix + forModule.getName() + ".js";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[301]++;

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[191]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[302]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((config.module.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[192]++;
      sub = config.moduleOutputPathPrefix;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[303]++;

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[193]++;
      sub = config.jsOutputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[304]++;
    }
}
    return path.replace("%outname%", sub);
  }

  /** Expansion function for source map. */
  @VisibleForTesting
  String expandSourceMapPath(B options, JSModule forModule) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[305]++;
int CodeCoverConditionCoverageHelper_C93;
    if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((Strings.isNullOrEmpty(options.sourceMapOutputPath)) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[194]++;
      return null;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[195]++;}
    return expandCommandLinePath(options.sourceMapOutputPath, forModule);
  }

  /**
   * Converts a file name into a Writer taking in account the output charset.
   * Returns null if the file name is null.
   */
  private Writer fileNameToLegacyOutputWriter(String fileName)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[306]++;
int CodeCoverConditionCoverageHelper_C94;
    if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((fileName == null) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[196]++;
      return null;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[197]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[307]++;
int CodeCoverConditionCoverageHelper_C95;
    if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((testMode) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[198]++;
      return new StringWriter();

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[199]++;}

    return streamToLegacyOutputWriter(filenameToOutputStream(fileName));
  }

  /**
   * Converts a file name into a Writer taking in account the output charset.
   * Returns null if the file name is null.
   */
  private Writer fileNameToOutputWriter2(String fileName) throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[308]++;
int CodeCoverConditionCoverageHelper_C96;
    if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((fileName == null) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[200]++;
      return null;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[201]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[309]++;
int CodeCoverConditionCoverageHelper_C97;
    if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((testMode) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[202]++;
      return new StringWriter();

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[203]++;}

    return streamToOutputWriter2(filenameToOutputStream(fileName));
  }

  /**
   * Converts a file name into a Outputstream.
   * Returns null if the file name is null.
   */
  protected OutputStream filenameToOutputStream(String fileName)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[310]++;
int CodeCoverConditionCoverageHelper_C98;
    if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((fileName == null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)){
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[204]++;
      return null;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[205]++;}
    return new FileOutputStream(fileName);
  }

  /**
   * Create a writer with the legacy output charset.
   */
  private Writer streamToLegacyOutputWriter(OutputStream stream)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[311]++;
int CodeCoverConditionCoverageHelper_C99;
    if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((legacyOutputCharset == null) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[206]++;
      return new BufferedWriter(
          new OutputStreamWriter(stream));

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[207]++;
      return new BufferedWriter(
          new OutputStreamWriter(stream, legacyOutputCharset));
    }
  }

  /**
   * Create a writer with the newer output charset.
   */
  private Writer streamToOutputWriter2(OutputStream stream) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[312]++;
int CodeCoverConditionCoverageHelper_C100;
    if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((outputCharset2 == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[208]++;
      return new BufferedWriter(
          new OutputStreamWriter(stream));

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[209]++;
      return new BufferedWriter(
          new OutputStreamWriter(stream, outputCharset2));
    }
  }

  /**
   * Outputs the source map found in the compiler to the proper path if one
   * exists.
   *
   * @param options The options to the Compiler.
   */
  private void outputSourceMap(B options, String associatedName)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[313]++;
int CodeCoverConditionCoverageHelper_C101;
    if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((Strings.isNullOrEmpty(options.sourceMapOutputPath)) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[210]++;
      return;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[211]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[314]++;

    String outName = expandSourceMapPath(options, null);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[315]++;
    Writer out = fileNameToOutputWriter2(outName);
    compiler.getSourceMap().appendTo(out, associatedName);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[316]++;
    out.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[317]++;
  }

  /**
   * Returns the path at which to output map file(s) based on the path at which
   * the JS binary will be placed.
   *
   * @return The path in which to place the generated map file(s).
   */
  private String getMapPath(String outputFile) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[318]++;
    String basePath = "";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[319]++;
int CodeCoverConditionCoverageHelper_C102;

    if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((outputFile.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[212]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[320]++;
int CodeCoverConditionCoverageHelper_C103;
      // If we have a js_module_binary rule, output the maps
      // at modulename_props_map.out, etc.
      if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((config.moduleOutputPathPrefix.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[214]++;
        basePath = config.moduleOutputPathPrefix;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[321]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[215]++;
        basePath = "jscompiler";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[322]++;
      }

    } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[213]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[323]++;
      // Get the path of the output file.
      File file = new File(outputFile);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[324]++;

      String outputFileName = file.getName();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[325]++;
int CodeCoverConditionCoverageHelper_C104;

      // Strip the .js from the name.
      if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((outputFileName.endsWith(".js")) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[216]++;
        outputFileName =
            outputFileName.substring(0, outputFileName.length() - 3);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[326]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[217]++;}

      basePath = file.getParent() + File.separatorChar + outputFileName;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[327]++;
    }

    return basePath;
  }

  /**
   * Outputs the variable and property name maps for the specified compiler if
   * the proper FLAGS are set.
   */
  private void outputNameMaps() throws FlagUsageException,
      IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[328]++;

    String propertyMapOutputPath = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[329]++;
    String variableMapOutputPath = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[330]++;
    String functionInformationMapOutputPath = null;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[331]++;
int CodeCoverConditionCoverageHelper_C105;

    // Check the create_name_map_files FLAG.
    if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((config.createNameMapFiles) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[218]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[332]++;
      String basePath = getMapPath(config.jsOutputFile);

      propertyMapOutputPath = basePath + "_props_map.out";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[333]++;
      variableMapOutputPath = basePath + "_vars_map.out";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[334]++;
      functionInformationMapOutputPath = basePath + "_functions_map.out";
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[335]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[219]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[336]++;
int CodeCoverConditionCoverageHelper_C106;

    // Check the individual FLAGS.
    if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((config.variableMapOutputFile.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[220]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[337]++;
int CodeCoverConditionCoverageHelper_C107;
      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((variableMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[222]++;
        throw new FlagUsageException("The flags variable_map_output_file and "
            + "create_name_map_files cannot both be used simultaniously.");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[223]++;}

      variableMapOutputPath = config.variableMapOutputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[338]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[221]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[339]++;
int CodeCoverConditionCoverageHelper_C108;

    if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((config.propertyMapOutputFile.equals("")) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[224]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[340]++;
int CodeCoverConditionCoverageHelper_C109;
      if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((propertyMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[226]++;
        throw new FlagUsageException("The flags property_map_output_file and "
            + "create_name_map_files cannot both be used simultaniously.");

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[227]++;}

      propertyMapOutputPath = config.propertyMapOutputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[341]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[225]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[342]++;
int CodeCoverConditionCoverageHelper_C110;

    // Output the maps.
    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((variableMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[228]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[343]++;
int CodeCoverConditionCoverageHelper_C111;
      if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((compiler.getVariableMap() != null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[230]++;
        compiler.getVariableMap().save(variableMapOutputPath);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[344]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[231]++;}

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[229]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[345]++;
int CodeCoverConditionCoverageHelper_C112;

    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((propertyMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[232]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[346]++;
int CodeCoverConditionCoverageHelper_C113;
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((compiler.getPropertyMap() != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[234]++;
        compiler.getPropertyMap().save(propertyMapOutputPath);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[347]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[235]++;}

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[233]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[348]++;
int CodeCoverConditionCoverageHelper_C114;

    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((functionInformationMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[236]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[349]++;
int CodeCoverConditionCoverageHelper_C115;
      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((compiler.getFunctionalInformationMap() != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[238]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[350]++;
        OutputStream file =
            filenameToOutputStream(functionInformationMapOutputPath);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[351]++;
        CodedOutputStream outputStream = CodedOutputStream.newInstance(file);
        compiler.getFunctionalInformationMap().writeTo(outputStream);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[352]++;
        outputStream.flush();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[353]++;
        file.flush();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[354]++;
        file.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[355]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[239]++;}

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[237]++;}
  }

  /**
   * Create a map of constant names to constant values from a textual
   * description of the map.
   *
   * @param definitions A list of overriding definitions for defines in
   *     the form <name>[=<val>], where <val> is a number, boolean, or
   *     single-quoted string without single quotes.
   */
  @VisibleForTesting
  static void createDefineOrTweakReplacements(List<String> definitions,
      CompilerOptions options, boolean tweaks) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[356]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[43]++;


    // Parse the definitions
    for (String override : definitions) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[43]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[44]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[45]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[357]++;
      String[] assignment = override.split("=", 2);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[358]++;
      String defName = assignment[0];
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[359]++;
int CodeCoverConditionCoverageHelper_C116;

      if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((defName.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[240]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[360]++;
        String defValue = assignment.length == 1 ? "true" : assignment[1];
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[361]++;

        boolean isTrue = defValue.equals("true");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[362]++;
        boolean isFalse = defValue.equals("false");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[363]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (8)) == 0 || true) &&
 ((isTrue) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((isFalse) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[242]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[364]++;
int CodeCoverConditionCoverageHelper_C118;
          if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((tweaks) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[244]++;
            options.setTweakToBooleanLiteral(defName, isTrue);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[365]++;

          } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[245]++;
            options.setDefineToBooleanLiteral(defName, isTrue);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[366]++;
          }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[367]++;
          continue;

        } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[243]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[368]++;
int CodeCoverConditionCoverageHelper_C119; if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (512)) == 0 || true) &&
 ((defValue.length() > 1) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (256)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C119 |= (128)) == 0 || true) &&
 ((defValue.charAt(0) == '\'') && 
  ((CodeCoverConditionCoverageHelper_C119 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C119 |= (32)) == 0 || true) &&
 ((defValue.charAt(defValue.length() - 1) == '\'') && 
  ((CodeCoverConditionCoverageHelper_C119 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C119 |= (8)) == 0 || true) &&
 ((defValue.charAt(0) == '\"') && 
  ((CodeCoverConditionCoverageHelper_C119 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((defValue.charAt(defValue.length() - 1) == '\"') && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 5) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 5) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[246]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[369]++;
          // If the value starts and ends with a single quote,
          // we assume that it's a string.
          String maybeStringVal =
              defValue.substring(1, defValue.length() - 1);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[370]++;
int CodeCoverConditionCoverageHelper_C120;
          if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((maybeStringVal.indexOf(defValue.charAt(0)) == -1) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[248]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[371]++;
int CodeCoverConditionCoverageHelper_C121;
            if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((tweaks) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[250]++;
              options.setTweakToStringLiteral(defName, maybeStringVal);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[372]++;

            } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[251]++;
              options.setDefineToStringLiteral(defName, maybeStringVal);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[373]++;
            }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[374]++;
            continue;

          } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[249]++;}

        } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[247]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[375]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
          try {
CodeCoverTryBranchHelper_Try6 = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[376]++;
            double value = Double.parseDouble(defValue);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[377]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((tweaks) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[253]++;
              options.setTweakToDoubleLiteral(defName, value);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[378]++;

            } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[254]++;
              options.setDefineToDoubleLiteral(defName, value);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[379]++;
            }
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[380]++;
            continue;
          } catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[255]++;
            // do nothing, it will be caught at the end
          } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[252]++;
}
  }
        }
}

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[241]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[381]++;
int CodeCoverConditionCoverageHelper_C123;

      if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((tweaks) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[256]++;
        throw new RuntimeException(
            "--tweak flag syntax invalid: " + override);

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[257]++;}
      throw new RuntimeException(
          "--define flag syntax invalid: " + override);
    }
  }

  /**
   * Returns true if and only if a manifest or bundle should be generated
   * for each module, as opposed to one unified manifest.
   */
  private boolean shouldGenerateOutputPerModule(String output) {
    return !config.module.isEmpty()
        && output != null && output.contains("%outname%");
  }

  private void outputManifest() throws IOException {
    outputManifestOrBundle(config.outputManifests, true);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[382]++;
  }

  private void outputBundle() throws IOException {
    outputManifestOrBundle(config.outputBundles, false);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[383]++;
  }

  /**
   * Writes the manifest or bundle of all compiler input files that survived
   * manage_closure_dependencies, if requested.
   */
  private void outputManifestOrBundle(List<String> outputFiles,
      boolean isManifest) throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[384]++;
int CodeCoverConditionCoverageHelper_C124;
    if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((outputFiles.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[258]++;
      return;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[259]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[385]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[46]++;



    for (String output : outputFiles) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[46]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[47]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[48]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[386]++;
int CodeCoverConditionCoverageHelper_C125;
      if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((output.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[260]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[387]++;
        continue;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[261]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[388]++;
int CodeCoverConditionCoverageHelper_C126;

      if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((shouldGenerateOutputPerModule(output)) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[262]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[389]++;
        // Generate per-module manifests or bundles
        JSModuleGraph graph = compiler.getDegenerateModuleGraph();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[390]++;
        Iterable<JSModule> modules = graph.getAllModules();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[391]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[49]++;


        for (JSModule module : modules) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[49]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[50]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[51]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[392]++;
          Writer out = fileNameToOutputWriter2(
              expandCommandLinePath(output, module));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[393]++;
int CodeCoverConditionCoverageHelper_C127;
          if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((isManifest) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[264]++;
            printManifestTo(module.getInputs(), out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[394]++;

          } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[265]++;
            printBundleTo(module.getInputs(), out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[395]++;
          }
          out.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[396]++;
        }

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[263]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[397]++;
        // Generate a single file manifest or bundle.
        Writer out = fileNameToOutputWriter2(
            expandCommandLinePath(output, null));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[398]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((config.module.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[266]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[399]++;
int CodeCoverConditionCoverageHelper_C129;
          if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((isManifest) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[268]++;
            printManifestTo(compiler.getInputsInOrder(), out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[400]++;

          } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[269]++;
            printBundleTo(compiler.getInputsInOrder(), out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[401]++;
          }

        } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[267]++;
          printModuleGraphManifestOrBundleTo(
              compiler.getDegenerateModuleGraph(), out, isManifest);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[402]++;
        }
        out.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[403]++;
      }
    }
  }

  /**
   * Creates a file containing the current module graph in JSON serialization.
   */
  private void outputModuleGraphJson() throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[404]++;
int CodeCoverConditionCoverageHelper_C130;
    if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (8)) == 0 || true) &&
 ((config.outputModuleDependencies != null) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((config.outputModuleDependencies != "") && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 2) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 2) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[270]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[405]++;
      Writer out = fileNameToOutputWriter2(config.outputModuleDependencies);
      printModuleGraphJsonTo(out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[406]++;
      out.close();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[407]++;

    } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[271]++;}
  }

  /**
   * Prints the current module graph as JSON.
   */
  @VisibleForTesting
  void printModuleGraphJsonTo(Appendable out) throws IOException {
    out.append(compiler.getDegenerateModuleGraph().toJson().toString());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[408]++;
  }

  /**
   * Prints a set of modules to the manifest or bundle file.
   */
  @VisibleForTesting
  void printModuleGraphManifestOrBundleTo(JSModuleGraph graph,
      Appendable out, boolean isManifest) throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[409]++;
    Joiner commas = Joiner.on(",");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[410]++;
    boolean requiresNewline = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[411]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[52]++;


    for (JSModule module : graph.getAllModules()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[52]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[53]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[54]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[412]++;
int CodeCoverConditionCoverageHelper_C131;
      if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((requiresNewline) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[272]++;
        out.append("\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[413]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[273]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[414]++;
int CodeCoverConditionCoverageHelper_C132;

      if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((isManifest) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[274]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[415]++;
        // See CommandLineRunnerTest to see what the format of this
        // manifest looks like.
        String dependencies = commas.join(module.getSortedDependencyNames());
        out.append(
            String.format("{%s%s}\n",
                module.getName(),
                dependencies.isEmpty() ? "" : ":" + dependencies));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[416]++;
        printManifestTo(module.getInputs(), out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[417]++;

      } else {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[275]++;
        printBundleTo(module.getInputs(), out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[418]++;
      }
      requiresNewline = true;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[419]++;
    }
  }

  /**
   * Prints a list of input names (using root-relative paths), delimited by
   * newlines, to the manifest file.
   */
  private void printManifestTo(Iterable<CompilerInput> inputs, Appendable out)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[420]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[55]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[55]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[56]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[57]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[421]++;
      String rootRelativePath = rootRelativePathsMap.get(input.getName());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[422]++;
      String displayName = rootRelativePath != null
          ? rootRelativePath
          : input.getName();
      out.append(displayName);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[423]++;
      out.append("\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[424]++;
    }
  }

  /**
   * Prints all the input contents, starting with a comment that specifies
   * the input file name (using root-relative paths) before each file.
   */
  private void printBundleTo(Iterable<CompilerInput> inputs, Appendable out)
      throws IOException {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[425]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[58]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[58]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[59]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[60]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[426]++;
int CodeCoverConditionCoverageHelper_C133;
      // Every module has an empty file in it. This makes it easier to implement
      // cross-module code motion.
      //
      // But it also leads to a weird edge case because
      // a) If we don't have a module spec, we create a singleton module, and
      // b) If we print a bundle file, we copy the original input files.
      //
      // This means that in the (rare) case where we have no inputs, and no
      // module spec, and we're printing a bundle file, we'll have a fake
      // input file that shouldn't be copied. So we special-case this, to
      // make all the other cases simpler.
      if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((input.getName().equals(
              Compiler.createFillFileName(Compiler.SINGLETON_MODULE_NAME))) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[276]++;
        Preconditions.checkState(1 == Iterables.size(inputs));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[427]++;
        return;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[277]++;}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[428]++;

      String rootRelativePath = rootRelativePathsMap.get(input.getName());
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[429]++;
      String displayName = rootRelativePath != null
          ? rootRelativePath
          : input.getName();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[430]++;
      File file = new File(input.getName());
      out.append("//");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[431]++;
      out.append(displayName);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[432]++;
      out.append("\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[433]++;
      Files.copy(file, inputCharset, out);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[434]++;
      out.append("\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[435]++;
    }
  }

  /**
   * Construct and return the input root path map. The key is the exec path of
   * each input file, and the value is the corresponding root relative path.
   */
  private Map<String, String> constructRootRelativePathsMap() {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[436]++;
    Map<String, String> rootRelativePathsMap = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[437]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[61]++;


    for (String mapString : config.manifestMaps) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[61]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[62]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[63]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[438]++;
      int colonIndex = mapString.indexOf(':');
      Preconditions.checkState(colonIndex > 0);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[439]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[440]++;
      String execPath = mapString.substring(0, colonIndex);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[441]++;
      String rootRelativePath = mapString.substring(colonIndex + 1);
      Preconditions.checkState(rootRelativePath.indexOf(':') == -1);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[442]++;
      rootRelativePathsMap.put(execPath, rootRelativePath);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[443]++;
    }
    return rootRelativePathsMap;
  }

  private class RunTimeStats {
    private long bestRunTime = Long.MAX_VALUE;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[444]++;
  }
    private long worstRunTime = Long.MIN_VALUE;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[445]++;
  }
    private long lastStartTime = 0;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[446]++;
  }
    private List<List<String>> loopedPassesInBestRun = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[447]++;
  }

    /**
     * Record the start of a run.
     */
    private void recordStartRun() {
      lastStartTime = System.currentTimeMillis();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[448]++;
      PhaseOptimizer.clearLoopsRun();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[449]++;
    }

    /**
     * Record the end of a run.
     */
    private void recordEndRun() {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[450]++;
      long endTime = System.currentTimeMillis();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[451]++;
      long length = endTime - lastStartTime;
      worstRunTime = Math.max(length, worstRunTime);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[452]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[453]++;
int CodeCoverConditionCoverageHelper_C134;
      if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((length < bestRunTime) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[278]++;
        loopedPassesInBestRun = PhaseOptimizer.getLoopsRun();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[454]++;
        bestRunTime = length;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[455]++;

      } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[279]++;}
    }

    /**
     * Print the best phase loop to stderr.
     */
    private void outputBestPhaseOrdering() {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[456]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
      try {
CodeCoverTryBranchHelper_Try7 = true;
        jsOutput.append("Best time: " + bestRunTime + "\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[457]++;
        jsOutput.append("Worst time: " + worstRunTime + "\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[458]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[459]++;

        int i = 1;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[460]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[64]++;


        for (List<String> loop : loopedPassesInBestRun) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[64]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[65]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[66]++;
}
          jsOutput.append(
              "\nLoop " + i + ":\n" + Joiner.on("\n").join(loop)+ "\n");
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[461]++;
          i++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[462]++;
        }
      } catch (IOException e) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[281]++;
        throw new RuntimeException("unexpected exception", e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[280]++;
}
  }
    }
  }

  /**
   * Configurations for the command line configs. Designed for easy
   * building, so that we can decouple the flags-parsing library from
   * the actual configuration options.
   *
   * By design, these configurations must match one-to-one with
   * command-line flags.
   */
  static class CommandLineConfig {
    private boolean printTree = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[463]++;
  }

    /** Prints out the parse tree and exits */
    CommandLineConfig setPrintTree(boolean printTree) {
      this.printTree = printTree;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[464]++;
      return this;
    }

    private boolean computePhaseOrdering = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[465]++;
  }

    /**
     * Runs the compile job many times, then prints out the best phase
     * ordering from this run
     */
    CommandLineConfig setComputePhaseOrdering(boolean computePhaseOrdering) {
      this.computePhaseOrdering = computePhaseOrdering;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[466]++;
      return this;
    }

    private boolean printAst = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[467]++;
  }

    /**
     * Prints a dot file describing the internal abstract syntax tree
     * and exits
     */
    CommandLineConfig setPrintAst(boolean printAst) {
      this.printAst = printAst;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[468]++;
      return this;
    }

    private boolean printPassGraph = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[469]++;
  }

    /** Prints a dot file describing the passes that will get run and exits */
    CommandLineConfig setPrintPassGraph(boolean printPassGraph) {
      this.printPassGraph = printPassGraph;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[470]++;
      return this;
    }

    private CompilerOptions.DevMode jscompDevMode = CompilerOptions.DevMode.OFF;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[471]++;
  }

    /** Turns on extra sanity checks */
    CommandLineConfig setJscompDevMode(CompilerOptions.DevMode jscompDevMode) {
      this.jscompDevMode = jscompDevMode;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[472]++;
      return this;
    }

    private String loggingLevel = Level.WARNING.getName();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[473]++;
  }

    /**
     * The logging level (standard java.util.logging.Level
     * values) for Compiler progress. Does not control errors or
     * warnings for the JavaScript code under compilation
     */
    CommandLineConfig setLoggingLevel(String loggingLevel) {
      this.loggingLevel = loggingLevel;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[474]++;
      return this;
    }

    private final List<String> externs = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[475]++;
  }

    /**
     * The file containing JavaScript externs. You may specify multiple.
     */
    CommandLineConfig setExterns(List<String> externs) {
      this.externs.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[476]++;
      this.externs.addAll(externs);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[477]++;
      return this;
    }

    private final List<String> js = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[478]++;
  }

    /**
     * The JavaScript filename. You may specify multiple.
     */
    CommandLineConfig setJs(List<String> js) {
      this.js.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[479]++;
      this.js.addAll(js);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[480]++;
      return this;
    }

    private String jsOutputFile = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[481]++;
  }

    /**
     * Primary output filename. If not specified, output is written to stdout
     */
    CommandLineConfig setJsOutputFile(String jsOutputFile) {
      this.jsOutputFile = jsOutputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[482]++;
      return this;
    }

    private final List<String> module = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[483]++;
  }

    /**
     * A JavaScript module specification. The format is
     * <name>:<num-js-files>[:[<dep>,...][:]]]. Module names must be
     * unique. Each dep is the name of a module that this module
     * depends on. Modules must be listed in dependency order, and JS
     * source files must be listed in the corresponding order. Where
     * --module flags occur in relation to --js flags is unimportant
     */
    CommandLineConfig setModule(List<String> module) {
      this.module.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[484]++;
      this.module.addAll(module);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[485]++;
      return this;
    }

    private String variableMapInputFile = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[486]++;
  }

    /**
     * File containing the serialized version of the variable renaming
     * map produced by a previous compilation
     */
    CommandLineConfig setVariableMapInputFile(String variableMapInputFile) {
      this.variableMapInputFile = variableMapInputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[487]++;
      return this;
    }

    private String propertyMapInputFile = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[488]++;
  }

    /**
     * File containing the serialized version of the property renaming
     * map produced by a previous compilation
     */
    CommandLineConfig setPropertyMapInputFile(String propertyMapInputFile) {
      this.propertyMapInputFile = propertyMapInputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[489]++;
      return this;
    }

    private String variableMapOutputFile = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[490]++;
  }

    /**
     * File where the serialized version of the variable renaming map
     * produced should be saved
     */
    CommandLineConfig setVariableMapOutputFile(String variableMapOutputFile) {
      this.variableMapOutputFile = variableMapOutputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[491]++;
      return this;
    }

    private boolean createNameMapFiles = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[492]++;
  }

    /**
     * If true, variable renaming and property renaming map
     * files will be produced as {binary name}_vars_map.out and
     * {binary name}_props_map.out. Note that this flag cannot be used
     * in conjunction with either variable_map_output_file or
     * property_map_output_file
     */
    CommandLineConfig setCreateNameMapFiles(boolean createNameMapFiles) {
      this.createNameMapFiles = createNameMapFiles;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[493]++;
      return this;
    }

    private String propertyMapOutputFile = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[494]++;
  }

    /**
     * File where the serialized version of the property renaming map
     * produced should be saved
     */
    CommandLineConfig setPropertyMapOutputFile(String propertyMapOutputFile) {
      this.propertyMapOutputFile = propertyMapOutputFile;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[495]++;
      return this;
    }

    private CodingConvention codingConvention = CodingConventions.getDefault();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[496]++;
  }

    /**
     * Sets rules and conventions to enforce.
     */
    CommandLineConfig setCodingConvention(CodingConvention codingConvention) {
      this.codingConvention = codingConvention;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[497]++;
      return this;
    }

    private int summaryDetailLevel = 1;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[498]++;
  }

    /**
     * Controls how detailed the compilation summary is. Values:
     *  0 (never print summary), 1 (print summary only if there are
     * errors or warnings), 2 (print summary if type checking is on,
     * see --check_types), 3 (always print summary). The default level
     * is 1
     */
    CommandLineConfig setSummaryDetailLevel(int summaryDetailLevel) {
      this.summaryDetailLevel = summaryDetailLevel;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[499]++;
      return this;
    }

    private String outputWrapper = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[500]++;
  }

    /**
     * Interpolate output into this string at the place denoted
     * by the marker token %output%, or %output|jsstring%
     */
    CommandLineConfig setOutputWrapper(String outputWrapper) {
      this.outputWrapper = outputWrapper;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[501]++;
      return this;
    }

    private final List<String> moduleWrapper = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[502]++;
  }

    /**
     * An output wrapper for a JavaScript module (optional). See the flag
     * description for formatting requirements.
     */
    CommandLineConfig setModuleWrapper(List<String> moduleWrapper) {
      this.moduleWrapper.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[503]++;
      this.moduleWrapper.addAll(moduleWrapper);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[504]++;
      return this;
    }

    private String moduleOutputPathPrefix = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[505]++;
  }

    /**
     * Prefix for filenames of compiled JS modules.
     * <module-name>.js will be appended to this prefix. Directories
     * will be created as needed. Use with --module
     */
    CommandLineConfig setModuleOutputPathPrefix(String moduleOutputPathPrefix) {
      this.moduleOutputPathPrefix = moduleOutputPathPrefix;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[506]++;
      return this;
    }

    private String createSourceMap = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[507]++;
  }

    /**
     * If specified, a source map file mapping the generated
     * source files back to the original source file will be
     * output to the specified path. The %outname% placeholder will
     * expand to the name of the output file that the source map
     * corresponds to.
     */
    CommandLineConfig setCreateSourceMap(String createSourceMap) {
      this.createSourceMap = createSourceMap;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[508]++;
      return this;
    }

    private SourceMap.DetailLevel sourceMapDetailLevel =
        SourceMap.DetailLevel.ALL;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[509]++;
  }

    /**
     * The detail supplied in the source map file, if generated.
     */
    CommandLineConfig setSourceMapDetailLevel(SourceMap.DetailLevel level) {
      this.sourceMapDetailLevel = level;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[510]++;
      return this;
    }

    private SourceMap.Format sourceMapFormat =
      SourceMap.Format.DEFAULT;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[511]++;
  }

    /**
     * The detail supplied in the source map file, if generated.
     */
    CommandLineConfig setSourceMapFormat(SourceMap.Format format) {
      this.sourceMapFormat = format;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[512]++;
      return this;
    }

    private WarningGuardSpec warningGuards = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[513]++;
  }

    /**
     * Add warning guards.
     */
    CommandLineConfig setWarningGuardSpec(WarningGuardSpec spec) {
      this.warningGuards = spec;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[514]++;
      return this;
    }

    private final List<String> define = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[515]++;
  }

    /**
     * Override the value of a variable annotated @define.
     * The format is <name>[=<val>], where <name> is the name of a @define
     * variable and <val> is a boolean, number, or a single-quoted string
     * that contains no single quotes. If [=<val>] is omitted,
     * the variable is marked true
     */
    CommandLineConfig setDefine(List<String> define) {
      this.define.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[516]++;
      this.define.addAll(define);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[517]++;
      return this;
    }

    private final List<String> tweak = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[518]++;
  }

    /**
     * Override the default value of a registered tweak. The format is
     * <name>[=<val>], where <name> is the ID of a tweak and <val> is a boolean,
     * number, or a single-quoted string that contains no single quotes. If
     * [=<val>] is omitted, then true is assumed.
     */
    CommandLineConfig setTweak(List<String> tweak) {
      this.tweak.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[519]++;
      this.tweak.addAll(tweak);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[520]++;
      return this;
    }

    private TweakProcessing tweakProcessing = TweakProcessing.OFF;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[521]++;
  }

    /**
     * Sets the kind of processing to do for goog.tweak functions.
     */
    CommandLineConfig setTweakProcessing(TweakProcessing tweakProcessing) {
      this.tweakProcessing = tweakProcessing;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[522]++;
      return this;
    }

    private String charset = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[523]++;
  }

    /**
     * Input charset for all files.
     */
    CommandLineConfig setCharset(String charset) {
      this.charset = charset;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[524]++;
      return this;
    }

    private boolean manageClosureDependencies = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[525]++;
  }

    /**
     * Sets whether to sort files by their goog.provide/require deps,
     * and prune inputs that are not required.
     */
    CommandLineConfig setManageClosureDependencies(boolean newVal) {
      this.manageClosureDependencies = newVal;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[526]++;
      return this;
    }

    private boolean onlyClosureDependencies = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[527]++;
  }

    /**
     * Sets whether to sort files by their goog.provide/require deps,
     * and prune inputs that are not required, and drop all non-closure
     * files.
     */
    CommandLineConfig setOnlyClosureDependencies(boolean newVal) {
      this.onlyClosureDependencies = newVal;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[528]++;
      return this;
    }

    private List<String> closureEntryPoints = ImmutableList.of();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[529]++;
  }

    /**
     * Set closure entry points, which makes the compiler only include
     * those files and sort them in dependency order.
     */
    CommandLineConfig setClosureEntryPoints(List<String> entryPoints) {
      Preconditions.checkNotNull(entryPoints);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[530]++;
      this.closureEntryPoints = entryPoints;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[531]++;
      return this;
    }

    private List<String> outputManifests = ImmutableList.of();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[532]++;
  }

    /**
     * Sets whether to print output manifest files.
     * Filter out empty file names.
     */
    CommandLineConfig setOutputManifest(List<String> outputManifests) {
      this.outputManifests = Lists.newArrayList();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[533]++;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[534]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[67]++;


      for (String manifestName : outputManifests) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[67]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[68]--;
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.loops[69]++;
}
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[535]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((manifestName.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[282]++;
          this.outputManifests.add(manifestName);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[536]++;

        } else {
  CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.branches[283]++;}
      }
      this.outputManifests = ImmutableList.copyOf(this.outputManifests);
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[537]++;
      return this;
    }

    private String outputModuleDependencies = null;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[538]++;
  }

    /**
     * Sets whether a JSON file representing the dependencies between modules
     * should be created.
     */
    CommandLineConfig setOutputModuleDependencies(String
        outputModuleDependencies) {
      this.outputModuleDependencies = outputModuleDependencies;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[539]++;
      return this;
    }

    private List<String> outputBundles = ImmutableList.of();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[540]++;
  }

    /**
     * Sets whether to print output bundle files.
     */
    CommandLineConfig setOutputBundle(List<String> outputBundles) {
      this.outputBundles = outputBundles;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[541]++;
      return this;
    }

    private boolean acceptConstKeyword = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[542]++;
  }

    /**
     * Sets whether to accept usage of 'const' keyword.
     */
    CommandLineConfig setAcceptConstKeyword(boolean acceptConstKeyword) {
      this.acceptConstKeyword = acceptConstKeyword;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[543]++;
      return this;
    }

    private String languageIn = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[544]++;
  }

    /**
     * Sets whether to accept input files as ECMAScript5 compliant.
     * Otherwise, input files are treated as ECMAScript3 compliant.
     */
    CommandLineConfig setLanguageIn(String languageIn) {
      this.languageIn = languageIn;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[545]++;
      return this;
    }

    private boolean skipNormalOutputs = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[546]++;
  }

    /**
     * Sets whether the normal outputs of compilation should be skipped.
     */
    CommandLineConfig setSkipNormalOutputs(boolean skipNormalOutputs) {
      this.skipNormalOutputs = skipNormalOutputs;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[547]++;
      return this;
    }

    private List<String> manifestMaps = ImmutableList.of();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[548]++;
  }

    /**
     * Sets the execPath:rootRelativePath mappings
     */
    CommandLineConfig setManifestMaps(List<String> manifestMaps) {
      this.manifestMaps = manifestMaps;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[549]++;
      return this;
    }


    private boolean transformAMDToCJSModules = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[550]++;
  }

    /**
     * Set whether to transform AMD to CommonJS modules.
     */
    CommandLineConfig setTransformAMDToCJSModules(
        boolean transformAMDToCJSModules) {
      this.transformAMDToCJSModules = transformAMDToCJSModules;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[551]++;
      return this;
    }

    private boolean processCommonJSModules = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[552]++;
  }

    /**
     * Sets whether to process CommonJS modules.
     */
    CommandLineConfig setProcessCommonJSModules(
        boolean processCommonJSModules) {
      this.processCommonJSModules = processCommonJSModules;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[553]++;
      return this;
    }


    private String commonJSModulePathPrefix =
        ProcessCommonJSModules.DEFAULT_FILENAME_PREFIX;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[554]++;
  }

    /**
     * Sets the CommonJS module path prefix.
     */
    CommandLineConfig setCommonJSModulePathPrefix(
        String commonJSModulePathPrefix) {
      this.commonJSModulePathPrefix = commonJSModulePathPrefix;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[555]++;
      return this;
    }

    private String warningsWhitelistFile = "";
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[556]++;
  }

    /**
     * Sets a whitelist file that suppresses warnings.
     */
    CommandLineConfig setWarningsWhitelistFile(String fileName) {
      this.warningsWhitelistFile = fileName;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[557]++;
      return this;
    }

    private boolean angularPass = false;
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[558]++;
  }

    /**
     * Sets whether to process AngularJS-specific annotations.
     */
    CommandLineConfig setAngularPass(boolean angularPass) {
      this.angularPass = angularPass;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[559]++;
      return this;
    }
  }

  /**
   * A little helper class to make it easier to collect warning types
   * from --jscomp_error, --jscomp_warning, and --jscomp_off.
   */
  protected static class WarningGuardSpec {
    private static class Entry {
      private final CheckLevel level;
      private final String groupName;

      private Entry(CheckLevel level, String groupName) {
        this.level = level;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[560]++;
        this.groupName = groupName;
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[561]++;
      }
    }

    // The entries, in the order that they were added.
    private final List<Entry> entries = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[562]++;
  }

    protected void add(CheckLevel level, String groupName) {
      entries.add(new Entry(level, groupName));
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[563]++;
    }

    protected void clear() {
      entries.clear();
CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1.statements[564]++;
    }
  }
}

class CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1 ());
  }
    public static long[] statements = new long[565];
    public static long[] branches = new long[284];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[136];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AbstractCommandLineRunner.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,3,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1};
    for (int i = 1; i <= 135; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[70];

  public CodeCoverCoverageCounter$15rklg6ahh1zpfl6f6mhhfe9r5okohg0ra41ualgz4uuoo1 () {
    super("com.google.javascript.jscomp.AbstractCommandLineRunner.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 564; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 283; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 135; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 69; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AbstractCommandLineRunner.java");
      for (int i = 1; i <= 564; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 283; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 135; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 23; i++) {
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

