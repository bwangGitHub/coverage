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

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.Token;
import com.google.javascript.rhino.TokenStream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Map;

/**
 * CodeGenerator generates codes from a parse tree, sending it to the specified
 * CodeConsumer.
 *
 */
class CodeGenerator {
  static {
    CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.ping();
  }

  private static final String LT_ESCAPED = "\\x3c";
  static {
    CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[1]++;
  }
  private static final String GT_ESCAPED = "\\x3e";
  static {
    CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[2]++;
  }

  // A memoizer for formatting strings as JS strings.
  private final Map<String, String> ESCAPED_JS_STRINGS = Maps.newHashMap();
  {
    CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[3]++;
  }

  private static final char[] HEX_CHARS
      = { '0', '1', '2', '3', '4', '5', '6', '7',
          '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
  static {
    CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[4]++;
  }

  private final CodeConsumer cc;

  private final CharsetEncoder outputCharsetEncoder;

  private final boolean preferSingleQuotes;
  private final boolean trustedStrings;

  private CodeGenerator(CodeConsumer consumer) {
    cc = consumer;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[5]++;
    outputCharsetEncoder = null;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[6]++;
    preferSingleQuotes = false;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[7]++;
    trustedStrings = true;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[8]++;
  }

  static CodeGenerator forCostEstimation(CodeConsumer consumer) {
    return new CodeGenerator(consumer);
  }

  CodeGenerator(
      CodeConsumer consumer,
      CompilerOptions options) {
    cc = consumer;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[9]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[10]++;

    Charset outputCharset = options.getOutputCharset();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[11]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((outputCharset == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((outputCharset == Charsets.US_ASCII) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[1]++;
      // If we want our default (pretending to be UTF-8, but escaping anything
      // outside of straight ASCII), then don't use the encoder, but
      // just special-case the code.  This keeps the normal path through
      // the code identical to how it's been for years.
      this.outputCharsetEncoder = null;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[12]++;

    } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[2]++;
      this.outputCharsetEncoder = outputCharset.newEncoder();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[13]++;
    }
    this.preferSingleQuotes = options.preferSingleQuotes;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[14]++;
    this.trustedStrings = options.trustedStrings;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[15]++;
  }

  /**
   * Insert a ECMASCRIPT 5 strict annotation.
   */
  public void tagAsStrict() {
    add("'use strict';");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[16]++;
  }

  void add(String str) {
    cc.add(str);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[17]++;
  }

  private void addIdentifier(String identifier) {
    cc.addIdentifier(identifierEscape(identifier));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[18]++;
  }

  void add(Node n) {
    add(n, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[19]++;
  }

  void add(Node n, Context context) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[20]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((cc.continueProcessing()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[3]++;
      return;

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[4]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[21]++;

    int type = n.getType();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[22]++;
    String opstr = NodeUtil.opToStr(type);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[23]++;
    int childCount = n.getChildCount();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[24]++;
    Node first = n.getFirstChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[25]++;
    Node last = n.getLastChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[26]++;
int CodeCoverConditionCoverageHelper_C3;

    // Handle all binary operators
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((opstr != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((first != last) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[5]++;
      Preconditions.checkState(
          childCount == 2,
          "Bad binary operator \"%s\": expected 2 arguments but got %s",
          opstr, childCount);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[27]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[28]++;
      int p = NodeUtil.precedence(type);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[29]++;

      // For right-hand-side of operations, only pass context if it's
      // the IN_FOR_INIT_CLAUSE one.
      Context rhsContext = getContextForNoInOperator(context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[30]++;
int CodeCoverConditionCoverageHelper_C4;

      // Handle associativity.
      // e.g. if the parse tree is a * (b * c),
      // we can simply generate a * b * c.
      if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((last.getType() == type) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssociative(type)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[7]++;
        addExpr(first, p, context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[31]++;
        cc.addOp(opstr, true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[32]++;
        addExpr(last, p, rhsContext);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[33]++;

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[8]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[34]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (8)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(n)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((NodeUtil.isAssignmentOp(last)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[9]++;
        // Assignments are the only right-associative binary operators
        addExpr(first, p, context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[35]++;
        cc.addOp(opstr, true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[36]++;
        addExpr(last, p, rhsContext);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[37]++;

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[10]++;
        unrollBinaryOperator(n, type, opstr, context, rhsContext, p, p + 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[38]++;
      }
}
      return;

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[6]++;}

    cc.startSourceMapping(n);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[39]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[40]++;

    switch (type) {
      case Token.TRY:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[11]++; {
        Preconditions.checkState(first.getNext().isBlock() &&
                !first.getNext().hasMoreThanOneChild());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[41]++;
        Preconditions.checkState(childCount >= 2 && childCount <= 3);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[42]++;

        add("try");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[43]++;
        add(first, Context.PRESERVE_BLOCK);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[44]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[45]++;

        // second child contains the catch block, or nothing if there
        // isn't a catch block
        Node catchblock = first.getNext().getFirstChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[46]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((catchblock != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[12]++;
          add(catchblock);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[47]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[13]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[48]++;
int CodeCoverConditionCoverageHelper_C7;

        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((childCount == 3) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[14]++;
          add("finally");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[49]++;
          add(last, Context.PRESERVE_BLOCK);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[50]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[15]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[51]++;
        break;
      }

      case Token.CATCH:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[16]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[52]++;
        add("catch(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[53]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[54]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[55]++;
        add(last, Context.PRESERVE_BLOCK);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[56]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[57]++;
        break;

      case Token.THROW:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[17]++;
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[58]++;
        add("throw");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[59]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[60]++;

        // Must have a ';' after a throw statement, otherwise safari can't
        // parse this.
        cc.endStatement(true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[61]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[62]++;
        break;

      case Token.RETURN:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[18]++;
        add("return");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[63]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[64]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((childCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[19]++;
          add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[65]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[20]++;
          Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[66]++;
        }
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[67]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[68]++;
        break;

      case Token.VAR:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[21]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[69]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((first != null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[22]++;
          add("var ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[70]++;
          addList(first, false, getContextForNoInOperator(context));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[71]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[23]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[72]++;
        break;

      case Token.LABEL_NAME:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[24]++;
        Preconditions.checkState(!n.getString().isEmpty());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[73]++;
        addIdentifier(n.getString());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[74]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[75]++;
        break;

      case Token.NAME:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[25]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[76]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (8)) == 0 || true) &&
 ((first == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((first.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[26]++;
          addIdentifier(n.getString());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[77]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[27]++;
          Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[78]++;
          addIdentifier(n.getString());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[79]++;
          cc.addOp("=", true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[80]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[81]++;
int CodeCoverConditionCoverageHelper_C11;
          if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((first.isComma()) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[28]++;
            addExpr(first, NodeUtil.precedence(Token.ASSIGN), Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[82]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[29]++;
            // Add expression, consider nearby code at lowest level of
            // precedence.
            addExpr(first, 0, getContextForNoInOperator(context));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[83]++;
          }
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[84]++;
        break;

      case Token.ARRAYLIT:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[30]++;
        add("[");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[85]++;
        addArrayList(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[86]++;
        add("]");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[87]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[88]++;
        break;

      case Token.PARAM_LIST:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[31]++;
        add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[89]++;
        addList(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[90]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[91]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[92]++;
        break;

      case Token.COMMA:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[32]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[93]++;
        unrollBinaryOperator(n, Token.COMMA, ",", context, Context.OTHER, 0, 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[94]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[95]++;
        break;

      case Token.NUMBER:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[33]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[96]++;
        cc.addNumber(n.getDouble());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[97]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[98]++;
        break;

      case Token.TYPEOF:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[34]++;
      case Token.VOID:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[35]++;
      case Token.NOT:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[36]++;
      case Token.BITNOT:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[37]++;
      case Token.POS:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[38]++; {
        // All of these unary operators are right-associative
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[99]++;
        cc.addOp(NodeUtil.opToStrNoFail(type), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[100]++;
        addExpr(first, NodeUtil.precedence(type), Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[101]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[102]++;
        break;
      }

      case Token.NEG:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[39]++; {
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[103]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[104]++;
int CodeCoverConditionCoverageHelper_C12;

        // It's important to our sanity checker that the code
        // we print produces the same AST as the code we parse back.
        // NEG is a weird case because Rhino parses "- -2" as "2".
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((n.getFirstChild().isNumber()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[40]++;
          cc.addNumber(-n.getFirstChild().getDouble());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[105]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[41]++;
          cc.addOp(NodeUtil.opToStrNoFail(type), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[106]++;
          addExpr(first, NodeUtil.precedence(type), Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[107]++;
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[108]++;

        break;
      }

      case Token.HOOK:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[42]++; {
        Preconditions.checkState(childCount == 3);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[109]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[110]++;
        int p = NodeUtil.precedence(type);
        addExpr(first, p + 1, context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[111]++;
        cc.addOp("?", true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[112]++;
        addExpr(first.getNext(), 1, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[113]++;
        cc.addOp(":", true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[114]++;
        addExpr(last, 1, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[115]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[116]++;
        break;
      }

      case Token.REGEXP:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[43]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[117]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((first.isString()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((last.isString()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[44]++;
          throw new Error("Expected children to be strings");

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[45]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[118]++;

        String regexp = regexpEscape(first.getString(), outputCharsetEncoder);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[119]++;
int CodeCoverConditionCoverageHelper_C14;

        // I only use one .add because whitespace matters
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((childCount == 2) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[46]++;
          add(regexp + last.getString());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[120]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[47]++;
          Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[121]++;
          add(regexp);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[122]++;
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[123]++;
        break;

      case Token.FUNCTION:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[48]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[124]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((n.getClass() != Node.class) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[49]++;
          throw new Error("Unexpected Node subclass.");

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[50]++;}
        Preconditions.checkState(childCount == 3);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[125]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[126]++;
        boolean funcNeedsParens = (context == Context.START_OF_EXPR);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[127]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((funcNeedsParens) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[51]++;
          add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[128]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[52]++;}

        add("function");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[129]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[130]++;

        add(first.getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[131]++;
        add(last, Context.PRESERVE_BLOCK);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[132]++;
        cc.endFunction(context == Context.STATEMENT);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[133]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[134]++;
int CodeCoverConditionCoverageHelper_C17;

        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((funcNeedsParens) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[53]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[135]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[54]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[136]++;
        break;

      case Token.GETTER_DEF:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[55]++;
      case Token.SETTER_DEF:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[56]++;
        Preconditions.checkState(n.getParent().isObjectLit());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[137]++;
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[138]++;
        Preconditions.checkState(first.isFunction());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[139]++;

        // Get methods are unnamed
        Preconditions.checkState(first.getFirstChild().getString().isEmpty());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[140]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[141]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((type == Token.GETTER_DEF) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[57]++;
          // Get methods have no parameters.
          Preconditions.checkState(!first.getChildAtIndex(1).hasChildren());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[142]++;
          add("get ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[143]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[58]++;
          // Set methods have one parameter.
          Preconditions.checkState(first.getChildAtIndex(1).hasOneChild());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[144]++;
          add("set ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[145]++;
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[146]++;

        // The name is on the GET or SET node.
        String name = n.getString();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[147]++;
        Node fn = first;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[148]++;
        Node parameters = fn.getChildAtIndex(1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[149]++;
        Node body = fn.getLastChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[150]++;
int CodeCoverConditionCoverageHelper_C19;

        // Add the property name.
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((n.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(name)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((// do not encode literally any non-literal characters that were
            // Unicode escaped.
            NodeUtil.isLatin(name)) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[59]++;
          add(name);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[151]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[60]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[152]++;
          // Determine if the string is a simple number.
          double d = getSimpleNumber(name);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[153]++;
int CodeCoverConditionCoverageHelper_C20;
          if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((Double.isNaN(d)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[61]++;
            cc.addNumber(d);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[154]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[62]++;
            addJsString(n);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[155]++;
          }
        }

        add(parameters);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[156]++;
        add(body, Context.PRESERVE_BLOCK);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[157]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[158]++;
        break;

      case Token.SCRIPT:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[63]++;
      case Token.BLOCK:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[64]++; {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[159]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((n.getClass() != Node.class) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[65]++;
          throw new Error("Unexpected Node subclass.");

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[66]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[160]++;
        boolean preserveBlock = context == Context.PRESERVE_BLOCK;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[161]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((preserveBlock) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[67]++;
          cc.beginBlock();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[162]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[68]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[163]++;

        boolean preferLineBreaks =
            type == Token.SCRIPT ||
            (type == Token.BLOCK &&
                !preserveBlock &&
                n.getParent() != null &&
                n.getParent().isScript());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[164]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[1]++;


int CodeCoverConditionCoverageHelper_C23;
        for (Node c = first;(((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[1]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[2]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[3]++;
}
          add(c, Context.STATEMENT);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[165]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[166]++;
int CodeCoverConditionCoverageHelper_C24;

          // VAR doesn't include ';' since it gets used in expressions
          if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((c.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[69]++;
            cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[167]++;

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[70]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[168]++;
int CodeCoverConditionCoverageHelper_C25;

          if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((c.isFunction()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[71]++;
            cc.maybeLineBreak();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[169]++;

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[72]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[170]++;
int CodeCoverConditionCoverageHelper_C26;

          // Prefer to break lines in between top-level statements
          // because top-level statements are more homogeneous.
          if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((preferLineBreaks) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[73]++;
            cc.notePreferredLineBreak();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[171]++;

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[74]++;}
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[172]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((preserveBlock) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[75]++;
          cc.endBlock(cc.breakAfterBlockFor(n, context == Context.STATEMENT));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[173]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[76]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[174]++;
        break;
      }

      case Token.FOR:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[77]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[175]++;
int CodeCoverConditionCoverageHelper_C28;
        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((childCount == 4) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[78]++;
          add("for(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[176]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[177]++;
int CodeCoverConditionCoverageHelper_C29;
          if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((first.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[80]++;
            add(first, Context.IN_FOR_INIT_CLAUSE);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[178]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[81]++;
            addExpr(first, 0, Context.IN_FOR_INIT_CLAUSE);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[179]++;
          }
          add(";");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[180]++;
          add(first.getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[181]++;
          add(";");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[182]++;
          add(first.getNext().getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[183]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[184]++;
          addNonEmptyStatement(
              last, getContextForNonEmptyExpression(context), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[185]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[79]++;
          Preconditions.checkState(childCount == 3);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[186]++;
          add("for(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[187]++;
          add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[188]++;
          add("in");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[189]++;
          add(first.getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[190]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[191]++;
          addNonEmptyStatement(
              last, getContextForNonEmptyExpression(context), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[192]++;
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[193]++;
        break;

      case Token.DO:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[82]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[194]++;
        add("do");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[195]++;
        addNonEmptyStatement(first, Context.OTHER, false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[196]++;
        add("while(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[197]++;
        add(last);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[198]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[199]++;
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[200]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[201]++;
        break;

      case Token.WHILE:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[83]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[202]++;
        add("while(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[203]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[204]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[205]++;
        addNonEmptyStatement(
            last, getContextForNonEmptyExpression(context), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[206]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[207]++;
        break;

      case Token.EMPTY:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[84]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[208]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[209]++;
        break;

      case Token.GETPROP:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[85]++; {
        Preconditions.checkState(
            childCount == 2,
            "Bad GETPROP: expected 2 children, but got %s", childCount);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[210]++;
        Preconditions.checkState(
            last.isString(),
            "Bad GETPROP: RHS should be STRING");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[211]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[212]++;
        boolean needsParens = (first.isNumber());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[213]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((needsParens) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[86]++;
          add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[214]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[87]++;}
        addExpr(first, NodeUtil.precedence(type), context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[215]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[216]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((needsParens) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[88]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[217]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[89]++;}
        add(".");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[218]++;
        addIdentifier(last.getString());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[219]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[220]++;
        break;
      }

      case Token.GETELEM:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[90]++;
        Preconditions.checkState(
            childCount == 2,
            "Bad GETELEM: expected 2 children but got %s", childCount);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[221]++;
        addExpr(first, NodeUtil.precedence(type), context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[222]++;
        add("[");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[223]++;
        add(first.getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[224]++;
        add("]");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[225]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[226]++;
        break;

      case Token.WITH:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[91]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[227]++;
        add("with(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[228]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[229]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[230]++;
        addNonEmptyStatement(
            last, getContextForNonEmptyExpression(context), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[231]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[232]++;
        break;

      case Token.INC:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[92]++;
      case Token.DEC:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[93]++; {
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[233]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[234]++;
        String o = type == Token.INC ? "++" : "--";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[235]++;
        int postProp = n.getIntProp(Node.INCRDECR_PROP);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[236]++;
int CodeCoverConditionCoverageHelper_C32;
        // A non-zero post-prop value indicates a post inc/dec, default of zero
        // is a pre-inc/dec.
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((postProp != 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[94]++;
          addExpr(first, NodeUtil.precedence(type), context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[237]++;
          cc.addOp(o, false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[238]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[95]++;
          cc.addOp(o, false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[239]++;
          add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[240]++;
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[241]++;
        break;
      }

      case Token.CALL:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[96]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[242]++;
int CodeCoverConditionCoverageHelper_C33;
        // We have two special cases here:
        // 1) If the left hand side of the call is a direct reference to eval,
        // then it must have a DIRECT_EVAL annotation. If it does not, then
        // that means it was originally an indirect call to eval, and that
        // indirectness must be preserved.
        // 2) If the left hand side of the call is a property reference,
        // then the call must not a FREE_CALL annotation. If it does, then
        // that means it was originally an call without an explicit this and
        // that must be preserved.
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (32)) == 0 || true) &&
 ((isIndirectEval(first)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C33 |= (8)) == 0 || true) &&
 ((n.getBooleanProp(Node.FREE_CALL)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((NodeUtil.isGet(first)) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 3) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 3) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[97]++;
          add("(0,");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[243]++;
          addExpr(first, NodeUtil.precedence(Token.COMMA), Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[244]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[245]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[98]++;
          addExpr(first, NodeUtil.precedence(type), context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[246]++;
        }
        add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[247]++;
        addList(first.getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[248]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[249]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[250]++;
        break;

      case Token.IF:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[99]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[251]++;
        boolean hasElse = childCount == 3;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[252]++;
        boolean ambiguousElseClause =
            context == Context.BEFORE_DANGLING_ELSE && !hasElse;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[253]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((ambiguousElseClause) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[100]++;
          cc.beginBlock();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[254]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[101]++;}

        add("if(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[255]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[256]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[257]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[258]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((hasElse) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[102]++;
          addNonEmptyStatement(
              first.getNext(), Context.BEFORE_DANGLING_ELSE, false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[259]++;
          add("else");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[260]++;
          addNonEmptyStatement(
              last, getContextForNonEmptyExpression(context), false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[261]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[103]++;
          addNonEmptyStatement(first.getNext(), Context.OTHER, false);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[262]++;
          Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[263]++;
        }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[264]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((ambiguousElseClause) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[104]++;
          cc.endBlock();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[265]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[105]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[266]++;
        break;

      case Token.NULL:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[106]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[267]++;
        cc.addConstant("null");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[268]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[269]++;
        break;

      case Token.THIS:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[107]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[270]++;
        add("this");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[271]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[272]++;
        break;

      case Token.FALSE:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[108]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[273]++;
        cc.addConstant("false");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[274]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[275]++;
        break;

      case Token.TRUE:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[109]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[276]++;
        cc.addConstant("true");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[277]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[278]++;
        break;

      case Token.CONTINUE:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[110]++;
        Preconditions.checkState(childCount <= 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[279]++;
        add("continue");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[280]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[281]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((childCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[111]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[282]++;
int CodeCoverConditionCoverageHelper_C38;
          if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((first.isLabelName()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[113]++;
            throw new Error("Unexpected token type. Should be LABEL_NAME.");

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[114]++;}
          add(" ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[283]++;
          add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[284]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[112]++;}
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[285]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[286]++;
        break;

      case Token.DEBUGGER:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[115]++;
        Preconditions.checkState(childCount == 0);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[287]++;
        add("debugger");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[288]++;
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[289]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[290]++;
        break;

      case Token.BREAK:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[116]++;
        Preconditions.checkState(childCount <= 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[291]++;
        add("break");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[292]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[293]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((childCount == 1) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[117]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[294]++;
int CodeCoverConditionCoverageHelper_C40;
          if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((first.isLabelName()) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[119]++;
            throw new Error("Unexpected token type. Should be LABEL_NAME.");

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[120]++;}
          add(" ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[295]++;
          add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[296]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[118]++;}
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[297]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[298]++;
        break;

      case Token.EXPR_RESULT:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[121]++;
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[299]++;
        add(first, Context.START_OF_EXPR);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[300]++;
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[301]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[302]++;
        break;

      case Token.NEW:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[122]++;
        add("new ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[303]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[304]++;
        int precedence = NodeUtil.precedence(type);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[305]++;
int CodeCoverConditionCoverageHelper_C41;

        // If the first child contains a CALL, then claim higher precedence
        // to force parentheses. Otherwise, when parsed, NEW will bind to the
        // first viable parentheses (don't traverse into functions).
        if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((NodeUtil.containsType(
            first, Token.CALL, NodeUtil.MATCH_NOT_FUNCTION)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[123]++;
          precedence = NodeUtil.precedence(first.getType()) + 1;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[306]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[124]++;}
        addExpr(first, precedence, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[307]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[308]++;

        // '()' is optional when no arguments are present
        Node next = first.getNext();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[309]++;
int CodeCoverConditionCoverageHelper_C42;
        if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[125]++;
          add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[310]++;
          addList(next);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[311]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[312]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[126]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[313]++;
        break;

      case Token.STRING_KEY:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[127]++;
        Preconditions.checkState(
            childCount == 1, "Object lit key must have 1 child");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[314]++;
        addJsString(n);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[315]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[316]++;
        break;

      case Token.STRING:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[128]++;
        Preconditions.checkState(
            childCount == 0, "A string may not have children");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[317]++;
        addJsString(n);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[318]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[319]++;
        break;

      case Token.DELPROP:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[129]++;
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[320]++;
        add("delete ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[321]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[322]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[323]++;
        break;

      case Token.OBJECTLIT:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[130]++; {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[324]++;
        boolean needsParens = (context == Context.START_OF_EXPR);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[325]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((needsParens) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[131]++;
          add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[326]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[132]++;}
        add("{");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[327]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[328]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[4]++;


int CodeCoverConditionCoverageHelper_C44;
        for (Node c = first;(((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[4]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[5]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[6]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[329]++;
int CodeCoverConditionCoverageHelper_C45;
          if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((c != first) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[133]++;
            cc.listSeparator();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[330]++;

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[134]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[331]++;
int CodeCoverConditionCoverageHelper_C46;

          if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (8)) == 0 || true) &&
 ((c.isGetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((c.isSetterDef()) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[135]++;
            add(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[332]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[136]++;
            Preconditions.checkState(c.isStringKey());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[333]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[334]++;
            String key = c.getString();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[335]++;
int CodeCoverConditionCoverageHelper_C47;
            // Object literal property names don't have to be quoted if they
            // are not JavaScript keywords
            if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (128)) == 0 || true) &&
 ((c.isQuotedString()) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (64)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C47 |= (32)) == 0 || true) &&
 ((TokenStream.isKeyword(key)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (8)) == 0 || true) &&
 ((TokenStream.isJSIdentifier(key)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((// do not encode literally any non-literal characters that
                // were Unicode escaped.
                NodeUtil.isLatin(key)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 4) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 4) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[137]++;
              add(key);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[336]++;

            } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[138]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[337]++;
              // Determine if the string is a simple number.
              double d = getSimpleNumber(key);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[338]++;
int CodeCoverConditionCoverageHelper_C48;
              if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((Double.isNaN(d)) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[139]++;
                cc.addNumber(d);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[339]++;

              } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[140]++;
                addExpr(c, 1, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[340]++;
              }
            }
            add(":");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[341]++;
            addExpr(c.getFirstChild(), 1, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[342]++;
          }
        }
        add("}");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[343]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[344]++;
int CodeCoverConditionCoverageHelper_C49;
        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((needsParens) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[141]++;
          add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[345]++;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[142]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[346]++;
        break;
      }

      case Token.SWITCH:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[143]++;
        add("switch(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[347]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[348]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[349]++;
        cc.beginBlock();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[350]++;
        addAllSiblings(first.getNext());
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[351]++;
        cc.endBlock(context == Context.STATEMENT);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[352]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[353]++;
        break;

      case Token.CASE:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[144]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[354]++;
        add("case ");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[355]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[356]++;
        addCaseBody(last);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[357]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[358]++;
        break;

      case Token.DEFAULT_CASE:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[145]++;
        Preconditions.checkState(childCount == 1);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[359]++;
        add("default");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[360]++;
        addCaseBody(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[361]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[362]++;
        break;

      case Token.LABEL:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[146]++;
        Preconditions.checkState(childCount == 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[363]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[364]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((first.isLabelName()) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[147]++;
          throw new Error("Unexpected token type. Should be LABEL_NAME.");

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[148]++;}
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[365]++;
        add(":");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[366]++;
        addNonEmptyStatement(
            last, getContextForNonEmptyExpression(context), true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[367]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[368]++;
        break;

      case Token.CAST:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[149]++;
        add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[369]++;
        add(first);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[370]++;
        add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[371]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[372]++;
        break;

      default:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[150]++;
        throw new Error("Unknown type " + type + "\n" + n.toStringTree());
    }

    cc.endSourceMapping(n);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[373]++;
  }

  /**
   * We could use addList recursively here, but sometimes we produce
   * very deeply nested operators and run out of stack space, so we
   * just unroll the recursion when possible.
   *
   * We assume nodes are left-recursive.
   */
  private void unrollBinaryOperator(
      Node n, int op, String opStr, Context context,
      Context rhsContext, int leftPrecedence, int rightPrecedence) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[374]++;
    Node firstNonOperator = n.getFirstChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[375]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[7]++;


int CodeCoverConditionCoverageHelper_C51;
    while ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((firstNonOperator.getType() == op) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[7]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[8]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[9]++;
}
      firstNonOperator = firstNonOperator.getFirstChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[376]++;
    }

    addExpr(firstNonOperator, leftPrecedence, context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[377]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[378]++;

    Node current = firstNonOperator;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[379]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[10]++;


int CodeCoverConditionCoverageHelper_C52;
    do {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[10]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[11]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[12]++;
}
      current = current.getParent();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[380]++;
      cc.addOp(opStr, true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[381]++;
      addExpr(current.getFirstChild().getNext(), rightPrecedence, rhsContext);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[382]++;
    } while ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((current != n) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false));
  }

  static boolean isSimpleNumber(String s) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[383]++;
    int len = s.length();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[384]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[13]++;


int CodeCoverConditionCoverageHelper_C53;
    for (int index = 0;(((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((index < len) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false); index++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[13]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[14]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[15]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[385]++;
      char c = s.charAt(index);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[386]++;
int CodeCoverConditionCoverageHelper_C54;
      if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (8)) == 0 || true) &&
 ((c < '0') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((c > '9') && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[151]++;
        return false;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[152]++;}
    }
    return len > 0 && s.charAt(0) != '0';
  }

  static double getSimpleNumber(String s) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[387]++;
int CodeCoverConditionCoverageHelper_C55;
    if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((isSimpleNumber(s)) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[153]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[388]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
      try {
CodeCoverTryBranchHelper_Try1 = true;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[389]++;
        long l = Long.parseLong(s);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[390]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((l < NodeUtil.MAX_POSITIVE_INTEGER_NUMBER) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[156]++;
          return l;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[157]++;}
      } catch (NumberFormatException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[158]++;
        // The number was too long to parse. Fall through to NaN.
      } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[155]++;
}
  }

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[154]++;}
    return Double.NaN;
  }

  /**
   * @return Whether the name is an indirect eval.
   */
  private boolean isIndirectEval(Node n) {
    return n.isName() && "eval".equals(n.getString()) &&
        !n.getBooleanProp(Node.DIRECT_EVAL);
  }

  /**
   * Adds a block or expression, substituting a VOID with an empty statement.
   * This is used for "for (...);" and "if (...);" type statements.
   *
   * @param n The node to print.
   * @param context The context to determine how the node should be printed.
   */
  private void addNonEmptyStatement(
      Node n, Context context, boolean allowNonBlockChild) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[391]++;
    Node nodeToProcess = n;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[392]++;
int CodeCoverConditionCoverageHelper_C57;

    if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((allowNonBlockChild) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[159]++;
      throw new Error("Missing BLOCK child.");

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[160]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[393]++;
int CodeCoverConditionCoverageHelper_C58;

    // Strip unneeded blocks, that is blocks with <2 children unless
    // the CodePrinter specifically wants to keep them.
    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((n.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[161]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[394]++;
      int count = getNonEmptyChildCount(n, 2);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[395]++;
int CodeCoverConditionCoverageHelper_C59;
      if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[163]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[396]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((cc.shouldPreserveExtraBlocks()) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[165]++;
          cc.beginBlock();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[397]++;
          cc.endBlock(cc.breakAfterBlockFor(n, context == Context.STATEMENT));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[398]++;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[166]++;
          cc.endStatement(true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[399]++;
        }
        return;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[164]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[400]++;
int CodeCoverConditionCoverageHelper_C61;

      if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((count == 1) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[167]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[401]++;
        // Hack around a couple of browser bugs:
        //   Safari needs a block around function declarations.
        //   IE6/7 needs a block around DOs.
        Node firstAndOnlyChild = getFirstNonEmptyChild(n);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[402]++;
        boolean alwaysWrapInBlock = cc.shouldPreserveExtraBlocks();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[403]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((alwaysWrapInBlock) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((isOneExactlyFunctionOrDo(firstAndOnlyChild)) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[169]++;
          cc.beginBlock();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[404]++;
          add(firstAndOnlyChild, Context.STATEMENT);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[405]++;
          cc.maybeLineBreak();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[406]++;
          cc.endBlock(cc.breakAfterBlockFor(n, context == Context.STATEMENT));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[407]++;
          return;

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[170]++;
          // Continue with the only child.
          nodeToProcess = firstAndOnlyChild;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[408]++;
        }

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[168]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[409]++;
int CodeCoverConditionCoverageHelper_C63;

      if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((count > 1) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[171]++;
        context = Context.PRESERVE_BLOCK;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[410]++;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[172]++;}

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[162]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[411]++;
int CodeCoverConditionCoverageHelper_C64;

    if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((nodeToProcess.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[173]++;
      cc.endStatement(true);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[412]++;

    } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[174]++;
      add(nodeToProcess, context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[413]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[414]++;
int CodeCoverConditionCoverageHelper_C65;

      // VAR doesn't include ';' since it gets used in expressions - so any
      // VAR in a statement context needs a call to endStatement() here.
      if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((nodeToProcess.isVar()) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[175]++;
        cc.endStatement();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[415]++;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[176]++;}
    }
  }

  /**
   * @return Whether the Node is a DO or FUNCTION (with or without
   * labels).
   */
  private boolean isOneExactlyFunctionOrDo(Node n) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[416]++;
int CodeCoverConditionCoverageHelper_C66;
    if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((n.isLabel()) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[177]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[417]++;
      Node labeledStatement = n.getLastChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[418]++;
int CodeCoverConditionCoverageHelper_C67;
      if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((labeledStatement.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[179]++;
        return isOneExactlyFunctionOrDo(labeledStatement);

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[180]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[419]++;
int CodeCoverConditionCoverageHelper_C68;
        // For labels with block children, we need to ensure that a
        // labeled FUNCTION or DO isn't generated when extraneous BLOCKs
        // are skipped.
        if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((getNonEmptyChildCount(n, 2) == 1) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[181]++;
          return isOneExactlyFunctionOrDo(getFirstNonEmptyChild(n));

        } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[182]++;
          // Either a empty statement or an block with more than one child,
          // way it isn't a FUNCTION or DO.
          return false;
        }
      }

    } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[178]++;
      return (n.isFunction() || n.isDo());
    }
  }

  private void addExpr(Node n, int minPrecedence, Context context) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[420]++;
int CodeCoverConditionCoverageHelper_C69;
    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C69 |= (32)) == 0 || true) &&
 ((NodeUtil.precedence(n.getType()) < minPrecedence) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (16)) == 0 || true)))
) || ((
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((context == Context.IN_FOR_INIT_CLAUSE) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
) && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((n.isIn()) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 3) && false)){
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[183]++;
      add("(");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[421]++;
      add(n, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[422]++;
      add(")");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[423]++;

    } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[184]++;
      add(n, context);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[424]++;
    }
  }

  void addList(Node firstInList) {
    addList(firstInList, true, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[425]++;
  }

  void addList(Node firstInList, boolean isArrayOrFunctionArgument) {
    addList(firstInList, isArrayOrFunctionArgument, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[426]++;
  }

  void addList(Node firstInList, boolean isArrayOrFunctionArgument,
               Context lhsContext) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[427]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[16]++;


int CodeCoverConditionCoverageHelper_C70;
    for (Node n = firstInList;(((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[16]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[17]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[18]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[428]++;
      boolean isFirst = n == firstInList;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[429]++;
int CodeCoverConditionCoverageHelper_C71;
      if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((isFirst) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[185]++;
        addExpr(n, isArrayOrFunctionArgument ? 1 : 0, lhsContext);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[430]++;

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[186]++;
        cc.listSeparator();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[431]++;
        addExpr(n, isArrayOrFunctionArgument ? 1 : 0, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[432]++;
      }
    }
  }

  /**
   * This function adds a comma-separated list as is specified by an ARRAYLIT
   * node with the associated skipIndexes array.  This is a space optimization
   * since we avoid creating a whole Node object for each empty array literal
   * slot.
   * @param firstInList The first in the node list (chained through the next
   * property).
   */
  void addArrayList(Node firstInList) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[433]++;
    boolean lastWasEmpty = false;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[434]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[19]++;


int CodeCoverConditionCoverageHelper_C72;
    for (Node n = firstInList;(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[19]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[20]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[21]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[435]++;
int CodeCoverConditionCoverageHelper_C73;
      if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((n != firstInList) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[187]++;
        cc.listSeparator();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[436]++;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[188]++;}
      addExpr(n, 1, Context.OTHER);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[437]++;
      lastWasEmpty = n.isEmpty();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[438]++;
    }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[439]++;
int CodeCoverConditionCoverageHelper_C74;

    if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((lastWasEmpty) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[189]++;
      cc.listSeparator();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[440]++;

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[190]++;}
  }

  void addCaseBody(Node caseBody) {
    cc.beginCaseBody();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[441]++;
    add(caseBody);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[442]++;
    cc.endCaseBody();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[443]++;
  }

  void addAllSiblings(Node n) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[444]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[22]++;


int CodeCoverConditionCoverageHelper_C75;
    for (Node c = n;(((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[22]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[23]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[24]++;
}
      add(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[445]++;
    }
  }

  /** Outputs a JS string, using the optimal (single/double) quote character */
  private void addJsString(Node n) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[446]++;
    String s = n.getString();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[447]++;
    boolean useSlashV = n.getBooleanProp(Node.SLASH_V);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[448]++;
int CodeCoverConditionCoverageHelper_C76;
    if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((useSlashV) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[191]++;
      add(jsString(n.getString(), useSlashV));
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[449]++;

    } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[192]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[450]++;
      String cached = ESCAPED_JS_STRINGS.get(s);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[451]++;
int CodeCoverConditionCoverageHelper_C77;
      if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((cached == null) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[193]++;
        cached = jsString(n.getString(), useSlashV);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[452]++;
        ESCAPED_JS_STRINGS.put(s, cached);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[453]++;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[194]++;}
      add(cached);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[454]++;
    }
  }

  private String jsString(String s, boolean useSlashV) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[455]++;
    int singleq = 0, doubleq = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[456]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[25]++;


int CodeCoverConditionCoverageHelper_C78;

    // could count the quotes and pick the optimal quote character
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((i < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[25]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[26]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[27]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[457]++;
      switch (s.charAt(i)) {
        case '"':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[195]++; doubleq++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[458]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[459]++; break;
        case '\'':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[196]++; singleq++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[460]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[461]++; break; default : CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[197]++;
      }
    }

    String doublequote, singlequote;
    char quote;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[462]++;
int CodeCoverConditionCoverageHelper_C79;
    if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (32)) == 0 || true) &&
 ((preferSingleQuotes) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (16)) == 0 || true)))
 ? (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((singleq <= doubleq) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
) : (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((singleq < doubleq) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 3) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 3) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[198]++;
      // more double quotes so enclose in single quotes.
      quote = '\'';
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[463]++;
      doublequote = "\"";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[464]++;
      singlequote = "\\\'";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[465]++;

    } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[199]++;
      // more single quotes so escape the doubles
      quote = '\"';
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[466]++;
      doublequote = "\\\"";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[467]++;
      singlequote = "\'";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[468]++;
    }

    return strEscape(s, quote, doublequote, singlequote, "\\\\",
        outputCharsetEncoder, useSlashV, false);
  }

  /** Escapes regular expression */
  String regexpEscape(String s, CharsetEncoder outputCharsetEncoder) {
    return strEscape(s, '/', "\"", "'", "\\", outputCharsetEncoder, false, true);
  }

  /**
   * Escapes the given string to a double quoted (") JavaScript/JSON string
   */
  String escapeToDoubleQuotedJsString(String s) {
    return strEscape(s, '"',  "\\\"", "\'", "\\\\", null, false, false);
  }

  /* If the user doesn't want to specify an output charset encoder, assume
     they want Latin/ASCII characters only.
   */
  String regexpEscape(String s) {
    return regexpEscape(s, null);
  }

  /** Helper to escape JavaScript string as well as regular expression */
  private String strEscape(
      String s,
      char quote,
      String doublequoteEscape,
      String singlequoteEscape,
      String backslashEscape,
      CharsetEncoder outputCharsetEncoder,
      boolean useSlashV,
      boolean isRegexp) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[469]++;
    StringBuilder sb = new StringBuilder(s.length() + 2);
    sb.append(quote);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[470]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[471]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[28]++;


int CodeCoverConditionCoverageHelper_C80;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((i < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[28]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[29]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[30]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[472]++;
      char c = s.charAt(i);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[473]++;
      switch (c) {
        case '\0':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[200]++; sb.append("\\x00");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[474]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[475]++; break;
        case '\u000B':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[201]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[476]++;
int CodeCoverConditionCoverageHelper_C81;
          if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((useSlashV) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[202]++;
            sb.append("\\v");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[477]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[203]++;
            sb.append("\\x0B");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[478]++;
          }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[479]++;
          break;
        // From the SingleEscapeCharacter grammar production.
        case '\b':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[204]++; sb.append("\\b");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[480]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[481]++; break;
        case '\f':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[205]++; sb.append("\\f");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[482]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[483]++; break;
        case '\n':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[206]++; sb.append("\\n");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[484]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[485]++; break;
        case '\r':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[207]++; sb.append("\\r");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[486]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[487]++; break;
        case '\t':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[208]++; sb.append("\\t");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[488]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[489]++; break;
        case '\\':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[209]++; sb.append(backslashEscape);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[490]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[491]++; break;
        case '\"':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[210]++; sb.append(doublequoteEscape);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[492]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[493]++; break;
        case '\'':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[211]++; sb.append(singlequoteEscape);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[494]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[495]++; break;

        // From LineTerminators (ES5 Section 7.3, Table 3)
        case '\u2028':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[212]++; sb.append("\\u2028");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[496]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[497]++; break;
        case '\u2029':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[213]++; sb.append("\\u2029");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[498]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[499]++; break;

        case '=':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[214]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[500]++;
int CodeCoverConditionCoverageHelper_C82;
          // '=' is a syntactically signficant regexp character.
          if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (8)) == 0 || true) &&
 ((trustedStrings) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((isRegexp) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[215]++;
            sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[501]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[216]++;
            sb.append("\\x3d");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[502]++;
          }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[503]++;
          break;

        case '&':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[217]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[504]++;
int CodeCoverConditionCoverageHelper_C83;
          if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (8)) == 0 || true) &&
 ((trustedStrings) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((isRegexp) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[218]++;
            sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[505]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[219]++;
            sb.append("\\x26");
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[506]++;
          }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[507]++;
          break;

        case '>':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[220]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[508]++;
int CodeCoverConditionCoverageHelper_C84;
          if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C84 |= (8)) == 0 || true) &&
 ((trustedStrings) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((isRegexp) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[221]++;
            sb.append(GT_ESCAPED);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[509]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[510]++;
            break;

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[222]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[511]++;
int CodeCoverConditionCoverageHelper_C85;

          // Break --> into --\> or ]]> into ]]\>
          //
          // This is just to prevent developers from shooting themselves in the
          // foot, and does not provide the level of security that you get
          // with trustedString == false.
          if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (512)) == 0 || true) &&
 ((i >= 2) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (256)) == 0 || true)))
 && ((
(((CodeCoverConditionCoverageHelper_C85 |= (128)) == 0 || true) &&
 ((s.charAt(i - 1) == '-') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (32)) == 0 || true) &&
 ((s.charAt(i - 2) == '-') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((s.charAt(i - 1) == ']') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((s.charAt(i - 2) == ']') && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)))) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 5) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 5) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[223]++;
            sb.append(GT_ESCAPED);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[512]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[224]++;
            sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[513]++;
          }
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[514]++;
          break;
        case '<':
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[225]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[515]++;
int CodeCoverConditionCoverageHelper_C86;
          if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C86 |= (8)) == 0 || true) &&
 ((trustedStrings) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((isRegexp) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[226]++;
            sb.append(LT_ESCAPED);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[516]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[517]++;
            break;

          } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[227]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[518]++;

          // Break </script into <\/script
          // As above, this is just to prevent developers from doing this
          // accidentally.
          final String END_SCRIPT = "/script";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[519]++;

          // Break <!-- into <\!--
          final String START_COMMENT = "!--";
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[520]++;
int CodeCoverConditionCoverageHelper_C87;

          if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((s.regionMatches(true, i + 1, END_SCRIPT, 0,
                              END_SCRIPT.length())) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[228]++;
            sb.append(LT_ESCAPED);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[521]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[229]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[522]++;
int CodeCoverConditionCoverageHelper_C88; if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((s.regionMatches(false, i + 1, START_COMMENT, 0,
                                     START_COMMENT.length())) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[230]++;
            sb.append(LT_ESCAPED);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[523]++;

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[231]++;
            sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[524]++;
          }
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[525]++;
          break;
        default:
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[232]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[526]++;
int CodeCoverConditionCoverageHelper_C89;
          // If we're given an outputCharsetEncoder, then check if the
          //  character can be represented in this character set.
          if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((outputCharsetEncoder != null) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[233]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[527]++;
int CodeCoverConditionCoverageHelper_C90;
            if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((outputCharsetEncoder.canEncode(c)) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[235]++;
              sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[528]++;

            } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[236]++;
              // Unicode-escape the character.
              appendHexJavaScriptRepresentation(sb, c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[529]++;
            }

          } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[234]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[530]++;
int CodeCoverConditionCoverageHelper_C91;
            // No charsetEncoder provided - pass straight Latin characters
            // through, and escape the rest.  Doing the explicit character
            // check is measurably faster than using the CharsetEncoder.
            if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (8)) == 0 || true) &&
 ((c > 0x1f) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((c < 0x7f) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[237]++;
              sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[531]++;

            } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[238]++;
              // Other characters can be misinterpreted by some JS parsers,
              // or perhaps mangled by proxies along the way,
              // so we play it safe and Unicode escape them.
              appendHexJavaScriptRepresentation(sb, c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[532]++;
            }
          }
      }
    }
    sb.append(quote);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[533]++;
    return sb.toString();
  }

  static String identifierEscape(String s) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[534]++;
int CodeCoverConditionCoverageHelper_C92;
    // First check if escaping is needed at all -- in most cases it isn't.
    if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((NodeUtil.isLatin(s)) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[239]++;
      return s;

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[240]++;}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[535]++;

    // Now going through the string to escape non-Latin characters if needed.
    StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[536]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[31]++;


int CodeCoverConditionCoverageHelper_C93;
    for (int i = 0;(((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((i < s.length()) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[31]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[32]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[33]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[537]++;
      char c = s.charAt(i);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[538]++;
int CodeCoverConditionCoverageHelper_C94;
      // Identifiers should always go to Latin1/ ASCII characters because
      // different browser's rules for valid identifier characters are
      // crazy.
      if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (8)) == 0 || true) &&
 ((c > 0x1F) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((c < 0x7F) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 2) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[241]++;
        sb.append(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[539]++;

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[242]++;
        appendHexJavaScriptRepresentation(sb, c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[540]++;
      }
    }
    return sb.toString();
  }
  /**
   * @param maxCount The maximum number of children to look for.
   * @return The number of children of this node that are non empty up to
   * maxCount.
   */
  private static int getNonEmptyChildCount(Node n, int maxCount) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[541]++;
    int i = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[542]++;
    Node c = n.getFirstChild();
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[543]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[34]++;


int CodeCoverConditionCoverageHelper_C95;
    for (;(((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (8)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((i < maxCount) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 2) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[34]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[35]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[36]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[544]++;
int CodeCoverConditionCoverageHelper_C96;
      if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((c.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[243]++;
        i += getNonEmptyChildCount(c, maxCount-i);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[545]++;

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[244]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[546]++;
int CodeCoverConditionCoverageHelper_C97; if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((c.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[245]++;
        i++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[547]++;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[246]++;}
}
    }
    return i;
  }

  /** Gets the first non-empty child of the given node. */
  private static Node getFirstNonEmptyChild(Node n) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[548]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[37]++;


int CodeCoverConditionCoverageHelper_C98;
    for (Node c = n.getFirstChild();(((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((c != null) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false); c = c.getNext()) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[37]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[38]--;
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.loops[39]++;
}
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[549]++;
int CodeCoverConditionCoverageHelper_C99;
      if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((c.isBlock()) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[247]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[550]++;
        Node result = getFirstNonEmptyChild(c);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[551]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[249]++;
          return result;

        } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[250]++;}

      } else {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[248]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[552]++;
int CodeCoverConditionCoverageHelper_C101; if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((c.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[251]++;
        return c;

      } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[252]++;}
}
    }
    return null;
  }

  // Information on the current context. Used for disambiguating special cases.
  // For example, a "{" could indicate the start of an object literal or a
  // block, depending on the current context.
  enum Context {
    STATEMENT,
    BEFORE_DANGLING_ELSE, // a hack to resolve the else-clause ambiguity
    START_OF_EXPR,
    PRESERVE_BLOCK,
    // Are we inside the init clause of a for loop?  If so, the containing
    // expression can't contain an in operator.  Pass this context flag down
    // until we reach expressions which no longer have the limitation.
    IN_FOR_INIT_CLAUSE,
    OTHER
  }

  private Context getContextForNonEmptyExpression(Context currentContext) {
    return currentContext == Context.BEFORE_DANGLING_ELSE ?
        Context.BEFORE_DANGLING_ELSE : Context.OTHER;
  }

  /**
   * If we're in a IN_FOR_INIT_CLAUSE, we can't permit in operators in the
   * expression.  Pass on the IN_FOR_INIT_CLAUSE flag through subexpressions.
   */
  private  Context getContextForNoInOperator(Context context) {
    return (context == Context.IN_FOR_INIT_CLAUSE
        ? Context.IN_FOR_INIT_CLAUSE : Context.OTHER);
  }

  /**
   * @see #appendHexJavaScriptRepresentation(int, Appendable)
   */
  private static void appendHexJavaScriptRepresentation(
      StringBuilder sb, char c) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[553]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
    try {
CodeCoverTryBranchHelper_Try2 = true;
      appendHexJavaScriptRepresentation(c, sb);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[554]++;
    } catch (IOException ex) {
CodeCoverTryBranchHelper_Try2 = false;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[254]++;
      // StringBuilder does not throw IOException.
      throw new RuntimeException(ex);
    } finally {
    if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[253]++;
}
  }
  }

  /**
   * Returns a JavaScript representation of the character in a hex escaped
   * format.
   *
   * @param codePoint The code point to append.
   * @param out The buffer to which the hex representation should be appended.
   */
  private static void appendHexJavaScriptRepresentation(
      int codePoint, Appendable out)
      throws IOException {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[555]++;
int CodeCoverConditionCoverageHelper_C102;
    if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((Character.isSupplementaryCodePoint(codePoint)) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[255]++;
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[556]++;
      // Handle supplementary Unicode values which are not representable in
      // JavaScript.  We deal with these by escaping them as two 4B sequences
      // so that they will round-trip properly when sent from Java to JavaScript
      // and back.
      char[] surrogates = Character.toChars(codePoint);
      appendHexJavaScriptRepresentation(surrogates[0], out);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[557]++;
      appendHexJavaScriptRepresentation(surrogates[1], out);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[558]++;
      return;

    } else {
  CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.branches[256]++;}
    out.append("\\u")
        .append(HEX_CHARS[(codePoint >>> 12) & 0xf])
        .append(HEX_CHARS[(codePoint >>> 8) & 0xf])
        .append(HEX_CHARS[(codePoint >>> 4) & 0xf])
        .append(HEX_CHARS[codePoint & 0xf]);
CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h.statements[559]++;
  }
}

class CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h ());
  }
    public static long[] statements = new long[560];
    public static long[] branches = new long[257];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[103];
  static {
    final String SECTION_NAME = "com.google.javascript.jscomp.CodeGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,2,2,2,1,1,1,1,2,1,1,2,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,1,1,1,2,3,1,1,1,1,1,1,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,1,3,1,1,2,2,2,3,2,1,1,1,1,2,1,1,2,2,1,1,1,1,1,1,1};
    for (int i = 1; i <= 102; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[40];

  public CodeCoverCoverageCounter$5lvsuupmfl9cidvlswy96p5e8z8h () {
    super("com.google.javascript.jscomp.CodeGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 559; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 256; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 102; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 39; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.jscomp.CodeGenerator.java");
      for (int i = 1; i <= 559; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 256; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 102; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 13; i++) {
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

