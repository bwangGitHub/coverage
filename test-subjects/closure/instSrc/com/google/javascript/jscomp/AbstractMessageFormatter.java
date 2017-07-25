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

import com.google.common.collect.ImmutableSet;
import com.google.javascript.jscomp.CheckLevel;

import java.util.Set;

/**
 * Abstract message formatter providing default behavior for implementations
 * of {@link MessageFormatter} needing a {@link SourceExcerptProvider}.
 *
 */
public abstract class AbstractMessageFormatter implements MessageFormatter {
  static {
    CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.ping();
  }

  private final SourceExcerptProvider source;
  private boolean colorize;

  public AbstractMessageFormatter(SourceExcerptProvider source) {
    this.source = source;
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.statements[1]++;
  }

  public void setColorize(boolean colorize) {
    this.colorize = colorize;
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.statements[2]++;
  }

  /**
   * Get the source excerpt provider.
   */
  protected final SourceExcerptProvider getSource() {
    return source;
  }

  private static final Set<String> SUPPORTED_COLOR_TERMINALS =
      ImmutableSet.of("xterm",
                      "xterm-color",
                      "xterm-256color",
                      "screen-bce");
  static {
    CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.statements[3]++;
  }

  static boolean termSupportsColor(String term) {
    return SUPPORTED_COLOR_TERMINALS.contains(term);
  }

  private static enum Color {
    ERROR("\033[31m"),
    WARNING("\033[35m"),
    RESET("\033[39m");

    private final String controlCharacter;

    Color(String controlCharacter) {
      this.controlCharacter = controlCharacter;
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.statements[4]++;
    }

    public String getControlCharacter() {
      return controlCharacter;
    }
  }

  String getLevelName(CheckLevel level) {
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.statements[5]++;
    switch (level) {
      case ERROR:
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.branches[1]++; return maybeColorize("ERROR", Color.ERROR);
      case WARNING:
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.branches[2]++; return maybeColorize("WARNING", Color.WARNING);
      default:
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.branches[3]++; return level.toString();
    }
  }

  private String maybeColorize(String text, Color color) {
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((colorize) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.branches[4]++; return text;
} else {
  CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap.branches[5]++;}

    return color.getControlCharacter() +
        text + Color.RESET.getControlCharacter();
  }
}

class CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap ());
  }
    public static long[] statements = new long[7];
    public static long[] branches = new long[6];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.AbstractMessageFormatter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1};
    for (int i = 1; i <= 1; i++) {
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

  public CodeCoverCoverageCounter$5vfu0l4ukylc0qfu4foxuv46xhsvh984adxhl0rerceap () {
    super("com.google.javascript.jscomp.AbstractMessageFormatter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 6; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 5; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.AbstractMessageFormatter.java");
      for (int i = 1; i <= 6; i++) {
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
    for (int i = 1; i <= 1; i++) {
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

