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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.javascript.jscomp.CodeChangeHandler.RecentChange;
import com.google.javascript.jscomp.CompilerOptions.TracerMode;
import com.google.javascript.rhino.Node;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPOutputStream;

/**
 */
public class PerformanceTracker {
  static {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.ping();
  }


  private final Node jsRoot;
  private final boolean trackSize;
  private final boolean trackGzSize;

  // Keeps track of AST changes and computes code size estimation
  // if there is any.
  private final RecentChange codeChange = new RecentChange();
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[1]++;
  }

  private int codeSize = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[2]++;
  }
  private int gzCodeSize = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[3]++;
  }
  private int initCodeSize = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[4]++;
  }
  private int initGzCodeSize = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[5]++;
  }

  private Deque<Stats> currentPass = new ArrayDeque<Stats>();
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[6]++;
  }

  /** Summary stats by pass name. */
  private final Map<String, Stats> summary = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[7]++;
  }

  // To share with the rest of the program
  private ImmutableMap<String, Stats> summaryCopy = null;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[8]++;
  }

  /** Stats for each run of a compiler pass. */
  private final List<Stats> log = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[9]++;
  }

  public static class Stats {
    Stats(String pass, boolean iot) {
      this.pass = pass;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[10]++;
      this.isOneTime = iot;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[11]++;
    }
    public final String pass;
    public final boolean isOneTime;
    public long runtime = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[12]++;
  }
    public int runs = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[13]++;
  }
    public int changes = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[14]++;
  }
    public int diff = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[15]++;
  }
    public int gzDiff = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[16]++;
  }
    public int size = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[17]++;
  }
    public int gzSize = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[18]++;
  }
  }

  PerformanceTracker(Node jsRoot, TracerMode mode) {
    this.jsRoot = jsRoot;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[19]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[20]++;
    switch (mode) {
      case TIMING_ONLY:
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[1]++;
        this.trackSize = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[21]++;
        this.trackGzSize = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[22]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[23]++;
        break;

      case RAW_SIZE:
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[2]++;
        this.trackSize = true;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[24]++;
        this.trackGzSize = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[25]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[26]++;
        break;

      case ALL:
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[3]++;
        this.trackSize = true;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[27]++;
        this.trackGzSize = true;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[28]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[29]++;
        break;

      case OFF:
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[4]++;
      default:
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[5]++;
        throw new UnsupportedOperationException();
    }
  }

  CodeChangeHandler getCodeChangeHandler() {
    return codeChange;
  }

  void recordPassStart(String passName, boolean isOneTime) {
    currentPass.push(new Stats(passName, isOneTime));
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[30]++;
    codeChange.reset();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[31]++;
  }

  /**
   * Record that a pass has stopped.
   *
   * @param passName Short name of the pass.
   * @param result Execution time.
   */
  void recordPassStop(String passName, long result) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[32]++;
    Stats logStats = currentPass.pop();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[33]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((passName.equals(logStats.pass)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[6]++;
      throw new RuntimeException(passName + " is not running.");

    } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[7]++;}
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[34]++;
int CodeCoverConditionCoverageHelper_C2;

    // After parsing, initialize codeSize and gzCodeSize
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((passName.equals(Compiler.PARSING_PASS_NAME)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((trackSize) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[8]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[35]++;
      CodeSizeEstimatePrinter printer = new CodeSizeEstimatePrinter();
      CodeGenerator.forCostEstimation(printer).add(jsRoot);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[36]++;
      initCodeSize = codeSize = printer.calcSize();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[37]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[38]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this.trackGzSize) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[10]++;
        initGzCodeSize = gzCodeSize = printer.calcZippedSize();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[39]++;

      } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[11]++;}

    } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[9]++;}

    // Populate log and summary
    log.add(logStats);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[40]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[41]++;
    Stats summaryStats = summary.get(passName);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[42]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((summaryStats == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[12]++;
      summaryStats = new Stats(passName, logStats.isOneTime);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[43]++;
      summary.put(passName, summaryStats);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[44]++;

    } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[13]++;}

    // Update fields that aren't related to code size
    logStats.runtime = result;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[45]++;
    logStats.runs = 1;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[46]++;
    summaryStats.runtime += result;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[47]++;
    summaryStats.runs += 1;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[48]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[49]++;
