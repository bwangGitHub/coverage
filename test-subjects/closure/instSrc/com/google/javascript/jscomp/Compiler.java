/*
 * Copyright 2004 The Closure Compiler Authors.
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
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.CharStreams;
import com.google.javascript.jscomp.CompilerOptions.DevMode;
import com.google.javascript.jscomp.CompilerOptions.LanguageMode;
import com.google.javascript.jscomp.ReferenceCollectingCallback.ReferenceCollection;
import com.google.javascript.jscomp.Scope.Var;
import com.google.javascript.jscomp.deps.SortedDependencies;
import com.google.javascript.jscomp.deps.SortedDependencies.CircularDependencyException;
import com.google.javascript.jscomp.deps.SortedDependencies.MissingProvideException;
import com.google.javascript.jscomp.parsing.Config;
import com.google.javascript.jscomp.parsing.ParserRunner;
import com.google.javascript.jscomp.type.ChainableReverseAbstractInterpreter;
import com.google.javascript.jscomp.type.ClosureReverseAbstractInterpreter;
import com.google.javascript.jscomp.type.ReverseAbstractInterpreter;
import com.google.javascript.jscomp.type.SemanticReverseAbstractInterpreter;
import com.google.javascript.rhino.IR;
import com.google.javascript.rhino.InputId;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.head.ErrorReporter;
import com.google.javascript.rhino.head.ast.AstRoot;
import com.google.javascript.rhino.jstype.JSTypeRegistry;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

/**
 * Compiler (and the other classes in this package) does the following:
 * <ul>
 * <li>parses JS code
 * <li>checks for undefined variables
 * <li>performs optimizations such as constant folding and constants inlining
 * <li>renames variables (to short names)
 * <li>outputs compact JavaScript code
 * </ul>
 *
 * External variables are declared in 'externs' files. For instance, the file
 * may include definitions for global javascript/browser objects such as
 * window, document.
 *
 */
