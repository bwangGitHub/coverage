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

package com.google.javascript.jscomp.parsing;

import com.google.common.base.Preconditions;
import com.google.javascript.rhino.head.ScriptRuntime;

/**
 * This class implements the scanner for JsDoc strings.
 *
 * It is heavily based on Rhino's TokenStream.
 *
 */
class JsDocTokenStream {
  static {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.ping();
  }

  /*
   * For chars - because we need something out-of-range
   * to check.  (And checking EOF by exception is annoying.)
   * Note distinction from EOF token type!
   */
  private final static int
      EOF_CHAR = -1;
  static {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[1]++;
  }

  JsDocTokenStream(String sourceString) {
    this(sourceString, 0);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[2]++;
  }

  JsDocTokenStream(String sourceString, int lineno) {
    this(sourceString, lineno, 0);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[3]++;
  }

  JsDocTokenStream(String sourceString, int lineno, int initCharno) {
    Preconditions.checkNotNull(sourceString);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[4]++;
    this.lineno = lineno;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[5]++;
    this.sourceString = sourceString;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[6]++;
    this.sourceEnd = sourceString.length();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[7]++;
    this.sourceCursor = this.cursor = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[8]++;
    this.initLineno = lineno;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[9]++;
    this.initCharno = initCharno;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[10]++;
  }

  /**
   * Tokenizes JSDoc comments.
   */
  @SuppressWarnings("fallthrough")
  final JsDocToken getJsDocToken() {
    int c;
    stringBufferTop = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[11]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[12]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[1]++;


    for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[1]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[2]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[3]++;
}
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[13]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[4]++;


