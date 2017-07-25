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

// Helper functions for doubles.
public class DoubleHelper {
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.ping();
  }


    static final long kSignMask = 0x8000000000000000L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[1]++;
  }
    static final long kExponentMask = 0x7FF0000000000000L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[2]++;
  }
    static final long kSignificandMask = 0x000FFFFFFFFFFFFFL;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[3]++;
  }
    static final long kHiddenBit = 0x0010000000000000L;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[4]++;
  }

    static DiyFp asDiyFp(long d64) {
        assert(!isSpecial(d64));
        return new DiyFp(significand(d64), exponent(d64));
    }

    // this->Significand() must not be 0.
    static DiyFp asNormalizedDiyFp(long d64) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[5]++;
        long f = significand(d64);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[6]++;
        int e = exponent(d64);

        assert(f != 0);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;

        // The current double could be a denormal.
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (((f & kHiddenBit) == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.loops[1]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.loops[2]--;
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.loops[3]++;
}
            f <<= 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[8]++;
            e--;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[9]++;
        }
        // Do the final shifts in one go. Don't forget the hidden bit (the '-1').
        f <<= DiyFp.kSignificandSize - kSignificandSize - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[10]++;
        e -= DiyFp.kSignificandSize - kSignificandSize - 1;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[11]++;
        return new DiyFp(f, e);
    }

    static int exponent(long d64) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isDenormal(d64)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.branches[1]++; return kDenormalExponent;
} else {
  CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.branches[2]++;}
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[13]++;

        int biased_e = (int)(((d64 & kExponentMask) >>> kSignificandSize) & 0xffffffffL);
        return biased_e - kExponentBias;
    }

    static long significand(long d64) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[14]++;
        long significand = d64 & kSignificandMask;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isDenormal(d64)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.branches[3]++;
            return significand + kHiddenBit;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.branches[4]++;
            return significand;
        }
    }

    // Returns true if the double is a denormal.
    static boolean isDenormal(long d64) {
        return (d64 & kExponentMask) == 0L;
    }

    // We consider denormals not to be special.
    // Hence only Infinity and NaN are special.
    static boolean isSpecial(long d64) {
        return (d64 & kExponentMask) == kExponentMask;
    }

    static boolean isNan(long d64) {
        return ((d64 & kExponentMask) == kExponentMask) &&
                ((d64 & kSignificandMask) != 0L);
    }


    static boolean isInfinite(long d64) {
        return ((d64 & kExponentMask) == kExponentMask) &&
                ((d64 & kSignificandMask) == 0L);
    }


    static int sign(long d64) {
        return (d64 & kSignMask) == 0L? 1: -1;
    }


    // Returns the two boundaries of first argument.
    // The bigger boundary (m_plus) is normalized. The lower boundary has the same
    // exponent as m_plus.
    static void normalizedBoundaries(long d64, DiyFp m_minus, DiyFp m_plus) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[16]++;
        DiyFp v = asDiyFp(d64);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[17]++;
        boolean significand_is_zero = (v.f() == kHiddenBit);
        m_plus.setF((v.f() << 1) + 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[18]++;
        m_plus.setE(v.e() - 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[19]++;
        m_plus.normalize();
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[20]++;
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((significand_is_zero) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((v.e() != kDenormalExponent) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.branches[5]++;
            // The boundary is closer. Think of v = 1000e10 and v- = 9999e9.
            // Then the boundary (== (v - v-)/2) is not just at a distance of 1e9 but
            // at a distance of 1e8.
            // The only exception is for the smallest normal: the largest denormal is
            // at the same distance as its successor.
            // Note: denormals have the same exponent as the smallest normals.
            m_minus.setF((v.f() << 2) - 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[22]++;
            m_minus.setE(v.e() - 2);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[23]++;

        } else {
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.branches[6]++;
            m_minus.setF((v.f() << 1) - 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[24]++;
            m_minus.setE(v.e() - 1);
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[25]++;
        }
        m_minus.setF(m_minus.f() << (m_minus.e() - m_plus.e()));
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[26]++;
        m_minus.setE(m_plus.e());
CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[27]++;
    }

    private static final int kSignificandSize = 52;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[28]++;
  }  // Excludes the hidden bit.
    private static final int kExponentBias = 0x3FF + kSignificandSize;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[29]++;
  }
    private static final int kDenormalExponent = -kExponentBias + 1;
  static {
    CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5.statements[30]++;
  }

}

class CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5 ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.v8dtoa.RHINO-SRC-DoubleHelper.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$59ffmx2g7lix6wzmhc37bu994lb4ohuacrncwyjrz5 () {
    super("org.mozilla.javascript.v8dtoa.RHINO-SRC-DoubleHelper.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.v8dtoa.RHINO-SRC-DoubleHelper.java");
      for (int i = 1; i <= 30; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

