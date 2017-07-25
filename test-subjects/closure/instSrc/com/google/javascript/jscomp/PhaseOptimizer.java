/*
 * Copyright 2009 The Closure Compiler Authors.
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

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.javascript.rhino.Node;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Optimizes the order of compiler passes.
 * @author nicksantos@google.com (Nick Santos)
 */
class PhaseOptimizer implements CompilerPass {
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.ping();
  }


  // This ordering is computed offline by running with compute_phase_ordering.
  @VisibleForTesting
  static final List<String> OPTIMAL_ORDER = ImmutableList.of(
     "deadAssignmentsElimination",
     "inlineFunctions",
     "removeUnusedPrototypeProperties",
     "removeUnreachableCode",
     "removeUnusedVars",
     "minimizeExitPoints",
     "inlineVariables",
     "collapseObjectLiterals",
     "peepholeOptimizations");
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[1]++;
  }

  static final int MAX_LOOPS = 100;
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[2]++;
  }
  static final String OPTIMIZE_LOOP_ERROR =
      "Fixed point loop exceeded the maximum number of iterations.";
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[3]++;
  }

  // Only used by Loop/process, but enum types can't be local
  enum State {
    RUN_PASSES_NOT_RUN_IN_PREV_ITER,
    RUN_PASSES_THAT_CHANGED_STH_IN_PREV_ITER
  }

  private static final Logger logger =
      Logger.getLogger(PhaseOptimizer.class.getName());
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[4]++;
  }

  private final List<CompilerPass> passes = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[5]++;
  }

  private final AbstractCompiler compiler;
  private final PerformanceTracker tracker;
  private final CodeChangeHandler.RecentChange recentChange =
      new CodeChangeHandler.RecentChange();
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[6]++;
  }
  private boolean loopMutex = false;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[7]++;
  }
  private Tracer currentTracer = null;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[8]++;
  }
  private String currentPassName = null;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[9]++;
  }
  private PassFactory sanityCheck = null;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[10]++;
  }
  private boolean printAstHashcodes = false;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[11]++;
  }

  private double progress = 0.0;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[12]++;
  }
  private double progressStep = 0.0;
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[13]++;
  }

  // The following static properties are only used for computing optimal
  // phase orderings. They should not be touched by normal compiler runs.
  private static boolean randomizeLoops = false;
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[14]++;
  }
  private static List<List<String>> loopsRun = Lists.newArrayList();
  static {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[15]++;
  }

  private final ProgressRange progressRange;

  /**
   * @param compiler the compiler that owns/creates this.
   * @param tracker an optional performance tracker
   * @param progressRange the progress range for the process function or null
   * if progress should not be reported.
   */
  PhaseOptimizer(AbstractCompiler compiler, PerformanceTracker tracker,
      ProgressRange progressRange) {
    this.compiler = compiler;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[16]++;
    this.tracker = tracker;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[17]++;
    this.progressRange = progressRange;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[18]++;
    compiler.addChangeHandler(recentChange);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[19]++;
  }

  /**
   * Randomizes loops. This should only be used when computing optimal phase
   * orderings.
   */
  static void randomizeLoops() {
    randomizeLoops = true;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[20]++;
  }

  /**
   * Get the phase ordering of loops during this run.
   * Returns an empty list when the loops are not randomized.
   */
  static List<List<String>> getLoopsRun() {
    return loopsRun;
  }

  /**
   * Clears the phase ordering of loops during this run.
   */
  static void clearLoopsRun() {
    loopsRun.clear();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[21]++;
  }

  /**
   * Add the passes generated by the given factories to the compile sequence.
   *
   * Automatically pulls multi-run passes into fixed point loops. If there
   * are 1 or more multi-run passes in a row, they will run together in
   * the same fixed point loop. The passes will run until they are finished
   * making changes.
   *
   * The PhaseOptimizer is free to tweak the order and frequency of multi-run
   * passes in a fixed-point loop.
   */
  void consume(List<PassFactory> factories) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[22]++;
    Loop currentLoop = new Loop();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[23]++;
    boolean isCurrentLoopPopulated = false;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[1]++;


    for (PassFactory factory : factories) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[1]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[2]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[3]++;
}
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[25]++;
int CodeCoverConditionCoverageHelper_C1;
      if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((factory.isOneTimePass()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[1]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[26]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((isCurrentLoopPopulated) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[3]++;
          passes.add(currentLoop);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[27]++;
          currentLoop = new Loop();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[28]++;
          isCurrentLoopPopulated = false;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[29]++;

        } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[4]++;}
        addOneTimePass(factory);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[30]++;

      } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[2]++;
        currentLoop.addLoopedPass(factory);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[31]++;
        isCurrentLoopPopulated = true;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[32]++;
      }
    }
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[33]++;
int CodeCoverConditionCoverageHelper_C3;

    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((isCurrentLoopPopulated) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[5]++;
      passes.add(currentLoop);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[34]++;

    } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[6]++;}
  }

  /**
   * Add the pass generated by the given factory to the compile sequence.
   * This pass will be run once.
   */
  void addOneTimePass(PassFactory factory) {
    passes.add(new NamedPass(factory));
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[35]++;
  }

  /**
   * Add a loop to the compile sequence. This loop will continue running
   * until the AST stops changing.
   * @return The loop structure. Pass suppliers should be added to the loop.
   */
  Loop addFixedPointLoop() {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[36]++;
    Loop loop = new Loop();
    passes.add(loop);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[37]++;
    return loop;
  }

  /**
   * Adds a sanity checker to be run after every pass. Intended for development.
   */
  void setSanityCheck(PassFactory sanityCheck) {
    this.sanityCheck = sanityCheck;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[38]++;
  }

  /**
   * Sets the hashcode of the AST to be logged every pass. Intended for development.
   */
  void setPrintAstHashcodes(boolean printAstHashcodes) {
    this.printAstHashcodes = printAstHashcodes;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[39]++;
  }

  /**
   * Run all the passes in the optimizer.
   */
  @Override
  public void process(Node externs, Node root) {
    progress = 0.0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[40]++;
    progressStep = 0.0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[41]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[42]++;
int CodeCoverConditionCoverageHelper_C4;
    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((progressRange != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[7]++;
      progressStep = (progressRange.maxValue - progressRange.initialValue)
          / passes.size();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[43]++;
      progress = progressRange.initialValue;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[44]++;

    } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[8]++;}
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[45]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[4]++;



    for (CompilerPass pass : passes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[4]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[5]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[6]++;
}
      pass.process(externs, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[46]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[47]++;
int CodeCoverConditionCoverageHelper_C5;
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((hasHaltingErrors()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[9]++;
        return;

      } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[10]++;}
    }
  }

  /**
   * Marks the beginning of a pass.
   */
  private void startPass(String passName, boolean isOneTime) {
    Preconditions.checkState(currentTracer == null && currentPassName == null);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[48]++;
    currentPassName = passName;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[49]++;
    currentTracer = newTracer(passName, isOneTime);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[50]++;
  }

  /**
   * Marks the end of a pass.
   */
  private void endPass(Node externs, Node root) {
    Preconditions.checkState(currentTracer != null && currentPassName != null);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[51]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[52]++;

    String passToCheck = currentPassName;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[53]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
    try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[54]++;
int CodeCoverConditionCoverageHelper_C6;
      if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((progressRange == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[12]++;
        compiler.setProgress(-1, currentPassName);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[55]++;

      } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[13]++;
        progress += progressStep;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[56]++;
        compiler.setProgress(progress, currentPassName);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[57]++;
      }
      stopTracer(currentTracer, currentPassName);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[58]++;
      currentPassName = null;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[59]++;
      currentTracer = null;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[60]++;

      maybePrintAstHashcodes(passToCheck, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[61]++;
      maybeSanityCheck(externs, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[62]++;
    } catch (Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[14]++;
      // TODO(johnlenz): Remove this once the normalization checks report
      // errors instead of exceptions.
      throw new RuntimeException("Sanity check failed for " + passToCheck, e);
    } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[11]++;
}
  }
  }

  private void maybePrintAstHashcodes(String passName, Node root) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[63]++;
