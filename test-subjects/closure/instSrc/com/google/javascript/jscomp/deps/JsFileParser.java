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
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.ErrorManager;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A parser that can extract goog.require() and goog.provide() dependency
 * information from a .js file.
 *
 * @author agrieve@google.com (Andrew Grieve)
 */
public class JsFileParser extends JsFileLineParser {
  static {
    CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.ping();
  }


  private static Logger logger = Logger.getLogger(JsFileParser.class.getName());
  static {
    CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[1]++;
  }

  /** Pattern for matching goog.provide(*) and goog.require(*). */
  private static final Pattern GOOG_PROVIDE_REQUIRE_PATTERN = Pattern.compile(
      "(?:^|;)\\s*goog\\.(provide|require|addDependency)\\s*\\((.*?)\\)");
  static {
    CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[2]++;
  }

  /** The first non-comment line of base.js */
  private static final String BASE_JS_START = "var COMPILED = false;";
  static {
    CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[3]++;
  }

  /** Matchers used in the parsing. */
  private Matcher googMatcher = GOOG_PROVIDE_REQUIRE_PATTERN.matcher("");
  {
    CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[4]++;
  }

  /** The info for the file we are currently parsing. */
  private List<String> provides;
  private List<String> requires;
  private boolean fileHasProvidesOrRequires;

  /** Whether to provide/require the root namespace. */
  private boolean includeGoogBase = false;
  {
    CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[5]++;
  }

  /**
   * Constructor
   *
   * @param errorManager Handles parse errors.
   */
  public JsFileParser(ErrorManager errorManager) {
    super(errorManager);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[6]++;
  }

  /**
   * Sets whether we should create implicit provides and requires of the
   * root namespace.
   *
   * When generating deps files, you do not want this behavior. Deps files
   * need base.js to run anyway, so they don't need information about it.
   *
   * When generating abstract build graphs, you probably do want this behavior.
   * It will create an implicit dependency of all files with provides/requires
   * on base.js.
   *
   * @return this for easy chaining.
   */
  public JsFileParser setIncludeGoogBase(boolean include) {
    includeGoogBase = include;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[7]++;
    return this;
  }

  /**
   * Parses the given file and returns the dependency information that it
   * contained.
   *
   * @param filePath Path to the file to parse.
   * @param closureRelativePath Path of the file relative to closure.
   * @return A DependencyInfo containing all provides/requires found in the
   *     file.
   * @throws IOException Thrown if there was an problem reading the given file.
   */
  public DependencyInfo parseFile(String filePath, String closureRelativePath)
      throws IOException {
    return parseReader(filePath, closureRelativePath, new FileReader(filePath));
  }

  /**
   * Parses the given file and returns the dependency information that it
   * contained.
   *
   * @param filePath Path to the file to parse.
   * @param closureRelativePath Path of the file relative to closure.
   * @param fileContents The contents to parse.
   * @return A DependencyInfo containing all provides/requires found in the
   *     file.
   */
  public DependencyInfo parseFile(String filePath, String closureRelativePath,
      String fileContents) {
    return parseReader(filePath, closureRelativePath,
        new StringReader(fileContents));
  }

  private DependencyInfo parseReader(String filePath,
      String closureRelativePath, Reader fileContents) {
    provides = Lists.newArrayList();
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[8]++;
    requires = Lists.newArrayList();
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[9]++;
    fileHasProvidesOrRequires = false;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[10]++;

    logger.fine("Parsing Source: " + filePath);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[11]++;
    doParse(filePath, fileContents);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[12]++;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[13]++;

    DependencyInfo dependencyInfo = new SimpleDependencyInfo(
        closureRelativePath, filePath, provides, requires);
    logger.fine("DepInfo: " + dependencyInfo);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[14]++;
    return dependencyInfo;
  }

  /**
   * Parses a line of JavaScript, extracting goog.provide and goog.require
   * information.
   */
  @Override
  protected boolean parseLine(String line) throws ParseException {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[15]++;
    boolean lineHasProvidesOrRequires = false;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[16]++;
int CodeCoverConditionCoverageHelper_C1;

    // Quick sanity check that will catch most cases. This is a performance
    // win for people with a lot of JS.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((line.indexOf("provide") != -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((line.indexOf("require") != -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((line.indexOf("addDependency") != -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[1]++;
      // Iterate over the provides/requires.
      googMatcher.reset(line);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[17]++;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
      while ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((googMatcher.find()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.loops[1]--;
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.loops[2]--;
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.loops[3]++;
}
        lineHasProvidesOrRequires = true;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[19]++;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((includeGoogBase) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((fileHasProvidesOrRequires) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[3]++;
          fileHasProvidesOrRequires = true;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[21]++;
          requires.add("goog");
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[22]++;

        } else {
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[4]++;}
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[23]++;

        // See if it's a require or provide.
        char firstChar = googMatcher.group(1).charAt(0);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[24]++;
        boolean isProvide = firstChar == 'p';
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[25]++;
        boolean isRequire = firstChar == 'r';
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[26]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((isProvide) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((isRequire) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[5]++;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[27]++;
          // Parse the param.
          String arg = parseJsString(googMatcher.group(2));
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;

          // Add the dependency.
          if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isRequire) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[7]++;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
            // goog is always implicit.
            // TODO(nicksantos): I'm pretty sure we don't need this anymore.
            // Remove this later.
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 (("goog".equals(arg)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[9]++;
              requires.add(arg);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[30]++;

            } else {
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[10]++;}

          } else {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[8]++;
            provides.add(arg);
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[31]++;
          }

        } else {
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[6]++;}
      }

    } else {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[2]++;
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[32]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (128)) == 0 || true) &&
 ((includeGoogBase) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (32)) == 0 || true) &&
 ((line.startsWith(BASE_JS_START)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((provides.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((requires.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 4) || true)) || (CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 4) && false)) {
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[11]++;
      provides.add("goog");
CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.statements[33]++;

      // base.js can't provide or require anything else.
      return false;

    } else {
  CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt.branches[12]++;}
}

    return !shortcutMode || lineHasProvidesOrRequires ||
        CharMatcher.WHITESPACE.matchesAllOf(line);
  }
}

class CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt ());
  }
    public static long[] statements = new long[34];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[8];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.JsFileParser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3,1,2,2,1,1,3};
    for (int i = 1; i <= 7; i++) {
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

  public CodeCoverCoverageCounter$vccscp671nqzbm5xkqrequk3tt () {
    super("com.google.javascript.jscomp.deps.JsFileParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 33; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 7; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.deps.JsFileParser.java");
      for (int i = 1; i <= 33; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 7; i++) {
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

