/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.util.Set;

import org.mozilla.javascript.ast.ErrorCollector;

public class CompilerEnvirons
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.ping();
  }

    public CompilerEnvirons()
    {
        errorReporter = DefaultErrorReporter.instance;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[1]++;
        languageVersion = Context.VERSION_DEFAULT;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[2]++;
        generateDebugInfo = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[3]++;
        reservedKeywordAsIdentifier = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[4]++;
        allowMemberExprAsFunctionName = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[5]++;
        xmlAvailable = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[6]++;
        optimizationLevel = 0;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[7]++;
        generatingSource = true;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[8]++;
        strictMode = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[9]++;
        warningAsError = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[10]++;
        generateObserverCount = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[11]++;
        allowSharpComments = false;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[12]++;
    }

    public void initFromContext(Context cx)
    {
        setErrorReporter(cx.getErrorReporter());
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[13]++;
        languageVersion = cx.getLanguageVersion();
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[14]++;
        generateDebugInfo = (!cx.isGeneratingDebugChanged()
                             || cx.isGeneratingDebug());
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[15]++;
        reservedKeywordAsIdentifier
            = cx.hasFeature(Context.FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[16]++;
        allowMemberExprAsFunctionName
            = cx.hasFeature(Context.FEATURE_MEMBER_EXPR_AS_FUNCTION_NAME);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[17]++;
        strictMode
            = cx.hasFeature(Context.FEATURE_STRICT_MODE);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[18]++;
        warningAsError = cx.hasFeature(Context.FEATURE_WARNING_AS_ERROR);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[19]++;
        xmlAvailable
            = cx.hasFeature(Context.FEATURE_E4X);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[20]++;

        optimizationLevel = cx.getOptimizationLevel();
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[21]++;

        generatingSource = cx.isGeneratingSource();
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[22]++;
        activationNames = cx.activationNames;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[23]++;

        // Observer code generation in compiled code :
        generateObserverCount = cx.generateObserverCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[24]++;
    }

    public final ErrorReporter getErrorReporter()
    {
        return errorReporter;
    }

    public void setErrorReporter(ErrorReporter errorReporter)
    {
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[25]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorReporter == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.branches[2]++;}
        this.errorReporter = errorReporter;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[26]++;
    }

    public final int getLanguageVersion()
    {
        return languageVersion;
    }

    public void setLanguageVersion(int languageVersion)
    {
        Context.checkLanguageVersion(languageVersion);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[27]++;
        this.languageVersion = languageVersion;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[28]++;
    }

    public final boolean isGenerateDebugInfo()
    {
        return generateDebugInfo;
    }

    public void setGenerateDebugInfo(boolean flag)
    {
        this.generateDebugInfo = flag;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[29]++;
    }

    public final boolean isReservedKeywordAsIdentifier()
    {
        return reservedKeywordAsIdentifier;
    }

    public void setReservedKeywordAsIdentifier(boolean flag)
    {
        reservedKeywordAsIdentifier = flag;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[30]++;
    }

    /**
     * Extension to ECMA: if 'function &lt;name&gt;' is not followed
     * by '(', assume &lt;name&gt; starts a {@code memberExpr}
     */
    public final boolean isAllowMemberExprAsFunctionName()
    {
        return allowMemberExprAsFunctionName;
    }

    public void setAllowMemberExprAsFunctionName(boolean flag)
    {
        allowMemberExprAsFunctionName = flag;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[31]++;
    }

    public final boolean isXmlAvailable()
    {
        return xmlAvailable;
    }

    public void setXmlAvailable(boolean flag)
    {
        xmlAvailable = flag;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[32]++;
    }

    public final int getOptimizationLevel()
    {
        return optimizationLevel;
    }

    public void setOptimizationLevel(int level)
    {
        Context.checkOptimizationLevel(level);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[33]++;
        this.optimizationLevel = level;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[34]++;
    }

    public final boolean isGeneratingSource()
    {
        return generatingSource;
    }

    public boolean getWarnTrailingComma() {
        return warnTrailingComma;
    }

    public void setWarnTrailingComma(boolean warn) {
        warnTrailingComma = warn;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[35]++;
    }

    public final boolean isStrictMode()
    {
        return strictMode;
    }

    public void setStrictMode(boolean strict)
    {
        strictMode = strict;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[36]++;
    }

    public final boolean reportWarningAsError()
    {
        return warningAsError;
    }

    /**
     * Specify whether or not source information should be generated.
     * <p>
     * Without source information, evaluating the "toString" method
     * on JavaScript functions produces only "[native code]" for
     * the body of the function.
     * Note that code generated without source is not fully ECMA
     * conformant.
     */
    public void setGeneratingSource(boolean generatingSource)
    {
        this.generatingSource = generatingSource;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[37]++;
    }

    /**
     * @return true iff code will be generated with callbacks to enable
     * instruction thresholds
     */
    public boolean isGenerateObserverCount() {
        return generateObserverCount;
    }

    /**
     * Turn on or off generation of code with callbacks to
     * track the count of executed instructions.
     * Currently only affects JVM byte code generation: this slows down the
     * generated code, but code generated without the callbacks will not
     * be counted toward instruction thresholds. Rhino's interpretive
     * mode does instruction counting without inserting callbacks, so
     * there is no requirement to compile code differently.
     * @param generateObserverCount if true, generated code will contain
     * calls to accumulate an estimate of the instructions executed.
     */
    public void setGenerateObserverCount(boolean generateObserverCount) {
        this.generateObserverCount = generateObserverCount;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[38]++;
    }

    public boolean isRecordingComments() {
        return recordingComments;
    }

    public void setRecordingComments(boolean record) {
        recordingComments = record;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[39]++;
    }

    public boolean isRecordingLocalJsDocComments() {
        return recordingLocalJsDocComments;
    }

    public void setRecordingLocalJsDocComments(boolean record) {
        recordingLocalJsDocComments = record;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[40]++;
    }

    /**
     * Turn on or off full error recovery.  In this mode, parse errors do not
     * throw an exception, and the parser attempts to build a full syntax tree
     * from the input.  Useful for IDEs and other frontends.
     */
    public void setRecoverFromErrors(boolean recover) {
        recoverFromErrors = recover;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[41]++;
    }

    public boolean recoverFromErrors() {
        return recoverFromErrors;
    }

    /**
     * Puts the parser in "IDE" mode.  This enables some slightly more expensive
     * computations, such as figuring out helpful error bounds.
     */
    public void setIdeMode(boolean ide) {
        ideMode = ide;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[42]++;
    }

    public boolean isIdeMode() {
        return ideMode;
    }

    public Set<String> getActivationNames() {
        return activationNames;
    }

    public void setActivationNames(Set<String> activationNames) {
        this.activationNames = activationNames;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[43]++;
    }

    /**
     * Mozilla sources use the C preprocessor.
     */
    public void setAllowSharpComments(boolean allow) {
        allowSharpComments = allow;
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[44]++;
    }

    public boolean getAllowSharpComments() {
        return allowSharpComments;
    }

    /**
     * Returns a {@code CompilerEnvirons} suitable for using Rhino
     * in an IDE environment.  Most features are enabled by default.
     * The {@link ErrorReporter} is set to an {@link ErrorCollector}.
     */
    public static CompilerEnvirons ideEnvirons() {
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[45]++;
        CompilerEnvirons env = new CompilerEnvirons();
        env.setRecoverFromErrors(true);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[46]++;
        env.setRecordingComments(true);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[47]++;
        env.setStrictMode(true);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[48]++;
        env.setWarnTrailingComma(true);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[49]++;
        env.setLanguageVersion(170);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[50]++;
        env.setReservedKeywordAsIdentifier(true);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[51]++;
        env.setIdeMode(true);
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[52]++;
        env.setErrorReporter(new ErrorCollector());
CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h.statements[53]++;
        return env;
    }

    private ErrorReporter errorReporter;

    private int languageVersion;
    private boolean generateDebugInfo;
    private boolean reservedKeywordAsIdentifier;
    private boolean allowMemberExprAsFunctionName;
    private boolean xmlAvailable;
    private int optimizationLevel;
    private boolean generatingSource;
    private boolean strictMode;
    private boolean warningAsError;
    private boolean generateObserverCount;
    private boolean recordingComments;
    private boolean recordingLocalJsDocComments;
    private boolean recoverFromErrors;
    private boolean warnTrailingComma;
    private boolean ideMode;
    private boolean allowSharpComments;
    Set<String> activationNames;
}

class CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-CompilerEnvirons.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$adralqrs9n89mg8nj9quchrnxcxt4fby3cvh3rvq8kdck50h () {
    super("org.mozilla.javascript.RHINO-SRC-CompilerEnvirons.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-CompilerEnvirons.java");
      for (int i = 1; i <= 53; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
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

