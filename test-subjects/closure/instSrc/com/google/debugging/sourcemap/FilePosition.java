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

package com.google.debugging.sourcemap;

/**
 * Represents a position in a source file.
 *
 */
public class FilePosition {
  static {
    CodeCoverCoverageCounter$tn5cpdmdknjo9tn7eimjo8r7fl.ping();
  }

  private final int line;
  private final int column;

  public FilePosition(int line, int column) {
    this.line = line;
CodeCoverCoverageCounter$tn5cpdmdknjo9tn7eimjo8r7fl.statements[1]++;
    this.column = column;
CodeCoverCoverageCounter$tn5cpdmdknjo9tn7eimjo8r7fl.statements[2]++;
  }

  /**
   * Returns the line number of this position.
   * Note: The v1 and v2 source maps use a line number with the first line
   * being 1, whereas the v3 source map corrects this and uses a first line
   * number of 0 to be consistent with the column representation.
   */
  public int getLine() {
    return line;
  }

  /**
   * @return the character index on the line
   * of this position, with the first column being 0.
   */
  public int getColumn() {
    return column;
  }
}

class CodeCoverCoverageCounter$tn5cpdmdknjo9tn7eimjo8r7fl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$tn5cpdmdknjo9tn7eimjo8r7fl ());
  }
    public static long[] statements = new long[3];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$tn5cpdmdknjo9tn7eimjo8r7fl () {
    super("com.google.debugging.sourcemap.FilePosition.java");
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
    log.startNamedSection("com.google.debugging.sourcemap.FilePosition.java");
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

