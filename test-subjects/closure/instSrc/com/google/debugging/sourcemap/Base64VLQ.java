/*
 * Copyright 2011 The Closure Compiler Authors. All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *  * Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  * Redistributions in binary form must reproduce the above
 *    copyright notice, this list of conditions and the following
 *    disclaimer in the documentation and/or other materials provided
 *    with the distribution.
 *  * Neither the name of Google Inc. nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.google.debugging.sourcemap;

import java.io.IOException;

/**
 * We encode our variable length numbers as base64 encoded strings with
 * the least significant digit coming first.  Each base64 digit encodes
 * a 5-bit value (0-31) and a continuation bit.  Signed values can be
 * represented by using the least significant bit of the value as the
 * sign bit.
 *
 * @author johnlenz@google.com (John Lenz)
 */
final class Base64VLQ {
  static {
    CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.ping();
  }

  // Utility class.
  private Base64VLQ() {}

  // A Base64 VLQ digit can represent 5 bits, so it is base-32.
  private static final int VLQ_BASE_SHIFT = 5;
  static {
    CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[1]++;
  }
  private static final int VLQ_BASE = 1 << VLQ_BASE_SHIFT;
  static {
    CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[2]++;
  }

  // A mask of bits for a VLQ digit (11111), 31 decimal.
  private static final int VLQ_BASE_MASK = VLQ_BASE-1;
  static {
    CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[3]++;
  }

  // The continuation bit is the 6th bit.
  private static final int VLQ_CONTINUATION_BIT = VLQ_BASE;
  static {
    CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[4]++;
  }

  /**
   * Converts from a two-complement value to a value where the sign bit is
   * is placed in the least significant bit.  For example, as decimals:
   *   1 becomes 2 (10 binary), -1 becomes 3 (11 binary)
   *   2 becomes 4 (100 binary), -2 becomes 5 (101 binary)
   */
  private static int toVLQSigned(int value) {
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.branches[1]++;
      return ((-value) << 1) + 1;

    } else {
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.branches[2]++;
      return (value << 1) + 0;
    }
  }

  /**
   * Converts to a two-complement value from a value where the sign bit is
   * is placed in the least significant bit.  For example, as decimals:
   *   2 (10 binary) becomes 1, 3 (11 binary) becomes -1
   *   4 (100 binary) becomes 2, 5 (101 binary) becomes -2
   */
  private static int fromVLQSigned(int value) {
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[6]++;
    boolean negate = (value & 1) == 1;
    value = value >> 1;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[7]++;
    return negate ? -value : value;
  }

  /**
   * Writes a VLQ encoded value to the provide appendable.
   * @throws IOException
   */
  public static void encode(Appendable out, int value)
      throws IOException {
    value = toVLQSigned(value);
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[8]++;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[1]--;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[2]--;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[3]++;
}
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[10]++;
      int digit = value & VLQ_BASE_MASK;
      value >>>= VLQ_BASE_SHIFT;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[11]++;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((value > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.branches[3]++;
        digit |= VLQ_CONTINUATION_BIT;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[13]++;

      } else {
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.branches[4]++;}
      out.append(Base64.toBase64(digit));
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[14]++;
    } while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false));
  }

  /**
   * A simple interface for advancing through a sequence of characters, that
   * communicates that advance back to the source.
   */
  interface CharIterator {
    boolean hasNext();
    char next();
  }

  /**
   * Decodes the next VLQValue from the provided CharIterator.
   */
  public static int decode(CharIterator in) {
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[15]++;
    int result = 0;
    boolean continuation;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[16]++;
    int shift = 0;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[17]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
    do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[4]--;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[5]--;
  CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.loops[6]++;
}
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[18]++;
      char c = in.next();
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[19]++;
      int digit = Base64.fromBase64(c);
      continuation = (digit & VLQ_CONTINUATION_BIT) != 0;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[20]++;
      digit &= VLQ_BASE_MASK;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[21]++;
      result = result + (digit << shift);
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[22]++;
      shift = shift + VLQ_BASE_SHIFT;
CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.statements[23]++;
    } while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((continuation) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false));

    return fromVLQSigned(result);
  }
}

class CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.Base64VLQ.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$2spvqux0rcg9vne8foawdd () {
    super("com.google.debugging.sourcemap.Base64VLQ.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.Base64VLQ.java");
      for (int i = 1; i <= 23; i++) {
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
    for (int i = 1; i <= 4; i++) {
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

