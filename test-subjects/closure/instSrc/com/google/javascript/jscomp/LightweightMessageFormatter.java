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

import static com.google.javascript.jscomp.SourceExcerptProvider.SourceExcerpt.LINE;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.javascript.jscomp.CheckLevel;
import com.google.javascript.jscomp.SourceExcerptProvider.ExcerptFormatter;
import com.google.javascript.jscomp.SourceExcerptProvider.SourceExcerpt;

/**
 * Lightweight message formatter. The format of messages this formatter
 * produces is very compact and to the point.
 *
 */
public class LightweightMessageFormatter extends AbstractMessageFormatter {
  static {
    CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.ping();
  }

  private SourceExcerpt excerpt;
  private static final ExcerptFormatter excerptFormatter =
      new LineNumberingFormatter();
  static {
    CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[1]++;
  }

  /**
   * A constructor for when the client doesn't care about source information.
   */
  private LightweightMessageFormatter() {
    super(null);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[2]++;
    this.excerpt = LINE;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[3]++;
  }

  public LightweightMessageFormatter(SourceExcerptProvider source) {
    this(source, LINE);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[4]++;
  }

  public LightweightMessageFormatter(SourceExcerptProvider source,
      SourceExcerpt excerpt) {
    super(source);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[5]++;
    Preconditions.checkNotNull(source);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[6]++;
    this.excerpt = excerpt;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[7]++;
  }

  static LightweightMessageFormatter withoutSource() {
    return new LightweightMessageFormatter();
  }

  @Override
  public String formatError(JSError error) {
    return format(error, false);
  }

  @Override
  public String formatWarning(JSError warning) {
    return format(warning, true);
  }

  private String format(JSError error, boolean warning) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[8]++;
    // extract source excerpt
    SourceExcerptProvider source = getSource();
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[9]++;
    String sourceExcerpt = source == null ? null :
        excerpt.get(
            source, error.sourceName, error.lineNumber, excerptFormatter);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[10]++;

    // formatting the message
    StringBuilder b = new StringBuilder();
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((error.sourceName != null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[1]++;
      b.append(error.sourceName);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[12]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((error.lineNumber > 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[3]++;
        b.append(':');
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[14]++;
        b.append(error.lineNumber);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[15]++;

      } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[4]++;}
      b.append(": ");
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[16]++;

    } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[2]++;}

    b.append(getLevelName(warning ? CheckLevel.WARNING : CheckLevel.ERROR));
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[17]++;
    b.append(" - ");
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[18]++;

    b.append(error.description);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[19]++;
    b.append('\n');
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[20]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[21]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((sourceExcerpt != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[5]++;
      b.append(sourceExcerpt);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[22]++;
      b.append('\n');
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[23]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[24]++;
      int charno = error.getCharno();
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[25]++;
int CodeCoverConditionCoverageHelper_C4;

      // padding equal to the excerpt and arrow at the end
      // charno == sourceExpert.length() means something is missing
      // at the end of the line
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((excerpt.equals(LINE)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((0 <= charno) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((charno <= sourceExcerpt.length()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[7]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[26]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < charno) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[1]--;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[2]--;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[3]++;
}
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[27]++;
          char c = sourceExcerpt.charAt(i);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
          if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((Character.isWhitespace(c)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[9]++;
            b.append(c);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[29]++;

          } else {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[10]++;
            b.append(' ');
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[30]++;
          }
        }
        b.append("^\n");
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[31]++;

      } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[8]++;}

    } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[6]++;}
    return b.toString();
  }

  /**
   * Formats a region by appending line numbers in front, e.g.
   * <pre>   9| if (foo) {
   *   10|   alert('bar');
   *   11| }</pre>
   * and return line excerpt without any modification.
   */
  static class LineNumberingFormatter implements ExcerptFormatter {
    @Override
    public String formatLine(String line, int lineNumber) {
      return line;
    }

    @Override
    public String formatRegion(Region region) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[32]++;
int CodeCoverConditionCoverageHelper_C7;
      if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((region == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[11]++;
        return null;

      } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[12]++;}
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[33]++;
      String code = region.getSourceExcerpt();
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[34]++;
int CodeCoverConditionCoverageHelper_C8;
      if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((code.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[13]++;
        return null;

      } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[14]++;}
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[35]++;

      // max length of the number display
      int numberLength = Integer.toString(region.getEndingLineNumber())
          .length();
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[36]++;

      // formatting
      StringBuilder builder = new StringBuilder(code.length() * 2);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[37]++;
      int start = 0;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[38]++;
      int end = code.indexOf('\n', start);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[39]++;
      int lineNumber = region.getBeginningLineNumber();
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[40]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[4]++;


int CodeCoverConditionCoverageHelper_C9;
      while ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((start >= 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[4]--;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[5]--;
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.loops[6]++;
}
        // line extraction
        String line;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[41]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[15]++;
          line = code.substring(start);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[42]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[43]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((line.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[17]++;
            return builder.substring(0, builder.length() - 1);

          } else {
  CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[18]++;}

        } else {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[16]++;
          line = code.substring(start, end);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[44]++;
        }
        builder.append("  ");
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[45]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[46]++;

        // nice spaces for the line number
        int spaces = numberLength - Integer.toString(lineNumber).length();
        builder.append(Strings.repeat(" ", spaces));
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[47]++;
        builder.append(lineNumber);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[48]++;
        builder.append("| ");
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[49]++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[50]++;
int CodeCoverConditionCoverageHelper_C12;

        // end & update
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[19]++;
          builder.append(line);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[51]++;
          start = -1;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[52]++;

        } else {
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.branches[20]++;
          builder.append(line);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[53]++;
          builder.append('\n');
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[54]++;
          start = end + 1;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[55]++;
          end = code.indexOf('\n', start);
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[56]++;
          lineNumber++;
CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29.statements[57]++;
        }
      }
      return builder.toString();
    }
  }
}

class CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29 ());
  }
    public static long[] statements = new long[58];
    public static long[] branches = new long[21];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.LightweightMessageFormatter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,3,1,1,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$1wk7f9yhlc1vfr5day69c3needhqbun6fcq1rtvbj1ydvuoh29 () {
    super("com.google.javascript.jscomp.LightweightMessageFormatter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 57; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 20; i++) {
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
    log.startNamedSection("com.google.javascript.jscomp.LightweightMessageFormatter.java");
      for (int i = 1; i <= 57; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 20; i++) {
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

