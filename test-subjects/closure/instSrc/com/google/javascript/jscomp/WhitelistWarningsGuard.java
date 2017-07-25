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

package com.google.javascript.jscomp;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.TreeMultimap;
import com.google.common.io.CharStreams;
import com.google.common.io.Files;
import com.google.common.io.InputSupplier;
import com.google.javascript.jscomp.CheckLevel;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * An extension of {@code WarningsGuard} that provides functionality to maintain
 * a list of warnings (white-list). It is subclasses' responsibility to decide
 * what to do with the white-list by implementing the {@code level} function.
 * Warnings are defined by the name of the JS file and the first line of
 * warnings description.
 *
 * @author anatol@google.com (Anatol Pomazau)
 * @author bashir@google.com (Bashir Sadjad)
 */
public class WhitelistWarningsGuard extends WarningsGuard {
  static {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.ping();
  }

  private static final Splitter LINE_SPLITTER = Splitter.on("\n");
  static {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[1]++;
  }

  /** The set of white-listed warnings, same format as {@code formatWarning}. */
  private final Set<String> whitelist;

  /** Pattern to match line number in error descriptions. */
  private static final Pattern LINE_NUMBER = Pattern.compile(":-?\\d+");
  static {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[2]++;
  }

  /**
   * This class depends on an input set that contains the white-list. The format
   * of each white-list string is:
   * <file-name>:<line-number>?  <warning-description>
   * # <optional-comment>
   *
   * @param whitelist The set of JS-warnings that are white-listed. This is
   *     expected to have similar format as {@code formatWarning(JSError)}.
   */
  public WhitelistWarningsGuard(Set<String> whitelist) {
    Preconditions.checkNotNull(whitelist);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[3]++;
    this.whitelist = normalizeWhitelist(whitelist);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[4]++;
  }

  /**
   * Loads legacy warnings list from the set of strings. During development line
   * numbers are changed very often - we just cut them and compare without ones.
   *
   * @return known legacy warnings without line numbers.
   */
  private static Set<String> normalizeWhitelist(Set<String> whitelist) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[5]++;
    Set<String> result = Sets.newHashSet();
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[1]++;


