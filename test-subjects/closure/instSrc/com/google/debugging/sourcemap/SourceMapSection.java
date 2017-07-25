/*
 * Copyright 2011 The Closure Compiler Authors.
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
 * A class representing a partial source map.
 * @author johnlenz@google.com (John Lenz)
 */
public class SourceMapSection {
  static {
    CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.ping();
  }


  /**
   * A URL for a valid source map file that represents a section of a generate
   * source file such as when multiple files are concatenated together.
   */
  private final String value;
  private final int line;
  private final int column;
  private final SectionType type;

  public static enum SectionType {
    URL,
    MAP
  }

  /**
   * @param sectionUrl The URL for the partial source map
   * @param line The number of lines into the file where the represented section
   *    starts.
   * @param column The number of characters into the line where the represented
   *    section starts.
   * @deprecated
   */
  @Deprecated
  public SourceMapSection(String sectionUrl, int line, int column) {
    this.type = SectionType.URL;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[1]++;
    this.value = sectionUrl;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[2]++;
    this.line = line;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[3]++;
    this.column = column;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[4]++;
  }

  private SourceMapSection(
      SectionType type, String value, int line, int column) {
    this.type = type;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[5]++;
    this.value = value;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[6]++;
    this.line = line;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[7]++;
    this.column = column;
CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl.statements[8]++;
  }

  public static SourceMapSection forMap(String value, int line, int column) {
    return new SourceMapSection(SectionType.MAP, value, line, column);
  }

  public static SourceMapSection forURL(String value, int line, int column) {
    return new SourceMapSection(SectionType.URL, value, line, column);
  }

  public SectionType getSectionType() {
    return this.type;
  }

  /**
   * @return the name of the map
   * @deprecated
   */
  @Deprecated
  public String getSectionUrl() {
    assert(type.equals(SectionType.URL));
    return value;
  }

  /**
   * @return the value that represents the map for this section.
   */
  public String getSectionValue() {
    return value;
  }

  /**
   * @return the starting line for this section
   */
  public int getLine() {
    return line;
  }

  /**
   * @return the column for this section
   */
  public int getColumn() {
    return column;
  }
}

class CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1xb0tvclheu2m48ewx5ayxt4sm7fml6jl () {
    super("com.google.debugging.sourcemap.SourceMapSection.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
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
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapSection.java");
      for (int i = 1; i <= 8; i++) {
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

