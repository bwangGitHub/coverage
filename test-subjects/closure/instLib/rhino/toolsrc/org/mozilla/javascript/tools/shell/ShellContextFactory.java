/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.*;

public class ShellContextFactory extends ContextFactory
{
  static {
    CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.ping();
  }

    private boolean strictMode;
    private boolean warningAsError;
    private int languageVersion = Context.VERSION_1_7;
  {
    CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[1]++;
  }
    private int optimizationLevel;
    private boolean generatingDebug;
    private boolean allowReservedKeywords = true;
  {
    CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[2]++;
  }
    private ErrorReporter errorReporter;
    private String characterEncoding;

    @Override
    protected boolean hasFeature(Context cx, int featureIndex)
    {
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[3]++;
        switch (featureIndex) {
          case Context.FEATURE_STRICT_VARS:
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[1]++;
          case Context.FEATURE_STRICT_EVAL:
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[2]++;
          case Context.FEATURE_STRICT_MODE:
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[3]++;
            return strictMode;

          case Context.FEATURE_RESERVED_KEYWORD_AS_IDENTIFIER:
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[4]++;
            return allowReservedKeywords;

          case Context.FEATURE_WARNING_AS_ERROR:
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[5]++;
            return warningAsError;

          case Context.FEATURE_LOCATION_INFORMATION_IN_ERROR:
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[6]++;
            return generatingDebug; default : CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[7]++;
        }
        return super.hasFeature(cx, featureIndex);
    }

    @Override
    protected void onContextCreated(Context cx)
    {
        cx.setLanguageVersion(languageVersion);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[4]++;
        cx.setOptimizationLevel(optimizationLevel);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[5]++;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((errorReporter != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[8]++;
            cx.setErrorReporter(errorReporter);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[7]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[9]++;}
        cx.setGeneratingDebug(generatingDebug);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[8]++;
        super.onContextCreated(cx);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[9]++;
    }

    public void setStrictMode(boolean flag)
    {
        checkNotSealed();
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[10]++;
        this.strictMode = flag;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[11]++;
    }

    public void setWarningAsError(boolean flag)
    {
        checkNotSealed();
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[12]++;
        this.warningAsError = flag;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[13]++;
    }

    public void setLanguageVersion(int version)
    {
        Context.checkLanguageVersion(version);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[14]++;
        checkNotSealed();
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[15]++;
        this.languageVersion = version;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[16]++;
    }

    public void setOptimizationLevel(int optimizationLevel)
    {
        Context.checkOptimizationLevel(optimizationLevel);
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[17]++;
        checkNotSealed();
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[18]++;
        this.optimizationLevel = optimizationLevel;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[19]++;
    }

    public void setErrorReporter(ErrorReporter errorReporter)
    {
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((errorReporter == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[10]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.branches[11]++;}
        this.errorReporter = errorReporter;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[21]++;
    }

    public void setGeneratingDebug(boolean generatingDebug)
    {
        this.generatingDebug = generatingDebug;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[22]++;
    }

    public String getCharacterEncoding()
    {
        return characterEncoding;
    }

    public void setCharacterEncoding(String characterEncoding)
    {
        this.characterEncoding = characterEncoding;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[23]++;
    }

    public void setAllowReservedKeywords(boolean allowReservedKeywords) {
        this.allowReservedKeywords = allowReservedKeywords;
CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox.statements[24]++;
    }
}

class CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[12];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-ShellContextFactory.java";
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2vpd6pkpsojjn8ona9zo04iuup46ykay6m7tepe3vos6otubyryox () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-ShellContextFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 11; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-ShellContextFactory.java");
      for (int i = 1; i <= 24; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 11; i++) {
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

