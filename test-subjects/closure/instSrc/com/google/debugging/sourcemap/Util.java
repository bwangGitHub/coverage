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
package com.google.debugging.sourcemap;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;

/**
 * @author johnlenz@google.com (John Lenz)
 */
class Util {
  static {
    CodeCoverCoverageCounter$98ogfj33cxidlt.ping();
  }


  private static final char[] HEX_CHARS
      = { '0', '1', '2', '3', '4', '5', '6', '7',
          '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  static {
    CodeCoverCoverageCounter$98ogfj33cxidlt.statements[1]++;
  }

  /**
   * Escapes the given string to a double quoted (") JavaScript/JSON string
   */
  static String escapeString(String s) {
    return escapeString(s, '"',  "\\\"", "\'", "\\\\", null);
  }

  /** Helper to escape JavaScript string as well as regular expression */
  static String escapeString(String s, char quote,
                          String doublequoteEscape,
                          String singlequoteEscape,
                          String backslashEscape,
                          CharsetEncoder outputCharsetEncoder) {
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[2]++;
    StringBuilder sb = new StringBuilder(s.length() + 2);
    sb.append(quote);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[3]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[4]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$98ogfj33cxidlt.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$98ogfj33cxidlt.loops[1]--;
  CodeCoverCoverageCounter$98ogfj33cxidlt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$98ogfj33cxidlt.loops[2]--;
  CodeCoverCoverageCounter$98ogfj33cxidlt.loops[3]++;
}
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[5]++;
      char c = s.charAt(i);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[6]++;
      switch (c) {
        case '\n':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[1]++; sb.append("\\n");
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[7]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[8]++; break;
        case '\r':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[2]++; sb.append("\\r");
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[9]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[10]++; break;
        case '\t':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[3]++; sb.append("\\t");
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[11]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[12]++; break;
        case '\\':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[4]++; sb.append(backslashEscape);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[13]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[14]++; break;
        case '\"':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[5]++; sb.append(doublequoteEscape);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[15]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[16]++; break;
        case '\'':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[6]++; sb.append(singlequoteEscape);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[17]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[18]++; break;
        case '>':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[7]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;                       // Break --> into --\> or ]]> into ]]\>
          if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (512)) == 0 || true) &&
 ((i >= 2) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (256)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C2 |= (128)) == 0 || true) &&
 ((s.charAt(i - 1) == '-') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (32)) == 0 || true) &&
 ((s.charAt(i - 2) == '-') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((s.charAt(i - 1) == ']') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((s.charAt(i - 2) == ']') && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 5) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 5) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[8]++;
            sb.append("\\>");
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[20]++;

          } else {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[9]++;
            sb.append(c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[21]++;
          }
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[22]++;
          break;
        case '<':
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[10]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[23]++;
          // Break </script into <\/script
          final String END_SCRIPT = "/script";
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[24]++;

          // Break <!-- into <\!--
          final String START_COMMENT = "!--";
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[25]++;
int CodeCoverConditionCoverageHelper_C3;

          if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((s.regionMatches(true, i + 1, END_SCRIPT, 0,
                              END_SCRIPT.length())) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[11]++;
            sb.append("<\\");
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[26]++;

          } else {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[12]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[27]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((s.regionMatches(false, i + 1, START_COMMENT, 0,
                                     START_COMMENT.length())) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[13]++;
            sb.append("<\\");
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[28]++;

          } else {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[14]++;
            sb.append(c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[29]++;
          }
}
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[30]++;
          break;
        default:
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[15]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[31]++;
int CodeCoverConditionCoverageHelper_C5;
          // If we're given an outputCharsetEncoder, then check if the
          //  character can be represented in this character set.
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((outputCharsetEncoder != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[16]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[32]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((outputCharsetEncoder.canEncode(c)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[18]++;
              sb.append(c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[33]++;

            } else {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[19]++;
              // Unicode-escape the character.
              appendCharAsHex(sb, c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[34]++;
            }

          } else {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[17]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[35]++;
int CodeCoverConditionCoverageHelper_C7;
            // No charsetEncoder provided - pass straight Latin characters
            // through, and escape the rest.  Doing the explicit character
            // check is measurably faster than using the CharsetEncoder.
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((c > 0x1f) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((c <= 0x7f) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[20]++;
              sb.append(c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[36]++;

            } else {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[21]++;
              // Other characters can be misinterpreted by some JS parsers,
              // or perhaps mangled by proxies along the way,
              // so we play it safe and Unicode escape them.
              appendCharAsHex(sb, c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[37]++;
            }
          }
      }
    }
    sb.append(quote);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[38]++;
    return sb.toString();
  }

  /**
   * @see #appendHexJavaScriptRepresentation(Appendable, int)
   */
  @SuppressWarnings("cast")
  private static void appendCharAsHex(
      StringBuilder sb, char c) {
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[39]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      appendHexJavaScriptRepresentation(sb, (int)c);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[40]++;
    } catch (IOException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[23]++;
      // StringBuilder does not throw IOException.
      throw new RuntimeException(ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$98ogfj33cxidlt.branches[22]++;
}
  }
  }

  /**
   * Returns a JavaScript representation of the character in a hex escaped
   * format.
   * @param out The buffer to which the hex representation should be appended.
   * @param codePoint The code point to append.
   */
  private static void appendHexJavaScriptRepresentation(
      Appendable out, int codePoint)
      throws IOException {
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[41]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((Character.isSupplementaryCodePoint(codePoint)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$98ogfj33cxidlt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$98ogfj33cxidlt.branches[24]++;
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[42]++;
      // Handle supplementary Unicode values which are not representable in
      // JavaScript.  We deal with these by escaping them as two 4B sequences
      // so that they will round-trip properly when sent from Java to JavaScript
      // and back.
      char[] surrogates = Character.toChars(codePoint);
      appendHexJavaScriptRepresentation(out, surrogates[0]);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[43]++;
      appendHexJavaScriptRepresentation(out, surrogates[1]);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[44]++;
      return;

    } else {
  CodeCoverCoverageCounter$98ogfj33cxidlt.branches[25]++;}
    out.append("\\u")
        .append(HEX_CHARS[(codePoint >>> 12) & 0xf])
        .append(HEX_CHARS[(codePoint >>> 8) & 0xf])
        .append(HEX_CHARS[(codePoint >>> 4) & 0xf])
        .append(HEX_CHARS[codePoint & 0xf]);
CodeCoverCoverageCounter$98ogfj33cxidlt.statements[45]++;
  }

}

class CodeCoverCoverageCounter$98ogfj33cxidlt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$98ogfj33cxidlt ());
  }
    public static long[] statements = new long[46];
    public static long[] branches = new long[26];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "com.google.debugging.sourcemap.Util.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,3,1,1,1,1,2,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$98ogfj33cxidlt () {
    super("com.google.debugging.sourcemap.Util.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 45; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 25; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.debugging.sourcemap.Util.java");
      for (int i = 1; i <= 45; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 25; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

