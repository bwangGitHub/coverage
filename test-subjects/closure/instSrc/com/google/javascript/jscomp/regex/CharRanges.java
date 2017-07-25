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

package com.google.javascript.jscomp.regex;

import java.util.Arrays;

/**
 * An immutable sparse bitset that deals well where the data is chunky:
 * where P(bit[x+1] == bit[x]).  E.g. [101,102,103,104,105,1001,1002,1003,1004]
 * is chunky.
 *
 * @author mikesamuel@gmail.com (Mike Samuel)
 */
final class CharRanges {
  static {
    CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.ping();
  }

  /**
   * A strictly increasing set of bit indices where even members are the
   * inclusive starts of ranges, and odd members are the exclusive ends.
   * <p>
   * E.g., { 1, 5, 6, 10 } represents the set ( 1, 2, 3, 4, 6, 7, 8, 9 ).
   */
  private final int[] ranges;

  public static final CharRanges EMPTY = new CharRanges(new int[0]);
  static {
    CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[1]++;
  }

  public static final CharRanges ALL_CODE_UNITS
      = new CharRanges(new int[] { 0, 0x10000 });
  static {
    CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[2]++;
  }

  public static CharRanges inclusive(int start, int end) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((start > end) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[1]++;
      throw new IndexOutOfBoundsException(start + " > " + end);

    } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[2]++;}
    return new CharRanges(new int[] { start, end + 1 });
  }

  /**
   * Returns an instance containing all and only the given members.
   */
  public static CharRanges withMembers(int... members) {
    return new CharRanges(intArrayToRanges(members.clone()));
  }

  /**
   * Returns an instance containing the given ranges.
   * @param ranges An even-length ordered sequence of non-overlapping,
   *     non-contiguous, [inclusive start, exclusive end) ranges.
   */
  public static CharRanges withRanges(int... ranges) {
    ranges = ranges.clone();
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[4]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[5]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((ranges.length & 1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[3]++; throw new IllegalArgumentException();
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[4]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i < ranges.length) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[1]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[2]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[3]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((ranges[i] <= ranges[i - 1]) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[5]++;
        throw new IllegalArgumentException(ranges[i] + " > " + ranges[i - 1]);

      } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[6]++;}
    }
    return new CharRanges(ranges);
  }

  private CharRanges(int[] ranges) {
    this.ranges = ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[8]++;
  }

  private static int[] intArrayToRanges(int[] members) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[9]++;
    int nMembers = members.length;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((nMembers == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[7]++;
      return new int[0];

    } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[8]++;}

    Arrays.sort(members);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[11]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[12]++;

    // Count the number of runs.
    int nRuns = 1;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[4]++;


