/*
 * Copyright 2004 The Closure Compiler Authors.
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

import com.google.javascript.rhino.Node;

import javax.annotation.Nullable;

/**
 * Compile error description
 *
 */
public class JSError {
  static {
    CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.ping();
  }

  /** A type of the error */
  private final DiagnosticType type;

  /** Description of the error */
  public final String description;

  /** Name of the source */
  public final String sourceName;

  /** Node where the warning occurred. */
  final Node node;

  /** Line number of the source */
  public final int lineNumber;

  /** @deprecated Use #getDefaultLevel */
  @Deprecated
  public final CheckLevel level;

  private final CheckLevel defaultLevel;

  // character number
  private final int charno;

  //
  // JSError.make - static factory methods for creating JSError objects
  //
  //  The general form of the arguments is
  //
  //    [source location] [level] DiagnosticType [argument ...]
  //
  //  This order echos a typical command line diagnostic.  Source location
  //  arguments are arranged to be sources of information in the order
  //  file-line-column.
  //
  //  If the level is not given, it is taken from the level of the
  //  DiagnosticType.


  /**
   * Creates a JSError with no source information
   *
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public static JSError make(DiagnosticType type, String... arguments) {
    return new JSError(null, null, -1, -1, type, null, arguments);
  }

  /**
   * Creates a JSError at a given source location
   *
   * @param sourceName The source file name
   * @param lineno Line number with source file, or -1 if unknown
   * @param charno Column number within line, or -1 for whole line.
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public static JSError make(String sourceName, int lineno, int charno,
                             DiagnosticType type, String... arguments) {
    return new JSError(sourceName, null, lineno, charno, type, null, arguments);
  }

  /**
   * Creates a JSError at a given source location
   *
   * @param sourceName The source file name
   * @param lineno Line number with source file, or -1 if unknown
   * @param charno Column number within line, or -1 for whole line.
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public static JSError make(String sourceName, int lineno, int charno,
      CheckLevel level, DiagnosticType type, String... arguments) {
    return new JSError(
        sourceName, null, lineno, charno, type, level, arguments);
  }

  /**
   * Creates a JSError from a file and Node position.
   *
   * @param sourceName The source file name
   * @param n Determines the line and char position within the source file name
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public static JSError make(String sourceName, Node n,
                             DiagnosticType type, String... arguments) {
    return new JSError(sourceName, n, type, arguments);
  }

  /**
   * Creates a JSError from a file and Node position.
   *
   * @param n Determines the line and char position and source file name
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public static JSError make(Node n, DiagnosticType type, String... arguments) {
    return new JSError(n.getSourceFileName(), n, type, arguments);
  }

  /**
   * Creates a JSError from a file and Node position.
   *
   * @param sourceName The source file name
   * @param n Determines the line and char position within the source file name
   * @param type The DiagnosticType
   * @param arguments Arguments to be incorporated into the message
   */
  public static JSError make(String sourceName, Node n, CheckLevel level,
      DiagnosticType type, String... arguments) {

    return new JSError(sourceName, n, n.getLineno(), n.getCharno(), type, level,
        arguments);
  }

  //
  //  JSError constructors
  //

  /**
   * Creates a JSError at a CheckLevel for a source file location.
   * Private to avoid any entanglement with code outside of the compiler.
   */
  private JSError(
      String sourceName, @Nullable Node node, int lineno, int charno,
      DiagnosticType type, CheckLevel level, String... arguments) {
    this.type = type;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[1]++;
    this.node = node;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[2]++;
    this.description = type.format.format(arguments);
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[3]++;
    this.lineNumber = lineno;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[4]++;
    this.charno = charno;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[5]++;
    this.sourceName = sourceName;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[6]++;
    this.defaultLevel = level == null ? type.level : level;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[7]++;
    this.level = level == null ? type.level : level;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[8]++;
  }

  /**
   * Creates a JSError for a source file location.  Private to avoid
   * any entanglement with code outside of the compiler.
   */
  private JSError(String sourceName, @Nullable Node node,
                  DiagnosticType type, String... arguments) {
    this(sourceName,
         node,
         (node != null) ? node.getLineno() : -1,
         (node != null) ? node.getCharno() : -1,
         type, null, arguments);
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[9]++;
  }

  public DiagnosticType getType() {
    return type;
  }

  /**
   * Format a message at the given level.
   *
   * @return the formatted message or {@code null}
   */
  public String format(CheckLevel level, MessageFormatter formatter) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[10]++;
    switch (level) {
      case ERROR:
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[1]++;
        return formatter.formatError(this);

      case WARNING:
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[2]++;
        return formatter.formatWarning(this);

      default:
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[3]++;
        return null;
    }
  }

  @Override
  public String toString() {
    // TODO(user): remove custom toString.
    return type.key + ". " + description + " at " +
      (sourceName != null && sourceName.length() > 0 ?
       sourceName : "(unknown source)") + " line " +
      (lineNumber != -1 ? String.valueOf(lineNumber) : "(unknown line)") +
      " : " + (charno != -1 ? String.valueOf(charno) : "(unknown column)");
  }

  /**
   * Get the character number.
   */
  public int getCharno() {
    return charno;
  }

  /**
   * Get the line number. One-based.
   */
  public int getLineNumber() {
    return lineNumber;
  }

  /**
   * @return the offset of the region the Error applies to, or -1 if the offset
   *         is unknown.
   */
  public int getNodeSourceOffset() {
    return node != null ? node.getSourceOffset() : -1;
  }

  /**
   * @return the length of the region the Error applies to, or 0 if the length
   *         is unknown.
   */
  public int getNodeLength() {
    return node != null ? node.getLength() : 0;
  }

  /** The default level, before any of the WarningsGuards are applied. */
  public CheckLevel getDefaultLevel() {
    return defaultLevel;
  }

  @Override
  public boolean equals(Object o) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    // Generated by Intellij IDEA
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((this == o) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[4]++;
      return true;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[5]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((o == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getClass() != o.getClass()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[6]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[7]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[13]++;

    JSError jsError = (JSError) o;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((charno != jsError.charno) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[8]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[9]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lineNumber != jsError.lineNumber) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[10]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[11]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((description.equals(jsError.description)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[12]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[13]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((defaultLevel != jsError.defaultLevel) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[14]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[15]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[18]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 ? !
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((sourceName.equals(jsError.sourceName)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 : 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((jsError.sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 3) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[16]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[17]++;}
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type.equals(jsError.type)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[18]++;
      return false;

    } else {
  CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.branches[19]++;}

    return true;
  }

  @Override
  public int hashCode() {
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[20]++;
    // Generated by Intellij IDEA
    int result = type.hashCode();
    result = 31 * result + description.hashCode();
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[21]++;
    result = 31 * result + (sourceName != null ? sourceName.hashCode() : 0);
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[22]++;
    result = 31 * result + lineNumber;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[23]++;
    result = 31 * result + defaultLevel.hashCode();
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[24]++;
    result = 31 * result + charno;
CodeCoverCoverageCounter$28a96e3okncv2f3tmjl.statements[25]++;
    return result;
  }
}

class CodeCoverCoverageCounter$28a96e3okncv2f3tmjl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$28a96e3okncv2f3tmjl ());
  }
    public static long[] statements = new long[26];
    public static long[] branches = new long[20];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JSError.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,3,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$28a96e3okncv2f3tmjl () {
    super("com.google.javascript.jscomp.JSError.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 25; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 19; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JSError.java");
      for (int i = 1; i <= 25; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 19; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

