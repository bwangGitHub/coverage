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

package com.google.javascript.jscomp.jsonml;

import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.DiagnosticType;
import com.google.javascript.jscomp.JSError;

/**
 * Class used to represent errors which correspond to JsonML elements.
 *
 * @author dhans@google.com (Daniel Hans)
 */
public class JsonMLError {
  static {
    CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.ping();
  }


  /** Description of the error */
  public final String description;

  /** Name of the source */
  public final String sourceName;

  /** Node where the warning occurred. */
  public final JsonML element;

  /** Line number of the source */
  public final int lineNumber;

  /** Level */
  public final ErrorLevel level;

  private JsonMLError(DiagnosticType type, String sourceName, JsonML element,
      int lineNumber, ErrorLevel level, String... arguments) {
    this.description = type.format.format(arguments);
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[1]++;
    this.sourceName = sourceName;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[2]++;
    this.element = element;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[3]++;
    this.lineNumber = lineNumber;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[4]++;
    this.level = level;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[5]++;
  }

  private JsonMLError(String description, DiagnosticType type,
      String sourceName, JsonML element, int lineNumber, ErrorLevel level) {
    this.description = description;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[6]++;
    this.sourceName = sourceName;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[7]++;
    this.element = element;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[8]++;
    this.lineNumber = lineNumber;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[9]++;
    this.level = level;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[10]++;
  }

  public static JsonMLError make(DiagnosticType type, String sourceName,
      JsonML element, int lineNumber, ErrorLevel level, String... arguments) {
    return new JsonMLError(type, sourceName, element, lineNumber, level,
        arguments);
  }

  public static JsonMLError make(JSError error, JsonMLAst ast) {
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[11]++;
    // try to find the corresponding JsonML element
    // it is stored as line number of the JSError
    int n = error.lineNumber;
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[12]++;
    JsonML element = ast.getElementPreOrder(n);
CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup.statements[13]++;

    ErrorLevel level = error.getDefaultLevel() == CheckLevel.ERROR
        ? ErrorLevel.COMPILATION_ERROR
        : ErrorLevel.COMPILATION_WARNING;

    return new JsonMLError(error.getType(), error.sourceName, element, 0,
        level, error.description);
  }
}

class CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$4eocgruswe51uxsg6t80ekxup () {
    super("com.google.javascript.jscomp.jsonml.JsonMLError.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.jsonml.JsonMLError.java");
      for (int i = 1; i <= 13; i++) {
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

