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

package com.google.javascript.jscomp.testing;

import com.google.javascript.jscomp.Region;
import com.google.javascript.jscomp.SourceExcerptProvider;
import com.google.javascript.jscomp.SourceFile;



/**
 * A simple source excerpt provider for testing.
 * @author nicksantos@google.com (Nick Santos)
 */
public class SimpleSourceExcerptProvider implements SourceExcerptProvider {
  static {
    CodeCoverCoverageCounter$22ubhgileq2e0beiruylw11k3d4fc1akbtd1aunwb8rge24w9t.ping();
  }


  private final SourceFile sourceFile;

  public SimpleSourceExcerptProvider(String source) {
    sourceFile = SourceFile.fromCode("input", source);
CodeCoverCoverageCounter$22ubhgileq2e0beiruylw11k3d4fc1akbtd1aunwb8rge24w9t.statements[1]++;
  }

  @Override
  public String getSourceLine(String sourceName, int lineNumber) {
    return sourceFile.getLine(lineNumber);
  }

  @Override
  public Region getSourceRegion(String sourceName, int lineNumber) {
    return sourceFile.getRegion(lineNumber);
  }
}

class CodeCoverCoverageCounter$22ubhgileq2e0beiruylw11k3d4fc1akbtd1aunwb8rge24w9t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$22ubhgileq2e0beiruylw11k3d4fc1akbtd1aunwb8rge24w9t ());
  }
    public static long[] statements = new long[2];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$22ubhgileq2e0beiruylw11k3d4fc1akbtd1aunwb8rge24w9t () {
    super("com.google.javascript.jscomp.testing.SimpleSourceExcerptProvider.java");
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
    log.startNamedSection("com.google.javascript.jscomp.testing.SimpleSourceExcerptProvider.java");
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

