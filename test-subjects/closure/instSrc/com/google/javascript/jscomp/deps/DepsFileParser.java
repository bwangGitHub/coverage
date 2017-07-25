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
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import com.google.javascript.jscomp.ErrorManager;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A parser that can extract dependency information from existing deps.js files.
 *
 * <p>See //javascript/closure/deps.js for an example file.</p>
 *
 * @author agrieve@google.com (Andrew Grieve)
 */
public class DepsFileParser extends JsFileLineParser {
  static {
    CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.ping();
  }


  private static Logger logger = Logger.getLogger(DepsFileParser.class.getName());
  static {
    CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[1]++;
  }

  /**
   * Pattern for matching JavaScript string literals. The group is:
   * goog.addDependency({1});
   */
  private final Matcher depMatcher =
      Pattern.compile("\\s*goog.addDependency\\((.*)\\);?\\s*").matcher("");
  {
    CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[2]++;
  }

  /**
   * Pattern for matching the args of a goog.addDependency(). The group is:
   * goog.addDependency({1}, {2}, {3});
   */
  private final Matcher depArgsMatch =
      Pattern.compile("\\s*([^,]*), (\\[[^\\]]*\\]), (\\[[^\\]]*\\])\\s*").matcher("");
  {
    CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[3]++;
  }

  /**
   * The dependency information extracted from the current file.
   */
  private List<DependencyInfo> depInfos;

  /** Translates paths in different build systems. */
  private final Function<String, String> pathTranslator;

  /**
   * Constructor
   *
   * @param errorManager Handles parse errors.
   */
  public DepsFileParser(ErrorManager errorManager) {
    this(Functions.<String>identity(), errorManager);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[4]++;
  }

  /**
   * @param pathTranslator Translates paths in different build systems.
   * @param errorManager Handles parse errors.
   */
  public DepsFileParser(Function<String, String> pathTranslator,
      ErrorManager errorManager) {
    super(errorManager);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[5]++;
    this.pathTranslator = pathTranslator;
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[6]++;
  }

  /**
   * Parses the given file and returns a list of dependency information that it
   * contained.
   *
   * @param filePath Path to the file to parse.
   * @return A list of DependencyInfo objects.
   * @throws IOException Thrown if the file could not be read.
   */
  public List<DependencyInfo> parseFile(String filePath) throws IOException {
    return parseFileReader(filePath, new FileReader(filePath));
  }

  /**
   * Parses the given file and returns a list of dependency information that it
   * contained.
   * It uses the passed in fileContents instead of reading the file.
   *
   * @param filePath Path to the file to parse.
   * @param fileContents The contents to parse.
   * @return A list of DependencyInfo objects.
   */
  public List<DependencyInfo> parseFile(String filePath, String fileContents) {
    return parseFileReader(filePath, new StringReader(fileContents));
  }


  /**
   * Parses the file from the given reader and returns a list of
   * dependency information that it contained.
   *
   * @param filePath Path to the file to parse.
   * @param reader A reader for the file.
   * @return A list of DependencyInfo objects.
   */
  public List<DependencyInfo> parseFileReader(String filePath, Reader reader) {
    depInfos = Lists.newArrayList();
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[7]++;
    logger.fine("Parsing Dep: " + filePath);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[8]++;
    doParse(filePath, reader);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[9]++;
    return depInfos;
  }

  /**
   * Extracts dependency information from lines that look like
   *   goog.addDependency('pathRelativeToClosure', ['provides'], ['requires']);
   * Adds the dependencies to depInfos.
   *
   * @throws ParseException Thrown if the given line has a malformed
   *     goog.addDependency().
   */
  @Override
  protected boolean parseLine(String line) throws ParseException {
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[10]++;
    boolean hasDependencies = false;
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;

    // Quick sanity check that will catch most cases. This is a performance
    // win for people with a lot of JS.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((line.indexOf("addDependency") != -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[1]++;
      depMatcher.reset(line);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[12]++;
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      // See if the line looks like: goog.addDependency(...)
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((depMatcher.matches()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[3]++;
        hasDependencies = true;
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[14]++;
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[15]++;
        String addDependencyParams = depMatcher.group(1);
        depArgsMatch.reset(addDependencyParams);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[16]++;
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
        // Extract the three parameters.
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((depArgsMatch.matches()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[5]++;
          // Although we could recover, we mark this as fatal since there should
          // not be problems with generated deps.js files.
          throw new ParseException("Invalid arguments to goog.addDependency(). Found: "
              + addDependencyParams, true);

        } else {
  CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[6]++;}
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[18]++;
        // Parse the file path.
        String path = pathTranslator.apply(parseJsString(depArgsMatch.group(1)));
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[19]++;
        DependencyInfo depInfo = new SimpleDependencyInfo(path, filePath,
            // Parse the provides.
            parseJsStringArray(depArgsMatch.group(2)),
            // Parse the requires.
            parseJsStringArray(depArgsMatch.group(3)));
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((logger.isLoggable(Level.FINE)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[7]++;
          logger.fine("Found dep: " + depInfo);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[21]++;

        } else {
  CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[8]++;}
        depInfos.add(depInfo);
CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.statements[22]++;

      } else {
  CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[4]++;}

    } else {
  CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt.branches[2]++;}

    return !shortcutMode || hasDependencies ||
        CharMatcher.WHITESPACE.matchesAllOf(line);
  }
}

class CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt ());
  }
    public static long[] statements = new long[23];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.DepsFileParser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
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

  public CodeCoverCoverageCounter$14g2quzpn5cfjvc98p2hnlrzcodopt () {
    super("com.google.javascript.jscomp.deps.DepsFileParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 22; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.deps.DepsFileParser.java");
      for (int i = 1; i <= 22; i++) {
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

