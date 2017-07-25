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

/**
 * Strategies for how to do naming of anonymous functions that occur as
 * r-values in assignments and variable declarations.
 */
public enum AnonymousFunctionNamingPolicy {

  /** Don't give anonymous functions names */
  OFF(null),

  /**
   * Generates names that are based on the left-hand side of the assignment.
   * Runs after variable and property renaming, so that the generated names
   * will be short and obfuscated.
   * @see NameAnonymousFunctions
   */
  UNMAPPED(new char[] { NameAnonymousFunctions.DELIMITER }),

  /**
   * Generates short unique names and provides a mapping from them back to a
   * more meaningful name that's based on the left-hand side of the
   * assignment.
   * @see NameAnonymousFunctionsMapped
   */
  MAPPED(new char[] { NameAnonymousFunctionsMapped.PREFIX }),
  ;

  private final char[] reservedCharacters;

  AnonymousFunctionNamingPolicy(char[] reservedCharacters) {
    this.reservedCharacters = reservedCharacters;
CodeCoverCoverageCounter$2agsk08ib4pdjeve9devq6ekmbjef3wdagc0cy9ala134z1hjrtr5.statements[1]++;
  }

  /**
   * Gets characters that are reserved for use in anonymous function names and
   * can't be used in variable or property names.
   * @return reserved characters or null if no characters are reserved
   */
  public char[] getReservedCharacters() {
    // TODO(user) - for MAPPED, only the first character is reserved which
    // can be used to further optimize
    return reservedCharacters;
  }
}

class CodeCoverCoverageCounter$2agsk08ib4pdjeve9devq6ekmbjef3wdagc0cy9ala134z1hjrtr5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2agsk08ib4pdjeve9devq6ekmbjef3wdagc0cy9ala134z1hjrtr5 ());
  }
    public static long[] statements = new long[2];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2agsk08ib4pdjeve9devq6ekmbjef3wdagc0cy9ala134z1hjrtr5 () {
    super("com.google.javascript.jscomp.AnonymousFunctionNamingPolicy.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.AnonymousFunctionNamingPolicy.java");
      for (int i = 1; i <= 1; i++) {
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

