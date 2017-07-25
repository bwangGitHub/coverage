/*
 * Copyright 2007 The Closure Compiler Authors.
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

import com.google.common.collect.Sets;
import com.google.javascript.jscomp.CheckLevel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

/**
 * <p>A basic error manager that sorts all errors and warnings reported to it to
 * generate a sorted report when the {@link #generateReport()} method
 * is called.</p>
 *
 * <p>This error manager does not produce any output, but subclasses can
 * override the {@link #println(CheckLevel, JSError)} method to generate custom
 * output.</p>
 *
 */
public abstract class BasicErrorManager implements ErrorManager {
  static {
    CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.ping();
  }

  private final SortedSet<ErrorWithLevel> messages =
      Sets.newTreeSet(new LeveledJSErrorComparator());
  {
    CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[1]++;
  }
  private int errorCount = 0;
  {
    CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[2]++;
  }
  private int warningCount = 0;
  {
    CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[3]++;
  }
  private double typedPercent = 0.0;
  {
    CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[4]++;
  }

  @Override
  public void report(CheckLevel level, JSError error) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((messages.add(new ErrorWithLevel(error, level))) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[1]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[6]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((level == CheckLevel.ERROR) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[3]++;
        errorCount++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[7]++;

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[4]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[8]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((level == CheckLevel.WARNING) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[5]++;
        warningCount++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[9]++;

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[6]++;}
}

    } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[2]++;}
  }

  @Override
  public void generateReport() {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[10]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[1]++;


    for (ErrorWithLevel message : messages) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[1]--;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[2]--;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[3]++;
}
      println(message.level, message.error);
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[11]++;
    }
    printSummary();
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[12]++;
  }

  /**
   * Print a message with a trailing new line. This method is called by the
   * {@link #generateReport()} method when generating messages.
   */
  public abstract void println(CheckLevel level, JSError error);

  /**
   * Print the summary of the compilation - number of errors and warnings.
   */
  protected abstract void printSummary();

  @Override
  public int getErrorCount() {
    return errorCount;
  }

  @Override
  public int getWarningCount() {
    return warningCount;
  }

  @Override
  public JSError[] getErrors() {
    return toArray(CheckLevel.ERROR);
  }

  @Override
  public JSError[] getWarnings() {
    return toArray(CheckLevel.WARNING);
  }

  @Override
  public void setTypedPercent(double typedPercent) {
    this.typedPercent = typedPercent;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[13]++;
  }

  @Override
  public double getTypedPercent() {
    return typedPercent;
  }

  private JSError[] toArray(CheckLevel level) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[14]++;
    List<JSError> errors = new ArrayList<JSError>(messages.size());
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[15]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[4]++;


    for (ErrorWithLevel p : messages) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[4]--;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[5]--;
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.loops[6]++;
}
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((p.level == level) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[7]++;
        errors.add(p.error);
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[17]++;

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[8]++;}
    }
    return errors.toArray(new JSError[errors.size()]);
  }

  /**
   * <p>Comparator of {@link JSError} with an associated {@link CheckLevel}.
   * The ordering is the standard lexical ordering on the quintuple
   * (file name, line number, {@link CheckLevel},
   * character number, description).</p>
   *
   * <p>Note: this comparator imposes orderings that are inconsistent with
   * {@link JSError#equals(Object)}.</p>
   */
  static final class LeveledJSErrorComparator
      implements Comparator<ErrorWithLevel> {
    private static final int P1_LT_P2 = -1;
    private static final int P1_GT_P2 = 1;

    @Override
    public int compare(ErrorWithLevel p1, ErrorWithLevel p2) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
      // null is the smallest value
      if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((p2 == null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[9]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[19]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((p1 == null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[11]++;
          return 0;

        } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[12]++;
          return P1_GT_P2;
        }

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[10]++;}
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;

      // check level
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((p1.level != p2.level) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[13]++;
        return p2.level.compareTo(p1.level);

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[14]++;}
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[21]++;

      // sourceName comparison
      String source1 = p1.error.sourceName;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[22]++;
      String source2 = p2.error.sourceName;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((source1 != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((source2 != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[15]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[24]++;
        int sourceCompare = source1.compareTo(source2);
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[25]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sourceCompare != 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[17]++;
          return sourceCompare;

        } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[18]++;}

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[16]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[26]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((source1 == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((source2 != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[19]++;
        return P1_LT_P2;

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[20]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[27]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((source1 != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((source2 == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[21]++;
        return P1_GT_P2;

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[22]++;}
}
}
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[28]++;
      // lineno comparison
      int lineno1 = p1.error.lineNumber;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[29]++;
      int lineno2 = p2.error.lineNumber;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[30]++;
int CodeCoverConditionCoverageHelper_C12;
      if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((lineno1 != lineno2) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[23]++;
        return lineno1 - lineno2;

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[24]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[31]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((lineno1 < 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((0 <= lineno2) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[25]++;
        return P1_LT_P2;

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[26]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[32]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((0 <= lineno1) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((lineno2 < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[27]++;
        return P1_GT_P2;

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[28]++;}
}
}
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[33]++;
      // charno comparison
      int charno1 = p1.error.getCharno();
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[34]++;
      int charno2 = p2.error.getCharno();
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[35]++;
int CodeCoverConditionCoverageHelper_C15;
      if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((charno1 != charno2) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[29]++;
        return charno1 - charno2;

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[30]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[36]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((charno1 < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((0 <= charno2) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[31]++;
        return P1_LT_P2;

      } else {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[32]++;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[37]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((0 <= charno1) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((charno2 < 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) || true)) || (CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 2) && false)) {
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[33]++;
        return P1_GT_P2;

      } else {
  CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.branches[34]++;}
}
}
      // description
      return p1.error.description.compareTo(p2.error.description);
    }
  }

  static class ErrorWithLevel {
    final JSError error;
    final CheckLevel level;

    ErrorWithLevel(JSError error, CheckLevel level) {
      this.error = error;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[38]++;
      this.level = level;
CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip.statements[39]++;
    }
  }
}

class CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip ());
  }
    public static long[] statements = new long[40];
    public static long[] branches = new long[35];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[18];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.BasicErrorManager.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,2,1,2,2,1,2,2,1,2,2};
    for (int i = 1; i <= 17; i++) {
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

  public CodeCoverCoverageCounter$aw3qavgqar4ainj1nuoi2wy99h01mgzgip () {
    super("com.google.javascript.jscomp.BasicErrorManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 39; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 34; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 17; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.BasicErrorManager.java");
      for (int i = 1; i <= 39; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 34; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 17; i++) {
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

