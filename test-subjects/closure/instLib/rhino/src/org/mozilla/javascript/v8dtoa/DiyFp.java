// Copyright 2010 the V8 project authors. All rights reserved.
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are
// met:
//
//     * Redistributions of source code must retain the above copyright
//       notice, this list of conditions and the following disclaimer.
//     * Redistributions in binary form must reproduce the above
//       copyright notice, this list of conditions and the following
//       disclaimer in the documentation and/or other materials provided
//       with the distribution.
//     * Neither the name of Google Inc. nor the names of its
//       contributors may be used to endorse or promote products derived
//       from this software without specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
// "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
// LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
// A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
// OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
// SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
// LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
// DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
// THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
// (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

// Ported to Java from Mozilla's version of V8-dtoa by Hannes Wallnoefer.
// The original revision was 67d1049b0bf9 from the mozilla-central tree.

package org.mozilla.javascript.v8dtoa;

// This "Do It Yourself Floating Point" class implements a floating-point number
// with a uint64 significand and an int exponent. Normalized DiyFp numbers will
// have the most significant bit of the significand set.
// Multiplication and Subtraction do not normalize their results.
// DiyFp are not designed to contain special doubles (NaN and Infinity).
class DiyFp {
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.ping();
  }


    private long f;
    private int e;

    static final int kSignificandSize = 64;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[1]++;
  }
    static final long kUint64MSB = 0x8000000000000000L;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[2]++;
  }


    DiyFp() {
        this.f = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[3]++;
        this.e = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[4]++;
    }

    DiyFp(long f, int e) {
        this.f = f;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[5]++;
        this.e = e;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[6]++;
    }

    private static boolean uint64_gte(long a, long b) {
        // greater-or-equal for unsigned int64 in java-style...
        return (a == b) || ((a > b) ^ (a < 0) ^ (b < 0));
    }

    // this = this - other.
    // The exponents of both numbers must be the same and the significand of this
    // must be bigger than the significand of other.
    // The result will not be normalized.
    void subtract(DiyFp other) {
        assert (e == other.e);
        assert uint64_gte(f, other.f);
        f -= other.f;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[7]++;
    }

    // Returns a - b.
    // The exponents of both numbers must be the same and this must be bigger
    // than other. The result will not be normalized.
    static DiyFp minus(DiyFp a, DiyFp b) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[8]++;
        DiyFp result = new DiyFp(a.f, a.e);
        result.subtract(b);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[9]++;
        return result;
    }


    // this = this * other.
    void multiply(DiyFp other) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[10]++;
        // Simply "emulates" a 128 bit multiplication.
        // However: the resulting number only contains 64 bits. The least
        // significant 64 bits are only used for rounding the most significant 64
        // bits.
        final long kM32 = 0xFFFFFFFFL;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[11]++;
        long a = f >>> 32;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[12]++;
        long b = f & kM32;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[13]++;
        long c = other.f >>> 32;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[14]++;
        long d = other.f & kM32;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[15]++;
        long ac = a * c;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[16]++;
        long bc = b * c;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[17]++;
        long ad = a * d;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[18]++;
        long bd = b * d;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[19]++;
        long tmp = (bd >>> 32) + (ad & kM32) + (bc & kM32);
        // By adding 1U << 31 to tmp we round the final result.
        // Halfway cases will be round up.
        tmp += 1L << 31;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[20]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[21]++;
        long result_f = ac + (ad >>> 32) + (bc >>> 32) + (tmp >>> 32);
        e += other.e + 64;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[22]++;
        f = result_f;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[23]++;
    }

    // returns a * b;
    static DiyFp times(DiyFp a, DiyFp b) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[24]++;
        DiyFp result = new DiyFp(a.f, a.e);
        result.multiply(b);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[25]++;
        return result;
    }

    void normalize() {
        assert(f != 0);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[26]++;
        long f = this.f;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[27]++;
        int e = this.e;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[28]++;

        // This method is mainly called for normalizing boundaries. In general
        // boundaries need to be shifted by 10 bits. We thus optimize for this case.
        final long k10MSBits = 0xFFC00000L << 32;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[29]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (((f & k10MSBits) == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[1]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[2]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[3]++;
}
            f <<= 10;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[30]++;
            e -= 10;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[31]++;
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[32]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[4]++;


int CodeCoverConditionCoverageHelper_C2;
        while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((f & kUint64MSB) == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[4]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[5]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.loops[6]++;
}
            f <<= 1;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[33]++;
            e--;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[34]++;
        }
        this.f = f;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[35]++;
        this.e = e;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[36]++;
    }

    static DiyFp normalize(DiyFp a) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[37]++;
        DiyFp result = new DiyFp(a.f, a.e);
        result.normalize();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[38]++;
        return result;
    }

    long f() { return f; }
    int e() { return e; }

    void setF(long new_value) { f = new_value;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[39]++; }
    void setE(int new_value) { e = new_value;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5.statements[40]++; }

    @Override
    public String toString() {
        return "[DiyFp f:" + f + ", e:" + e + "]";
    }

}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5 ());
  }
    public static long[] statements = new long[41];
    public static long[] branches = new long[0];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[3];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.v8dtoa.RHINO-SRC-DiyFp.java";
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

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3be9jnynhjft7gkf5 () {
    super("org.mozilla.javascript.v8dtoa.RHINO-SRC-DiyFp.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 40; i++) {
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
    log.startNamedSection("org.mozilla.javascript.v8dtoa.RHINO-SRC-DiyFp.java");
      for (int i = 1; i <= 40; i++) {
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

