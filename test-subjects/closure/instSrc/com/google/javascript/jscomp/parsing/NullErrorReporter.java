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

import com.google.javascript.rhino.ErrorReporter;

/**
 * An error reporter which consumes all calls and performs no actions.
 *
 */
public abstract class NullErrorReporter  {
  static {
    CodeCoverCoverageCounter$cvg3l7clevgaontjxjkgkwwdxr1qij8ktd.ping();
  }

  private NullErrorReporter() {
  }

  public void error(String message, String sourceName, int line,
      int lineOffset) {
  }

  public void warning(String message, String sourceName, int line,
      int lineOffset) {
  }

  public static ErrorReporter forOldRhino() {
    return new OldRhinoNullReporter();
  }

  public static com.google.javascript.rhino.head.ErrorReporter
      forNewRhino() {
    return new NewRhinoNullReporter();
  }

  private static class NewRhinoNullReporter extends NullErrorReporter
      implements com.google.javascript.rhino.head.ErrorReporter {
    @Override
    public com.google.javascript.rhino.head.EvaluatorException
      runtimeError(String message, String sourceName, int line,
                   String lineSource, int lineOffset) {
      return new com.google.javascript.rhino.head.EvaluatorException(
          message);
    }

    @Override
    public void error(String message, String sourceName, int line,
        String sourceLine, int lineOffset) {
      super.error(message, sourceName, line, lineOffset);
CodeCoverCoverageCounter$cvg3l7clevgaontjxjkgkwwdxr1qij8ktd.statements[1]++;
    }

    @Override
    public void warning(String message, String sourceName, int line,
        String sourceLine, int lineOffset) {
      super.warning(message, sourceName, line, lineOffset);
CodeCoverCoverageCounter$cvg3l7clevgaontjxjkgkwwdxr1qij8ktd.statements[2]++;
    }
  }

  private static class OldRhinoNullReporter extends NullErrorReporter
      implements ErrorReporter {
  }
}

class CodeCoverCoverageCounter$cvg3l7clevgaontjxjkgkwwdxr1qij8ktd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$cvg3l7clevgaontjxjkgkwwdxr1qij8ktd ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$cvg3l7clevgaontjxjkgkwwdxr1qij8ktd () {
    super("com.google.javascript.jscomp.parsing.NullErrorReporter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 2; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.NullErrorReporter.java");
      for (int i = 1; i <= 2; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= -1; i++) {
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