int CodeCoverConditionCoverageHelper_C7;
    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((printAstHashcodes) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[15]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[64]++;
      String hashCodeMsg = "AST hashCode after " + passName + ": " +
          compiler.toSource(root).hashCode();
      System.err.println(hashCodeMsg);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[65]++;
      compiler.addToDebugLog(hashCodeMsg);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[66]++;

    } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[16]++;}
  }

  /**
   * Runs the sanity check if it is available.
   */
  void maybeSanityCheck(Node externs, Node root) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[67]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((sanityCheck != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[17]++;
      sanityCheck.create(compiler).process(externs, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[68]++;

    } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[18]++;}
  }

  private boolean hasHaltingErrors() {
    return compiler.hasHaltingErrors();
  }

  /**
   * Returns a new tracer for the given pass name.
   */
  private Tracer newTracer(String passName, boolean isOneTime) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[69]++;
    String comment = passName +
        (recentChange.hasCodeChanged() ? " on recently changed AST" : "");
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[70]++;
int CodeCoverConditionCoverageHelper_C9;
    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((tracker != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[19]++;
      tracker.recordPassStart(passName, isOneTime);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[71]++;

    } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[20]++;}
    return new Tracer("JSCompiler", comment);
  }

  private void stopTracer(Tracer t, String passName) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[72]++;
    long result = t.stop();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[73]++;