      // eat white spaces
      for (;;) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[4]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[5]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[6]++;
}
        charno = -1;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[14]++;
        c = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[15]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((c == EOF_CHAR) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[1]++;
          return JsDocToken.EOF;

        } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[2]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[17]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[3]++;
          return JsDocToken.EOL;

        } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[4]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[18]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((isJSSpace(c)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[5]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[19]++;
          break;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[6]++;}
}
}
      }
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[20]++;

      switch (c) {
        // annotation, e.g. @type or @constructor
        case '@':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[7]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[21]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[7]++;


          do {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[7]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[8]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[9]++;
}
            c = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[22]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[23]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((isAlpha(c)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[8]++;
              addToString(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[24]++;

            } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[9]++;
              ungetChar(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[25]++;
              this.string = getStringFromBuffer();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[26]++;
              stringBufferTop = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[27]++;
              return JsDocToken.ANNOTATION;
            }
          } while (true);

        case '*':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[10]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
          if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((matchChar('/')) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[11]++;
            return JsDocToken.EOC;

          } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[12]++;
            return JsDocToken.STAR;
          }

        case ',':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[13]++;
          return JsDocToken.COMMA;

        case '>':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[14]++;
          return JsDocToken.GT;

        case '(':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[15]++;
          return JsDocToken.LP;

        case ')':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[16]++;
          return JsDocToken.RP;

        case '{':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[17]++;
          return JsDocToken.LC;

        case '}':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[18]++;
          return JsDocToken.RC;

        case '[':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[19]++;
          return JsDocToken.LB;

        case ']':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[20]++;
          return JsDocToken.RB;

        case '?':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[21]++;
          return JsDocToken.QMARK;

        case '!':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[22]++;
          return JsDocToken.BANG;

        case ':':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[23]++;
          return JsDocToken.COLON;

        case '=':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[24]++;
          return JsDocToken.EQUALS;

        case '|':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[25]++;
          matchChar('|');
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[29]++;
          return JsDocToken.PIPE;

        case '.':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[26]++;
          c = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[30]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
          if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((c == '<') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[27]++;
            return JsDocToken.LT;

          } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[28]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[32]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[29]++;
              c = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[33]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[34]++;
int CodeCoverConditionCoverageHelper_C11;
              if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((c == '.') && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[31]++;
                return JsDocToken.ELLIPSIS;

              } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[32]++;
                addToString('.');
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[35]++;
              }

            } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[30]++;}
            // we may backtrack across line boundary
            ungetBuffer[ungetCursor++] = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[36]++;
            c = '.';
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[37]++;
          }
          // fall through

        default:
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[33]++; {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[38]++;
          // recognize a JsDoc string but discard last . if it is followed by
          // a non-JsDoc comment char, e.g. Array.<
          int c1 = c;
          addToString(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[39]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[40]++;
          int c2 = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[41]++;
int CodeCoverConditionCoverageHelper_C12;
          if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((isJSDocString(c2)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[34]++;
            ungetChar(c2);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[42]++;
            this.string = getStringFromBuffer();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[43]++;
            stringBufferTop = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[44]++;
            return JsDocToken.STRING;

          } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[35]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[45]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[10]++;


            do {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[10]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[11]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[12]++;
}
              c1 = c2;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[46]++;
              c2 = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[47]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[48]++;
int CodeCoverConditionCoverageHelper_C14;
              if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((c1 == '.') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((c2 == '<') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[36]++;
                ungetChar(c2);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[49]++;
                ungetChar(c1);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[50]++;
                this.string = getStringFromBuffer();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[51]++;
                stringBufferTop = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[52]++;
                return JsDocToken.STRING;

              } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[37]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[53]++;
int CodeCoverConditionCoverageHelper_C15;
                if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((isJSDocString(c2)) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[38]++;
                  addToString(c1);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[54]++;

                } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[39]++;
                  ungetChar(c2);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[55]++;
                  addToString(c1);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[56]++;
                  this.string = getStringFromBuffer();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[57]++;
                  stringBufferTop = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[58]++;
                  return JsDocToken.STRING;
                }
              }
            } while (true);
          }
        }
      }
    }
  }

  /**
   * Gets the remaining JSDoc line without the {@link JsDocToken#EOL},
   * {@link JsDocToken#EOF} or {@link JsDocToken#EOC}.
   */
  @SuppressWarnings("fallthrough")
  String getRemainingJSDocLine() {
    int c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[59]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[13]++;


    for (;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[13]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[14]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[15]++;
}
      c = getChar();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[60]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[61]++;
      switch (c) {
        case '*':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[40]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[62]++;
int CodeCoverConditionCoverageHelper_C17;
          if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((peekChar() != '/') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[41]++;
            addToString(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[63]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[64]++;
            break;

          } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[42]++;}
          // fall through
        case EOF_CHAR:
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[43]++;
        case '\n':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[44]++;
          ungetChar(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[65]++;
          this.string = getStringFromBuffer();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[66]++;
          stringBufferTop = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[67]++;
          return this.string;

        default:
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[45]++;
          addToString(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[68]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[69]++;
          break;
      }
    }
  }

  final int getLineno() { return lineno; }

  final int getCharno() {
    return lineno == initLineno? initCharno + charno : charno;
  }

  final String getString() { return string; }

  final boolean eof() { return hitEOF; }

  private String getStringFromBuffer() {
    tokenEnd = cursor;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[70]++;
    return new String(stringBuffer, 0, stringBufferTop);
  }

  private void addToString(int c) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[71]++;
    int N = stringBufferTop;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[72]++;
int CodeCoverConditionCoverageHelper_C18;
    if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((N == stringBuffer.length) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[46]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[73]++;
        char[] tmp = new char[stringBuffer.length * 2];
        System.arraycopy(stringBuffer, 0, tmp, 0, N);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[74]++;
        stringBuffer = tmp;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[75]++;

    } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[47]++;}
    stringBuffer[N] = (char)c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[76]++;
    stringBufferTop = N + 1;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[77]++;
  }

  void ungetChar(int c) {
    // can not unread past across line boundary
    assert(!(ungetCursor != 0 && ungetBuffer[ungetCursor - 1] == '\n'));
    ungetBuffer[ungetCursor++] = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[78]++;
    cursor--;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[79]++;
  }

  private boolean matchChar(int test) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[80]++;
    int c = getCharIgnoreLineEnd();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[81]++;
