/*
 * Copyright 2008 The Closure Compiler Authors.
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

package com.google.javascript.jscomp;

import com.google.common.base.CaseFormat;
import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.javascript.jscomp.JsMessage.IdGenerator;
import com.google.javascript.jscomp.JsMessage.PlaceholderReference;

import java.util.List;

/**
 * An {@link IdGenerator} designed to play nicely with Google's Translation
 * systems. Each message is scoped to a project id, so that it does
 * not conflict with other messages at Google.
 * <p>
 * Just as reminder what key type used in different formats:
 * <ol>
 * <li>XMB - id. We export using this format.
 * <li>XTB - id. Internal, result of translation.
 * <li>XLB - name. External, use it if we need to share translation with third
 *     part.
 * <li>PROPERTIES - name.
 * </ol>
 *
 * @see <a href="http://cldr.unicode.org/development/development-process/design-proposals/xmb">xmb</a>
 */
public class GoogleJsMessageIdGenerator implements IdGenerator {
  static {
    CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.ping();
  }


  private final String projectId;

  /**
   * Creates an instance.
   *
   * @param projectId A TC project name (e.g. "MyProject")
   */
  public GoogleJsMessageIdGenerator(String projectId) {
    this.projectId = projectId;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[1]++;
  }

  @Override
  public String generateId(String meaning, List<CharSequence> messageParts) {
    Preconditions.checkState(meaning != null);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[2]++;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[3]++;

    StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[1]++;


    for (CharSequence part : messageParts) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[1]--;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[2]--;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[3]++;
}
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((part instanceof PlaceholderReference) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[1]++;
        sb.append(CaseFormat.LOWER_CAMEL.to(
            CaseFormat.UPPER_UNDERSCORE,
            ((PlaceholderReference) part).getName()));
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[6]++;

      } else {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[2]++;
        sb.append(part);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[7]++;
      }
    }
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[8]++;
    String tcValue = sb.toString();
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[9]++;

    String projectScopedMeaning =
        (projectId != null ? (projectId + ": ") : "") + meaning;
    return String.valueOf(
        MessageId.GenerateId(tcValue, projectScopedMeaning));
  }


  /**
   * 64-bit fingerprint support.
   *
   * Forked from the guava-internal library.
   */
  private static final class FP {
    private FP() {}

    /** Generate fingerprint of "byte[start,limit-1]". */
    private static long fingerprint(byte[] str, int start, int limit) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[10]++;
      int hi = hash32(str, start, limit, 0);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[11]++;
      int lo = hash32(str, start, limit, 102072);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((hi == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((lo == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((lo == 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) || true)) || (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 3) && false)) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[3]++;
        // Turn 0/1 into another fingerprint
        hi ^= 0x130f9bef;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[13]++;
        lo ^= 0x94a0a928;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[14]++;

      } else {
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[4]++;}
      return (((long) hi) << 32) | (lo & 0xffffffffl);
    }

    /**
     * Generate fingerprint of "str". Equivalent to UTF-encoding "str" into
     * bytes and then fingerprinting those bytes.
     */
    private static long fingerprint(String str) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[15]++;
      byte[] tmp = str.getBytes(Charsets.UTF_8);
      return FP.fingerprint(tmp, 0, tmp.length);
    }

    @SuppressWarnings("fallthrough")
    private static int hash32(byte[] str, int start, int limit, int c) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[16]++;
      int a = 0x9e3779b9;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[17]++;
      int b = 0x9e3779b9;
      int i;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
      for (i = start;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i + 12 <= limit) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); i += 12) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[4]--;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[5]--;
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.loops[6]++;
}
        a += (((str[i + 0] & 0xff) << 0)
            | ((str[i + 1] & 0xff) << 8)
            | ((str[i + 2] & 0xff) << 16)
            | ((str[i + 3] & 0xff) << 24));
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[19]++;
        b += (((str[i + 4] & 0xff) << 0)
            | ((str[i + 5] & 0xff) << 8)
            | ((str[i + 6] & 0xff) << 16)
            | ((str[i + 7] & 0xff) << 24));
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[20]++;
        c += (((str[i + 8] & 0xff) << 0)
            | ((str[i + 9] & 0xff) << 8) | ((str[i + 10] & 0xff) << 16)
            | ((str[i + 11] & 0xff) << 24));
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[21]++;

        // Mix
        a -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[22]++;
        a -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[23]++;
        a ^= (c >>> 13);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[24]++;
        b -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[25]++;
        b -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[26]++;
        b ^= (a << 8);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[27]++;
        c -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[28]++;
        c -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[29]++;
        c ^= (b >>> 13);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[30]++;
        a -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[31]++;
        a -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[32]++;
        a ^= (c >>> 12);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[33]++;
        b -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[34]++;
        b -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[35]++;
        b ^= (a << 16);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[36]++;
        c -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[37]++;
        c -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[38]++;
        c ^= (b >>> 5);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[39]++;
        a -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[40]++;
        a -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[41]++;
        a ^= (c >>> 3);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[42]++;
        b -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[43]++;
        b -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[44]++;
        b ^= (a << 10);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[45]++;
        c -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[46]++;
        c -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[47]++;
        c ^= (b >>> 15);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[48]++;
      }

      c += limit - start;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[49]++;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[50]++;
      switch (limit - i) { // deal with rest. Cases fall through
        case 11:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[5]++;
          c += (str[i + 10] & 0xff) << 24;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[51]++;
        case 10:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[6]++;
          c += (str[i + 9] & 0xff) << 16;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[52]++;
        case 9:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[7]++;
          c += (str[i + 8] & 0xff) << 8;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[53]++;
          // the first byte of c is reserved for the length
        case 8:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[8]++;
          b += (str[i + 7] & 0xff) << 24;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[54]++;
        case 7:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[9]++;
          b += (str[i + 6] & 0xff) << 16;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[55]++;
        case 6:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[10]++;
          b += (str[i + 5] & 0xff) << 8;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[56]++;
        case 5:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[11]++;
          b += (str[i + 4] & 0xff);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[57]++;
        case 4:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[12]++;
          a += (str[i + 3] & 0xff) << 24;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[58]++;
        case 3:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[13]++;
          a += (str[i + 2] & 0xff) << 16;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[59]++;
        case 2:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[14]++;
          a += (str[i + 1] & 0xff) << 8;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[60]++;
        case 1:
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[15]++;
          a += (str[i + 0] & 0xff);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[61]++; default : CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[16]++;
          // case 0 : nothing left to add
      }

      // Mix
      a -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[62]++;
      a -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[63]++;
      a ^= (c >>> 13);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[64]++;
      b -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[65]++;
      b -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[66]++;
      b ^= (a << 8);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[67]++;
      c -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[68]++;
      c -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[69]++;
      c ^= (b >>> 13);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[70]++;
      a -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[71]++;
      a -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[72]++;
      a ^= (c >>> 12);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[73]++;
      b -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[74]++;
      b -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[75]++;
      b ^= (a << 16);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[76]++;
      c -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[77]++;
      c -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[78]++;
      c ^= (b >>> 5);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[79]++;
      a -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[80]++;
      a -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[81]++;
      a ^= (c >>> 3);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[82]++;
      b -= c;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[83]++;
      b -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[84]++;
      b ^= (a << 10);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[85]++;
      c -= a;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[86]++;
      c -= b;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[87]++;
      c ^= (b >>> 15);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[88]++;
      return c;
    }
  }

  /**
   * Generates fingerprint for an English message using the FP package.
   * This supersedes the message id generation using C fingerprint
   * functions and JNI.  This is slower than the C implementation (
   * we're talking about microseconds here) but it avoids using JNI and
   * shared libraries.<p>
   *
   * Forked from the i18n library.
   */
  private static class MessageId {
    private final static long GenerateId(String message, String meaning) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[89]++;
      long fp = FP.fingerprint(message);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[90]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((null != meaning) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((meaning.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[17]++;
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[91]++;
        // combine the fingerprints of message and meaning
        long fp2 = FP.fingerprint(meaning);
        fp = fp2 + (fp << 1) + (fp < 0 ? 1 : 0);
CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.statements[92]++;

      } else {
  CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9.branches[18]++;}
      // To avoid negative ids we strip the high-order bit
      return fp & 0x7fffffffffffffffL;
    }
  }
}

class CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9 ());
  }
    public static long[] statements = new long[93];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.GoogleJsMessageIdGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,1,2};
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

  public CodeCoverCoverageCounter$90himiezb7o99hm0r9zw5erxo0j7zyv3ec00rsgucv4aezq9 () {
    super("com.google.javascript.jscomp.GoogleJsMessageIdGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 92; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.GoogleJsMessageIdGenerator.java");
      for (int i = 1; i <= 92; i++) {
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

