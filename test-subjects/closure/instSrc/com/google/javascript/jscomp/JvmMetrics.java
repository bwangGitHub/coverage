/*
 * Copyright 2012 The Closure Compiler Authors.
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

import java.io.PrintStream;
import java.lang.management.CompilationMXBean;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A class to report jvm/jmx statistics.
 * Borrowed from:
 * http://code.google.com/p/dart/source/browse/trunk/dart/compiler/java/com/google/dart/compiler/metrics/JvmMetrics.java
 */
class JvmMetrics {
  static {
    CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.ping();
  }


  private static int TABULAR_COLON_POS = 40;
  static {
    CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[1]++;
  }
  private static long ONE_KILO_BYTE = 1L << 10L;
  static {
    CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[2]++;
  }
  private static long ONE_MEGA_BYTE = 1L << 20L;
  static {
    CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[3]++;
  }
  private static long ONE_GIGA_BYTE = 1L << 30L;
  static {
    CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[4]++;
  }

  public static void maybeWriteJvmMetrics(PrintStream out, String options) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((options == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[1]++;
      return;

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[2]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[6]++;

    boolean verboseMode = false;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[7]++;
    boolean prettyMode = false;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[8]++;
    StringTokenizer st = new StringTokenizer(options, ":");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
    // options are grouped in order 'detail:format:types'
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[3]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[10]++;
      String mode = st.nextToken();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[11]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((mode.equalsIgnoreCase("verbose")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[5]++;
        verboseMode = true;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[12]++;

      } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[6]++;}

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[4]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;

    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[7]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[14]++;
      String mode = st.nextToken();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((mode.equalsIgnoreCase("pretty")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[9]++;
        prettyMode = true;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[16]++;

      } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[10]++;}

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[8]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[17]++;
int CodeCoverConditionCoverageHelper_C6;

    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[11]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[18]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[1]++;


int CodeCoverConditionCoverageHelper_C7;
      while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((st.hasMoreTokens()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[1]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[2]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[3]++;
}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[19]++;
        String types = st.nextToken();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[20]++;
        StringTokenizer typeSt = new StringTokenizer(types, ",");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[21]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[4]++;


int CodeCoverConditionCoverageHelper_C8;
        while ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((typeSt.hasMoreElements()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[4]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[5]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[6]++;
}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[22]++;
          String type = typeSt.nextToken();
          writeMetrics(out, type, verboseMode, prettyMode);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[23]++;
        }
      }

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[12]++;
      // the default
      writeMetrics(out, "all", verboseMode, prettyMode);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[24]++;
    }
  }