int CodeCoverConditionCoverageHelper_C19;
    if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((c == test) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[48]++;
      tokenEnd = cursor;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[82]++;
      return true;

    } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[49]++;
      ungetCharIgnoreLineEnd(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[83]++;
      return false;
    }
  }

  private static boolean isAlpha(int c) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[84]++;
int CodeCoverConditionCoverageHelper_C20;
    // Use 'Z' < 'a'
    if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((c <= 'Z') && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[50]++;
      return 'A' <= c;

    } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[51]++;
      return 'a' <= c && c <= 'z';
    }
  }

  private boolean isJSDocString(int c) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[85]++;
    switch (c) {
      case '@':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[52]++;
      case '*':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[53]++;
      case ',':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[54]++;
      case '>':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[55]++;
      case ':':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[56]++;
      case '(':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[57]++;
      case ')':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[58]++;
      case '{':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[59]++;
      case '}':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[60]++;
      case '[':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[61]++;
      case ']':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[62]++;
      case '?':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[63]++;
      case '!':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[64]++;
      case '|':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[65]++;
      case '=':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[66]++;
      case EOF_CHAR:
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[67]++;
      case '\n':
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[68]++;
        return false;

      default:
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[69]++;
        return !isJSSpace(c);
    }
  }

  /* As defined in ECMA.  jsscan.c uses C isspace() (which allows
   * \v, I think.)  note that code in getChar() implicitly accepts
   * '\r' == \u000D as well.
   */
  static boolean isJSSpace(int c) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[86]++;
int CodeCoverConditionCoverageHelper_C21;
    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((c <= 127) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[70]++;
      return c == 0x20 || c == 0x9 || c == 0xC || c == 0xB;

    } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[71]++;
      return c == 0xA0
          || Character.getType((char)c) == Character.SPACE_SEPARATOR;
    }
  }

  private static boolean isJSFormatChar(int c) {
    return c > 127 && Character.getType((char)c) == Character.FORMAT;
  }

  /**
   * Allows the JSDocParser to update the character offset
   * so that getCharno() returns a valid character position.
   */
  void update() {
    charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[87]++;
  }

  private int peekChar() {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[88]++;
    int c = getChar();
    ungetChar(c);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[89]++;
    return c;
  }

  protected int getChar() {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[90]++;
int CodeCoverConditionCoverageHelper_C22;
    if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ungetCursor != 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[72]++;
      cursor++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[91]++;
      --ungetCursor;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[92]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[93]++;
int CodeCoverConditionCoverageHelper_C23;
      if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[74]++;
        charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[94]++;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[75]++;}
      return ungetBuffer[ungetCursor];

    } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[73]++;}
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[95]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[16]++;



    for(;;) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[16]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[17]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[18]++;
}
      int c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[96]++;
int CodeCoverConditionCoverageHelper_C25;
      if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((sourceCursor == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[76]++;
        hitEOF = true;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[97]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[98]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[78]++;
          charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[99]++;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[79]++;}
        return EOF_CHAR;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[77]++;}
      cursor++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[100]++;
      c = sourceString.charAt(sourceCursor++);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[101]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[102]++;
int CodeCoverConditionCoverageHelper_C27;


      if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((lineEndChar >= 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[80]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[103]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((lineEndChar == '\r') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[82]++;
          lineEndChar = '\n';
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[104]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[105]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[83]++;}
        lineEndChar = -1;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[106]++;
        lineStart = sourceCursor - 1;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[107]++;
        lineno++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[108]++;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[81]++;}
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[109]++;
int CodeCoverConditionCoverageHelper_C29;

      if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((c <= 127) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[84]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[110]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[86]++;
          lineEndChar = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[111]++;
          c = '\n';
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[112]++;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[87]++;}

      } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[85]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[113]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((isJSFormatChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[88]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[114]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[89]++;}
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[115]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSLineTerminator(c)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[90]++;
          lineEndChar = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[116]++;
          c = '\n';
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[117]++;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[91]++;}
      }
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[118]++;
int CodeCoverConditionCoverageHelper_C33;

      if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[92]++;
        charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[119]++;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[93]++;}

      return c;
    }
  }

  private int getCharIgnoreLineEnd() {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[120]++;
int CodeCoverConditionCoverageHelper_C34;
    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((ungetCursor != 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[94]++;
      cursor++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[121]++;
      --ungetCursor;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[122]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[123]++;
int CodeCoverConditionCoverageHelper_C35;
      if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[96]++;
        charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[124]++;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[97]++;}
      return ungetBuffer[ungetCursor];

    } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[95]++;}
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[125]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[19]++;



    for(;;) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[19]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[20]--;
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.loops[21]++;
}
      int c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[126]++;
