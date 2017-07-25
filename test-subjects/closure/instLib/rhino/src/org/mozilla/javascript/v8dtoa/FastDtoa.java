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

public class FastDtoa {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.ping();
  }


    // FastDtoa will produce at most kFastDtoaMaximalLength digits.
    static final int kFastDtoaMaximalLength = 17;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[1]++;
  }


    // The minimal and maximal target exponent define the range of w's binary
    // exponent, where 'w' is the result of multiplying the input by a cached power
    // of ten.
    //
    // A different range might be chosen on a different platform, to optimize digit
    // generation, but a smaller range requires more powers of ten to be cached.
    static final int minimal_target_exponent = -60;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[2]++;
  }
    static final int maximal_target_exponent = -32;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[3]++;
  }


    // Adjusts the last digit of the generated number, and screens out generated
    // solutions that may be inaccurate. A solution may be inaccurate if it is
    // outside the safe interval, or if we ctannot prove that it is closer to the
    // input than a neighboring representation of the same length.
    //
    // Input: * buffer containing the digits of too_high / 10^kappa
    //        * distance_too_high_w == (too_high - w).f() * unit
    //        * unsafe_interval == (too_high - too_low).f() * unit
    //        * rest = (too_high - buffer * 10^kappa).f() * unit
    //        * ten_kappa = 10^kappa * unit
    //        * unit = the common multiplier
    // Output: returns true if the buffer is guaranteed to contain the closest
    //    representable number to the input.
    //  Modifies the generated digits in the buffer to approach (round towards) w.
    static boolean roundWeed(FastDtoaBuilder buffer,
                             long distance_too_high_w,
                             long unsafe_interval,
                             long rest,
                             long ten_kappa,
                             long unit) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[4]++;
        long small_distance = distance_too_high_w - unit;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[5]++;
        long big_distance = distance_too_high_w + unit;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        // Let w_low  = too_high - big_distance, and
        //     w_high = too_high - small_distance.
        // Note: w_low < w < w_high
        //
        // The real w (* unit) must lie somewhere inside the interval
        // ]w_low; w_low[ (often written as "(w_low; w_low)")

        // Basically the buffer currently contains a number in the unsafe interval
        // ]too_low; too_high[ with too_low < w < too_high
        //
        //  too_high - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //                     ^v 1 unit            ^      ^                 ^      ^
        //  boundary_high ---------------------     .      .                 .      .
        //                     ^v 1 unit            .      .                 .      .
        //   - - - - - - - - - - - - - - - - - - -  +  - - + - - - - - -     .      .
        //                                          .      .         ^       .      .
        //                                          .  big_distance  .       .      .
        //                                          .      .         .       .    rest
        //                              small_distance     .         .       .      .
        //                                          v      .         .       .      .
        //  w_high - - - - - - - - - - - - - - - - - -     .         .       .      .
        //                     ^v 1 unit                   .         .       .      .
        //  w ----------------------------------------     .         .       .      .
        //                     ^v 1 unit                   v         .       .      .
        //  w_low  - - - - - - - - - - - - - - - - - - - - -         .       .      .
        //                                                           .       .      v
        //  buffer --------------------------------------------------+-------+--------
        //                                                           .       .
        //                                                  safe_interval    .
        //                                                           v       .
        //   - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -     .
        //                     ^v 1 unit                                     .
        //  boundary_low -------------------------                     unsafe_interval
        //                     ^v 1 unit                                     v
        //  too_low  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
        //
        //
        // Note that the value of buffer could lie anywhere inside the range too_low
        // to too_high.
        //
        // boundary_low, boundary_high and w are approximations of the real boundaries
        // and v (the input number). They are guaranteed to be precise up to one unit.
        // In fact the error is guaranteed to be strictly less than one unit.
        //
        // Anything that lies outside the unsafe interval is guaranteed not to round
        // to v when read again.
        // Anything that lies inside the safe interval is guaranteed to round to v
        // when read again.
        // If the number inside the buffer lies inside the unsafe interval but not
        // inside the safe interval then we simply do not know and bail out (returning
        // false).
        //
        // Similarly we have to take into account the imprecision of 'w' when rounding
        // the buffer. If we have two potential representations we need to make sure
        // that the chosen one is closer to w_low and w_high since v can be anywhere
        // between them.
        //
        // By generating the digits of too_high we got the largest (closest to
        // too_high) buffer that is still in the unsafe interval. In the case where
        // w_high < buffer < too_high we try to decrement the buffer.
        // This way the buffer approaches (rounds towards) w.
        // There are 3 conditions that stop the decrementation process:
        //   1) the buffer is already below w_high
        //   2) decrementing the buffer would make it leave the unsafe interval
        //   3) decrementing the buffer would yield a number below w_high and farther
        //      away than the current number. In other words:
        //              (buffer{-1} < w_high) && w_high - buffer{-1} > buffer - w_high
        // Instead of using the buffer directly we use its distance to too_high.
        // Conceptually rest ~= too_high - buffer
        while ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (128)) == 0 || true) &&
 ((rest < small_distance) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((// Negated condition 1
                unsafe_interval - rest >= ten_kappa) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((rest + ten_kappa < small_distance) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((// buffer{-1} > w_high
                        small_distance - rest >= rest + ten_kappa - small_distance) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 4) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[1]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[2]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[3]++;
}
            buffer.decreaseLast();
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[7]++;
            rest += ten_kappa;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[8]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;

        // We have approached w+ as much as possible. We now test if approaching w-
        // would require changing the buffer. If yes, then we have two possible
        // representations close to w, but we cannot decide which one is closer.
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (128)) == 0 || true) &&
 ((rest < big_distance) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((unsafe_interval - rest >= ten_kappa) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((rest + ten_kappa < big_distance) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((big_distance - rest > rest + ten_kappa - big_distance) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 4) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 4) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[1]++;
            return false;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[2]++;}

        // Weeding test.
        //   The safe interval is [too_low + 2 ulp; too_high - 2 ulp]
        //   Since too_low = too_high - unsafe_interval this is equivalent to
        //      [too_high - unsafe_interval + 4 ulp; too_high - 2 ulp]
        //   Conceptually we have: rest ~= too_high - buffer
        return (2 * unit <= rest) && (rest <= unsafe_interval - 4 * unit);
    }



    static final int kTen4 = 10000;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[10]++;
  }
    static final int kTen5 = 100000;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[11]++;
  }
    static final int kTen6 = 1000000;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[12]++;
  }
    static final int kTen7 = 10000000;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[13]++;
  }
    static final int kTen8 = 100000000;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[14]++;
  }
    static final int kTen9 = 1000000000;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[15]++;
  }

    // Returns the biggest power of ten that is less than or equal than the given
    // number. We furthermore receive the maximum number of bits 'number' has.
    // If number_bits == 0 then 0^-1 is returned
    // The number of bits must be <= 32.
    // Precondition: (1 << number_bits) <= number < (1 << (number_bits + 1)).
    static long biggestPowerTen(int number,
                                int number_bits) {
        int power, exponent;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[16]++;
        switch (number_bits) {
            case 32:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[3]++;
            case 31:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[4]++;
            case 30:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[5]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((kTen9 <= number) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[6]++;
                    power = kTen9;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[18]++;
                    exponent = 9;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[19]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[20]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[7]++;}  // else fallthrough
            case 29:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[8]++;
            case 28:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[9]++;
            case 27:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[10]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
                if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((kTen8 <= number) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[11]++;
                    power = kTen8;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[22]++;
                    exponent = 8;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[23]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[24]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[12]++;}  // else fallthrough
            case 26:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[13]++;
            case 25:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[14]++;
            case 24:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[15]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[25]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((kTen7 <= number) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[16]++;
                    power = kTen7;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[26]++;
                    exponent = 7;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[27]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[28]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[17]++;}  // else fallthrough
            case 23:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[18]++;
            case 22:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[19]++;
            case 21:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[20]++;
            case 20:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[21]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((kTen6 <= number) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[22]++;
                    power = kTen6;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[30]++;
                    exponent = 6;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[31]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[32]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[23]++;}  // else fallthrough
            case 19:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[24]++;
            case 18:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[25]++;
            case 17:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[26]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[33]++;
int CodeCoverConditionCoverageHelper_C7;
                if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((kTen5 <= number) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[27]++;
                    power = kTen5;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[34]++;
                    exponent = 5;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[35]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[36]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[28]++;}  // else fallthrough
            case 16:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[29]++;
            case 15:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[30]++;
            case 14:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[31]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[37]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((kTen4 <= number) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[32]++;
                    power = kTen4;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[38]++;
                    exponent = 4;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[39]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[40]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[33]++;}  // else fallthrough
            case 13:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[34]++;
            case 12:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[35]++;
            case 11:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[36]++;
            case 10:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[37]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[41]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((1000 <= number) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[38]++;
                    power = 1000;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[42]++;
                    exponent = 3;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[43]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[44]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[39]++;}  // else fallthrough
            case 9:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[40]++;
            case 8:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[41]++;
            case 7:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[42]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
                if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((100 <= number) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[43]++;
                    power = 100;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[46]++;
                    exponent = 2;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[47]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[48]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[44]++;}  // else fallthrough
            case 6:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[45]++;
            case 5:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[46]++;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[47]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((10 <= number) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[48]++;
                    power = 10;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[50]++;
                    exponent = 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[51]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[52]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[49]++;}  // else fallthrough
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[50]++;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[51]++;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[52]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((1 <= number) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[53]++;
                    power = 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[54]++;
                    exponent = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[55]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[56]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[54]++;}  // else fallthrough
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[55]++;
                power = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[57]++;
                exponent = -1;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[58]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[59]++;
                break;
            default:
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[56]++;
                // Following assignments are here to silence compiler warnings.
                power = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[60]++;
                exponent = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[61]++;
                // UNREACHABLE();
        }
        return ((long)power << 32) | (0xffffffffL & exponent);
    }

    private static boolean uint64_lte(long a, long b) {
        // less-or-equal for unsigned int64 in java-style...
        return (a == b) || ((a < b) ^ (a < 0) ^ (b < 0));
    }

    // Generates the digits of input number w.
    // w is a floating-point number (DiyFp), consisting of a significand and an
    // exponent. Its exponent is bounded by minimal_target_exponent and
    // maximal_target_exponent.
    //       Hence -60 <= w.e() <= -32.
    //
    // Returns false if it fails, in which case the generated digits in the buffer
    // should not be used.
    // Preconditions:
    //  * low, w and high are correct up to 1 ulp (unit in the last place). That
    //    is, their error must be less that a unit of their last digits.
    //  * low.e() == w.e() == high.e()
    //  * low < w < high, and taking into account their error: low~ <= high~
    //  * minimal_target_exponent <= w.e() <= maximal_target_exponent
    // Postconditions: returns false if procedure fails.
    //   otherwise:
    //     * buffer is not null-terminated, but len contains the number of digits.
    //     * buffer contains the shortest possible decimal digit-sequence
    //       such that LOW < buffer * 10^kappa < HIGH, where LOW and HIGH are the
    //       correct values of low and high (without their error).
    //     * if more than one decimal representation gives the minimal number of
    //       decimal digits then the one closest to W (where W is the correct value
    //       of w) is chosen.
    // Remark: this procedure takes into account the imprecision of its input
    //   numbers. If the precision is not enough to guarantee all the postconditions
    //   then false is returned. This usually happens rarely (~0.5%).
    //
    // Say, for the sake of example, that
    //   w.e() == -48, and w.f() == 0x1234567890abcdef
    // w's value can be computed by w.f() * 2^w.e()
    // We can obtain w's integral digits by simply shifting w.f() by -w.e().
    //  -> w's integral part is 0x1234
    //  w's fractional part is therefore 0x567890abcdef.
    // Printing w's integral part is easy (simply print 0x1234 in decimal).
    // In order to print its fraction we repeatedly multiply the fraction by 10 and
    // get each digit. Example the first digit after the point would be computed by
    //   (0x567890abcdef * 10) >> 48. -> 3
    // The whole thing becomes slightly more complicated because we want to stop
    // once we have enough digits. That is, once the digits inside the buffer
    // represent 'w' we can stop. Everything inside the interval low - high
    // represents w. However we have to pay attention to low, high and w's
    // imprecision.
    static boolean digitGen(DiyFp low,
                     DiyFp w,
                     DiyFp high,
                     FastDtoaBuilder buffer,
                     int mk) {
        assert(low.e() == w.e() && w.e() == high.e());
        assert uint64_lte(low.f() + 1, high.f() - 1);
        assert(minimal_target_exponent <= w.e() && w.e() <= maximal_target_exponent);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[62]++;
        // low, w and high are imprecise, but by less than one ulp (unit in the last
        // place).
        // If we remove (resp. add) 1 ulp from low (resp. high) we are certain that
        // the new numbers are outside of the interval we want the final
        // representation to lie in.
        // Inversely adding (resp. removing) 1 ulp from low (resp. high) would yield
        // numbers that are certain to lie in the interval. We will use this fact
        // later on.
        // We will now start by generating the digits within the uncertain
        // interval. Later we will weed out representations that lie outside the safe
        // interval and thus _might_ lie outside the correct interval.
        long unit = 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[63]++;
        DiyFp too_low = new DiyFp(low.f() - unit, low.e());
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[64]++;
        DiyFp too_high = new DiyFp(high.f() + unit, high.e());
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[65]++;
        // too_low and too_high are guaranteed to lie outside the interval we want the
        // generated number in.
        DiyFp unsafe_interval = DiyFp.minus(too_high, too_low);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[66]++;
        // We now cut the input number into two parts: the integral digits and the
        // fractionals. We will not write any decimal separator though, but adapt
        // kappa instead.
        // Reminder: we are currently computing the digits (stored inside the buffer)
        // such that:   too_low < buffer * 10^kappa < too_high
        // We use too_high for the digit_generation and stop as soon as possible.
        // If we stop early we effectively round down.
        DiyFp one = new DiyFp(1l << -w.e(), w.e());
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[67]++;
        // Division by one is a shift.
        int integrals = (int)((too_high.f() >>> -one.e()) & 0xffffffffL);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[68]++;
        // Modulo by one is an and.
        long fractionals = too_high.f() & (one.f() - 1);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[69]++;
        long result = biggestPowerTen(integrals, DiyFp.kSignificandSize - (-one.e()));
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[70]++;
        int divider = (int) ((result >>> 32) & 0xffffffffL);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[71]++;
        int divider_exponent = (int) (result & 0xffffffffL);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[72]++;
        int kappa = divider_exponent + 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[73]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[4]++;


int CodeCoverConditionCoverageHelper_C13;
        // Loop invariant: buffer = too_high / 10^kappa  (integer division)
        // The invariant holds for the first iteration: kappa has been initialized
        // with the divider exponent + 1. And the divider is the biggest power of ten
        // that is smaller than integrals.
        while ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((kappa > 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[4]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[5]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[6]++;
}
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[74]++;
            int digit = integrals / divider;
            buffer.append((char) ('0' + digit));
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[75]++;
            integrals %= divider;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[76]++;
            kappa--;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[77]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[78]++;
            // Note that kappa now equals the exponent of the divider and that the
            // invariant thus holds again.
            long rest =
                    ((long)integrals << -one.e()) + fractionals;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[79]++;
int CodeCoverConditionCoverageHelper_C14;
            // Invariant: too_high = buffer * 10^kappa + DiyFp(rest, one.e())
            // Reminder: unsafe_interval.e() == one.e()
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((rest < unsafe_interval.f()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[57]++;
                // Rounding down (by not emitting the remaining digits) yields a number
                // that lies within the unsafe interval.
                buffer.point = buffer.end - mk + kappa;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[80]++;
                return roundWeed(buffer, DiyFp.minus(too_high, w).f(),
                        unsafe_interval.f(), rest,
                        (long)divider << -one.e(), unit);

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[58]++;}
            divider /= 10;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[81]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[82]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[7]++;



        // The integrals have been generated. We are at the point of the decimal
        // separator. In the following loop we simply multiply the remaining digits by
        // 10 and divide by one. We just need to pay attention to multiply associated
        // data (like the interval or 'unit'), too.
        // Instead of multiplying by 10 we multiply by 5 (cheaper operation) and
        // increase its (imaginary) exponent. At the same time we decrease the
        // divider's (one's) exponent and shift its significand.
        // Basically, if fractionals was a DiyFp (with fractionals.e == one.e):
        //      fractionals.f *= 10;
        //      fractionals.f >>= 1; fractionals.e++; // value remains unchanged.
        //      one.f >>= 1; one.e++;                 // value remains unchanged.
        //      and we have again fractionals.e == one.e which allows us to divide
        //           fractionals.f() by one.f()
        // We simply combine the *= 10 and the >>= 1.
        while (true) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[7]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[8]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.loops[9]++;
}
            fractionals *= 5;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[83]++;
            unit *= 5;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[84]++;
            unsafe_interval.setF(unsafe_interval.f() * 5);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[85]++;
            unsafe_interval.setE(unsafe_interval.e() + 1);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[86]++;  // Will be optimized out.
            one.setF(one.f() >>> 1);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[87]++;
            one.setE(one.e() + 1);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[88]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[89]++;
            // Integer division by one.
            int digit = (int)((fractionals >>> -one.e()) & 0xffffffffL);
            buffer.append((char) ('0' + digit));
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[90]++;
            fractionals &= one.f() - 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[91]++;  // Modulo by one.
            kappa--;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[92]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[93]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((fractionals < unsafe_interval.f()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[59]++;
                buffer.point = buffer.end - mk + kappa;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[94]++;
                return roundWeed(buffer, DiyFp.minus(too_high, w).f() * unit,
                        unsafe_interval.f(), fractionals, one.f(), unit);

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[60]++;}
        }
    }


    // Provides a decimal representation of v.
    // Returns true if it succeeds, otherwise the result cannot be trusted.
    // There will be *length digits inside the buffer (not null-terminated).
    // If the function returns true then
    //        v == (double) (buffer * 10^decimal_exponent).
    // The digits in the buffer are the shortest representation possible: no
    // 0.09999999999999999 instead of 0.1. The shorter representation will even be
    // chosen even if the longer one would be closer to v.
    // The last digit will be closest to the actual v. That is, even if several
    // digits might correctly yield 'v' when read again, the closest will be
    // computed.
    static boolean grisu3(double v, FastDtoaBuilder buffer) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[95]++;
        long bits = Double.doubleToLongBits(v);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[96]++;
        DiyFp w = DoubleHelper.asNormalizedDiyFp(bits);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[97]++;
        // boundary_minus and boundary_plus are the boundaries between v and its
        // closest floating-point neighbors. Any number strictly between
        // boundary_minus and boundary_plus will round to v when convert to a double.
        // Grisu3 will never output representations that lie exactly on a boundary.
        DiyFp boundary_minus = new DiyFp(), boundary_plus = new DiyFp();
        DoubleHelper.normalizedBoundaries(bits, boundary_minus, boundary_plus);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[98]++;
        assert(boundary_plus.e() == w.e());
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[99]++;
        DiyFp ten_mk = new DiyFp();
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[100]++;  // Cached power of ten: 10^-k
        int mk =  CachedPowers.getCachedPower(w.e() + DiyFp.kSignificandSize,
                minimal_target_exponent, maximal_target_exponent, ten_mk);
        assert(minimal_target_exponent <= w.e() + ten_mk.e() +
                DiyFp.kSignificandSize &&
                maximal_target_exponent >= w.e() + ten_mk.e() +
                        DiyFp.kSignificandSize);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[101]++;
        // Note that ten_mk is only an approximation of 10^-k. A DiyFp only contains a
        // 64 bit significand and ten_mk is thus only precise up to 64 bits.

        // The DiyFp::Times procedure rounds its result, and ten_mk is approximated
        // too. The variable scaled_w (as well as scaled_boundary_minus/plus) are now
        // off by a small amount.
        // In fact: scaled_w - w*10^k < 1ulp (unit in the last place) of scaled_w.
        // In other words: let f = scaled_w.f() and e = scaled_w.e(), then
        //           (f-1) * 2^e < w*10^k < (f+1) * 2^e
        DiyFp scaled_w = DiyFp.times(w, ten_mk);
        assert(scaled_w.e() ==
                boundary_plus.e() + ten_mk.e() + DiyFp.kSignificandSize);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[102]++;
        // In theory it would be possible to avoid some recomputations by computing
        // the difference between w and boundary_minus/plus (a power of 2) and to
        // compute scaled_boundary_minus/plus by subtracting/adding from
        // scaled_w. However the code becomes much less readable and the speed
        // enhancements are not terriffic.
        DiyFp scaled_boundary_minus = DiyFp.times(boundary_minus, ten_mk);
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[103]++;
        DiyFp scaled_boundary_plus  = DiyFp.times(boundary_plus,  ten_mk);

        // DigitGen will generate the digits of scaled_w. Therefore we have
        // v == (double) (scaled_w * 10^-mk).
        // Set decimal_exponent == -mk and pass it to DigitGen. If scaled_w is not an
        // integer than it will be updated. For instance if scaled_w == 1.23 then
        // the buffer will be filled with "123" und the decimal_exponent will be
        // decreased by 2.
        return digitGen(scaled_boundary_minus, scaled_w, scaled_boundary_plus,
                buffer, mk);
    }


    public static boolean dtoa(double v, FastDtoaBuilder buffer) {
        assert(v > 0);
        assert(!Double.isNaN(v));
        assert(!Double.isInfinite(v));

        return grisu3(v, buffer);
    }

    public static String numberToString(double v) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[104]++;
        FastDtoaBuilder buffer = new FastDtoaBuilder();
        return numberToString(v, buffer) ? buffer.format() : null;
    }

    public static boolean numberToString(double v, FastDtoaBuilder buffer) {
        buffer.reset();
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[105]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[106]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((v < 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[61]++;
            buffer.append('-');
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[107]++;
            v = -v;
CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.statements[108]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1.branches[62]++;}
        return dtoa(v, buffer);
    }

}

class CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1 ());
  }
    public static long[] statements = new long[109];
    public static long[] branches = new long[63];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.v8dtoa.RHINO-SRC-FastDtoa.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,3,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1};
    for (int i = 1; i <= 17; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$2o08iygcpwmc6w269j2ln0m9i784ch5br2w1 () {
    super("org.mozilla.javascript.v8dtoa.RHINO-SRC-FastDtoa.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 108; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 62; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.v8dtoa.RHINO-SRC-FastDtoa.java");
      for (int i = 1; i <= 108; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 62; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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
