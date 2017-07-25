/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.v8dtoa;

import java.util.Arrays;

public class FastDtoaBuilder {
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.ping();
  }


    // allocate buffer for generated digits + extra notation + padding zeroes
    final char[] chars = new char[FastDtoa.kFastDtoaMaximalLength + 8];
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[1]++;
  }
    int end = 0;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[2]++;
  }
    int point;
    boolean formatted = false;
  {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[3]++;
  }

    void append(char c) {
        chars[end++] = c;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[4]++;
    }

    void decreaseLast() {
        chars[end - 1]--;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[5]++;
    }

    public void reset() {
        end = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[6]++;
        formatted = false;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[7]++;
    }

    @Override
    public String toString() {
        return "[chars:" + new String(chars, 0, end) + ", point:" + point + "]";
    }

    public String format() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((formatted) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[1]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[9]++;
            // check for minus sign
            int firstDigit = chars[0] == '-' ? 1 : 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[10]++;
            int decPoint = point - firstDigit;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((decPoint < -5) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((decPoint > 21) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[3]++;
                toExponentialFormat(firstDigit, decPoint);
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[12]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[4]++;
                toFixedFormat(firstDigit, decPoint);
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[13]++;
            }
            formatted = true;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[14]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[2]++;}
        return new String(chars, 0, end);

    }

    private void toFixedFormat(int firstDigit, int decPoint) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((point < end) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[5]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
            // insert decimal point
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((decPoint > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[7]++;
                // >= 1, split decimals and insert point
                System.arraycopy(chars, point, chars, point + 1, end - point);
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[17]++;
                chars[point] = '.';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[18]++;
                end++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[19]++;

            } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[8]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[20]++;
                // < 1,
                int target = firstDigit + 2 - decPoint;
                System.arraycopy(chars, firstDigit, chars, target, end - firstDigit);
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[21]++;
                chars[firstDigit] = '0';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[22]++;
                chars[firstDigit + 1] = '.';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[23]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[24]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((decPoint < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[9]++;
                    Arrays.fill(chars, firstDigit + 2, target, '0');
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[25]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[10]++;}
                end += 2 - decPoint;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[26]++;
            }

        } else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[6]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[27]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((point > end) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[11]++;
            // large integer, add trailing zeroes
            Arrays.fill(chars, end, point, '0');
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[28]++;
            end += point - end;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[29]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[12]++;}
}
    }

    private void toExponentialFormat(int firstDigit, int decPoint) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[30]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((end - firstDigit > 1) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[13]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[31]++;
            // insert decimal point if more than one digit was produced
            int dot = firstDigit + 1;
            System.arraycopy(chars, dot, chars, dot + 1, end - dot);
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[32]++;
            chars[dot] = '.';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[33]++;
            end++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[34]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[14]++;}
        chars[end++] = 'e';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[35]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[36]++;
        char sign = '+';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[37]++;
        int exp = decPoint - 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((exp < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[15]++;
            sign = '-';
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[39]++;
            exp = -exp;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[40]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[16]++;}
        chars[end++] = sign;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[41]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[42]++;

        int charPos = exp > 99 ? end + 2 : exp > 9 ? end + 1 : end;
        end = charPos + 1;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[43]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[44]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.loops[1]++;



        // code below is needed because Integer.getChars() is not public
        for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.loops[3]++;
}
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[45]++;
            int r = exp % 10;
            chars[charPos--] = digits[r];
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[46]++;
            exp = exp / 10;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[47]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[48]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((exp == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[17]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[49]++; break;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.branches[18]++;}
        }
    }

    final static char[] digits = {
        '0' , '1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'
    };
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1.statements[50]++;
  }
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1 ());
  }
    public static long[] statements = new long[51];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[11];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.v8dtoa.RHINO-SRC-FastDtoaBuilder.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1,0,1};
    for (int i = 1; i <= 10; i++) {
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpthozf7zro38nftzmk8et8c0h2me09k1 () {
    super("org.mozilla.javascript.v8dtoa.RHINO-SRC-FastDtoaBuilder.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 50; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 10; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.v8dtoa.RHINO-SRC-FastDtoaBuilder.java");
      for (int i = 1; i <= 50; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 10; i++) {
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