int CodeCoverConditionCoverageHelper_C37;
      if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((sourceCursor == sourceEnd) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[98]++;
        hitEOF = true;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[127]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[128]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[100]++;
          charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[129]++;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[101]++;}
        return EOF_CHAR;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[99]++;}
      cursor++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[130]++;
      c = sourceString.charAt(sourceCursor++);
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[131]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[132]++;
int CodeCoverConditionCoverageHelper_C39;


      if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((c <= 127) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[102]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[133]++;
int CodeCoverConditionCoverageHelper_C40;
        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 2) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[104]++;
          lineEndChar = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[134]++;
          c = '\n';
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[135]++;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[105]++;}

      } else {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[103]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[136]++;
int CodeCoverConditionCoverageHelper_C41;
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((isJSFormatChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[106]++;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[137]++;
          continue;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[107]++;}
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[138]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((ScriptRuntime.isJSLineTerminator(c)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[108]++;
          lineEndChar = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[139]++;
          c = '\n';
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[140]++;

        } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[109]++;}
      }
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[141]++;
int CodeCoverConditionCoverageHelper_C43;

      if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((charno == -1) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[110]++;
        charno = getOffset();
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[142]++;

      } else {
  CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.branches[111]++;}

      return c;
    }
  }

  private void ungetCharIgnoreLineEnd(int c) {
    ungetBuffer[ungetCursor++] = c;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[143]++;
    cursor--;
CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[144]++;
  }

  /**
   * Returns the offset into the current line.
   */
  final int getOffset() {
    return sourceCursor - lineStart - ungetCursor - 1;
  }

  // Set this to an initial non-null value so that the Parser has
  // something to retrieve even if an error has occurred and no
  // string is found.  Fosters one class of error, but saves lots of
  // code.
  private String string = "";
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[145]++;
  }

  private char[] stringBuffer = new char[128];
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[146]++;
  }
  private int stringBufferTop;

  // Room to backtrace from to < on failed match of the last - in <!--
  private final int[] ungetBuffer = new int[3];
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[147]++;
  }
  private int ungetCursor;

  private boolean hitEOF = false;
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[148]++;
  }

  private int lineStart = 0;
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[149]++;
  }
  private int lineEndChar = -1;
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[150]++;
  }
  int lineno;
  private int charno = -1;
  {
    CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl.statements[151]++;
  }
  private int initCharno;
  private int initLineno;

  private String sourceString;
  private int sourceEnd;

  // sourceCursor is an index into a small buffer that keeps a
  // sliding window of the source stream.
  int sourceCursor;

  // cursor is a monotonically increasing index into the original
  // source stream, tracking exactly how far scanning has progressed.
  // Its value is the index of the next character to be scanned.
  int cursor;

  // Record start and end positions of last scanned token.
  int tokenBeg;
  int tokenEnd;
}

class CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl ());
  }
    public static long[] statements = new long[152];
    public static long[] branches = new long[112];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[44];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.parsing.JsDocTokenStream.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,0,0,1,1,1,0,1,1,1,1,1,1,0,2,1,0,1,1,1,1,1,1,1,0,1,1,1,2,1,2,1,1,1,1,1,0,1,1,1,2,1,1,1};
    for (int i = 1; i <= 43; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[22];

  public CodeCoverCoverageCounter$1puc27323k2y0prryoppkegpwecc8ssbl () {
    super("com.google.javascript.jscomp.parsing.JsDocTokenStream.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 151; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 111; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 21; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.parsing.JsDocTokenStream.java");
      for (int i = 1; i <= 151; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 111; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 43; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 7; i++) {
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

