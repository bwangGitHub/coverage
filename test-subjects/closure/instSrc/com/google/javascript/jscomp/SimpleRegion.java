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
 * Simple region.
 */
public class SimpleRegion implements Region {
  static {
    CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h.ping();
  }

  private final int beginningLineNumber;
  private final int endingLineNumber;
  private final String source;

  public SimpleRegion(int beginningLineNumber, int endingLineNumber,
      String source) {
    this.beginningLineNumber = beginningLineNumber;
CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h.statements[1]++;
    this.endingLineNumber = endingLineNumber;
CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h.statements[2]++;
    this.source = source;
CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h.statements[3]++;
  }

  @Override
  public int getBeginningLineNumber() {
    return beginningLineNumber;
  }

  @Override
  public int getEndingLineNumber() {
    return endingLineNumber;
  }

  @Override
  public String getSourceExcerpt() {
    return source;
  }
}

class CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[0];
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$z46a9kmk2ig7kqoz69nqtbq44h () {
    super("com.google.javascript.jscomp.SimpleRegion.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.SimpleRegion.java");
      for (int i = 1; i <= 3; i++) {
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