int CodeCoverConditionCoverageHelper_C10;
    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((tracker != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[21]++;
      tracker.recordPassStop(passName, result);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[74]++;

    } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[22]++;}
  }

  /**
   * A single compiler pass.
   */
  class NamedPass implements CompilerPass {
    final String name;
    private final PassFactory factory;

    NamedPass(PassFactory factory) {
      this.name = factory.getName();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[75]++;
      this.factory = factory;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[76]++;
    }

    @Override
    public void process(Node externs, Node root) {
      logger.fine(name);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[77]++;
      startPass(name, factory.isOneTimePass());
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[78]++;
      // Delay the creation of the actual pass until *after* all previous passes
      // have been processed.
      // Some precondition checks rely on this, eg, in CoalesceVariableNames.
      factory.create(compiler).process(externs, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[79]++;
      endPass(externs, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[80]++;
    }
  }

  /**
   * Runs a set of compiler passes until they reach a fixed point.
   *
   * Notice that this is a non-static class, because it includes the closure
   * of PhaseOptimizer.
   */
  class Loop implements CompilerPass {
    private final List<NamedPass> myPasses = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[81]++;
  }
    private final Set<String> myNames = Sets.newHashSet();
  {
    CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[82]++;
  }

    void addLoopedPass(PassFactory factory) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[83]++;
      String name = factory.getName();
      Preconditions.checkArgument(!myNames.contains(name),
          "Already a pass with name '%s' in this loop", name);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[84]++;
      myNames.add(name);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[85]++;
      myPasses.add(new NamedPass(factory));
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[86]++;
    }

    @Override
    public void process(Node externs, Node root) {
      Preconditions.checkState(!loopMutex, "Nested loops are forbidden");
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[87]++;
      loopMutex = true;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[88]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[89]++;
int CodeCoverConditionCoverageHelper_C11;
      if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((randomizeLoops) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[23]++;
        randomizePasses();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[90]++;

      } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[24]++;
        optimizePasses();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[91]++;
      }
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[92]++;

      // Contains a pass iff it made changes the last time it was run.
      Set<NamedPass> madeChanges = new HashSet<NamedPass>();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[93]++;
      // Contains a pass iff it was run during the last inner loop.
      Set<NamedPass> runInPrevIter = new HashSet<NamedPass>();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[94]++;
      State s = State.RUN_PASSES_NOT_RUN_IN_PREV_ITER;
      boolean lastIterMadeChanges;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[95]++;
      int count = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[96]++;
boolean CodeCoverTryBranchHelper_Try2 = false;

      try {
CodeCoverTryBranchHelper_Try2 = true;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[97]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[7]++;


        while (true) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[7]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[8]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[9]++;
}
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[98]++;
int CodeCoverConditionCoverageHelper_C13;
          if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((count++ > MAX_LOOPS) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[26]++;
            compiler.throwInternalError(OPTIMIZE_LOOP_ERROR, null);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[99]++;

          } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[27]++;}
          lastIterMadeChanges = false;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[100]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[101]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[10]++;


          for (NamedPass pass : myPasses) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[10]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[11]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[12]++;
}
            recentChange.reset();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[102]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[103]++;
