/*
 * Copyright 2010 The Closure Compiler Authors.
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

/**
 * Guava code that will eventually be open-sourced properly. Package-private
 * until they're able to do that. A lot of these methods are discouraged
 * anyways.
 *
 * @author nicksantos@google.com (Nick Santos)
 */
class Strings {
  static {
    CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.ping();
  }

  private Strings() {} // All static

  /**
   * If this given string is of length {@code maxLength} or less, it will
   * be returned as-is.
   * Otherwise, it will be truncated to {@code maxLength}, regardless of whether
   * there are any space characters in the String. If an ellipsis is requested
   * to be appended to the truncated String, the String will be truncated so
   * that the ellipsis will also fit within maxLength.
   * If no truncation was necessary, no ellipsis will be added.
   * @param source the String to truncate if necessary
   * @param maxLength the maximum number of characters to keep
   * @param addEllipsis if true, and if the String had to be truncated,
   *     add "..." to the end of the String before returning. Additionally,
   *     the ellipsis will only be added if maxLength is greater than 3.
   * @return the original string if it's length is less than or equal to
   *     maxLength, otherwise a truncated string as mentioned above
   */
  static String truncateAtMaxLength(String source, int maxLength,
      boolean addEllipsis) {
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.statements[1]++;
int CodeCoverConditionCoverageHelper_C1;

    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((source.length() <= maxLength) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[1]++;
      return source;

    } else {
  CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[2]++;}
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.statements[2]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((addEllipsis) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((maxLength > 3) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[3]++;
      return unicodePreservingSubstring(source, 0, maxLength - 3) + "...";

    } else {
  CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[4]++;}
    return unicodePreservingSubstring(source, 0, maxLength);
  }

  /**
   * Normalizes {@code index} such that it respects Unicode character
   * boundaries in {@code str}.
   *
   * <p>If {@code index} is the low surrogate of a Unicode character,
   * the method returns {@code index - 1}. Otherwise, {@code index} is
   * returned.
   *
   * <p>In the case in which {@code index} falls in an invalid surrogate pair
   * (e.g. consecutive low surrogates, consecutive high surrogates), or if
   * if it is not a valid index into {@code str}, the original value of
   * {@code index} is returned.
   *
   * @param str the String
   * @param index the index to be normalized
   * @return a normalized index that does not split a Unicode character
   */
  private static int unicodePreservingIndex(String str, int index) {
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.statements[3]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((index > 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((index < str.length()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[5]++;
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.statements[4]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((Character.isHighSurrogate(str.charAt(index - 1))) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((Character.isLowSurrogate(str.charAt(index))) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[7]++;
        return index - 1;

      } else {
  CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1.branches[6]++;}
    return index;
  }

  /**
   * Returns a substring of {@code str} that respects Unicode character
   * boundaries.
   *
   * <p>The string will never be split between a [high, low] surrogate pair,
   * as defined by {@link Character#isHighSurrogate} and
   * {@link Character#isLowSurrogate}.
   *
   * <p>If {@code begin} or {@code end} are the low surrogate of a Unicode
   * character, it will be offset by -1.
   *
   * <p>This behavior guarantees that
   * {@code str.equals(StringUtil.unicodePreservingSubstring(str, 0, n) +
   *     StringUtil.unicodePreservingSubstring(str, n, str.length())) } is
   * true for all {@code n}.
   * </pre>
   *
   * <p>This means that unlike {@link String#substring(int, int)}, the length of
   * the returned substring may not necessarily be equivalent to
   * {@code end - begin}.
   *
   * @param str the original String
   * @param begin the beginning index, inclusive
   * @param end the ending index, exclusive
   * @return the specified substring, possibly adjusted in order to not
   *   split Unicode surrogate pairs
   * @throws IndexOutOfBoundsException if the {@code begin} is negative,
   *   or {@code end} is larger than the length of {@code str}, or
   *   {@code begin} is larger than {@code end}
   */
  private static String unicodePreservingSubstring(
      String str, int begin, int end) {
    return str.substring(unicodePreservingIndex(str, begin),
        unicodePreservingIndex(str, end));
  }
}

class CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1 ());
  }
    public static long[] statements = new long[5];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.Strings.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,2,2};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$2i59udjf0yu0wj8pnc1 () {
    super("com.google.javascript.jscomp.Strings.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 4; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.Strings.java");
      for (int i = 1; i <= 4; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
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
      for (int i = 1; i <= 0; i++) {
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

