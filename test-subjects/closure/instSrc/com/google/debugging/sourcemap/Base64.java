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

import java.util.Arrays;

/**
 * A utility class for working with Base64 values.
 * @author johnlenz@google.com (John Lenz)
 */
public final class Base64 {
  static {
    CodeCoverCoverageCounter$a2zww15lxltdhk76p.ping();
  }


  // This is a utility class
  private Base64() {}

  /**
   *  A map used to convert integer values in the range 0-63 to their base64
   *  values.
   */
  private static final String BASE64_MAP =
      "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
      "abcdefghijklmnopqrstuvwxyz" +
      "0123456789+/";
  static {
    CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[1]++;
  }

  /**
   * A map used to convert base64 character into integer values.
   */
  private static final int[] BASE64_DECODE_MAP = new int[256];
  static {
    CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[2]++;
  }
  static {
      Arrays.fill(BASE64_DECODE_MAP, -1);
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[3]++;
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
      for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < BASE64_MAP.length()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a2zww15lxltdhk76p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$a2zww15lxltdhk76p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[1]--;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[2]--;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[3]++;
}
        BASE64_DECODE_MAP[BASE64_MAP.charAt(i)] = i;
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[5]++;
      }
  }

  /**
   * @param value A value in the range of 0-63.
   * @return a base64 digit.
   */
  public static char toBase64(int value) {
    assert (value <= 63 && value >= 0) : "value out of range:" + value;
    return BASE64_MAP.charAt(value);
  }

  /**
   * @param c A base64 digit.
   * @return A value in the range of 0-63.
   */
  public static int fromBase64(char c) {
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[6]++;
    int result = BASE64_DECODE_MAP[c];
    assert (result != -1) : "invalid char";
    return BASE64_DECODE_MAP[c];
  }

  /**
   * @param value an integer to base64 encode.
   * @return the six digit long base64 encoded value of the integer.
   */
  public static String base64EncodeInt(int value) {
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[7]++;
    char[] c = new char[6];
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[8]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < 5) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$a2zww15lxltdhk76p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$a2zww15lxltdhk76p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[4]--;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[5]--;
  CodeCoverCoverageCounter$a2zww15lxltdhk76p.loops[6]++;
}
      c[i] = Base64.toBase64((value >> (26 - i * 6)) & 0x3f);
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[9]++;
    }
    c[5] = Base64.toBase64((value << 4) & 0x3f);
CodeCoverCoverageCounter$a2zww15lxltdhk76p.statements[10]++;
    return new String(c);
  }
}

class CodeCoverCoverageCounter$a2zww15lxltdhk76p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$a2zww15lxltdhk76p ());
  }
    public static long[] statements = new long[11];
    public static long[] branches = new long[0];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.Base64.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1};
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
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$a2zww15lxltdhk76p () {
    super("com.google.debugging.sourcemap.Base64.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 10; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= -1; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.Base64.java");
      for (int i = 1; i <= 10; i++) {
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
    for (int i = 1; i <= 2; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

