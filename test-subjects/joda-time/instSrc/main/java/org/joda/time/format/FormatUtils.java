/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.joda.time.format;

import java.io.IOException;
import java.io.Writer;

/**
 * Utility methods used by formatters.
 * <p>
 * FormatUtils is thread-safe and immutable.
 *
 * @author Brian S O'Neill
 * @since 1.0
 */
public class FormatUtils {
  static {
    CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.ping();
  }


    private static final double LOG_10 = Math.log(10);
  static {
    CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[1]++;
  }

    /**
     * Restricted constructor.
     */
    private FormatUtils() {
    }

    /**
     * Converts an integer to a string, prepended with a variable amount of '0'
     * pad characters, and appends it to the given buffer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param buf receives integer converted to a string
     * @param value value to convert to a string
     * @param size minumum amount of digits to append
     */
    public static void appendPaddedInteger(StringBuffer buf, int value, int size) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[1]++;
            buf.append('-');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[3]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((value != Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[3]++;
                value = -value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[5]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[4]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[1]++;


int CodeCoverConditionCoverageHelper_C3;
                for (;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((size > 10) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[1]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[2]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[3]++;
}
                    buf.append('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[7]++;
                }
                buf.append("" + -(long)Integer.MIN_VALUE);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[8]++;
                return;
            }

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[2]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[9]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value < 10) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[5]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[10]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
            for (;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((size > 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[4]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[5]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[6]++;
}
                buf.append('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[11]++;
            }
            buf.append((char)(value + '0'));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[12]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[6]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[13]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value < 100) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[7]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[14]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[7]++;


int CodeCoverConditionCoverageHelper_C7;
            for (;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((size > 2) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[7]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[8]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[9]++;
}
                buf.append('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[15]++;
            }
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[16]++;
            // Calculate value div/mod by 10 without using two expensive
            // division operations. (2 ^ 27) / 10 = 13421772. Add one to
            // value to correct rounding error.
            int d = ((value + 1) * 13421772) >> 27;
            buf.append((char) (d + '0'));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[17]++;
            // Append remainder by calculating (value - d * 10).
            buf.append((char) (value - (d << 3) - (d << 1) + '0'));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[18]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[8]++;
            int digits;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
            if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((value < 1000) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[9]++;
                digits = 3;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[20]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[10]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[21]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((value < 10000) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[11]++;
                digits = 4;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[22]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[12]++;
                digits = (int)(Math.log(value) / LOG_10) + 1;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[23]++;
            }
}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[24]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[10]++;


int CodeCoverConditionCoverageHelper_C10;
            for (;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((size > digits) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[10]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[11]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[12]++;
}
                buf.append('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[25]++;
            }
            buf.append(Integer.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[26]++;
        }
}
    }

    /**
     * Converts an integer to a string, prepended with a variable amount of '0'
     * pad characters, and appends it to the given buffer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param buf receives integer converted to a string
     * @param value value to convert to a string
     * @param size minumum amount of digits to append
     */
    public static void appendPaddedInteger(StringBuffer buf, long value, int size) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[27]++;
        int intValue = (int)value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[28]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((intValue == value) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[13]++;
            appendPaddedInteger(buf, intValue, size);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[29]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[14]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[30]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((size <= 19) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[15]++;
            buf.append(Long.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[31]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[16]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[32]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[17]++;
                buf.append('-');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[33]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[34]++;
int CodeCoverConditionCoverageHelper_C14;
                if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((value != Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[19]++;
                    value = -value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[35]++;

                } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[20]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[36]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[13]++;


int CodeCoverConditionCoverageHelper_C15;
                    for (;(((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((size > 19) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[13]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[14]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[15]++;
}
                        buf.append('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[37]++;
                    }
                    buf.append("9223372036854775808");
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[38]++;
                    return;
                }

            } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[18]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[39]++;
            int digits = (int)(Math.log(value) / LOG_10) + 1;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[40]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[16]++;


int CodeCoverConditionCoverageHelper_C16;
            for (;(((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((size > digits) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[16]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[17]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[18]++;
}
                buf.append('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[41]++;
            }
            buf.append(Long.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[42]++;
        }
}
    }

    /**
     * Converts an integer to a string, prepended with a variable amount of '0'
     * pad characters, and writes it to the given writer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param out receives integer converted to a string
     * @param value value to convert to a string
     * @param size minumum amount of digits to append
     */
    public static void writePaddedInteger(Writer out, int value, int size)
        throws IOException
    {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[43]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[21]++;
            out.write('-');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[44]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[45]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((value != Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[23]++;
                value = -value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[46]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[24]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[47]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[19]++;


int CodeCoverConditionCoverageHelper_C19;
                for (;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((size > 10) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[19]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[20]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[21]++;
}
                    out.write('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[48]++;
                }
                out.write("" + -(long)Integer.MIN_VALUE);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[49]++;
                return;
            }

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[22]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[50]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((value < 10) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[25]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[51]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[22]++;


int CodeCoverConditionCoverageHelper_C21;
            for (;(((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((size > 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[22]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[23]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[24]++;
}
                out.write('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[52]++;
            }
            out.write(value + '0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[53]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[26]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[54]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((value < 100) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[27]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[55]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[25]++;


int CodeCoverConditionCoverageHelper_C23;
            for (;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((size > 2) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[25]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[26]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[27]++;
}
                out.write('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[56]++;
            }
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[57]++;
            // Calculate value div/mod by 10 without using two expensive
            // division operations. (2 ^ 27) / 10 = 13421772. Add one to
            // value to correct rounding error.
            int d = ((value + 1) * 13421772) >> 27;
            out.write(d + '0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[58]++;
            // Append remainder by calculating (value - d * 10).
            out.write(value - (d << 3) - (d << 1) + '0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[59]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[28]++;
            int digits;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[60]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((value < 1000) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[29]++;
                digits = 3;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[61]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[30]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[62]++;
int CodeCoverConditionCoverageHelper_C25; if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((value < 10000) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[31]++;
                digits = 4;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[63]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[32]++;
                digits = (int)(Math.log(value) / LOG_10) + 1;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[64]++;
            }
}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[65]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[28]++;


int CodeCoverConditionCoverageHelper_C26;
            for (;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((size > digits) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[28]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[29]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[30]++;
}
                out.write('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[66]++;
            }
            out.write(Integer.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[67]++;
        }
}
    }

    /**
     * Converts an integer to a string, prepended with a variable amount of '0'
     * pad characters, and writes it to the given writer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param out receives integer converted to a string
     * @param value value to convert to a string
     * @param size minumum amount of digits to append
     */
    public static void writePaddedInteger(Writer out, long value, int size)
        throws IOException
    {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[68]++;
        int intValue = (int)value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[69]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((intValue == value) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[33]++;
            writePaddedInteger(out, intValue, size);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[70]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[34]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[71]++;
int CodeCoverConditionCoverageHelper_C28; if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((size <= 19) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[35]++;
            out.write(Long.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[72]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[36]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[73]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[37]++;
                out.write('-');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[74]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[75]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((value != Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[39]++;
                    value = -value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[76]++;

                } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[40]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[77]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[31]++;


int CodeCoverConditionCoverageHelper_C31;
                    for (;(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((size > 19) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[31]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[32]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[33]++;
}
                        out.write('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[78]++;
                    }
                    out.write("9223372036854775808");
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[79]++;
                    return;
                }

            } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[38]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[80]++;
            int digits = (int)(Math.log(value) / LOG_10) + 1;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[81]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[34]++;


int CodeCoverConditionCoverageHelper_C32;
            for (;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((size > digits) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); size--) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[34]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[35]--;
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.loops[36]++;
}
                out.write('0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[82]++;
            }
            out.write(Long.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[83]++;
        }
}
    }

    /**
     * Converts an integer to a string, and appends it to the given buffer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param buf receives integer converted to a string
     * @param value value to convert to a string
     */
    public static void appendUnpaddedInteger(StringBuffer buf, int value) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[84]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[41]++;
            buf.append('-');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[85]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[86]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((value != Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[43]++;
                value = -value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[87]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[44]++;
                buf.append("" + -(long)Integer.MIN_VALUE);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[88]++;
                return;
            }

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[42]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[89]++;
int CodeCoverConditionCoverageHelper_C35;
        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((value < 10) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[45]++;
            buf.append((char)(value + '0'));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[90]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[46]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[91]++;
int CodeCoverConditionCoverageHelper_C36; if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((value < 100) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[47]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[92]++;
            // Calculate value div/mod by 10 without using two expensive
            // division operations. (2 ^ 27) / 10 = 13421772. Add one to
            // value to correct rounding error.
            int d = ((value + 1) * 13421772) >> 27;
            buf.append((char) (d + '0'));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[93]++;
            // Append remainder by calculating (value - d * 10).
            buf.append((char) (value - (d << 3) - (d << 1) + '0'));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[94]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[48]++;
            buf.append(Integer.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[95]++;
        }
}
    }

    /**
     * Converts an integer to a string, and appends it to the given buffer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param buf receives integer converted to a string
     * @param value value to convert to a string
     */
    public static void appendUnpaddedInteger(StringBuffer buf, long value) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[96]++;
        int intValue = (int)value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[97]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((intValue == value) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[49]++;
            appendUnpaddedInteger(buf, intValue);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[98]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[50]++;
            buf.append(Long.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[99]++;
        }
    }

    /**
     * Converts an integer to a string, and writes it to the given writer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param out receives integer converted to a string
     * @param value value to convert to a string
     */
    public static void writeUnpaddedInteger(Writer out, int value)
        throws IOException
    {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[100]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[51]++;
            out.write('-');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[101]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[102]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((value != Integer.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[53]++;
                value = -value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[103]++;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[54]++;
                out.write("" + -(long)Integer.MIN_VALUE);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[104]++;
                return;
            }

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[52]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[105]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((value < 10) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[55]++;
            out.write(value + '0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[106]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[56]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[107]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((value < 100) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[57]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[108]++;
            // Calculate value div/mod by 10 without using two expensive
            // division operations. (2 ^ 27) / 10 = 13421772. Add one to
            // value to correct rounding error.
            int d = ((value + 1) * 13421772) >> 27;
            out.write(d + '0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[109]++;
            // Append remainder by calculating (value - d * 10).
            out.write(value - (d << 3) - (d << 1) + '0');
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[110]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[58]++;
            out.write(Integer.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[111]++;
        }
}
    }

    /**
     * Converts an integer to a string, and writes it to the given writer.
     *
     * <p>This method is optimized for converting small values to strings.
     *
     * @param out receives integer converted to a string
     * @param value value to convert to a string
     */
    public static void writeUnpaddedInteger(Writer out, long value)
        throws IOException
    {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[112]++;
        int intValue = (int)value;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[113]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((intValue == value) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[59]++;
            writeUnpaddedInteger(out, intValue);
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[114]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[60]++;
            out.write(Long.toString(value));
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[115]++;
        }
    }

    /**
     * Calculates the number of decimal digits for the given value,
     * including the sign.
     */
    public static int calculateDigitCount(long value) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[116]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((value < 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[61]++;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[117]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((value != Long.MIN_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[63]++;
                return calculateDigitCount(-value) + 1;

            } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[64]++;
                return 20;
            }

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[62]++;}
        return 
            (value < 10 ? 1 :
             (value < 100 ? 2 :
              (value < 1000 ? 3 :
               (value < 10000 ? 4 :
                ((int)(Math.log(value) / LOG_10) + 1)))));
    }

    static int parseTwoDigits(String text, int position) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[118]++;
        int value = text.charAt(position) - '0';
        return ((value << 3) + (value << 1)) + text.charAt(position + 1) - '0';
    }

    static String createErrorMessage(final String text, final int errorPos) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[119]++;
        int sampleLen = errorPos + 32;
        String sampleText;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[120]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((text.length() <= sampleLen + 3) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[65]++;
            sampleText = text;
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[121]++;

        } else {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[66]++;
            sampleText = text.substring(0, sampleLen).concat("...");
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[122]++;
        }
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[123]++;
int CodeCoverConditionCoverageHelper_C46;
        
        if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((errorPos <= 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[67]++;
            return "Invalid format: \"" + sampleText + '"';

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[68]++;}
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.statements[124]++;
int CodeCoverConditionCoverageHelper_C47;
        
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((errorPos >= text.length()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[69]++;
            return "Invalid format: \"" + sampleText + "\" is too short";

        } else {
  CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5.branches[70]++;}
        
        return "Invalid format: \"" + sampleText + "\" is malformed at \"" +
            sampleText.substring(errorPos) + '"';
    }

}

class CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5 ());
  }
    public static long[] statements = new long[125];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[48];
  static {
    final String SECTION_NAME = "org.joda.time.format.FormatUtils.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 47; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$4648ua6lyebpnoi7f2elzvrz5 () {
    super("org.joda.time.format.FormatUtils.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 124; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.joda.time.format.FormatUtils.java");
      for (int i = 1; i <= 124; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 70; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 47; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

