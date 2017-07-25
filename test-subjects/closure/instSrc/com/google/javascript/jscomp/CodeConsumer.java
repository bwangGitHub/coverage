/*
 * Copyright 2004 The Closure Compiler Authors.
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

import com.google.javascript.rhino.Node;


/**
 * Abstracted consumer of the CodeGenerator output.
 *
 * @see CodeGenerator
 * @see CodePrinter
 * @see InlineCostEstimator
 */
abstract class CodeConsumer {
  static {
    CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.ping();
  }

  boolean statementNeedsEnded = false;
  {
    CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[1]++;
  }
  boolean statementStarted = false;
  {
    CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[2]++;
  }
  boolean sawFunction = false;
  {
    CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[3]++;
  }

  /**
   * Starts the source mapping for the given
   * node at the current position.
   */
  void startSourceMapping(Node node) {
  }

  /**
   * Finishes the source mapping for the given
   * node at the current position.
   */
  void endSourceMapping(Node node) {
  }

  /**
   * Provides a means of interrupting the CodeGenerator. Derived classes should
   * return false to stop further processing.
   */
  boolean continueProcessing() {
    return true;
  }

  /**
   * Retrieve the last character of the last string sent to append.
   */
  abstract char getLastChar();

  void addIdentifier(String identifier) {
    add(identifier);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[4]++;
  }

  /**
   * Appends a string to the code, keeping track of the current line length.
   *
   * NOTE: the string must be a complete token--partial strings or
   * partial regexes will run the risk of being split across lines.
   *
   * Do not directly append newlines with this method. Instead use
   * {@link #startNewLine}.
   */
  abstract void append(String str);

  void appendBlockStart() {
    append("{");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[5]++;
  }

  void appendBlockEnd() {
    append("}");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[6]++;
  }

  void startNewLine() {
  }

  void maybeLineBreak() {
    maybeCutLine();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[7]++;
  }

  void maybeCutLine() {
  }

  void endLine() {
  }

  void notePreferredLineBreak() {
  }

  void beginBlock() {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((statementNeedsEnded) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[1]++;
      append(";");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[9]++;
      maybeLineBreak();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[10]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[2]++;}
    appendBlockStart();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[11]++;

    endLine();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[12]++;
    statementNeedsEnded = false;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[13]++;
  }

  void endBlock() {
    endBlock(false);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[14]++;
  }

  void endBlock(boolean shouldEndLine) {
    appendBlockEnd();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[15]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[16]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((shouldEndLine) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[3]++;
      endLine();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[17]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[4]++;}
    statementNeedsEnded = false;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[18]++;
  }

  void listSeparator() {
    add(",");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[19]++;
    maybeLineBreak();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[20]++;
  }

  /**
   * Indicates the end of a statement and a ';' may need to be added.
   * But we don't add it now, in case we're at the end of a block (in which
   * case we don't have to add the ';').
   * See maybeEndStatement()
   */
  void endStatement() {
    endStatement(false);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[21]++;
  }

