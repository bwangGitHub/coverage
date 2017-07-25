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

import com.google.debugging.sourcemap.proto.Mapping.OriginalMapping;

import java.util.Collection;

/**
 * A SourceMappingReversable is a SourceMapping that can provide the reverse
 * (source --> target) source mapping.
 */
public interface SourceMappingReversable extends SourceMapping {

  /**
   * @return the collection of original sources in this source mapping
   */
  public Collection<String> getOriginalSources();

  /**
   * Given a source file, line, and column, return the reverse mapping (source --> target).
   * A collection is returned as in some cases (like a function being inlined), one source line
   * may map to more then one target location. An empty collection is returned if there were
   * no matches.
   * @param originalFile the source file
   * @param line the source line
   * @param column the source column
   * @return the reverse mapping (source --> target)
   */
  public Collection<OriginalMapping> getReverseMapping(String originalFile, int line, int column);

}

class CodeCoverCoverageCounter$11xxamgnqcq7u71cvjrpb6ddqtv8jjpyk9sibq0nj4v5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11xxamgnqcq7u71cvjrpb6ddqtv8jjpyk9sibq0nj4v5 ());
  }
    public static long[] statements = new long[0];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$11xxamgnqcq7u71cvjrpb6ddqtv8jjpyk9sibq0nj4v5 () {
    super("com.google.debugging.sourcemap.SourceMappingReversable.java");
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
    log.startNamedSection("com.google.debugging.sourcemap.SourceMappingReversable.java");
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