    for (String line : whitelist) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[1]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[2]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[3]++;
}
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[7]++;
      String trimmed = line.trim();
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((trimmed.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((trimmed.charAt(0) == '#') && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[1]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[9]++;
        // strip out empty lines and comments.
        continue;

      } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[2]++;}

      // Strip line number for matching.
      result.add(LINE_NUMBER.matcher(trimmed).replaceFirst(":"));
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[10]++;
    }
    return ImmutableSet.copyOf(result);
  }

  @Override
  public CheckLevel level(JSError error) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((containWarning(formatWarning(error))) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[3]++;
      // If the message matches the guard we use WARNING, so that it
      // - Shows up on stderr, and
      // - Gets caught by the WhitelistBuilder downstream in the pipeline
      return CheckLevel.WARNING;

    } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[4]++;}

    return null;
  }

  /**
   * Determines whether a given warning is included in the white-list.
   *
   * @param formattedWarning the warning formatted by {@code formatWarning}
   * @return whether the given warning is white-listed or not.
   */
  protected boolean containWarning(String formattedWarning) {
    return whitelist.contains(formattedWarning);
  }

  @Override
  public int getPriority() {
    return WarningsGuard.Priority.SUPPRESS_BY_WHITELIST.getValue();
  }

  /** Creates a warnings guard from a file. */
  public static WhitelistWarningsGuard fromFile(File file) {
    return new WhitelistWarningsGuard(loadWhitelistedJsWarnings(file));
  }

  /**
   * Loads legacy warnings list from the file.
   * @return The lines of the file.
   */
  public static Set<String> loadWhitelistedJsWarnings(File file) {
    return loadWhitelistedJsWarnings(
        Files.newReaderSupplier(file, Charsets.UTF_8));
  }

  /**
   * Loads legacy warnings list from the file.
   * @return The lines of the file.
   */
  protected static Set<String> loadWhitelistedJsWarnings(
      InputSupplier<? extends Reader> supplier) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[12]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
      return loadWhitelistedJsWarnings(supplier.getInput());
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[6]++;
      throw new RuntimeException(e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[5]++;
}
  }
  }

  /**
   * Loads legacy warnings list from the file.
   * @return The lines of the file.
   */
  // TODO(nicksantos): This is a weird API.
  static Set<String> loadWhitelistedJsWarnings(Reader reader)
      throws IOException {
    Preconditions.checkNotNull(reader);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[13]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[14]++;
    Set<String> result = Sets.newHashSet();
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[4]++;



    for (String line : CharStreams.readLines(reader)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[4]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[5]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[6]++;
}
      result.add(line);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[16]++;
    }

    return result;
  }

  public static String formatWarning(JSError error) {
    return formatWarning(error, false);
  }

  /**
   * @param withMetaData If true, include metadata that's useful to humans
   *     This metadata won't be used for matching the warning.
   */
  public static String formatWarning(JSError error, boolean withMetaData) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[17]++;
    StringBuilder sb = new StringBuilder();
    sb.append(error.sourceName).append(":");
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[18]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[19]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((withMetaData) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[7]++;
      sb.append(error.lineNumber);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[20]++;

    } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[8]++;}
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[21]++;
    List<String> lines = ImmutableList.copyOf(
        LINE_SPLITTER.split(error.description));
    sb.append("  ").append(lines.get(0));
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[22]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;

    // Add the rest of the message as a comment.
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((withMetaData) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[9]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[24]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
      for (int i = 1;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < lines.size()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[7]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[8]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[9]++;
}
        sb.append("\n# ").append(lines.get(i));
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[25]++;
      }
      sb.append("\n");
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[26]++;

    } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[10]++;}

    return sb.toString();
  }

  public static String getFirstLine(String warning) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[27]++;
    int lineLength = warning.indexOf('\n');
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((lineLength > 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[11]++;
      warning = warning.substring(0, lineLength);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[29]++;

    } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[12]++;}
    return warning;
  }

  public static class WhitelistBuilder implements ErrorHandler {
    private final Set<JSError> warnings = Sets.newLinkedHashSet();
  {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[30]++;
  }
    private String productName = null;
  {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[31]++;
  }
    private String generatorTarget = null;
  {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[32]++;
  }
    private String headerNote = null;
  {
    CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[33]++;
  }

    /** Fill in your product name to get a fun message! */
    public WhitelistBuilder setProductName(String name) {
      this.productName = name;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[34]++;
      return this;
    }

    /** Fill in instructions on how to generate this whitelist. */
    public WhitelistBuilder setGeneratorTarget(String name) {
      this.generatorTarget = name;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[35]++;
      return this;
    }

    /** A note to include at the top of the whitelist file. */
    public WhitelistBuilder setNote(String note) {
      this.headerNote  = note;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[36]++;
      return this;
    }

    /** We now always record the line number. */
    @Deprecated
    public WhitelistBuilder setWithLineNumber(boolean line) {
      return this;
    }

    @Override
    public void report(CheckLevel level, JSError error) {
      warnings.add(error);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[37]++;
    }

    /**
     * Writes the warnings collected in a format that the WhitelistWarningsGuard
     * can read back later.
     */
    public void writeWhitelist(File out) throws IOException {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[38]++;
      PrintStream stream = new PrintStream(out);
      appendWhitelist(stream);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[39]++;
      stream.close();
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[40]++;
    }

    /**
     * Writes the warnings collected in a format that the WhitelistWarningsGuard
     * can read back later.
     */
    public void appendWhitelist(PrintStream out) {
      out.append(
          "# This is a list of legacy warnings that have yet to be fixed.\n");
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[41]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[42]++;
int CodeCoverConditionCoverageHelper_C7;

      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((productName != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[13]++;
        out.append("# Please find some time and fix at least one of them "
            + "and it will be the happiest day for " + productName + ".\n");
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[43]++;

      } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[14]++;}
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[44]++;
int CodeCoverConditionCoverageHelper_C8;

      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((generatorTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[15]++;
        out.append("# When you fix any of these warnings, run "
            + generatorTarget + " task.\n");
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[45]++;

      } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[16]++;}
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[46]++;
int CodeCoverConditionCoverageHelper_C9;
      
      if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((headerNote != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[17]++;
        out.append("#"
            + Joiner.on("\n# ").join(Splitter.on("\n").split(headerNote))
            + "\n");
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[47]++;

      } else {
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.branches[18]++;}
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[48]++;

      Multimap<DiagnosticType, String> warningsByType = TreeMultimap.create();
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[49]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[10]++;


      for (JSError warning : warnings) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[10]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[11]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[12]++;
}
        warningsByType.put(
            warning.getType(),
            formatWarning(warning, true /* withLineNumber */));
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[50]++;
      }
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[51]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[13]++;



      for (DiagnosticType type : warningsByType.keySet()) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[13]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[14]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[15]++;
}
        out.append("\n# Warning ")
            .append(type.key)
            .append(": ")
            .println(Iterables.get(
                LINE_SPLITTER.split(type.format.toPattern()), 0));
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[52]++;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[53]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[16]++;



        for (String warning : warningsByType.get(type)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[16]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[17]--;
  CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.loops[18]++;
}
          out.println(warning);
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[54]++;
        }
      }
      out.flush();
CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep.statements[55]++;
    }
  }
}

class CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep ());
  }
    public static long[] statements = new long[56];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[10];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.WhitelistWarningsGuard.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 9; i++) {
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

  public CodeCoverCoverageCounter$5l8867haxp0772ibk67fmp45z4tqtenyeu73jb91ep () {
    super("com.google.javascript.jscomp.WhitelistWarningsGuard.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 55; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 9; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.WhitelistWarningsGuard.java");
      for (int i = 1; i <= 55; i++) {
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
    for (int i = 1; i <= 9; i++) {
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

