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

import com.google.javascript.jscomp.CheckLevel;

import java.io.PrintStream;

/**
 * <p>An error manager that prints errors and warnings to the print stream
 * provided in addition to the functionality of the
 * {@link BasicErrorManager}.</p>
 *
 * <p>It collaborates with a {@link SourceExcerptProvider} via a
 * {@link MessageFormatter} to display error messages with source context.</p>
 *
 */
public class PrintStreamErrorManager extends BasicErrorManager {
  static {
    CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.ping();
  }

  private final MessageFormatter formatter;
  private final PrintStream stream;
  private int summaryDetailLevel = 1;
  {
    CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[1]++;
  }

  /**
   * Creates an error manager.
   * @param formatter the message formatter used to format the messages
   * @param stream the stream on which the errors and warnings should be
   *     printed. This class does not close the stream
   */
  public PrintStreamErrorManager(MessageFormatter formatter,
                                 PrintStream stream) {
    this.formatter = formatter;
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[2]++;
    this.stream = stream;
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[3]++;
  }

  /**
   * Creates an instance with a source-less error formatter.
   */
  public PrintStreamErrorManager(PrintStream stream) {
    this(ErrorFormat.SOURCELESS.toFormatter(null, false), stream);
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[4]++;
  }

  @Override
  public void println(CheckLevel level, JSError error) {
    stream.println(error.format(level, formatter));
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[5]++;
  }

  public void setSummaryDetailLevel(int summaryDetailLevel) {
    this.summaryDetailLevel = summaryDetailLevel;
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[6]++;
  }

  @Override
  public void printSummary() {
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (512)) == 0 || true) &&
 ((summaryDetailLevel >= 3) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (256)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((summaryDetailLevel >= 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((getErrorCount() + getWarningCount() > 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((summaryDetailLevel >= 2) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((getTypedPercent() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 5) || true)) || (CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 5) && false)) {
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.branches[1]++;
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((getTypedPercent() > 0.0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.branches[3]++;
        stream.format("%d error(s), %d warning(s), %.1f%% typed%n",
            getErrorCount(), getWarningCount(), getTypedPercent());
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[9]++;

      } else {
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.branches[4]++;
        stream.format("%d error(s), %d warning(s)%n", getErrorCount(),
            getWarningCount());
CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.statements[10]++;
      }

    } else {
  CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p.branches[2]++;}
  }
}

class CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PrintStreamErrorManager.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1};
    for (int i = 1; i <= 2; i++) {
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

  public CodeCoverCoverageCounter$10l00q6qcv7p3vqw50jsk23tkupeh8yzs5de0wmz3b6p () {
    super("com.google.javascript.jscomp.PrintStreamErrorManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PrintStreamErrorManager.java");
      for (int i = 1; i <= 10; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 2; i++) {
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

