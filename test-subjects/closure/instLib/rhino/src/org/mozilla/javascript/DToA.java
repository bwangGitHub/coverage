/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/****************************************************************
  *
  * The author of this software is David M. Gay.
  *
  * Copyright (c) 1991, 2000, 2001 by Lucent Technologies.
  *
  * Permission to use, copy, modify, and distribute this software for any
  * purpose without fee is hereby granted, provided that this entire notice
  * is included in all copies of any software which is or includes a copy
  * or modification of this software and in all copies of the supporting
  * documentation for such software.
  *
  * THIS SOFTWARE IS BEING PROVIDED "AS IS", WITHOUT ANY EXPRESS OR IMPLIED
  * WARRANTY.  IN PARTICULAR, NEITHER THE AUTHOR NOR LUCENT MAKES ANY
  * REPRESENTATION OR WARRANTY OF ANY KIND CONCERNING THE MERCHANTABILITY
  * OF THIS SOFTWARE OR ITS FITNESS FOR ANY PARTICULAR PURPOSE.
  *
  ***************************************************************/

package org.mozilla.javascript;

import java.math.BigInteger;

class DToA {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.ping();
  }



    private static char BASEDIGIT(int digit) {
        return (char)((digit >= 10) ? 'a' - 10 + digit : '0' + digit);
    }

    static final int
        DTOSTR_STANDARD = 0,              /* Either fixed or exponential format; round-trip */
        DTOSTR_STANDARD_EXPONENTIAL = 1,  /* Always exponential format; round-trip */
        DTOSTR_FIXED = 2,                 /* Round to <precision> digits after the decimal point; exponential if number is large */
        DTOSTR_EXPONENTIAL = 3,           /* Always exponential format; <precision> significant digits */
        DTOSTR_PRECISION = 4;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[1]++;
  }             /* Either fixed or exponential format; <precision> significant digits */


    private static final int Frac_mask = 0xfffff;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[2]++;
  }
    private static final int Exp_shift = 20;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[3]++;
  }
    private static final int Exp_msk1 = 0x100000;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[4]++;
  }

    private static final long Frac_maskL = 0xfffffffffffffL;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[5]++;
  }
    private static final int Exp_shiftL = 52;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[6]++;
  }
    private static final long Exp_msk1L = 0x10000000000000L;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[7]++;
  }

    private static final int Bias = 1023;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[8]++;
  }
    private static final int P = 53;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[9]++;
  }

    private static final int Exp_shift1 = 20;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[10]++;
  }
    private static final int Exp_mask  = 0x7ff00000;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[11]++;
  }
    private static final int Exp_mask_shifted = 0x7ff;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[12]++;
  }
    private static final int Bndry_mask  = 0xfffff;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[13]++;
  }
    private static final int Log2P = 1;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[14]++;
  }

    private static final int Sign_bit = 0x80000000;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[15]++;
  }
    private static final int Exp_11  = 0x3ff00000;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[16]++;
  }
    private static final int Ten_pmax = 22;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[17]++;
  }
    private static final int Quick_max = 14;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[18]++;
  }
    private static final int Bletch = 0x10;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[19]++;
  }
    private static final int Frac_mask1 = 0xfffff;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[20]++;
  }
    private static final int Int_max = 14;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[21]++;
  }
    private static final int n_bigtens = 5;
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[22]++;
  }


    private static final double tens[] = {
        1e0, 1e1, 1e2, 1e3, 1e4, 1e5, 1e6, 1e7, 1e8, 1e9,
        1e10, 1e11, 1e12, 1e13, 1e14, 1e15, 1e16, 1e17, 1e18, 1e19,
        1e20, 1e21, 1e22
    };
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[23]++;
  }

    private static final double bigtens[] = { 1e16, 1e32, 1e64, 1e128, 1e256 };
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[24]++;
  }

    private static int lo0bits(int y)
    {
        int k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[25]++;
        int x = y;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[26]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 (((x & 7) != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[1]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[27]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((x & 1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[3]++;
                return 0;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[4]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[28]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 (((x & 2) != 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[5]++;
                return 1;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[6]++;}
            return 2;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[2]++;}
        k = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[29]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 (((x & 0xffff) == 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[7]++;
            k = 16;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[31]++;
            x >>>= 16;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[32]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[8]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[33]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 (((x & 0xff) == 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[9]++;
            k += 8;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[34]++;
            x >>>= 8;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[35]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[10]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (((x & 0xf) == 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[11]++;
            k += 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[37]++;
            x >>>= 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[38]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[12]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[39]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (((x & 0x3) == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[13]++;
            k += 2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[40]++;
            x >>>= 2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[41]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[14]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 (((x & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[15]++;
            k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[43]++;
            x >>>= 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[44]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[45]++;
int CodeCoverConditionCoverageHelper_C9;
            if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 (((x & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[17]++;
                return 32;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[18]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[16]++;}
        return k;
    }

    /* Return the number (0 through 32) of most significant zero bits in x. */
    private static int hi0bits(int x)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[46]++;
        int k = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[47]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 (((x & 0xffff0000) == 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[19]++;
            k = 16;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[48]++;
            x <<= 16;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[49]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[20]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 (((x & 0xff000000) == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[21]++;
            k += 8;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[51]++;
            x <<= 8;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[52]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[22]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 (((x & 0xf0000000) == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[23]++;
            k += 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[54]++;
            x <<= 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[55]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[24]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[56]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 (((x & 0xc0000000) == 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[25]++;
            k += 2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[57]++;
            x <<= 2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[58]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[26]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[59]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 (((x & 0x80000000) == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[27]++;
            k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[60]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[61]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 (((x & 0x40000000) == 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[29]++;
                return 32;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[30]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[28]++;}
        return k;
    }

    private static void stuffBits(byte bits[], int offset, int val)
    {
        bits[offset] = (byte)(val >> 24);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[62]++;
        bits[offset + 1] = (byte)(val >> 16);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[63]++;
        bits[offset + 2] = (byte)(val >> 8);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[64]++;
        bits[offset + 3] = (byte)(val);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[65]++;
    }

    /* Convert d into the form b*2^e, where b is an odd integer.  b is the returned
     * Bigint and e is the returned binary exponent.  Return the number of significant
     * bits in b in bits.  d must be finite and nonzero. */
    private static BigInteger d2b(double d, int[] e, int[] bits)
    {
        byte dbl_bits[];
        int i, k, y, z, de;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[66]++;
        long dBits = Double.doubleToLongBits(d);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[67]++;
        int d0 = (int)(dBits >>> 32);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[68]++;
        int d1 = (int)(dBits);

        z = d0 & Frac_mask;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[69]++;
        d0 &= 0x7fffffff;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[70]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[71]++;   /* clear sign bit, which we ignore */

        if ((de = (d0 >>> Exp_shift)) != 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[31]++;
            z |= Exp_msk1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[72]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[32]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[73]++;

        if ((y = d1) != 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[33]++;
            dbl_bits = new byte[8];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[74]++;
            k = lo0bits(y);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[75]++;
            y >>>= k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[76]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[77]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((k != 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[35]++;
                stuffBits(dbl_bits, 4, y | z << (32 - k));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[78]++;
                z >>= k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[79]++;

            }
            else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[36]++;
                stuffBits(dbl_bits, 4, y);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[80]++;
}
            stuffBits(dbl_bits, 0, z);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[81]++;
            i = (z != 0) ? 2 : 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[82]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[34]++;
    //        JS_ASSERT(z);
            dbl_bits = new byte[4];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[83]++;
            k = lo0bits(z);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[84]++;
            z >>>= k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[85]++;
            stuffBits(dbl_bits, 0, z);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[86]++;
            k += 32;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[87]++;
            i = 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[88]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[89]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((de != 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[37]++;
            e[0] = de - Bias - (P-1) + k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[90]++;
            bits[0] = P - k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[91]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[38]++;
            e[0] = de - Bias - (P-1) + 1 + k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[92]++;
            bits[0] = 32*i - hi0bits(z);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[93]++;
        }
        return new BigInteger(dbl_bits);
    }

    static String JS_dtobasestr(int base, double d)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[94]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((2 <= base) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((base <= 36) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[39]++;
            throw new IllegalArgumentException("Bad base: "+base);
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[40]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[95]++;
int CodeCoverConditionCoverageHelper_C21;

        /* Check for Infinity and NaN */
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((Double.isNaN(d)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[41]++;
            return "NaN";

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[42]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[96]++;
int CodeCoverConditionCoverageHelper_C22; if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((Double.isInfinite(d)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[43]++;
            return (d > 0.0) ? "Infinity" : "-Infinity";

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[44]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[97]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((d == 0) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[45]++;
            // ALERT: should it distinguish -0.0 from +0.0 ?
            return "0";

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[46]++;}
}
}

        boolean negative;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[98]++;
int CodeCoverConditionCoverageHelper_C24;
        if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((d >= 0.0) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[47]++;
            negative = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[99]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[48]++;
            negative = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[100]++;
            d = -d;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[101]++;
        }

        /* Get the integer part of d including '-' sign. */
        String intDigits;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[102]++;

        double dfloor = Math.floor(d);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[103]++;
        long lfloor = (long)dfloor;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[104]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((lfloor == dfloor) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[49]++;
            // int part fits long
            intDigits = Long.toString((negative) ? -lfloor : lfloor, base);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[105]++;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[50]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[106]++;
            // BigInteger should be used
            long floorBits = Double.doubleToLongBits(dfloor);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[107]++;
            int exp = (int)(floorBits >> Exp_shiftL) & Exp_mask_shifted;
            long mantissa;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[108]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((exp == 0) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[51]++;
                mantissa = (floorBits & Frac_maskL) << 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[109]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[52]++;
                mantissa = (floorBits & Frac_maskL) | Exp_msk1L;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[110]++;
            }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[111]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((negative) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[53]++;
                mantissa = -mantissa;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[112]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[54]++;}
            exp -= 1075;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[113]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[114]++;
            BigInteger x = BigInteger.valueOf(mantissa);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[115]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((exp > 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[55]++;
                x = x.shiftLeft(exp);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[116]++;

            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[56]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[117]++;
int CodeCoverConditionCoverageHelper_C29; if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((exp < 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[57]++;
                x = x.shiftRight(-exp);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[118]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[58]++;}
}
            intDigits = x.toString(base);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[119]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[120]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((d == dfloor) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[59]++;
            // No fraction part
            return intDigits;

        } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[60]++;
            /* We have a fraction. */

            StringBuilder buffer;       /* The output string */
            int digit;
            double df;           /* The fractional part of d */
            BigInteger b;

            buffer = new StringBuilder();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[121]++;
            buffer.append(intDigits).append('.');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[122]++;
            df = d - dfloor;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[123]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[124]++;

            long dBits = Double.doubleToLongBits(d);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[125]++;
            int word0 = (int)(dBits >> 32);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[126]++;
            int word1 = (int)(dBits);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[127]++;

            int[] e = new int[1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[128]++;
            int[] bbits = new int[1];

            b = d2b(df, e, bbits);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[129]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[130]++;
//            JS_ASSERT(e < 0);
            /* At this point df = b * 2^e.  e must be less than zero because 0 < df < 1. */

            int s2 = -(word0 >>> Exp_shift1 & Exp_mask >> Exp_shift1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[131]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((s2 == 0) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[61]++;
                s2 = -1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[132]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[62]++;}
            s2 += Bias + P;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[133]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[134]++;
            /* 1/2^s2 = (nextDouble(d) - d)/2 */
//            JS_ASSERT(-s2 < e);
            BigInteger mlo = BigInteger.valueOf(1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[135]++;
            BigInteger mhi = mlo;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[136]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C32 |= (32)) == 0 || true) &&
 ((word1 == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 (((word0 & Bndry_mask) == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 (((word0 & (Exp_mask & Exp_mask << 1)) != 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[63]++;
                /* The special case.  Here we want to be within a quarter of the last input
                   significant digit instead of one half of it when the output string's value is less than d.  */
                s2 += Log2P;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[137]++;
                mhi = BigInteger.valueOf(1<<Log2P);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[138]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[64]++;}

            b = b.shiftLeft(e[0] + s2);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[139]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[140]++;
            BigInteger s = BigInteger.valueOf(1);
            s = s.shiftLeft(s2);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[141]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[142]++;
            /* At this point we have the following:
             *   s = 2^s2;
             *   1 > df = b/2^s2 > 0;
             *   (d - prevDouble(d))/2 = mlo/2^s2;
             *   (nextDouble(d) - d)/2 = mhi/2^s2. */
            BigInteger bigBase = BigInteger.valueOf(base);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[143]++;

            boolean done = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[144]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[1]++;


int CodeCoverConditionCoverageHelper_C33;
            do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[1]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[2]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[3]++;
}
                b = b.multiply(bigBase);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[145]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[146]++;
                BigInteger[] divResult = b.divideAndRemainder(s);
                b = divResult[1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[147]++;
                digit = (char)(divResult[0].intValue());
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[148]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[149]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((mlo == mhi) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[65]++;
                    mlo = mhi = mlo.multiply(bigBase);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[150]++;
}
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[66]++;
                    mlo = mlo.multiply(bigBase);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[151]++;
                    mhi = mhi.multiply(bigBase);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[152]++;
                }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[153]++;

                /* Do we yet have the shortest string that will round to d? */
                int j = b.compareTo(mlo);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[154]++;
                /* j is b/2^s2 compared with mlo/2^s2. */
                BigInteger delta = s.subtract(mhi);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[155]++;
                int j1 = (delta.signum() <= 0) ? 1 : b.compareTo(delta);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[156]++;
int CodeCoverConditionCoverageHelper_C35;
                /* j1 is b/2^s2 compared with 1 - mhi/2^s2. */
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((j1 == 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 (((word1 & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[67]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[157]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((j > 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[69]++;
                        digit++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[158]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[70]++;}
                    done = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[159]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[68]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[160]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (32)) == 0 || true) &&
 ((j < 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 (((word1 & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[71]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[161]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((j1 > 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[73]++;
                        /* Either dig or dig+1 would work here as the least significant digit.
                           Use whichever would produce an output value closer to d. */
                        b = b.shiftLeft(1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[162]++;
                        j1 = b.compareTo(s);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[163]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[164]++;
int CodeCoverConditionCoverageHelper_C39;
                        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((j1 > 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[75]++; /* The even test (|| (j1 == 0 && (digit & 1))) is not here because it messes up odd base output
                                     * such as 3.5 in base 3.  */
                            digit++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[165]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[76]++;}

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[74]++;}
                    done = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[166]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[72]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[167]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((j1 > 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[77]++;
                    digit++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[168]++;
                    done = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[169]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[78]++;}
}
}
//                JS_ASSERT(digit < (uint32)base);
                buffer.append(BASEDIGIT(digit));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[170]++;
            } while ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((done) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false));

            return buffer.toString();
        }

    }

    /* dtoa for IEEE arithmetic (dmg): convert double to ASCII string.
     *
     * Inspired by "How to Print Floating-Point Numbers Accurately" by
     * Guy L. Steele, Jr. and Jon L. White [Proc. ACM SIGPLAN '90, pp. 92-101].
     *
     * Modifications:
     *  1. Rather than iterating, we use a simple numeric overestimate
     *     to determine k = floor(log10(d)).  We scale relevant
     *     quantities using O(log2(k)) rather than O(k) multiplications.
     *  2. For some modes > 2 (corresponding to ecvt and fcvt), we don't
     *     try to generate digits strictly left to right.  Instead, we
     *     compute with fewer bits and propagate the carry if necessary
     *     when rounding the final digit up.  This is often faster.
     *  3. Under the assumption that input will be rounded nearest,
     *     mode 0 renders 1e23 as 1e23 rather than 9.999999999999999e22.
     *     That is, we allow equality in stopping tests when the
     *     round-nearest rule will give the same floating-point value
     *     as would satisfaction of the stopping test with strict
     *     inequality.
     *  4. We remove common factors of powers of 2 from relevant
     *     quantities.
     *  5. When converting floating-point integers less than 1e16,
     *     we use floating-point arithmetic rather than resorting
     *     to multiple-precision integers.
     *  6. When asked to produce fewer than 15 digits, we first try
     *     to get by with floating-point arithmetic; we resort to
     *     multiple-precision integer arithmetic only if we cannot
     *     guarantee that the floating-point calculation has given
     *     the correctly rounded result.  For k requested digits and
     *     "uniformly" distributed input, the probability is
     *     something like 10^(k-15) that we must resort to the Long
     *     calculation.
     */

    static int word0(double d)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[171]++;
        long dBits = Double.doubleToLongBits(d);
        return (int)(dBits >> 32);
    }

    static double setWord0(double d, int i)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[172]++;
        long dBits = Double.doubleToLongBits(d);
        dBits = ((long)i << 32) | (dBits & 0x0FFFFFFFFL);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[173]++;
        return Double.longBitsToDouble(dBits);
    }

    static int word1(double d)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[174]++;
        long dBits = Double.doubleToLongBits(d);
        return (int)(dBits);
    }

    /* Return b * 5^k.  k must be nonnegative. */
    // XXXX the C version built a cache of these
    static BigInteger pow5mult(BigInteger b, int k)
    {
        return b.multiply(BigInteger.valueOf(5).pow(k));
    }

    static boolean roundOff(StringBuilder buf)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[175]++;
        int i = buf.length();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[176]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[4]++;


int CodeCoverConditionCoverageHelper_C41;
        while ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[4]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[5]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[6]++;
}
            --i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[177]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[178]++;
            char c = buf.charAt(i);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[179]++;
int CodeCoverConditionCoverageHelper_C42;
            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((c != '9') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[79]++;
                buf.setCharAt(i, (char)(c + 1));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[180]++;
                buf.setLength(i + 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[181]++;
                return false;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[80]++;}
        }
        buf.setLength(0);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[182]++;
        return true;
    }

    /* Always emits at least one digit. */
    /* If biasUp is set, then rounding in modes 2 and 3 will round away from zero
     * when the number is exactly halfway between two representable values.  For example,
     * rounding 2.5 to zero digits after the decimal point will return 3 and not 2.
     * 2.49 will still round to 2, and 2.51 will still round to 3. */
    /* bufsize should be at least 20 for modes 0 and 1.  For the other modes,
     * bufsize should be two greater than the maximum number of output characters expected. */
    static int
    JS_dtoa(double d, int mode, boolean biasUp, int ndigits,
                    boolean[] sign, StringBuilder buf)
    {
        /*  Arguments ndigits, decpt, sign are similar to those
            of ecvt and fcvt; trailing zeros are suppressed from
            the returned string.  If not null, *rve is set to point
            to the end of the return value.  If d is +-Infinity or NaN,
            then *decpt is set to 9999.

            mode:
            0 ==> shortest string that yields d when read in
            and rounded to nearest.
            1 ==> like 0, but with Steele & White stopping rule;
            e.g. with IEEE P754 arithmetic , mode 0 gives
            1e23 whereas mode 1 gives 9.999999999999999e22.
            2 ==> max(1,ndigits) significant digits.  This gives a
            return value similar to that of ecvt, except
            that trailing zeros are suppressed.
            3 ==> through ndigits past the decimal point.  This
            gives a return value similar to that from fcvt,
            except that trailing zeros are suppressed, and
            ndigits can be negative.
            4-9 should give the same return values as 2-3, i.e.,
            4 <= mode <= 9 ==> same return as mode
            2 + (mode & 1).  These modes are mainly for
            debugging; often they run slower but sometimes
            faster than modes 2-3.
            4,5,8,9 ==> left-to-right digit generation.
            6-9 ==> don't try fast floating-point estimate
            (if applicable).

            Values of mode other than 0-9 are treated as mode 0.

            Sufficient space is allocated to the return value
            to hold the suppressed trailing zeros.
        */

        int b2, b5, i, ieps, ilim, ilim0, ilim1,
            j, j1, k, k0, m2, m5, s2, s5;
        char dig;
        long L;
        long x;
        BigInteger b, b1, delta, mlo, mhi, S;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[183]++;
        int[] be = new int[1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[184]++;
        int[] bbits = new int[1];
        double d2, ds, eps;
        boolean spec_case, denorm, k_check, try_quick, leftright;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[185]++;
int CodeCoverConditionCoverageHelper_C43;

        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 (((word0(d) & Sign_bit) != 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[81]++;
            /* set sign for everything, including 0's and NaNs */
            sign[0] = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[186]++;
            // word0(d) &= ~Sign_bit;  /* clear sign bit */
            d = setWord0(d, word0(d) & ~Sign_bit);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[187]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[82]++;
            sign[0] = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[188]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[189]++;
int CodeCoverConditionCoverageHelper_C44;

        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 (((word0(d) & Exp_mask) == Exp_mask) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[83]++;
            /* Infinity or NaN */
            buf.append(((word1(d) == 0) && ((word0(d) & Frac_mask) == 0)) ? "Infinity" : "NaN");
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[190]++;
            return 9999;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[84]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[191]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((d == 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[85]++;
//          no_digits:
            buf.setLength(0);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[192]++;
            buf.append('0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[193]++;        /* copy "0" to buffer */
            return 1;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[86]++;}

        b = d2b(d, be, bbits);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[194]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[195]++;
        if ((i = (word0(d) >>> Exp_shift1 & (Exp_mask>>Exp_shift1))) != 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[87]++;
            d2 = setWord0(d, (word0(d) & Frac_mask1) | Exp_11);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[196]++;
            /* log(x)   ~=~ log(1.5) + (x-1.5)/1.5
             * log10(x)  =  log(x) / log(10)
             *      ~=~ log(1.5)/log(10) + (x-1.5)/(1.5*log(10))
             * log10(d) = (i-Bias)*log(2)/log(10) + log10(d2)
             *
             * This suggests computing an approximation k to log10(d) by
             *
             * k = (i - Bias)*0.301029995663981
             *  + ( (d2-1.5)*0.289529654602168 + 0.176091259055681 );
             *
             * We want k to be too large rather than too small.
             * The error in the first-order Taylor series approximation
             * is in our favor, so we just round up the constant enough
             * to compensate for any error in the multiplication of
             * (i - Bias) by 0.301029995663981; since |i - Bias| <= 1077,
             * and 1077 * 0.30103 * 2^-52 ~=~ 7.2e-14,
             * adding 1e-13 to the constant term more than suffices.
             * Hence we adjust the constant term to 0.1760912590558.
             * (We could get a more accurate k by invoking log10,
             *  but this is probably not worthwhile.)
             */
            i -= Bias;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[197]++;
            denorm = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[198]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[88]++;
            /* d is denormalized */
            i = bbits[0] + be[0] + (Bias + (P-1) - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[199]++;
            x = (i > 32)
                    ? ((long) word0(d)) << (64 - i) | word1(d) >>> (i - 32)
                    : ((long) word1(d)) << (32 - i);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[200]++;
//            d2 = x;
//            word0(d2) -= 31*Exp_msk1; /* adjust exponent */
            d2 = setWord0(x, word0(x) - 31*Exp_msk1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[201]++;
            i -= (Bias + (P-1) - 1) + 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[202]++;
            denorm = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[203]++;
        }
        /* At this point d = f*2^i, where 1 <= f < 2.  d2 is an approximation of f. */
        ds = (d2-1.5)*0.289529654602168 + 0.1760912590558 + i*0.301029995663981;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[204]++;
        k = (int)ds;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[205]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[206]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((ds < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ds != k) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[89]++;
            k--;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[207]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[90]++;}    /* want k = floor(ds) */
        k_check = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[208]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[209]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (8)) == 0 || true) &&
 ((k >= 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((k <= Ten_pmax) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[91]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[210]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((d < tens[k]) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[93]++;
                k--;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[211]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[94]++;}
            k_check = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[212]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[92]++;}
        /* At this point floor(log10(d)) <= k <= floor(log10(d))+1.
           If k_check is zero, we're guaranteed that k = floor(log10(d)). */
        j = bbits[0] - i - 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[213]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[214]++;
int CodeCoverConditionCoverageHelper_C50;
        /* At this point d = b/2^j, where b is an odd integer. */
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((j >= 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[95]++;
            b2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[215]++;
            s2 = j;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[216]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[96]++;
            b2 = -j;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[217]++;
            s2 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[218]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[219]++;
int CodeCoverConditionCoverageHelper_C51;
        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((k >= 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[97]++;
            b5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[220]++;
            s5 = k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[221]++;
            s2 += k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[222]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[98]++;
            b2 -= k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[223]++;
            b5 = -k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[224]++;
            s5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[225]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[226]++;
int CodeCoverConditionCoverageHelper_C52;
        /* At this point d/10^k = (b * 2^b2 * 5^b5) / (2^s2 * 5^s5), where b is an odd integer,
           b2 >= 0, b5 >= 0, s2 >= 0, and s5 >= 0. */
        if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((mode < 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((mode > 9) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[99]++;
            mode = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[227]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[100]++;}
        try_quick = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[228]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[229]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((mode > 5) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[101]++;
            mode -= 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[230]++;
            try_quick = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[231]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[102]++;}
        leftright = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[232]++;
        ilim = ilim1 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[233]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[234]++;
        switch(mode) {
            case 0:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[103]++;
            case 1:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[104]++;
                ilim = ilim1 = -1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[235]++;
                i = 18;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[236]++;
                ndigits = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[237]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[238]++;
                break;
            case 2:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[105]++;
                leftright = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[239]++;
                /* no break */
            case 4:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[106]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[240]++;
int CodeCoverConditionCoverageHelper_C54;
                if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ndigits <= 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[107]++;
                    ndigits = 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[241]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[108]++;}
                ilim = ilim1 = i = ndigits;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[242]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[243]++;
                break;
            case 3:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[109]++;
                leftright = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[244]++;
                /* no break */
            case 5:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[110]++;
                i = ndigits + k + 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[245]++;
                ilim = i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[246]++;
                ilim1 = i - 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[247]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[248]++;
int CodeCoverConditionCoverageHelper_C55;
                if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((i <= 0) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[111]++;
                    i = 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[249]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[112]++;} default : CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[113]++;
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[250]++;
        /* ilim is the maximum number of significant digits we want, based on k and ndigits. */
        /* ilim1 is the maximum number of significant digits we want, based on k and ndigits,
           when it turns out that k was computed too high by one. */

        boolean fast_failed = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[251]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (32)) == 0 || true) &&
 ((ilim >= 0) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (8)) == 0 || true) &&
 ((ilim <= Quick_max) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((try_quick) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[114]++;

            /* Try to get by with floating-point arithmetic. */

            i = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[252]++;
            d2 = d;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[253]++;
            k0 = k;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[254]++;
            ilim0 = ilim;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[255]++;
            ieps = 2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[256]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[257]++;
int CodeCoverConditionCoverageHelper_C57; /* conservative */
            /* Divide d by 10^k, keeping track of the roundoff error and avoiding overflows. */
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((k > 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[116]++;
                ds = tens[k&0xf];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[258]++;
                j = k >> 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[259]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[260]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 (((j & Bletch) != 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[118]++;
                    /* prevent overflows */
                    j &= Bletch - 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[261]++;
                    d /= bigtens[n_bigtens-1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[262]++;
                    ieps++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[263]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[119]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[264]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[7]++;


int CodeCoverConditionCoverageHelper_C59;
                for(;(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((j != 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false); j >>= 1, i++) { 
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[7]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[8]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[9]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[265]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 (((j & 1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[120]++;
                        ieps++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[266]++;
                        ds *= bigtens[i];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[267]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[121]++;}
  }
                d /= ds;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[268]++;

            }
            else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[117]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[269]++; if ((j1 = -k) != 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[122]++;
                d *= tens[j1 & 0xf];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[270]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[271]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[10]++;


int CodeCoverConditionCoverageHelper_C62;
                for(j = j1 >> 4;(((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((j != 0) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false); j >>= 1, i++) { 
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[10]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[11]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[12]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[272]++;
int CodeCoverConditionCoverageHelper_C63;
                    if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 (((j & 1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[124]++;
                        ieps++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[273]++;
                        d *= bigtens[i];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[274]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[125]++;}
  }

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[123]++;}
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[275]++;
int CodeCoverConditionCoverageHelper_C64;
            /* Check that k was computed correctly. */
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (32)) == 0 || true) &&
 ((k_check) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((d < 1.0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((ilim > 0) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[126]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[276]++;
int CodeCoverConditionCoverageHelper_C65;
                if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((ilim1 <= 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[128]++;
                    fast_failed = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[277]++;
}
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[129]++;
                    ilim = ilim1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[278]++;
                    k--;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[279]++;
                    d *= 10.;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[280]++;
                    ieps++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[281]++;
                }

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[127]++;}
            /* eps bounds the cumulative error. */
//            eps = ieps*d + 7.0;
//            word0(eps) -= (P-1)*Exp_msk1;
            eps = ieps*d + 7.0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[282]++;
            eps = setWord0(eps, word0(eps) - (P-1)*Exp_msk1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[283]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[284]++;
int CodeCoverConditionCoverageHelper_C66;
            if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((ilim == 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[130]++;
                S = mhi = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[285]++;
                d -= 5.0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[286]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[287]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((d > eps) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[132]++;
                    buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[288]++;
                    k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[289]++;
                    return k + 1;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[133]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[290]++;
int CodeCoverConditionCoverageHelper_C68;
                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((d < -eps) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[134]++;
                    buf.setLength(0);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[291]++;
                    buf.append('0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[292]++;        /* copy "0" to buffer */
                    return 1;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[135]++;}
                fast_failed = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[293]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[131]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[294]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((fast_failed) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[136]++;
                fast_failed = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[295]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[296]++;
int CodeCoverConditionCoverageHelper_C70;
                if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((leftright) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[138]++;
                    /* Use Steele & White method of only
                     * generating digits needed.
                     */
                    eps = 0.5/tens[ilim-1] - eps;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[297]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[298]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[13]++;


                    for(i = 0;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[13]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[14]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[15]++;
}
                        L = (long)d;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[299]++;
                        d -= L;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[300]++;
                        buf.append((char)('0' + L));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[301]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[302]++;
int CodeCoverConditionCoverageHelper_C72;
                        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((d < eps) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[140]++;
                            return k + 1;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[141]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[303]++;
int CodeCoverConditionCoverageHelper_C73;
                        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((1.0 - d < eps) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[142]++;
//                            goto bump_up;
                                char lastCh;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[304]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[16]++;


                                while (true) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[16]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[17]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[18]++;
}
                                    lastCh = buf.charAt(buf.length() - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[305]++;
                                    buf.setLength(buf.length() - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[306]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[307]++;
int CodeCoverConditionCoverageHelper_C75;
                                    if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((lastCh != '9') && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[144]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[308]++; break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[145]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[309]++;
int CodeCoverConditionCoverageHelper_C76;
                                    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((buf.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[146]++;
                                        k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[310]++;
                                        lastCh = '0';
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[311]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[312]++;
                                        break;

                                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[147]++;}
                                }
                                buf.append((char)(lastCh + 1));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[313]++;
                                return k + 1;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[143]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[314]++;
int CodeCoverConditionCoverageHelper_C77;
                        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((++i >= ilim) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[148]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[315]++;
                            break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[149]++;}
                        eps *= 10.0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[316]++;
                        d *= 10.0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[317]++;
                    }

                }
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[139]++;
                    /* Generate ilim digits, then fix them up. */
                    eps *= tens[ilim-1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[318]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[319]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[19]++;


                    for(i = 1;; i++, d *= 10.0) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[19]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[20]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[21]++;
}
                        L = (long)d;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[320]++;
                        d -= L;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[321]++;
                        buf.append((char)('0' + L));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[322]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[323]++;
int CodeCoverConditionCoverageHelper_C79;
                        if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((i == ilim) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[150]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[324]++;
int CodeCoverConditionCoverageHelper_C80;
                            if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((d > 0.5 + eps) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[152]++;
//                                goto bump_up;
                                char lastCh;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[325]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[22]++;


                                while (true) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[22]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[23]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[24]++;
}
                                    lastCh = buf.charAt(buf.length() - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[326]++;
                                    buf.setLength(buf.length() - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[327]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[328]++;
int CodeCoverConditionCoverageHelper_C82;
                                    if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((lastCh != '9') && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[154]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[329]++; break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[155]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[330]++;
int CodeCoverConditionCoverageHelper_C83;
                                    if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((buf.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[156]++;
                                        k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[331]++;
                                        lastCh = '0';
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[332]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[333]++;
                                        break;

                                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[157]++;}
                                }
                                buf.append((char)(lastCh + 1));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[334]++;
                                return k + 1;

                            }
                            else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[153]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[335]++;
int CodeCoverConditionCoverageHelper_C84;
                                if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((d < 0.5 - eps) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[158]++;
                                    stripTrailingZeroes(buf);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[336]++;
//                                    while(*--s == '0') ;
//                                    s++;
                                    return k + 1;

                                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[159]++;}
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[337]++;
                            break;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[151]++;}
                    }
                }

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[137]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[338]++;
int CodeCoverConditionCoverageHelper_C85;
            if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((fast_failed) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[160]++;
                buf.setLength(0);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[339]++;
                d = d2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[340]++;
                k = k0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[341]++;
                ilim = ilim0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[342]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[161]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[115]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[343]++;
int CodeCoverConditionCoverageHelper_C86;

        /* Do we have a "small" integer? */

        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((be[0] >= 0) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((k <= Int_max) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[162]++;
            /* Yes. */
            ds = tens[k];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[344]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[345]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (8)) == 0 || true) &&
 ((ndigits < 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((ilim <= 0) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[164]++;
                S = mhi = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[346]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[347]++;
int CodeCoverConditionCoverageHelper_C88;
                if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (128)) == 0 || true) &&
 ((ilim < 0) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C88 |= (32)) == 0 || true) &&
 ((d < 5*ds) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (16)) == 0 || true)))
 || (!
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((biasUp) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((d == 5*ds) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 4) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 4) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[166]++;
                    buf.setLength(0);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[348]++;
                    buf.append('0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[349]++;        /* copy "0" to buffer */
                    return 1;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[167]++;}
                buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[350]++;
                k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[351]++;
                return k + 1;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[165]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[352]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[25]++;


            for(i = 1;; i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[25]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[26]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[27]++;
}
                L = (long) (d / ds);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[353]++;
                d -= L*ds;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[354]++;
                buf.append((char)('0' + L));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[355]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[356]++;
int CodeCoverConditionCoverageHelper_C90;
                if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((i == ilim) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[168]++;
                    d += d;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[357]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[358]++;
int CodeCoverConditionCoverageHelper_C91;
                    if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C91 |= (128)) == 0 || true) &&
 ((d > ds) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (64)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C91 |= (32)) == 0 || true) &&
 ((d == ds) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (16)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 (((L & 1) != 0) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((biasUp) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 4) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 4) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[170]++;
//                    bump_up:
//                        while(*--s == '9')
//                            if (s == buf) {
//                                k++;
//                                *s = '0';
//                                break;
//                            }
//                        ++*s++;
                        char lastCh;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[359]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[28]++;


                        while (true) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[28]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[29]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[30]++;
}
                            lastCh = buf.charAt(buf.length() - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[360]++;
                            buf.setLength(buf.length() - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[361]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[362]++;
int CodeCoverConditionCoverageHelper_C93;
                            if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((lastCh != '9') && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[172]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[363]++; break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[173]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[364]++;
int CodeCoverConditionCoverageHelper_C94;
                            if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((buf.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[174]++;
                                k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[365]++;
                                lastCh = '0';
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[366]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[367]++;
                                break;

                            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[175]++;}
                        }
                        buf.append((char)(lastCh + 1));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[368]++;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[171]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[369]++;
                    break;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[169]++;}
                d *= 10.0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[370]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[371]++;
int CodeCoverConditionCoverageHelper_C95;
                if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((d == 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[176]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[372]++;
                    break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[177]++;}
            }
            return k + 1;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[163]++;}

        m2 = b2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[373]++;
        m5 = b5;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[374]++;
        mhi = mlo = null;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[375]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[376]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((leftright) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[178]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[377]++;
int CodeCoverConditionCoverageHelper_C97;
            if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((mode < 2) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[180]++;
                i = (denorm) ? be[0] + (Bias + (P-1) - 1 + 1) : 1 + P - bbits[0];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[378]++;

                /* i is 1 plus the number of trailing zero bits in d's significand. Thus,
                   (2^m2 * 5^m5) / (2^(s2+i) * 5^s5) = (1/2 lsb of d)/10^k. */
            }
            else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[181]++;
                j = ilim - 1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[379]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[380]++;
int CodeCoverConditionCoverageHelper_C98;
                if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((m5 >= j) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[182]++;
                    m5 -= j;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[381]++;
}
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[183]++;
                    s5 += j -= m5;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[382]++;
                    b5 += j;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[383]++;
                    m5 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[384]++;
                }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[385]++;
                if ((i = ilim) < 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[184]++;
                    m2 -= i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[386]++;
                    i = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[387]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[185]++;}
                /* (2^m2 * 5^m5) / (2^(s2+i) * 5^s5) = (1/2 * 10^(1-ilim))/10^k. */
            }
            b2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[388]++;
            s2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[389]++;
            mhi = BigInteger.valueOf(1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[390]++;

            /* (mhi * 2^m2 * 5^m5) / (2^s2 * 5^s5) = one-half of last printed (when mode >= 2) or
               input (when mode < 2) significant digit, divided by 10^k. */
        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[179]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[391]++;
int CodeCoverConditionCoverageHelper_C100;
        /* We still have d/10^k = (b * 2^b2 * 5^b5) / (2^s2 * 5^s5).  Reduce common factors in
           b2, m2, and s2 without changing the equalities. */
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (8)) == 0 || true) &&
 ((m2 > 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((s2 > 0) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[186]++;
            i = (m2 < s2) ? m2 : s2;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[392]++;
            b2 -= i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[393]++;
            m2 -= i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[394]++;
            s2 -= i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[395]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[187]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[396]++;
int CodeCoverConditionCoverageHelper_C101;

        /* Fold b5 into b and m5 into mhi. */
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((b5 > 0) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[188]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[397]++;
int CodeCoverConditionCoverageHelper_C102;
            if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((leftright) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[190]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[398]++;
int CodeCoverConditionCoverageHelper_C103;
                if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((m5 > 0) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[192]++;
                    mhi = pow5mult(mhi, m5);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[399]++;
                    b1 = mhi.multiply(b);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[400]++;
                    b = b1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[401]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[193]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[402]++;
                if ((j = b5 - m5) != 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[194]++;
                    b = pow5mult(b, j);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[403]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[195]++;}

            }
            else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[191]++;
                b = pow5mult(b, b5);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[404]++;
}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[189]++;}
        /* Now we have d/10^k = (b * 2^b2) / (2^s2 * 5^s5) and
           (mhi * 2^m2) / (2^s2 * 5^s5) = one-half of last printed or input significant digit, divided by 10^k. */

        S = BigInteger.valueOf(1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[405]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[406]++;
int CodeCoverConditionCoverageHelper_C105;
        if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((s5 > 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[196]++;
            S = pow5mult(S, s5);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[407]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[197]++;}
        /* Now we have d/10^k = (b * 2^b2) / (S * 2^s2) and
           (mhi * 2^m2) / (S * 2^s2) = one-half of last printed or input significant digit, divided by 10^k. */

        /* Check for special case that d is a normalized power of 2. */
        spec_case = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[408]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[409]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((mode < 2) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[198]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[410]++;
int CodeCoverConditionCoverageHelper_C107;
            if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C107 |= (32)) == 0 || true) &&
 ((word1(d) == 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 (((word0(d) & Bndry_mask) == 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 (((word0(d) & (Exp_mask & Exp_mask << 1)) != 0) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 3) && false)
                ) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[200]++;
                /* The special case.  Here we want to be within a quarter of the last input
                   significant digit instead of one half of it when the decimal output string's value is less than d.  */
                b2 += Log2P;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[411]++;
                s2 += Log2P;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[412]++;
                spec_case = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[413]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[201]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[199]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[414]++;

        /* Arrange for convenient computation of quotients:
         * shift left if necessary so divisor has 4 leading 0 bits.
         *
         * Perhaps we should just compute leading 28 bits of S once
         * and for all and pass them and a shift to quorem, so it
         * can do shifts and ors to compute the numerator for q.
         */
        byte [] S_bytes = S.toByteArray();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[415]++;
        int S_hiWord = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[416]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[31]++;


int CodeCoverConditionCoverageHelper_C108;
        for (int idx = 0;(((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((idx < 4) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false); idx++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[31]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[32]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[33]++;
}
            S_hiWord = (S_hiWord << 8);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[417]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[418]++;
int CodeCoverConditionCoverageHelper_C109;
            if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((idx < S_bytes.length) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[202]++;
                S_hiWord |= (S_bytes[idx] & 0xFF);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[419]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[203]++;}
        }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[420]++;
        if ((i = (((s5 != 0) ? 32 - hi0bits(S_hiWord) : 1) + s2) & 0x1f) != 0) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[204]++;
            i = 32 - i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[421]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[205]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[422]++;
int CodeCoverConditionCoverageHelper_C111;
        /* i is the number of leading zero bits in the most significant word of S*2^s2. */
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((i > 4) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[206]++;
            i -= 4;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[423]++;
            b2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[424]++;
            m2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[425]++;
            s2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[426]++;

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[207]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[427]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((i < 4) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[208]++;
            i += 28;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[428]++;
            b2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[429]++;
            m2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[430]++;
            s2 += i;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[431]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[209]++;}
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[432]++;
int CodeCoverConditionCoverageHelper_C113;
        /* Now S*2^s2 has exactly four leading zero bits in its most significant word. */
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((b2 > 0) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[210]++;
            b = b.shiftLeft(b2);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[433]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[211]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[434]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((s2 > 0) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[212]++;
            S = S.shiftLeft(s2);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[435]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[213]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[436]++;
int CodeCoverConditionCoverageHelper_C115;
        /* Now we have d/10^k = b/S and
           (mhi * 2^m2) / S = maximum acceptable error, divided by 10^k. */
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((k_check) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[214]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[437]++;
int CodeCoverConditionCoverageHelper_C116;
            if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((b.compareTo(S) < 0) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[216]++;
                k--;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[438]++;
                b = b.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[439]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[440]++;
int CodeCoverConditionCoverageHelper_C117;  /* we botched the k estimate */
                if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((leftright) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[218]++;
                    mhi = mhi.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[441]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[219]++;}
                ilim = ilim1;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[442]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[217]++;}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[215]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[443]++;
int CodeCoverConditionCoverageHelper_C118;
        /* At this point 1 <= d/10^k = b/S < 10. */

        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((ilim <= 0) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((mode > 2) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[220]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[444]++;
            /* We're doing fixed-mode output and d is less than the minimum nonzero output in this mode.
               Output either zero or the minimum nonzero output depending on which is closer to d. */
            if ((ilim < 0) || ((i = b.compareTo(S = S.multiply(BigInteger.valueOf(5)))) < 0) || ((i == 0 && !biasUp))) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[222]++;
            /* Always emit at least one digit.  If the number appears to be zero
               using the current mode, then emit one '0' digit and set decpt to 1. */
            /*no_digits:
                k = -1 - ndigits;
                goto ret; */
                buf.setLength(0);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[445]++;
                buf.append('0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[446]++;        /* copy "0" to buffer */
                return 1;

//                goto no_digits;
            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[223]++;}
//        one_digit:
            buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[447]++;
            k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[448]++;
            return k + 1;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[221]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[449]++;
int CodeCoverConditionCoverageHelper_C120;
        if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((leftright) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[224]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[450]++;
int CodeCoverConditionCoverageHelper_C121;
            if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((m2 > 0) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[226]++;
                mhi = mhi.shiftLeft(m2);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[451]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[227]++;}

            /* Compute mlo -- check for special case
             * that d is a normalized power of 2.
             */

            mlo = mhi;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[452]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[453]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((spec_case) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[228]++;
                mhi = mlo;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[454]++;
                mhi = mhi.shiftLeft(Log2P);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[455]++;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[229]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[456]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[34]++;


            /* mlo/S = maximum acceptable error, divided by 10^k, if the output is less than d. */
            /* mhi/S = maximum acceptable error, divided by 10^k, if the output is greater than d. */

            for(i = 1;;i++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[34]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[35]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[36]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[457]++;
                BigInteger[] divResult = b.divideAndRemainder(S);
                b = divResult[1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[458]++;
                dig = (char)(divResult[0].intValue() + '0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[459]++;
                /* Do we yet have the shortest decimal string
                 * that will round to d?
                 */
                j = b.compareTo(mlo);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[460]++;
                /* j is b/S compared with mlo/S. */
                delta = S.subtract(mhi);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[461]++;
                j1 = (delta.signum() <= 0) ? 1 : b.compareTo(delta);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[462]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[463]++;
int CodeCoverConditionCoverageHelper_C124;
                /* j1 is b/S compared with 1 - mhi/S. */
                if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C124 |= (32)) == 0 || true) &&
 ((j1 == 0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C124 |= (8)) == 0 || true) &&
 ((mode == 0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 (((word1(d) & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[230]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[464]++;
int CodeCoverConditionCoverageHelper_C125;
                    if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((dig == '9') && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[232]++;
                        buf.append('9');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[465]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[466]++;
int CodeCoverConditionCoverageHelper_C126;
                        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((roundOff(buf)) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[234]++;
                            k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[467]++;
                            buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[468]++;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[235]++;}
                        return k + 1;

//                        goto round_9_up;
                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[233]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[469]++;
int CodeCoverConditionCoverageHelper_C127;
                    if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((j > 0) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[236]++;
                        dig++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[470]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[237]++;}
                    buf.append(dig);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[471]++;
                    return k + 1;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[231]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[472]++;
int CodeCoverConditionCoverageHelper_C128;
                if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C128 |= (128)) == 0 || true) &&
 ((j < 0) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (64)) == 0 || true)))
) || ((
(((CodeCoverConditionCoverageHelper_C128 |= (32)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C128 |= (8)) == 0 || true) &&
 ((mode == 0) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 (((word1(d) & 1) == 0) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 4) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 4) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[238]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[473]++;
int CodeCoverConditionCoverageHelper_C129;
                    if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((j1 > 0) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[240]++;
                        /* Either dig or dig+1 would work here as the least significant decimal digit.
                           Use whichever would produce a decimal value closer to d. */
                        b = b.shiftLeft(1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[474]++;
                        j1 = b.compareTo(S);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[475]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[476]++;
int CodeCoverConditionCoverageHelper_C130;
                        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (((
(((CodeCoverConditionCoverageHelper_C130 |= (512)) == 0 || true) &&
 ((j1 > 0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (256)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C130 |= (128)) == 0 || true) &&
 ((j1 == 0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (64)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C130 |= (32)) == 0 || true) &&
 (((dig & 1) == 1) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (16)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C130 |= (8)) == 0 || true) &&
 ((biasUp) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (4)) == 0 || true)))
))) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((dig++ == '9') && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 5) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 5) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[242]++;
                                buf.append('9');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[477]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[478]++;
int CodeCoverConditionCoverageHelper_C131;
                                if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((roundOff(buf)) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[244]++;
                                    k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[479]++;
                                    buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[480]++;

                                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[245]++;}
                                return k + 1;

//                                goto round_9_up;
                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[243]++;}

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[241]++;}
                    buf.append(dig);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[481]++;
                    return k + 1;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[239]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[482]++;
int CodeCoverConditionCoverageHelper_C132;
                if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((j1 > 0) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[246]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[483]++;
int CodeCoverConditionCoverageHelper_C133;
                    if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((dig == '9') && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[248]++; /* possible if i == 1 */
//                    round_9_up:
//                        *s++ = '9';
//                        goto roundoff;
                        buf.append('9');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[484]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[485]++;
int CodeCoverConditionCoverageHelper_C134;
                        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((roundOff(buf)) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[250]++;
                            k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[486]++;
                            buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[487]++;

                        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[251]++;}
                        return k + 1;

                    } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[249]++;}
                    buf.append((char)(dig + 1));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[488]++;
                    return k + 1;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[247]++;}
                buf.append(dig);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[489]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[490]++;
int CodeCoverConditionCoverageHelper_C135;
                if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((i == ilim) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[252]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[491]++;
                    break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[253]++;}
                b = b.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[492]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[493]++;
int CodeCoverConditionCoverageHelper_C136;
                if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((mlo == mhi) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[254]++;
                    mlo = mhi = mhi.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[494]++;
}
                else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[255]++;
                    mlo = mlo.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[495]++;
                    mhi = mhi.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[496]++;
                }
            }

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[225]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[497]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[37]++;


            for(i = 1;; i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[37]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[38]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[39]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[498]++;
//                (char)(dig = quorem(b,S) + '0');
                BigInteger[] divResult = b.divideAndRemainder(S);
                b = divResult[1];
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[499]++;
                dig = (char)(divResult[0].intValue() + '0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[500]++;
                buf.append(dig);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[501]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[502]++;
int CodeCoverConditionCoverageHelper_C138;
                if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((i >= ilim) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[256]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[503]++;
                    break;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[257]++;}
                b = b.multiply(BigInteger.valueOf(10));
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[504]++;
            }
}

        /* Round off last digit */

        b = b.shiftLeft(1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[505]++;
        j = b.compareTo(S);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[506]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[507]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C139 |= (128)) == 0 || true) &&
 ((j > 0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (64)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C139 |= (32)) == 0 || true) &&
 ((j == 0) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (16)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C139 |= (8)) == 0 || true) &&
 (((dig & 1) == 1) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((biasUp) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 4) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 4) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[258]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[508]++;
int CodeCoverConditionCoverageHelper_C140;
//        roundoff:
//            while(*--s == '9')
//                if (s == buf) {
//                    k++;
//                    *s++ = '1';
//                    goto ret;
//                }
//            ++*s++;
            if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((roundOff(buf)) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[260]++;
                k++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[509]++;
                buf.append('1');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[510]++;
                return k + 1;

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[261]++;}

        }
        else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[259]++;
            stripTrailingZeroes(buf);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[511]++;
//            while(*--s == '0') ;
//            s++;
        }
//      ret:
//        Bfree(S);
//        if (mhi) {
//            if (mlo && mlo != mhi)
//                Bfree(mlo);
//            Bfree(mhi);
//        }
//      ret1:
//        Bfree(b);
//        JS_ASSERT(s < buf + bufsize);
        return k + 1;
    }

    private static void
    stripTrailingZeroes(StringBuilder buf)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[512]++;
//      while(*--s == '0') ;
//      s++;
        int bl = buf.length();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[513]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[40]++;


int CodeCoverConditionCoverageHelper_C141;
        while((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((bl-->0) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((buf.charAt(bl) == '0') && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[40]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[41]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[42]++;
}
          // empty
        }
        buf.setLength(bl + 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[514]++;
    }

    /* Mapping of JSDToStrMode -> JS_dtoa mode */
    private static final int dtoaModes[] = {
        0,   /* DTOSTR_STANDARD */
        0,   /* DTOSTR_STANDARD_EXPONENTIAL, */
        3,   /* DTOSTR_FIXED, */
        2,   /* DTOSTR_EXPONENTIAL, */
        2};
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[515]++;
  }  /* DTOSTR_PRECISION */

    static void
    JS_dtostr(StringBuilder buffer, int mode, int precision, double d)
    {
        int decPt;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[516]++;                                    /* Position of decimal point relative to first digit returned by JS_dtoa */
        boolean[] sign = new boolean[1];            /* true if the sign bit was set in d */
        int nDigits;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[517]++;
int CodeCoverConditionCoverageHelper_C142;                                /* Number of significand digits returned by JS_dtoa */

//        JS_ASSERT(bufferSize >= (size_t)(mode <= DTOSTR_STANDARD_EXPONENTIAL ? DTOSTR_STANDARD_BUFFER_SIZE :
//                DTOSTR_VARIABLE_BUFFER_SIZE(precision)));

        if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (32)) == 0 || true) &&
 ((mode == DTOSTR_FIXED) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (16)) == 0 || true)))
 && (
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 ((d >= 1e21) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((d <= -1e21) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 3) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 3) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[262]++;
            mode = DTOSTR_STANDARD;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[518]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[263]++;} /* Change mode here rather than below because the buffer may not be large enough to hold a large integer. */

        decPt = JS_dtoa(d, dtoaModes[mode], mode >= DTOSTR_FIXED, precision, sign, buffer);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[519]++;
        nDigits = buffer.length();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[520]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[521]++;
int CodeCoverConditionCoverageHelper_C143;

        /* If Infinity, -Infinity, or NaN, return the string regardless of the mode. */
        if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((decPt != 9999) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[264]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[522]++;
            boolean exponentialNotation = false;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[523]++;
            int minNDigits = 0;         /* Minimum number of significand digits required by mode and precision */
            int p;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[524]++;

            switch (mode) {
                case DTOSTR_STANDARD:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[266]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[525]++;
int CodeCoverConditionCoverageHelper_C144;
                    if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 ((decPt < -5) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((decPt > 21) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[267]++;
                        exponentialNotation = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[526]++;
}
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[268]++;
                        minNDigits = decPt;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[527]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[528]++;
                    break;

                case DTOSTR_FIXED:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[269]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[529]++;
int CodeCoverConditionCoverageHelper_C145;
                    if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((precision >= 0) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[270]++;
                        minNDigits = decPt + precision;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[530]++;
}
                    else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[271]++;
                        minNDigits = decPt;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[531]++;
}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[532]++;
                    break;

                case DTOSTR_EXPONENTIAL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[272]++;
//                    JS_ASSERT(precision > 0);
                    minNDigits = precision;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[533]++;
                    /* Fall through */
                case DTOSTR_STANDARD_EXPONENTIAL:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[273]++;
                    exponentialNotation = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[534]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[535]++;
                    break;

                case DTOSTR_PRECISION:
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[274]++;
//                    JS_ASSERT(precision > 0);
                    minNDigits = precision;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[536]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[537]++;
int CodeCoverConditionCoverageHelper_C146;
                    if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (8)) == 0 || true) &&
 ((decPt < -5) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((decPt > precision) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[275]++;
                        exponentialNotation = true;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[538]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[276]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[539]++;
                    break; default : CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[277]++;
            }
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[540]++;
int CodeCoverConditionCoverageHelper_C147;

            /* If the number has fewer than minNDigits, pad it with zeros at the end */
            if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((nDigits < minNDigits) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[278]++;
                p = minNDigits;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[541]++;
                nDigits = minNDigits;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[542]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[543]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[43]++;


int CodeCoverConditionCoverageHelper_C148;
                do {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[43]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[44]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[45]++;
}
                    buffer.append('0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[544]++;
                } while ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((buffer.length() != p) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false));

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[279]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[545]++;
int CodeCoverConditionCoverageHelper_C149;

            if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((exponentialNotation) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[280]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[546]++;
int CodeCoverConditionCoverageHelper_C150;
                /* Insert a decimal point if more than one significand digit */
                if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((nDigits != 1) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[282]++;
                    buffer.insert(1, '.');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[547]++;

                } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[283]++;}
                buffer.append('e');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[548]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[549]++;
int CodeCoverConditionCoverageHelper_C151;
                if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 (((decPt - 1) >= 0) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[284]++;
                    buffer.append('+');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[550]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[285]++;}
                buffer.append(decPt - 1);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[551]++;

//                JS_snprintf(numEnd, bufferSize - (numEnd - buffer), "e%+d", decPt-1);
            } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[281]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[552]++;
int CodeCoverConditionCoverageHelper_C152; if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((decPt != nDigits) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[286]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[553]++;
int CodeCoverConditionCoverageHelper_C153;
                /* Some kind of a fraction in fixed notation */
//                JS_ASSERT(decPt <= nDigits);
                if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((decPt > 0) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[288]++;
                    /* dd...dd . dd...dd */
                    buffer.insert(decPt, '.');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[554]++;

                } else {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[289]++;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[555]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[46]++;


int CodeCoverConditionCoverageHelper_C154;
                    /* 0 . 00...00dd...dd */
                    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C154 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C154 |= (2)) == 0 || true) &&
 ((i < 1 - decPt) && 
  ((CodeCoverConditionCoverageHelper_C154 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[154].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C154, 1) && false); i++) { 
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[46]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[47]--;
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.loops[48]++;
}
                        buffer.insert(0, '0');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[556]++;
  }
                    buffer.insert(1, '.');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[557]++;
                }

            } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[287]++;}
}

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[265]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[558]++;
int CodeCoverConditionCoverageHelper_C155;

        /* If negative and neither -0.0 nor NaN, output a leading '-'. */
        if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2048)) == 0 || true) &&
 ((sign[0]) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1024)) == 0 || true)))
 && !(
(((CodeCoverConditionCoverageHelper_C155 |= (512)) == 0 || true) &&
 ((word0(d) == Sign_bit) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (256)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C155 |= (128)) == 0 || true) &&
 ((word1(d) == 0) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (64)) == 0 || true)))
) && !(
(((CodeCoverConditionCoverageHelper_C155 |= (32)) == 0 || true) &&
 (((word0(d) & Exp_mask) == Exp_mask) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (16)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C155 |= (8)) == 0 || true) &&
 ((word1(d) != 0) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 (((word0(d) & Frac_mask) != 0) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
))))) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 6) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 6) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[290]++;
            buffer.insert(0, '-');
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.statements[559]++;

        } else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d.branches[291]++;}
    }

}

class CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d ());
  }
    public static long[] statements = new long[560];
    public static long[] branches = new long[292];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[156];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-DToA.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,3,1,1,2,1,3,1,1,1,1,1,1,1,1,0,2,2,1,1,1,2,1,1,1,3,1,1,1,1,0,1,1,3,1,1,1,1,1,1,0,1,1,0,1,1,1,0,1,1,0,1,1,1,1,2,2,3,0,1,3,0,1,1,1,1,1,1,0,2,1,1,1,0,1,1,3,1,1,0,1,1,1,1,1,1,1,2,0,1,1,1,0,3,1,1,1,3,1,3,1,1,1,1,1,1,0,1,3,1,2,3,1,2,1,2,1,1,1,1,1,1,1,1,3};
    for (int i = 1; i <= 155; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[49];

  public CodeCoverCoverageCounter$1cnp1i09zf4s7wx3afnb5me53tiy5d () {
    super("org.mozilla.javascript.RHINO-SRC-DToA.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 559; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 291; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 155; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 48; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-DToA.java");
      for (int i = 1; i <= 559; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 291; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 155; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 16; i++) {
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


