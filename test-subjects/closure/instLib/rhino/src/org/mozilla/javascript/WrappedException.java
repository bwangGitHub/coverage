/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * A wrapper for runtime exceptions.
 *
 * Used by the JavaScript runtime to wrap and propagate exceptions that occur
 * during runtime.
 *
 */
public class WrappedException extends EvaluatorException
{
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.ping();
  }

    static final long serialVersionUID = -1551979216966520648L;
  static {
    CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[1]++;
  }

    /**
     * @see Context#throwAsScriptRuntimeEx(Throwable e)
     */
    public WrappedException(Throwable exception)
    {
        super("Wrapped "+exception.toString());
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[2]++;
        this.exception = exception;
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[3]++;
        Kit.initCause(this, exception);
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[4]++;
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[5]++;

        int[] linep = { 0 };
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[6]++;
        String sourceName = Context.getSourcePositionFromStack(linep);
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[7]++;
        int lineNumber = linep[0];
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.branches[1]++;
            initSourceName(sourceName);
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[9]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.branches[2]++;}
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((lineNumber != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.branches[3]++;
            initLineNumber(lineNumber);
CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.statements[11]++;

        } else {
  CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld.branches[4]++;}
    }

    /**
     * Get the wrapped exception.
     *
     * @return the exception that was presented as a argument to the
     *         constructor when this object was created
     */
    public Throwable getWrappedException()
    {
        return exception;
    }

    /**
     * @deprecated Use {@link #getWrappedException()} instead.
     */
    public Object unwrap()
    {
        return getWrappedException();
    }

    private Throwable exception;
}

class CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-WrappedException.java";
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

  public CodeCoverCoverageCounter$adralqrs9n89mg8nzw5w60xru4b27ksxze5uzs0pzgscwmld () {
    super("org.mozilla.javascript.RHINO-SRC-WrappedException.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
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
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-WrappedException.java");
      for (int i = 1; i <= 11; i++) {
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

