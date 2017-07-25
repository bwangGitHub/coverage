/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.EvaluatorException;

import java.util.ArrayList;
import java.util.List;

/**
 * An error reporter that gathers the errors and warnings for later display.
 * This a useful {@link org.mozilla.javascript.ErrorReporter} when the
 * {@link org.mozilla.javascript.CompilerEnvirons} is set to
 * ide-mode (for IDEs).
 *
 */
public class ErrorCollector implements IdeErrorReporter {
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.ping();
  }


    private List<ParseProblem> errors = new ArrayList<ParseProblem>();
  {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.statements[1]++;
  }

    /**
     * This is not called during AST generation.
     * {@link #warning(String,String,int,int)} is used instead.
     * @throws UnsupportedOperationException
     */
    public void warning(String message, String sourceName, int line,
                        String lineSource, int lineOffset) {
        throw new UnsupportedOperationException();
    }

    /**
     * @inheritDoc
     */
    public void warning(String message, String sourceName, int offset, int length)
    {
        errors.add(new ParseProblem(ParseProblem.Type.Warning,
                                    message, sourceName,
                                    offset, length));
CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.statements[2]++;
    }

    /**
     * This is not called during AST generation.
     * {@link #warning(String,String,int,int)} is used instead.
     * @throws UnsupportedOperationException
     */
    public void error(String message, String sourceName, int line,
                      String lineSource, int lineOffset)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * @inheritDoc
     */
    public void error(String message, String sourceName,
                      int fileOffset, int length)
    {
        errors.add(new ParseProblem(ParseProblem.Type.Error,
                                    message, sourceName,
                                    fileOffset, length));
CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.statements[3]++;
    }

    /**
     * @inheritDoc
     */
    public EvaluatorException runtimeError(String message, String sourceName,
                                           int line, String lineSource,
                                           int lineOffset)
    {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the list of errors and warnings produced during parsing.
     */
    public List<ParseProblem> getErrors() {
        return errors;
    }

    @Override
    public String toString() {
CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.statements[4]++;
        StringBuilder sb = new StringBuilder(errors.size() * 100);
CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.loops[1]++;


        for (ParseProblem pp : errors) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.loops[1]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.loops[2]--;
  CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.loops[3]++;
}
            sb.append(pp.toString()).append("\n");
CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75.statements[6]++;
        }
        return sb.toString();
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75 ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[0];
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevozy7qpe3pa2clgrbjklzpb09s3a75 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-ErrorCollector.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-ErrorCollector.java");
      for (int i = 1; i <= 6; i++) {
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

