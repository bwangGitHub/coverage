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

import com.google.common.collect.ImmutableMap;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.rhino.ErrorReporter;
import com.google.javascript.rhino.ScriptRuntime;

import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

/**
 * An error reporter for serializing Rhino errors into our error format.
 * @author nicksantos@google.com (Nick Santos)
 */
class RhinoErrorReporter {
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.ping();
  }


  static final DiagnosticType PARSE_ERROR =
      DiagnosticType.error("JSC_PARSE_ERROR", "Parse error. {0}");
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[1]++;
  }

  static final DiagnosticType TYPE_PARSE_ERROR =
      DiagnosticType.warning("JSC_TYPE_PARSE_ERROR", "{0}");
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[2]++;
  }

  // Special-cased errors, so that they can be configured via the
  // warnings API.
  static final DiagnosticType TRAILING_COMMA =
      DiagnosticType.error("JSC_TRAILING_COMMA",
          "Parse error. IE8 (and below) will parse trailing commas in " +
          "array and object literals incorrectly. " +
          "If you are targeting newer versions of JS, " +
          "set the appropriate language_in option.");
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[3]++;
  }

  static final DiagnosticType DUPLICATE_PARAM =
      DiagnosticType.error("JSC_DUPLICATE_PARAM", "Parse error. {0}");
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[4]++;
  }

  static final DiagnosticType BAD_JSDOC_ANNOTATION =
      DiagnosticType.warning("JSC_BAD_JSDOC_ANNOTATION", "Parse error. {0}");
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[5]++;
  }

  static final DiagnosticType MISPLACED_TYPE_ANNOTATION =
      DiagnosticType.warning("JSC_MISPLACED_TYPE_ANNOTATION",
          "Type annotations are not allowed here. " +
          "Are you missing parentheses?");
  static {
    CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[6]++;
  }

  // A map of Rhino messages to their DiagnosticType.
  private final Map<Pattern, DiagnosticType> typeMap;

  final AbstractCompiler compiler;

  /**
   * For each message such as "Not a good use of {0}", replace the place
   * holder {0} with a wild card that matches all possible strings.
   * Also put the any non-place-holder in quotes for regex matching later.
   */
  private Pattern replacePlaceHolders(String s) {
    s = Pattern.quote(s);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[7]++;
    return Pattern.compile(s.replaceAll("\\{\\d+\\}", "\\\\E.*\\\\Q"));
  }

  private RhinoErrorReporter(AbstractCompiler compiler) {
    this.compiler = compiler;
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[8]++;
    typeMap = ImmutableMap.of(
        // Trailing comma
        replacePlaceHolders(
            com.google.javascript.rhino.head.ScriptRuntime
              .getMessage0("msg.extra.trailing.comma")),
        TRAILING_COMMA,

        // Duplicate parameter
        replacePlaceHolders(
            com.google.javascript.rhino.head.ScriptRuntime
              .getMessage0("msg.dup.parms")),
        DUPLICATE_PARAM,

        // Unknown @annotations.
        replacePlaceHolders(ScriptRuntime.getMessage0("msg.bad.jsdoc.tag")),
        BAD_JSDOC_ANNOTATION,

        Pattern.compile("^Type annotations are not allowed here.*"),
        MISPLACED_TYPE_ANNOTATION,

        // Type annotation errors.
        Pattern.compile("^Bad type annotation.*"),
        TYPE_PARSE_ERROR
        );
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[9]++;
  }

  public static com.google.javascript.rhino.head.ErrorReporter
      forNewRhino(AbstractCompiler compiler) {
    return new NewRhinoErrorReporter(compiler);
  }

  public static ErrorReporter forOldRhino(AbstractCompiler compiler) {
    return new OldRhinoErrorReporter(compiler);
  }

  void warningAtLine(String message, String sourceName, int line,
      int lineOffset) {
    compiler.report(
        makeError(message, sourceName, line, lineOffset, CheckLevel.WARNING));
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[10]++;
  }

  void errorAtLine(String message, String sourceName, int line,
      int lineOffset) {
    compiler.report(
        makeError(message, sourceName, line, lineOffset, CheckLevel.ERROR));
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[11]++;
  }

  private JSError makeError(String message, String sourceName, int line,
      int lineOffset, CheckLevel defaultLevel) {
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.loops[1]++;



    // Try to see if the message is one of the rhino errors we want to
    // expose as DiagnosticType by matching it with the regex key.
    for (Entry<Pattern, DiagnosticType> entry : typeMap.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.loops[1]--;
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.loops[2]--;
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.loops[3]++;
}
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[13]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((entry.getKey().matcher(message).matches()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.branches[1]++;
        return JSError.make(
            sourceName, line, lineOffset, entry.getValue(), message);

      } else {
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.branches[2]++;}
    }

    return JSError.make(sourceName, line, lineOffset, defaultLevel,
        PARSE_ERROR, message);
  }

  private static class OldRhinoErrorReporter extends RhinoErrorReporter
      implements ErrorReporter {

    private OldRhinoErrorReporter(AbstractCompiler compiler) {
      super(compiler);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[14]++;
    }

    @Override
    public void error(String message, String sourceName, int line,
        int lineOffset) {
      super.errorAtLine(message, sourceName, line, lineOffset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[15]++;
    }

    @Override
    public void warning(String message, String sourceName, int line,
        int lineOffset) {
      super.warningAtLine(message, sourceName, line, lineOffset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[16]++;
    }
  }

  private static class NewRhinoErrorReporter extends RhinoErrorReporter
      implements com.google.javascript.rhino.head.ast.IdeErrorReporter {

    private NewRhinoErrorReporter(AbstractCompiler compiler) {
      super(compiler);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[17]++;
    }

    @Override
    public com.google.javascript.rhino.head.EvaluatorException
        runtimeError(String message, String sourceName, int line,
            String lineSource, int lineOffset) {
      return new com.google.javascript.rhino.head.EvaluatorException(
          message, sourceName, line, lineSource, lineOffset);
    }

    @Override
    public void error(String message, String sourceName, int line,
        String sourceLine, int lineOffset) {
      super.errorAtLine(message, sourceName, line, lineOffset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[18]++;
    }

    @Override
    public void error(String message, String sourceName,
        int offset, int length) {
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[19]++;
      int line = 1;
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[20]++;
      int column = 0;
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[21]++;
      SourceFile file = this.compiler.getSourceFileByName(sourceName);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((file != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.branches[3]++;
        line = file.getLineOfOffset(offset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[23]++;
        column = file.getColumnOfOffset(offset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[24]++;

      } else {
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.branches[4]++;}
      super.errorAtLine(message, sourceName, line, column);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[25]++;
    }

    @Override
    public void warning(String message, String sourceName, int line,
        String sourceLine, int lineOffset) {
      super.warningAtLine(message, sourceName, line, lineOffset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[26]++;
    }

    @Override
    public void warning(String message, String sourceName,
        int offset, int length) {
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[27]++;
      int line = 1;
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[28]++;
      int column = 0;
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[29]++;
      SourceFile file = this.compiler.getSourceFileByName(sourceName);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[30]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((file != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.branches[5]++;
        line = file.getLineOfOffset(offset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[31]++;
        column = file.getColumnOfOffset(offset);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[32]++;

      } else {
  CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.branches[6]++;}
      super.errorAtLine(message, sourceName, line, column);
CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx.statements[33]++;
    }
  }
}

class CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.RhinoErrorReporter.java";
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

  public CodeCoverCoverageCounter$2o5iadvcv026jwis2tv2on1yjgxd7tbn7kgx () {
    super("com.google.javascript.jscomp.RhinoErrorReporter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.RhinoErrorReporter.java");
      for (int i = 1; i <= 33; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
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

