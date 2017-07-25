/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-2000
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Patrick Beard
 *   Norris Boyd
 *   Igor Bukanov
 *   Ethan Hugg
 *   Roger Lawrence
 *   Terry Lucas
 *   Frank Mitchell
 *   Milen Nankov
 *   Andrew Wason
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This is the class that implements the run-time.
 *
 */

public class ScriptRuntime {
  static {
    CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.ping();
  }


    /**
     * No instances should be created.
     */
    protected ScriptRuntime() {
    }

    // It is public so NativeRegExp can access it .
    public static boolean isJSLineTerminator(int c) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;
        // Optimization for faster check for EOL character:
        // they do not have 0xDFD0 bits set
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (((c & 0xDFD0) != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[1]++;
            return false;

        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[2]++;}
        return c == '\n' || c == '\r' || c == 0x2028 || c == 0x2029;
    }

    // Can not use Double.NaN defined as 0.0d / 0.0 as under the Microsoft VM,
    // versions 2.01 and 3.0P1, that causes some uses (returns at least) of
    // Double.NaN to be converted to 1.0.
    // So we use ScriptRuntime.NaN instead of Double.NaN.
    public static final double
        NaN = Double.longBitsToDouble(0x7ff8000000000000L);
  static {
    CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[2]++;
  }

    // A similar problem exists for negative zero.
    public static final double
        negativeZero = Double.longBitsToDouble(0x8000000000000000L);
  static {
    CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[3]++;
  }

