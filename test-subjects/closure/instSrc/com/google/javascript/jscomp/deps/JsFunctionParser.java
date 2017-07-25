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

import java.io.Reader;
import java.io.StringReader;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A parser that can extract dependency information from a .js file.
 *
 * @author agrieve@google.com (Andrew Grieve)
 * @author ielashi@google.com (Islam El-Ashi)
 */
public class JsFunctionParser extends JsFileLineParser {
  static {
    CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.ping();
  }


  public static class SymbolInfo {
    public final String functionName;
    public final String symbol;

    private SymbolInfo(String functionName, String symbol) {
      this.functionName = functionName;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[1]++;
      this.symbol = symbol;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[2]++;
    }
  }

  private static Logger logger =
      Logger.getLogger(JsFunctionParser.class.getName());
  static {
    CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[3]++;
  }

  /** Pattern for matching functions. */
  private Pattern pattern;

  /** Matcher used in the parsing. */
  private Matcher matcher;

  /** Symbols parsed. */
  private Collection<SymbolInfo> symbols;

  /** Functions to parse */
  private Collection<String> functionsToParse;

  /**
   * Constructor
   *
   * @param functions Functions to parse.
   * @param errorManager Handles parse errors.
   */
  public JsFunctionParser(
      Collection<String> functions, ErrorManager errorManager) {
    super(errorManager);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[4]++;
    functionsToParse = functions;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[5]++;
    pattern = getPattern(functions);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[6]++;
    matcher = pattern.matcher("");
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[7]++;
  }

  /**
   * Constructs a pattern to extract the arguments of the given functions.
   *
   * @param functions Functions to parse.
   * @return A pattern to extract {@code functions}' arguments.
   */
  private Pattern getPattern(Collection<String> functions) {
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[8]++;
    StringBuilder sb = new StringBuilder("(?:^|;)\\s*(");
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[9]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[1]++;



    for (String function : functions) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[1]--;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[2]--;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[3]++;
}
      sb.append(Pattern.quote(function) + "|");
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[10]++;
    }

    // remove last '|'
    sb.deleteCharAt(sb.length() - 1);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[11]++;
    sb.append(")\\s*\\((.*?)\\)");
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[12]++;

    return Pattern.compile(sb.toString());
  }

  /**
   * Parses the given file and returns the dependency information that it
   * contained.
   *
   * @param filePath Path to the file to parse.
   * @param fileContents The contents to parse.
   * @return A collection containing all symbols found in the
   *     file.
   */
  public Collection<SymbolInfo> parseFile(
      String filePath, String fileContents) {
    return parseReader(filePath, new StringReader(fileContents));
  }

  private Collection<SymbolInfo> parseReader(
      String filePath, Reader fileContents) {
    symbols = Lists.newArrayList();
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[13]++;

    logger.fine("Parsing Source: " + filePath);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[14]++;
    doParse(filePath, fileContents);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[15]++;

    return symbols;
  }

  /**
   * Parses a line of JavaScript, extracting dependency information.
   */
  @Override
  protected boolean parseLine(String line) throws ParseException {
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[16]++;
    boolean hasFunctions = false;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[17]++;
    boolean parseLine = false;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[4]++;



    // Quick sanity check that will catch most cases. This is a performance
    // win for people with a lot of JS.
    for (String function : functionsToParse) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[4]--;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[5]--;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[6]++;
}
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[19]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((line.indexOf(function) != -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.branches[1]++;
        parseLine = true;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[20]++;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[21]++;
        break;

      } else {
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.branches[2]++;}
    }
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[22]++;
int CodeCoverConditionCoverageHelper_C2;

    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((parseLine) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.branches[3]++;
      matcher.reset(line);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[23]++;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[7]++;


int CodeCoverConditionCoverageHelper_C3;
      while ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((matcher.find()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[7]--;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[8]--;
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.loops[9]++;
}
        hasFunctions = true;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[25]++;
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[26]++;
        String functionName = matcher.group(1);
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[27]++;
        String arg = parseJsString(matcher.group(2)); // Parse the param.
        symbols.add(new SymbolInfo(functionName, arg));
CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.statements[28]++;
      }

    } else {
  CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9.branches[4]++;}

    return !shortcutMode || hasFunctions ||
        CharMatcher.WHITESPACE.matchesAllOf(line);
  }
}

class CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9 ());
  }
    public static long[] statements = new long[29];
    public static long[] branches = new long[5];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.deps.JsFunctionParser.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$1puc3e5y230b46uv0cysr7sf9sl54u3q9 () {
    super("com.google.javascript.jscomp.deps.JsFunctionParser.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 28; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 4; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.deps.JsFunctionParser.java");
      for (int i = 1; i <= 28; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 4; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 3; i++) {
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

