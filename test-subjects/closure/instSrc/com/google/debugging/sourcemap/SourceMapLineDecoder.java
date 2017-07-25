/*
 * Copyright 2010 The Closure Compiler Authors.
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

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Class for parsing the line maps in SourceMap v2.
 *
 * @author johnlenz@google.com (John Lenz)
 * @author jschorr@google.com (Joseph Schorr)
 */
class SourceMapLineDecoder {
  static {
    CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.ping();
  }


  /**
   * Decodes a line in a character map into a list of mapping IDs.
   */
  static List<Integer> decodeLine(String lineSource) {
    return decodeLine(new StringParser(lineSource));
  }

  private SourceMapLineDecoder() {}

  static LineEntry decodeLineEntry(String in, int lastId) {
    return decodeLineEntry(new StringParser(in), lastId);
  }

  private static LineEntry decodeLineEntry(StringParser reader, int lastId) {
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[1]++;
    int repDigits = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

    // Determine the number of digits used for the repetition count.
    // Each "!" indicates another base64 digit.
    for (char peek = reader.peek();(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((peek == '!') && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); peek = reader.peek()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[1]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[2]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[3]++;
}
      repDigits++;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[3]++;
      reader.next();
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[4]++; // consume the "!"
    }
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[5]++;

    int idDigits = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[6]++;
    int reps = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((repDigits == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.branches[1]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[8]++;
      // No repetition digit escapes, so the next character represents the
      // number of digits in the id (bottom 2 bits) and the number of
      // repetitions (top 4 digits).
      char digit = reader.next();
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[9]++;
      int value = addBase64Digit(digit, 0);
      reps = (value >> 2);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[10]++;
      idDigits = (value & 3);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[11]++;

    } else {
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.branches[2]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[12]++;
      char digit = reader.next();
      idDigits = addBase64Digit(digit, 0);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[13]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[14]++;

      int value = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < repDigits) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[4]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[5]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[6]++;
}
        digit = reader.next();
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[16]++;
        value = addBase64Digit(digit, value);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[17]++;
      }
      reps = value;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[18]++;
    }

    // Adjust for 1 offset encoding.
    reps += 1;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[19]++;
    idDigits += 1;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[20]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[21]++;

    // Decode the id token.
    int value = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[22]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[7]++;


int CodeCoverConditionCoverageHelper_C4;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i < idDigits) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[7]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[8]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[9]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[23]++;
      char digit = reader.next();
      value = addBase64Digit(digit, value);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[24]++;
    }
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[25]++;
    int mappingId = getIdFromRelativeId(value, idDigits, lastId);
    return new LineEntry(mappingId, reps);
  }

  private static List<Integer> decodeLine(StringParser reader) {
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[26]++;
    List<Integer> result = Lists.newArrayListWithCapacity(512);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[27]++;
    int lastId = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[28]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[10]++;


int CodeCoverConditionCoverageHelper_C5;
    while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((reader.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[10]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[11]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[12]++;
}
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[29]++;
      LineEntry entry = decodeLineEntry(reader, lastId);
      lastId = entry.id;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[30]++;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[31]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[13]++;


int CodeCoverConditionCoverageHelper_C6;

      for (int i=0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < entry.reps) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[13]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[14]--;
  CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.loops[15]++;
}
        result.add(entry.id);
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[32]++;
      }
    }

    return result;
  }

  /**
   * Build base64 number a digit at a time, most significant digit first.
   */
  private static int addBase64Digit(char digit, int previousValue) {
    return (previousValue * 64) + Base64.fromBase64(digit);
  }

  /**
   * @return the id from the relative id.
   */
  static int getIdFromRelativeId(int rawId, int digits, int lastId) {
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[33]++;
    // The value range depends on the number of digits
    int base = 1 << (digits * 6);
    return ((rawId >= base/2) ? rawId - base : rawId) + lastId;
  }

  /**
   * Simple class for tracking a single entry in a line map.
   */
  static class LineEntry {
    final int id;
    final int reps;
    public LineEntry(int id, int reps) {
      this.id = id;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[34]++;
      this.reps = reps;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[35]++;
    }
  }

  /**
   * A simple class for maintaining the current location
   * in the input.
   */
  static class StringParser {
    final String content;
    int current = 0;
  {
    CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[36]++;
  }

    StringParser(String content) {
      this.content = content;
CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d.statements[37]++;
    }

    char next() {
      return content.charAt(current++);
    }

    char peek() {
      return content.charAt(current);
    }

    boolean hasNext() {
      return current < content.length() -1;
    }
  }
}

class CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d ());
  }
    public static long[] statements = new long[38];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.SourceMapLineDecoder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$3sqvqynh2c9zerfpqbc37es931sxr5yv3nkld9d () {
    super("com.google.debugging.sourcemap.SourceMapLineDecoder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 37; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.SourceMapLineDecoder.java");
      for (int i = 1; i <= 37; i++) {
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
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

