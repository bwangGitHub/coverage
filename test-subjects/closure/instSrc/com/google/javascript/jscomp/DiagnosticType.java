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
package com.google.javascript.jscomp;

import com.google.javascript.jscomp.CheckLevel;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * The type of a compile or analysis error.
 *
 */
public class DiagnosticType
    implements Comparable<DiagnosticType>, Serializable {
  static {
    CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl.ping();
  }

  private static final long serialVersionUID = 1;
  static {
    CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl.statements[1]++;
  }

  /**
   * The error type. Used as the BugPattern and BugInstance types by
   * BugBot's XML
   */
  public final String key;

  /** The default way to format errors */
  public final MessageFormat format;

  /** Default level */
  public final CheckLevel defaultLevel;

  /** Reporting level, initially the defaultLevel but may be changed. */
  public CheckLevel level;

  /**
   * Create a DiagnosticType at level CheckLevel.ERROR
   *
   * @param name An identifier
   * @param descriptionFormat A format string
   * @return A new DiagnosticType
   */
  public static DiagnosticType error(String name, String descriptionFormat) {
    return make(name, CheckLevel.ERROR, descriptionFormat);
  }

  /**
   * Create a DiagnosticType at level CheckLevel.WARNING
   *
   * @param name An identifier
   * @param descriptionFormat A format string
   * @return A new DiagnosticType
   */
  public static DiagnosticType warning(String name, String descriptionFormat) {
    return make(name, CheckLevel.WARNING, descriptionFormat);
  }

  /**
   * Create a DiagnosticType at level CheckLevel.OFF
   *
   * @param name An identifier
   * @param descriptionFormat A format string
   * @return A new DiagnosticType
   */
  public static DiagnosticType disabled(String name,
      String descriptionFormat) {
    return make(name, CheckLevel.OFF, descriptionFormat);
  }

  /**
   * Create a DiagnosticType at a given CheckLevel.
   *
   * @param name An identifier
   * @param level Either CheckLevel.ERROR or CheckLevel.WARNING
   * @param descriptionFormat A format string
   * @return A new DiagnosticType
   */
  public static DiagnosticType make(String name, CheckLevel level,
                                    String descriptionFormat) {
    return
        new DiagnosticType(name, level, new MessageFormat(descriptionFormat));
  }

  /**
   * Create a DiagnosticType. Private to force use of static factory methods.
   */
  private DiagnosticType(String key, CheckLevel level, MessageFormat format) {
    this.key = key;
CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl.statements[2]++;
    this.defaultLevel = level;
CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl.statements[3]++;
    this.format = format;
CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl.statements[4]++;

    this.level = this.defaultLevel;
CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl.statements[5]++;
  }

  /**
   * Create a description from the MessageFormat and the arguments.
   * Used by unit tests.
   */
  String format(Object ... arguments) {
    return format.format(arguments);
  }

  @Override
  public boolean equals(Object type) {
    return type instanceof DiagnosticType &&
        ((DiagnosticType) type).key.equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(DiagnosticType diagnosticType) {
    return key.compareTo(diagnosticType.key);
  }

  @Override
  public String toString() {
    return key + ": " + format.toPattern();
  }
}

class CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl ());
  }
    public static long[] statements = new long[6];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$14gejmigkomhbmc10s2nn3fb5bxkrl () {
    super("com.google.javascript.jscomp.DiagnosticType.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 5; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.DiagnosticType.java");
      for (int i = 1; i <= 5; i++) {
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

