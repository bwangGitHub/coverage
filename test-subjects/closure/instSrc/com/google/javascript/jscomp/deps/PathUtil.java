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

package com.google.javascript.jscomp.deps;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * Utility methods for manipulation of UNIX-like paths.
 * NOTE: According to kevinb, equivalent methods will be in the standard library once
 * jsr203 is ready.
 *
 */
public final class PathUtil {
  static {
    CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.ping();
  }


  private static final CharMatcher SLASH_MATCHER = CharMatcher.is('/');
  static {
    CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[1]++;
  }
  private static final CharMatcher NON_SLASH_MATCHER = CharMatcher.isNot('/');
  static {
    CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[2]++;
  }

  private PathUtil() {
  }

  /**
   * Removes all ../ and ./ entries from within the given path. If there are extra ..s that move
   * beyond the first directory given, they are removed.
   *
   * Examples:
   *   "a/b/../c" results in "a/c"
   *   "./foo/./../bar" results in "bar"
   *   "a/.." results in ""
   *   "a/../../foo" results in "foo"
   *
   * @param path The path to remove dots from.
   * @return The path with all dots collapsed.
   */
  public static String collapseDots(String path) {
    path = removeExtraneousSlashes(path);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[3]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    // Optimization: Most paths don't contain dots.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((path.contains(".")) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[1]++;
      return path;

    } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[2]++;}
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[5]++;

    String[] srcFragments = path.split("/");
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[6]++;
    List<String> dstFragments = Lists.newArrayList();
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[1]++;


    for (String fragment : srcFragments) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[1]--;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[2]--;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[3]++;
}
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((fragment.equals("..")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[3]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((dstFragments.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[5]++;
          dstFragments.remove(dstFragments.size() - 1);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[10]++;

        } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[6]++;}

      } else {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[4]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[11]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((fragment.equals(".")) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[7]++;
        dstFragments.add(fragment);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[12]++;

      } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[8]++;}
}
    }
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[13]++;
int CodeCoverConditionCoverageHelper_C5;

    // Special case for Join.join([""]); -> "/"
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((dstFragments.size() == 1) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((dstFragments.get(0).isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[9]++;
      return "/";

    } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[10]++;}
    return Joiner.on("/").join(dstFragments);
  }

  /**
   * Determines if a path is absolute or not by testing for the presence of "/"
   * at the front of the string.
   *
   * @param path The path to test
   * @return true if the path starts with DELIMITER, false otherwise.
   */
  static boolean isAbsolute(String path) {
    return path.startsWith("/");
  }

  /**
   * Removes extra slashes from a path.  Leading slash is preserved, trailing
   * slash is stripped, and any runs of more than one slash in the middle is
   * replaced by a single slash.
   */
  static String removeExtraneousSlashes(String s) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[14]++;
    int lastNonSlash = NON_SLASH_MATCHER.lastIndexIn(s);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((lastNonSlash != -1) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[11]++;
      s = s.substring(0, lastNonSlash + 1);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[16]++;

    } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[12]++;}

    return SLASH_MATCHER.collapseFrom(s, '/');
  }


  /**
   * Converts the given path into an absolute one. This prepends the current
   * working directory and removes all .'s from the path. If an absolute path
   * is given, it will not be prefixed.
   *
   * <p>Unlike File.getAbsolutePath(), this function does remove .'s from the
   * path and unlike File.getCanonicalPath(), this function does not resolve
   * symlinks and does not use filesystem calls.</p>
   *
   * @param path The path to make absolute.
   * @return The path made absolute.
   */
  public static String makeAbsolute(String path) {
    return makeAbsolute(path, System.getProperty("user.dir"));
  }

  /**
   * Converts the given path into an absolute one. This prepends the given
   * rootPath and removes all .'s from the path. If an absolute path is given,
   * it will not be prefixed.
   *
   * <p>Unlike File.getAbsolutePath(), this function does remove .'s from the
   * path and unlike File.getCanonicalPath(), this function does not resolve
   * symlinks and does not use filesystem calls.</p>
   *
   * @param rootPath The path to prefix to path if path is not already absolute.
   * @param path The path to make absolute.
   * @return The path made absolute.
   */
  public static String makeAbsolute(String path, String rootPath) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isAbsolute(path)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[13]++;
      path = rootPath + "/" + path;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[18]++;

    } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[14]++;}
    return collapseDots(path);
  }

  /**
   * Returns targetPath relative to basePath.
   *
   * <p>basePath and targetPath must either both be relative, or both be
   * absolute paths.</p>
   *
   * <p>This function is different from makeRelative
   * in that it is able to add in ../ components and collapse existing ones as well.</p>
   *
   * Examples:
   *   base="some/relative/path" target="some/relative/path/foo" return="foo"
   *   base="some/relative/path" target="some/relative" return=".."
   *   base="some/relative/path" target="foo/bar" return="../../../foo/bar"
   *   base="/some/abs/path" target="/foo/bar" return="../../../foo/bar"
   *
   * @param basePath The path to make targetPath relative to.
   * @param targetPath The path to make relative.
   * @return A path relative to targetPath. The returned value will never start
   *     with a slash.
   */
  public static String makeRelative(String basePath, String targetPath) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[19]++;
int CodeCoverConditionCoverageHelper_C8;
    // Ensure the paths are both absolute or both relative.
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isAbsolute(basePath) !=
        isAbsolute(targetPath)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[15]++;
      throw new IllegalArgumentException(
          "Paths must both be relative or both absolute.\n" +
          "  basePath: " + basePath + "\n" +
          "  targetPath: " + targetPath);

    } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[16]++;}

    basePath = collapseDots(basePath);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[20]++;
    targetPath = collapseDots(targetPath);
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[21]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[22]++;
    String[] baseFragments = basePath.split("/");
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[23]++;
    String[] targetFragments = targetPath.split("/");
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[24]++;

    int i = -1;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[25]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
    do {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[4]--;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[5]--;
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.loops[6]++;
}
      i += 1;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[26]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
      if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((i == baseFragments.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i == targetFragments.length) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[17]++;
        // Eg) base: /java/com/google
        //   target: /java/com/google
        //   result: .  <-- . is better than "" since "" + "/path" = "/path"
        return ".";

      } else {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[18]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[28]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i == baseFragments.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[19]++;
        // Eg) base: /java/com/google
        //   target: /java/com/google/c/ui
        //   result: c/ui
        return Joiner.on("/").join(
            Lists.newArrayList(
                Arrays.asList(targetFragments).listIterator(i)));

      } else {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[20]++;
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.statements[29]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i == targetFragments.length) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[21]++;
        // Eg) base: /java/com/google/c/ui
        //   target: /java/com/google
        //   result: ../..
        return Strings.repeat("../", baseFragments.length - i - 1) + "..";

      } else {
  CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.branches[22]++;}
}
}

    } while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((baseFragments[i].equals(targetFragments[i])) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false));

    // Eg) base: /java/com/google/c
    //   target: /java/com/google/common/base
    //   result: ../common/base
    return Strings.repeat("../", baseFragments.length - i) +
        Joiner.on("/").join(
            Lists.newArrayList(Arrays.asList(targetFragments).listIterator(i)));
  }
}

class CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x ());
  }
    public static long[] statements = new long[30];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.PathUtil.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,2,1,1,1,1,2,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$h5fekp7f5bj2tpzwtn4x () {
    super("com.google.javascript.jscomp.deps.PathUtil.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 29; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.deps.PathUtil.java");
      for (int i = 1; i <= 29; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