  private static void writeMetrics(
      PrintStream out, String type, boolean verbose, boolean pretty) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;

    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((type.equals("gc")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type.equalsIgnoreCase("all")) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[13]++;
      writeGarbageCollectionStats(out, verbose, pretty);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[26]++;

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[14]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[27]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((type.equals("mem")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type.equalsIgnoreCase("all")) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[15]++;
      writeMemoryMetrics(out, verbose, pretty);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[28]++;

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[16]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[29]++;
int CodeCoverConditionCoverageHelper_C11;
    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((type.equals("jit")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type.equalsIgnoreCase("all")) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[17]++;
      writeJitMetrics(out, verbose, pretty);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[30]++;

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[18]++;}
  }

  private static void writeJitMetrics(
      PrintStream out, boolean verbose, boolean pretty) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[31]++;

    CompilationMXBean cBean = ManagementFactory.getCompilationMXBean();

    String name;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[32]++;
int CodeCoverConditionCoverageHelper_C12;
    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((verbose) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[19]++;
      name = cBean.getName();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[33]++;

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[20]++;
      name = "total";
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[34]++;
    }
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[35]++;
int CodeCoverConditionCoverageHelper_C13;

    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[21]++;
      out.println("\nJIT Stats");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[36]++;
      out.println(String.format(
          "\t%s jit time: %d ms", name, cBean.getTotalCompilationTime()));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[37]++;

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[22]++;
      out.println(normalizeTabularColonPos(String.format("%s-jit-time-ms : %d",
          normalizeName(name), cBean.getTotalCompilationTime())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[38]++;
    }
  }

  private static void writeOverallMemoryUsage(
      PrintStream out, MemoryUsage usage, String prefix, boolean pretty) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[23]++;
      out.format("\t%s\n", prefix);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[40]++;
      out.format("\t\tavailable         : %s\n", formatBytes(usage.getMax()));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[41]++;
      out.format("\t\tcurrent           : %s\n", formatBytes(usage.getUsed()));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[42]++;

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[24]++;
      prefix = normalizeName(prefix);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[43]++;
      out.println(normalizeTabularColonPos(
          String.format(prefix + "-available-bytes : %d", usage.getMax())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[44]++;
      out.println(normalizeTabularColonPos(
          String.format(prefix + "-current-bytes : %d", usage.getUsed())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[45]++;
    }
  }

  private static void writePoolMemoryUsage(PrintStream out, MemoryUsage usage,
      MemoryUsage peakUsage, String prefix, boolean pretty) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[46]++;
int CodeCoverConditionCoverageHelper_C15;
    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[25]++;
      out.format("\t\tavailable         : %s\n",
          formatBytes(usage.getMax()));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[47]++;
      out.format("\t\tpeak              : %s\n",
          formatBytes(peakUsage.getUsed()));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[48]++;
      out.format("\t\tcurrent           : %s\n",
          formatBytes(usage.getUsed()));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[49]++;

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[26]++;
      out.println(normalizeTabularColonPos(
          String.format(prefix + "-available-bytes : %d", usage.getMax())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[50]++;
      out.println(normalizeTabularColonPos(
          String.format(prefix + "-peak-bytes : %d", peakUsage.getUsed())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[51]++;
      out.println(normalizeTabularColonPos(
          String.format(prefix + "-current-bytes : %d",     usage.getUsed())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[52]++;
    }
  }

  private static void writeMemoryMetrics(
      PrintStream out, boolean verbose, boolean pretty) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[53]++;
int CodeCoverConditionCoverageHelper_C16;
    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[27]++;
      out.println("\nMemory usage");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[54]++;

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[28]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;

    // only show overall stats in verbose mode
    if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((verbose) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[29]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[56]++;
      MemoryMXBean overallMemBean = ManagementFactory.getMemoryMXBean();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[57]++;
      MemoryUsage usage = overallMemBean.getHeapMemoryUsage();
      writeOverallMemoryUsage(out, usage, "Heap", pretty);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[58]++;

      usage = overallMemBean.getNonHeapMemoryUsage();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[59]++;
      writeOverallMemoryUsage(out, usage, "Non-heap", pretty);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[60]++;

    } else {
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[30]++;}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[61]++;
int CodeCoverConditionCoverageHelper_C18;

    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((verbose) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[31]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[62]++;
      List<MemoryPoolMXBean> mpBeans = ManagementFactory.getMemoryPoolMXBeans();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[63]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[7]++;


      for (MemoryPoolMXBean mpBean : mpBeans) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[7]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[8]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[9]++;
}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[64]++;
        MemoryUsage currentUsage = mpBean.getUsage();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[65]++;
        MemoryUsage peakUsage = mpBean.getPeakUsage();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[66]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[33]++;
          out.println("\tPool " + mpBean.getName());
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[67]++;
          writePoolMemoryUsage(out, currentUsage, peakUsage, null, true);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[68]++;

        } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[34]++;
          writePoolMemoryUsage(out, currentUsage, peakUsage,
              "mem-pool-" + normalizeName(mpBean.getName()), false);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[69]++;
        }
      }

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[32]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[70]++;
      long available = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[71]++;
      long current = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[72]++;
      long peak = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[73]++;
      List<MemoryPoolMXBean> mpBeans = ManagementFactory.getMemoryPoolMXBeans();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[74]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[10]++;


      for (MemoryPoolMXBean mpBean : mpBeans) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[10]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[11]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[12]++;
}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[75]++;
        MemoryUsage currentUsage = mpBean.getUsage();
        available += currentUsage.getMax();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[76]++;
        current += currentUsage.getUsed();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[77]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[78]++;
        MemoryUsage peakUsage = mpBean.getPeakUsage();
        peak += peakUsage.getUsed();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[79]++;
      }
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[80]++;
      MemoryUsage summaryUsage = new MemoryUsage(
          0, current, current, available);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[81]++;
      MemoryUsage summaryPeakUsage = new MemoryUsage(0, peak, peak, peak);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[82]++;
int CodeCoverConditionCoverageHelper_C20;
      if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[35]++;
        out.format("\tAggregate of %d memory pools\n", mpBeans.size());
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[83]++;
        writePoolMemoryUsage(out, summaryUsage, summaryPeakUsage, null, true);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[84]++;

      } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[36]++;
        writePoolMemoryUsage(out, summaryUsage, summaryPeakUsage, "mem", false);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[85]++;
      }
    }
  }

  private static void writeGarbageCollectionStats(
      PrintStream out, boolean verbose, boolean pretty) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[86]++;
    List<GarbageCollectorMXBean> gcBeans =
        ManagementFactory.getGarbageCollectorMXBeans();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;

    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((verbose) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[37]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[88]++;
int CodeCoverConditionCoverageHelper_C22;
      if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[39]++;
        out.println("\nGarbage collection stats");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[89]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[90]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[13]++;


        for (GarbageCollectorMXBean gcBean : gcBeans) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[13]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[14]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[15]++;
}
          out.println("\tCollector " + gcBean.getName());
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[91]++;
          out.format(
              "\t\tcollection count   : %d\n", gcBean.getCollectionCount());
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[92]++;
          out.format(
              "\t\tcollection time    : %d ms\n", gcBean.getCollectionTime());
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[93]++;
        }

      } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[40]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[94]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[16]++;


        for (GarbageCollectorMXBean gcBean : gcBeans) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[16]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[17]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[18]++;
}
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[95]++;
          String name = normalizeName(gcBean.getName());
          out.println(normalizeTabularColonPos(String.format("gc-" + name
              + "-collection-count : %d", gcBean.getCollectionCount())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[96]++;
          out.println(normalizeTabularColonPos(String.format("gc-" + name
              + "-collection-time-ms : %d", gcBean.getCollectionTime())));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[97]++;
        }
      }

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[38]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[98]++;
      long collectionCount = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[99]++;
      long collectionTime = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[100]++;
      int collectorCount = gcBeans.size();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[101]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[19]++;


      for (GarbageCollectorMXBean gcBean : gcBeans) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[19]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[20]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[21]++;
}
        collectionCount += gcBean.getCollectionCount();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[102]++;
        collectionTime += gcBean.getCollectionTime();
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[103]++;
      }
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[104]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((pretty) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[41]++;
        out.println("\nGarbage collection stats");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[105]++;
        out.format("\tAggregate of %d collectors\n", collectorCount);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[106]++;
        out.format("\t\tcollection count   : %d\n", collectionCount);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[107]++;
        out.format("\t\tcollection time    : %d ms\n", collectionTime);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[108]++;

      } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[42]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[109]++;
        String name = normalizeName("aggregate");
        out.println(normalizeTabularColonPos(
            String.format("gc-" + name + "-collection-count : %d",
            collectionCount)));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[110]++;
        out.println(normalizeTabularColonPos(
            String.format("gc-" + name + "-collection-time-ms : %d",
            collectionTime)));
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[111]++;
      }
    }
  }

  private static String normalizeName(String name) {
    return name.replace(" ", "_").toLowerCase();
  }

  private static String normalizeTabularColonPos(String string) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[112]++;
    StringBuilder sb = new StringBuilder(string);
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[113]++;
    int index = sb.indexOf(":");
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[114]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[22]++;