  void endStatement(boolean needSemiColon) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((needSemiColon) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[5]++;
      append(";");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[23]++;
      maybeLineBreak();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[24]++;
      statementNeedsEnded = false;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[25]++;

    } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[6]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[26]++;
int CodeCoverConditionCoverageHelper_C4; if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((statementStarted) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[7]++;
      statementNeedsEnded = true;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[27]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[8]++;}
}
  }

  /**
   * This is to be called when we're in a statement. If the prev statement
   * needs to be ended, add a ';'.
   */
  void maybeEndStatement() {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[28]++;
int CodeCoverConditionCoverageHelper_C5;
    // Add a ';' if we need to.
    if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((statementNeedsEnded) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[9]++;
      append(";");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[29]++;
      maybeLineBreak();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[30]++;
      endLine();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[31]++;
      statementNeedsEnded = false;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[32]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[10]++;}
    statementStarted = true;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[33]++;
  }

  void endFunction() {
    endFunction(false);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[34]++;
  }

  void endFunction(boolean statementContext) {
    sawFunction = true;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[35]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[36]++;
int CodeCoverConditionCoverageHelper_C6;
    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((statementContext) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[11]++;
      endLine();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[37]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[12]++;}
  }

  void beginCaseBody() {
    append(":");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[38]++;
  }

  void endCaseBody() {
  }

  void add(String newcode) {
    maybeEndStatement();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[39]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[40]++;
int CodeCoverConditionCoverageHelper_C7;

    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((newcode.length() == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[13]++;
      return;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[14]++;}
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[41]++;

    char c = newcode.charAt(0);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[42]++;
int CodeCoverConditionCoverageHelper_C8;
    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C8 |= (32)) == 0 || true) &&
 ((isWordChar(c)) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((c == '\\') && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((isWordChar(getLastChar())) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 3) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[15]++;
      // need space to separate. This is not pretty printing.
      // For example: "return foo;"
      append(" ");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[43]++;

    } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[16]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[44]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((c == '/') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((getLastChar() == '/') && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 2) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[17]++;
      // Do not allow a forward slash to appear after a DIV.
      // For example,
      // REGEXP DIV REGEXP
      // is valid and should print like
      // / // / /
      append(" ");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[45]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[18]++;}
}

    append(newcode);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[46]++;
  }

  void appendOp(String op, boolean binOp) {
    append(op);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[47]++;
  }

  void addOp(String op, boolean binOp) {
    maybeEndStatement();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[48]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[49]++;

    char first = op.charAt(0);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[50]++;
    char prev = getLastChar();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[51]++;
int CodeCoverConditionCoverageHelper_C10;

    if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C10 |= (32)) == 0 || true) &&
 ((first == '+') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((first == '-') && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((prev == first) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 3) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[19]++;
      // This is not pretty printing. This is to prevent misparsing of
      // things like "x + ++y" or "x++ + ++y"
      append(" ");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[52]++;

    } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[20]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[53]++;
int CodeCoverConditionCoverageHelper_C11; if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((Character.isLetter(first)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((isWordChar(prev)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 2) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[21]++;
      // Make sure there is a space after e.g. instanceof , typeof
      append(" ");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[54]++;

    } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[22]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[55]++;
int CodeCoverConditionCoverageHelper_C12; if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((prev == '-') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((first == '>') && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[23]++;
      // Make sure that we don't emit -->
      append(" ");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[56]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[24]++;}
}
}

    // Allow formatting around the operator.
    appendOp(op, binOp);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[57]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[58]++;
int CodeCoverConditionCoverageHelper_C13;

    // Line breaking after an operator is always safe. Line breaking before an
    // operator on the other hand is not. We only line break after a bin op
    // because it looks strange.
    if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((binOp) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[25]++;
      maybeCutLine();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[59]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[26]++;}
  }

  void addNumber(double x) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[60]++;
    // This is not pretty printing. This is to prevent misparsing of x- -4 as
    // x--4 (which is a syntax error).
    char prev = getLastChar();
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[61]++;
    boolean negativeZero = isNegativeZero(x);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[62]++;
int CodeCoverConditionCoverageHelper_C14;
    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C14 |= (32)) == 0 || true) &&
 ((x < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((negativeZero) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((prev == '-') && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 3) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[27]++;
      add(" ");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[63]++;

    } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[28]++;}
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[64]++;
int CodeCoverConditionCoverageHelper_C15;

    if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((negativeZero) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[29]++;
      addConstant("-0");
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[65]++;

    } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[30]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[66]++;
int CodeCoverConditionCoverageHelper_C16; if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 (((long) x == x) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[31]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[67]++;
      long value = (long) x;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[68]++;
      long mantissa = value;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[69]++;
      int exp = 0;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[70]++;
int CodeCoverConditionCoverageHelper_C17;
      if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((Math.abs(x) >= 100) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[33]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[71]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.loops[1]++;


int CodeCoverConditionCoverageHelper_C18;
        while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((mantissa / 10 * Math.pow(10, exp + 1) == value) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.loops[1]--;
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.loops[2]--;
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.loops[3]++;
}
          mantissa /= 10;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[72]++;
          exp++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[73]++;
        }

      } else {
  CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[34]++;}
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[74]++;
int CodeCoverConditionCoverageHelper_C19;
      if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((exp > 2) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[35]++;
        addConstant(Long.toString(mantissa) + "E" + Integer.toString(exp));
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[75]++;

      } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[36]++;
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[76]++;
        long valueAbs = Math.abs(value);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[77]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((Long.toHexString(valueAbs).length() + 2 <
            Long.toString(valueAbs).length()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[37]++;
          addConstant((value < 0 ? "-" : "") + "0x" +
              Long.toHexString(valueAbs));
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[78]++;

        } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[38]++;
          addConstant(Long.toString(value));
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[79]++;
        }
      }

    } else {
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.branches[32]++;
      addConstant(String.valueOf(x).replace(".0E", "E"));
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[80]++;
    }
}
  }

  void addConstant(String newcode) {
    add(newcode);
CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d.statements[81]++;
  }

  static boolean isNegativeZero(double x) {
    return x == 0.0 && Math.copySign(1, x) == -1.0;
  }

  static boolean isWordChar(char ch) {
    return (ch == '_' ||
            ch == '$' ||
            Character.isLetterOrDigit(ch));
  }

  /**
   * If the body of a for loop or the then clause of an if statement has
   * a single statement, should it be wrapped in a block?  Doing so can
   * help when pretty-printing the code, and permits putting a debugging
   * breakpoint on the statement inside the condition.
   *
   * @return {@boolean true} if such expressions should be wrapped
   */
  boolean shouldPreserveExtraBlocks() {
    return false;
  }

  /**
   * @return Whether the a line break can be added after the specified BLOCK.
   */
  boolean breakAfterBlockFor(Node n, boolean statementContext) {
    return statementContext;
  }

  /** Called when we're at the end of a file. */
  void endFile() {}
}

class CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d ());
  }
    public static long[] statements = new long[82];
    public static long[] branches = new long[39];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CodeConsumer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,3,2,3,2,2,1,3,1,1,1,1,1,1};
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$se18u6yde4emfosyx7lh096b1d () {
    super("com.google.javascript.jscomp.CodeConsumer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 81; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 38; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CodeConsumer.java");
      for (int i = 1; i <= 81; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 38; i++) {
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
      for (int i = 1; i <= 1; i++) {
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