int CodeCoverConditionCoverageHelper_C14;
            if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C14 |= (128)) == 0 || true) &&
 ((s == State.RUN_PASSES_NOT_RUN_IN_PREV_ITER) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((runInPrevIter.contains(pass)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((s == State.RUN_PASSES_THAT_CHANGED_STH_IN_PREV_ITER) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((madeChanges.contains(pass)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 4) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[28]++;
              pass.process(externs, root);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[104]++;
              runInPrevIter.add(pass);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[105]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[106]++;
int CodeCoverConditionCoverageHelper_C15;
              if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((hasHaltingErrors()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[30]++;
                return;

              } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[31]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[107]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((recentChange.hasCodeChanged()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[32]++;
                madeChanges.add(pass);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[108]++;
                lastIterMadeChanges = true;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[109]++;

              } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[33]++;
                madeChanges.remove(pass);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[110]++;
              }
}

            } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[29]++;
              runInPrevIter.remove(pass);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[111]++;
            }
          }
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[112]++;
int CodeCoverConditionCoverageHelper_C17;
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((s == State.RUN_PASSES_NOT_RUN_IN_PREV_ITER) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[34]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[113]++;
int CodeCoverConditionCoverageHelper_C18;
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((lastIterMadeChanges) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[36]++;
              s = State.RUN_PASSES_THAT_CHANGED_STH_IN_PREV_ITER;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[114]++;

            } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[37]++;
              return;
            }

          } else {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[35]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[115]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((lastIterMadeChanges) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[38]++;
            s = State.RUN_PASSES_NOT_RUN_IN_PREV_ITER;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[116]++;

          } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[39]++;}
}
        }
      } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[25]++;
}
        loopMutex = false;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[117]++;
      }
    }

    /** Re-arrange the passes in a random order. */
    private void randomizePasses() {
      Collections.shuffle(myPasses);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[118]++;
    }

    /** Re-arrange the passes in an optimal order. */
    private void optimizePasses() {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[119]++;
      // It's important that this ordering is deterministic, so that
      // multiple compiles with the same input produce exactly the same
      // results.
      //
      // To do this, grab any passes we recognize, and move them to the end
      // in an "optimal" order.
      List<NamedPass> optimalPasses = Lists.newArrayList();
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[120]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[13]++;


      for (String passName : OPTIMAL_ORDER) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[13]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[14]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[15]++;
}
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[121]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[16]++;


        for (NamedPass pass : myPasses) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[16]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[17]--;
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.loops[18]++;
}
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[122]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((pass.name.equals(passName)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[40]++;
            optimalPasses.add(pass);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[123]++;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[124]++;
            break;

          } else {
  CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.branches[41]++;}
        }
      }

      myPasses.removeAll(optimalPasses);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[125]++;
      myPasses.addAll(optimalPasses);
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[126]++;
    }
  }

  static class ProgressRange {
    public final double initialValue;
    public final double maxValue;

    public ProgressRange(double initialValue, double maxValue) {
      this.initialValue = initialValue;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[127]++;
      this.maxValue = maxValue;
CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd.statements[128]++;
    }
  }
}

class CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd ());
  }
    public static long[] statements = new long[129];
    public static long[] branches = new long[42];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.PhaseOptimizer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,0,1,3,1,1,1,1,1,1};
    for (int i = 1; i <= 20; i++) {
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

  public CodeCoverCoverageCounter$1bjsbstzy6gpz998579q71ciz2q7hd () {
    super("com.google.javascript.jscomp.PhaseOptimizer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 128; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 41; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.PhaseOptimizer.java");
      for (int i = 1; i <= 128; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 41; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 20; i++) {
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