int CodeCoverConditionCoverageHelper_C24;
    for (;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((index < TABULAR_COLON_POS) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ++index) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[22]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[23]--;
  CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.loops[24]++;
}
      sb.insert(index, ' ');
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[115]++;
    }
    return sb.toString();
  }

  private static String formatBytes(long numBytes) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[116]++;
int CodeCoverConditionCoverageHelper_C25;
    if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((numBytes < ONE_KILO_BYTE) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[43]++;
      return String.format("%d B", numBytes);

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[44]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[117]++;
int CodeCoverConditionCoverageHelper_C26; if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((numBytes < ONE_MEGA_BYTE) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[45]++;
      return String.format("%d KB", numBytes / ONE_KILO_BYTE);

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[46]++;
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.statements[118]++;
int CodeCoverConditionCoverageHelper_C27; if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((numBytes < ONE_GIGA_BYTE) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[47]++;
      return String.format("%d MB", numBytes / ONE_MEGA_BYTE);

    } else {
CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd.branches[48]++;
      return String.format("%d GB", numBytes / ONE_GIGA_BYTE);
    }
}
}
  }
}

class CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd ());
  }
    public static long[] statements = new long[119];
    public static long[] branches = new long[49];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[28];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.JvmMetrics.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 27; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[25];

  public CodeCoverCoverageCounter$mbfaisxdkulpxv26cbnvvxd () {
    super("com.google.javascript.jscomp.JvmMetrics.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 118; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 48; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 24; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.JvmMetrics.java");
      for (int i = 1; i <= 118; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 48; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 27; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 8; i++) {
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

