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

package com.google.javascript.jscomp.regex;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An AST for JavaScript regular expressions.
 *
 * @author Mike Samuel <mikesamuel@gmail.com>
 */
public abstract class RegExpTree {
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.ping();
  }


  /**
   * Returns a simpler regular expression that is semantically the same assuming
   * the given flags.
   * @param flags Regular expression flags, e.g. {@code "igm"}.
   */
  public abstract RegExpTree simplify(String flags);

  /**
   * True if the presence or absence of an {@code "i"} flag would change the
   * meaning of this regular expression.
   */
  public abstract boolean isCaseSensitive();

  /**
   * True if the regular expression contains an anchor : {@code ^} or {@code $}.
   */
  public abstract boolean containsAnchor();

  /**
   * True if the regular expression contains capturing groups.
   */
  public final boolean hasCapturingGroup() {
    return numCapturingGroups() != 0;
  }

  /**
   * The number of capturing groups.
   */
  public abstract int numCapturingGroups();

  /**
   * The children of this node.
   */
  public abstract List<? extends RegExpTree> children();

  /**
   * Appends this regular expression source to the given buffer.
   */
  protected abstract void appendSourceCode(StringBuilder sb);

  protected abstract void appendDebugInfo(StringBuilder sb);

  @Override
  public final String toString() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[1]++;
    StringBuilder sb = new StringBuilder();
    sb.append('/');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[2]++;
    appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[3]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
    // Don't emit a regular expression that looks like a line comment start.
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sb.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[1]++;
      sb.append("(?:)");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[5]++;

    } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[2]++;}
    sb.append('/');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[6]++;
    return sb.toString();
  }

  public final String toDebugString() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[7]++;
    StringBuilder sb = new StringBuilder();
    appendDebugString(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[8]++;
    return sb.toString();
  }

  private void appendDebugString(StringBuilder sb) {
    sb.append('(').append(getClass().getSimpleName());
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[9]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[10]++;
    int len = sb.length();
    sb.append(' ');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[11]++;
    appendDebugInfo(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[12]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[13]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((sb.length() == len + 1) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[3]++; sb.setLength(len);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[14]++;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[4]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[15]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[1]++;


    for (RegExpTree child : children()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[1]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[2]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[3]++;
}
      sb.append(' ');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[16]++;
      child.appendDebugString(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[17]++;
    }
    sb.append(')');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[18]++;
  }

  @Override
  public abstract boolean equals(Object o);

  @Override
  public abstract int hashCode();


  /**
   * Parses a regular expression to an AST.
   *
   * @param pattern The {@code foo} From {@code /foo/i}.
   * @param flags The {@code i} From {@code /foo/i}.
   */
  public static RegExpTree parseRegExp(
      final String pattern, final String flags) {

    /** A recursive descent parser that closes over pattern and flags above. */
    class Parser {
      /** The number of characters in pattern consumed. */
      int pos;
      /** The number of capturing groups seen so far. */
      int numCapturingGroups = 0;
  {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[19]++;
  }
      /** The length of pattern. */
      final int limit = pattern.length();
  {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[20]++;
  }

      RegExpTree parseTopLevel() {
        this.pos = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[21]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[22]++;
        RegExpTree out = parse();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[23]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[5]++;  // Unmatched closed paren maybe.
          throw new IllegalArgumentException(pattern.substring(pos));

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[6]++;}
        return out;
      }

      RegExpTree parse() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[24]++;
        // Collects ["foo", "bar", "baz"] for /foo|bar|baz/.
        ImmutableList.Builder<RegExpTree> alternatives = null;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[25]++;
        // The last item parsed within an alternation.
        RegExpTree preceder = null;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[26]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;

        topLoop:
        while ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[4]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[5]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[6]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[27]++;
          char ch = pattern.charAt(pos);
          RegExpTree atom;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[28]++;
          switch (ch) {
            case '[':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[7]++;
              atom = parseCharset();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[29]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[30]++;
              break;
            case '(':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[8]++;
              atom = parseParenthetical();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[31]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[32]++;
              break;
            case ')':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[9]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[33]++;
              break topLoop;
            case '\\':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[10]++;
              atom = parseEscape();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[34]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[35]++;
              break;
            case '^':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[11]++;
            case '$':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[12]++;
              atom = new Anchor(ch);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[36]++;
              ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[37]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[38]++;
              break;
            case '.':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[13]++;
              // We represent . as a character set to make it easy to simplify
              // things like /.|[\r\n]/.
              atom = DOT_CHARSET;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[39]++;
              ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[40]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[41]++;
              break;
            case '|':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[14]++;
              // An alternative may be empty as in /foo||bar/.
              // The '|' is consumed below.
              atom = Empty.INSTANCE;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[42]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[43]++;
              break;
            default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[15]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[44]++;
              // Find a run of concatenated characters to avoid building a
              // tree node per literal character.
              int start = pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[45]++;
              int end = pos + 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[46]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[7]++;


int CodeCoverConditionCoverageHelper_C5;
              charsLoop:
              while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((end < limit) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[7]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[8]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[9]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[47]++;
                switch (pattern.charAt(end)) {
                  case '[':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[16]++;
                  case '(':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[17]++;
                  case ')':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[18]++;
                  case '\\':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[19]++;
                  case '^':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[20]++;
                  case '$':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[21]++;
                  case '|':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[22]++;
                  case '.':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[23]++;
                  case '*':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[24]++;
                  case '+':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[25]++;
                  case '?':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[26]++;
                  case '{':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[27]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[48]++;
                    break charsLoop;
                  default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[28]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[49]++;
int CodeCoverConditionCoverageHelper_C6;
                    // Repetition binds more tightly than concatenation.
                    // Only consume up to "foo" in /foob*/ so that the suffix
                    // operator parser below has the right precedence.
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((end + 1 >= limit) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((isRepetitionStart(pattern.charAt(end + 1))) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[29]++;
                      ++end;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[50]++;

                    } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[30]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[51]++;
                      break charsLoop;
                    }
                }
              }
              atom = new Text(pattern.substring(start, end));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[52]++;
              pos = end;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[53]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[54]++;
              break;
          }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[55]++;
int CodeCoverConditionCoverageHelper_C7;
          if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isRepetitionStart(pattern.charAt(pos))) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[31]++;
            atom = parseRepetition(atom);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[56]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[32]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[57]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((preceder == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[33]++;
            preceder = atom;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[58]++;

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[34]++;
            preceder = new Concatenation(preceder, atom);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[59]++;
          }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[60]++;
int CodeCoverConditionCoverageHelper_C9;
          // If this is an alternative in a alternation, then add it to the
          // list of complete alternatives, and reset the parser state for the
          // next alternative.
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((pattern.charAt(pos) == '|') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[35]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[61]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((alternatives == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[37]++;
              alternatives = ImmutableList.builder();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[62]++;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[38]++;}
            alternatives.add(preceder);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[63]++;
            preceder = null;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[64]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[65]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[36]++;}
        }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[66]++;
int CodeCoverConditionCoverageHelper_C11;
        // An alternative may have no parsed content blank as in /foo|/.
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((preceder == null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[39]++; preceder = Empty.INSTANCE;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[67]++;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[40]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[68]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((alternatives != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[41]++;
          alternatives.add(preceder);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[69]++;
          return new Alternation(alternatives.build());

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[42]++;
          return preceder;
        }
      }

      /**
       * Handles capturing groups {@code (...)},
       * non-capturing groups {@code (?:...)}, and lookahead assertions
       * {@code (?=...)}.
       */
      private RegExpTree parseParenthetical() {
        Preconditions.checkState(pattern.charAt(pos) == '(');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[70]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[71]++;
        int start = pos;
        ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[72]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[73]++;
        boolean capturing = true;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[74]++;
        int type = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[75]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((pattern.charAt(pos) == '?') && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[43]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[76]++;
int CodeCoverConditionCoverageHelper_C14;
          if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((pos + 1 < limit) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[45]++;
            capturing = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[77]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[78]++;
            char ch = pattern.charAt(pos + 1);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[79]++;
            switch (ch) {
              case ':':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[47]++;  // A (?:...) style non-capturing group.
                pos += 2;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[80]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[81]++;
                break;
              case '!':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[48]++;  // A lookahead assertion
              case '=':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[49]++;
                pos += 2;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[82]++;
                type = ch;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[83]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[84]++;
                break;
              default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[50]++;
                throw new IllegalArgumentException(
                    "Malformed parenthetical: " + pattern.substring(start));
            }

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[46]++;}

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[44]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[85]++;
        RegExpTree body = parse();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[86]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pattern.charAt(pos) == ')') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[51]++;
          ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[87]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[52]++;
          throw new IllegalArgumentException(
              "Unclosed parenthetical group: " + pattern.substring(start));
        }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[88]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((capturing) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[53]++;
          ++numCapturingGroups;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[89]++;
          return new CapturingGroup(body);

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[54]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[90]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type != 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[55]++;
          return new LookaheadAssertion(body, type == '=');

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[56]++;
          return body;
        }
}
      }

      /**
       * Parses a square bracketed character set.
       * Standalone character groups (@code /\d/} are handled by
       * {@link #parseEscape}.
       */
      private RegExpTree parseCharset() {
        Preconditions.checkState(pattern.charAt(pos) == '[');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[91]++;
        ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[92]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[93]++;

        boolean isCaseInsensitive = flags.indexOf('i') >= 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[94]++;
        boolean inverse = pos < limit && pattern.charAt(pos) == '^';
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[95]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((inverse) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[57]++; ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[96]++;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[58]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[97]++;
        CharRanges ranges = CharRanges.EMPTY;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[98]++;
        CharRanges ieExplicits = CharRanges.EMPTY;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[99]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
        while ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((pattern.charAt(pos) != ']') && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 2) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[10]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[11]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[12]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[100]++;
          char ch = pattern.charAt(pos);
          char start;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[101]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((ch == '\\') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[59]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[102]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[103]++;
            char possibleGroupName = pattern.charAt(pos);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[104]++;
            CharRanges group = NAMED_CHAR_GROUPS.get(possibleGroupName);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[105]++;
int CodeCoverConditionCoverageHelper_C21;
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((group != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[61]++;
              ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[106]++;
              ranges = ranges.union(group);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[107]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[108]++;
              continue;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[62]++;}
            start = parseEscapeChar();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[109]++;

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[60]++;
            start = ch;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[110]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[111]++;
          }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[112]++;
          char end = start;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[113]++;
int CodeCoverConditionCoverageHelper_C22;
          if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (32)) == 0 || true) &&
 ((pos + 1 < limit) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (8)) == 0 || true) &&
 ((pattern.charAt(pos) == '-') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((pattern.charAt(pos + 1) != ']') && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 3) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[63]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[114]++;
            ch = pattern.charAt(pos);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[115]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[116]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((ch == '\\') && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[65]++;
              ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[117]++;
              end = parseEscapeChar();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[118]++;

            } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[66]++;
              end = ch;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[119]++;
              ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[120]++;
            }

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[64]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[121]++;
          CharRanges range = CharRanges.inclusive(start, end);
          ranges = ranges.union(range);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[122]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[123]++;
int CodeCoverConditionCoverageHelper_C24;
          if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (8)) == 0 || true) &&
 ((IE_SPEC_ERRORS.contains(start)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((IE_SPEC_ERRORS.contains(end)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[67]++;
            ieExplicits = ieExplicits.union(range.intersection(IE_SPEC_ERRORS));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[124]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[68]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[125]++;
int CodeCoverConditionCoverageHelper_C25;
          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((isCaseInsensitive) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[69]++;
            // If the flags contain the 'i' flag, then it is not correct to
            // say that [^a-z] contains the letter 'A', or that [a-z] does not
            // contain the letter 'A'.
            // We expand out letter groups here so that parse returns something
            // that is valid independent of flags.
            // Calls to simplify(flags) may later reintroduce flag assumptions.
            // but without this step, later steps might conflate
            //     /[a-z]/i
            // and
            //     /[^\0-`{-\uffff]/i
            // which matches nothing because the information about whether the
            // ^ is present has been lost during optimizations and charset
            // unionizing as in /[...]|[^...]/.
            ranges = CaseCanonicalize.expandToAllMatched(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[126]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[70]++;}
        }
        ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[127]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[128]++;
int CodeCoverConditionCoverageHelper_C26;  // Consume ']'

        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((inverse) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[71]++;
          ranges = CharRanges.ALL_CODE_UNITS.difference(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[129]++;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[72]++;}

        return new Charset(ranges, ieExplicits);
      }

      /**
       * Parses an escape to a code point.
       * Some of the characters parsed here have special meanings in various
       * contexts, so contexts must filter those instead.
       * E.g. '\b' means a different thing inside a charset than without.
       */
      private char parseEscapeChar() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[130]++;
        char ch = pattern.charAt(pos++);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[131]++;
        switch (ch) {
          case 'b':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[73]++; return '\b';
          case 'f':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[74]++; return '\f';
          case 'n':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[75]++; return '\n';
          case 'r':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[76]++; return '\r';
          case 't':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[77]++; return '\t';
          case 'u':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[78]++; return parseHex(4);
          case 'v':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[79]++; return '\u000b';
          case 'x':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[80]++; return parseHex(2);
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[81]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[132]++;
int CodeCoverConditionCoverageHelper_C27;
            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (8)) == 0 || true) &&
 (('0' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((ch <= '7') && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[82]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[133]++;
              char codeUnit = (char) (ch - '0');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[134]++;
              // Allow octal literals in the range \0-\377.
              // \41 might be a group, but \041 is not a group.
              // We read, but do not emit octal literals since they
              // are deprecated in ES5.
              int octLimit = Math.min(
                  limit, pos + (ch <= '3' ? 2 : 1) + (ch == '0' ? 1 : 0));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[135]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[13]++;


int CodeCoverConditionCoverageHelper_C28;
              while ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((pos < octLimit) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[13]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[14]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[15]++;
}
                ch = pattern.charAt(pos);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[136]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[137]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 (('0' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((ch <= '7') && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[84]++;
                  codeUnit = (char) ((codeUnit << 3) + (ch - '0'));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[138]++;
                  ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[139]++;

                } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[85]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[140]++;
                  break;
                }
              }
              return codeUnit;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[83]++;}
            return ch;
        }
      }

      /**
       * Parses an escape that appears outside a charset.
       */
      private RegExpTree parseEscape() {
        Preconditions.checkState(pattern.charAt(pos) == '\\');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[141]++;
        ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[142]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[143]++;
        char ch = pattern.charAt(pos);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[144]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((ch == 'b') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((ch == 'B') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[86]++;
          ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[145]++;
          return new WordBoundary(ch);

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[87]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[146]++;
int CodeCoverConditionCoverageHelper_C31; if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 (('1' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((ch <= '9') && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[88]++;
          ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[147]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[148]++;
          int possibleGroupIndex = ch - '0';
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[149]++;
int CodeCoverConditionCoverageHelper_C32;
          if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((numCapturingGroups >= possibleGroupIndex) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[90]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[150]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[92]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[151]++;
              char next = pattern.charAt(pos);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[152]++;
int CodeCoverConditionCoverageHelper_C34;
              if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (8)) == 0 || true) &&
 (('0' <= next) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((next <= '9') && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[94]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[153]++;
                int twoDigitGroupIndex = possibleGroupIndex * 10 + (next - '0');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[154]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((numCapturingGroups >= twoDigitGroupIndex) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[96]++;
                  ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[155]++;
                  possibleGroupIndex = twoDigitGroupIndex;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[156]++;

                } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[97]++;}

              } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[95]++;}

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[93]++;}
            return new BackReference(possibleGroupIndex);

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[91]++;
            // \1 - \7 are octal escapes if there is no such group.
            // \8 and \9 are the literal characters '8' and '9' if there
            // is no such group.
            return new Text(Character.toString(
              possibleGroupIndex <= 7 ? (char) possibleGroupIndex : ch));
          }

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[89]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[157]++;
          CharRanges charGroup = NAMED_CHAR_GROUPS.get(ch);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[158]++;
int CodeCoverConditionCoverageHelper_C36;
          if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((charGroup != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[98]++;  // Handle \d, etc.
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[159]++;
            return new Charset(charGroup, CharRanges.EMPTY);

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[99]++;}
          return new Text("" + parseEscapeChar());
        }
}
      }

      /**
       * Parses n hex digits to a code-unit.
       */
      private char parseHex(int n) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[160]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((pos + n > limit) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[100]++;
          throw new IllegalArgumentException(
              "Abbreviated hex escape " + pattern.substring(pos));

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[101]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[161]++;
        int result = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[162]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[16]++;


int CodeCoverConditionCoverageHelper_C38;
        while ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((--n >= 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[16]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[17]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[18]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[163]++;
          char ch = pattern.charAt(pos);
          int digit;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[164]++;
int CodeCoverConditionCoverageHelper_C39;
          if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 (('0' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((ch <= '9') && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[102]++;
            digit = ch - '0';
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[165]++;

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[103]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[166]++;
int CodeCoverConditionCoverageHelper_C40; if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 (('a' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((ch <= 'f') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[104]++;
            digit = ch + (10 - 'a');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[167]++;

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[105]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[168]++;
int CodeCoverConditionCoverageHelper_C41; if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 (('A' <= ch) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((ch <= 'F') && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[106]++;
            digit = ch + (10 - 'A');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[169]++;

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[107]++;
            throw new IllegalArgumentException(pattern.substring(pos));
          }
}
}
          ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[170]++;
          result = (result << 4) | digit;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[171]++;
        }
        return (char) result;
      }

      private boolean isRepetitionStart(char ch) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[172]++;
        switch (ch) {
          case '?':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[108]++;
          case '*':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[109]++;
          case '+':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[110]++;
          case '{':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[111]++;
            return true;
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[112]++;
            return false;
        }
      }

      /**
       * Parse a repetition.  {@code x?} is treated as a repetition --
       * an optional production can be matched 0 or 1 time.
       */
      private RegExpTree parseRepetition(RegExpTree body) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[173]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((pos == limit) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[113]++; return body;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[114]++;}
        int min, max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[174]++;
        switch (pattern.charAt(pos)) {
          case '+':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[115]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[175]++;
            min = 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[176]++;
            max = Integer.MAX_VALUE;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[177]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[178]++;
            break;
          case '*':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[116]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[179]++;
            min = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[180]++;
            max = Integer.MAX_VALUE;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[181]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[182]++;
            break;
          case '?':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[117]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[183]++;
            min = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[184]++;
            max = 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[185]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[186]++;
            break;
          case '{':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[118]++;
            ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[187]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[188]++;
            int start = pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[189]++;
            int end = pattern.indexOf('}', start);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[190]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((end < 0) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[119]++;
              pos = start - 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[191]++;
              return body;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[120]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[192]++;
            String counts = pattern.substring(start, end);
            pos = end + 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[193]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[194]++;
            int comma = counts.indexOf(',');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[195]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
              min = Integer.parseInt(
                  comma >= 0 ? counts.substring(0, comma) : counts);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[196]++;
              max = comma >= 0
                  ? comma + 1 != counts.length()
                      ? Integer.parseInt(counts.substring(comma + 1))
                      : Integer.MAX_VALUE
                  : min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[197]++;
            } catch (NumberFormatException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[122]++;
              min = max = -1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[198]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[121]++;
}
  }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[199]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((min < 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((min > max) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[123]++;
              // Treat the open curly bracket literally.
              pos = start - 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[200]++;
              return body;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[124]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[201]++;
            break;
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[125]++;
            return body;
        }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[202]++;
        boolean greedy = true;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[203]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((pos < limit) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((pattern.charAt(pos) == '?') && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[126]++;
          greedy = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[204]++;
          ++pos;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[205]++;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[127]++;}
        return new Repetition(body, min, max, greedy);
      }
    }

    return new Parser().parseTopLevel();
  }


  /**
   * True if, but not necessarily always when the, given regular expression
   * must match the whole input or none of it.
   */
  public static boolean matchesWholeInput(RegExpTree t, String flags) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[206]++;
int CodeCoverConditionCoverageHelper_C46;
    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((flags.indexOf('m') >= 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[128]++; return false;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[129]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[207]++;
int CodeCoverConditionCoverageHelper_C47;

    if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((t instanceof Concatenation) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[130]++;
      return false;

    } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[131]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[208]++;

    Concatenation c = (Concatenation) t;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[209]++;
int CodeCoverConditionCoverageHelper_C48;
    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((c.elements.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[132]++; return false;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[133]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[210]++;
    RegExpTree first = c.elements.get(0),
        last = c.elements.get(c.elements.size() - 1);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[211]++;
int CodeCoverConditionCoverageHelper_C49;
    if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C49 |= (8)) == 0 || true) &&
 ((first instanceof Anchor) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((last instanceof Anchor) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[134]++; return false;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[135]++;}
    return ((Anchor) first).type == '^' && ((Anchor) last).type == '$';
  }


  static abstract class RegExpTreeAtom extends RegExpTree {
    @Override
    public boolean isCaseSensitive() {
      return false;
    }

    @Override
    public boolean containsAnchor() {
      return false;
    }

    @Override
    public final int numCapturingGroups() {
      return 0;
    }

    @Override
    public final List<? extends RegExpTree> children() {
      return ImmutableList.of();
    }
  }

  static final class Empty extends RegExpTreeAtom {
    static final Empty INSTANCE = new Empty();

    @Override
    public RegExpTree simplify(String flags) {
      return this;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
      // No output
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      // No output
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Empty;
    }

    @Override
    public int hashCode() {
      return 0x7ee06141;
    }
  }

  static final class Anchor extends RegExpTreeAtom {
    final char type;
    Anchor(char type) { this.type = type;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[212]++; }

    @Override
    public RegExpTree simplify(String flags) {
      return this;
    }

    @Override
    public boolean containsAnchor() {
      return true;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
      sb.append(type);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[213]++;
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append(type);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[214]++;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Anchor && type == ((Anchor) o).type;
    }

    @Override
    public int hashCode() {
      return type ^ 0xe85317ff;
    }
  }

  static final class WordBoundary extends RegExpTreeAtom {
    final char type;

    WordBoundary(char type) {
      this.type = type;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[215]++;
    }

    @Override
    public RegExpTree simplify(String flags) {
      return this;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
      sb.append('\\').append(type);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[216]++;
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append(type);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[217]++;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof WordBoundary && type == ((WordBoundary) o).type;
    }

    @Override
    public int hashCode() {
      return 0x5673aa29 ^ type;
    }
  }

  static final class BackReference extends RegExpTreeAtom {
    final int groupIndex;

    BackReference(int groupIndex) {
      Preconditions.checkArgument(groupIndex >= 0 && groupIndex <= 99);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[218]++;
      this.groupIndex = groupIndex;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[219]++;
    }

    @Override
    public RegExpTree simplify(String flags) {
      return this;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
      sb.append('\\').append(groupIndex);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[220]++;
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append(groupIndex);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[221]++;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof BackReference
          && groupIndex == ((BackReference) o).groupIndex;
    }

    @Override
    public int hashCode() {
      return 0xff072663 ^ groupIndex;
    }
  }

  static final class Text extends RegExpTreeAtom {
    final String text;

    Text(String text) {
      this.text = text;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[222]++;
    }

    /**
     * @param ch The code-unit to escape.
     * @param next The next code-unit or -1 if indeterminable.
     */
    private static void escapeRegularCharOnto(
        char ch, int next, StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[223]++;
      switch (ch) {
        case '$':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[136]++;
        case '^':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[137]++;
        case '*':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[138]++;
        case '(':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[139]++;
        case ')':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[140]++;
        case '+':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[141]++;
        case '[':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[142]++;
        case '|':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[143]++;
        case '.':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[144]++;
        case '/':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[145]++;
        case '?':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[146]++;
          sb.append('\\').append(ch);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[224]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[225]++;
          break;
        case '{':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[147]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[226]++;
int CodeCoverConditionCoverageHelper_C50;
          // If possibly part of a repetition, then escape.
          // Concatenation is handled by the digitsMightBleed check.
          if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 (('0' <= next) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((next <= '9') && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[148]++;
            sb.append('\\');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[227]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[149]++;}
          sb.append(ch);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[228]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[229]++;
          break;
        default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[150]++;
          escapeCharOnto(ch, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[230]++;
      }
    }

    @Override
    public RegExpTree simplify(String flags) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[231]++;
      int n = text.length();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[232]++;
int CodeCoverConditionCoverageHelper_C51;
      if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((n == 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[151]++;
        return Empty.INSTANCE;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[152]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[233]++;
int CodeCoverConditionCoverageHelper_C52;
      if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((flags.indexOf('i') >= 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[153]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[234]++;
        String canonicalized = CaseCanonicalize.caseCanonicalize(text);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[235]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((text != canonicalized) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[155]++;
          return new Text(canonicalized);

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[156]++;}

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[154]++;}
      return this;
    }

    @Override
    public boolean isCaseSensitive() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[236]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[19]++;


int CodeCoverConditionCoverageHelper_C54;
      for (int i = 0, n = text.length();(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[19]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[20]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[21]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[237]++;
int CodeCoverConditionCoverageHelper_C55;
        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((CaseCanonicalize.CASE_SENSITIVE.contains(text.charAt(i))) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[157]++;
          return true;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[158]++;}
      }
      return false;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[238]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[22]++;


int CodeCoverConditionCoverageHelper_C56;
      for (int i = 0, n = text.length();(((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[22]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[23]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[24]++;
}
        escapeRegularCharOnto(text.charAt(i), i + 1 < n ? text.charAt(i + 1) : -1, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[239]++;
      }
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append('`').append(text).append('`');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[240]++;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Text && text.equals(((Text) o).text);
    }

    @Override
    public int hashCode() {
      return text.hashCode() ^ 0x617e310;
    }
  }

  static final class Repetition extends RegExpTree {
    final RegExpTree body;
    final int min, max;
    final boolean greedy;

    Repetition(RegExpTree body, int min, int max, boolean greedy) {
      this.body = body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[241]++;
      this.min = min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[242]++;
      this.max = max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[243]++;
      this.greedy = greedy;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[244]++;
    }

    @Override
    public RegExpTree simplify(String flags) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[245]++;
      RegExpTree body = this.body.simplify(flags);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[246]++;
int CodeCoverConditionCoverageHelper_C57;
      if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((max == 0) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((body.hasCapturingGroup()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[159]++; return Empty.INSTANCE;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[160]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[247]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (8)) == 0 || true) &&
 ((body instanceof Empty) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((NEVER_MATCHES.equals(body)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[161]++; return body;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[162]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[248]++;
      int min = this.min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[249]++;
      int max = this.max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[250]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((body instanceof Repetition) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[163]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[251]++;
        Repetition rbody = (Repetition) body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[252]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((rbody.greedy == greedy) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[165]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[253]++;
          long lmin = ((long) min) * rbody.min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[254]++;
          long lmax = ((long) max) * rbody.max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[255]++;
int CodeCoverConditionCoverageHelper_C61;
          if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((lmin < Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[167]++;
            body = rbody.body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[256]++;
            min = (int) lmin;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[257]++;
            max = lmax >= Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) lmax;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[258]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[168]++;}

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[166]++;}

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[164]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[259]++;
int CodeCoverConditionCoverageHelper_C62;
      if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((min == 1) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((max == 1) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[169]++; return body;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[170]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[260]++;
      boolean greedy = this.greedy || min == max;
      return body.equals(this.body) && min == this.min && max == this.max
          && greedy == this.greedy
          ? this
          : new Repetition(body, min, max, greedy).simplify(flags);
    }

    @Override
    public boolean isCaseSensitive() {
      return body.isCaseSensitive();
    }

    @Override
    public boolean containsAnchor() {
      return body.containsAnchor();
    }

    @Override
    public int numCapturingGroups() {
      return body.numCapturingGroups();
    }

    @Override
    public List<? extends RegExpTree> children() {
      return ImmutableList.of(body);
    }

    private void appendBodySourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[261]++;
int CodeCoverConditionCoverageHelper_C63;
      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (512)) == 0 || true) &&
 ((body instanceof Alternation) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (256)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (128)) == 0 || true) &&
 ((body instanceof Concatenation) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C63 |= (32)) == 0 || true) &&
 ((body instanceof Repetition) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C63 |= (8)) == 0 || true) &&
 ((body instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((((Text) body).text.length() > 1) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 5) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 5) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[171]++;
        sb.append("(?:");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[262]++;
        body.appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[263]++;
        sb.append(')');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[264]++;

      } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[172]++;
        body.appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[265]++;
      }
    }

    private static int suffixLen(int min, int max) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[266]++;
int CodeCoverConditionCoverageHelper_C64;
      // This mirrors the branches that renders a suffix in appendSourceCode below.
      if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((max == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[173]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[267]++;
        switch (min) {
          case 0:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[175]++; return 1;  // *
          case 1:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[176]++; return 1;  // +
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[177]++; return 3 + numDecimalDigits(min);  // {3,}
        }

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[174]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[268]++;
int CodeCoverConditionCoverageHelper_C65;
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (8)) == 0 || true) &&
 ((min == 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((max == 1) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[178]++;
        return 1;
  // ?
      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[179]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[269]++;
int CodeCoverConditionCoverageHelper_C66;
      if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((min == max) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[180]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[270]++;
int CodeCoverConditionCoverageHelper_C67;
        if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((min == 1) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[182]++;
          return 0;
  // No suffix needed for {1}.
        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[183]++;}
        return 2 + numDecimalDigits(min);
  // {4}
      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[181]++;}
      return 3 + numDecimalDigits(min) + numDecimalDigits(max);  // {2,7}
    }

    private static int numDecimalDigits(int n) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[271]++;
int CodeCoverConditionCoverageHelper_C68;
      if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((n < 0) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[184]++;
        // Negative values should not be passed in.
        throw new AssertionError();

        // If changing this code to support negative values,
        // Integer.MIN_VALUE is a corner-case..
      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[185]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[272]++;
      int nDigits = 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[273]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[25]++;


int CodeCoverConditionCoverageHelper_C69;
      while ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((n >= 10) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[25]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[26]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[27]++;
}
        ++nDigits;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[274]++;
        n /= 10;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[275]++;
      }
      return nDigits;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[276]++;
      int bodyStart = sb.length();
      appendBodySourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[277]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[278]++;
      int bodyEnd = sb.length();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[279]++;
      int bodyLen = bodyEnd - bodyStart;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[280]++;
      int min = this.min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[281]++;
      int max = this.max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[282]++;
int CodeCoverConditionCoverageHelper_C70;
      if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (32)) == 0 || true) &&
 ((min >= 2) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C70 |= (8)) == 0 || true) &&
 ((max == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((max - min <= 1) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 3) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[186]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[283]++;
        int expanded =
           // If min == max then we want to try expanding to the limit and
           // attach the empty suffix, which is equivalent to min = max = 1,
           // i.e. /a/ vs /a{1}/.
           min == max
           // Give aa+ preference over aaa*.
           || max == Integer.MAX_VALUE
           ? min - 1
           : min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[284]++;
        int expandedMin = min - expanded;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[285]++;
        int expandedMax = max == Integer.MAX_VALUE ? max : max - expanded;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[286]++;
        int suffixLen = suffixLen(min, max);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[287]++;
        int expandedSuffixLen = suffixLen(expandedMin, expandedMax);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[288]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (8)) == 0 || true) &&
 ((bodyLen * expanded + expandedSuffixLen < suffixLen) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((body.hasCapturingGroup()) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[188]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[289]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[28]++;


int CodeCoverConditionCoverageHelper_C72;
          // a{2} -> aa
          // a{2,} -> aa+
          // a{2,3} -> aaa?
          while ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((--expanded >= 0) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[28]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[29]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[30]++;
}
            sb.append(sb, bodyStart, bodyEnd);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[290]++;
          }
          min = expandedMin;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[291]++;
          max = expandedMax;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[292]++;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[189]++;}

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[187]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[293]++;
int CodeCoverConditionCoverageHelper_C73;

      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((max == Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[190]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[294]++;
        switch (min) {
          case 0:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[192]++; sb.append('*');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[295]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[296]++; break;
          case 1:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[193]++; sb.append('+');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[297]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[298]++; break;
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[194]++;
            sb.append('{').append(min).append(",}");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[299]++;
        }

      } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[191]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[300]++;
int CodeCoverConditionCoverageHelper_C74; if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((min == 0) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((max == 1) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[195]++;
        sb.append('?');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[301]++;

      } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[196]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[302]++;
int CodeCoverConditionCoverageHelper_C75; if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((min == max) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[197]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[303]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((min != 1) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[199]++;
          sb.append('{').append(min).append('}');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[304]++;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[200]++;}

      } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[198]++;
        sb.append('{').append(min).append(',').append(max).append('}');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[305]++;
      }
}
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[306]++;
int CodeCoverConditionCoverageHelper_C77;
      if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((greedy) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[201]++;
        sb.append('?');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[307]++;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[202]++;}
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append(" min=").append(min).append(", max=").append(max);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[308]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[309]++;
int CodeCoverConditionCoverageHelper_C78;
      if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((greedy) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[203]++; sb.append("  not_greedy");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[310]++;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[204]++;}
    }

    @Override
    public boolean equals(Object o) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[311]++;
int CodeCoverConditionCoverageHelper_C79;
      if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((o instanceof Repetition) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[205]++; return false;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[206]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[312]++;
      Repetition that = (Repetition) o;
      return this.body.equals(that.body)
          && this.min == that.min
          && this.max == that.max
          && this.greedy == that.greedy;
    }

    @Override
    public int hashCode() {
      return min + 31 * (max + 31 * ((greedy ? 1 : 0) + 31 * body.hashCode()));
    }
  }

  static final class Alternation extends RegExpTree {
    final ImmutableList<RegExpTree> alternatives;

    Alternation(List<? extends RegExpTree> alternatives) {
      this.alternatives = ImmutableList.copyOf(alternatives);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[313]++;
    }

    @Override
    public RegExpTree simplify(String flags) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[314]++;
      List<RegExpTree> alternatives = Lists.newArrayList();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[315]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[31]++;


      for (RegExpTree alternative : this.alternatives) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[31]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[32]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[33]++;
}
        alternative = alternative.simplify(flags);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[316]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[317]++;
int CodeCoverConditionCoverageHelper_C80;
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((alternative instanceof Alternation) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[207]++;
          alternatives.addAll(((Alternation) alternative).alternatives);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[318]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[208]++;
          alternatives.add(alternative);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[319]++;
        }
      }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[320]++;
      // Remove duplicates
      RegExpTree last = null;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[321]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[34]++;


int CodeCoverConditionCoverageHelper_C81;
      for (Iterator<RegExpTree> it = alternatives.iterator();(((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((it.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false);) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[34]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[35]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[36]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[322]++;
        RegExpTree alternative = it.next();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[323]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((alternative.equals(NEVER_MATCHES)) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[209]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[324]++; continue;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[210]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[325]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((alternative.equals(last)) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((alternative.hasCapturingGroup()) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[211]++;
          it.remove();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[326]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[212]++;
          last = alternative;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[327]++;
        }
      }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[328]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[37]++;


int CodeCoverConditionCoverageHelper_C84;
      // Collapse character alternatives into character sets.
      for (int i = 0, n = alternatives.size();(((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[37]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[38]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[39]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[329]++;
        RegExpTree alternative = alternatives.get(i);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[330]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C85 |= (32)) == 0 || true) &&
 ((alternative instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((((Text) alternative).text.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
) || 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((alternative instanceof Charset) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 3) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 3) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[213]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[331]++;
          int end = i;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[332]++;
          int nCharsets = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[333]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[40]++;


int CodeCoverConditionCoverageHelper_C86;
          while ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((end < n) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[40]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[41]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[42]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[334]++;
            RegExpTree follower = alternatives.get(end);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[335]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((follower instanceof Charset) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[215]++;
              ++nCharsets;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[336]++;

            } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[216]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[337]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C88 |= (8)) == 0 || true) &&
 ((follower instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((((Text) follower).text.length() == 1) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[217]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[338]++;
              break;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[218]++;}
}
            ++end;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[339]++;
          }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[340]++;
int CodeCoverConditionCoverageHelper_C89;
          if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (32)) == 0 || true) &&
 ((end - i >= 3) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C89 |= (8)) == 0 || true) &&
 ((nCharsets != 0) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((end - i >= 2) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 3) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 3) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[219]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[341]++;
            int[] members = new int[end - i - nCharsets];
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[342]++;
            int memberIdx = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[343]++;
            CharRanges chars = CharRanges.EMPTY;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[344]++;
            CharRanges ieExplicits = CharRanges.EMPTY;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[345]++;
            List<RegExpTree> charAlternatives = alternatives.subList(i, end);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[346]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[43]++;


            for (RegExpTree charAlternative : charAlternatives) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[43]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[44]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[45]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[347]++;
int CodeCoverConditionCoverageHelper_C90;
              if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((charAlternative instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[221]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[348]++;
                char ch = ((Text) charAlternative).text.charAt(0);
                members[memberIdx++] = ch;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[349]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[350]++;
int CodeCoverConditionCoverageHelper_C91;
                if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((IE_SPEC_ERRORS.contains(ch)) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[223]++;
                  ieExplicits = ieExplicits.union(CharRanges.inclusive(ch, ch));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[351]++;

                } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[224]++;}

              } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[222]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[352]++;
int CodeCoverConditionCoverageHelper_C92; if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((charAlternative instanceof Charset) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[225]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[353]++;
                Charset cs = (Charset) charAlternative;
                chars = chars.union(cs.ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[354]++;
                ieExplicits = ieExplicits.union(cs.ieExplicits);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[355]++;

              } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[226]++;}
}
            }
            chars = chars.union(CharRanges.withMembers(members));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[356]++;
            charAlternatives.clear();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[357]++;
            charAlternatives.add(
                new Charset(chars, ieExplicits).simplify(flags));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[358]++;
            n = alternatives.size();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[359]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[220]++;}

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[214]++;}
      }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[360]++;
      switch (alternatives.size()) {
        case 0:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[227]++; return Empty.INSTANCE;
        case 1:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[228]++; return alternatives.get(0);
        case 2:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[229]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[361]++;
int CodeCoverConditionCoverageHelper_C93;
          if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((alternatives.get(1) instanceof Empty) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[230]++;  // (?:a|) -> a?
            return new Repetition(alternatives.get(0), 0, 1, true);

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[231]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[362]++;
int CodeCoverConditionCoverageHelper_C94; if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((alternatives.get(0) instanceof Empty) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[232]++;
            return new Repetition(alternatives.get(1), 0, 1, false);

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[233]++;}
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[363]++;
          break; default : CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[234]++;
      }
      // TODO: maybe pull out common prefix or suffix
      return alternatives.equals(this.alternatives)
          ? this : new Alternation(alternatives);
    }

    @Override
    public boolean isCaseSensitive() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[364]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[46]++;


      for (RegExpTree alternative : alternatives) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[46]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[47]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[48]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[365]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((alternative.isCaseSensitive()) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[235]++; return true;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[236]++;}
      }
      return false;
    }

    @Override
    public boolean containsAnchor() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[366]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[49]++;


      for (RegExpTree alternative : alternatives) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[49]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[50]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[51]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[367]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((alternative.containsAnchor()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[237]++; return true;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[238]++;}
      }
      return false;
    }

    @Override
    public int numCapturingGroups() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[368]++;
      int n = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[369]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[52]++;


      for (RegExpTree alternative : alternatives) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[52]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[53]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[54]++;
}
        n += alternative.numCapturingGroups();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[370]++;
      }
      return n;
    }

    @Override
    public List<? extends RegExpTree> children() {
      return alternatives;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[371]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[55]++;


int CodeCoverConditionCoverageHelper_C97;
      for (int i = 0, n = alternatives.size();(((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[55]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[56]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[57]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[372]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[239]++;
          sb.append('|');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[373]++;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[240]++;}
        alternatives.get(i).appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[374]++;
      }
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      // Nothing besides children.
    }

    @Override
    public boolean equals(Object o) {
      return this == o || (
          (o instanceof Alternation)
          && alternatives.equals(((Alternation) o).alternatives));
    }

    @Override
    public int hashCode() {
      return 0x51b57cd1 ^ alternatives.hashCode();
    }
  }

  private static final RegExpTree NEVER_MATCHES = new LookaheadAssertion(
      Empty.INSTANCE, false);
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[375]++;
  }

  static final class LookaheadAssertion extends RegExpTree {
    final RegExpTree body;
    final boolean positive;

    LookaheadAssertion(RegExpTree body, boolean positive) {
      this.body = body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[376]++;
      this.positive = positive;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[377]++;
    }

    @Override
    public RegExpTree simplify(String flags) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[378]++;
      RegExpTree simpleBody = body.simplify(flags);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[379]++;
int CodeCoverConditionCoverageHelper_C99;
      if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((simpleBody instanceof Empty) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[241]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[380]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((positive) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[243]++;  // Always true
          return simpleBody;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[244]++;}

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[242]++;}
      return new LookaheadAssertion(simpleBody, positive);
    }

    @Override
    public boolean isCaseSensitive() {
      return body.isCaseSensitive();
    }

    @Override
    public boolean containsAnchor() {
      return body.containsAnchor();
    }

    @Override
    public int numCapturingGroups() {
      return body.numCapturingGroups();
    }

    @Override
    public List<? extends RegExpTree> children() {
      return ImmutableList.of(body);
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
      sb.append(positive ? "(?=" : "(?!");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[381]++;
      body.appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[382]++;
      sb.append(')');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[383]++;
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append(positive ? "positive" : "negative");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[384]++;
    }

    @Override
    public boolean equals(Object o) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[385]++;
int CodeCoverConditionCoverageHelper_C101;
      if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((o instanceof LookaheadAssertion) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[245]++; return false;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[246]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[386]++;
      LookaheadAssertion that = (LookaheadAssertion) o;
      return this.positive == that.positive && this.body.equals(that.body);
    }

    @Override
    public int hashCode() {
      return 0x723aba9 ^ body.hashCode();
    }
  }

  static final class CapturingGroup extends RegExpTree {
    final RegExpTree body;

    CapturingGroup(RegExpTree body) {
      this.body = body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[387]++;
    }

    @Override
    public RegExpTree simplify(String flags) {
      return new CapturingGroup(body.simplify(flags));
    }

    @Override
    public boolean isCaseSensitive() {
      return body.isCaseSensitive();
    }

    @Override
    public boolean containsAnchor() {
      return body.containsAnchor();
    }

    @Override
    public int numCapturingGroups() {
      return 1;
    }

    @Override
    public List<? extends RegExpTree> children() {
      return ImmutableList.of(body);
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
      sb.append('(');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[388]++;
      body.appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[389]++;
      sb.append(')');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[390]++;
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      // Nothing besides children.
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof CapturingGroup
          && body.equals(((CapturingGroup) o).body);
    }

    @Override
    public int hashCode() {
      return 0x55781738 ^ body.hashCode();
    }
  }

  private static final CharRanges DIGITS = CharRanges.inclusive('0', '9');
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[391]++;
  }

  private static final CharRanges UCASE_LETTERS
      = CharRanges.inclusive('A', 'Z');
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[392]++;
  }

  private static final CharRanges LCASE_LETTERS
      = CharRanges.inclusive('a', 'z');
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[393]++;
  }

  private static final CharRanges LETTERS = UCASE_LETTERS.union(LCASE_LETTERS);
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[394]++;
  }

  private static final CharRanges WORD_CHARS = DIGITS
      .union(LETTERS).union(CharRanges.withMembers('_'));
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[395]++;
  }

  private static final CharRanges INVERSE_WORD_CHARS
      = CharRanges.ALL_CODE_UNITS.difference(WORD_CHARS);
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[396]++;
  }

  private static final CharRanges SPACE_CHARS = CharRanges.withMembers(
      '\t', '\n', '\u000b', '\u000c', '\r', ' ', '\u00a0',
      // Unicode 3.0 Zs
      '\u1680', '\u180e', '\u2000', '\u2001',
      '\u2002', '\u2003', '\u2004', '\u2005',
      '\u2006', '\u2007', '\u2008', '\u2009',
      '\u200a',
      // Line terminator chars
      '\u2028', '\u2029',
      // Unicode 3.0 Zs
      '\u202f', '\u205f', '\u3000',
      // Byte order marker is a space character in ES5 but not ES3.
      '\ufeff'
      );
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[397]++;
  }

  /** IE is broken around \s.  IE (6, 7, 8 at least), only recognize these. */
  private static final CharRanges IE_SPACE_CHARS = CharRanges.withMembers(
    '\t', '\n', '\u000b', '\u000c', '\r', ' '
    );
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[398]++;
  }

  /** IE is broken around \s.  IE (6, 7, 8 at least), only recognize these. */
  private static final CharRanges IE_SPEC_ERRORS = SPACE_CHARS.difference(
      IE_SPACE_CHARS);
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[399]++;
  }

  private static final ImmutableMap<Character, CharRanges> NAMED_CHAR_GROUPS
       = ImmutableMap.<Character, CharRanges>builder()
          .put('d', DIGITS)
          .put('D', CharRanges.ALL_CODE_UNITS.difference(DIGITS))
          .put('s', SPACE_CHARS)
          .put('S', CharRanges.ALL_CODE_UNITS.difference(SPACE_CHARS))
          .put('w', WORD_CHARS)
          .put('W', INVERSE_WORD_CHARS)
          .build();
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[400]++;
  }

  private static final Charset DOT_CHARSET = new Charset(
      CharRanges.ALL_CODE_UNITS.difference(
          CharRanges.withMembers('\n', '\r', '\u2028', '\u2029')),
      CharRanges.EMPTY);
  static {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[401]++;
  }

  static final class Charset extends RegExpTreeAtom {
    final CharRanges ranges;
    /**
     * Code units that were mentioned explicitly and that might be matched by
     * a group according to ECMAScript 5 but would not because of specification
     * violations in IE.
     */
    final CharRanges ieExplicits;

    Charset(CharRanges ranges, CharRanges ieExplicits) {
      this.ranges = ranges;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[402]++;
      this.ieExplicits = ieExplicits;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[403]++;
    }

    private static int complexityWordFolded(CharRanges ranges) {
      return Math.min(
          complexityWordFoldedHelper(ranges),
          1 + complexityWordFoldedHelper(
              CharRanges.ALL_CODE_UNITS.difference(ranges)));
    }

    private static int complexityWordFoldedHelper(CharRanges ranges) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[404]++;
      int complexity = DecomposedCharset.complexity(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[405]++;
int CodeCoverConditionCoverageHelper_C102;
      if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((ranges.containsAll(WORD_CHARS)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[247]++;
        complexity = Math.min(
            complexity,
            1 + DecomposedCharset.complexity(ranges.difference(WORD_CHARS)));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[406]++;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[248]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[407]++;
int CodeCoverConditionCoverageHelper_C103;
      if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((ranges.containsAll(INVERSE_WORD_CHARS)) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[249]++;
        complexity = Math.min(
            complexity,
            1 + DecomposedCharset.complexity(
                ranges.difference(INVERSE_WORD_CHARS)));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[408]++;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[250]++;}
      return complexity;
    }

    @Override
    public RegExpTree simplify(String flags) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[409]++;
int CodeCoverConditionCoverageHelper_C104;
      if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((ranges.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[251]++;
        return NEVER_MATCHES;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[252]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[410]++;
      CharRanges best = ranges;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[411]++;
int CodeCoverConditionCoverageHelper_C105;
      if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((flags.indexOf('i') >= 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[253]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[412]++;
        Set<CharRanges> options = Sets.newLinkedHashSet();
        options.add(CaseCanonicalize.expandToAllMatched(ranges));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[413]++;
        options.add(CaseCanonicalize.reduceToMinimum(ranges));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[414]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[415]++;

        CharRanges lcaseLetters = ranges.intersection(LCASE_LETTERS);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[416]++;
        CharRanges ucaseLetters = ranges.intersection(UCASE_LETTERS);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[417]++;

        CharRanges lcaseLettersToUpper = lcaseLetters.shift(-32);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[418]++;
        CharRanges ucaseLettersToLower = ucaseLetters.shift(32);

        options.add(ranges.union(ucaseLettersToLower));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[419]++;
        options.add(ranges.union(lcaseLettersToUpper));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[420]++;
        options.add(ranges.union(lcaseLettersToUpper)
                    .union(ucaseLettersToLower));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[421]++;

        options.add(ranges.union(ucaseLettersToLower).difference(ucaseLetters));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[422]++;
        options.add(ranges.union(lcaseLettersToUpper).difference(lcaseLetters));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[423]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[424]++;

        int bestComplexity = complexityWordFolded(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[425]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[58]++;



        for (CharRanges option : options) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[58]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[59]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[60]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[426]++;
          int complexity = complexityWordFolded(option);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[427]++;
int CodeCoverConditionCoverageHelper_C106;
          if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((complexity < bestComplexity) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[255]++;
            bestComplexity = complexity;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[428]++;
            best = option;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[429]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[256]++;}
        }

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[254]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[430]++;
int CodeCoverConditionCoverageHelper_C107;

      if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (8)) == 0 || true) &&
 ((best.getNumRanges() == 1) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((best.end(0) - best.start(0) == 1) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[257]++;
        return new Text(Character.toString((char) best.start(0)));

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[258]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[431]++;
int CodeCoverConditionCoverageHelper_C108;

      if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((best.equals(ranges)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[259]++;
        return new Charset(best, ieExplicits);

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[260]++;}

      return this;
    }

    @Override
    public boolean isCaseSensitive() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[432]++;
      // We could test
      //     !ranges.equals(CaseCanonicalize.expandToAllMatched(ranges))
      // but we get better optimizations by leaving the 'i' flag on in most
      // cases.

      // Check whether skipping all the character groups that are known
      // case-insensitive leaves us with something that matches the above
      // definition.
      CharRanges withoutNamedGroups = decompose().ranges;
      return !withoutNamedGroups.equals(
            CaseCanonicalize.expandToAllMatched(withoutNamedGroups));
    }

    private DecomposedCharset decompose(CharRanges ranges, boolean inverted) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[433]++;
      StringBuilder namedGroups = new StringBuilder();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[434]++;
      CharRanges rangesInterIeExplicits = ranges.intersection(ieExplicits);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[435]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[61]++;


      while (true) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[61]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[62]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[63]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[436]++;
        char groupName = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[437]++;
        CharRanges simplest = null;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[438]++;
        int minComplexity = DecomposedCharset.complexity(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[439]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[64]++;


        for (Map.Entry<Character, CharRanges> namedGroup
             : NAMED_CHAR_GROUPS.entrySet()) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[64]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[65]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[66]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[440]++;
          CharRanges group = namedGroup.getValue();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[441]++;
int CodeCoverConditionCoverageHelper_C110;
          if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((ranges.containsAll(group)) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[261]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[442]++;
            CharRanges withoutGroup = ranges.difference(group).union(
                rangesInterIeExplicits);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[443]++;
            int complexity = DecomposedCharset.complexity(withoutGroup);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[444]++;
int CodeCoverConditionCoverageHelper_C111;
            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((complexity < minComplexity) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[263]++;
              simplest = withoutGroup;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[445]++;
              groupName = namedGroup.getKey().charValue();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[446]++;
              minComplexity = complexity;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[447]++;

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[264]++;}

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[262]++;}
        }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[448]++;
int CodeCoverConditionCoverageHelper_C112;
        if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((simplest != null) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[265]++;
          namedGroups.append('\\').append(groupName);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[449]++;
          ranges = simplest;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[450]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[266]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[451]++;
          break;
        }
      }
      return new DecomposedCharset(inverted, ranges, namedGroups.toString());
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[452]++;
int CodeCoverConditionCoverageHelper_C113;
      if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((DOT_CHARSET.ranges.equals(ranges)) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[267]++;
        sb.append('.');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[453]++;
        return;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[268]++;}
      decompose().appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[454]++;
    }

    DecomposedCharset decompose() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[455]++;
      CharRanges negRanges = CharRanges.ALL_CODE_UNITS.difference(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[456]++;
int CodeCoverConditionCoverageHelper_C114;
      if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((ieExplicits.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[269]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[457]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((negRanges.intersection(ieExplicits).isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[271]++;
          return decompose(ranges, false);

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[272]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[458]++;
int CodeCoverConditionCoverageHelper_C116; if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((ranges.intersection(ieExplicits).isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[273]++;
          return decompose(negRanges, true);

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[274]++;}
}

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[270]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[459]++;
      DecomposedCharset positive = decompose(ranges, false);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[460]++;
      DecomposedCharset negative = decompose(negRanges, true);
      return positive.complexity() <= negative.complexity()
          ? positive : negative;
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      sb.append(ranges);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[461]++;
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Charset && ranges.equals(((Charset) o).ranges);
    }

    @Override
    public int hashCode() {
      return ranges.hashCode() ^ 0xdede2246;
    }
  }

  static final class DecomposedCharset {
    boolean inverted;
    final CharRanges ranges;
    final String namedGroups;

    DecomposedCharset(
        boolean inverted, CharRanges ranges, String namedGroups) {
      this.inverted = inverted;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[462]++;
      this.ranges = ranges;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[463]++;
      this.namedGroups = namedGroups;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[464]++;
    }

    int complexity() {
      return (inverted ? 1 : 0) + namedGroups.length() + complexity(ranges);
    }

    void appendSourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[465]++;
int CodeCoverConditionCoverageHelper_C117;
      if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((ranges.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[275]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[466]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C118 |= (8)) == 0 || true) &&
 ((inverted) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((namedGroups.length() == 2) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[277]++;
          sb.append(namedGroups);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[467]++;
          return;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[278]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[468]++;
int CodeCoverConditionCoverageHelper_C119; if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (8)) == 0 || true) &&
 ((ranges.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((namedGroups.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[279]++;
          sb.append(inverted ? "[\\S\\s]" : "(?!)");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[469]++;
          return;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[280]++;}
}

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[276]++;}
      sb.append('[');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[470]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[471]++;
int CodeCoverConditionCoverageHelper_C120;
      if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((inverted) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[281]++; sb.append('^');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[472]++;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[282]++;}
      sb.append(namedGroups);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[473]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[474]++;
      boolean rangesStartCharset = !inverted && namedGroups.length() == 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[475]++;
      boolean emitDashAtEnd = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[476]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[67]++;


int CodeCoverConditionCoverageHelper_C121;
      for (int i = 0, n = ranges.getNumRanges();(((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[67]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[68]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[69]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[477]++;
        char start = (char) ranges.start(i);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[478]++;
        char end = (char) (ranges.end(i) - 1);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[479]++;
        switch (end - start) {
          case 0:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[283]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[480]++;
int CodeCoverConditionCoverageHelper_C122;
            if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((start == '-') && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[284]++;
              // Put it at the end where it doesn't need escaping.
              emitDashAtEnd = true;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[481]++;

            } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[285]++;
              escapeRangeCharOnto(
                  start, rangesStartCharset, i == 0, i + 1 == n, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[482]++;
            }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[483]++;
            break;
          case 1:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[286]++;
            escapeRangeCharOnto(start, rangesStartCharset, i == 0, false, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[484]++;
            escapeRangeCharOnto(
                end, rangesStartCharset, false, i + 1 == n, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[485]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[486]++;
            break;
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[287]++;
            escapeRangeCharOnto(start, rangesStartCharset, i == 0, false, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[487]++;
            sb.append('-');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[488]++;
            escapeRangeCharOnto(end, rangesStartCharset, false, true, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[489]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[490]++;
            break;
        }
      }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[491]++;
int CodeCoverConditionCoverageHelper_C123;
      if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((emitDashAtEnd) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[288]++; sb.append('-');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[492]++;
 } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[289]++;}
      sb.append(']');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[493]++;
    }

    static void escapeRangeCharOnto(
        char ch, boolean startIsFlush, boolean atStart, boolean atEnd,
        StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[494]++;
      switch (ch) {
        case '\b':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[290]++;
          sb.append("\\b");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[495]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[496]++;
          break;
        case '^':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[291]++;
          sb.append(atStart && startIsFlush ? "\\^" : "^");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[497]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[498]++;
          break;
        case '-':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[292]++;
          sb.append(atStart || atEnd ? "-" : "\\-");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[499]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[500]++;
          break;
        case '\\':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[293]++;
        case ']':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[294]++;
          sb.append('\\').append(ch);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[501]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[502]++;
          break;
        default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[295]++;
          escapeCharOnto(ch, sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[503]++;
      }
    }

    static int complexity(CharRanges ranges) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[504]++;
      int complexity = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[505]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[70]++;


int CodeCoverConditionCoverageHelper_C124;
      for (int i = 0, n = ranges.getNumRanges();(((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((i < n) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[70]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[71]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[72]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[506]++;
        int start = ranges.start(i);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[507]++;
        int end = ranges.end(i) - 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[508]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (8)) == 0 || true) &&
 ((start < 0x20) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((start >= 0x7f) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[296]++;
          complexity += start >= 0x100 ? 6 : 4;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[509]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[297]++;
          ++complexity;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[510]++;
        }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[511]++;
        switch (end - start) {
          case 0:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[298]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[512]++; continue;
          case 1:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[299]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[513]++; break;
          default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[300]++; complexity += 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[514]++;
        }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[515]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (8)) == 0 || true) &&
 ((end < 0x20) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((end >= 0x7f) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[301]++;
          complexity += end >= 0x100 ? 6 : 4;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[516]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[302]++;
          ++complexity;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[517]++;
        }
      }
      return complexity;
    }

    @Override
    public boolean equals(Object o) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[518]++;
int CodeCoverConditionCoverageHelper_C127;
      if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((o instanceof DecomposedCharset) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[303]++;
        return false;

      } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[304]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[519]++;
      DecomposedCharset that = (DecomposedCharset) o;
      return this.inverted = that.inverted
          && this.ranges.equals(that.ranges)
          && this.namedGroups.equals(that.namedGroups);
    }

    @Override
    public int hashCode() {
      return ranges.hashCode()
          + 31 * (namedGroups.hashCode() + (inverted ? 1 : 0));
    }
  }

  static final class Concatenation extends RegExpTree {
    final ImmutableList<RegExpTree> elements;

    Concatenation(RegExpTree a, RegExpTree b) {
      elements = ImmutableList.of(a, b);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[520]++;
    }

    Concatenation(List<? extends RegExpTree> elements) {
      this.elements = ImmutableList.copyOf(elements);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[521]++;
    }

    @Override
    public RegExpTree simplify(final String flags) {
      class Simplifier {
        final List<RegExpTree> simplified = Lists.newArrayList();
  {
    CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[522]++;
  }

        void simplify(RegExpTree t) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[523]++;
int CodeCoverConditionCoverageHelper_C128;
          if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((t instanceof Concatenation) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[305]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[524]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[73]++;


            for (RegExpTree child : ((Concatenation) t).elements) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[73]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[74]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[75]++;
}
              simplify(child);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[525]++;
            }

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[306]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[526]++;
int CodeCoverConditionCoverageHelper_C129; if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((t instanceof Empty) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[307]++;

            // Do nothing
          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[308]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[527]++;
            int lastIndex = simplified.size() - 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[528]++;
int CodeCoverConditionCoverageHelper_C130;
            if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((lastIndex >= 0) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[309]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[529]++;
              RegExpTree pairwise = simplifyPairwise(
                  simplified.get(lastIndex), t);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[530]++;
int CodeCoverConditionCoverageHelper_C131;
              if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((pairwise != null) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[311]++;
                simplified.set(lastIndex, pairwise);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[531]++;
                return;

              } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[312]++;}

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[310]++;}
            simplified.add(t);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[532]++;
          }
}
        }

        RegExpTree simplifyPairwise(RegExpTree before, RegExpTree after) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[533]++;
int CodeCoverConditionCoverageHelper_C132;
          if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (8)) == 0 || true) &&
 ((before instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((after instanceof Text) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[313]++;
            return new Text(
                ((Text) before).text + ((Text) after).text).simplify(flags);

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[314]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[534]++;
          // Fold adjacent repetitions.
          int beforeMin = 1, beforeMax = 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[535]++;
          RegExpTree beforeBody = before;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[536]++;
          boolean beforeGreedy = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[537]++;
int CodeCoverConditionCoverageHelper_C133;
          if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((before instanceof Repetition) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[315]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[538]++;
            Repetition r = (Repetition) before;
            beforeMin = r.min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[539]++;
            beforeMax = r.max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[540]++;
            beforeBody = r.body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[541]++;
            beforeGreedy = r.greedy;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[542]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[316]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[543]++;
          int afterMin = 1, afterMax = 1;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[544]++;
          RegExpTree afterBody = after;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[545]++;
          boolean afterGreedy = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[546]++;
int CodeCoverConditionCoverageHelper_C134;
          if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((after instanceof Repetition) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[317]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[547]++;
            Repetition r = (Repetition) after;
            afterMin = r.min;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[548]++;
            afterMax = r.max;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[549]++;
            afterBody = r.body;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[550]++;
            afterGreedy = r.greedy;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[551]++;

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[318]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[552]++;
int CodeCoverConditionCoverageHelper_C135;
          if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (8)) == 0 || true) &&
 ((beforeBody.equals(afterBody)) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((beforeBody.hasCapturingGroup()) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[319]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[553]++;
            long lmin = ((long) beforeMin) + afterMin;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[554]++;
            long lmax = ((long) beforeMax) + afterMax;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[555]++;
int CodeCoverConditionCoverageHelper_C136;
            if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((lmin < Integer.MAX_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[321]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[556]++;
              int min = (int) lmin;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[557]++;
              int max = lmax >= Integer.MAX_VALUE
                  ? Integer.MAX_VALUE : (int) lmax;
              return new Repetition(
                  beforeBody, min, max,
                  beforeGreedy || afterGreedy || min == max);

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[322]++;}

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[320]++;}
          return null;
        }
      }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[558]++;

      Simplifier s = new Simplifier();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[559]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[76]++;


      for (RegExpTree element : elements) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[76]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[77]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[78]++;
}
        s.simplify(element.simplify(flags));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[560]++;
      }
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[561]++;

      switch (s.simplified.size()) {
        case 0:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[323]++; return Empty.INSTANCE;
        case 1:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[324]++; return s.simplified.get(0);
        default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[325]++; return new Concatenation(s.simplified);
      }
    }

    @Override
    public boolean isCaseSensitive() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[562]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[79]++;


      for (RegExpTree element : elements) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[79]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[80]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[81]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[563]++;
int CodeCoverConditionCoverageHelper_C137;
        if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((element.isCaseSensitive()) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[326]++;
          return true;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[327]++;}
      }
      return false;
    }

    @Override
    public boolean containsAnchor() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[564]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[82]++;


      for (RegExpTree element : elements) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[82]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[83]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[84]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[565]++;
int CodeCoverConditionCoverageHelper_C138;
        if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((element.containsAnchor()) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[328]++;
          return true;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[329]++;}
      }
      return false;
    }

    @Override
    public int numCapturingGroups() {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[566]++;
      int n = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[567]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[85]++;


      for (RegExpTree element : elements) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[85]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[86]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[87]++;
}
        n += element.numCapturingGroups();
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[568]++;
      }
      return n;
    }

    @Override
    public List<? extends RegExpTree> children() {
      return elements;
    }

    @Override
    protected void appendSourceCode(StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[569]++;
      // True if the last content written might consume
      // decimal digits written subsequently.
      boolean digitsMightBleed = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[570]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[88]++;


      for (RegExpTree element : elements) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[88]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[89]--;
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.loops[90]++;
}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[571]++;
        boolean parenthesize = false;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[572]++;
int CodeCoverConditionCoverageHelper_C139;
        if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (8)) == 0 || true) &&
 ((element instanceof Alternation) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((element instanceof Concatenation) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[330]++;
          parenthesize = true;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[573]++;

        } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[331]++;}
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[574]++;
int CodeCoverConditionCoverageHelper_C140;
        if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((parenthesize) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[332]++;
          sb.append("(?:");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[575]++;
          element.appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[576]++;
          sb.append(')');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[577]++;

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[333]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[578]++;
          int start = sb.length();
          element.appendSourceCode(sb);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[579]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[580]++;
int CodeCoverConditionCoverageHelper_C141;
          if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (8)) == 0 || true) &&
 ((digitsMightBleed) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((sb.length() > start) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[334]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[581]++;
            char firstChar = sb.charAt(start);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[582]++;
int CodeCoverConditionCoverageHelper_C142;
            if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (8)) == 0 || true) &&
 (('0' <= firstChar) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((firstChar <= '9') && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[336]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[583]++;
int CodeCoverConditionCoverageHelper_C143;
              // Bleeding happened.
              // If the last character would be ambiguous
              // with a repetition, escape it.
              if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((sb.charAt(start - 1) == '{') && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[338]++;
                // Concatenation from optimization of
                // /{(?:0,}/ -> /\{0,}/
                sb.insert(start - 1, '\\');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[584]++;

              } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[339]++;
                // Or parenthesize otherwise.
                // Concatenation from optimization of
                // /(.)\1(?:0)/ -> /(.)\1(?:0)/.
                sb.insert(start, "(?:").append(')');
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[585]++;
              }

            } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[337]++;}

          } else {
  CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[335]++;}
        }
        digitsMightBleed = (
            // \1(?:0) bleeds if there are 10 or more
            // capturing groups preceding.
            (element instanceof BackReference
             && ((BackReference) element).groupIndex < 10)
            // foo{(?:10}) bleeds.
            || (element instanceof Text
                && ((Text) element).text.endsWith("{")));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[586]++;
      }
    }

    @Override
    protected void appendDebugInfo(StringBuilder sb) {
      // Nothing besides children.
    }

    @Override
    public boolean equals(Object o) {
      return o instanceof Concatenation
          && elements.equals(((Concatenation) o).elements);
    }

    @Override
    public int hashCode() {
      return 0x20997e3e ^ elements.hashCode();
    }
  }

  static void escapeCharOnto(char ch, StringBuilder sb) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[587]++;
    switch (ch) {
      case '\u0000':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[340]++;
        sb.append("\\0");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[588]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[589]++;
        break;
      case '\f':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[341]++;
        sb.append("\\f");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[590]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[591]++;
        break;
      case '\t':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[342]++;
        sb.append("\\t");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[592]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[593]++;
        break;
      case '\n':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[343]++;
        sb.append("\\n");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[594]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[595]++;
        break;
      case '\r':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[344]++;
        sb.append("\\r");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[596]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[597]++;
        break;
      case '\\':
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[345]++;
        sb.append("\\\\");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[598]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[599]++;
        break;
      default:
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[346]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[600]++;
int CodeCoverConditionCoverageHelper_C144;
        if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 ((ch < 0x20) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((ch >= 0x7f) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 2) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[347]++;
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[601]++;
int CodeCoverConditionCoverageHelper_C145;
          if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((ch >= 0x100) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[349]++;
            sb.append("\\u");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[602]++;
            sb.append("0123456789abcdef".charAt((ch >> 12) & 0xf));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[603]++;
            sb.append("0123456789abcdef".charAt((ch >> 8) & 0xf));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[604]++;
            sb.append("0123456789abcdef".charAt((ch >> 4) & 0xf));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[605]++;
            sb.append("0123456789abcdef".charAt((ch) & 0xf));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[606]++;

          } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[350]++;
            sb.append("\\x");
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[607]++;
            sb.append("0123456789abcdef".charAt((ch >> 4) & 0xf));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[608]++;
            sb.append("0123456789abcdef".charAt((ch) & 0xf));
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[609]++;
          }

        } else {
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.branches[348]++;
          sb.append(ch);
CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht.statements[610]++;
        }
    }
  }
}

class CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht ());
  }
    public static long[] statements = new long[611];
    public static long[] branches = new long[351];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[146];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.regex.RegExpTree.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,2,2,1,2,1,1,1,2,1,2,1,1,1,2,1,1,3,1,2,1,1,2,1,2,2,2,1,1,2,1,1,1,1,2,2,2,1,1,2,2,1,1,1,2,2,1,1,1,1,1,1,2,2,1,1,1,2,3,1,2,1,1,1,1,3,2,1,1,2,1,1,1,1,1,1,1,1,2,1,3,1,1,2,3,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,0,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,2,2,1,1,1,1,1,2,1,1,2,1,1,1,2,1,2,2,1,2,1};
    for (int i = 1; i <= 145; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[91];

  public CodeCoverCoverageCounter$op0vnffbn3dhsk60p7ljyht () {
    super("com.google.javascript.jscomp.regex.RegExpTree.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 610; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 350; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 145; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 90; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.regex.RegExpTree.java");
      for (int i = 1; i <= 610; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 350; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 145; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 30; i++) {
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

