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

import com.google.common.base.Strings;

import com.google.javascript.jscomp.CheckLevel;

/**
 * Verbose message formatter. This formatter generates very loud and long
 * messages with multi-line source excerpts.
 *
 */
class VerboseMessageFormatter extends AbstractMessageFormatter {
  static {
    CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.ping();
  }

  VerboseMessageFormatter(SourceExcerptProvider source) {
    super(source);
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[1]++;
  }

  @Override
  public String formatError(JSError error) {
    return getLevelName(CheckLevel.ERROR) + ": " + format(error);
  }

  @Override
  public String formatWarning(JSError warning) {
    return getLevelName(CheckLevel.WARNING) + ": " + format(warning);
  }

  private String format(JSError message) {
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[2]++;
    String description = message.description;
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[3]++;
    String sourceName = message.sourceName;
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[4]++;
    int lineNumber = message.lineNumber;
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[5]++;
    Region sourceRegion = getSource().getSourceRegion(sourceName, lineNumber);
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[6]++;
    String lineSource = null;
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sourceRegion != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.branches[1]++;
      lineSource = sourceRegion.getSourceExcerpt();
CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.statements[8]++;

    } else {
  CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl.branches[2]++;}
    return String.format("%s at %s line %s %s", description,
        (Strings.isNullOrEmpty(sourceName) ? "(unknown source)" : sourceName),
        ((lineNumber < 0) ? String.valueOf(lineNumber) : "(unknown line)"),
        ((lineSource != null) ? ":\n\n" + lineSource : "."));
  }
}

class CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl ());
  }
    public static long[] statements = new long[9];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.VerboseMessageFormatter.java";
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

  public CodeCoverCoverageCounter$13aebhn7b3wxkjue87vjijpy3n704czghbjxoxmgczfl () {
    super("com.google.javascript.jscomp.VerboseMessageFormatter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 8; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.VerboseMessageFormatter.java");
      for (int i = 1; i <= 8; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
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

