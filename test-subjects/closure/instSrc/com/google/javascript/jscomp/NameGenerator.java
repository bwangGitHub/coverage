/*
 * Copyright 2005 The Closure Compiler Authors.
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

import com.google.javascript.rhino.TokenStream;
import javax.annotation.Nullable;
import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;

import java.util.*;

/**
 * A simple class for generating unique JavaScript variable/property names.
 *
 * <p>This class is not thread safe.
 *
 */
final class NameGenerator {
  static {
    CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.ping();
  }

  /** Generate short name with this first character */
  static final char[] FIRST_CHAR =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ$".toCharArray();
  static {
    CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[1]++;
  }

  /** These appear after after the first character */
  static final char[] NONFIRST_CHAR =
    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_0123456789$"
        .toCharArray();
  static {
    CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[2]++;
  }

  private final Set<String> reservedNames;
  private final String prefix;
  private int nameCount;

  private final char[] firstChars;
  private final char[] nonFirstChars;

  /**
   * Creates a NameGenerator.
   *
   * @param reservedNames set of names that are reserved; generated names will
   *   not include these names. This set is referenced rather than copied,
   *   so changes to the set will be reflected in how names are generated.
   * @param prefix all generated names begin with this prefix.
   * @param reservedCharacters If specified these characters won't be used in
   *   generated names
   */
  NameGenerator(Set<String> reservedNames, String prefix,
      @Nullable char[] reservedCharacters) {
    this.reservedNames = reservedNames;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[3]++;
    this.prefix = prefix;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[4]++;

    // build the character arrays to use
    this.firstChars = reserveCharacters(FIRST_CHAR, reservedCharacters);
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[5]++;
    this.nonFirstChars = reserveCharacters(NONFIRST_CHAR, reservedCharacters);
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[6]++;

    checkPrefix(prefix);
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[7]++;
  }

  /**
   * Provides the array of available characters based on the specified arrays.
   * @param chars The list of characters that are legal
   * @param reservedCharacters The characters that should not be used
   * @return An array of characters to use. Will return the chars array if
   *    reservedCharacters is null or empty, otherwise creates a new array.
   */
  static char[] reserveCharacters(char[] chars, char[] reservedCharacters) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((reservedCharacters == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((reservedCharacters.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[1]++;
      return chars;

    } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[2]++;}
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[9]++;
    Set<Character> charSet = Sets.newLinkedHashSet(Chars.asList(chars));
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[1]++;


    for (char reservedCharacter : reservedCharacters) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[1]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[2]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[3]++;
}
      charSet.remove(reservedCharacter);
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[11]++;
    }
    return Chars.toArray(charSet);
  }

  /** Validates a name prefix. */
  private void checkPrefix(String prefix) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((prefix.length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[3]++;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
      // Make sure that prefix starts with a legal character.
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((contains(firstChars, prefix.charAt(0))) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[5]++;
        throw new IllegalArgumentException("prefix must start with one of: " +
                                           Arrays.toString(firstChars));

      } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[6]++;}
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[14]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
      for (int pos = 1;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((pos < prefix.length()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); ++pos) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[4]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[5]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[6]++;
}
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((contains(nonFirstChars, prefix.charAt(pos))) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[7]++;
          throw new IllegalArgumentException("prefix has invalid characters, " +
                                             "must be one of: " +
                                             Arrays.toString(nonFirstChars));

        } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[8]++;}
      }

    } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[4]++;}
  }

  private boolean contains(char[] arr, char c) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[16]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[7]++;


int CodeCoverConditionCoverageHelper_C6;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((i < arr.length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[7]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[8]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[9]++;
}
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((arr[i] == c) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[9]++;
        return true;

      } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[10]++;}
    }
    return false;
  }

  /**
   * Generates the next short name.
   */
  String generateNextName() {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[18]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[10]++;


    while (true) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[10]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[11]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[12]++;
}
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[19]++;
      String name = prefix;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[20]++;

      int i = nameCount;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[21]++;
int CodeCoverConditionCoverageHelper_C9;

      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((name.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[11]++;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[22]++;
        int pos = i % firstChars.length;
        name += firstChars[pos];
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[23]++;
        i /= firstChars.length;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[24]++;

      } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[12]++;}
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[25]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[13]++;


int CodeCoverConditionCoverageHelper_C10;

      while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i > 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[13]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[14]--;
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.loops[15]++;
}
        i--;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[26]++;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[27]++;
        int pos = i % nonFirstChars.length;
        name += nonFirstChars[pos];
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[28]++;
        i /= nonFirstChars.length;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[29]++;
      }

      nameCount++;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[30]++;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;

      // Make sure it's not a JS keyword or reserved name.
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((TokenStream.isKeyword(name)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((reservedNames.contains(name)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[13]++;
CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.statements[32]++;
        continue;

      } else {
  CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt.branches[14]++;}

      return name;
    }
  }
}

class CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt ());
  }
    public static long[] statements = new long[33];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.NameGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,0,1,1,2};
    for (int i = 1; i <= 11; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$6infzv7s1f4bzsdbzxjziabcqbtt () {
    super("com.google.javascript.jscomp.NameGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 32; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.NameGenerator.java");
      for (int i = 1; i <= 32; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

