/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.ast;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.Token;

/**
 * Used for code generation.  During codegen, the AST is transformed
 * into an Intermediate Representation (IR) in which loops, ifs, switches
 * and other control-flow statements are rewritten as labeled jumps.
 * If the parser is set to IDE-mode, the resulting AST will not contain
 * any instances of this class.
 */
public class Jump extends AstNode {
  static {
    CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.ping();
  }


    public Node target;
    private Node target2;
    private Jump jumpNode;

    public Jump() {
        type = Token.ERROR;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[1]++;
    }

    public Jump(int nodeType) {
        type = nodeType;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[2]++;
    }

    public Jump(int type, int lineno) {
        this(type);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[3]++;
        setLineno(lineno);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[4]++;
    }

    public Jump(int type, Node child) {
        this(type);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[5]++;
        addChildToBack(child);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[6]++;
    }

    public Jump(int type, Node child, int lineno) {
        this(type, child);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[7]++;
        setLineno(lineno);
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[8]++;
    }

    public Jump getJumpStatement()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((type != Token.BREAK) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((type != Token.CONTINUE) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[1]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[10]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[2]++;}
        return jumpNode;
    }

    public void setJumpStatement(Jump jumpStatement)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((type != Token.BREAK) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((type != Token.CONTINUE) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[3]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[12]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[4]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[13]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((jumpStatement == null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[5]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[14]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[6]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this.jumpNode != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[7]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[16]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[8]++;} //only once
        this.jumpNode = jumpStatement;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[17]++;
    }

    public Node getDefault()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[18]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((type != Token.SWITCH) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[9]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[19]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[10]++;}
        return target2;
    }

    public void setDefault(Node defaultTarget)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((type != Token.SWITCH) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[11]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[21]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[12]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((defaultTarget.getType() != Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[13]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[23]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[14]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((target2 != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[15]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[25]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[16]++;} //only once
        target2 = defaultTarget;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[26]++;
    }

    public Node getFinally()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[27]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type != Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[17]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[28]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[18]++;}
        return target2;
    }

    public void setFinally(Node finallyTarget)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type != Token.TRY) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[19]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[30]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[20]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((finallyTarget.getType() != Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[21]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[32]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[22]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[33]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((target2 != null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[23]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[34]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[24]++;} //only once
        target2 = finallyTarget;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[35]++;
    }

    public Jump getLoop()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[36]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((type != Token.LABEL) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[25]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[37]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[26]++;}
        return jumpNode;
    }

    public void setLoop(Jump loop)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[38]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((type != Token.LABEL) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[27]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[39]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[28]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((loop == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[29]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[41]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[30]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[42]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((jumpNode != null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[31]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[43]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[32]++;} //only once
        jumpNode = loop;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[44]++;
    }

    public Node getContinue()
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[45]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((type != Token.LOOP) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[33]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[46]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[34]++;}
        return target2;
    }

    public void setContinue(Node continueTarget)
    {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[47]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((type != Token.LOOP) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[35]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[48]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[36]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[49]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((continueTarget.getType() != Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[37]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[50]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[38]++;}
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[51]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((target2 != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[39]++; codeBug();
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[52]++;
} else {
  CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.branches[40]++;} //only once
        target2 = continueTarget;
CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601.statements[53]++;
    }

    /**
     * Jumps are only used directly during code generation, and do
     * not support this interface.
     * @throws UnsupportedOperationException
     */
    @Override
    public void visit(NodeVisitor visitor) {
        throw new UnsupportedOperationException(this.toString());
    }

    @Override
    public String toSource(int depth) {
        throw new UnsupportedOperationException(this.toString());
    }
}

class CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601 ());
  }
    public static long[] statements = new long[54];
    public static long[] branches = new long[41];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.ast.RHINO-SRC-Jump.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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
    public static long[] loops = new long[1];

  public CodeCoverCoverageCounter$1cnp1i09zf4s7wx3b3i9qir32w0601 () {
    super("org.mozilla.javascript.ast.RHINO-SRC-Jump.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 53; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 40; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.ast.RHINO-SRC-Jump.java");
      for (int i = 1; i <= 53; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 40; i++) {
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
      for (int i = 1; i <= 0; i++) {
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

