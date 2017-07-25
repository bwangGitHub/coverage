/*
 * Copyright 2007 The Closure Compiler Authors.
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
 * A source excerpt provider is responsible for building source code excerpt
 * of specific locations, such as a specific line or a region around a
 * given line number.
 *
 */
public interface SourceExcerptProvider {
  /**
   * Source excerpt variety.
   */
  enum SourceExcerpt {
    /**
     * Line excerpt.
     */
    LINE {
      @Override
      public String get(SourceExcerptProvider source, String sourceName,
          int lineNumber, ExcerptFormatter formatter) {
        return formatter.formatLine(
            source.getSourceLine(sourceName, lineNumber), lineNumber);
      }
    },
    /**
     * Region excerpt.
     */
    REGION {
      @Override
      public String get(SourceExcerptProvider source, String sourceName,
          int lineNumber, ExcerptFormatter formatter) {
        return formatter.formatRegion(
            source.getSourceRegion(sourceName, lineNumber));
      }
    };

    /**
     * Get a source excerpt string based on the type of the source excerpt.
     */
    public abstract String get(SourceExcerptProvider source, String sourceName,
        int lineNumber, ExcerptFormatter formatter);
  }

  /**
   * Get the line indicated by the line number. This call will return only the
   * specific line.
   *
   * @param lineNumber the line number, 1 being the first line of the file
   * @return the line indicated, or {@code null} if it does not exist
   */
  String getSourceLine(String sourceName, int lineNumber);

  /**
   * Get a region around the indicated line number. The exact definition of a
   * region is implementation specific, but it must contain the line indicated
   * by the line number. A region must not start or end by a carriage return.
   *
   * @param lineNumber the line number, 1 being the first line of the file
   * @return the region around the line number indicated, or <code>null</null>
   * if it does not exist
   */
  Region getSourceRegion(String sourceName, int lineNumber);

  /**
   * A excerpt formatter is responsible of formatting source excerpts.
   */
  interface ExcerptFormatter {
    /**
     * Format a line excerpt.
     */
    String formatLine(String line, int lineNumber);

    /**
     * Format a region excerpt.
     */
    String formatRegion(Region region);
  }
}

class CodeCoverCoverageCounter$r0f5rqexcmdqa0jxdhq53mmrhd4779npvvsp76n5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$r0f5rqexcmdqa0jxdhq53mmrhd4779npvvsp76n5 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$r0f5rqexcmdqa0jxdhq53mmrhd4779npvvsp76n5 () {
    super("com.google.javascript.jscomp.SourceExcerptProvider.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= -1; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.SourceExcerptProvider.java");
      for (int i = 1; i <= -1; i++) {
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

