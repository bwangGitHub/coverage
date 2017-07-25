/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This is the default error reporter for JavaScript.
 *
 */
class DefaultErrorReporter implements ErrorReporter
{
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.ping();
  }

    static final DefaultErrorReporter instance = new DefaultErrorReporter();
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[1]++;
  }

    private boolean forEval;
    private ErrorReporter chainedReporter;

    private DefaultErrorReporter() { }

    static ErrorReporter forEval(ErrorReporter reporter)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[2]++;
        DefaultErrorReporter r = new DefaultErrorReporter();
        r.forEval = true;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[3]++;
        r.chainedReporter = reporter;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[4]++;
        return r;
    }

    public void warning(String message, String sourceURI, int line,
                        String lineText, int lineOffset)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((chainedReporter != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[1]++;
            chainedReporter.warning(
                message, sourceURI, line, lineText, lineOffset);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[6]++;

        } else {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[2]++;
            // Do nothing
        }
    }

    public void error(String message, String sourceURI, int line,
                      String lineText, int lineOffset)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((forEval) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[3]++;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[8]++;
            // Assume error message strings that start with "TypeError: "
            // should become TypeError exceptions. A bit of a hack, but we
            // don't want to change the ErrorReporter interface.
            String error = "SyntaxError";
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[9]++;
            final String TYPE_ERROR_NAME = "TypeError";
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[10]++;
            final String DELIMETER = ": ";
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[11]++;
            final String prefix = TYPE_ERROR_NAME + DELIMETER;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((message.startsWith(prefix)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[5]++;
                error = TYPE_ERROR_NAME;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[13]++;
                message = message.substring(prefix.length());
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[14]++;

            } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[6]++;}
            throw ScriptRuntime.constructError(error, message, sourceURI,
                                               line, lineText, lineOffset);

        } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[4]++;}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((chainedReporter != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[7]++;
            chainedReporter.error(
                message, sourceURI, line, lineText, lineOffset);
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[16]++;

        } else {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[8]++;
            throw runtimeError(
                message, sourceURI, line, lineText, lineOffset);
        }
    }

    public EvaluatorException runtimeError(String message, String sourceURI,
                                           int line, String lineText,
                                           int lineOffset)
    {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.statements[17]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((chainedReporter != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[9]++;
            return chainedReporter.runtimeError(
                message, sourceURI, line, lineText, lineOffset);

        } else {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x.branches[10]++;
            return new EvaluatorException(
                message, sourceURI, line, lineText, lineOffset);
        }
    }
}

class CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x ());
  }
    public static long[] statements = new long[18];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-DefaultErrorReporter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$khgdrpvbfyfsmmlwejl3ufd5pbaf9r780ldv6jcq5ksoxqkq80dn4x () {
    super("org.mozilla.javascript.RHINO-SRC-DefaultErrorReporter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 17; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-DefaultErrorReporter.java");
      for (int i = 1; i <= 17; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