    /*
     * Helper function for toNumber, parseInt, and TokenStream.getToken.
     */
    @SuppressWarnings("fallthrough")
    static double stringToNumber(String s, int start, int radix) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[4]++;
        char digitMax = '9';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[5]++;
        char lowerCaseBound = 'a';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[6]++;
        char upperCaseBound = 'A';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[7]++;
        int len = s.length();
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((radix < 10) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[3]++;
            digitMax = (char) ('0' + radix - 1);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[9]++;

        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[4]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((radix > 10) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[5]++;
            lowerCaseBound = (char) ('a' + radix - 10);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[11]++;
            upperCaseBound = (char) ('A' + radix - 10);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[12]++;

        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[6]++;}
        int end;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[13]++;
        double sum = 0.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
        for (end=start;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((end < len) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); end++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[1]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[2]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[3]++;
}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[15]++;
            char c = s.charAt(end);
            int newDigit;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[16]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 (('0' <= c) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((c <= digitMax) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[7]++;
                newDigit = c - '0';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[17]++;
}
            else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[8]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[18]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 (('a' <= c) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((c < lowerCaseBound) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[9]++;
                newDigit = c - 'a' + 10;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[19]++;
}
            else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[10]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[20]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 (('A' <= c) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c < upperCaseBound) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[11]++;
                newDigit = c - 'A' + 10;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[21]++;
}
            else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[12]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[22]++;
                break;
}
}
}
            sum = sum*radix + newDigit;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[23]++;
        }
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((start == end) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[13]++;
            return NaN;

        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[14]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sum >= 9007199254740992.0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[15]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[26]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((radix == 10) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[17]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[27]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
                /* If we're accumulating a decimal number and the number
                 * is >= 2^53, then the result from the repeated multiply-add
                 * above may be inaccurate.  Call Java to get the correct
                 * answer.
                 */
                try {
CodeCoverTryBranchHelper_Try1 = true;
                    return Double.valueOf(s.substring(start, end)).doubleValue();
                } catch (NumberFormatException nfe) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[20]++;
                    return NaN;
                } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[19]++;
}
  }

            } else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[18]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[28]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (512)) == 0 || true) &&
 ((radix == 2) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (128)) == 0 || true) &&
 ((radix == 4) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((radix == 8) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((radix == 16) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((radix == 32) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 5) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 5) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[21]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[29]++;
                /* The number may also be inaccurate for one of these bases.
                 * This happens if the addition in value*radix + digit causes
                 * a round-down to an even least significant mantissa bit
                 * when the first dropped bit is a one.  If any of the
                 * following digits in the number (which haven't been added
                 * in yet) are nonzero then the correct action would have
                 * been to round up instead of down.  An example of this
                 * occurs when reading the number 0x1000000000000081, which
                 * rounds to 0x1000000000000000 instead of 0x1000000000000100.
                 */
                int bitShiftInChar = 1;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[30]++;
                int digit = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[31]++;

                final int SKIP_LEADING_ZEROS = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[32]++;
                final int FIRST_EXACT_53_BITS = 1;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[33]++;
                final int AFTER_BIT_53         = 2;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[34]++;
                final int ZEROS_AFTER_54 = 3;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[35]++;
                final int MIXED_AFTER_54 = 4;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[36]++;

                int state = SKIP_LEADING_ZEROS;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[37]++;
                int exactBitsLimit = 53;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[38]++;
                double factor = 0.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[39]++;
                boolean bit53 = false;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[40]++;
                // bit54 is the 54th bit (the first dropped from the mantissa)
                boolean bit54 = false;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[41]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[4]++;



                for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[4]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[5]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[6]++;
}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[42]++;
int CodeCoverConditionCoverageHelper_C13;
                    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((bitShiftInChar == 1) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[23]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[43]++;
int CodeCoverConditionCoverageHelper_C14;
                        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((start == end) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[25]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[44]++;
                            break;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[26]++;}
                        digit = s.charAt(start++);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[45]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[46]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 (('0' <= digit) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((digit <= '9') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[27]++;
                            digit -= '0';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[47]++;
}
                        else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[28]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[48]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 (('a' <= digit) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((digit <= 'z') && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[29]++;
                            digit -= 'a' - 10;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[49]++;
}
                        else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[30]++;
                            digit -= 'A' - 10;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[50]++;
}
}
                        bitShiftInChar = radix;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[51]++;

                    } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[24]++;}
                    bitShiftInChar >>= 1;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[52]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[53]++;
                    boolean bit = (digit & bitShiftInChar) != 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[54]++;

                    switch (state) {
                      case SKIP_LEADING_ZEROS:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[31]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
                          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((bit) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[32]++;
                            --exactBitsLimit;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[56]++;
                            sum = 1.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[57]++;
                            state = FIRST_EXACT_53_BITS;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[58]++;

                        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[33]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[59]++;
                        break;
                      case FIRST_EXACT_53_BITS:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[34]++;
                           sum *= 2.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[60]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[61]++;
int CodeCoverConditionCoverageHelper_C18;
                        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((bit) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[35]++;
                            sum += 1.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[62]++;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[36]++;}
                        --exactBitsLimit;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[63]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[64]++;
int CodeCoverConditionCoverageHelper_C19;
                        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((exactBitsLimit == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[37]++;
                            bit53 = bit;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[65]++;
                            state = AFTER_BIT_53;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[66]++;

                        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[38]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[67]++;
                        break;
                      case AFTER_BIT_53:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[39]++;
                        bit54 = bit;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[68]++;
                        factor = 2.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[69]++;
                        state = ZEROS_AFTER_54;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[70]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[71]++;
                        break;
                      case ZEROS_AFTER_54:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[40]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[72]++;
int CodeCoverConditionCoverageHelper_C20;
                        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((bit) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[41]++;
                            state = MIXED_AFTER_54;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[73]++;

                        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[42]++;}
                        // fallthrough
                      case MIXED_AFTER_54:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[43]++;
                        factor *= 2;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[74]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[75]++;
                        break; default : CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[44]++;
                    }
                }
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[76]++;
                switch (state) {
                  case SKIP_LEADING_ZEROS:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[45]++;
                    sum = 0.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[77]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[78]++;
                    break;
                  case FIRST_EXACT_53_BITS:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[46]++;
                  case AFTER_BIT_53:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[47]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[79]++;
                    // do nothing
                    break;
                  case ZEROS_AFTER_54:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[48]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[80]++;
int CodeCoverConditionCoverageHelper_C21;
                    // x1.1 -> x1 + 1 (round up)
                    // x0.1 -> x0 (round down)
                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((bit54) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 & 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((bit53) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[49]++;
                        sum += 1.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[81]++;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[50]++;}
                    sum *= factor;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[82]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[83]++;
                    break;
                  case MIXED_AFTER_54:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[51]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[84]++;
int CodeCoverConditionCoverageHelper_C22;
                    // x.100...1.. -> x + 1 (round up)
                    // x.0anything -> x (round down)
                    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((bit54) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[52]++;
                        sum += 1.0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[85]++;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[53]++;}
                    sum *= factor;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[86]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[87]++;
                    break; default : CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[54]++;
                }

            } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[22]++;}
}

            /* We don't worry about inaccurate numbers for any other base. */
        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[16]++;}
        return sum;
    }

    public static String escapeString(String s) {
        return escapeString(s, '"');
    }

    /**
     * For escaping strings printed by object and array literals; not quite
     * the same as 'escape.'
     */
    public static String escapeString(String s, char escapeQuote) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[88]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C23 |= (8)) == 0 || true) &&
 ((escapeQuote == '"') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((escapeQuote == '\'') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[55]++;
          throw new IllegalStateException("unexpected quote char:" + escapeQuote);

        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[56]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[89]++;
        StringBuffer sb = null;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[90]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[7]++;


int CodeCoverConditionCoverageHelper_C24;

        for(int i = 0, L = s.length();(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[7]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[8]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[9]++;
}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[91]++;
            int c = s.charAt(i);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[92]++;
int CodeCoverConditionCoverageHelper_C25;

            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (128)) == 0 || true) &&
 ((' ' <= c) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((c <= '~') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((c != escapeQuote) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c != '\\') && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 4) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 4) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[57]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[93]++;
int CodeCoverConditionCoverageHelper_C26;
                // an ordinary print character (like C isprint()) and not "
                // or \ .
                if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((sb != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[59]++;
                    sb.append((char)c);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[94]++;

                } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[60]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[95]++;
                continue;

            } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[58]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[96]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((sb == null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[61]++;
                sb = new StringBuffer(L + 3);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[97]++;
                sb.append(s);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[98]++;
                sb.setLength(i);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[99]++;

            } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[62]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[100]++;

            int escape = -1;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[101]++;
            switch (c) {
                case '\b':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[63]++;  escape = 'b';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[102]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[103]++;  break;
                case '\f':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[64]++;  escape = 'f';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[104]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[105]++;  break;
                case '\n':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[65]++;  escape = 'n';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[106]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[107]++;  break;
                case '\r':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[66]++;  escape = 'r';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[108]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[109]++;  break;
                case '\t':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[67]++;  escape = 't';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[110]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[111]++;  break;
                case 0xb:
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[68]++;   escape = 'v';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[112]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[113]++;  break; // Java lacks \v.
                case ' ':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[69]++;   escape = ' ';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[114]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[115]++;  break;
                case '\\':
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[70]++;  escape = '\\';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[116]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[117]++; break; default : CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[71]++;
            }
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[118]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((escape >= 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[72]++;
                // an \escaped sort of character
                sb.append('\\');
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[119]++;
                sb.append((char)escape);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[120]++;

            } else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[73]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[121]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c == escapeQuote) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[74]++;
                sb.append('\\');
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[122]++;
                sb.append(escapeQuote);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[123]++;

            } else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[75]++;
                int hexSize;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[124]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c < 256) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[76]++;
                    // 2-digit hex
                    sb.append("\\x");
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[125]++;
                    hexSize = 2;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[126]++;

                } else {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[77]++;
                    // Unicode.
                    sb.append("\\u");
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[127]++;
                    hexSize = 4;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[128]++;
                }
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[129]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[10]++;


