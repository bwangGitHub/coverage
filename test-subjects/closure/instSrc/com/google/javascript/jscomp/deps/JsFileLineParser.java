/*
 * Copyright 2008 The Closure Compiler Authors.
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

package com.google.javascript.jscomp.deps;

import com.google.common.collect.Lists;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.DiagnosticType;
import com.google.javascript.jscomp.ErrorManager;
import com.google.javascript.jscomp.JSError;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Base class for classes that parse JavaScript sources on a line-by-line basis. Strips comments
 * from files and records all parsing errors.
 *
 * @author agrieve@google.com (Andrew Grieve)
 */
public abstract class JsFileLineParser {
  static {
    CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.ping();
  }


  static final DiagnosticType PARSE_WARNING = DiagnosticType.warning(
      "DEPS_PARSE_WARNING", "{0}\n{1}");
  static {
    CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[1]++;
  }
  static final DiagnosticType PARSE_ERROR = DiagnosticType.error(
      "DEPS_PARSE_ERROR", "{0}\n{1}");
  static {
    CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[2]++;
  }

  boolean shortcutMode = false;
  {
    CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[3]++;
  }

  /**
   * Thrown by base classes to signify a problem parsing a line.
   */
  static class ParseException extends Exception {
    public static final long serialVersionUID = 1L;
    private boolean fatal;

    /**
     * Constructor.
     *
     * @param message A description of what caused the exception.
     * @param fatal Whether the exception is recoverable.
     */
    public ParseException(String message, boolean fatal) {
      super(message);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[4]++;
      this.fatal = fatal;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[5]++;
    }

    public boolean isFatal() {
      return fatal;
    }
  }

  /** Pattern for matching JavaScript string literals. */
  private static final Pattern STRING_LITERAL_PATTERN = Pattern.compile(
      "\\s*(?:'((?:\\\\'|[^'])*?)'|\"((?:\\\\\"|[^\"])*?)\")\\s*");
  static {
    CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[6]++;
  }

  /** Matcher used in the parsing string literals. */
  private Matcher valueMatcher = STRING_LITERAL_PATTERN.matcher("");
  {
    CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[7]++;
  }

  /** Path of the file currently being parsed. */
  String filePath;
  /** The line number of the line currently being parsed. */
  int lineNum;
  /** Handles error messages. */
  ErrorManager errorManager;
  /** Did our parse succeed. */
  boolean parseSucceeded;

  /**
   * Constructor.
   *
   * @param errorManager Parse error handler.
   */
  public JsFileLineParser(ErrorManager errorManager) {
    this.errorManager = errorManager;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[8]++;
  }

  /**
   * In shortcut mode, the file line parser can stop reading early if
   * it thinks it found enough information.
   *
   * For example, many parsers assume that dependency information never
   * shows up after "real" code.
   */
  public void setShortcutMode(boolean mode) {
    this.shortcutMode = mode;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[9]++;
  }

  public boolean didParseSucceed() {
    return parseSucceeded;
  }