public class Compiler extends AbstractCompiler {
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.ping();
  }

  static final String SINGLETON_MODULE_NAME = "[singleton]";
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[1]++;
  }

  static final DiagnosticType MODULE_DEPENDENCY_ERROR =
      DiagnosticType.error("JSC_MODULE_DEPENDENCY_ERROR",
          "Bad dependency: {0} -> {1}. "
              + "Modules must be listed in dependency order.");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[2]++;
  }

  static final DiagnosticType MISSING_ENTRY_ERROR = DiagnosticType.error(
      "JSC_MISSING_ENTRY_ERROR",
      "required entry point \"{0}\" never provided");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[3]++;
  }

  // Used in PerformanceTracker
  static final String PARSING_PASS_NAME = "parseInputs";
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[4]++;
  }

  private static final String CONFIG_RESOURCE =
      "com.google.javascript.jscomp.parsing.ParserConfig";
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[5]++;
  }

  CompilerOptions options = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[6]++;
  }

  private PassConfig passes = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[7]++;
  }

  // The externs inputs
  private List<CompilerInput> externs;

  // The JS source modules
  private List<JSModule> modules;

  // The graph of the JS source modules. Must be null if there are less than
  // 2 modules, because we use this as a signal for which passes to run.
  private JSModuleGraph moduleGraph;

  // The JS source inputs
  private List<CompilerInput> inputs;

  // error manager to which error management is delegated
  private ErrorManager errorManager;

  // Warnings guard for filtering warnings.
  private WarningsGuard warningsGuard;

  // Compile-time injected libraries. The node points to the last node of
  // the library, so code can be inserted after.
  private final Map<String, Node> injectedLibraries = Maps.newLinkedHashMap();
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[8]++;
  }

  // Parse tree root nodes
  Node externsRoot;
  Node jsRoot;
  Node externAndJsRoot;

  private Map<InputId, CompilerInput> inputsById;

  /** The source code map */
  private SourceMap sourceMap;

  /** The externs created from the exports.  */
  private String externExports = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[9]++;
  }

  /**
   * Ids for function inlining so that each declared name remains
   * unique.
   */
  private int uniqueNameId = 0;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[10]++;
  }

  /**
   * Whether to assume there are references to the RegExp Global object
   * properties.
   */
  private boolean hasRegExpGlobalReferences = true;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[11]++;
  }

  /** The function information map */
  private FunctionInformationMap functionInformationMap;

  /** Debugging information */
  private final StringBuilder debugLog = new StringBuilder();
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[12]++;
  }

  /** Detects Google-specific coding conventions. */
  CodingConvention defaultCodingConvention = new ClosureCodingConvention();
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[13]++;
  }

  private JSTypeRegistry typeRegistry;
  private Config parserConfig = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[14]++;
  }

  private ReverseAbstractInterpreter abstractInterpreter;
  private TypeValidator typeValidator;

  public PerformanceTracker tracker;

  // The oldErrorReporter exists so we can get errors from the JSTypeRegistry.
  private final com.google.javascript.rhino.ErrorReporter oldErrorReporter =
      RhinoErrorReporter.forOldRhino(this);
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[15]++;
  }

  // This error reporter gets the messages from the current Rhino parser.
  private final ErrorReporter defaultErrorReporter =
      RhinoErrorReporter.forNewRhino(this);
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[16]++;
  }

  /** Error strings used for reporting JSErrors */
  public static final DiagnosticType OPTIMIZE_LOOP_ERROR = DiagnosticType.error(
      "JSC_OPTIMIZE_LOOP_ERROR",
      "Exceeded max number of optimization iterations: {0}");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[17]++;
  }
  public static final DiagnosticType MOTION_ITERATIONS_ERROR =
      DiagnosticType.error("JSC_OPTIMIZE_LOOP_ERROR",
          "Exceeded max number of code motion iterations: {0}");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[18]++;
  }

  // We use many recursive algorithms that use O(d) memory in the depth
  // of the tree.
  private static final long COMPILER_STACK_SIZE = (1 << 21);
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[19]++;
  } // About 2MB

  /**
   * Under JRE 1.6, the JS Compiler overflows the stack when running on some
   * large or complex JS code. When threads are available, we run all compile
   * jobs on a separate thread with a larger stack.
   *
   * That way, we don't have to increase the stack size for *every* thread
   * (which is what -Xss does).
   */
  private static final ExecutorService compilerExecutor =
      Executors.newCachedThreadPool(new ThreadFactory() {
    @Override public Thread newThread(Runnable r) {
      return new Thread(null, r, "jscompiler", COMPILER_STACK_SIZE);
    }
  });
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[20]++;
  }

  /**
   * Use a dedicated compiler thread per Compiler instance.
   */
  private Thread compilerThread = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[21]++;
  }

  /** Whether to use threads. */
  private boolean useThreads = true;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[22]++;
  }


  /**
   * Logger for the whole com.google.javascript.jscomp domain -
   * setting configuration for this logger affects all loggers
   *  in other classes within the compiler.
   */
  private static final Logger logger =
      Logger.getLogger("com.google.javascript.jscomp");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[23]++;
  }

  private final PrintStream outStream;

  private GlobalVarReferenceMap globalRefMap = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[24]++;
  }

  private volatile double progress = 0.0;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[25]++;
  }
  private String lastPassName;

  /**
   * Creates a Compiler that reports errors and warnings to its logger.
   */
  public Compiler() {
    this((PrintStream) null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[26]++;
  }

  /**
   * Creates n Compiler that reports errors and warnings to an output
   * stream.
   */
  public Compiler(PrintStream stream) {
    addChangeHandler(recentChange);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[27]++;
    outStream = stream;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[28]++;
  }

  /**
   * Creates a Compiler that uses a custom error manager.
   */
  public Compiler(ErrorManager errorManager) {
    this();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[29]++;
    setErrorManager(errorManager);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[30]++;
  }

  /**
   * Sets the error manager.
   *
   * @param errorManager the error manager, it cannot be {@code null}
   */
  public void setErrorManager(ErrorManager errorManager) {
    Preconditions.checkNotNull(
        errorManager, "the error manager cannot be null");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[31]++;
    this.errorManager = errorManager;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[32]++;
  }

  /**
   * Creates a message formatter instance corresponding to the value of
   * {@link CompilerOptions}.
   */
  private MessageFormatter createMessageFormatter() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[33]++;
    boolean colorize = options.shouldColorizeErrorOutput();
    return options.errorFormat.toFormatter(this, colorize);
  }

  /**
   * Initialize the compiler options. Only necessary if you're not doing
   * a normal compile() job.
   */
  public void initOptions(CompilerOptions options) {
    this.options = options;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[34]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[35]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorManager == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[1]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[36]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((outStream == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[3]++;
        setErrorManager(
            new LoggerErrorManager(createMessageFormatter(), logger));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[37]++;

      } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[4]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[38]++;
        PrintStreamErrorManager printer =
            new PrintStreamErrorManager(createMessageFormatter(), outStream);
        printer.setSummaryDetailLevel(options.summaryDetailLevel);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[39]++;
        setErrorManager(printer);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[40]++;
      }

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[2]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[41]++;
int CodeCoverConditionCoverageHelper_C3;

    // DiagnosticGroups override the plain checkTypes option.
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((options.enables(DiagnosticGroups.CHECK_TYPES)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[5]++;
      options.checkTypes = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[42]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[6]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[43]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((options.disables(DiagnosticGroups.CHECK_TYPES)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[7]++;
      options.checkTypes = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[44]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[8]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[45]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((options.checkTypes) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[9]++;
      // If DiagnosticGroups did not override the plain checkTypes
      // option, and checkTypes is enabled, then turn off the
      // parser type warnings.
      options.setWarningLevel(
          DiagnosticGroup.forType(
              RhinoErrorReporter.TYPE_PARSE_ERROR),
          CheckLevel.OFF);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[46]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[10]++;}
}
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[47]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((options.checkGlobalThisLevel.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((options.disables(DiagnosticGroups.GLOBAL_THIS)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[11]++;
      options.setWarningLevel(
          DiagnosticGroups.GLOBAL_THIS,
          options.checkGlobalThisLevel);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[48]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[12]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[49]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((options.getLanguageIn() == LanguageMode.ECMASCRIPT5_STRICT) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[13]++;
      options.setWarningLevel(
          DiagnosticGroups.ES5_STRICT,
          CheckLevel.ERROR);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[50]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[14]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[51]++;

    // Initialize the warnings guard.
    List<WarningsGuard> guards = Lists.newArrayList();
    guards.add(
        new SuppressDocWarningsGuard(
            getDiagnosticGroups().getRegisteredGroups()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[52]++;
    guards.add(options.getWarningsGuard());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[53]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[54]++;

    ComposeWarningsGuard composedGuards = new ComposeWarningsGuard(guards);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[55]++;
int CodeCoverConditionCoverageHelper_C8;

    // All passes must run the variable check. This synthesizes
    // variables later so that the compiler doesn't crash. It also
    // checks the externs file for validity. If you don't want to warn
    // about missing variable declarations, we shut that specific
    // error off.
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((options.checkSymbols) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((composedGuards.enables(DiagnosticGroups.CHECK_VARIABLES)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[15]++;
      composedGuards.addGuard(new DiagnosticGroupWarningsGuard(
          DiagnosticGroups.CHECK_VARIABLES, CheckLevel.OFF));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[56]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[16]++;}

    this.warningsGuard = composedGuards;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[57]++;
  }

  /**
   * Initializes the instance state needed for a compile job.
   * @deprecated Convert your arrays to lists and use the list-based API.
   */
  @Deprecated
  public void init(JSSourceFile[] externs, JSSourceFile[] inputs,
      CompilerOptions options) {
    init(Lists.<JSSourceFile>newArrayList(externs),
        Lists.<JSSourceFile>newArrayList(inputs), options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[58]++;
  }

  /**
   * Initializes the instance state needed for a compile job.
   */
  public <T1 extends SourceFile, T2 extends SourceFile> void init(
      List<T1> externs,
      List<T2> inputs,
      CompilerOptions options) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[59]++;
    JSModule module = new JSModule(SINGLETON_MODULE_NAME);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[60]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[1]++;


    for (SourceFile input : inputs) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[1]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[2]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[3]++;
}
      module.add(input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[61]++;
    }

    initModules(externs, Lists.newArrayList(module), options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[62]++;
  }

  /**
   * Initializes the instance state needed for a compile job if the sources
   * are in modules.
   * @deprecated Convert your arrays to lists and use the list-based API.
   */
  @Deprecated
  public void init(JSSourceFile[] externs, JSModule[] modules,
      CompilerOptions options) {
    initModules(Lists.<SourceFile>newArrayList(externs),
         Lists.<JSModule>newArrayList(modules), options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[63]++;
  }

  /**
   * Initializes the instance state needed for a compile job if the sources
   * are in modules.
   */
  public <T extends SourceFile> void initModules(
      List<T> externs, List<JSModule> modules, CompilerOptions options) {
    initOptions(options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[64]++;

    checkFirstModule(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[65]++;
    fillEmptyModules(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[66]++;

    this.externs = makeCompilerInput(externs, true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[67]++;

    // Generate the module graph, and report any errors in the module
    // specification as errors.
    this.modules = modules;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[68]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[69]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((modules.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[17]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[70]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
        this.moduleGraph = new JSModuleGraph(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[71]++;
      } catch (JSModuleGraph.ModuleDependenceException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[20]++;
        // problems with the module format.  Report as an error.  The
        // message gives all details.
        report(JSError.make(MODULE_DEPENDENCY_ERROR,
                e.getModule().getName(), e.getDependentModule().getName()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[72]++;
        return;
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[19]++;
}
  }

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[18]++;
      this.moduleGraph = null;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[73]++;
    }

    this.inputs = getAllInputsFromModules(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[74]++;
    initBasedOnOptions();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[75]++;

    initInputsByIdMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[76]++;
  }

  /**
   * Do any initialization that is dependent on the compiler options.
   */
  private void initBasedOnOptions() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[77]++;
int CodeCoverConditionCoverageHelper_C10;
    // Create the source map if necessary.
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((options.sourceMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[21]++;
      sourceMap = options.sourceMapFormat.getInstance();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[78]++;
      sourceMap.setPrefixMappings(options.sourceMapLocationMappings);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[79]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[22]++;}
  }

  private <T extends SourceFile> List<CompilerInput> makeCompilerInput(
      List<T> files, boolean isExtern) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[80]++;
    List<CompilerInput> inputs = Lists.newArrayList();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[81]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[4]++;


    for (T file : files) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[4]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[5]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[6]++;
}
      inputs.add(new CompilerInput(file, isExtern));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[82]++;
    }
    return inputs;
  }

  private static final DiagnosticType EMPTY_MODULE_LIST_ERROR =
      DiagnosticType.error("JSC_EMPTY_MODULE_LIST_ERROR",
          "At least one module must be provided");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[83]++;
  }

  private static final DiagnosticType EMPTY_ROOT_MODULE_ERROR =
      DiagnosticType.error("JSC_EMPTY_ROOT_MODULE_ERROR",
          "Root module '{0}' must contain at least one source code input");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[84]++;
  }

  /**
   * Verifies that at least one module has been provided and that the first one
   * has at least one source code input.
   */
  private void checkFirstModule(List<JSModule> modules) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[85]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((modules.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[23]++;
      report(JSError.make(EMPTY_MODULE_LIST_ERROR));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[86]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[24]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[87]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((modules.get(0).getInputs().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((modules.size() > 1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[25]++;
      // The root module may only be empty if there is exactly 1 module.
      report(JSError.make(EMPTY_ROOT_MODULE_ERROR,
          modules.get(0).getName()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[88]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[26]++;}
}
  }

  /**
   * Empty modules get an empty "fill" file, so that we can move code into
   * an empty module.
   */
  static String createFillFileName(String moduleName) {
    return "[" + moduleName + "]";
  }

  /**
   * Fill any empty modules with a place holder file. It makes any cross module
   * motion easier.
   */
  private static void fillEmptyModules(List<JSModule> modules) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[89]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[7]++;


    for (JSModule module : modules) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[7]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[8]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[9]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[90]++;
int CodeCoverConditionCoverageHelper_C13;
      if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((module.getInputs().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[27]++;
        module.add(SourceFile.fromCode(
            createFillFileName(module.getName()), ""));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[91]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[28]++;}
    }
  }

  /**
   * Rebuilds the internal list of inputs by iterating over all modules.
   * This is necessary if inputs have been added to or removed from a module
   * after the {@link #init(List, List, CompilerOptions)} call.
   */
  public void rebuildInputsFromModules() {
    inputs = getAllInputsFromModules(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[92]++;
    initInputsByIdMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[93]++;
  }

  /**
   * Builds a single list of all module inputs. Verifies that it contains no
   * duplicates.
   */
  private static List<CompilerInput> getAllInputsFromModules(
      List<JSModule> modules) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[94]++;
    List<CompilerInput> inputs = Lists.newArrayList();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[95]++;
    Map<String, JSModule> inputMap = Maps.newHashMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[96]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[10]++;


    for (JSModule module : modules) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[10]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[11]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[12]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[97]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[13]++;


      for (CompilerInput input : module.getInputs()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[13]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[14]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[15]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[98]++;
        String inputName = input.getName();

        // NOTE(nicksantos): If an input is in more than one module,
        // it will show up twice in the inputs list, and then we
        // will get an error down the line.
        inputs.add(input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[99]++;
        inputMap.put(inputName, module);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[100]++;
      }
    }
    return inputs;
  }

  static final DiagnosticType DUPLICATE_INPUT =
      DiagnosticType.error("JSC_DUPLICATE_INPUT", "Duplicate input: {0}");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[101]++;
  }
  static final DiagnosticType DUPLICATE_EXTERN_INPUT =
      DiagnosticType.error("JSC_DUPLICATE_EXTERN_INPUT",
          "Duplicate extern input: {0}");
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[102]++;
  }

  /**
   * Creates a map to make looking up an input by name fast. Also checks for
   * duplicate inputs.
   */
  void initInputsByIdMap() {
    inputsById = new HashMap<InputId, CompilerInput>();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[103]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[104]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[16]++;


    for (CompilerInput input : externs) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[16]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[17]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[18]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[105]++;
      InputId id = input.getInputId();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[106]++;
      CompilerInput previous = putCompilerInput(id, input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[107]++;
int CodeCoverConditionCoverageHelper_C14;
      if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((previous != null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[29]++;
        report(JSError.make(DUPLICATE_EXTERN_INPUT, input.getName()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[108]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[30]++;}
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[109]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[19]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[19]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[20]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[21]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[110]++;
      InputId id = input.getInputId();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[111]++;
      CompilerInput previous = putCompilerInput(id, input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[112]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((previous != null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[31]++;
        report(JSError.make(DUPLICATE_INPUT, input.getName()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[113]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[32]++;}
    }
  }

  public Result compile(
      SourceFile extern, SourceFile input, CompilerOptions options) {
     return compile(Lists.newArrayList(extern), Lists.newArrayList(input), options);
  }

  /**
   * @deprecated Convert your arrays to lists and use the list-based API.
   */
  @Deprecated
  public Result compile(
      SourceFile extern, JSSourceFile[] input, CompilerOptions options) {
     return compile(Lists.newArrayList(extern), Lists.newArrayList(input), options);
  }

  /**
   * @deprecated Convert your arrays to lists and use the list-based
   *     compileModules method.
   */
  @Deprecated
  public Result compile(
      JSSourceFile extern, JSModule[] modules, CompilerOptions options) {
     return compileModules(
         Lists.newArrayList(extern), Lists.newArrayList(modules), options);
  }

  /**
   * Compiles a list of inputs.
   * @deprecated Convert your arrays to lists and use the list-based compile
   *     method.
   */
  @Deprecated
  public Result compile(JSSourceFile[] externs,
                        JSSourceFile[] inputs,
                        CompilerOptions options) {
    return compile(Lists.<SourceFile>newArrayList(externs),
        Lists.<SourceFile>newArrayList(inputs),
        options);
  }

  /**
   * Compiles a list of inputs.
   */
  public <T1 extends SourceFile, T2 extends SourceFile> Result compile(
      List<T1> externs, List<T2> inputs, CompilerOptions options) {
    // The compile method should only be called once.
    Preconditions.checkState(jsRoot == null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[114]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[115]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

    try {
CodeCoverTryBranchHelper_Try2 = true;
      init(externs, inputs, options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[116]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[117]++;
int CodeCoverConditionCoverageHelper_C16;
      if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[34]++;
        return getResult();

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[35]++;}
      return compile();
    } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[33]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[118]++;
      Tracer t = newTracer("generateReport");
      errorManager.generateReport();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[119]++;
      stopTracer(t, "generateReport");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[120]++;
    }
  }

  /**
   * Compiles a list of modules.
   * @deprecated Convert your arrays to lists and use the list-based
   *     compileModules method.
   */
  @Deprecated
  public Result compile(JSSourceFile[] externs,
                        JSModule[] modules,
                        CompilerOptions options) {
    return compileModules(Lists.<SourceFile>newArrayList(externs),
        Lists.<JSModule>newArrayList(modules),
        options);
  }

  /**
   * Compiles a list of modules.
   */
  public <T extends SourceFile> Result compileModules(List<T> externs,
      List<JSModule> modules, CompilerOptions options) {
    // The compile method should only be called once.
    Preconditions.checkState(jsRoot == null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[121]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[122]++;
boolean CodeCoverTryBranchHelper_Try3 = false;

    try {
CodeCoverTryBranchHelper_Try3 = true;
      initModules(externs, modules, options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[123]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[124]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[37]++;
        return getResult();

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[38]++;}
      return compile();
    } finally {
if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[36]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[125]++;
      Tracer t = newTracer("generateReport");
      errorManager.generateReport();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[126]++;
      stopTracer(t, "generateReport");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[127]++;
    }
  }

  private Result compile() {
    return runInCompilerThread(new Callable<Result>() {
      @Override
      public Result call() throws Exception {
        compileInternal();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[128]++;
        return getResult();
      }
    });
  }

  /**
   * Disable threads. This is for clients that run on AppEngine and
   * don't have threads.
   */
  public void disableThreads() {
    useThreads = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[129]++;
  }

  @SuppressWarnings("unchecked")
  <T> T runInCompilerThread(final Callable<T> callable) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[130]++;
    final boolean dumpTraceReport = options != null && options.tracer.isOn();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[131]++;
    T result = null;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[132]++;
    final Throwable[] exception = new Throwable[1];
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[133]++;
    Callable<T> bootCompilerThread = new Callable<T>() {
      @Override
      public T call() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[134]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
        try {
CodeCoverTryBranchHelper_Try4 = true;
          compilerThread = Thread.currentThread();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[135]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[136]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((dumpTraceReport) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[40]++;
            Tracer.initCurrentThreadTrace();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[137]++;

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[41]++;}
          return callable.call();
        } catch (Throwable e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[42]++;
          exception[0] = e;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[138]++;
        } finally {
if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[39]++;
}
          compilerThread = null;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[139]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[140]++;
int CodeCoverConditionCoverageHelper_C19;
          if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((dumpTraceReport) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[43]++;
            Tracer.logAndClearCurrentThreadTrace();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[141]++;
            tracker.outputTracerReport(outStream == null ?
                System.out : outStream);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[142]++;

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[44]++;}
        }
        return null;
      }
    };

    Preconditions.checkState(
        compilerThread == null || compilerThread == Thread.currentThread(),
        "Please do not share the Compiler across threads");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[143]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[144]++;
int CodeCoverConditionCoverageHelper_C20;

    // If the compiler thread is available, use it.
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((useThreads) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((compilerThread == null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[45]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[145]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
      try {
CodeCoverTryBranchHelper_Try5 = true;
        result = compilerExecutor.submit(bootCompilerThread).get();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[146]++;
      } catch (InterruptedException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[48]++;
        throw Throwables.propagate(e);
      } catch (ExecutionException e) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[49]++;
        throw Throwables.propagate(e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[47]++;
}
  }

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[46]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[147]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
      try {
CodeCoverTryBranchHelper_Try6 = true;
        result = callable.call();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[148]++;
      } catch (Exception e) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[51]++;
        exception[0] = e;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[149]++;
      } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[50]++;
}
  }
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[150]++;
int CodeCoverConditionCoverageHelper_C21;

    // Pass on any exception caught by the runnable object.
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((exception[0] != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[52]++;
      throw new RuntimeException(exception[0]);

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[53]++;}

    return result;
  }

  private void compileInternal() {
    setProgress(0.0, null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[151]++;
    parse();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[152]++;
    // 15 percent of the work is assumed to be for parsing (based on some
    // minimal analysis on big JS projects, of course this depends on options)
    setProgress(0.15, "parse");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[153]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[154]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[54]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[55]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[155]++;
int CodeCoverConditionCoverageHelper_C23;

    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((precheck()) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[56]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[57]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[156]++;
int CodeCoverConditionCoverageHelper_C24;

    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((options.nameAnonymousFunctionsOnly) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[58]++;
      // TODO(nicksantos): Move this into an instrument() phase maybe?
      check();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[157]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[59]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[158]++;
int CodeCoverConditionCoverageHelper_C25;

    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((options.skipAllPasses) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[60]++;
      check();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[159]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[160]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[62]++;
        return;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[63]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[161]++;
int CodeCoverConditionCoverageHelper_C27;

      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 ((options.isExternExportsEnabled()) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((options.externExportsPath != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[64]++;
        externExports();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[162]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[65]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[163]++;
int CodeCoverConditionCoverageHelper_C28;

      // IDE-mode is defined to stop here, before the heavy rewriting begins.
      if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((options.ideMode) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[66]++;
        optimize();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[164]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[67]++;}

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[61]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[165]++;
int CodeCoverConditionCoverageHelper_C29;

    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((options.recordFunctionInformation) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[68]++;
      recordFunctionInformation();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[166]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[69]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[167]++;
int CodeCoverConditionCoverageHelper_C30;

    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((options.devMode == DevMode.START_AND_END) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[70]++;
      runSanityCheck();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[168]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[71]++;}
    setProgress(1.0, "recordFunctionInformation");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[169]++;
  }

  public void parse() {
    parseInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[170]++;
  }

  PassConfig getPassConfig() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[171]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((passes == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[72]++;
      passes = createPassConfigInternal();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[172]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[73]++;}
    return passes;
  }

  /**
   * Create the passes object. Clients should use setPassConfig instead of
   * overriding this.
   */
  PassConfig createPassConfigInternal() {
    return new DefaultPassConfig(options);
  }

  /**
   * @param passes The PassConfig to use with this Compiler.
   * @throws NullPointerException if passes is null
   * @throws IllegalStateException if this.passes has already been assigned
   */
  public void setPassConfig(PassConfig passes) {
    // Important to check for null because if setPassConfig(null) is
    // called before this.passes is set, getPassConfig() will create a
    // new PassConfig object and use that, which is probably not what
    // the client wanted since he or she probably meant to use their
    // own PassConfig object.
    Preconditions.checkNotNull(passes);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[173]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[174]++;
int CodeCoverConditionCoverageHelper_C32;

    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((this.passes != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[74]++;
      throw new IllegalStateException("this.passes has already been assigned");

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[75]++;}
    this.passes = passes;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[175]++;
  }

  /**
   * Carry out any special checks or procedures that need to be done before
   * proceeding with rest of the compilation process.
   *
   * @return true, to continue with compilation
   */
  boolean precheck() {
    return true;
  }

  public void check() {
    runCustomPasses(CustomPassExecutionTime.BEFORE_CHECKS);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[176]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[177]++;

    // We are currently only interested in check-passes for progress reporting
    // as it is used for IDEs, that's why the maximum progress is set to 1.0.
    PhaseOptimizer phaseOptimizer = new PhaseOptimizer(this, tracker,
        new PhaseOptimizer.ProgressRange(getProgress(), 1.0));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[178]++;
int CodeCoverConditionCoverageHelper_C33;
    if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((options.devMode == DevMode.EVERY_PASS) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[76]++;
      phaseOptimizer.setSanityCheck(sanityCheck);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[179]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[77]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[180]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((options.getCheckDeterminism()) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[78]++;
      phaseOptimizer.setPrintAstHashcodes(true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[181]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[79]++;}
    phaseOptimizer.consume(getPassConfig().getChecks());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[182]++;
    phaseOptimizer.process(externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[183]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[184]++;
int CodeCoverConditionCoverageHelper_C35;
    if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[80]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[81]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[185]++;
int CodeCoverConditionCoverageHelper_C36;

    // TODO(nicksantos): clean this up. The flow here is too hard to follow.
    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((options.nameAnonymousFunctionsOnly) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[82]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[83]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[186]++;
int CodeCoverConditionCoverageHelper_C37;

    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((options.removeTryCatchFinally) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[84]++;
      removeTryCatchFinally();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[187]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[85]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[188]++;
int CodeCoverConditionCoverageHelper_C38;

    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (512)) == 0 || true) &&
 ((options.getTweakProcessing().shouldStrip()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (256)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C38 |= (128)) == 0 || true) &&
 ((options.stripTypes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (64)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C38 |= (32)) == 0 || true) &&
 ((options.stripNameSuffixes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (16)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((options.stripTypePrefixes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((options.stripNamePrefixes.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 5) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 5) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[86]++;
      stripCode(options.stripTypes, options.stripNameSuffixes,
          options.stripTypePrefixes, options.stripNamePrefixes);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[189]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[87]++;}

    runCustomPasses(CustomPassExecutionTime.BEFORE_OPTIMIZATIONS);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[190]++;
  }

  private void externExports() {
    logger.fine("Creating extern file for exports");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[191]++;
    startPass("externExports");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[192]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[193]++;

    ExternExportsPass pass = new ExternExportsPass(this);
    process(pass);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[194]++;

    externExports = pass.getGeneratedExterns();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[195]++;

    endPass();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[196]++;
  }

  @Override
  void process(CompilerPass p) {
    p.process(externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[197]++;
  }

  private final PassFactory sanityCheck =
      new PassFactory("sanityCheck", false) {
    @Override
    protected CompilerPass create(AbstractCompiler compiler) {
      return new SanityCheck(compiler);
    }
  };
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[198]++;
  }

  private void maybeSanityCheck() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[199]++;
int CodeCoverConditionCoverageHelper_C39;
    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((options.devMode == DevMode.EVERY_PASS) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[88]++;
      runSanityCheck();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[200]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[89]++;}
  }

  private void runSanityCheck() {
    sanityCheck.create(this).process(externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[201]++;
  }

  /**
   * Removes try/catch/finally statements for easier debugging.
   */
  void removeTryCatchFinally() {
    logger.fine("Remove try/catch/finally");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[202]++;
    startPass("removeTryCatchFinally");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[203]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[204]++;
    RemoveTryCatch r = new RemoveTryCatch(this);
    process(r);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[205]++;
    endPass();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[206]++;
  }

  /**
   * Strips code for smaller compiled code. This is useful for removing debug
   * statements to prevent leaking them publicly.
   */
  void stripCode(Set<String> stripTypes, Set<String> stripNameSuffixes,
      Set<String> stripTypePrefixes, Set<String> stripNamePrefixes) {
    logger.fine("Strip code");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[207]++;
    startPass("stripCode");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[208]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[209]++;
    StripCode r = new StripCode(this, stripTypes, stripNameSuffixes,
        stripTypePrefixes, stripNamePrefixes);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[210]++;
int CodeCoverConditionCoverageHelper_C40;
    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((options.getTweakProcessing().shouldStrip()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[90]++;
      r.enableTweakStripping();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[211]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[91]++;}
    process(r);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[212]++;
    endPass();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[213]++;
  }

  /**
   * Runs custom passes that are designated to run at a particular time.
   */
  private void runCustomPasses(CustomPassExecutionTime executionTime) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[214]++;
int CodeCoverConditionCoverageHelper_C41;
    if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((options.customPasses != null) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[92]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[215]++;
      Tracer t = newTracer("runCustomPasses");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[216]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
      try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[217]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[22]++;


        for (CompilerPass p : options.customPasses.get(executionTime)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[22]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[23]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[24]++;
}
          process(p);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[218]++;
        }
      } finally {
if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[94]++;
}
        stopTracer(t, "runCustomPasses");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[219]++;
      }

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[93]++;}
  }

  private Tracer currentTracer = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[220]++;
  }
  private String currentPassName = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[221]++;
  }

  /**
   * Marks the beginning of a pass.
   */
  void startPass(String passName) {
    Preconditions.checkState(currentTracer == null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[222]++;
    currentPassName = passName;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[223]++;
    currentTracer = newTracer(passName);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[224]++;
  }

  /**
   * Marks the end of a pass.
   */
  void endPass() {
    Preconditions.checkState(currentTracer != null,
        "Tracer should not be null at the end of a pass.");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[225]++;
    stopTracer(currentTracer, currentPassName);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[226]++;
    currentPassName = null;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[227]++;
    currentTracer = null;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[228]++;

    maybeSanityCheck();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[229]++;
  }

  /**
   * Returns a new tracer for the given pass name.
   */
  Tracer newTracer(String passName) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[230]++;
    String comment = passName
        + (recentChange.hasCodeChanged() ? " on recently changed AST" : "");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[231]++;
int CodeCoverConditionCoverageHelper_C42;
    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((options.tracer.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[95]++;
      tracker.recordPassStart(passName, true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[232]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[96]++;}
    return new Tracer("Compiler", comment);
  }

  void stopTracer(Tracer t, String passName) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[233]++;
    long result = t.stop();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[234]++;
int CodeCoverConditionCoverageHelper_C43;
    if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((options.tracer.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[97]++;
      tracker.recordPassStop(passName, result);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[235]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[98]++;}
  }

  /**
   * Returns the result of the compilation.
   */
  public Result getResult() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[236]++;
    PassConfig.State state = getPassConfig().getIntermediateState();
    return new Result(getErrors(), getWarnings(), debugLog.toString(),
        state.variableMap, state.propertyMap,
        state.anonymousFunctionNameMap, state.stringMap, functionInformationMap,
        sourceMap, externExports, state.cssNames, state.idGeneratorMap);
  }

  /**
   * Returns an array constructed from errors + temporary warnings.
   */
  public JSError[] getMessages() {
    return getErrors();
  }

  /**
   * Returns the array of errors (never null).
   */
  public JSError[] getErrors() {
    return errorManager.getErrors();
  }

  /**
   * Returns the array of warnings (never null).
   */
  public JSError[] getWarnings() {
    return errorManager.getWarnings();
  }

  @Override
  public Node getRoot() {
    return externAndJsRoot;
  }

  /**
   * Creates a new id for making unique names.
   */
  private int nextUniqueNameId() {
    return uniqueNameId++;
  }

  /**
   * Resets the unique name id counter
   */
  @VisibleForTesting
  void resetUniqueNameId() {
    uniqueNameId = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[237]++;
  }

  @Override
  Supplier<String> getUniqueNameIdSupplier() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[238]++;
    final Compiler self = this;
    return new Supplier<String>() {
      @Override
      public String get() {
        return String.valueOf(self.nextUniqueNameId());
      }
    };
  }

  @Override
  boolean areNodesEqualForInlining(Node n1, Node n2) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[239]++;
int CodeCoverConditionCoverageHelper_C44;
    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((options.ambiguateProperties) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((options.disambiguateProperties) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[99]++;
      // The type based optimizations require that type information is preserved
      // during other optimizations.
      return n1.isEquivalentToTyped(n2);

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[100]++;
      return n1.isEquivalentTo(n2);
    }
  }

  //------------------------------------------------------------------------
  // Inputs
  //------------------------------------------------------------------------

  // TODO(nicksantos): Decide which parts of these belong in an AbstractCompiler
  // interface, and which ones should always be injected.

  @Override
  public CompilerInput getInput(InputId id) {
    return inputsById.get(id);
  }

  /**
   * Removes an input file from AST.
   * @param id The id of the input to be removed.
   */
  protected void removeExternInput(InputId id) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[240]++;
    CompilerInput input = getInput(id);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[241]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((input == null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[101]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[102]++;}
    Preconditions.checkState(input.isExtern(), "Not an extern input: %s", input.getName());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[242]++;
    inputsById.remove(id);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[243]++;
    externs.remove(input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[244]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[245]++;
    Node root = input.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[246]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((root != null) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[103]++;
      root.detachFromParent();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[247]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[104]++;}
  }

  @Override
  public CompilerInput newExternInput(String name) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[248]++;
    SourceAst ast = new SyntheticAst(name);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[249]++;
int CodeCoverConditionCoverageHelper_C47;
    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((inputsById.containsKey(ast.getInputId())) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[105]++;
      throw new IllegalArgumentException("Conflicting externs name: " + name);

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[106]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[250]++;
    CompilerInput input = new CompilerInput(ast, true);
    putCompilerInput(input.getInputId(), input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[251]++;
    externsRoot.addChildToFront(ast.getAstRoot(this));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[252]++;
    externs.add(0, input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[253]++;
    return input;
  }

  private CompilerInput putCompilerInput(InputId id, CompilerInput input) {
    input.setCompiler(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[254]++;
    return inputsById.put(id, input);
  }

  /** Add a source input dynamically. Intended for incremental compilation. */
  void addIncrementalSourceAst(JsAst ast) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[255]++;
    InputId id = ast.getInputId();
    Preconditions.checkState(getInput(id) == null, "Duplicate input %s", id.getIdName());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[256]++;
    putCompilerInput(id, new CompilerInput(ast));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[257]++;
  }

  /**
   * Replace a source input dynamically. Intended for incremental
   * re-compilation.
   *
   * If the new source input doesn't parse, then keep the old input
   * in the AST and return false.
   *
   * @return Whether the new AST was attached successfully.
   */
  boolean replaceIncrementalSourceAst(JsAst ast) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[258]++;
    CompilerInput oldInput = getInput(ast.getInputId());
    Preconditions.checkNotNull(oldInput, "No input to replace: %s", ast.getInputId().getIdName());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[259]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[260]++;
    Node newRoot = ast.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[261]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((newRoot == null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[107]++;
      return false;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[108]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[262]++;

    Node oldRoot = oldInput.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[263]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((oldRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[109]++;
      oldRoot.getParent().replaceChild(oldRoot, newRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[264]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[110]++;
      getRoot().getLastChild().addChildToBack(newRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[265]++;
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[266]++;

    CompilerInput newInput = new CompilerInput(ast);
    putCompilerInput(ast.getInputId(), newInput);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[267]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[268]++;

    JSModule module = oldInput.getModule();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[269]++;
int CodeCoverConditionCoverageHelper_C50;
    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((module != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[111]++;
      module.addAfter(newInput, oldInput);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[270]++;
      module.remove(oldInput);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[271]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[112]++;}

    // Verify the input id is set properly.
    Preconditions.checkState(
        newInput.getInputId().equals(oldInput.getInputId()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[272]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[273]++;
    InputId inputIdOnAst = newInput.getAstRoot(this).getInputId();
    Preconditions.checkState(newInput.getInputId().equals(inputIdOnAst));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[274]++;

    inputs.remove(oldInput);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[275]++;
    return true;
  }

  /**
   * Add a new source input dynamically. Intended for incremental compilation.
   * <p>
   * If the new source input doesn't parse, it will not be added, and a false
   * will be returned.
   *
   * @param ast the JS Source to add.
   * @return true if the source was added successfully, false otherwise.
   * @throws IllegalStateException if an input for this ast already exists.
   */
  boolean addNewSourceAst(JsAst ast) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[276]++;
    CompilerInput oldInput = getInput(ast.getInputId());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[277]++;
int CodeCoverConditionCoverageHelper_C51;
    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((oldInput != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[113]++;
      throw new IllegalStateException(
          "Input already exists: " + ast.getInputId().getIdName());

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[114]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[278]++;
    Node newRoot = ast.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[279]++;
int CodeCoverConditionCoverageHelper_C52;
    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((newRoot == null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[115]++;
      return false;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[116]++;}

    getRoot().getLastChild().addChildToBack(newRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[280]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[281]++;

    CompilerInput newInput = new CompilerInput(ast);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[282]++;
int CodeCoverConditionCoverageHelper_C53;

    // TODO(tylerg): handle this for multiple modules at some point.
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((moduleGraph == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((modules.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[117]++;
      // singleton module
      modules.get(0).add(newInput);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[283]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[118]++;}

    putCompilerInput(ast.getInputId(), newInput);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[284]++;

    return true;
  }

  @Override
  JSModuleGraph getModuleGraph() {
    return moduleGraph;
  }

  /**
   * Gets a module graph. This will always return a module graph, even
   * in the degenerate case when there's only one module.
   */
  JSModuleGraph getDegenerateModuleGraph() {
    return moduleGraph == null ? new JSModuleGraph(modules) : moduleGraph;
  }

  @Override
  public JSTypeRegistry getTypeRegistry() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[285]++;
int CodeCoverConditionCoverageHelper_C54;
    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((typeRegistry == null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[119]++;
      typeRegistry = new JSTypeRegistry(oldErrorReporter, options.looseTypes);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[286]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[120]++;}
    return typeRegistry;
  }

  @Override
  public MemoizedScopeCreator getTypedScopeCreator() {
    return getPassConfig().getTypedScopeCreator();
  }

  @SuppressWarnings("unchecked")
  DefaultPassConfig ensureDefaultPassConfig() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[287]++;
    PassConfig passes = getPassConfig().getBasePassConfig();
    Preconditions.checkState(passes instanceof DefaultPassConfig,
        "PassConfigs must eventually delegate to the DefaultPassConfig");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[288]++;
    return (DefaultPassConfig) passes;
  }

  public SymbolTable buildKnownSymbolTable() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[289]++;
    SymbolTable symbolTable = new SymbolTable(getTypeRegistry());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[290]++;

    MemoizedScopeCreator typedScopeCreator = getTypedScopeCreator();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[291]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((typedScopeCreator != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[121]++;
      symbolTable.addScopes(typedScopeCreator.getAllMemoizedScopes());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[292]++;
      symbolTable.addSymbolsFrom(typedScopeCreator);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[293]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[122]++;
      symbolTable.findScopes(this, externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[294]++;
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[295]++;

    GlobalNamespace globalNamespace =
        ensureDefaultPassConfig().getGlobalNamespace();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[296]++;
int CodeCoverConditionCoverageHelper_C56;
    if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((globalNamespace != null) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[123]++;
      symbolTable.addSymbolsFrom(globalNamespace);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[297]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[124]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[298]++;

    ReferenceCollectingCallback refCollector =
        new ReferenceCollectingCallback(
            this, ReferenceCollectingCallback.DO_NOTHING_BEHAVIOR);
    NodeTraversal.traverse(this, getRoot(), refCollector);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[299]++;
    symbolTable.addSymbolsFrom(refCollector);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[300]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[301]++;

    PreprocessorSymbolTable preprocessorSymbolTable =
        ensureDefaultPassConfig().getPreprocessorSymbolTable();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[302]++;
int CodeCoverConditionCoverageHelper_C57;
    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((preprocessorSymbolTable != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[125]++;
      symbolTable.addSymbolsFrom(preprocessorSymbolTable);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[303]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[126]++;}

    symbolTable.fillNamespaceReferences();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[304]++;
    symbolTable.fillPropertyScopes();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[305]++;
    symbolTable.fillThisReferences(this, externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[306]++;
    symbolTable.fillPropertySymbols(this, externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[307]++;
    symbolTable.fillJSDocInfo(this, externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[308]++;

    return symbolTable;
  }

  @Override
  public Scope getTopScope() {
    return getPassConfig().getTopScope();
  }

  @Override
  public ReverseAbstractInterpreter getReverseAbstractInterpreter() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[309]++;
int CodeCoverConditionCoverageHelper_C58;
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((abstractInterpreter == null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[127]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[310]++;
      ChainableReverseAbstractInterpreter interpreter =
          new SemanticReverseAbstractInterpreter(
              getCodingConvention(), getTypeRegistry());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[311]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((options.closurePass) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[129]++;
        interpreter = new ClosureReverseAbstractInterpreter(
            getCodingConvention(), getTypeRegistry())
            .append(interpreter).getFirst();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[312]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[130]++;}
      abstractInterpreter = interpreter;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[313]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[128]++;}
    return abstractInterpreter;
  }

  @Override
  TypeValidator getTypeValidator() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[314]++;
int CodeCoverConditionCoverageHelper_C60;
    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((typeValidator == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[131]++;
      typeValidator = new TypeValidator(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[315]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[132]++;}
    return typeValidator;
  }

  //------------------------------------------------------------------------
  // Parsing
  //------------------------------------------------------------------------

  /**
   * Parses the externs and main inputs.
   *
   * @return A synthetic root node whose two children are the externs root
   *     and the main root
   */
  Node parseInputs() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[316]++;
    boolean devMode = options.devMode != DevMode.OFF;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[317]++;
int CodeCoverConditionCoverageHelper_C61;

    // If old roots exist (we are parsing a second time), detach each of the
    // individual file parse trees.
    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((externsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[133]++;
      externsRoot.detachChildren();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[318]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[134]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[319]++;
int CodeCoverConditionCoverageHelper_C62;
    if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((jsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[135]++;
      jsRoot.detachChildren();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[320]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[136]++;}

    // Parse main JS sources.
    jsRoot = IR.block();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[321]++;
    jsRoot.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[322]++;

    externsRoot = IR.block();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[323]++;
    externsRoot.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[324]++;

    externAndJsRoot = IR.block(externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[325]++;
    externAndJsRoot.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[326]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[327]++;
int CodeCoverConditionCoverageHelper_C63;

    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((options.tracer.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[137]++;
      tracker = new PerformanceTracker(jsRoot, options.tracer);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[328]++;
      addChangeHandler(tracker.getCodeChangeHandler());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[329]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[138]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[330]++;

    Tracer tracer = newTracer(PARSING_PASS_NAME);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[331]++;
boolean CodeCoverTryBranchHelper_Try8 = false;

    try {
CodeCoverTryBranchHelper_Try8 = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[332]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[25]++;


      // Parse externs sources.
      for (CompilerInput input : externs) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[25]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[26]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[27]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[333]++;
        Node n = input.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[334]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[140]++;
          return null;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[141]++;}
        externsRoot.addChildToBack(n);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[335]++;
      }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[336]++;
int CodeCoverConditionCoverageHelper_C65;

      // Modules inferred in ProcessCommonJS pass.
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((options.transformAMDToCJSModules) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((options.processCommonJSModules) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[142]++;
        processAMDAndCommonJSModules();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[337]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[143]++;}

      hoistExterns(externsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[338]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[339]++;

      // Check if the sources need to be re-ordered.
      boolean staleInputs = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[340]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((options.dependencyOptions.needsManagement()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[144]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[341]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[28]++;


        for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[28]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[29]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[30]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[342]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[31]++;


          // Forward-declare all the provided types, so that they
          // are not flagged even if they are dropped from the process.
          for (String provide : input.getProvides()) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[31]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[32]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[33]++;
}
            getTypeRegistry().forwardDeclareType(provide);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[343]++;
          }
        }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[344]++;
boolean CodeCoverTryBranchHelper_Try9 = false;

        try {
CodeCoverTryBranchHelper_Try9 = true;
          inputs =
              (moduleGraph == null ? new JSModuleGraph(modules) : moduleGraph)
              .manageDependencies(options.dependencyOptions, inputs);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[345]++;
          staleInputs = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[346]++;
        } catch (CircularDependencyException e) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[147]++;
          report(JSError.make(
              JSModule.CIRCULAR_DEPENDENCY_ERROR, e.getMessage()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[347]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[348]++;
int CodeCoverConditionCoverageHelper_C67;

          // If in IDE mode, we ignore the error and keep going.
          if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[148]++;
            return null;

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[149]++;}
        } catch (MissingProvideException e) {
CodeCoverTryBranchHelper_Try9 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[150]++;
          report(JSError.make(
              MISSING_ENTRY_ERROR, e.getMessage()));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[349]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[350]++;
int CodeCoverConditionCoverageHelper_C68;

          // If in IDE mode, we ignore the error and keep going.
          if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[151]++;
            return null;

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[152]++;}
        } finally {
    if ( CodeCoverTryBranchHelper_Try9 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[146]++;
}
  }

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[145]++;}

      hoistNoCompileFiles();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[351]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[352]++;
int CodeCoverConditionCoverageHelper_C69;

      if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((staleInputs) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[153]++;
        repartitionInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[353]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[154]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[354]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[34]++;



      // Build the AST.
      for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[34]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[35]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[36]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[355]++;
        Node n = input.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[356]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[155]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[357]++;
          continue;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[156]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[358]++;
int CodeCoverConditionCoverageHelper_C71;

        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((devMode) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[157]++;
          runSanityCheck();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[359]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[360]++;
int CodeCoverConditionCoverageHelper_C72;
          if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[159]++;
            return null;

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[160]++;}

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[158]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[361]++;
int CodeCoverConditionCoverageHelper_C73;

        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (8)) == 0 || true) &&
 ((options.sourceMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((options.nameReferenceReportPath != null) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[161]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[362]++;

          // Annotate the nodes in the tree with information from the
          // input file. This information is used to construct the SourceMap.
          SourceInformationAnnotator sia =
              new SourceInformationAnnotator(
                  input.getName(), options.devMode != DevMode.OFF);
          NodeTraversal.traverse(this, n, sia);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[363]++;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[162]++;}

        jsRoot.addChildToBack(n);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[364]++;
      }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[365]++;
int CodeCoverConditionCoverageHelper_C74;

      if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((hasErrors()) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[163]++;
        return null;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[164]++;}
      return externAndJsRoot;
    } finally {
if ( CodeCoverTryBranchHelper_Try8 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[139]++;
}
      stopTracer(tracer, PARSING_PASS_NAME);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[366]++;
    }
  }

  /**
   * Hoists inputs with the @externs annotation into the externs list.
   */
  private void hoistExterns(Node externsRoot) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[367]++;
    boolean staleInputs = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[368]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[37]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[37]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[38]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[39]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[369]++;
int CodeCoverConditionCoverageHelper_C75;
      if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((options.dependencyOptions.needsManagement()) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[165]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[370]++;
int CodeCoverConditionCoverageHelper_C76;
        // If we're doing scanning dependency info anyway, use that
        // information to skip sources that obviously aren't externs.
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (8)) == 0 || true) &&
 ((input.getProvides().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((input.getRequires().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[167]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[371]++;
          continue;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[168]++;}

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[166]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[372]++;

      Node n = input.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[373]++;
int CodeCoverConditionCoverageHelper_C77;

      // Inputs can have a null AST on a parse error.
      if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[169]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[374]++;
        continue;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[170]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[375]++;

      JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[376]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((info.isExterns()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[171]++;
        // If the input file is explicitly marked as an externs file, then
        // assume the programmer made a mistake and throw it into
        // the externs pile anyways.
        externsRoot.addChildToBack(n);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[377]++;
        input.setIsExtern(true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[378]++;

        input.getModule().remove(input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[379]++;

        externs.add(input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[380]++;
        staleInputs = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[381]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[172]++;}
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[382]++;
int CodeCoverConditionCoverageHelper_C79;

    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((staleInputs) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[173]++;
      repartitionInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[383]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[174]++;}
  }

  /**
   * Hoists inputs with the @nocompiler annotation out of the inputs.
   */
  private void hoistNoCompileFiles() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[384]++;
    boolean staleInputs = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[385]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[40]++;


    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[40]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[41]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[42]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[386]++;
      Node n = input.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[387]++;
int CodeCoverConditionCoverageHelper_C80;

      // Inputs can have a null AST on a parse error.
      if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((n == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[175]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[388]++;
        continue;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[176]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[389]++;

      JSDocInfo info = n.getJSDocInfo();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[390]++;
int CodeCoverConditionCoverageHelper_C81;
      if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (8)) == 0 || true) &&
 ((info != null) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((info.isNoCompile()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[177]++;
        input.getModule().remove(input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[391]++;
        staleInputs = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[392]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[178]++;}
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[393]++;
int CodeCoverConditionCoverageHelper_C82;

    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((staleInputs) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[179]++;
      repartitionInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[394]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[180]++;}
  }

  private void repartitionInputs() {
    fillEmptyModules(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[395]++;
    rebuildInputsFromModules();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[396]++;
  }

  /**
   * Transforms AMD and CJS modules to something closure compiler can
   * process and creates JSModules and the corresponding dependency tree
   * on the way.
   */
  void processAMDAndCommonJSModules() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[397]++;
    Map<String, JSModule> modulesByName = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[398]++;
    Map<CompilerInput, JSModule> modulesByInput = Maps.newLinkedHashMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[399]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[43]++;


    // TODO(nicksantos): Refactor module dependency resolution to work nicely
    // with multiple ways to express dependencies. Directly support JSModules
    // that are equivalent to a signal file and which express their deps
    // directly in the source.
    for (CompilerInput input : inputs) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[43]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[44]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[45]++;
}
      input.setCompiler(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[400]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[401]++;
      Node root = input.getAstRoot(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[402]++;
int CodeCoverConditionCoverageHelper_C83;
      if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((root == null) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[181]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[403]++;
        continue;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[182]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[404]++;
int CodeCoverConditionCoverageHelper_C84;
      if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((options.transformAMDToCJSModules) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[183]++;
        new TransformAMDToCJSModule(this).process(null, root);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[405]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[184]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[406]++;
int CodeCoverConditionCoverageHelper_C85;
      if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((options.processCommonJSModules) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[185]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[407]++;
        ProcessCommonJSModules cjs = new ProcessCommonJSModules(this,
            options.commonJSModulePathPrefix);
        cjs.process(null, root);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[408]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[409]++;
        JSModule m = cjs.getModule();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[410]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((m != null) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[187]++;
          modulesByName.put(m.getName(), m);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[411]++;
          modulesByInput.put(input, m);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[412]++;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[188]++;}

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[186]++;}
    }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[413]++;
int CodeCoverConditionCoverageHelper_C87;
    if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((options.processCommonJSModules) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[189]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[414]++;
      List<JSModule> modules = Lists.newArrayList(modulesByName.values());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[415]++;
int CodeCoverConditionCoverageHelper_C88;
      if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((modules.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[191]++;
        this.modules = modules;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[416]++;
        this.moduleGraph = new JSModuleGraph(this.modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[417]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[192]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[418]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[46]++;


      for (JSModule module : modules) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[46]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[47]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[48]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[419]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[49]++;


        for (CompilerInput input : module.getInputs()) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[49]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[50]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[51]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[420]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[52]++;


          for (String require : input.getRequires()) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[52]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[53]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[54]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[421]++;
            JSModule dependency = modulesByName.get(require);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[422]++;
int CodeCoverConditionCoverageHelper_C89;
            if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((dependency == null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[193]++;
              report(JSError.make(MISSING_ENTRY_ERROR, require));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[423]++;

            } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[194]++;
              module.addDependency(dependency);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[424]++;
            }
          }
        }
      }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[425]++;
boolean CodeCoverTryBranchHelper_Try10 = false;
      try {
CodeCoverTryBranchHelper_Try10 = true;
        modules = Lists.newArrayList();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[426]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[427]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[55]++;


        for (CompilerInput input : this.moduleGraph.manageDependencies(
            options.dependencyOptions, inputs)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[55]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[56]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[57]++;
}
          modules.add(modulesByInput.get(input));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[428]++;
        }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[429]++;
        JSModule root = new JSModule("root");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[430]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[58]++;


        for (JSModule m : modules) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[58]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[59]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[60]++;
}
          m.addDependency(root);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[431]++;
        }
        modules.add(0, root);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[432]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[433]++;
        SortedDependencies<JSModule> sorter =
          new SortedDependencies<JSModule>(modules);
        modules = sorter.getDependenciesOf(modules, true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[434]++;
        this.modules = modules;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[435]++;

        this.moduleGraph = new JSModuleGraph(modules);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[436]++;
      } catch (Exception e) {
CodeCoverTryBranchHelper_Try10 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[196]++;
        Throwables.propagate(e);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[437]++;
      } finally {
    if ( CodeCoverTryBranchHelper_Try10 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[195]++;
}
  }

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[190]++;}
  }

  public Node parse(SourceFile file) {
    initCompilerOptionsIfTesting();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[438]++;
    addToDebugLog("Parsing: " + file.getName());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[439]++;
    return new JsAst(file).getAstRoot(this);
  }

  private int syntheticCodeId = 0;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[440]++;
  }

  @Override
  Node parseSyntheticCode(String js) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[441]++;
    CompilerInput input = new CompilerInput(
        SourceFile.fromCode(" [synthetic:" + (++syntheticCodeId) + "] ", js));
    putCompilerInput(input.getInputId(), input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[442]++;
    return input.getAstRoot(this);
  }

  /**
   * Allow subclasses to override the default CompileOptions object.
   */
  protected CompilerOptions newCompilerOptions() {
    return new CompilerOptions();
  }

  void initCompilerOptionsIfTesting() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[443]++;
int CodeCoverConditionCoverageHelper_C90;
    if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((options == null) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[197]++;
      // initialization for tests that don't initialize the compiler
      // by the normal mechanisms.
      initOptions(newCompilerOptions());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[444]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[198]++;}
  }

  @Override
  Node parseSyntheticCode(String fileName, String js) {
    initCompilerOptionsIfTesting();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[445]++;
    return parse(SourceFile.fromCode(fileName, js));
  }

  @Override
  Node parseTestCode(String js) {
    initCompilerOptionsIfTesting();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[446]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[447]++;
    CompilerInput input = new CompilerInput(
        SourceFile.fromCode("[testcode]", js));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[448]++;
int CodeCoverConditionCoverageHelper_C91;
    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((inputsById == null) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[199]++;
      inputsById = Maps.newHashMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[449]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[200]++;}
    putCompilerInput(input.getInputId(), input);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[450]++;
    return input.getAstRoot(this);
  }

  @Override
  ErrorReporter getDefaultErrorReporter() {
    return defaultErrorReporter;
  }

  //------------------------------------------------------------------------
  // Convert back to source code
  //------------------------------------------------------------------------

  /**
   * Converts the main parse tree back to JS code.
   */
  public String toSource() {
    return runInCompilerThread(new Callable<String>() {
      @Override
      public String call() throws Exception {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[451]++;
        Tracer tracer = newTracer("toSource");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[452]++;
boolean CodeCoverTryBranchHelper_Try11 = false;
        try {
CodeCoverTryBranchHelper_Try11 = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[453]++;
          CodeBuilder cb = new CodeBuilder();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[454]++;
int CodeCoverConditionCoverageHelper_C92;
          if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((jsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[202]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[455]++;
            int i = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[456]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[61]++;


int CodeCoverConditionCoverageHelper_C93;
            for (Node scriptNode = jsRoot.getFirstChild();(((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((scriptNode != null) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false);
                 scriptNode = scriptNode.getNext()) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[61]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[62]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[63]++;
}
              toSource(cb, i++, scriptNode);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[457]++;
            }

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[203]++;}
          return cb.toString();
        } finally {
if ( CodeCoverTryBranchHelper_Try11 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[201]++;
}
          stopTracer(tracer, "toSource");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[458]++;
        }
      }
    });
  }

  /**
   * Converts the parse tree for each input back to JS code.
   */
  public String[] toSourceArray() {
    return runInCompilerThread(new Callable<String[]>() {
      @Override
      public String[] call() throws Exception {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[459]++;
        Tracer tracer = newTracer("toSourceArray");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[460]++;
boolean CodeCoverTryBranchHelper_Try12 = false;
        try {
CodeCoverTryBranchHelper_Try12 = true;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[461]++;
          int numInputs = inputs.size();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[462]++;
          String[] sources = new String[numInputs];
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[463]++;
          CodeBuilder cb = new CodeBuilder();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[464]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[64]++;


int CodeCoverConditionCoverageHelper_C94;
          for (int i = 0;(((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((i < numInputs) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[64]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[65]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[66]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[465]++;
            Node scriptNode = inputs.get(i).getAstRoot(Compiler.this);
            cb.reset();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[466]++;
            toSource(cb, i, scriptNode);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[467]++;
            sources[i] = cb.toString();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[468]++;
          }
          return sources;
        } finally {
if ( CodeCoverTryBranchHelper_Try12 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[204]++;
}
          stopTracer(tracer, "toSourceArray");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[469]++;
        }
      }
    });
  }

  /**
   * Converts the parse tree for a module back to JS code.
   */
  public String toSource(final JSModule module) {
    return runInCompilerThread(new Callable<String>() {
      @Override
      public String call() throws Exception {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[470]++;
        List<CompilerInput> inputs = module.getInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[471]++;
        int numInputs = inputs.size();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[472]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((numInputs == 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[205]++;
          return "";

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[206]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[473]++;
        CodeBuilder cb = new CodeBuilder();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[474]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[67]++;


int CodeCoverConditionCoverageHelper_C96;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((i < numInputs) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[67]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[68]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[69]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[475]++;
          Node scriptNode = inputs.get(i).getAstRoot(Compiler.this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[476]++;
int CodeCoverConditionCoverageHelper_C97;
          if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((scriptNode == null) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[207]++;
            throw new IllegalArgumentException(
                "Bad module: " + module.getName());

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[208]++;}
          toSource(cb, i, scriptNode);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[477]++;
        }
        return cb.toString();
      }
    });
  }


  /**
   * Converts the parse tree for each input in a module back to JS code.
   */
  public String[] toSourceArray(final JSModule module) {
    return runInCompilerThread(new Callable<String[]>() {
      @Override
      public String[] call() throws Exception {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[478]++;
        List<CompilerInput> inputs = module.getInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[479]++;
        int numInputs = inputs.size();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[480]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((numInputs == 0) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[209]++;
          return new String[0];

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[210]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[481]++;

        String[] sources = new String[numInputs];
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[482]++;
        CodeBuilder cb = new CodeBuilder();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[483]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[70]++;


int CodeCoverConditionCoverageHelper_C99;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((i < numInputs) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[70]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[71]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[72]++;
}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[484]++;
          Node scriptNode = inputs.get(i).getAstRoot(Compiler.this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[485]++;
int CodeCoverConditionCoverageHelper_C100;
          if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((scriptNode == null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[211]++;
            throw new IllegalArgumentException(
                "Bad module input: " + inputs.get(i).getName());

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[212]++;}

          cb.reset();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[486]++;
          toSource(cb, i, scriptNode);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[487]++;
          sources[i] = cb.toString();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[488]++;
        }
        return sources;
      }
    });
  }

  /**
   * Writes out JS code from a root node. If printing input delimiters, this
   * method will attach a comment to the start of the text indicating which
   * input the output derived from. If there were any preserve annotations
   * within the root's source, they will also be printed in a block comment
   * at the beginning of the output.
   */
  public void toSource(final CodeBuilder cb,
                       final int inputSeqNum,
                       final Node root) {
    runInCompilerThread(new Callable<Void>() {
      @Override
      public Void call() throws Exception {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[490]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((options.printInputDelimiter) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[213]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[491]++;
int CodeCoverConditionCoverageHelper_C102;
          if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C102 |= (8)) == 0 || true) &&
 ((cb.getLength() > 0) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (4)) == 0 || true)))
) && !
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((cb.endsWith("\n")) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[215]++;
            cb.append("\n");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[492]++;
  // Make sure that the label starts on a new line
          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[216]++;}
          Preconditions.checkState(root.isScript());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[493]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[494]++;

          String delimiter = options.inputDelimiter;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[495]++;

          String inputName = root.getInputId().getIdName();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[496]++;
          String sourceName = root.getSourceFileName();
          Preconditions.checkState(sourceName != null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[497]++;
          Preconditions.checkState(!sourceName.isEmpty());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[498]++;

          delimiter = delimiter
              .replaceAll("%name%", Matcher.quoteReplacement(inputName))
              .replaceAll("%num%", String.valueOf(inputSeqNum));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[499]++;

          cb.append(delimiter)
            .append("\n");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[500]++;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[214]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[501]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (8)) == 0 || true) &&
 ((root.getJSDocInfo() != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((root.getJSDocInfo().getLicense() != null) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 2) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[217]++;
          cb.append("/*\n")
            .append(root.getJSDocInfo().getLicense())
            .append("*/\n");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[502]++;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[218]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[503]++;
int CodeCoverConditionCoverageHelper_C104;

        // If there is a valid source map, then indicate to it that the current
        // root node's mappings are offset by the given string builder buffer.
        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((options.sourceMapOutputPath != null) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[219]++;
          sourceMap.setStartingPosition(
              cb.getLineIndex(), cb.getColumnIndex());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[504]++;

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[220]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[505]++;

        // if LanguageMode is ECMASCRIPT5_STRICT, only print 'use strict'
        // for the first input file
        String code = toSource(root, sourceMap, inputSeqNum == 0);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[506]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((code.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[221]++;
          cb.append(code);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[507]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[508]++;

          // In order to avoid parse ambiguity when files are concatenated
          // together, all files should end in a semi-colon. Do a quick
          // heuristic check if there's an obvious semi-colon already there.
          int length = code.length();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[509]++;
          char lastChar = code.charAt(length - 1);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[510]++;
          char secondLastChar = length >= 2 ?
              code.charAt(length - 2) : '\0';
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[511]++;
          boolean hasSemiColon = lastChar == ';' ||
              (lastChar == '\n' && secondLastChar == ';');
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[512]++;
int CodeCoverConditionCoverageHelper_C106;
          if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((hasSemiColon) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[223]++;
            cb.append(";");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[513]++;

          } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[224]++;}

        } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[222]++;}
        return null;
      }
    });
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[489]++;
  }

  /**
   * Generates JavaScript source code for an AST, doesn't generate source
   * map info.
   */
  @Override
  String toSource(Node n) {
    initCompilerOptionsIfTesting();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[514]++;
    return toSource(n, null, true);
  }

  /**
   * Generates JavaScript source code for an AST.
   */
  private String toSource(Node n, SourceMap sourceMap, boolean firstOutput) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[515]++;
    CodePrinter.Builder builder = new CodePrinter.Builder(n);
    builder.setCompilerOptions(options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[516]++;
    builder.setSourceMap(sourceMap);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[517]++;
    builder.setTagAsStrict(firstOutput &&
        options.getLanguageOut() == LanguageMode.ECMASCRIPT5_STRICT);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[518]++;
    return builder.build();
  }

  /**
   * Stores a buffer of text to which more can be appended.  This is just like a
   * StringBuilder except that we also track the number of lines.
   */
  public static class CodeBuilder {
    private final StringBuilder sb = new StringBuilder();
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[519]++;
  }
    private int lineCount = 0;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[520]++;
  }
    private int colCount = 0;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[521]++;
  }

    /** Removes all text, but leaves the line count unchanged. */
    void reset() {
      sb.setLength(0);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[522]++;
    }

    /** Appends the given string to the text buffer. */
    CodeBuilder append(String str) {
      sb.append(str);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[523]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[524]++;

      // Adjust the line and column information for the new text.
      int index = -1;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[525]++;
      int lastIndex = index;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[526]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[73]++;


      while ((index = str.indexOf('\n', index + 1)) >= 0) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[73]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[74]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[75]++;
}
        ++lineCount;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[527]++;
        lastIndex = index;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[528]++;
      }
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[529]++;
int CodeCoverConditionCoverageHelper_C108;

      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((lastIndex == -1) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[225]++;
        // No new lines, append the new characters added.
        colCount += str.length();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[530]++;

      } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[226]++;
        colCount = str.length() - (lastIndex + 1);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[531]++;
      }

      return this;
    }

    /** Returns all text in the text buffer. */
    @Override
    public String toString() {
      return sb.toString();
    }

    /** Returns the length of the text buffer. */
    public int getLength() {
      return sb.length();
    }

    /** Returns the (zero-based) index of the last line in the text buffer. */
    int getLineIndex() {
      return lineCount;
    }

    /** Returns the (zero-based) index of the last column in the text buffer. */
    int getColumnIndex() {
      return colCount;
    }

    /** Determines whether the text ends with the given suffix. */
    boolean endsWith(String suffix) {
      return (sb.length() > suffix.length())
          && suffix.equals(sb.substring(sb.length() - suffix.length()));
    }
  }

  //------------------------------------------------------------------------
  // Optimizations
  //------------------------------------------------------------------------

  public void optimize() {
    // Ideally, this pass should be the first pass run, however:
    // 1) VariableReferenceCheck reports unexpected warnings if Normalize
    // is done first.
    // 2) ReplaceMessages, stripCode, and potentially custom passes rely on
    // unmodified local names.
    normalize();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[532]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[533]++;

    PhaseOptimizer phaseOptimizer = new PhaseOptimizer(this, tracker, null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[534]++;
int CodeCoverConditionCoverageHelper_C109;
    if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((options.devMode == DevMode.EVERY_PASS) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[227]++;
      phaseOptimizer.setSanityCheck(sanityCheck);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[535]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[228]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[536]++;
int CodeCoverConditionCoverageHelper_C110;
    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((options.getCheckDeterminism()) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[229]++;
      phaseOptimizer.setPrintAstHashcodes(true);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[537]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[230]++;}
    phaseOptimizer.consume(getPassConfig().getOptimizations());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[538]++;
    phaseOptimizer.process(externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[539]++;
  }

  @Override
  void setCssRenamingMap(CssRenamingMap map) {
    options.cssRenamingMap = map;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[540]++;
  }

  @Override
  CssRenamingMap getCssRenamingMap() {
    return options.cssRenamingMap;
  }

  /**
   * Reprocesses the current defines over the AST.  This is used by GwtCompiler
   * to generate N outputs for different targets from the same (checked) AST.
   * For each target, we apply the target-specific defines by calling
   * {@code processDefines} and then {@code optimize} to optimize the AST
   * specifically for that target.
   */
  public void processDefines() {
    (new DefaultPassConfig(options)).processDefines.create(this)
        .process(externsRoot, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[541]++;
  }

  boolean isInliningForbidden() {
    return options.propertyRenaming == PropertyRenamingPolicy.HEURISTIC ||
        options.propertyRenaming ==
            PropertyRenamingPolicy.AGGRESSIVE_HEURISTIC;
  }

  /** Control Flow Analysis. */
  ControlFlowGraph<Node> computeCFG() {
    logger.fine("Computing Control Flow Graph");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[542]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[543]++;
    Tracer tracer = newTracer("computeCFG");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[544]++;
    ControlFlowAnalysis cfa = new ControlFlowAnalysis(this, true, false);
    process(cfa);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[545]++;
    stopTracer(tracer, "computeCFG");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[546]++;
    return cfa.getCfg();
  }

  public void normalize() {
    logger.fine("Normalizing");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[547]++;
    startPass("normalize");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[548]++;
    process(new Normalize(this, false));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[549]++;
    endPass();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[550]++;
  }

  @Override
  void prepareAst(Node root) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[551]++;
    CompilerPass pass = new PrepareAst(this);
    pass.process(null, root);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[552]++;
  }

  void recordFunctionInformation() {
    logger.fine("Recording function information");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[553]++;
    startPass("recordFunctionInformation");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[554]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[555]++;
    RecordFunctionInformation recordFunctionInfoPass =
        new RecordFunctionInformation(
            this, getPassConfig().getIntermediateState().functionNames);
    process(recordFunctionInfoPass);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[556]++;
    functionInformationMap = recordFunctionInfoPass.getMap();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[557]++;
    endPass();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[558]++;
  }

  protected final CodeChangeHandler.RecentChange recentChange =
      new CodeChangeHandler.RecentChange();
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[559]++;
  }
  private final List<CodeChangeHandler> codeChangeHandlers =
      Lists.<CodeChangeHandler>newArrayList();
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[560]++;
  }

  /** Name of the synthetic input that holds synthesized externs. */
  static final String SYNTHETIC_EXTERNS = "{SyntheticVarsDeclar}";
  static {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[561]++;
  }

  private CompilerInput synthesizedExternsInput = null;
  {
    CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[562]++;
  }

  @Override
  void addChangeHandler(CodeChangeHandler handler) {
    codeChangeHandlers.add(handler);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[563]++;
  }

  @Override
  void removeChangeHandler(CodeChangeHandler handler) {
    codeChangeHandlers.remove(handler);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[564]++;
  }

  /**
   * All passes should call reportCodeChange() when they alter
   * the JS tree structure. This is verified by CompilerTestCase.
   * This allows us to optimize to a fixed point.
   */
  @Override
  public void reportCodeChange() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[565]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[76]++;


    for (CodeChangeHandler handler : codeChangeHandlers) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[76]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[77]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[78]++;
}
      handler.reportChange();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[566]++;
    }
  }

  @Override
  public CodingConvention getCodingConvention() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[567]++;
    CodingConvention convention = options.getCodingConvention();
    convention = convention != null ? convention : defaultCodingConvention;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[568]++;
    return convention;
  }

  @Override
  public boolean isIdeMode() {
    return options.ideMode;
  }

  @Override
  public boolean acceptEcmaScript5() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[569]++;
    switch (options.getLanguageIn()) {
      case ECMASCRIPT5:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[231]++;
      case ECMASCRIPT5_STRICT:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[232]++;
        return true;
      case ECMASCRIPT3:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[233]++;
        return false; default : CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[234]++;
    }
    throw new IllegalStateException("unexpected language mode");
  }

  public LanguageMode languageMode() {
    return options.getLanguageIn();
  }

  @Override
  public boolean acceptConstKeyword() {
    return options.acceptConstKeyword;
  }

  @Override
  Config getParserConfig() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[570]++;
int CodeCoverConditionCoverageHelper_C111;
    if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((parserConfig == null) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[235]++;
      Config.LanguageMode mode;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[571]++;
      switch (options.getLanguageIn()) {
        case ECMASCRIPT3:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[237]++;
          mode = Config.LanguageMode.ECMASCRIPT3;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[572]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[573]++;
          break;
        case ECMASCRIPT5:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[238]++;
          mode = Config.LanguageMode.ECMASCRIPT5;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[574]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[575]++;
          break;
        case ECMASCRIPT5_STRICT:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[239]++;
          mode = Config.LanguageMode.ECMASCRIPT5_STRICT;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[576]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[577]++;
          break;
        default:
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[240]++;
          throw new IllegalStateException("unexpected language mode");
      }

      parserConfig = ParserRunner.createConfig(
        isIdeMode(),
        mode,
        acceptConstKeyword(),
        options.extraAnnotationNames);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[578]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[236]++;}
    return parserConfig;
  }

  @Override
  public boolean isTypeCheckingEnabled() {
    return options.checkTypes;
  }


  //------------------------------------------------------------------------
  // Error reporting
  //------------------------------------------------------------------------

  /**
   * The warning classes that are available from the command-line, and
   * are suppressible by the {@code @suppress} annotation.
   */
  protected DiagnosticGroups getDiagnosticGroups() {
    return new DiagnosticGroups();
  }

  @Override
  public void report(JSError error) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[579]++;
    CheckLevel level = error.getDefaultLevel();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[580]++;
int CodeCoverConditionCoverageHelper_C112;
    if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((warningsGuard != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[241]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[581]++;
      CheckLevel newLevel = warningsGuard.level(error);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[582]++;
int CodeCoverConditionCoverageHelper_C113;
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((newLevel != null) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[243]++;
        level = newLevel;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[583]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[244]++;}

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[242]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[584]++;
int CodeCoverConditionCoverageHelper_C114;

    if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((level.isOn()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[245]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[585]++;
int CodeCoverConditionCoverageHelper_C115;
      if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((getOptions().errorHandler != null) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[247]++;
        getOptions().errorHandler.report(level, error);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[586]++;

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[248]++;}
      errorManager.report(level, error);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[587]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[246]++;}
  }

  @Override
  public CheckLevel getErrorLevel(JSError error) {
    Preconditions.checkNotNull(options);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[588]++;
    return warningsGuard.level(error);
  }

  /**
   * Report an internal error.
   */
  @Override
  void throwInternalError(String message, Exception cause) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[589]++;
    String finalMessage =
      "INTERNAL COMPILER ERROR.\n" +
      "Please report this problem.\n" + message;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[590]++;

    RuntimeException e = new RuntimeException(finalMessage, cause);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[591]++;
int CodeCoverConditionCoverageHelper_C116;
    if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((cause != null) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[249]++;
      e.setStackTrace(cause.getStackTrace());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[592]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[250]++;}
    throw e;
  }


  /**
   * Gets the number of errors.
   */
  public int getErrorCount() {
    return errorManager.getErrorCount();
  }

  /**
   * Gets the number of warnings.
   */
  public int getWarningCount() {
    return errorManager.getWarningCount();
  }

  @Override
  boolean hasHaltingErrors() {
    return !isIdeMode() && getErrorCount() > 0;
  }

  /**
   * Consults the {@link ErrorManager} to see if we've encountered errors
   * that should halt compilation. <p>
   *
   * If {@link CompilerOptions#ideMode} is {@code true}, this function
   * always returns {@code false} without consulting the error manager. The
   * error manager will continue to be told about new errors and warnings, but
   * the compiler will complete compilation of all inputs.<p>
   */
  public boolean hasErrors() {
    return hasHaltingErrors();
  }

  /** Called from the compiler passes, adds debug info */
  @Override
  void addToDebugLog(String str) {
    debugLog.append(str);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[593]++;
    debugLog.append('\n');
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[594]++;
    logger.fine(str);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[595]++;
  }

  @Override
  SourceFile getSourceFileByName(String sourceName) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[596]++;
int CodeCoverConditionCoverageHelper_C117;
    // Here we assume that the source name is the input name, this
    // is try of JavaScript parsed from source.
    if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[251]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[597]++;
      CompilerInput input = inputsById.get(new InputId(sourceName));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[598]++;
int CodeCoverConditionCoverageHelper_C118;
      if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((input != null) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[253]++;
        return input.getSourceFile();

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[254]++;}

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[252]++;}
    return null;
  }

  @Override
  public String getSourceLine(String sourceName, int lineNumber) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[599]++;
int CodeCoverConditionCoverageHelper_C119;
    if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((lineNumber < 1) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[255]++;
      return null;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[256]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[600]++;
    SourceFile input = getSourceFileByName(sourceName);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[601]++;
int CodeCoverConditionCoverageHelper_C120;
    if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((input != null) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[257]++;
      return input.getLine(lineNumber);

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[258]++;}
    return null;
  }

  @Override
  public Region getSourceRegion(String sourceName, int lineNumber) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[602]++;
int CodeCoverConditionCoverageHelper_C121;
    if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((lineNumber < 1) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[259]++;
      return null;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[260]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[603]++;
    SourceFile input = getSourceFileByName(sourceName);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[604]++;
int CodeCoverConditionCoverageHelper_C122;
    if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((input != null) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[261]++;
      return input.getRegion(lineNumber);

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[262]++;}
    return null;
  }

  //------------------------------------------------------------------------
  // Package-private helpers
  //------------------------------------------------------------------------

  @Override
  Node getNodeForCodeInsertion(JSModule module) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[605]++;
int CodeCoverConditionCoverageHelper_C123;
    if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((module == null) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[263]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[606]++;
int CodeCoverConditionCoverageHelper_C124;
      if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((inputs.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[265]++;
        throw new IllegalStateException("No inputs");

      } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[266]++;}

      return inputs.get(0).getAstRoot(this);

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[264]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[607]++;

    List<CompilerInput> moduleInputs = module.getInputs();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[608]++;
int CodeCoverConditionCoverageHelper_C125;
    if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((moduleInputs.size() > 0) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[267]++;
      return moduleInputs.get(0).getAstRoot(this);

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[268]++;}
    throw new IllegalStateException("Root module has no inputs");
  }

  public SourceMap getSourceMap() {
    return sourceMap;
  }

  VariableMap getVariableMap() {
    return getPassConfig().getIntermediateState().variableMap;
  }

  VariableMap getPropertyMap() {
    return getPassConfig().getIntermediateState().propertyMap;
  }

  CompilerOptions getOptions() {
    return options;
  }

  FunctionInformationMap getFunctionalInformationMap() {
    return functionInformationMap;
  }

  /**
   * Sets the logging level for the com.google.javascript.jscomp package.
   */
  public static void setLoggingLevel(Level level) {
    logger.setLevel(level);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[609]++;
  }

  /** Gets the DOT graph of the AST generated at the end of compilation. */
  public String getAstDotGraph() throws IOException {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[610]++;
int CodeCoverConditionCoverageHelper_C126;
    if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((jsRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[269]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[611]++;
      ControlFlowAnalysis cfa = new ControlFlowAnalysis(this, true, false);
      cfa.process(null, jsRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[612]++;
      return DotFormatter.toDot(jsRoot, cfa.getCfg());

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[270]++;
      return "";
    }
  }

  @Override
  public ErrorManager getErrorManager() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[613]++;
int CodeCoverConditionCoverageHelper_C127;
    if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((options == null) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[271]++;
      initOptions(newCompilerOptions());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[614]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[272]++;}
    return errorManager;
  }

  @Override
  List<CompilerInput> getInputsInOrder() {
    return Collections.<CompilerInput>unmodifiableList(inputs);
  }

  /**
   * Returns an unmodifiable view of the compiler inputs indexed by id.
   */
  public Map<InputId, CompilerInput> getInputsById() {
    return Collections.unmodifiableMap(inputsById);
  }

  /**
   * Gets the externs in the order in which they are being processed.
   */
  List<CompilerInput> getExternsInOrder() {
    return Collections.<CompilerInput>unmodifiableList(externs);
  }

  /**
   * Stores the internal compiler state just before optimization is performed.
   * This can be saved and restored in order to efficiently optimize multiple
   * different output targets without having to perform checking multiple times.
   *
   * NOTE: This does not include all parts of the compiler's internal state. In
   * particular, SourceFiles and CompilerOptions are not recorded. In
   * order to recreate a Compiler instance from scratch, you would need to
   * call {@code init} with the same arguments as in the initial creation before
   * restoring intermediate state.
   */
  public static class IntermediateState implements Serializable {
    private static final long serialVersionUID = 1L;

    Node externsRoot;
    private Node jsRoot;
    private List<CompilerInput> externs;
    private List<CompilerInput> inputs;
    private List<JSModule> modules;
    private PassConfig.State passConfigState;
    private JSTypeRegistry typeRegistry;
    private AbstractCompiler.LifeCycleStage lifeCycleStage;
    private Map<String, Node> injectedLibraries;

    private IntermediateState() {}
  }

  /**
   * Returns the current internal state, excluding the input files and modules.
   */
  public IntermediateState getState() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[615]++;
    IntermediateState state = new IntermediateState();
    state.externsRoot = externsRoot;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[616]++;
    state.jsRoot = jsRoot;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[617]++;
    state.externs = externs;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[618]++;
    state.inputs = inputs;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[619]++;
    state.modules = modules;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[620]++;
    state.passConfigState = getPassConfig().getIntermediateState();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[621]++;
    state.typeRegistry = typeRegistry;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[622]++;
    state.lifeCycleStage = getLifeCycleStage();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[623]++;
    state.injectedLibraries = Maps.newLinkedHashMap(injectedLibraries);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[624]++;

    return state;
  }

  /**
   * Sets the internal state to the capture given.  Note that this assumes that
   * the input files are already set up.
   */
  public void setState(IntermediateState state) {
    externsRoot = state.externsRoot;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[625]++;
    jsRoot = state.jsRoot;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[626]++;
    externs = state.externs;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[627]++;
    inputs = state.inputs;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[628]++;
    modules = state.modules;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[629]++;
    passes = createPassConfigInternal();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[630]++;
    getPassConfig().setIntermediateState(state.passConfigState);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[631]++;
    typeRegistry = state.typeRegistry;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[632]++;
    setLifeCycleStage(state.lifeCycleStage);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[633]++;

    injectedLibraries.clear();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[634]++;
    injectedLibraries.putAll(state.injectedLibraries);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[635]++;
  }

  @VisibleForTesting
  List<CompilerInput> getInputsForTesting() {
    return inputs;
  }

  @VisibleForTesting
  List<CompilerInput> getExternsForTesting() {
    return externs;
  }

  @Override
  boolean hasRegExpGlobalReferences() {
    return hasRegExpGlobalReferences;
  }

  @Override
  void setHasRegExpGlobalReferences(boolean references) {
    hasRegExpGlobalReferences = references;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[636]++;
  }

  @Override
  void updateGlobalVarReferences(Map<Var, ReferenceCollection> refMapPatch,
      Node collectionRoot) {
    Preconditions.checkState(collectionRoot.isScript()
        || collectionRoot.isBlock());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[637]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[638]++;
int CodeCoverConditionCoverageHelper_C128;
    if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((globalRefMap == null) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[273]++;
      globalRefMap = new GlobalVarReferenceMap(getInputsInOrder(),
          getExternsInOrder());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[639]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[274]++;}
    globalRefMap.updateGlobalVarReferences(refMapPatch, collectionRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[640]++;
  }

  @Override
  GlobalVarReferenceMap getGlobalVarReferences() {
    return globalRefMap;
  }

  @Override
  CompilerInput getSynthesizedExternsInput() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[641]++;
int CodeCoverConditionCoverageHelper_C129;
    if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((synthesizedExternsInput == null) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[275]++;
      synthesizedExternsInput = newExternInput(SYNTHETIC_EXTERNS);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[642]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[276]++;}
    return synthesizedExternsInput;
  }

  @Override
  public double getProgress() {
    return progress;
  }

  @Override
  String getLastPassName() {
    return lastPassName;
  }

  @Override
  void setProgress(double newProgress, String passName) {
    this.lastPassName = passName;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[643]++;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[644]++;
int CodeCoverConditionCoverageHelper_C130;
    if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((newProgress > 1.0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[277]++;
      progress = 1.0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[645]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[278]++;
      progress = newProgress;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[646]++;
    }
  }

  /**
   * Replaces one file in a hot-swap mode. The given JsAst should be made
   * from a new version of a file that already was present in the last compile
   * call. If the file is new, this will silently ignored.
   *
   * @param ast the ast of the file that is being replaced
   */
  public void replaceScript(JsAst ast) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[647]++;
    CompilerInput input = this.getInput(ast.getInputId());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[648]++;
int CodeCoverConditionCoverageHelper_C131;
    if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((replaceIncrementalSourceAst(ast)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[279]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[280]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[649]++;
    Node originalRoot = input.getAstRoot(this);

    processNewScript(ast, originalRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[650]++;
  }

  /**
   * Adds a new Script AST to the compile state. If a script for the same file
   * already exists the script will not be added, instead a call to
   * #replaceScript should be used.
   *
   * @param ast the ast of the new file
   */
  public void addNewScript(JsAst ast) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[651]++;
int CodeCoverConditionCoverageHelper_C132;
    if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((addNewSourceAst(ast)) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[281]++;
      return;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[282]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[652]++;
    Node emptyScript = new Node(Token.SCRIPT);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[653]++;
    InputId inputId = ast.getInputId();
    emptyScript.setInputId(inputId);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[654]++;
    emptyScript.setStaticSourceFile(
        SourceFile.fromCode(inputId.getIdName(), ""));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[655]++;

    processNewScript(ast, emptyScript);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[656]++;
  }

  private void processNewScript(JsAst ast, Node originalRoot) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[657]++;
    Node js = ast.getAstRoot(this);
    Preconditions.checkNotNull(js);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[658]++;

    runHotSwap(originalRoot, js, this.getCleanupPassConfig());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[659]++;
    // NOTE: If hot swap passes that use GlobalNamespace are added, we will need
    // to revisit this approach to clearing GlobalNamespaces
    runHotSwapPass(null, null, ensureDefaultPassConfig().garbageCollectChecks);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[660]++;

    this.getTypeRegistry().clearNamedTypes();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[661]++;
    this.removeSyntheticVarsInput();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[662]++;

    runHotSwap(originalRoot, js, this.ensureDefaultPassConfig());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[663]++;
  }

  /**
   * Execute the passes from a PassConfig instance over a single replaced file.
   */
  private void runHotSwap(
      Node originalRoot, Node js, PassConfig passConfig) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[664]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[79]++;


    for (PassFactory passFactory : passConfig.getChecks()) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[79]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[80]--;
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.loops[81]++;
}
      runHotSwapPass(originalRoot, js, passFactory);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[665]++;
    }
  }

  private void runHotSwapPass(
      Node originalRoot, Node js, PassFactory passFactory) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[666]++;
    HotSwapCompilerPass pass = passFactory.getHotSwapPass(this);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[667]++;
int CodeCoverConditionCoverageHelper_C133;
    if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((pass != null) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[283]++;
      logger.info("Performing HotSwap for pass " + passFactory.getName());
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[668]++;
      pass.hotSwapScript(js, originalRoot);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[669]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[284]++;}
  }

  private PassConfig getCleanupPassConfig() {
    return new CleanupPasses(getOptions());
  }

  private void removeSyntheticVarsInput() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[670]++;
    String sourceName = Compiler.SYNTHETIC_EXTERNS;
    removeExternInput(new InputId(sourceName));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[671]++;
  }

  @Override
  Node ensureLibraryInjected(String resourceName) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[672]++;
int CodeCoverConditionCoverageHelper_C134;
    if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((injectedLibraries.containsKey(resourceName)) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[285]++;
      return null;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[286]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[673]++;

    // All libraries depend on js/base.js
    boolean isBase = "base".equals(resourceName);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[674]++;
int CodeCoverConditionCoverageHelper_C135;
    if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((isBase) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[287]++;
      ensureLibraryInjected("base");
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[675]++;

    } else {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[288]++;}
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[676]++;

    Node firstChild = loadLibraryCode(resourceName).removeChildren();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[677]++;
    Node lastChild = firstChild.getLastSibling();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[678]++;

    Node parent = getNodeForCodeInsertion(null);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[679]++;
int CodeCoverConditionCoverageHelper_C136;
    if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((isBase) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$edznfl19y894wunkbmg1.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[289]++;
      parent.addChildrenToFront(firstChild);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[680]++;

    } else {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[290]++;
      parent.addChildrenAfter(
          firstChild, injectedLibraries.get("base"));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[681]++;
    }
    reportCodeChange();
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[682]++;

    injectedLibraries.put(resourceName, lastChild);
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[683]++;
    return lastChild;
  }

  /** Load a library as a resource */
  @VisibleForTesting
  Node loadLibraryCode(String resourceName) {
    String originalCode;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[684]++;
boolean CodeCoverTryBranchHelper_Try13 = false;
    try {
CodeCoverTryBranchHelper_Try13 = true;
      originalCode = CharStreams.toString(new InputStreamReader(
          Compiler.class.getResourceAsStream(
              String.format("js/%s.js", resourceName)),
          Charsets.UTF_8));
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[685]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try13 = false;
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[292]++;
      throw new RuntimeException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try13 ) {
  CodeCoverCoverageCounter$edznfl19y894wunkbmg1.branches[291]++;
}
  }

    return Normalize.parseAndNormalizeSyntheticCode(
        this, originalCode,
        String.format("jscomp_%s_", resourceName));
  }

  /** Returns the compiler version baked into the jar. */
  public static String getReleaseVersion() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[686]++;
    ResourceBundle config = ResourceBundle.getBundle(CONFIG_RESOURCE);
    return config.getString("compiler.version");
  }

  /** Returns the compiler date baked into the jar. */
  public static String getReleaseDate() {
CodeCoverCoverageCounter$edznfl19y894wunkbmg1.statements[687]++;
    ResourceBundle config = ResourceBundle.getBundle(CONFIG_RESOURCE);
    return config.getString("compiler.date");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setOldParseTree(String sourceName, AstRoot oldAst) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AstRoot getOldParseTreeByName(String sourceName) {
    return null;
  }
}

class CodeCoverCoverageCounter$edznfl19y894wunkbmg1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$edznfl19y894wunkbmg1 ());
  }
    public static long[] statements = new long[688];
    public static long[] branches = new long[293];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[137];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.Compiler.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,1,2,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,2,1,2,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 136; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[82];

  public CodeCoverCoverageCounter$edznfl19y894wunkbmg1 () {
    super("com.google.javascript.jscomp.Compiler.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 687; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 292; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 136; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 81; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Compiler.java");
      for (int i = 1; i <= 687; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 292; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 136; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 27; i++) {
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