int CodeCoverConditionCoverageHelper_C31;
                // append hexadecimal form of c left-padded with 0
                for (int shift = (hexSize - 1) * 4;(((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((shift >= 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false); shift -= 4) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[10]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[11]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[12]++;
}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[130]++;
                    int digit = 0xf & (c >> shift);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[131]++;
                    int hc = (digit < 10) ? '0' + digit : 'a' - 10 + digit;
                    sb.append((char)hc);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[132]++;
                }
            }
}
        }
        return (sb == null) ? s : sb.toString();
    }

    static boolean isValidIdentifierName(String s) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[133]++;
        int L = s.length();
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[134]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((L == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[78]++;
            return false;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[79]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[135]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierStart(s.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[80]++;
            return false;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[81]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[136]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[13]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i = 1;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i != L) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[13]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[14]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[15]++;
}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[137]++;
int CodeCoverConditionCoverageHelper_C35;
            if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((Character.isJavaIdentifierPart(s.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[82]++;
                return false;
} else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[83]++;}
        }
        return !TokenStream.isKeyword(s);
    }

    /**
     * If str is a decimal presentation of Uint32 value, return it as long.
     * Otherwise, return -1L;
     */
    public static long testUint32String(String str) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[138]++;
        // The length of the decimal string representation of
        //  UINT32_MAX_VALUE, 4294967296
        final int MAX_VALUE_LENGTH = 10;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[139]++;

        int len = str.length();
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[140]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((1 <= len) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((len <= MAX_VALUE_LENGTH) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[84]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[141]++;
            int c = str.charAt(0);
            c -= '0';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[142]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[143]++;
int CodeCoverConditionCoverageHelper_C37;
            if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((c == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[86]++;
                // Note that 00,01 etc. are not valid Uint32 presentations
                return (len == 1) ? 0L : -1L;

            } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[87]++;}
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[144]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((1 <= c) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((c <= 9) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[88]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[145]++;
                long v = c;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[146]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[16]++;


int CodeCoverConditionCoverageHelper_C39;
                for (int i = 1;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i != len) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[16]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[17]--;
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.loops[18]++;
}
                    c = str.charAt(i) - '0';
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[147]++;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[148]++;
int CodeCoverConditionCoverageHelper_C40;
                    if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((0 <= c) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c <= 9) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[90]++;
                        return -1;

                    } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[91]++;}
                    v = 10 * v + c;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[149]++;
                }
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[150]++;
int CodeCoverConditionCoverageHelper_C41;
                // Check for overflow
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 (((v >>> 32) == 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[92]++;
                    return v;

                } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[93]++;}

            } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[89]++;}

        } else {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[85]++;}
        return -1;
    }

    static boolean isSpecialProperty(String s) {
        return s.equals("__proto__") || s.equals("__parent__");
    }

    // ------------------
    // Statements
    // ------------------

    public static String getMessage0(String messageId) {
        return getMessage(messageId, null);
    }

    public static String getMessage1(String messageId, Object arg1) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[151]++;
        Object[] arguments = {arg1};
        return getMessage(messageId, arguments);
    }

    /* OPT there's a noticeable delay for the first error!  Maybe it'd
     * make sense to use a ListResourceBundle instead of a properties
     * file to avoid (synchronized) text parsing.
     */
    public static String getMessage(String messageId, Object[] arguments) {
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[152]++;
        final String defaultResource
            = "rhino_ast.java.com.google.javascript.rhino.Messages";
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[153]++;

        Locale locale = Locale.getDefault();
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[154]++;

        // ResourceBundle does caching.
        ResourceBundle rb = ResourceBundle.getBundle(defaultResource, locale);

        String formatString;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[155]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
        try {
CodeCoverTryBranchHelper_Try2 = true;
            formatString = rb.getString(messageId);
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[156]++;
        } catch (java.util.MissingResourceException mre) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[95]++;
            throw new RuntimeException
                ("no message resource found for message property "+ messageId);
        } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.branches[94]++;
}
  }
CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9.statements[157]++;

        /*
         * It's OK to format the string, even if 'arguments' is null;
         * we need to format it anyway, to make double ''s collapse to
         * single 's.
         */
        // TODO: MessageFormat is not available on pJava
        MessageFormat formatter = new MessageFormat(formatString);
        return formatter.format(arguments);
    }
}

class CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9 ());
  }
    public static long[] statements = new long[158];
    public static long[] branches = new long[96];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[42];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.ScriptRuntime.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,2,2,1,1,1,3,0,1,1,2,2,1,1,1,1,2,1,2,1,3,1,1,1,1,1,1,1,1,1,1,2,1,2,1,2,1};
    for (int i = 1; i <= 41; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$6xn61xyjwldsuyfyouwvc0qa3ja9 () {
    super("com.google.javascript.rhino.ScriptRuntime.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 157; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 95; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.ScriptRuntime.java");
      for (int i = 1; i <= 157; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 95; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 41; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