  /**
   * Performs the line-by-line parsing of the given fileContents. This method
   * strips out JavaScript comments and then uses the abstract parseLine()
   * method to do the line parsing.
   *
   * @param filePath The path to the file being parsed. Used for reporting parse
   *     exceptions.
   * @param fileContents A reader for the contents of the file.
   */
  void doParse(String filePath, Reader fileContents) {
    this.filePath = filePath;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[10]++;
    parseSucceeded = true;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[11]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[12]++;

    BufferedReader lineBuffer = new BufferedReader(fileContents);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[13]++;

    // Parse all lines.
    String line = null;
    lineNum = 0;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[14]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[15]++;
    boolean inMultilineComment = false;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[16]++;
boolean CodeCoverTryBranchHelper_Try1 = false;

    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[17]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[1]++;


      while (null != (line = lineBuffer.readLine())) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[1]--;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[2]--;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[3]++;
}
        ++lineNum;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[18]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[19]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[20]++;
          String revisedLine = line;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[21]++;
int CodeCoverConditionCoverageHelper_C2;
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((inMultilineComment) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[3]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[22]++;
            int endOfComment = revisedLine.indexOf("*/");
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((endOfComment != -1) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[5]++;
              revisedLine = revisedLine.substring(endOfComment + 2);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[24]++;
              inMultilineComment = false;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[25]++;

            } else {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[6]++;
              revisedLine = "";
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[26]++;
            }

          } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[4]++;}
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[27]++;
int CodeCoverConditionCoverageHelper_C4;

          if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((inMultilineComment) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[7]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[28]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[4]++;


            while (true) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[4]--;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[5]--;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[6]++;
}
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[29]++;
              int startOfLineComment = revisedLine.indexOf("//");
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[30]++;
              int startOfMultilineComment = revisedLine.indexOf("/*");
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[31]++;
int CodeCoverConditionCoverageHelper_C6;
              if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (32)) == 0 || true) &&
 ((startOfLineComment != -1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((startOfMultilineComment == -1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((startOfLineComment < startOfMultilineComment) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 3) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[9]++;
                revisedLine = revisedLine.substring(0, startOfLineComment);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[32]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[33]++;
                break;

              } else {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[10]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[34]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((startOfMultilineComment != -1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[11]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[35]++;
                int endOfMultilineComment = revisedLine.indexOf("*/",
                    startOfMultilineComment + 2);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[36]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((endOfMultilineComment == -1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[13]++;
                  revisedLine = revisedLine.substring(
                      0, startOfMultilineComment);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[37]++;
                  inMultilineComment = true;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[38]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[39]++;
                  break;

                } else {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[14]++;
                  revisedLine =
                      revisedLine.substring(0, startOfMultilineComment) +
                      revisedLine.substring(endOfMultilineComment + 2);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[40]++;
                }

              } else {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[12]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[41]++;
                break;
              }
}
            }

          } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[8]++;}
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;

          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((revisedLine.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[15]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[43]++;
int CodeCoverConditionCoverageHelper_C10;
            // This check for shortcut mode should be redundant, but
            // it's done for safety reasons.
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((parseLine(revisedLine)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((shortcutMode) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[17]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[44]++;
              break;

            } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[18]++;}

          } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[16]++;}
        } catch (ParseException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[19]++;
          // Inform the error handler of the exception.
          errorManager.report(
              e.isFatal() ? CheckLevel.ERROR : CheckLevel.WARNING,
              JSError.make(filePath, lineNum, 0 /* char offset */,
                  e.isFatal() ? PARSE_ERROR : PARSE_WARNING,
                  e.getMessage(), line));
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[45]++;
          parseSucceeded = parseSucceeded && !e.isFatal();
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[46]++;
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[2]++;
}
  }
      }
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[20]++;
      errorManager.report(CheckLevel.ERROR,
          JSError.make(filePath, 0, 0 /* char offset */,
              PARSE_ERROR, "Error reading file: " + filePath));
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[47]++;
      parseSucceeded = false;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[48]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[1]++;
}
  }
  }

  /**
   * Called for each line of the file being parsed.
   *
   * @param line The line to parse.
   * @return true to keep going, false otherwise.
   * @throws ParseException Should be thrown to signify a problem with the line.
   */
  abstract boolean parseLine(String line) throws ParseException;

  /**
   * Parses a JS string literal.
   *
   * @param jsStringLiteral The literal. Must look like "asdf" or 'asdf'
   * @throws ParseException Thrown if there is a string literal that cannot be
   *     parsed.
   */
  String parseJsString(String jsStringLiteral) throws ParseException {
    valueMatcher.reset(jsStringLiteral);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[49]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((valueMatcher.matches()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[21]++;
      throw new ParseException("Syntax error in JS String literal", true /* fatal */);

    } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[22]++;}
    return valueMatcher.group(1) != null ? valueMatcher.group(1) : valueMatcher.group(2);
  }

  /**
   * Parses a JavaScript array of string literals. (eg: ['a', 'b', "c"]).
   * @param input A string containing a JavaScript array of string literals.
   * @return A list of parsed string literals.
   * @throws ParseException Thrown if there is a syntax error with the input.
   */
  List<String> parseJsStringArray(String input)
      throws ParseException {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[51]++;
    List<String> results = Lists.newArrayList();
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[52]++;
    int indexStart = input.indexOf('[');
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[53]++;
    int indexEnd = input.lastIndexOf(']');
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[54]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((indexStart == -1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((indexEnd == -1) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[23]++;
      throw new ParseException("Syntax error when parsing JS array", true /* fatal */);

    } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[24]++;}
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[55]++;
    String innerValues = input.substring(indexStart + 1, indexEnd);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[56]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((innerValues.trim().isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[25]++;
      valueMatcher.reset(innerValues);
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[57]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[58]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[7]++;


      for (;;) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[7]--;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[8]--;
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.loops[9]++;
}
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[59]++;
int CodeCoverConditionCoverageHelper_C15;
        // Parse the current string literal.
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((valueMatcher.lookingAt()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[27]++;
          throw new ParseException("Syntax error in JS String literal", true /* fatal */);

        } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[28]++;}
        // Add it to the results.
        results.add(valueMatcher.group(1) != null ?
            valueMatcher.group(1) : valueMatcher.group(2));
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[60]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[61]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((valueMatcher.hitEnd()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[29]++;
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[62]++;
          break;

        } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[30]++;}
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[63]++;
int CodeCoverConditionCoverageHelper_C17;
        // Ensure there is a comma after the value.
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((innerValues.charAt(valueMatcher.end()) != ',') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[31]++;
          throw new ParseException("Missing comma in string array", true /* fatal */);

        } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[32]++;}
        // Move to the next value.
        valueMatcher.region(valueMatcher.end() + 1, valueMatcher.regionEnd());
CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.statements[64]++;
      }

    } else {
  CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p.branches[26]++;}
    return results;
  }
}

class CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p ());
  }
    public static long[] statements = new long[65];
    public static long[] branches = new long[33];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.JsFileLineParser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,0,1,1,1,0,3,1,1,1,2,1,2,1,0,1,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$1puc3d5zx7bj91k5m2426zxfx3lfrds2p () {
    super("com.google.javascript.jscomp.deps.JsFileLineParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 64; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 32; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.deps.JsFileLineParser.java");
      for (int i = 1; i <= 64; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 32; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