int CodeCoverConditionCoverageHelper_C5;
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((codeChange.hasCodeChanged()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[14]++;
      logStats.changes = 1;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[50]++;
      summaryStats.changes += 1;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[51]++;

    } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[15]++;}
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[52]++;
int CodeCoverConditionCoverageHelper_C6;

    // Update fields related to code size
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((codeChange.hasCodeChanged()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((trackSize) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[16]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[53]++;
      int newSize = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[54]++;
      CodeSizeEstimatePrinter printer = new CodeSizeEstimatePrinter();
      CodeGenerator.forCostEstimation(printer).add(jsRoot);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[55]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[56]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((trackSize) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[18]++;
        newSize = printer.calcSize();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[57]++;
        logStats.diff = codeSize - newSize;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[58]++;
        summaryStats.diff += logStats.diff;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[59]++;
        codeSize = summaryStats.size = logStats.size = newSize;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[60]++;

      } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[19]++;}
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[61]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((trackGzSize) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[20]++;
        newSize = printer.calcZippedSize();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[62]++;
        logStats.gzDiff = gzCodeSize - newSize;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[63]++;
        summaryStats.gzDiff += logStats.gzDiff;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[64]++;
        gzCodeSize = summaryStats.gzSize = logStats.gzSize = newSize;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[65]++;

      } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[21]++;}

    } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[17]++;}
  }

  public ImmutableMap<String, Stats> getStats() {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[66]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((summaryCopy == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[22]++;
      summaryCopy = ImmutableMap.copyOf(summary);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[67]++;

    } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[23]++;}
    return summaryCopy;
  }

  class CmpEntries implements Comparator<Entry<String, Stats>> {
    @Override
    public int compare(Entry<String, Stats> e1, Entry<String, Stats> e2) {
      return (int) (e1.getValue().runtime - e2.getValue().runtime);
    }
  }

  public void outputTracerReport(PrintStream pstr) {
    JvmMetrics.maybeWriteJvmMetrics(pstr, "verbose:pretty:all");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[68]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[69]++;
    OutputStreamWriter output = new OutputStreamWriter(pstr);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[70]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[71]++;
      int runtime = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[72]++;
      int runs = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[73]++;
      int changes = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[74]++;
      int loopRuns = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[75]++;
      int loopChanges = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[76]++;
      int diff = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[77]++;
      int gzDiff = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[78]++;

      ArrayList<Entry<String, Stats>> a = new ArrayList<Entry<String, Stats>>();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[79]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[1]++;


      for (Entry<String, Stats> entry : summary.entrySet()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[1]--;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[2]--;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[3]++;
}
        a.add(entry);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[80]++;
      }
      Collections.sort(a, new CmpEntries());
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[81]++;

      output.write("Summary:\n" +
          "pass,runtime,runs,changingRuns,reduction,gzReduction\n");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[82]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[83]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[4]++;


      for (Entry<String, Stats> entry : a) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[4]--;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[5]--;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[6]++;
}
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[84]++;
        String key = entry.getKey();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[85]++;
        Stats stats = entry.getValue();
        runtime += stats.runtime;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[86]++;
        runs += stats.runs;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[87]++;
        changes += stats.changes;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[88]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[89]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((stats.isOneTime) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[25]++;
          loopRuns += stats.runs;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[90]++;
          loopChanges += stats.changes;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[91]++;

        } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[26]++;}
        diff += stats.diff;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[92]++;
        gzDiff += stats.gzDiff;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[93]++;
        output.write(key + "," +
            String.valueOf(stats.runtime) + "," +
            String.valueOf(stats.runs) + "," +
            String.valueOf(stats.changes) + "," +
            String.valueOf(stats.diff) + "," +
            String.valueOf(stats.gzDiff) + "\n");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[94]++;
      }
      output.write("\nTOTAL:" +
          "\nRuntime(ms): " + String.valueOf(runtime) +
          "\n#Runs: " + String.valueOf(runs) +
          "\n#Changing runs: " + String.valueOf(changes) +
          "\n#Loopable runs: " + String.valueOf(loopRuns) +
          "\n#Changing loopable runs: " + String.valueOf(loopChanges) +
          "\nReduction(bytes): " + String.valueOf(diff) +
          "\nGzReduction(bytes): " + String.valueOf(gzDiff) +
          "\nSize(bytes): " + String.valueOf(codeSize) +
          "\nGzSize(bytes): " + String.valueOf(gzCodeSize) + "\n\n");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[95]++;

      Preconditions.checkState(!trackSize || initCodeSize == diff + codeSize);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[96]++;
      Preconditions.checkState(!trackGzSize ||
          initGzCodeSize == gzDiff + gzCodeSize);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[97]++;

      output.write("Log:\n" +
          "pass,runtime,runs,changingRuns,reduction,gzReduction,size,gzSize\n");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[98]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[99]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[7]++;


      for (Stats stats : log) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[7]--;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[8]--;
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.loops[9]++;
}
        output.write(stats.pass + "," +
            String.valueOf(stats.runtime) + "," +
            String.valueOf(stats.runs) + "," +
            String.valueOf(stats.changes) + "," +
            String.valueOf(stats.diff) + "," +
            String.valueOf(stats.gzDiff) + "," +
            String.valueOf(stats.size) + "," +
            String.valueOf(stats.gzSize) + "\n");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[100]++;
      }
      output.write("\n");
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[101]++;
      output.close();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[102]++;
    } catch (IOException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[27]++;
      e.printStackTrace();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[103]++;
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[24]++;
}
  }
  }

  /**
   * Purely use to get a code size estimate and not generate any code at all.
   */
  private final class CodeSizeEstimatePrinter extends CodeConsumer {
    private int size = 0;
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[104]++;
  }
    private char lastChar = '\0';
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[105]++;
  }
    private final ByteArrayOutputStream output = new ByteArrayOutputStream();
  {
    CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[106]++;
  }
    private final GZIPOutputStream stream;

    private CodeSizeEstimatePrinter() {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[107]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
      try {
CodeCoverTryBranchHelper_Try2 = true;
        stream = new GZIPOutputStream(output);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[108]++;
      } catch (IOException e) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[29]++;
        throw new RuntimeException(e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[28]++;
}
  }
    }

    @Override
    void append(String str) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[109]++;
      int len = str.length();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[110]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((len > 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[30]++;
        size += len;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[111]++;
        lastChar = str.charAt(len - 1);
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[112]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[113]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((trackGzSize) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[32]++;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[114]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
          try {
CodeCoverTryBranchHelper_Try3 = true;
            stream.write(str.getBytes());
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[115]++;
          } catch (IOException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[35]++;
            throw new RuntimeException(e);
          } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[34]++;
}
  }

        } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[33]++;}

      } else {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[31]++;}
    }

    @Override
    char getLastChar() {
      return lastChar;
    }

    private int calcSize() {
      return size;
    }

    // Called iff trackGzSize is true
    private int calcZippedSize() {
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[116]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
      try {
CodeCoverTryBranchHelper_Try4 = true;
        stream.finish();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[117]++;
        stream.flush();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[118]++;
        stream.close();
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.statements[119]++;
        return output.size();
      } catch (IOException e) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[37]++;
        throw new RuntimeException(e);
      } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh.branches[36]++;
}
  }
    }
  }
}

class CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh ());
  }
    public static long[] statements = new long[120];
    public static long[] branches = new long[38];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PerformanceTracker.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,2,1,1,1,1,1,1};
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
    public static long[] loops = new long[10];

  public CodeCoverCoverageCounter$2lt0g03uisq10qbuium9xtfnqg3rwbr30twh () {
    super("com.google.javascript.jscomp.PerformanceTracker.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 119; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 37; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 9; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PerformanceTracker.java");
      for (int i = 1; i <= 119; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 37; i++) {
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

