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
 * @author johnlenz@google.com (John Lenz)
 */
public class SourceMapGeneratorFactory {
  static {
    CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.ping();
  }


  /**
   * @return The appropriate source map object for the given source map format.
   */
  public static SourceMapGenerator getInstance() {
    return getInstance(SourceMapFormat.DEFAULT);
  }

  /**
   * @return The appropriate source map object for the given source map format.
   */
  public static SourceMapGenerator getInstance(SourceMapFormat format) {
CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.statements[1]++;
    switch (format) {
      case V1:
CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.branches[1]++;
        return new SourceMapGeneratorV1();
      case V2:
CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.branches[2]++;
        return new SourceMapGeneratorV2();
      case DEFAULT:
CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.branches[3]++;
      case V3:
CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.branches[4]++;
        return new SourceMapGeneratorV3();
      default:
CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5.branches[5]++;
        throw new IllegalStateException("unsupported source map format");
    }
  }
}

class CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5 ());
  }
    public static long[] statements = new long[2];
    public static long[] branches = new long[6];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1hanybwc76v73y09p23a3tcfft87oo1da7wms3elycwy3j5 () {
    super("com.google.debugging.sourcemap.SourceMapGeneratorFactory.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 5; i++) {
        branches[i] = 0L;
      }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapGeneratorFactory.java");
      for (int i = 1; i <= 1; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 5; i++) {
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