int CodeCoverConditionCoverageHelper_C6;
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < nMembers) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[4]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[5]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[6]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[14]++;
      int current = members[i], last = members[i - 1];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[15]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((current == last) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[9]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[16]++; continue;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[10]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[17]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((current != last + 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[11]++; ++nRuns;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[18]++;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[12]++;}
    }
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[19]++;

    int[] ranges = new int[nRuns * 2];
    ranges[0] = members[0];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[20]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[21]++;
    int k = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[22]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[7]++;


int CodeCoverConditionCoverageHelper_C9;
    for (int i = 1;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((k + 2 < ranges.length) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[7]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[8]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[9]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[23]++;
      int current = members[i], last = members[i - 1];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[24]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((current == last) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[13]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[25]++; continue;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[14]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[26]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((current != last + 1) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[15]++;
        ranges[++k] = last + 1;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[27]++;  // add 1 to make end exclusive
        ranges[++k] = current;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[28]++;

      } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[16]++;}
    }
    ranges[++k] = members[nMembers - 1] + 1;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[29]++;  // add 1 to make end exclusive
    return ranges;
  }

  public boolean contains(int bit) {
    return (Arrays.binarySearch(ranges, bit) & 1) == 0;
    // By the contract of Arrays.binarySearch, its result is either the position
    // of bit in ranges or it is the bitwise inverse of the position of the
    // least element greater than bit.

    // Two cases
    // case (idx >= 0)
    //     We ended up exactly on a range boundary.
    //     Starts are inclusive and ends are both exclusive, so this contains
    //     bit iff idx is even.
    //
    // case (idx < 0)
    //     If the least element greater than bit is an odd element,
    //     then bit must be greater than a start and less than an end, so
    //     contained.
    //
    //     If bit is greater than all elements, then idx will be past the end of
    //     the array, and will be even since ranges.length is even.
    //
    //     Otherwise, bit must be in the space between two runs, so not
    //     contained.
    //
    //     In all cases, oddness is equivalent to containedness.

    // Those two cases lead to
    //     idx >= 0 ? ((idx & 1) == 0) : ((~idx & 1) == 1)

    // But ~n & bit == bit   <=>   n & bit == 0, so
    //     idx >= 0 ? ((idx & 1) == 0) : ((~idx & 1) == 1)
    // =>  idx >= 0 ? ((idx & 1) == 0) : ((idx & 1) == 0)
    // =>  (idx & 1) == 0
  }

  public int minSetBit() {
    return ranges.length >= 0 ? ranges[0] : Integer.MIN_VALUE;
  }

  public boolean isEmpty() {
    return ranges.length == 0;
  }

  public int getNumRanges() { return ranges.length >> 1; }

  public int start(int i) { return ranges[i << 1]; }

  public int end(int i) { return ranges[(i << 1) | 1]; }

  public CharRanges union(CharRanges other) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[30]++;
    // Index of the input ranges
    int[] q = this.ranges, r = other.ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[31]++;
    // Lengths of the inputs
    int m = q.length, n = r.length;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;

    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((m == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[17]++; return other;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[18]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[33]++;
int CodeCoverConditionCoverageHelper_C13;
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((n == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[19]++; return this;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[20]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[34]++;

    // The output array.  The length is m+n in the worst case when all the
    // ranges in a are disjoint from the ranges in b.
    int[] out = new int[m + n];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[35]++;

    // Indexes into the various arrays
    int i = 0, j = 0, k = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[36]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[10]++;


int CodeCoverConditionCoverageHelper_C14;
    // Since there are three arrays, and indices into them the following
    // should never occur in this function:
    // (1) q[j] or q[k]                         -- q is indexed by i
    // (2) r[i] or r[k]                         -- r is indexed by j
    // (3) out[i] or out[j]                     -- out is indexed by k
    // (4) i < n or j < m                       -- index compared to wrong limit

    // This loop exits because we always increment at least one of i,j.
    while ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((i < m) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((j < n) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[10]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[11]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[12]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[37]++;
      // Range starts and ends.
      int a0 = q[i], a1 = q[i + 1],
          b0 = r[j], b1 = r[j + 1];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[38]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((a1 < b0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[21]++;  // [a0, a1) ends before [b0, b1) starts
        out[k++] = a0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[39]++;
        out[k++] = a1;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[40]++;
        i += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[41]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[22]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[42]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((b1 < a0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[23]++;  // [b0, b1) ends before [a0, a1) starts
        out[k++] = b0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[43]++;
        out[k++] = b1;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[44]++;
        j += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[45]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[24]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[46]++;  // ranges overlap
        // We need to compute a new range based on the set of ranges that
        // transitively overlap.
        //       AAAAAAAAA AAA
        //     BBB  BBB* BBB
        // In the range above, the start comes from one set, and the end from
        // another.  The range with the asterisk next to it is subsumed entirely
        // by a range from the other, and so not all ranges on the input
        // contribute a value to the output.
        // The last BBB run serves only as a bridge -- it overlaps two
        // disjoint ranges in the other one so establishes that they
        // transitively overlap.
        int start = Math.min(a0, b0);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[47]++;
        // Guess at the end, and lookahead to come up with a more complete
        // estimate.
        int end = Math.max(a1, b1);
        i += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[48]++;
        j += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[49]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[50]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[13]++;


int CodeCoverConditionCoverageHelper_C17;
        while ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((i < m) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((j < n) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[13]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[14]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[15]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[51]++;
int CodeCoverConditionCoverageHelper_C18;
          if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((i < m) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((q[i] <= end) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[25]++;
            end = Math.max(end, q[i + 1]);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[52]++;
            i += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[53]++;

          } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[26]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[54]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((j < n) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((r[j] <= end) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[27]++;
            end = Math.max(end, r[j + 1]);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[55]++;
            j += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[56]++;

          } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[28]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[57]++;
            break;
          }
}
        }
        out[k++] = start;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[58]++;
        out[k++] = end;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[59]++;
      }
}
    }
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[60]++;
int CodeCoverConditionCoverageHelper_C20;
    // There may be unprocessed ranges at the end of one of the inputs.
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < m) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[29]++;
      System.arraycopy(q, i, out, k, m - i);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[61]++;
      k += m - i;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[62]++;

    } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[30]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[63]++;
int CodeCoverConditionCoverageHelper_C21; if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((j < n) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[31]++;
      System.arraycopy(r, j, out, k, n - j);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[64]++;
      k += n - j;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[65]++;

    } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[32]++;}
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[66]++;
int CodeCoverConditionCoverageHelper_C22;
    // We guessed at the output length above.  Cut off the tail.
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((k != out.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[33]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[67]++;
      int[] clipped = new int[k];
      System.arraycopy(out, 0, clipped, 0, k);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[68]++;
      out = clipped;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[69]++;

    } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[34]++;}
    return new CharRanges(out);
  }

  public CharRanges intersection(CharRanges other) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[70]++;
    int[] aRanges = ranges, bRanges = other.ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[71]++;
    int aLen = aRanges.length, bLen = bRanges.length;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[72]++;
int CodeCoverConditionCoverageHelper_C23;
    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((aLen == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[35]++; return this;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[36]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[73]++;
int CodeCoverConditionCoverageHelper_C24;
    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((bLen == 0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[37]++; return other;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[38]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[74]++;
    int aIdx = 0, bIdx = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[75]++;
    int[] intersection = new int[Math.min(aLen, bLen)];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[76]++;
    int intersectionIdx = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[77]++;
    int pos = Math.min(aRanges[0], bRanges[0]);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[78]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[16]++;


int CodeCoverConditionCoverageHelper_C25;
    while ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((aIdx < aLen) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((bIdx < bLen) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[16]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[17]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[18]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[79]++;
int CodeCoverConditionCoverageHelper_C26;
      if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((aRanges[aIdx + 1] <= pos) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[39]++;
        aIdx += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[80]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[40]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[81]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((bRanges[bIdx + 1] <= pos) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[41]++;
        bIdx += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[82]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[42]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[83]++;
        int start = Math.max(aRanges[aIdx], bRanges[bIdx]);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[84]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((pos < start) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[43]++;  // Advance to start of common block.
          pos = start;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[85]++;

        } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[44]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[86]++;
          // Now we know that pos is less than the ends of the two ranges and
          // greater or equal to the starts of the two ranges.
          int end = Math.min(aRanges[aIdx + 1], bRanges[bIdx + 1]);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[87]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((intersectionIdx != 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((pos == intersection[intersectionIdx - 1]) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[45]++;
            intersection[intersectionIdx - 1] = end;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[88]++;

          } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[46]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[89]++;
int CodeCoverConditionCoverageHelper_C30;
            if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((intersectionIdx == intersection.length) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[47]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[90]++;
              int[] newArr = new int[intersectionIdx * 2];
              System.arraycopy(intersection, 0, newArr, 0, intersectionIdx);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[91]++;
              intersection = newArr;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[92]++;

            } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[48]++;}
            intersection[intersectionIdx++] = pos;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[93]++;
            intersection[intersectionIdx++] = end;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[94]++;
          }
          pos = end;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[95]++;
        }
      }
}
    }
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[96]++;
int CodeCoverConditionCoverageHelper_C31;
    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((intersectionIdx != intersection.length) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[49]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[97]++;
      int[] newArr = new int[intersectionIdx];
      System.arraycopy(intersection, 0, newArr, 0, intersectionIdx);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[98]++;
      intersection = newArr;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[99]++;

    } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[50]++;}
    return new CharRanges(intersection);
  }

  public CharRanges difference(CharRanges subtrahendRanges) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[100]++;
    // difference = minuend - subtrahend
    int[] minuend = this.ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[101]++;
    int[] subtrahend = subtrahendRanges.ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[102]++;

    int mn = minuend.length, sn = subtrahend.length;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[103]++;
int CodeCoverConditionCoverageHelper_C32;
    if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((mn == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((sn == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[51]++; return this;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[52]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[104]++;

    int[] difference = new int[minuend.length];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[105]++;

    // Indices into minuend.ranges, subtrahend.ranges, and difference.
    int mIdx = 0, sIdx = 0, dIdx = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[106]++;

    int pos = minuend[0];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[107]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[19]++;


int CodeCoverConditionCoverageHelper_C33;
    while ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((mIdx < mn) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[19]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[20]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[21]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[108]++;
int CodeCoverConditionCoverageHelper_C34;
      if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((pos >= minuend[mIdx + 1]) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[53]++;
        mIdx += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[109]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[54]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[110]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((pos < minuend[mIdx]) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[55]++;
        // Skip gaps in the minuend.
        pos = minuend[mIdx];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[111]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[56]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[112]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((sIdx < sn) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((pos >= subtrahend[sIdx]) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[57]++;
        // Skip over a removed part.
        pos = subtrahend[sIdx + 1];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[113]++;
        sIdx += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[114]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[58]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[115]++;
        // Now we know that pos is between [minuend[i], minuend[i + 1])
        // and outside [subtrahend[j], subtrahend[j + 1]).
        int end = sIdx < sn
            ? Math.min(minuend[mIdx + 1], subtrahend[sIdx]) : minuend[mIdx + 1];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[116]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((dIdx != 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((difference[dIdx - 1] == pos) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[59]++;
          difference[dIdx - 1] = pos;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[117]++;

        } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[60]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[118]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((dIdx == difference.length) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[61]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[119]++;
            int[] newArr = new int[dIdx * 2];
            System.arraycopy(difference, 0, newArr, 0, dIdx);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[120]++;
            difference = newArr;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[121]++;

          } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[62]++;}
          difference[dIdx++] = pos;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[122]++;
          difference[dIdx++] = end;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[123]++;
        }
        pos = end;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[124]++;
      }
}
}
    }
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[125]++;
int CodeCoverConditionCoverageHelper_C39;

    if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((dIdx != difference.length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[63]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[126]++;
      int[] newArr = new int[dIdx];
      System.arraycopy(difference, 0, newArr, 0, dIdx);
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[127]++;
      difference = newArr;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[128]++;

    } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[64]++;}

    return new CharRanges(difference);
  }

  public boolean containsAll(CharRanges sub) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[129]++;
    int[] superRanges = this.ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[130]++;
    int[] subRanges = sub.ranges;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[131]++;

    int superIdx = 0, subIdx = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[132]++;
    int superLen = superRanges.length, subLen = subRanges.length;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[133]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[22]++;


int CodeCoverConditionCoverageHelper_C40;
    while ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((subIdx < subLen) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[22]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[23]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[24]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[134]++;
int CodeCoverConditionCoverageHelper_C41;
      if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((superIdx == superLen) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[65]++;
        return false;

      } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[66]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[135]++;
int CodeCoverConditionCoverageHelper_C42;
      if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((superRanges[superIdx + 1] <= subRanges[subIdx]) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[67]++;
        // Super range ends before subRange starts.
        superIdx += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[136]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[68]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[137]++;
int CodeCoverConditionCoverageHelper_C43; if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((superRanges[superIdx] > subRanges[subIdx]) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[69]++;
        // Uncontained portion at start of sub range.
        return false;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[70]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[138]++;
int CodeCoverConditionCoverageHelper_C44; if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((superRanges[superIdx + 1] >= subRanges[subIdx + 1]) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[71]++;
        // A sub range is completely contained in the super range.
        // We know this because of the above condition and we have already
        // ruled out that subRanges[subIdx] < superRanges[superIdx].
        subIdx += 2;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[139]++;

      } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[72]++;
        // Uncontained portion at end of sub range.
        return false;
      }
}
}
    }
    return subIdx == subLen;
  }

  /**
   * Shifts the bits matched by the given delta.
   * So if this has the bits (a, b, c, ..., z) set then the result has the bits
   * ((a - delta), (b - delta), (c - delta), ...., (z - delta)) set.
   *
   * @throws IndexOutOfBoundsException if shifting by delta would cause an
   *     overflow or underflow in a 32 bit {@code signed int} range boundary.
   *     Since the end boundaries of ranges are exclusive, even if there is no
   *     range containing {@link Integer#MAX_VALUE}, shifting by a delta of 1
   *     can cause an overflow.
   */
  public CharRanges shift(int delta) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[140]++;
    int n = ranges.length;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[141]++;
int CodeCoverConditionCoverageHelper_C45;
    if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((delta == 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((n == 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[73]++; return this;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[74]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[142]++;
int CodeCoverConditionCoverageHelper_C46;
    // Test overflow/underflow
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((delta < 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[75]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[143]++;
      long lmin = ranges[0] + delta;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[144]++;
int CodeCoverConditionCoverageHelper_C47;
      if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((lmin < Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[77]++; throw new IndexOutOfBoundsException();
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[78]++;}

    } else {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[76]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[145]++;
      long lmax = ranges[n - 1] + delta;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[146]++;
int CodeCoverConditionCoverageHelper_C48;
      if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((lmax > Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[79]++; throw new IndexOutOfBoundsException();
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[80]++;}
    }
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[147]++;
    // Create a shifted range.
    int[] shiftedRanges = new int[n];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[148]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[25]++;


int CodeCoverConditionCoverageHelper_C49;
    for (int i = n;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((--i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false);) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[25]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[26]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[27]++;
}
      shiftedRanges[i] = ranges[i] + delta;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[149]++;
    }
    return new CharRanges(shiftedRanges);
  }

  @Override
  public String toString() {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[150]++;
    StringBuilder sb = new StringBuilder();
    sb.append('[');
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[151]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[152]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[28]++;


int CodeCoverConditionCoverageHelper_C50;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((i < ranges.length) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[28]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[29]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[30]++;
}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[153]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 (((i & 1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((ranges[i] == ranges[i - 1] + 1) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 2) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[81]++;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[154]++; continue;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[82]++;}
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[155]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[83]++; sb.append((i & 1) == 0 ? ' ' : '-');
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[156]++;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[84]++;}
      sb.append("0x").append(Integer.toString(ranges[i] - (i & 1), 16));
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[157]++;
    }
    sb.append(']');
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[158]++;
    return sb.toString();
  }

  @Override
  public boolean equals(Object o) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[159]++;
int CodeCoverConditionCoverageHelper_C53;
    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((o instanceof CharRanges) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[85]++; return false;
 } else {
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.branches[86]++;}
    return Arrays.equals(this.ranges, ((CharRanges) o).ranges);
  }

  @Override
  public int hashCode() {
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[160]++;
    int hc = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[161]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[31]++;


int CodeCoverConditionCoverageHelper_C54;
    for (int i = 0, n = Math.min(16, ranges.length);(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[31]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[32]--;
  CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.loops[33]++;
}
      hc = (hc << 2) + ranges[i];
CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h.statements[162]++;
    }
    return hc;
  }
}

class CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h ());
  }
    public static long[] statements = new long[163];
    public static long[] branches = new long[87];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[55];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.regex.CharRanges.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,2,2,2,1,1,1,1,1,2,1,1,1,2,1,1,2,1,1,1,2,2,1,1,1,1,1,1,1,2,1,1,1,1,1,2,1,1,1};
    for (int i = 1; i <= 54; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[34];

  public CodeCoverCoverageCounter$k7azni6numk6fs3f2omad0h () {
    super("com.google.javascript.jscomp.regex.CharRanges.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 162; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 86; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 54; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 33; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.regex.CharRanges.java");
      for (int i = 1; i <= 162; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 86; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 54; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 11; i++) {
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

