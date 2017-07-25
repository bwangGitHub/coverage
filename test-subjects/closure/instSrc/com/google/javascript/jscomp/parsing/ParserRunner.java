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

package com.google.javascript.jscomp.parsing;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.javascript.jscomp.parsing.Config.LanguageMode;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.head.CompilerEnvirons;
import com.google.javascript.rhino.head.Context;
import com.google.javascript.rhino.head.ErrorReporter;
import com.google.javascript.rhino.head.EvaluatorException;
import com.google.javascript.rhino.head.Parser;
import com.google.javascript.rhino.head.ast.AstRoot;
import com.google.javascript.rhino.jstype.StaticSourceFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;

public class ParserRunner {
  static {
    CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.ping();
  }


  private static final String configResource =
      "com.google.javascript.jscomp.parsing.ParserConfig";
  static {
    CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[1]++;
  }

  private static Set<String> annotationNames = null;
  static {
    CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[2]++;
  }

  private static Set<String> suppressionNames = null;
  static {
    CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[3]++;
  }
  private static Set<String> reservedVars = null;
  static {
    CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[4]++;
  }

  // Should never need to instantiate class of static methods.
  private ParserRunner() {}

  @Deprecated
  public static Config createConfig(boolean isIdeMode) {
    return createConfig(isIdeMode, LanguageMode.ECMASCRIPT3, false);
  }

  public static Config createConfig(boolean isIdeMode,
                                    LanguageMode languageMode,
                                    boolean acceptConstKeyword) {
    return createConfig(isIdeMode, languageMode, acceptConstKeyword, null);
  }

  public static Config createConfig(boolean isIdeMode,
                                    LanguageMode languageMode,
                                    boolean acceptConstKeyword,
                                    Set<String> extraAnnotationNames) {
    initResourceConfig();
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[5]++;
    Set<String> effectiveAnnotationNames;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((extraAnnotationNames == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[1]++;
      effectiveAnnotationNames = annotationNames;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[7]++;

    } else {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[2]++;
      effectiveAnnotationNames = new HashSet<String>(annotationNames);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[8]++;
      effectiveAnnotationNames.addAll(extraAnnotationNames);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[9]++;
    }
    return new Config(effectiveAnnotationNames, suppressionNames,
        isIdeMode, languageMode, acceptConstKeyword);
  }

  public static Set<String> getReservedVars() {
    initResourceConfig();
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[10]++;
    return reservedVars;
  }

  private static synchronized void initResourceConfig() {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((annotationNames != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[4]++;}
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[12]++;

    ResourceBundle config = ResourceBundle.getBundle(configResource);
    annotationNames = extractList(config.getString("jsdoc.annotations"));
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[13]++;
    suppressionNames = extractList(config.getString("jsdoc.suppressions"));
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[14]++;
    reservedVars = extractList(config.getString("compiler.reserved.vars"));
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[15]++;
  }

  private static Set<String> extractList(String configProp) {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[16]++;
    String[] names = configProp.split(",");
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[17]++;
    Set<String> trimmedNames = Sets.newHashSet();
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.loops[1]++;


    for (String name : names) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.loops[1]--;
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.loops[2]--;
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.loops[3]++;
}
      trimmedNames.add(name.trim());
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[19]++;
    }
    return ImmutableSet.copyOf(trimmedNames);
  }

  /**
   * Parses the JavaScript text given by a reader.
   *
   * @param sourceString Source code from the file.
   * @param errorReporter An error.
   * @param logger A logger.
   * @return The AST of the given text.
   * @throws IOException
   */
  public static ParseResult parse(StaticSourceFile sourceFile,
                                  String sourceString,
                                  Config config,
                                  ErrorReporter errorReporter,
                                  Logger logger) throws IOException {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[20]++;
    Context cx = Context.enter();
    cx.setErrorReporter(errorReporter);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[21]++;
    cx.setLanguageVersion(Context.VERSION_1_5);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[22]++;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[23]++;
    CompilerEnvirons compilerEnv = new CompilerEnvirons();
    compilerEnv.initFromContext(cx);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[24]++;
    compilerEnv.setRecordingComments(true);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[25]++;
    compilerEnv.setRecordingLocalJsDocComments(true);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[26]++;

    // ES5 specifically allows trailing commas
    compilerEnv.setWarnTrailingComma(
        config.languageMode == LanguageMode.ECMASCRIPT3);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[27]++;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[28]++;

    // Do our own identifier check for ECMASCRIPT 5
    boolean acceptEs5 =
        config.isIdeMode || config.languageMode != LanguageMode.ECMASCRIPT3;
    compilerEnv.setReservedKeywordAsIdentifier(acceptEs5);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[29]++;

    compilerEnv.setAllowMemberExprAsFunctionName(false);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[30]++;
    compilerEnv.setIdeMode(config.isIdeMode);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[31]++;
    compilerEnv.setRecoverFromErrors(config.isIdeMode);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[32]++;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[33]++;

    Parser p = new Parser(compilerEnv, errorReporter);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[34]++;
    AstRoot astRoot = null;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[35]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      astRoot = p.parse(sourceString, sourceFile.getName(), 1);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[36]++;
    } catch (EvaluatorException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[6]++;
      logger.info(
          "Error parsing " + sourceFile.getName() + ": " + e.getMessage());
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[37]++;
    } finally {
if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[5]++;
}
      Context.exit();
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[38]++;
    }
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[39]++;
    Node root = null;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[40]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((astRoot != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[7]++;
      root = IRFactory.transformTree(
          astRoot, sourceFile, sourceString, config, errorReporter);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[41]++;
      root.setIsSyntheticBlock(true);
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[42]++;

    } else {
  CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.branches[8]++;}
    return new ParseResult(root, astRoot);
  }

  /**
   * Holds results of parsing. Includes both ast formats.
   */
  public static class ParseResult {
    public final Node ast;
    public final AstRoot oldAst;

    public ParseResult(Node ast, AstRoot oldAst) {
      this.ast = ast;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[43]++;
      this.oldAst = oldAst;
CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx.statements[44]++;
    }
  }
}

class CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx ());
  }
    public static long[] statements = new long[45];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.parsing.ParserRunner.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$xu8g4pg4pxnxqkpwrealwuwpcx () {
    super("com.google.javascript.jscomp.parsing.ParserRunner.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 44; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.ParserRunner.java");
      for (int i = 1; i <= 44; i++) {
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
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

