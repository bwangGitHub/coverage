/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ScriptRuntime.NoSuchMethodShim;
import org.mozilla.javascript.debug.DebugFrame;

import static org.mozilla.javascript.UniqueTag.DOUBLE_MARK;

public final class Interpreter extends Icode implements Evaluator
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.ping();
  }

    // data for parsing
    InterpreterData itsData;

    static final int EXCEPTION_TRY_START_SLOT  = 0;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1]++;
  }
    static final int EXCEPTION_TRY_END_SLOT    = 1;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[2]++;
  }
    static final int EXCEPTION_HANDLER_SLOT    = 2;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[3]++;
  }
    static final int EXCEPTION_TYPE_SLOT       = 3;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[4]++;
  }
    static final int EXCEPTION_LOCAL_SLOT      = 4;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[5]++;
  }
    static final int EXCEPTION_SCOPE_SLOT      = 5;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[6]++;
  }
    // SLOT_SIZE: space for try start/end, handler, start, handler type,
    //            exception local and scope local
    static final int EXCEPTION_SLOT_SIZE       = 6;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[7]++;
  }

    /**
     * Class to hold data corresponding to one interpreted call stack frame.
     */
    private static class CallFrame implements Cloneable, Serializable
    {
        static final long serialVersionUID = -2843792508994958978L;

        CallFrame parentFrame;
        // amount of stack frames before this one on the interpretation stack
        int frameIndex;
        // If true indicates read-only frame that is a part of continuation
        boolean frozen;

        InterpretedFunction fnOrScript;
        InterpreterData idata;

// Stack structure
// stack[0 <= i < localShift]: arguments and local variables
// stack[localShift <= i <= emptyStackTop]: used for local temporaries
// stack[emptyStackTop < i < stack.length]: stack data
// sDbl[i]: if stack[i] is UniqueTag.DOUBLE_MARK, sDbl[i] holds the number value

        Object[] stack;
        int[] stackAttributes;
        double[] sDbl;
        CallFrame varSource; // defaults to this unless continuation frame
        int localShift;
        int emptyStackTop;

        DebugFrame debuggerFrame;
        boolean useActivation;
        boolean isContinuationsTopFrame;

        Scriptable thisObj;

// The values that change during interpretation

        Object result;
        double resultDbl;
        int pc;
        int pcPrevBranch;
        int pcSourceLineStart;
        Scriptable scope;

        int savedStackTop;
        int savedCallOp;
        Object throwable;

        CallFrame cloneFrozen()
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((frozen) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[1]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[9]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[2]++;}

            CallFrame copy;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[10]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
            try {
CodeCoverTryBranchHelper_Try1 = true;
                copy = (CallFrame)clone();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[11]++;
            } catch (CloneNotSupportedException ex) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[4]++;
                throw new IllegalStateException();
            } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[3]++;
}
  }

            // clone stack but keep varSource to point to values
            // from this frame to share variables.

            copy.stack = stack.clone();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[12]++;
            copy.stackAttributes = stackAttributes.clone();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[13]++;
            copy.sDbl = sDbl.clone();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[14]++;

            copy.frozen = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[15]++;
            return copy;
        }
    }

    private static final class ContinuationJump implements Serializable
    {
        static final long serialVersionUID = 7687739156004308247L;

        CallFrame capturedFrame;
        CallFrame branchFrame;
        Object result;
        double resultDbl;

        ContinuationJump(NativeContinuation c, CallFrame current)
        {
            this.capturedFrame = (CallFrame)c.getImplementation();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[16]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[17]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((this.capturedFrame == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((current == null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[5]++;
                // Continuation and current execution does not share
                // any frames if there is nothing to capture or
                // if there is no currently executed frames
                this.branchFrame = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[18]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[6]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[19]++;
                // Search for branch frame where parent frame chains starting
                // from captured and current meet.
                CallFrame chain1 = this.capturedFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[20]++;
                CallFrame chain2 = current;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[21]++;

                // First work parents of chain1 or chain2 until the same
                // frame depth.
                int diff = chain1.frameIndex - chain2.frameIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[22]++;
int CodeCoverConditionCoverageHelper_C3;
                if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((diff != 0) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[7]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[23]++;
int CodeCoverConditionCoverageHelper_C4;
                    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((diff < 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[9]++;
                        // swap to make sure that
                        // chain1.frameIndex > chain2.frameIndex and diff > 0
                        chain1 = current;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[24]++;
                        chain2 = this.capturedFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[25]++;
                        diff = -diff;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[26]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[10]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[27]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[1]++;


int CodeCoverConditionCoverageHelper_C5;
                    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[3]++;
}
                        chain1 = chain1.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[28]++;
                    } while ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((--diff != 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[29]++;
int CodeCoverConditionCoverageHelper_C6;
                    if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((chain1.frameIndex != chain2.frameIndex) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[11]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[30]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[12]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[8]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[31]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;

                // Now walk parents in parallel until a shared frame is found
                // or until the root is reached.
                while ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((chain1 != chain2) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((chain1 != null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[6]++;
}
                    chain1 = chain1.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[32]++;
                    chain2 = chain2.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[33]++;
                }

                this.branchFrame = chain1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[34]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[35]++;
int CodeCoverConditionCoverageHelper_C8;
                if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((this.branchFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this.branchFrame.frozen) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[13]++;
                    Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[36]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[14]++;}
            }
        }
    }

    private static CallFrame captureFrameForGenerator(CallFrame frame) {
      frame.frozen = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[38]++;
      CallFrame result = frame.cloneFrozen();
      frame.frozen = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[39]++;

      // now isolate this frame from its previous context
      result.parentFrame = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[40]++;
      result.frameIndex = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[41]++;

      return result;
    }

    static {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        // Checks for byte code consistencies, good compiler can eliminate them

        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((Token.LAST_BYTECODE_TOKEN > 127) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[15]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[43]++;
            String str = "Violation of Token.LAST_BYTECODE_TOKEN <= 127";
            System.err.println(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[44]++;
            throw new IllegalStateException(str);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[16]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[45]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((MIN_ICODE < -128) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[17]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[46]++;
            String str = "Violation of Interpreter.MIN_ICODE >= -128";
            System.err.println(str);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[47]++;
            throw new IllegalStateException(str);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[18]++;}
    }

    public Object compile(CompilerEnvirons compilerEnv,
                          ScriptNode tree,
                          String encodedSource,
                          boolean returnFunction)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[48]++;
        CodeGenerator cgen = new CodeGenerator();
        itsData = cgen.compile(compilerEnv, tree, encodedSource, returnFunction);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[49]++;
        return itsData;
    }

    public Script createScriptObject(Object bytecode, Object staticSecurityDomain)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[50]++;
int CodeCoverConditionCoverageHelper_C11;
        if((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((bytecode != itsData) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[19]++;
            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[51]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[20]++;}
        return InterpretedFunction.createScript(itsData,
                                                staticSecurityDomain);
    }

    public void setEvalScriptFlag(Script script) {
        ((InterpretedFunction)script).idata.evalScriptFlag = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[52]++;
    }


    public Function createFunctionObject(Context cx, Scriptable scope,
            Object bytecode, Object staticSecurityDomain)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[53]++;
int CodeCoverConditionCoverageHelper_C12;
        if((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((bytecode != itsData) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[21]++;
            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[54]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[22]++;}
        return InterpretedFunction.createFunction(cx, scope, itsData,
                                                  staticSecurityDomain);
    }

    private static int getShort(byte[] iCode, int pc) {
        return (iCode[pc] << 8) | (iCode[pc + 1] & 0xFF);
    }

    private static int getIndex(byte[] iCode, int pc) {
        return ((iCode[pc] & 0xFF) << 8) | (iCode[pc + 1] & 0xFF);
    }

    private static int getInt(byte[] iCode, int pc) {
        return (iCode[pc] << 24) | ((iCode[pc + 1] & 0xFF) << 16)
               | ((iCode[pc + 2] & 0xFF) << 8) | (iCode[pc + 3] & 0xFF);
    }

    private static int getExceptionHandler(CallFrame frame,
                                           boolean onlyFinally)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[55]++;
        int[] exceptionTable = frame.idata.itsExceptionTable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[56]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((exceptionTable == null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[23]++;
            // No exception handlers
            return -1;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[24]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[57]++;

        // Icode switch in the interpreter increments PC immediately
        // and it is necessary to subtract 1 from the saved PC
        // to point it before the start of the next instruction.
        int pc = frame.pc - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[58]++;

        // OPT: use binary search
        int best = -1, bestStart = 0, bestEnd = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[59]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[7]++;


int CodeCoverConditionCoverageHelper_C14;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i != exceptionTable.length) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); i += EXCEPTION_SLOT_SIZE) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[9]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[60]++;
            int start = exceptionTable[i + EXCEPTION_TRY_START_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[61]++;
            int end = exceptionTable[i + EXCEPTION_TRY_END_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[62]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((start <= pc) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((pc < end) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[25]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[63]++;
                continue;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[26]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[64]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (8)) == 0 || true) &&
 ((onlyFinally) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((exceptionTable[i + EXCEPTION_TYPE_SLOT] != 1) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[27]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[65]++;
                continue;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[28]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[66]++;
int CodeCoverConditionCoverageHelper_C17;
            if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((best >= 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[29]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;
                // Since handlers always nest and they never have shared end
                // although they can share start  it is sufficient to compare
                // handlers ends
                if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((bestEnd < end) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[31]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[68]++;
                    continue;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[32]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[69]++;
int CodeCoverConditionCoverageHelper_C19;
                // Check the above assumption
                if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((bestStart > start) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[33]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[70]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[34]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[71]++;
int CodeCoverConditionCoverageHelper_C20; // should be nested
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((bestEnd == end) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[35]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[72]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[36]++;}
  // no ens sharing
            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[30]++;}
            best = i;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[73]++;
            bestStart = start;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[74]++;
            bestEnd = end;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[75]++;
        }
        return best;
    }

    static void dumpICode(InterpreterData idata)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[76]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((Token.printICode) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[37]++;
            return;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[38]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[77]++;

        byte iCode[] = idata.itsICode;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[78]++;
        int iCodeLength = iCode.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[79]++;
        String[] strings = idata.itsStringTable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[80]++;
        PrintStream out = System.out;
        out.println("ICode dump, for " + idata.itsName
                    + ", length = " + iCodeLength);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[81]++;
        out.println("MaxStack = " + idata.itsMaxStack);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[82]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[83]++;

        int indexReg = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[84]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[10]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int pc = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((pc < iCodeLength) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); ) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[10]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[11]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[12]++;
}
            out.flush();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[85]++;
            out.print(" [" + pc + "] ");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[86]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[87]++;
            int token = iCode[pc];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[88]++;
            int icodeLength = bytecodeSpan(token);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[89]++;
            String tname = Icode.bytecodeName(token);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[90]++;
            int old_pc = pc;
            ++pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[91]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[92]++;
            switch (token) {
              default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[39]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[93]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((icodeLength != 1) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[40]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[94]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[41]++;}
                out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[95]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[96]++;
                break;

              case Icode_GOSUB :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[42]++;
              case Token.GOTO :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[43]++;
              case Token.IFEQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[44]++;
              case Token.IFNE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[45]++;
              case Icode_IFEQ_POP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[46]++;
              case Icode_LEAVEDQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[47]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[97]++;
                int newPC = pc + getShort(iCode, pc) - 1;
                out.println(tname + " " + newPC);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[98]++;
                pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[99]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[100]++;
                break;
              }
              case Icode_VAR_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[48]++;
              case Icode_NAME_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[49]++;
              case Icode_PROP_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[50]++;
              case Icode_ELEM_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[51]++;
              case Icode_REF_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[52]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[101]++;
                int incrDecrType = iCode[pc];
                out.println(tname + " " + incrDecrType);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[102]++;
                ++pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[103]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[104]++;
                break;
              }

              case Icode_CALLSPECIAL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[53]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[105]++;
                int callType = iCode[pc] & 0xFF;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[106]++;
                boolean isNew =  (iCode[pc + 1] != 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[107]++;
                int line = getIndex(iCode, pc+2);
                out.println(tname+" "+callType+" "+isNew+" "+indexReg+" "+line);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[108]++;
                pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[109]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[110]++;
                break;
              }

              case Token.CATCH_SCOPE:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[54]++;
                {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[111]++;
                    boolean afterFisrtFlag =  (iCode[pc] != 0);
                    out.println(tname+" "+afterFisrtFlag);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[112]++;
                    ++pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[113]++;
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[114]++;
                break;
              case Token.REGEXP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[55]++;
                out.println(tname+" "+idata.itsRegExpLiterals[indexReg]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[115]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[116]++;
                break;
              case Token.OBJECTLIT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[56]++;
              case Icode_SPARE_ARRAYLIT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[57]++;
                out.println(tname+" "+idata.literalIds[indexReg]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[117]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[118]++;
                break;
              case Icode_CLOSURE_EXPR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[58]++;
              case Icode_CLOSURE_STMT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[59]++;
                out.println(tname+" "+idata.itsNestedFunctions[indexReg]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[119]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[120]++;
                break;
              case Token.CALL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[60]++;
              case Icode_TAIL_CALL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[61]++;
              case Token.REF_CALL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[62]++;
              case Token.NEW :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[63]++;
                out.println(tname+' '+indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[121]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[122]++;
                break;
              case Token.THROW :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[64]++;
              case Token.YIELD :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[65]++;
              case Icode_GENERATOR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[66]++;
              case Icode_GENERATOR_END :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[67]++;
              {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[123]++;
                int line = getIndex(iCode, pc);
                out.println(tname + " : " + line);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[124]++;
                pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[125]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[126]++;
                break;
              }
              case Icode_SHORTNUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[68]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[127]++;
                int value = getShort(iCode, pc);
                out.println(tname + " " + value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[128]++;
                pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[129]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[130]++;
                break;
              }
              case Icode_INTNUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[69]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[131]++;
                int value = getInt(iCode, pc);
                out.println(tname + " " + value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[132]++;
                pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[133]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[134]++;
                break;
              }
              case Token.NUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[70]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[135]++;
                double value = idata.itsDoubleTable[indexReg];
                out.println(tname + " " + value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[136]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[137]++;
                break;
              }
              case Icode_LINE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[71]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[138]++;
                int line = getIndex(iCode, pc);
                out.println(tname + " : " + line);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[139]++;
                pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[140]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[141]++;
                break;
              }
              case Icode_REG_STR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[72]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[142]++;
                String str = strings[0xFF & iCode[pc]];
                out.println(tname + " \"" + str + '"');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[143]++;
                ++pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[144]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[145]++;
                break;
              }
              case Icode_REG_STR2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[73]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[146]++;
                String str = strings[getIndex(iCode, pc)];
                out.println(tname + " \"" + str + '"');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[147]++;
                pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[148]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[149]++;
                break;
              }
              case Icode_REG_STR4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[74]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[150]++;
                String str = strings[getInt(iCode, pc)];
                out.println(tname + " \"" + str + '"');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[151]++;
                pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[152]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[153]++;
                break;
              }
              case Icode_REG_IND_C0:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[75]++;
                  indexReg = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[154]++;
                  out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[155]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[156]++;
                  break;
              case Icode_REG_IND_C1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[76]++;
                  indexReg = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[157]++;
                  out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[158]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[159]++;
                  break;
              case Icode_REG_IND_C2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[77]++;
                  indexReg = 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[160]++;
                  out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[161]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[162]++;
                  break;
              case Icode_REG_IND_C3:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[78]++;
                  indexReg = 3;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[163]++;
                  out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[164]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[165]++;
                  break;
              case Icode_REG_IND_C4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[79]++;
                  indexReg = 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[166]++;
                  out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[167]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[168]++;
                  break;
              case Icode_REG_IND_C5:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[80]++;
                  indexReg = 5;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[169]++;
                  out.println(tname);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[170]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[171]++;
                  break;
              case Icode_REG_IND1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[81]++; {
                indexReg = 0xFF & iCode[pc];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[172]++;
                out.println(tname+" "+indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[173]++;
                ++pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[174]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[175]++;
                break;
              }
              case Icode_REG_IND2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[82]++; {
                indexReg = getIndex(iCode, pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[176]++;
                out.println(tname+" "+indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[177]++;
                pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[178]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[179]++;
                break;
              }
              case Icode_REG_IND4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[83]++; {
                indexReg = getInt(iCode, pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[180]++;
                out.println(tname+" "+indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[181]++;
                pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[182]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[183]++;
                break;
              }
              case Icode_GETVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[84]++;
              case Icode_SETVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[85]++;
              case Icode_SETCONSTVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[86]++;
                indexReg = iCode[pc];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[184]++;
                out.println(tname+" "+indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[185]++;
                ++pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[186]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[187]++;
                break;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[188]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((old_pc + icodeLength != pc) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[87]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[189]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[88]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[190]++;

        int[] table = idata.itsExceptionTable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[191]++;
int CodeCoverConditionCoverageHelper_C25;
        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((table != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[89]++;
            out.println("Exception handlers: "
                         +table.length / EXCEPTION_SLOT_SIZE);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[192]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[193]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[13]++;


int CodeCoverConditionCoverageHelper_C26;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i != table.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false);
                 i += EXCEPTION_SLOT_SIZE)
            {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[13]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[14]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[15]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[194]++;
                int tryStart       = table[i + EXCEPTION_TRY_START_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[195]++;
                int tryEnd         = table[i + EXCEPTION_TRY_END_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[196]++;
                int handlerStart   = table[i + EXCEPTION_HANDLER_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[197]++;
                int type           = table[i + EXCEPTION_TYPE_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[198]++;
                int exceptionLocal = table[i + EXCEPTION_LOCAL_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[199]++;
                int scopeLocal     = table[i + EXCEPTION_SCOPE_SLOT];

                out.println(" tryStart="+tryStart+" tryEnd="+tryEnd
                            +" handlerStart="+handlerStart
                            +" type="+(type == 0 ? "catch" : "finally")
                            +" exceptionLocal="+exceptionLocal);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[200]++;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[90]++;}
        out.flush();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[201]++;
    }

    private static int bytecodeSpan(int bytecode)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[202]++;
        switch (bytecode) {
            case Token.THROW :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[91]++;
            case Token.YIELD:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[92]++;
            case Icode_GENERATOR:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[93]++;
            case Icode_GENERATOR_END:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[94]++;
                // source line
                return 1 + 2;

            case Icode_GOSUB :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[95]++;
            case Token.GOTO :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[96]++;
            case Token.IFEQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[97]++;
            case Token.IFNE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[98]++;
            case Icode_IFEQ_POP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[99]++;
            case Icode_LEAVEDQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[100]++;
                // target pc offset
                return 1 + 2;

            case Icode_CALLSPECIAL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[101]++;
                // call type
                // is new
                // line number
                return 1 + 1 + 1 + 2;

            case Token.CATCH_SCOPE:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[102]++;
                // scope flag
                return 1 + 1;

            case Icode_VAR_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[103]++;
            case Icode_NAME_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[104]++;
            case Icode_PROP_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[105]++;
            case Icode_ELEM_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[106]++;
            case Icode_REF_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[107]++;
                // type of ++/--
                return 1 + 1;

            case Icode_SHORTNUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[108]++;
                // short number
                return 1 + 2;

            case Icode_INTNUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[109]++;
                // int number
                return 1 + 4;

            case Icode_REG_IND1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[110]++;
                // ubyte index
                return 1 + 1;

            case Icode_REG_IND2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[111]++;
                // ushort index
                return 1 + 2;

            case Icode_REG_IND4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[112]++;
                // int index
                return 1 + 4;

            case Icode_REG_STR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[113]++;
                // ubyte string index
                return 1 + 1;

            case Icode_REG_STR2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[114]++;
                // ushort string index
                return 1 + 2;

            case Icode_REG_STR4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[115]++;
                // int string index
                return 1 + 4;

            case Icode_GETVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[116]++;
            case Icode_SETVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[117]++;
            case Icode_SETCONSTVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[118]++;
                // byte var index
                return 1 + 1;

            case Icode_LINE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[119]++;
                // line number
                return 1 + 2; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[120]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[203]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((validBytecode(bytecode)) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[121]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[122]++;}
        return 1;
    }

    static int[] getLineNumbers(InterpreterData data)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[204]++;
        UintMap presentLines = new UintMap();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[205]++;

        byte[] iCode = data.itsICode;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[206]++;
        int iCodeLength = iCode.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[207]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[16]++;


int CodeCoverConditionCoverageHelper_C28;
        for (int pc = 0;(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((pc != iCodeLength) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false);) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[16]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[17]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[18]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[208]++;
            int bytecode = iCode[pc];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[209]++;
            int span = bytecodeSpan(bytecode);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[210]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((bytecode == Icode_LINE) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[123]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[211]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((span != 3) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[125]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[212]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[126]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[213]++;
                int line = getIndex(iCode, pc + 1);
                presentLines.put(line, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[214]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[124]++;}
            pc += span;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[215]++;
        }

        return presentLines.getKeys();
    }

    public void captureStackInfo(RhinoException ex)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[216]++;
        Context cx = Context.getCurrentContext();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[217]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((cx == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((cx.lastInterpreterFrame == null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[127]++;
            // No interpreter invocations
            ex.interpreterStackInfo = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[218]++;
            ex.interpreterLineData = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[219]++;
            return;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[128]++;}
        // has interpreter frame on the stack
        CallFrame[] array;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[220]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((cx.previousInterpreterInvocations == null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((cx.previousInterpreterInvocations.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[129]++;
            array = new CallFrame[1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[221]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[130]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[222]++;
            int previousCount = cx.previousInterpreterInvocations.size();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[223]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((cx.previousInterpreterInvocations.peek()
                == cx.lastInterpreterFrame) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[131]++;
                // It can happen if exception was generated after
                // frame was pushed to cx.previousInterpreterInvocations
                // but before assignment to cx.lastInterpreterFrame.
                // In this case frames has to be ignored.
                --previousCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[224]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[132]++;}
            array = new CallFrame[previousCount + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[225]++;
            cx.previousInterpreterInvocations.toArray(array);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[226]++;
        }
        array[array.length - 1]  = (CallFrame)cx.lastInterpreterFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[227]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[228]++;

        int interpreterFrameCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[229]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[19]++;


int CodeCoverConditionCoverageHelper_C34;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((i != array.length) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[19]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[20]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[21]++;
}
            interpreterFrameCount += 1 + array[i].frameIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[230]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[231]++;

        int[] linePC = new int[interpreterFrameCount];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[232]++;
        // Fill linePC with pc positions from all interpreter frames.
        // Start from the most nested frame
        int linePCIndex = interpreterFrameCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[233]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[22]++;


int CodeCoverConditionCoverageHelper_C35;
        for (int i = array.length;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false);) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[22]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[23]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[24]++;
}
            --i;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[234]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[235]++;
            CallFrame frame = array[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[236]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[25]++;


int CodeCoverConditionCoverageHelper_C36;
            while ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((frame != null) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[25]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[26]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[27]++;
}
                --linePCIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[237]++;
                linePC[linePCIndex] = frame.pcSourceLineStart;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[238]++;
                frame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[239]++;
            }
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[240]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((linePCIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[133]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[241]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[134]++;}

        ex.interpreterStackInfo = array;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[242]++;
        ex.interpreterLineData = linePC;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[243]++;
    }

    public String getSourcePositionFromStack(Context cx, int[] linep)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[244]++;
        CallFrame frame = (CallFrame)cx.lastInterpreterFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[245]++;
        InterpreterData idata = frame.idata;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[246]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((frame.pcSourceLineStart >= 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[135]++;
            linep[0] = getIndex(idata.itsICode, frame.pcSourceLineStart);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[247]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[136]++;
            linep[0] = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[248]++;
        }
        return idata.itsSourceFile;
    }

    public String getPatchedStack(RhinoException ex,
                                  String nativeStackTrace)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[249]++;
        String tag = "org.mozilla.javascript.Interpreter.interpretLoop";
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[250]++;
        StringBuffer sb = new StringBuffer(nativeStackTrace.length() + 1000);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[251]++;
        String lineSeparator = SecurityUtilities.getSystemProperty("line.separator");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[252]++;

        CallFrame[] array = (CallFrame[])ex.interpreterStackInfo;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[253]++;
        int[] linePC = ex.interpreterLineData;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[254]++;
        int arrayIndex = array.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[255]++;
        int linePCIndex = linePC.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[256]++;
        int offset = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[257]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[28]++;


int CodeCoverConditionCoverageHelper_C39;
        while ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((arrayIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[28]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[29]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[30]++;
}
            --arrayIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[258]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[259]++;
            int pos = nativeStackTrace.indexOf(tag, offset);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[260]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((pos < 0) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[137]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[261]++;
                break;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[138]++;}

            // Skip tag length
            pos += tag.length();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[262]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[263]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[31]++;


int CodeCoverConditionCoverageHelper_C41;
            // Skip until the end of line
            for (;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((pos != nativeStackTrace.length()) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); ++pos) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[31]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[32]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[33]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[264]++;
                char c = nativeStackTrace.charAt(pos);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[265]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[139]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[266]++;
                    break;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[140]++;}
            }
            sb.append(nativeStackTrace.substring(offset, pos));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[267]++;
            offset = pos;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[268]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[269]++;

            CallFrame frame = array[arrayIndex];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[270]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[34]++;


int CodeCoverConditionCoverageHelper_C43;
            while ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((frame != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[34]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[35]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[36]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[271]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((linePCIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[141]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[272]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[142]++;}
                --linePCIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[273]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[274]++;
                InterpreterData idata = frame.idata;
                sb.append(lineSeparator);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[275]++;
                sb.append("\tat script");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[276]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[277]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((idata.itsName != null) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((idata.itsName.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[143]++;
                    sb.append('.');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[278]++;
                    sb.append(idata.itsName);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[279]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[144]++;}
                sb.append('(');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[280]++;
                sb.append(idata.itsSourceFile);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[281]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[282]++;
                int pc = linePC[linePCIndex];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[283]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((pc >= 0) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[145]++;
                    // Include line info only if available
                    sb.append(':');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[284]++;
                    sb.append(getIndex(idata.itsICode, pc));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[285]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[146]++;}
                sb.append(')');
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[286]++;
                frame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[287]++;
            }
        }
        sb.append(nativeStackTrace.substring(offset));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[288]++;

        return sb.toString();
    }

    public List<String> getScriptStack(RhinoException ex) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[289]++;
        ScriptStackElement[][] stack = getScriptStackElements(ex);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[290]++;
        List<String> list = new ArrayList<String>(stack.length);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[291]++;
        String lineSeparator =
                SecurityUtilities.getSystemProperty("line.separator");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[292]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[37]++;


        for (ScriptStackElement[] group : stack) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[37]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[38]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[39]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[293]++;
            StringBuilder sb = new StringBuilder();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[294]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[40]++;


            for (ScriptStackElement elem : group) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[40]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[41]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[42]++;
}
                elem.renderJavaStyle(sb);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[295]++;
                sb.append(lineSeparator);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[296]++;
            }
            list.add(sb.toString());
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[297]++;
        }
        return list;
    }

    public ScriptStackElement[][] getScriptStackElements(RhinoException ex)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[298]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((ex.interpreterStackInfo == null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[147]++;
            return null;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[148]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[299]++;

        List<ScriptStackElement[]> list = new ArrayList<ScriptStackElement[]>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[300]++;

        CallFrame[] array = (CallFrame[])ex.interpreterStackInfo;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[301]++;
        int[] linePC = ex.interpreterLineData;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[302]++;
        int arrayIndex = array.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[303]++;
        int linePCIndex = linePC.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[304]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[43]++;


int CodeCoverConditionCoverageHelper_C48;
        while ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((arrayIndex != 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[43]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[44]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[45]++;
}
            --arrayIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[305]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[306]++;
            CallFrame frame = array[arrayIndex];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[307]++;
            List<ScriptStackElement> group = new ArrayList<ScriptStackElement>();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[308]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[46]++;


int CodeCoverConditionCoverageHelper_C49;
            while ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((frame != null) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[46]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[47]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[48]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[309]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((linePCIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[149]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[310]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[150]++;}
                --linePCIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[311]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[312]++;
                InterpreterData idata = frame.idata;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[313]++;
                String fileName = idata.itsSourceFile;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[314]++;
                String functionName = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[315]++;
                int lineNumber = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[316]++;
                int pc = linePC[linePCIndex];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[317]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((pc >= 0) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[151]++;
                    lineNumber = getIndex(idata.itsICode, pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[318]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[152]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[319]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((idata.itsName != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((idata.itsName.length() != 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[153]++;
                    functionName = idata.itsName;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[320]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[154]++;}
                frame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[321]++;
                group.add(new ScriptStackElement(fileName, functionName, lineNumber));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[322]++;
            }
            list.add(group.toArray(new ScriptStackElement[group.size()]));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[323]++;
        }
        return list.toArray(new ScriptStackElement[list.size()][]);
    }

    static String getEncodedSource(InterpreterData idata)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[324]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((idata.encodedSource == null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[155]++;
            return null;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[156]++;}
        return idata.encodedSource.substring(idata.encodedSourceStart,
                                             idata.encodedSourceEnd);
    }

    private static void initFunction(Context cx, Scriptable scope,
                                     InterpretedFunction parent, int index)
    {
        InterpretedFunction fn;
        fn = InterpretedFunction.createFunction(cx, scope, parent, index);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[325]++;
        ScriptRuntime.initFunction(cx, scope, fn, fn.idata.itsFunctionType,
                                   parent.idata.evalScriptFlag);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[326]++;
    }

    static Object interpret(InterpretedFunction ifun,
                            Context cx, Scriptable scope,
                            Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[327]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((ScriptRuntime.hasTopCall(cx)) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[157]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[328]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[158]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[329]++;
int CodeCoverConditionCoverageHelper_C55;

        if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((cx.interpreterSecurityDomain != ifun.securityDomain) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[159]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[330]++;
            Object savedDomain = cx.interpreterSecurityDomain;
            cx.interpreterSecurityDomain = ifun.securityDomain;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[331]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[332]++;
boolean CodeCoverTryBranchHelper_Try2 = false;
            try {
CodeCoverTryBranchHelper_Try2 = true;
                return ifun.securityController.callWithDomain(
                    ifun.securityDomain, cx, ifun, scope, thisObj, args);
            } finally {
if ( CodeCoverTryBranchHelper_Try2 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[161]++;
}
                cx.interpreterSecurityDomain = savedDomain;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[333]++;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[160]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[334]++;

        CallFrame frame = new CallFrame();
        initFrame(cx, scope, thisObj, args, null, 0, args.length,
                  ifun, null, frame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[335]++;
        frame.isContinuationsTopFrame = cx.isContinuationsTopCall;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[336]++;
        cx.isContinuationsTopCall = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[337]++;

        return interpretLoop(cx, frame, null);
    }

    static class GeneratorState {
        GeneratorState(int operation, Object value) {
            this.operation = operation;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[338]++;
            this.value = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[339]++;
        }
        int operation;
        Object value;
        RuntimeException returnedException;
    }

    public static Object resumeGenerator(Context cx,
                                         Scriptable scope,
                                         int operation,
                                         Object savedState,
                                         Object value)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[340]++;
      CallFrame frame = (CallFrame) savedState;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[341]++;
      GeneratorState generatorState = new GeneratorState(operation, value);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[342]++;
int CodeCoverConditionCoverageHelper_C56;
      if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((operation == NativeGenerator.GENERATOR_CLOSE) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[162]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[343]++;
boolean CodeCoverTryBranchHelper_Try3 = false;
          try {
CodeCoverTryBranchHelper_Try3 = true;
              return interpretLoop(cx, frame, generatorState);
          } catch (RuntimeException e) {
CodeCoverTryBranchHelper_Try3 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[165]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[344]++;
int CodeCoverConditionCoverageHelper_C57;
              // Only propagate exceptions other than closingException
              if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((e != value) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[166]++;
                  throw e;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[167]++;}
          } finally {
    if ( CodeCoverTryBranchHelper_Try3 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[164]++;
}
  }
          return Undefined.instance;

      } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[163]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[345]++;
      Object result = interpretLoop(cx, frame, generatorState);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[346]++;
int CodeCoverConditionCoverageHelper_C58;
      if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((generatorState.returnedException != null) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[168]++;
          throw generatorState.returnedException;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[169]++;}
      return result;
    }

    public static Object restartContinuation(NativeContinuation c, Context cx,
                                             Scriptable scope, Object[] args)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[347]++;
int CodeCoverConditionCoverageHelper_C59;
        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((ScriptRuntime.hasTopCall(cx)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[170]++;
            return ScriptRuntime.doTopCall(c, cx, scope, null, args);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[171]++;}

        Object arg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[348]++;
int CodeCoverConditionCoverageHelper_C60;
        if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((args.length == 0) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[172]++;
            arg = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[349]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[173]++;
            arg = args[0];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[350]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[351]++;

        CallFrame capturedFrame = (CallFrame)c.getImplementation();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[352]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((capturedFrame == null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[174]++;
            // No frames to restart
            return arg;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[175]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[353]++;

        ContinuationJump cjump = new ContinuationJump(c, null);

        cjump.result = arg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[354]++;
        return interpretLoop(cx, null, cjump);
    }

    private static Object interpretLoop(Context cx, CallFrame frame,
                                        Object throwable)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[355]++;
        // throwable holds exception object to rethrow or catch
        // It is also used for continuation restart in which case
        // it holds ContinuationJump

        final Object DBL_MRK = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[356]++;
        final Object undefined = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[357]++;

        final boolean instructionCounting = (cx.instructionThreshold != 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[358]++;
        // arbitrary number to add to instructionCount when calling
        // other functions
        final int INVOCATION_COST = 100;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[359]++;
        // arbitrary exception cost for instruction counting
        final int EXCEPTION_COST = 100;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[360]++;

        String stringReg = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[361]++;
        int indexReg = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[362]++;
int CodeCoverConditionCoverageHelper_C62;

        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((cx.lastInterpreterFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[176]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[363]++;
int CodeCoverConditionCoverageHelper_C63;
            // save the top frame from the previous interpretLoop
            // invocation on the stack
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((cx.previousInterpreterInvocations == null) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[178]++;
                cx.previousInterpreterInvocations = new ObjArray();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[364]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[179]++;}
            cx.previousInterpreterInvocations.push(cx.lastInterpreterFrame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[365]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[177]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[366]++;

        // When restarting continuation throwable is not null and to jump
        // to the code that rewind continuation state indexReg should be set
        // to -1.
        // With the normal call throwable == null and indexReg == -1 allows to
        // catch bugs with using indeReg to access array elements before
        // initializing indexReg.

        GeneratorState generatorState = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[367]++;
int CodeCoverConditionCoverageHelper_C64;
        if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((throwable != null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[180]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[368]++;
int CodeCoverConditionCoverageHelper_C65;
            if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((throwable instanceof GeneratorState) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[182]++;
              generatorState = (GeneratorState) throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[369]++;

              // reestablish this call frame
              enterFrame(cx, frame, ScriptRuntime.emptyArgs, true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[370]++;
              throwable = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[371]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[183]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[372]++;
int CodeCoverConditionCoverageHelper_C66; if ((((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((throwable instanceof ContinuationJump) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[184]++;
                // It should be continuation
                Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[373]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[185]++;}
}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[181]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[374]++;

        Object interpreterResult = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[375]++;
        double interpreterResultDbl = 0.0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[376]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[49]++;



        StateLoop: for (;;) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[49]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[50]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[51]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[377]++;
boolean CodeCoverTryBranchHelper_Try4 = false;
            withoutExceptions: try {
CodeCoverTryBranchHelper_Try4 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[378]++;
int CodeCoverConditionCoverageHelper_C68;

                if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((throwable != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[187]++;
                    // Need to return both 'frame' and 'throwable' from
                    // 'processThrowable', so just added a 'throwable'
                    // member in 'frame'.
                    frame = processThrowable(cx, throwable, frame, indexReg,
                                             instructionCounting);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[379]++;
                    throwable = frame.throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[380]++;
                    frame.throwable = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[381]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[188]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[382]++;
int CodeCoverConditionCoverageHelper_C69;
                    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (8)) == 0 || true) &&
 ((generatorState == null) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((frame.frozen) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[189]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[383]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[190]++;}
                }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[384]++;

                // Use local variables for constant values in frame
                // for faster access
                Object[] stack = frame.stack;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[385]++;
                double[] sDbl = frame.sDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[386]++;
                Object[] vars = frame.varSource.stack;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[387]++;
                double[] varDbls = frame.varSource.sDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[388]++;
                int[] varAttributes = frame.varSource.stackAttributes;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[389]++;
                byte[] iCode = frame.idata.itsICode;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[390]++;
                String[] strings = frame.idata.itsStringTable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[391]++;

                // Use local for stackTop as well. Since execption handlers
                // can only exist at statement level where stack is empty,
                // it is necessary to save/restore stackTop only across
                // function calls and normal returns.
                int stackTop = frame.savedStackTop;

                // Store new frame in cx which is used for error reporting etc.
                cx.lastInterpreterFrame = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[392]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[393]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[52]++;



                Loop: for (;;) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[52]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[53]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[54]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[394]++;

                    // Exception handler assumes that PC is already incremented
                    // pass the instruction start when it searches the
                    // exception handler
                    int op = iCode[frame.pc++];
                    jumplessRun: {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[395]++;

    // Back indent to ease implementation reading
switch (op) {
    case Icode_GENERATOR:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[191]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[396]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((frame.frozen) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[192]++;
          // First time encountering this opcode: create new generator
          // object and return
          frame.pc--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[397]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[398]++; // we want to come back here when we resume
          CallFrame generatorFrame = captureFrameForGenerator(frame);
          generatorFrame.frozen = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[399]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[400]++;
          NativeGenerator generator = new NativeGenerator(frame.scope,
              generatorFrame.fnOrScript, generatorFrame);
          frame.result = generator;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[401]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[402]++;
          break Loop;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[193]++;
          // We are now resuming execution. Fall through to YIELD case.
        }
    }
    // fall through...
    case Token.YIELD:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[194]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[403]++;
int CodeCoverConditionCoverageHelper_C72;
        if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((frame.frozen) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[195]++;
            return freezeGenerator(cx, frame, stackTop, generatorState);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[196]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[404]++;
            Object obj = thawGenerator(frame, stackTop, generatorState, op);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[405]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((obj != Scriptable.NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[197]++;
                throwable = obj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[406]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[407]++;
                break withoutExceptions;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[198]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[408]++;
            continue Loop;
        }
    }
    case Icode_GENERATOR_END:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[199]++; {
      // throw StopIteration
      frame.frozen = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[409]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[410]++;
      int sourceLine = getIndex(iCode, frame.pc);
      generatorState.returnedException = new JavaScriptException(
          NativeIterator.getStopIterationObject(frame.scope),
          frame.idata.itsSourceFile, sourceLine);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[411]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[412]++;
      break Loop;
    }
    case Token.THROW:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[200]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[413]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[414]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((value == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[201]++; value = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[415]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[202]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[416]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[417]++;

        int sourceLine = getIndex(iCode, frame.pc);
        throwable = new JavaScriptException(value,
                                            frame.idata.itsSourceFile,
                                            sourceLine);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[418]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[419]++;
        break withoutExceptions;
    }
    case Token.RETHROW:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[203]++; {
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[420]++;
        throwable = stack[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[421]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[422]++;
        break withoutExceptions;
    }
    case Token.GE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[204]++;
    case Token.LE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[205]++;
    case Token.GT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[206]++;
    case Token.LT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[207]++; {
        stackTop = doCompare(frame, op, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[423]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[424]++;
        continue Loop;
    }
    case Token.IN :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[208]++;
    case Token.INSTANCEOF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[209]++; {
        stackTop = doInOrInstanceof(cx, op, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[425]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[426]++;
        continue Loop;
    }
    case Token.EQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[210]++;
    case Token.NE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[211]++; {
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[427]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[428]++;
        boolean valBln = doEquals(stack, sDbl, stackTop);
        valBln ^= (op == Token.NE);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[429]++;
        stack[stackTop] = ScriptRuntime.wrapBoolean(valBln);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[430]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[431]++;
        continue Loop;
    }
    case Token.SHEQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[212]++;
    case Token.SHNE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[213]++; {
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[432]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[433]++;
        boolean valBln = doShallowEquals(stack, sDbl, stackTop);
        valBln ^= (op == Token.SHNE);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[434]++;
        stack[stackTop] = ScriptRuntime.wrapBoolean(valBln);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[435]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[436]++;
        continue Loop;
    }
    case Token.IFNE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[214]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[437]++;
int CodeCoverConditionCoverageHelper_C75;
        if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((stack_boolean(frame, stackTop--)) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[215]++;
            frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[438]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[439]++;
            continue Loop;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[216]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[440]++;
        break jumplessRun;
    case Token.IFEQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[217]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[441]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((stack_boolean(frame, stackTop--)) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[218]++;
            frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[442]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[443]++;
            continue Loop;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[219]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[444]++;
        break jumplessRun;
    case Icode_IFEQ_POP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[220]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[445]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((stack_boolean(frame, stackTop--)) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[221]++;
            frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[446]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[447]++;
            continue Loop;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[222]++;}
        stack[stackTop--] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[448]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[449]++;
        break jumplessRun;
    case Token.GOTO :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[223]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[450]++;
        break jumplessRun;
    case Icode_GOSUB :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[224]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[451]++;
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[452]++;
        sDbl[stackTop] = frame.pc + 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[453]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[454]++;
        break jumplessRun;
    case Icode_STARTSUB :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[225]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[455]++;
int CodeCoverConditionCoverageHelper_C78;
        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((stackTop == frame.emptyStackTop + 1) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[226]++;
            // Call from Icode_GOSUB: store return PC address in the local
            indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[456]++;
            stack[indexReg] = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[457]++;
            sDbl[indexReg] = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[458]++;
            --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[459]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[227]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[460]++;
int CodeCoverConditionCoverageHelper_C79;
            // Call from exception handler: exception object is already stored
            // in the local
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((stackTop != frame.emptyStackTop) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[228]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[461]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[229]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[462]++;
        continue Loop;
    case Icode_RETSUB :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[230]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[463]++;
int CodeCoverConditionCoverageHelper_C80;
        // indexReg: local to store return address
        if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[231]++;
            addInstructionCount(cx, frame, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[464]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[232]++;}
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[465]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[466]++;
        Object value = stack[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[467]++;
int CodeCoverConditionCoverageHelper_C81;
        if ((((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((value != DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[233]++;
            // Invocation from exception handler, restore object to rethrow
            throwable = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[468]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[469]++;
            break withoutExceptions;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[234]++;}
        // Normal return from GOSUB
        frame.pc = (int)sDbl[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[470]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[471]++;
int CodeCoverConditionCoverageHelper_C82;
        if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[235]++;
            frame.pcPrevBranch = frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[472]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[236]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[473]++;
        continue Loop;
    }
    case Icode_POP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[237]++;
        stack[stackTop] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[474]++;
        stackTop--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[475]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[476]++;
        continue Loop;
    case Icode_POP_RESULT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[238]++;
        frame.result = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[477]++;
        frame.resultDbl = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[478]++;
        stack[stackTop] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[479]++;
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[480]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[481]++;
        continue Loop;
    case Icode_DUP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[239]++;
        stack[stackTop + 1] = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[482]++;
        sDbl[stackTop + 1] = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[483]++;
        stackTop++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[484]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[485]++;
        continue Loop;
    case Icode_DUP2 :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[240]++;
        stack[stackTop + 1] = stack[stackTop - 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[486]++;
        sDbl[stackTop + 1] = sDbl[stackTop - 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[487]++;
        stack[stackTop + 2] = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[488]++;
        sDbl[stackTop + 2] = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[489]++;
        stackTop += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[490]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[491]++;
        continue Loop;
    case Icode_SWAP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[241]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[492]++;
        Object o = stack[stackTop];
        stack[stackTop] = stack[stackTop - 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[493]++;
        stack[stackTop - 1] = o;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[494]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[495]++;
        double d = sDbl[stackTop];
        sDbl[stackTop] = sDbl[stackTop - 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[496]++;
        sDbl[stackTop - 1] = d;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[497]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[498]++;
        continue Loop;
    }
    case Token.RETURN :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[242]++;
        frame.result = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[499]++;
        frame.resultDbl = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[500]++;
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[501]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[502]++;
        break Loop;
    case Token.RETURN_RESULT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[243]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[503]++;
        break Loop;
    case Icode_RETUNDEF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[244]++;
        frame.result = undefined;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[504]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[505]++;
        break Loop;
    case Token.BITNOT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[245]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[506]++;
        int rIntValue = stack_int32(frame, stackTop);
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[507]++;
        sDbl[stackTop] = ~rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[508]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[509]++;
        continue Loop;
    }
    case Token.BITAND :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[246]++;
    case Token.BITOR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[247]++;
    case Token.BITXOR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[248]++;
    case Token.LSH :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[249]++;
    case Token.RSH :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[250]++; {
        stackTop = doBitOp(frame, op, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[510]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[511]++;
        continue Loop;
    }
    case Token.URSH :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[251]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[512]++;
        double lDbl = stack_double(frame, stackTop - 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[513]++;
        int rIntValue = stack_int32(frame, stackTop) & 0x1F;
        stack[--stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[514]++;
        sDbl[stackTop] = ScriptRuntime.toUint32(lDbl) >>> rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[515]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[516]++;
        continue Loop;
    }
    case Token.NEG :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[252]++;
    case Token.POS :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[253]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[517]++;
        double rDbl = stack_double(frame, stackTop);
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[518]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[519]++;
int CodeCoverConditionCoverageHelper_C83;
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((op == Token.NEG) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[254]++;
            rDbl = -rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[520]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[255]++;}
        sDbl[stackTop] = rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[521]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[522]++;
        continue Loop;
    }
    case Token.ADD :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[256]++;
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[523]++;
        doAdd(stack, sDbl, stackTop, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[524]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[525]++;
        continue Loop;
    case Token.SUB :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[257]++;
    case Token.MUL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[258]++;
    case Token.DIV :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[259]++;
    case Token.MOD :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[260]++; {
        stackTop = doArithmetic(frame, op, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[526]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[527]++;
        continue Loop;
    }
    case Token.NOT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[261]++;
        stack[stackTop] = ScriptRuntime.wrapBoolean(
                              !stack_boolean(frame, stackTop));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[528]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[529]++;
        continue Loop;
    case Token.BINDNAME :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[262]++;
        stack[++stackTop] = ScriptRuntime.bind(cx, frame.scope, stringReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[530]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[531]++;
        continue Loop;
    case Token.STRICT_SETNAME:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[263]++;
    case Token.SETNAME :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[264]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[532]++;
        Object rhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[533]++;
int CodeCoverConditionCoverageHelper_C84;
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((rhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[265]++; rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[534]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[266]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[535]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[536]++;
        Scriptable lhs = (Scriptable)stack[stackTop];
        stack[stackTop] = op == Token.SETNAME ?
                ScriptRuntime.setName(lhs, rhs, cx,
                                      frame.scope, stringReg) :
                ScriptRuntime.strictSetName(lhs, rhs, cx,
                                      frame.scope, stringReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[537]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[538]++;
        continue Loop;
    }
    case Icode_SETCONST:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[267]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[539]++;
        Object rhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[540]++;
int CodeCoverConditionCoverageHelper_C85;
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((rhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[268]++; rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[541]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[269]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[542]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[543]++;
        Scriptable lhs = (Scriptable)stack[stackTop];
        stack[stackTop] = ScriptRuntime.setConst(lhs, rhs, cx, stringReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[544]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[545]++;
        continue Loop;
    }
    case Token.DELPROP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[270]++;
    case Icode_DELNAME :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[271]++; {
        stackTop = doDelName(cx, op, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[546]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[547]++;
        continue Loop;
    }
    case Token.GETPROPNOWARN :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[272]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[548]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[549]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[273]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[550]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[274]++;}
        stack[stackTop] = ScriptRuntime.getObjectPropNoWarn(lhs, stringReg, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[551]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[552]++;
        continue Loop;
    }
    case Token.GETPROP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[275]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[553]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[554]++;
int CodeCoverConditionCoverageHelper_C87;
        if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[276]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[555]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[277]++;}
        stack[stackTop] = ScriptRuntime.getObjectProp(lhs, stringReg, cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[556]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[557]++;
        continue Loop;
    }
    case Token.SETPROP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[278]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[558]++;
        Object rhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[559]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((rhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[279]++; rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[560]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[280]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[561]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[562]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[563]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[281]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[564]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[282]++;}
        stack[stackTop] = ScriptRuntime.setObjectProp(lhs, stringReg, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[565]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[566]++;
        continue Loop;
    }
    case Icode_PROP_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[283]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[567]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[568]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[284]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[569]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[285]++;}
        stack[stackTop] = ScriptRuntime.propIncrDecr(lhs, stringReg,
                                                     cx, iCode[frame.pc]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[570]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[571]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[572]++;
        continue Loop;
    }
    case Token.GETELEM :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[286]++; {
        stackTop = doGetElem(cx, frame, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[573]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[574]++;
        continue Loop;
    }
    case Token.SETELEM :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[287]++; {
        stackTop = doSetElem(cx, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[575]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[576]++;
        continue Loop;
    }
    case Icode_ELEM_INC_DEC:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[288]++; {
        stackTop = doElemIncDec(cx, frame, iCode, stack, sDbl, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[577]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[578]++;
        continue Loop;
    }
    case Token.GET_REF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[289]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[579]++;
        Ref ref = (Ref)stack[stackTop];
        stack[stackTop] = ScriptRuntime.refGet(ref, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[580]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[581]++;
        continue Loop;
    }
    case Token.SET_REF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[290]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[582]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[583]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((value == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[291]++; value = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[584]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[292]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[585]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[586]++;
        Ref ref = (Ref)stack[stackTop];
        stack[stackTop] = ScriptRuntime.refSet(ref, value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[587]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[588]++;
        continue Loop;
    }
    case Token.DEL_REF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[293]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[589]++;
        Ref ref = (Ref)stack[stackTop];
        stack[stackTop] = ScriptRuntime.refDel(ref, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[590]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[591]++;
        continue Loop;
    }
    case Icode_REF_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[294]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[592]++;
        Ref ref = (Ref)stack[stackTop];
        stack[stackTop] = ScriptRuntime.refIncrDecr(ref, cx, iCode[frame.pc]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[593]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[594]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[595]++;
        continue Loop;
    }
    case Token.LOCAL_LOAD :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[295]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[596]++;
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[597]++;
        stack[stackTop] = stack[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[598]++;
        sDbl[stackTop] = sDbl[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[599]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[600]++;
        continue Loop;
    case Icode_LOCAL_CLEAR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[296]++;
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[601]++;
        stack[indexReg] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[602]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[603]++;
        continue Loop;
    case Icode_NAME_AND_THIS :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[297]++;
        // stringReg: name
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[604]++;
        stack[stackTop] = ScriptRuntime.getNameFunctionAndThis(stringReg,
                                                               cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[605]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[606]++;
        stack[stackTop] = ScriptRuntime.lastStoredScriptable(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[607]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[608]++;
        continue Loop;
    case Icode_PROP_AND_THIS:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[298]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[609]++;
        Object obj = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[610]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 ((obj == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[299]++; obj = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[611]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[300]++;}
        // stringReg: property
        stack[stackTop] = ScriptRuntime.getPropFunctionAndThis(obj, stringReg,
                                                               cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[612]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[613]++;
        stack[stackTop] = ScriptRuntime.lastStoredScriptable(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[614]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[615]++;
        continue Loop;
    }
    case Icode_ELEM_AND_THIS:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[301]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[616]++;
        Object obj = stack[stackTop - 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[617]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((obj == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[302]++; obj = ScriptRuntime.wrapNumber(sDbl[stackTop - 1]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[618]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[303]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[619]++;
        Object id = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[620]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((id == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[304]++; id = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[621]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[305]++;}
        stack[stackTop - 1] = ScriptRuntime.getElemFunctionAndThis(obj, id, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[622]++;
        stack[stackTop] = ScriptRuntime.lastStoredScriptable(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[623]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[624]++;
        continue Loop;
    }
    case Icode_VALUE_AND_THIS :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[306]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[625]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[626]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((value == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[307]++; value = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[627]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[308]++;}
        stack[stackTop] = ScriptRuntime.getValueFunctionAndThis(value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[628]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[629]++;
        stack[stackTop] = ScriptRuntime.lastStoredScriptable(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[630]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[631]++;
        continue Loop;
    }
    case Icode_CALLSPECIAL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[309]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[632]++;
int CodeCoverConditionCoverageHelper_C96;
        if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[310]++;
            cx.instructionCount += INVOCATION_COST;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[633]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[311]++;}
        stackTop = doCallSpecial(cx, frame, stack, sDbl, stackTop, iCode, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[634]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[635]++;
        continue Loop;
    }
    case Token.CALL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[312]++;
    case Icode_TAIL_CALL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[313]++;
    case Token.REF_CALL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[314]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[636]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[315]++;
            cx.instructionCount += INVOCATION_COST;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[637]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[316]++;}
        // stack change: function thisObj arg0 .. argN -> result
        // indexReg: number of arguments
        stackTop -= 1 + indexReg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[638]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[639]++;

        // CALL generation ensures that fun and funThisObj
        // are already Scriptable and Callable objects respectively
        Callable fun = (Callable)stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[640]++;
        Scriptable funThisObj = (Scriptable)stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[641]++;
int CodeCoverConditionCoverageHelper_C98;
        if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((op == Token.REF_CALL) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[317]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[642]++;
            Object[] outArgs = getArgsArray(stack, sDbl, stackTop + 2,
                                            indexReg);
            stack[stackTop] = ScriptRuntime.callRef(fun, funThisObj,
                                                    outArgs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[643]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[644]++;
            continue Loop;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[318]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[645]++;
        Scriptable calleeScope = frame.scope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[646]++;
int CodeCoverConditionCoverageHelper_C99;
        if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((frame.useActivation) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[319]++;
            calleeScope = ScriptableObject.getTopLevelScope(frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[647]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[320]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[648]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((fun instanceof InterpretedFunction) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[321]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[649]++;
            InterpretedFunction ifun = (InterpretedFunction)fun;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[650]++;
int CodeCoverConditionCoverageHelper_C101;
            if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((frame.fnOrScript.securityDomain == ifun.securityDomain) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[323]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[651]++;
                CallFrame callParentFrame = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[652]++;
                CallFrame calleeFrame = new CallFrame();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[653]++;
int CodeCoverConditionCoverageHelper_C102;
                if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((op == Icode_TAIL_CALL) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[325]++;
                    // In principle tail call can re-use the current
                    // frame and its stack arrays but it is hard to
                    // do properly. Any exceptions that can legally
                    // happen during frame re-initialization including
                    // StackOverflowException during innocent looking
                    // System.arraycopy may leave the current frame
                    // data corrupted leading to undefined behaviour
                    // in the catch code bellow that unwinds JS stack
                    // on exceptions. Then there is issue about frame release
                    // end exceptions there.
                    // To avoid frame allocation a released frame
                    // can be cached for re-use which would also benefit
                    // non-tail calls but it is not clear that this caching
                    // would gain in performance due to potentially
                    // bad interaction with GC.
                    callParentFrame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[654]++;
                    // Release the current frame. See Bug #344501 to see why
                    // it is being done here.
                    exitFrame(cx, frame, null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[655]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[326]++;}
                initFrame(cx, calleeScope, funThisObj, stack, sDbl,
                          stackTop + 2, indexReg, ifun, callParentFrame,
                          calleeFrame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[656]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[657]++;
int CodeCoverConditionCoverageHelper_C103;
                if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((op != Icode_TAIL_CALL) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[327]++;
                    frame.savedStackTop = stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[658]++;
                    frame.savedCallOp = op;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[659]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[328]++;}
                frame = calleeFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[660]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[661]++;
                continue StateLoop;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[324]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[322]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[662]++;
int CodeCoverConditionCoverageHelper_C104;

        if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((fun instanceof NativeContinuation) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[329]++;
            // Jump to the captured continuation
            ContinuationJump cjump;
            cjump = new ContinuationJump((NativeContinuation)fun, frame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[663]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[664]++;
int CodeCoverConditionCoverageHelper_C105;

            // continuation result is the first argument if any
            // of continuation call
            if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((indexReg == 0) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[331]++;
                cjump.result = undefined;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[665]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[332]++;
                cjump.result = stack[stackTop + 2];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[666]++;
                cjump.resultDbl = sDbl[stackTop + 2];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[667]++;
            }

            // Start the real unwind job
            throwable = cjump;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[668]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[669]++;
            break withoutExceptions;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[330]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[670]++;
int CodeCoverConditionCoverageHelper_C106;

        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((fun instanceof IdFunctionObject) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[333]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[671]++;
            IdFunctionObject ifun = (IdFunctionObject)fun;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[672]++;
int CodeCoverConditionCoverageHelper_C107;
            if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((NativeContinuation.isContinuationConstructor(ifun)) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[335]++;
                frame.stack[stackTop] = captureContinuation(cx,
                        frame.parentFrame, false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[673]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[674]++;
                continue Loop;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[336]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[675]++;
int CodeCoverConditionCoverageHelper_C108;
            // Bug 405654 -- make best effort to keep Function.apply and
            // Function.call within this interpreter loop invocation
            if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((BaseFunction.isApplyOrCall(ifun)) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[337]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[676]++;
                Callable applyCallable = ScriptRuntime.getCallable(funThisObj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[677]++;
int CodeCoverConditionCoverageHelper_C109;
                if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((applyCallable instanceof InterpretedFunction) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[339]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[678]++;
                    InterpretedFunction iApplyCallable = (InterpretedFunction)applyCallable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[679]++;
int CodeCoverConditionCoverageHelper_C110;
                    if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((frame.fnOrScript.securityDomain == iApplyCallable.securityDomain) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[341]++;
                        frame = initFrameForApplyOrCall(cx, frame, indexReg,
                                stack, sDbl, stackTop, op, calleeScope, ifun,
                                iApplyCallable);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[680]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[681]++;
                        continue StateLoop;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[342]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[340]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[338]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[334]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[682]++;
int CodeCoverConditionCoverageHelper_C111;

        // Bug 447697 -- make best effort to keep __noSuchMethod__ within this
        // interpreter loop invocation
        if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((fun instanceof NoSuchMethodShim) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[343]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[683]++;
            // get the shim and the actual method
            NoSuchMethodShim noSuchMethodShim = (NoSuchMethodShim) fun;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[684]++;
            Callable noSuchMethodMethod = noSuchMethodShim.noSuchMethodMethod;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[685]++;
int CodeCoverConditionCoverageHelper_C112;
            // if the method is in fact an InterpretedFunction
            if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((noSuchMethodMethod instanceof InterpretedFunction) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[345]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[686]++;
                InterpretedFunction ifun = (InterpretedFunction) noSuchMethodMethod;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[687]++;
int CodeCoverConditionCoverageHelper_C113;
                if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((frame.fnOrScript.securityDomain == ifun.securityDomain) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[347]++;
                    frame = initFrameForNoSuchMethod(cx, frame, indexReg, stack, sDbl,
                                             stackTop, op, funThisObj, calleeScope,
                                             noSuchMethodShim, ifun);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[688]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[689]++;
                    continue StateLoop;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[348]++;}

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[346]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[344]++;}

        cx.lastInterpreterFrame = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[690]++;
        frame.savedCallOp = op;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[691]++;
        frame.savedStackTop = stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[692]++;
        stack[stackTop] = fun.call(cx, calleeScope, funThisObj,
                getArgsArray(stack, sDbl, stackTop + 2, indexReg));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[693]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[694]++;

        continue Loop;
    }
    case Token.NEW :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[349]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[695]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[350]++;
            cx.instructionCount += INVOCATION_COST;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[696]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[351]++;}
        // stack change: function arg0 .. argN -> newResult
        // indexReg: number of arguments
        stackTop -= indexReg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[697]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[698]++;

        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[699]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((lhs instanceof InterpretedFunction) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[352]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[700]++;
            InterpretedFunction f = (InterpretedFunction)lhs;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[701]++;
int CodeCoverConditionCoverageHelper_C116;
            if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((frame.fnOrScript.securityDomain == f.securityDomain) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[354]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[702]++;
                Scriptable newInstance = f.createObject(cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[703]++;
                CallFrame calleeFrame = new CallFrame();
                initFrame(cx, frame.scope, newInstance, stack, sDbl,
                          stackTop + 1, indexReg, f, frame,
                          calleeFrame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[704]++;

                stack[stackTop] = newInstance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[705]++;
                frame.savedStackTop = stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[706]++;
                frame.savedCallOp = op;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[707]++;
                frame = calleeFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[708]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[709]++;
                continue StateLoop;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[355]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[353]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[710]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((lhs instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[356]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[711]++;
int CodeCoverConditionCoverageHelper_C118;
            if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[358]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[712]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[359]++;}
            throw ScriptRuntime.notFunctionError(lhs);

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[357]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[713]++;
        Function fun = (Function)lhs;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[714]++;
int CodeCoverConditionCoverageHelper_C119;

        if ((((((CodeCoverConditionCoverageHelper_C119 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C119 |= (2)) == 0 || true) &&
 ((fun instanceof IdFunctionObject) && 
  ((CodeCoverConditionCoverageHelper_C119 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[119].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C119, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[360]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[715]++;
            IdFunctionObject ifun = (IdFunctionObject)fun;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[716]++;
int CodeCoverConditionCoverageHelper_C120;
            if ((((((CodeCoverConditionCoverageHelper_C120 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C120 |= (2)) == 0 || true) &&
 ((NativeContinuation.isContinuationConstructor(ifun)) && 
  ((CodeCoverConditionCoverageHelper_C120 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[120].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C120, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[362]++;
                frame.stack[stackTop] =
                    captureContinuation(cx, frame.parentFrame, false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[717]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[718]++;
                continue Loop;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[363]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[361]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[719]++;

        Object[] outArgs = getArgsArray(stack, sDbl, stackTop + 1, indexReg);
        stack[stackTop] = fun.construct(cx, frame.scope, outArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[720]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[721]++;
        continue Loop;
    }
    case Token.TYPEOF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[364]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[722]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[723]++;
int CodeCoverConditionCoverageHelper_C121;
        if ((((((CodeCoverConditionCoverageHelper_C121 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C121 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C121 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[121].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C121, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[365]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[724]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[366]++;}
        stack[stackTop] = ScriptRuntime.typeof(lhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[725]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[726]++;
        continue Loop;
    }
    case Icode_TYPEOFNAME :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[367]++;
        stack[++stackTop] = ScriptRuntime.typeofName(frame.scope, stringReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[727]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[728]++;
        continue Loop;
    case Token.STRING :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[368]++;
        stack[++stackTop] = stringReg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[729]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[730]++;
        continue Loop;
    case Icode_SHORTNUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[369]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[731]++;
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[732]++;
        sDbl[stackTop] = getShort(iCode, frame.pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[733]++;
        frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[734]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[735]++;
        continue Loop;
    case Icode_INTNUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[370]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[736]++;
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[737]++;
        sDbl[stackTop] = getInt(iCode, frame.pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[738]++;
        frame.pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[739]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[740]++;
        continue Loop;
    case Token.NUMBER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[371]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[741]++;
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[742]++;
        sDbl[stackTop] = frame.idata.itsDoubleTable[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[743]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[744]++;
        continue Loop;
    case Token.NAME :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[372]++;
        stack[++stackTop] = ScriptRuntime.name(cx, frame.scope, stringReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[745]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[746]++;
        continue Loop;
    case Icode_NAME_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[373]++;
        stack[++stackTop] = ScriptRuntime.nameIncrDecr(frame.scope, stringReg,
                                                       cx, iCode[frame.pc]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[747]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[748]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[749]++;
        continue Loop;
    case Icode_SETCONSTVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[374]++;
        indexReg = iCode[frame.pc++];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[750]++;
        // fallthrough
    case Token.SETCONSTVAR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[375]++;
        stackTop = doSetConstVar(frame, stack, sDbl, stackTop, vars, varDbls,
                                 varAttributes, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[751]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[752]++;
        continue Loop;
    case Icode_SETVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[376]++;
        indexReg = iCode[frame.pc++];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[753]++;
        // fallthrough
    case Token.SETVAR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[377]++;
        stackTop = doSetVar(frame, stack, sDbl, stackTop, vars, varDbls,
                            varAttributes, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[754]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[755]++;
        continue Loop;
    case Icode_GETVAR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[378]++;
        indexReg = iCode[frame.pc++];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[756]++;
        // fallthrough
    case Token.GETVAR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[379]++;
        stackTop = doGetVar(frame, stack, sDbl, stackTop, vars, varDbls, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[757]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[758]++;
        continue Loop;
    case Icode_VAR_INC_DEC :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[380]++; {
        stackTop = doVarIncDec(cx, frame, stack, sDbl, stackTop,
                               vars, varDbls, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[759]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[760]++;
        continue Loop;
    }
    case Icode_ZERO :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[381]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[761]++;
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[762]++;
        sDbl[stackTop] = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[763]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[764]++;
        continue Loop;
    case Icode_ONE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[382]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[765]++;
        stack[stackTop] = DBL_MRK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[766]++;
        sDbl[stackTop] = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[767]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[768]++;
        continue Loop;
    case Token.NULL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[383]++;
        stack[++stackTop] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[769]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[770]++;
        continue Loop;
    case Token.THIS :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[384]++;
        stack[++stackTop] = frame.thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[771]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[772]++;
        continue Loop;
    case Token.THISFN :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[385]++;
        stack[++stackTop] = frame.fnOrScript;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[773]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[774]++;
        continue Loop;
    case Token.FALSE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[386]++;
        stack[++stackTop] = Boolean.FALSE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[775]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[776]++;
        continue Loop;
    case Token.TRUE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[387]++;
        stack[++stackTop] = Boolean.TRUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[777]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[778]++;
        continue Loop;
    case Icode_UNDEF :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[388]++;
        stack[++stackTop] = undefined;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[779]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[780]++;
        continue Loop;
    case Token.ENTERWITH :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[389]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[781]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[782]++;
int CodeCoverConditionCoverageHelper_C122;
        if ((((((CodeCoverConditionCoverageHelper_C122 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C122 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C122 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[122].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C122, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[390]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[783]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[391]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[784]++;
        frame.scope = ScriptRuntime.enterWith(lhs, cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[785]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[786]++;
        continue Loop;
    }
    case Token.LEAVEWITH :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[392]++;
        frame.scope = ScriptRuntime.leaveWith(frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[787]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[788]++;
        continue Loop;
    case Token.CATCH_SCOPE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[393]++; {
        // stack top: exception object
        // stringReg: name of exception variable
        // indexReg: local for exception scope
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[789]++;
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[790]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[791]++;

        boolean afterFirstScope =  (frame.idata.itsICode[frame.pc] != 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[792]++;
        Throwable caughtException = (Throwable)stack[stackTop + 1];
        Scriptable lastCatchScope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[793]++;
int CodeCoverConditionCoverageHelper_C123;
        if ((((((CodeCoverConditionCoverageHelper_C123 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C123 |= (2)) == 0 || true) &&
 ((afterFirstScope) && 
  ((CodeCoverConditionCoverageHelper_C123 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[123].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C123, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[394]++;
            lastCatchScope = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[794]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[395]++;
            lastCatchScope = (Scriptable)stack[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[795]++;
        }
        stack[indexReg] = ScriptRuntime.newCatchScope(caughtException,
                                                      lastCatchScope, stringReg,
                                                      cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[796]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[797]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[798]++;
        continue Loop;
    }
    case Token.ENUM_INIT_KEYS :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[396]++;
    case Token.ENUM_INIT_VALUES :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[397]++;
    case Token.ENUM_INIT_ARRAY :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[398]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[799]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[800]++;
int CodeCoverConditionCoverageHelper_C124;
        if ((((((CodeCoverConditionCoverageHelper_C124 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C124 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C124 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[124].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C124, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[399]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[801]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[400]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[802]++;
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[803]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[804]++;
        int enumType = op == Token.ENUM_INIT_KEYS
                         ? ScriptRuntime.ENUMERATE_KEYS :
                       op == Token.ENUM_INIT_VALUES
                         ? ScriptRuntime.ENUMERATE_VALUES :
                       ScriptRuntime.ENUMERATE_ARRAY;
        stack[indexReg] = ScriptRuntime.enumInit(lhs, cx, enumType);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[805]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[806]++;
        continue Loop;
    }
    case Token.ENUM_NEXT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[401]++;
    case Token.ENUM_ID :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[402]++; {
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[807]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[808]++;
        Object val = stack[indexReg];
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[809]++;
        stack[stackTop] = (op == Token.ENUM_NEXT)
                          ? (Object)ScriptRuntime.enumNext(val)
                          : (Object)ScriptRuntime.enumId(val, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[810]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[811]++;
        continue Loop;
    }
    case Token.REF_SPECIAL :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[403]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[812]++;
        //stringReg: name of special property
        Object obj = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[813]++;
int CodeCoverConditionCoverageHelper_C125;
        if ((((((CodeCoverConditionCoverageHelper_C125 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C125 |= (2)) == 0 || true) &&
 ((obj == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C125 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[125].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C125, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[404]++; obj = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[814]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[405]++;}
        stack[stackTop] = ScriptRuntime.specialRef(obj, stringReg, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[815]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[816]++;
        continue Loop;
    }
    case Token.REF_MEMBER:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[406]++; {
        //indexReg: flags
        stackTop = doRefMember(cx, stack, sDbl, stackTop, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[817]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[818]++;
        continue Loop;
    }
    case Token.REF_NS_MEMBER:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[407]++; {
        //indexReg: flags
        stackTop = doRefNsMember(cx, stack, sDbl, stackTop, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[819]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[820]++;
        continue Loop;
    }
    case Token.REF_NAME:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[408]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[821]++;
        //indexReg: flags
        Object name = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[822]++;
int CodeCoverConditionCoverageHelper_C126;
        if ((((((CodeCoverConditionCoverageHelper_C126 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C126 |= (2)) == 0 || true) &&
 ((name == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C126 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[126].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C126, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[409]++; name = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[823]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[410]++;}
        stack[stackTop] = ScriptRuntime.nameRef(name, cx, frame.scope,
                                                indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[824]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[825]++;
        continue Loop;
    }
    case Token.REF_NS_NAME:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[411]++; {
        //indexReg: flags
        stackTop = doRefNsName(cx, frame, stack, sDbl, stackTop, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[826]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[827]++;
        continue Loop;
    }
    case Icode_SCOPE_LOAD :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[412]++;
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[828]++;
        frame.scope = (Scriptable)stack[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[829]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[830]++;
        continue Loop;
    case Icode_SCOPE_SAVE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[413]++;
        indexReg += frame.localShift;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[831]++;
        stack[indexReg] = frame.scope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[832]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[833]++;
        continue Loop;
    case Icode_CLOSURE_EXPR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[414]++;
        stack[++stackTop] = InterpretedFunction.createFunction(cx, frame.scope,
                                                               frame.fnOrScript,
                                                               indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[834]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[835]++;
        continue Loop;
    case Icode_CLOSURE_STMT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[415]++;
        initFunction(cx, frame.scope, frame.fnOrScript, indexReg);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[836]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[837]++;
        continue Loop;
    case Token.REGEXP :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[416]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[838]++;
        Object re = frame.idata.itsRegExpLiterals[indexReg];
        stack[++stackTop] = ScriptRuntime.wrapRegExp(cx, frame.scope, re);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[839]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[840]++;
        continue Loop;
    case Icode_LITERAL_NEW :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[417]++;
        // indexReg: number of values in the literal
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[841]++;
        stack[stackTop] = new int[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[842]++;
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[843]++;
        stack[stackTop] = new Object[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[844]++;
        sDbl[stackTop] = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[845]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[846]++;
        continue Loop;
    case Icode_LITERAL_SET :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[418]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[847]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[848]++;
int CodeCoverConditionCoverageHelper_C127;
        if ((((((CodeCoverConditionCoverageHelper_C127 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C127 |= (2)) == 0 || true) &&
 ((value == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C127 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[127].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C127, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[419]++; value = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[849]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[420]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[850]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[851]++;
        int i = (int)sDbl[stackTop];
        ((Object[])stack[stackTop])[i] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[852]++;
        sDbl[stackTop] = i + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[853]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[854]++;
        continue Loop;
    }
    case Icode_LITERAL_GETTER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[421]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[855]++;
        Object value = stack[stackTop];
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[856]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[857]++;
        int i = (int)sDbl[stackTop];
        ((Object[])stack[stackTop])[i] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[858]++;
        ((int[])stack[stackTop - 1])[i] = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[859]++;
        sDbl[stackTop] = i + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[860]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[861]++;
        continue Loop;
    }
    case Icode_LITERAL_SETTER :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[422]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[862]++;
        Object value = stack[stackTop];
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[863]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[864]++;
        int i = (int)sDbl[stackTop];
        ((Object[])stack[stackTop])[i] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[865]++;
        ((int[])stack[stackTop - 1])[i] = +1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[866]++;
        sDbl[stackTop] = i + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[867]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[868]++;
        continue Loop;
    }
    case Token.ARRAYLIT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[423]++;
    case Icode_SPARE_ARRAYLIT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[424]++;
    case Token.OBJECTLIT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[425]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[869]++;
        Object[] data = (Object[])stack[stackTop];
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[870]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[871]++;
        int[] getterSetters = (int[])stack[stackTop];
        Object val;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[872]++;
int CodeCoverConditionCoverageHelper_C128;
        if ((((((CodeCoverConditionCoverageHelper_C128 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C128 |= (2)) == 0 || true) &&
 ((op == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C128 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[128].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C128, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[426]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[873]++;
            Object[] ids = (Object[])frame.idata.literalIds[indexReg];
            val = ScriptRuntime.newObjectLiteral(ids, data, getterSetters, cx,
                    frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[874]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[427]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[875]++;
            int[] skipIndexces = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[876]++;
int CodeCoverConditionCoverageHelper_C129;
            if ((((((CodeCoverConditionCoverageHelper_C129 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C129 |= (2)) == 0 || true) &&
 ((op == Icode_SPARE_ARRAYLIT) && 
  ((CodeCoverConditionCoverageHelper_C129 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[129].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C129, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[428]++;
                skipIndexces = (int[])frame.idata.literalIds[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[877]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[429]++;}
            val = ScriptRuntime.newArrayLiteral(data, skipIndexces, cx,
                                                frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[878]++;
        }
        stack[stackTop] = val;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[879]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[880]++;
        continue Loop;
    }
    case Icode_ENTERDQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[430]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[881]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[882]++;
int CodeCoverConditionCoverageHelper_C130;
        if ((((((CodeCoverConditionCoverageHelper_C130 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C130 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C130 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[130].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C130, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[431]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[883]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[432]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[884]++;
        frame.scope = ScriptRuntime.enterDotQuery(lhs, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[885]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[886]++;
        continue Loop;
    }
    case Icode_LEAVEDQ :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[433]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[887]++;
        boolean valBln = stack_boolean(frame, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[888]++;
        Object x = ScriptRuntime.updateDotQuery(valBln, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[889]++;
int CodeCoverConditionCoverageHelper_C131;
        if ((((((CodeCoverConditionCoverageHelper_C131 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C131 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C131 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[131].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C131, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[434]++;
            stack[stackTop] = x;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[890]++;
            frame.scope = ScriptRuntime.leaveDotQuery(frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[891]++;
            frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[892]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[893]++;
            continue Loop;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[435]++;}
        // reset stack and PC to code after ENTERDQ
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[894]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[895]++;
        break jumplessRun;
    }
    case Token.DEFAULTNAMESPACE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[436]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[896]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[897]++;
int CodeCoverConditionCoverageHelper_C132;
        if ((((((CodeCoverConditionCoverageHelper_C132 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C132 |= (2)) == 0 || true) &&
 ((value == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C132 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[132].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C132, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[437]++; value = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[898]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[438]++;}
        stack[stackTop] = ScriptRuntime.setDefaultNamespace(value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[899]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[900]++;
        continue Loop;
    }
    case Token.ESCXMLATTR :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[439]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[901]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[902]++;
int CodeCoverConditionCoverageHelper_C133;
        if ((((((CodeCoverConditionCoverageHelper_C133 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C133 |= (2)) == 0 || true) &&
 ((value != DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C133 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[133].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C133, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[440]++;
            stack[stackTop] = ScriptRuntime.escapeAttributeValue(value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[903]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[441]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[904]++;
        continue Loop;
    }
    case Token.ESCXMLTEXT :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[442]++; {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[905]++;
        Object value = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[906]++;
int CodeCoverConditionCoverageHelper_C134;
        if ((((((CodeCoverConditionCoverageHelper_C134 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C134 |= (2)) == 0 || true) &&
 ((value != DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C134 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[134].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C134, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[443]++;
            stack[stackTop] = ScriptRuntime.escapeTextValue(value, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[907]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[444]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[908]++;
        continue Loop;
    }
    case Icode_DEBUGGER:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[445]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[909]++;
int CodeCoverConditionCoverageHelper_C135;
        if ((((((CodeCoverConditionCoverageHelper_C135 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C135 |= (2)) == 0 || true) &&
 ((frame.debuggerFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C135 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[135].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C135, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[446]++;
            frame.debuggerFrame.onDebuggerStatement(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[910]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[447]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[911]++;
        continue Loop;
    case Icode_LINE :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[448]++;
        frame.pcSourceLineStart = frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[912]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[913]++;
int CodeCoverConditionCoverageHelper_C136;
        if ((((((CodeCoverConditionCoverageHelper_C136 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C136 |= (2)) == 0 || true) &&
 ((frame.debuggerFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C136 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[136].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C136, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[449]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[914]++;
            int line = getIndex(iCode, frame.pc);
            frame.debuggerFrame.onLineChange(cx, line);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[915]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[450]++;}
        frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[916]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[917]++;
        continue Loop;
    case Icode_REG_IND_C0:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[451]++;
        indexReg = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[918]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[919]++;
        continue Loop;
    case Icode_REG_IND_C1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[452]++;
        indexReg = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[920]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[921]++;
        continue Loop;
    case Icode_REG_IND_C2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[453]++;
        indexReg = 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[922]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[923]++;
        continue Loop;
    case Icode_REG_IND_C3:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[454]++;
        indexReg = 3;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[924]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[925]++;
        continue Loop;
    case Icode_REG_IND_C4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[455]++;
        indexReg = 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[926]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[927]++;
        continue Loop;
    case Icode_REG_IND_C5:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[456]++;
        indexReg = 5;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[928]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[929]++;
        continue Loop;
    case Icode_REG_IND1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[457]++;
        indexReg = 0xFF & iCode[frame.pc];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[930]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[931]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[932]++;
        continue Loop;
    case Icode_REG_IND2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[458]++;
        indexReg = getIndex(iCode, frame.pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[933]++;
        frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[934]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[935]++;
        continue Loop;
    case Icode_REG_IND4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[459]++;
        indexReg = getInt(iCode, frame.pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[936]++;
        frame.pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[937]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[938]++;
        continue Loop;
    case Icode_REG_STR_C0:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[460]++;
        stringReg = strings[0];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[939]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[940]++;
        continue Loop;
    case Icode_REG_STR_C1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[461]++;
        stringReg = strings[1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[941]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[942]++;
        continue Loop;
    case Icode_REG_STR_C2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[462]++;
        stringReg = strings[2];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[943]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[944]++;
        continue Loop;
    case Icode_REG_STR_C3:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[463]++;
        stringReg = strings[3];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[945]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[946]++;
        continue Loop;
    case Icode_REG_STR1:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[464]++;
        stringReg = strings[0xFF & iCode[frame.pc]];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[947]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[948]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[949]++;
        continue Loop;
    case Icode_REG_STR2:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[465]++;
        stringReg = strings[getIndex(iCode, frame.pc)];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[950]++;
        frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[951]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[952]++;
        continue Loop;
    case Icode_REG_STR4:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[466]++;
        stringReg = strings[getInt(iCode, frame.pc)];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[953]++;
        frame.pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[954]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[955]++;
        continue Loop;
    default :
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[467]++;
        dumpICode(frame.idata);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[956]++;
        throw new RuntimeException("Unknown icode : " + op
                                 + " @ pc : " + (frame.pc-1));
}  // end of interpreter switch

                    }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[957]++;
int CodeCoverConditionCoverageHelper_C137; // end of jumplessRun label block

                    // This should be reachable only for jump implementation
                    // when pc points to encoded target offset
                    if ((((((CodeCoverConditionCoverageHelper_C137 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C137 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C137 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[137].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C137, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[468]++;
                        addInstructionCount(cx, frame, 2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[958]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[469]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[959]++;
                    int offset = getShort(iCode, frame.pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[960]++;
int CodeCoverConditionCoverageHelper_C138;
                    if ((((((CodeCoverConditionCoverageHelper_C138 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C138 |= (2)) == 0 || true) &&
 ((offset != 0) && 
  ((CodeCoverConditionCoverageHelper_C138 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[138].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C138, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[470]++;
                        // -1 accounts for pc pointing to jump opcode + 1
                        frame.pc += offset - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[961]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[471]++;
                        frame.pc = frame.idata.longJumps.
                                       getExistingInt(frame.pc);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[962]++;
                    }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[963]++;
int CodeCoverConditionCoverageHelper_C139;
                    if ((((((CodeCoverConditionCoverageHelper_C139 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C139 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C139 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[139].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C139, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[472]++;
                        frame.pcPrevBranch = frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[964]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[473]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[965]++;
                    continue Loop;

                } // end of Loop: for

                exitFrame(cx, frame, null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[966]++;
                interpreterResult = frame.result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[967]++;
                interpreterResultDbl = frame.resultDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[968]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[969]++;
int CodeCoverConditionCoverageHelper_C140;
                if ((((((CodeCoverConditionCoverageHelper_C140 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C140 |= (2)) == 0 || true) &&
 ((frame.parentFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C140 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[140].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C140, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[474]++;
                    frame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[970]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[971]++;
int CodeCoverConditionCoverageHelper_C141;
                    if ((((((CodeCoverConditionCoverageHelper_C141 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C141 |= (2)) == 0 || true) &&
 ((frame.frozen) && 
  ((CodeCoverConditionCoverageHelper_C141 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[141].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C141, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[476]++;
                        frame = frame.cloneFrozen();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[972]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[477]++;}
                    setCallResult(
                        frame, interpreterResult, interpreterResultDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[973]++;
                    interpreterResult = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[974]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[975]++; // Help GC
                    continue StateLoop;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[475]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[976]++;
                break StateLoop;

            }  // end of interpreter withoutExceptions: try
            catch (Throwable ex) {
CodeCoverTryBranchHelper_Try4 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[478]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[977]++;
int CodeCoverConditionCoverageHelper_C142;
                if ((((((CodeCoverConditionCoverageHelper_C142 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C142 |= (2)) == 0 || true) &&
 ((throwable != null) && 
  ((CodeCoverConditionCoverageHelper_C142 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[142].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C142, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[479]++;
                    // This is serious bug and it is better to track it ASAP
                    ex.printStackTrace(System.err);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[978]++;
                    throw new IllegalStateException();

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[480]++;}
                throwable = ex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[979]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try4 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[186]++;
}
  }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[980]++;
int CodeCoverConditionCoverageHelper_C143;

            // This should be reachable only after above catch or from
            // finally when it needs to propagate exception or from
            // explicit throw
            if ((((((CodeCoverConditionCoverageHelper_C143 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C143 |= (2)) == 0 || true) &&
 ((throwable == null) && 
  ((CodeCoverConditionCoverageHelper_C143 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[143].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C143, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[481]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[981]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[482]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[982]++;

            // Exception type
            final int EX_CATCH_STATE = 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[983]++; // Can execute JS catch
            final int EX_FINALLY_STATE = 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[984]++; // Can execute JS finally
            final int EX_NO_JS_STATE = 0; // Terminate JS execution

            int exState;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[985]++;
            ContinuationJump cjump = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[986]++;
int CodeCoverConditionCoverageHelper_C144;

            if ((((((CodeCoverConditionCoverageHelper_C144 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C144 |= (32)) == 0 || true) &&
 ((generatorState != null) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C144 |= (8)) == 0 || true) &&
 ((generatorState.operation == NativeGenerator.GENERATOR_CLOSE) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C144 |= (2)) == 0 || true) &&
 ((throwable == generatorState.value) && 
  ((CodeCoverConditionCoverageHelper_C144 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[144].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C144, 3) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[483]++;
                exState = EX_FINALLY_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[987]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[484]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[988]++;
int CodeCoverConditionCoverageHelper_C145; if ((((((CodeCoverConditionCoverageHelper_C145 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C145 |= (2)) == 0 || true) &&
 ((throwable instanceof JavaScriptException) && 
  ((CodeCoverConditionCoverageHelper_C145 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[145].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C145, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[485]++;
                exState = EX_CATCH_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[989]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[486]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[990]++;
int CodeCoverConditionCoverageHelper_C146; if ((((((CodeCoverConditionCoverageHelper_C146 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C146 |= (2)) == 0 || true) &&
 ((throwable instanceof EcmaError) && 
  ((CodeCoverConditionCoverageHelper_C146 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[146].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C146, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[487]++;
                // an offical ECMA error object,
                exState = EX_CATCH_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[991]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[488]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[992]++;
int CodeCoverConditionCoverageHelper_C147; if ((((((CodeCoverConditionCoverageHelper_C147 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C147 |= (2)) == 0 || true) &&
 ((throwable instanceof EvaluatorException) && 
  ((CodeCoverConditionCoverageHelper_C147 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[147].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C147, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[489]++;
                exState = EX_CATCH_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[993]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[490]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[994]++;
int CodeCoverConditionCoverageHelper_C148; if ((((((CodeCoverConditionCoverageHelper_C148 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C148 |= (2)) == 0 || true) &&
 ((throwable instanceof ContinuationPending) && 
  ((CodeCoverConditionCoverageHelper_C148 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[148].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C148, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[491]++;
                exState = EX_NO_JS_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[995]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[492]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[996]++;
int CodeCoverConditionCoverageHelper_C149; if ((((((CodeCoverConditionCoverageHelper_C149 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C149 |= (2)) == 0 || true) &&
 ((throwable instanceof RuntimeException) && 
  ((CodeCoverConditionCoverageHelper_C149 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[149].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C149, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[493]++;
                exState = cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)
                          ? EX_CATCH_STATE
                          : EX_FINALLY_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[997]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[494]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[998]++;
int CodeCoverConditionCoverageHelper_C150; if ((((((CodeCoverConditionCoverageHelper_C150 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C150 |= (2)) == 0 || true) &&
 ((throwable instanceof Error) && 
  ((CodeCoverConditionCoverageHelper_C150 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[150].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C150, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[495]++;
                exState = cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)
                          ? EX_CATCH_STATE
                          : EX_NO_JS_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[999]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[496]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1000]++;
int CodeCoverConditionCoverageHelper_C151; if ((((((CodeCoverConditionCoverageHelper_C151 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C151 |= (2)) == 0 || true) &&
 ((throwable instanceof ContinuationJump) && 
  ((CodeCoverConditionCoverageHelper_C151 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[151].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C151, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[497]++;
                // It must be ContinuationJump
                exState = EX_FINALLY_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1001]++;
                cjump = (ContinuationJump)throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1002]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[498]++;
                exState = cx.hasFeature(Context.FEATURE_ENHANCED_JAVA_ACCESS)
                          ? EX_CATCH_STATE
                          : EX_FINALLY_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1003]++;
            }
}
}
}
}
}
}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1004]++;
int CodeCoverConditionCoverageHelper_C152;

            if ((((((CodeCoverConditionCoverageHelper_C152 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C152 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C152 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[152].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C152, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[499]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1005]++;
boolean CodeCoverTryBranchHelper_Try5 = false;
                try {
CodeCoverTryBranchHelper_Try5 = true;
                    addInstructionCount(cx, frame, EXCEPTION_COST);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1006]++;
                } catch (RuntimeException ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[502]++;
                    throwable = ex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1007]++;
                    exState = EX_FINALLY_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1008]++;
                } catch (Error ex) {
CodeCoverTryBranchHelper_Try5 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[503]++;
                    // Error from instruction counting
                    //     => unconditionally terminate JS
                    throwable = ex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1009]++;
                    cjump = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1010]++;
                    exState = EX_NO_JS_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1011]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try5 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[501]++;
}
  }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[500]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1012]++;
int CodeCoverConditionCoverageHelper_C153;
            if ((((((CodeCoverConditionCoverageHelper_C153 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C153 |= (8)) == 0 || true) &&
 ((frame.debuggerFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C153 |= (2)) == 0 || true) &&
 ((throwable instanceof RuntimeException) && 
  ((CodeCoverConditionCoverageHelper_C153 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[153].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C153, 2) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[504]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1013]++;
                // Call debugger only for RuntimeException
                RuntimeException rex = (RuntimeException)throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1014]++;
boolean CodeCoverTryBranchHelper_Try6 = false;
                try {
CodeCoverTryBranchHelper_Try6 = true;
                    frame.debuggerFrame.onExceptionThrown(cx, rex);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1015]++;
                } catch (Throwable ex) {
CodeCoverTryBranchHelper_Try6 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[507]++;
                    // Any exception from debugger
                    //     => unconditionally terminate JS
                    throwable = ex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1016]++;
                    cjump = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1017]++;
                    exState = EX_NO_JS_STATE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1018]++;
                } finally {
    if ( CodeCoverTryBranchHelper_Try6 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[506]++;
}
  }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[505]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1019]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[55]++;



            for (;;) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[55]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[56]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[57]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1020]++;
int CodeCoverConditionCoverageHelper_C155;
                if ((((((CodeCoverConditionCoverageHelper_C155 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C155 |= (2)) == 0 || true) &&
 ((exState != EX_NO_JS_STATE) && 
  ((CodeCoverConditionCoverageHelper_C155 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[155].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C155, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[508]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1021]++;
                    boolean onlyFinally = (exState != EX_CATCH_STATE);
                    indexReg = getExceptionHandler(frame, onlyFinally);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1022]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1023]++;
int CodeCoverConditionCoverageHelper_C156;
                    if ((((((CodeCoverConditionCoverageHelper_C156 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C156 |= (2)) == 0 || true) &&
 ((indexReg >= 0) && 
  ((CodeCoverConditionCoverageHelper_C156 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[156].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C156, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[510]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1024]++;
                        // We caught an exception, restart the loop
                        // with exception pending the processing at the loop
                        // start
                        continue StateLoop;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[511]++;}

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[509]++;}
                // No allowed exception handlers in this frame, unwind
                // to parent and try to look there

                exitFrame(cx, frame, throwable);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1025]++;

                frame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1026]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1027]++;
int CodeCoverConditionCoverageHelper_C157;
                if ((((((CodeCoverConditionCoverageHelper_C157 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C157 |= (2)) == 0 || true) &&
 ((frame == null) && 
  ((CodeCoverConditionCoverageHelper_C157 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[157].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C157, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[512]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1028]++; break;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[513]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1029]++;
int CodeCoverConditionCoverageHelper_C158;
                if ((((((CodeCoverConditionCoverageHelper_C158 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C158 |= (8)) == 0 || true) &&
 ((cjump != null) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C158 |= (2)) == 0 || true) &&
 ((cjump.branchFrame == frame) && 
  ((CodeCoverConditionCoverageHelper_C158 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[158].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C158, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[514]++;
                    // Continuation branch point was hit,
                    // restart the state loop to reenter continuation
                    indexReg = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1030]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1031]++;
                    continue StateLoop;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[515]++;}
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1032]++;
int CodeCoverConditionCoverageHelper_C159;

            // No more frames, rethrow the exception or deal with continuation
            if ((((((CodeCoverConditionCoverageHelper_C159 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C159 |= (2)) == 0 || true) &&
 ((cjump != null) && 
  ((CodeCoverConditionCoverageHelper_C159 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[159].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C159, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[516]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1033]++;
int CodeCoverConditionCoverageHelper_C160;
                if ((((((CodeCoverConditionCoverageHelper_C160 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C160 |= (2)) == 0 || true) &&
 ((cjump.branchFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C160 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[160].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C160, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[518]++;
                    // The above loop should locate the top frame
                    Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1034]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[519]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1035]++;
int CodeCoverConditionCoverageHelper_C161;
                if ((((((CodeCoverConditionCoverageHelper_C161 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C161 |= (2)) == 0 || true) &&
 ((cjump.capturedFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C161 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[161].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C161, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[520]++;
                    // Restarting detached continuation
                    indexReg = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1036]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1037]++;
                    continue StateLoop;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[521]++;}
                // Return continuation result to the caller
                interpreterResult = cjump.result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1038]++;
                interpreterResultDbl = cjump.resultDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1039]++;
                throwable = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1040]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[517]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1041]++;
            break StateLoop;

        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1042]++;
int CodeCoverConditionCoverageHelper_C162; // end of StateLoop: for(;;)

        // Do cleanups/restorations before the final return or throw

        if ((((((CodeCoverConditionCoverageHelper_C162 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C162 |= (8)) == 0 || true) &&
 ((cx.previousInterpreterInvocations != null) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C162 |= (2)) == 0 || true) &&
 ((cx.previousInterpreterInvocations.size() != 0) && 
  ((CodeCoverConditionCoverageHelper_C162 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[162].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C162, 2) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[522]++;
            cx.lastInterpreterFrame
                = cx.previousInterpreterInvocations.pop();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1043]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[523]++;
            // It was the last interpreter frame on the stack
            cx.lastInterpreterFrame = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1044]++;
            // Force GC of the value cx.previousInterpreterInvocations
            cx.previousInterpreterInvocations = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1045]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1046]++;
int CodeCoverConditionCoverageHelper_C163;

        if ((((((CodeCoverConditionCoverageHelper_C163 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C163 |= (2)) == 0 || true) &&
 ((throwable != null) && 
  ((CodeCoverConditionCoverageHelper_C163 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[163].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C163, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[524]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1047]++;
int CodeCoverConditionCoverageHelper_C164;
            if ((((((CodeCoverConditionCoverageHelper_C164 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C164 |= (2)) == 0 || true) &&
 ((throwable instanceof RuntimeException) && 
  ((CodeCoverConditionCoverageHelper_C164 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[164].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C164, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[526]++;
                throw (RuntimeException)throwable;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[527]++;
                // Must be instance of Error or code bug
                throw (Error)throwable;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[525]++;}

        return (interpreterResult != DBL_MRK)
               ? interpreterResult
               : ScriptRuntime.wrapNumber(interpreterResultDbl);
    }

    private static int doInOrInstanceof(Context cx, int op, Object[] stack,
                                        double[] sDbl, int stackTop) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1048]++;
        Object rhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1049]++;
int CodeCoverConditionCoverageHelper_C165;
        if ((((((CodeCoverConditionCoverageHelper_C165 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C165 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C165 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[165].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C165, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[528]++; rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1050]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[529]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1051]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1052]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1053]++;
int CodeCoverConditionCoverageHelper_C166;
        if ((((((CodeCoverConditionCoverageHelper_C166 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C166 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C166 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[166].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C166, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[530]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1054]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[531]++;}
        boolean valBln;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1055]++;
int CodeCoverConditionCoverageHelper_C167;
        if ((((((CodeCoverConditionCoverageHelper_C167 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C167 |= (2)) == 0 || true) &&
 ((op == Token.IN) && 
  ((CodeCoverConditionCoverageHelper_C167 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[167].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C167, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[532]++;
            valBln = ScriptRuntime.in(lhs, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1056]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[533]++;
            valBln = ScriptRuntime.instanceOf(lhs, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1057]++;
        }
        stack[stackTop] = ScriptRuntime.wrapBoolean(valBln);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1058]++;
        return stackTop;
    }

    private static int doCompare(CallFrame frame, int op, Object[] stack,
                                 double[] sDbl, int stackTop) {
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1059]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1060]++;
        Object rhs = stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1061]++;
        Object lhs = stack[stackTop];
        boolean valBln;
        object_compare:
        {
            number_compare:
            {
                double rDbl, lDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1062]++;
int CodeCoverConditionCoverageHelper_C168;
                if ((((((CodeCoverConditionCoverageHelper_C168 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C168 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C168 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[168].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C168, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[534]++;
                    rDbl = sDbl[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1063]++;
                    lDbl = stack_double(frame, stackTop);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1064]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[535]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1065]++;
int CodeCoverConditionCoverageHelper_C169; if ((((((CodeCoverConditionCoverageHelper_C169 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C169 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C169 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[169].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C169, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[536]++;
                    rDbl = ScriptRuntime.toNumber(rhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1066]++;
                    lDbl = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1067]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[537]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1068]++;
                    break number_compare;
                }
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1069]++;
                switch (op) {
                    case Token.GE:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[538]++;
                        valBln = (lDbl >= rDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1070]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1071]++;
                        break object_compare;
                    case Token.LE:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[539]++;
                        valBln = (lDbl <= rDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1072]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1073]++;
                        break object_compare;
                    case Token.GT:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[540]++;
                        valBln = (lDbl > rDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1074]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1075]++;
                        break object_compare;
                    case Token.LT:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[541]++;
                        valBln = (lDbl < rDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1076]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1077]++;
                        break object_compare;
                    default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[542]++;
                        throw Kit.codeBug();
                }
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1078]++;
            switch (op) {
                case Token.GE:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[543]++;
                    valBln = ScriptRuntime.cmp_LE(rhs, lhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1079]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1080]++;
                    break;
                case Token.LE:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[544]++;
                    valBln = ScriptRuntime.cmp_LE(lhs, rhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1081]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1082]++;
                    break;
                case Token.GT:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[545]++;
                    valBln = ScriptRuntime.cmp_LT(rhs, lhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1083]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1084]++;
                    break;
                case Token.LT:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[546]++;
                    valBln = ScriptRuntime.cmp_LT(lhs, rhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1085]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1086]++;
                    break;
                default:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[547]++;
                    throw Kit.codeBug();
            }
        }
        stack[stackTop] = ScriptRuntime.wrapBoolean(valBln);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1087]++;
        return stackTop;
    }

    private static int doBitOp(CallFrame frame, int op, Object[] stack,
                               double[] sDbl, int stackTop) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1088]++;
        int lIntValue = stack_int32(frame, stackTop - 1);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1089]++;
        int rIntValue = stack_int32(frame, stackTop);
        stack[--stackTop] = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1090]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1091]++;
        switch (op) {
          case Token.BITAND:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[548]++;
            lIntValue &= rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1092]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1093]++;
            break;
          case Token.BITOR:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[549]++;
            lIntValue |= rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1094]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1095]++;
            break;
          case Token.BITXOR:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[550]++;
            lIntValue ^= rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1096]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1097]++;
            break;
          case Token.LSH:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[551]++;
            lIntValue <<= rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1098]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1099]++;
            break;
          case Token.RSH:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[552]++;
            lIntValue >>= rIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1100]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1101]++;
            break; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[553]++;
        }
        sDbl[stackTop] = lIntValue;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1102]++;
        return stackTop;
    }

    private static int doDelName(Context cx, int op, Object[] stack,
                                 double[] sDbl, int stackTop) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1103]++;
        Object rhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1104]++;
int CodeCoverConditionCoverageHelper_C170;
        if ((((((CodeCoverConditionCoverageHelper_C170 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C170 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C170 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[170].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C170, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[554]++; rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1105]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[555]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1106]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1107]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1108]++;
int CodeCoverConditionCoverageHelper_C171;
        if ((((((CodeCoverConditionCoverageHelper_C171 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C171 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C171 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[171].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C171, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[556]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1109]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[557]++;}
        stack[stackTop] = ScriptRuntime.delete(lhs, rhs, cx, op == Icode_DELNAME);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1110]++;
        return stackTop;
    }

    private static int doGetElem(Context cx, CallFrame frame, Object[] stack,
                                 double[] sDbl, int stackTop) {
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1111]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1112]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1113]++;
int CodeCoverConditionCoverageHelper_C172;
        if ((((((CodeCoverConditionCoverageHelper_C172 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C172 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C172 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[172].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C172, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[558]++;
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1114]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[559]++;}
        Object value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1115]++;
        Object id = stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1116]++;
int CodeCoverConditionCoverageHelper_C173;
        if ((((((CodeCoverConditionCoverageHelper_C173 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C173 |= (2)) == 0 || true) &&
 ((id != DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C173 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[173].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C173, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[560]++;
            value = ScriptRuntime.getObjectElem(lhs, id, cx, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1117]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[561]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1118]++;
            double d = sDbl[stackTop + 1];
            value = ScriptRuntime.getObjectIndex(lhs, d, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1119]++;
        }
        stack[stackTop] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1120]++;
        return stackTop;
    }

    private static int doSetElem(Context cx, Object[] stack, double[] sDbl,
                                 int stackTop) {
        stackTop -= 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1121]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1122]++;
        Object rhs = stack[stackTop + 2];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1123]++;
int CodeCoverConditionCoverageHelper_C174;
        if ((((((CodeCoverConditionCoverageHelper_C174 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C174 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C174 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[174].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C174, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[562]++;
            rhs = ScriptRuntime.wrapNumber(sDbl[stackTop + 2]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1124]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[563]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1125]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1126]++;
int CodeCoverConditionCoverageHelper_C175;
        if ((((((CodeCoverConditionCoverageHelper_C175 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C175 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C175 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[175].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C175, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[564]++;
            lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1127]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[565]++;}
        Object value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1128]++;
        Object id = stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1129]++;
int CodeCoverConditionCoverageHelper_C176;
        if ((((((CodeCoverConditionCoverageHelper_C176 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C176 |= (2)) == 0 || true) &&
 ((id != DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C176 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[176].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C176, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[566]++;
            value = ScriptRuntime.setObjectElem(lhs, id, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1130]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[567]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1131]++;
            double d = sDbl[stackTop + 1];
            value = ScriptRuntime.setObjectIndex(lhs, d, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1132]++;
        }
        stack[stackTop] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1133]++;
        return stackTop;
    }

    private static int doElemIncDec(Context cx, CallFrame frame, byte[] iCode,
                                    Object[] stack, double[] sDbl, int stackTop) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1134]++;
        Object rhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1135]++;
int CodeCoverConditionCoverageHelper_C177;
        if ((((((CodeCoverConditionCoverageHelper_C177 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C177 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C177 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[177].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C177, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[568]++; rhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1136]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[569]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1137]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1138]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1139]++;
int CodeCoverConditionCoverageHelper_C178;
        if ((((((CodeCoverConditionCoverageHelper_C178 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C178 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C178 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[178].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C178, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[570]++; lhs = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1140]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[571]++;}
        stack[stackTop] = ScriptRuntime.elemIncrDecr(lhs, rhs, cx,
                                                     iCode[frame.pc]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1141]++;
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1142]++;
        return stackTop;
    }

    private static int doCallSpecial(Context cx, CallFrame frame,
                                     Object[] stack, double[] sDbl,
                                     int stackTop, byte[] iCode,
                                     int indexReg) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1143]++;
        int callType = iCode[frame.pc] & 0xFF;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1144]++;
        boolean isNew =  (iCode[frame.pc + 1] != 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1145]++;
        int sourceLine = getIndex(iCode, frame.pc + 2);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1146]++;
int CodeCoverConditionCoverageHelper_C179;

        // indexReg: number of arguments
        if ((((((CodeCoverConditionCoverageHelper_C179 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C179 |= (2)) == 0 || true) &&
 ((isNew) && 
  ((CodeCoverConditionCoverageHelper_C179 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[179].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C179, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[572]++;
            // stack change: function arg0 .. argN -> newResult
            stackTop -= indexReg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1147]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1148]++;

            Object function = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1149]++;
int CodeCoverConditionCoverageHelper_C180;
            if ((((((CodeCoverConditionCoverageHelper_C180 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C180 |= (2)) == 0 || true) &&
 ((function == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C180 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[180].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C180, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[574]++;
                function = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1150]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[575]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1151]++;
            Object[] outArgs = getArgsArray(
                                   stack, sDbl, stackTop + 1, indexReg);
            stack[stackTop] = ScriptRuntime.newSpecial(
                                  cx, function, outArgs, frame.scope, callType);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1152]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[573]++;
            // stack change: function thisObj arg0 .. argN -> result
            stackTop -= 1 + indexReg;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1153]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1154]++;

            // Call code generation ensure that stack here
            // is ... Callable Scriptable
            Scriptable functionThis = (Scriptable)stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1155]++;
            Callable function = (Callable)stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1156]++;
            Object[] outArgs = getArgsArray(
                                   stack, sDbl, stackTop + 2, indexReg);
            stack[stackTop] = ScriptRuntime.callSpecial(
                                  cx, function, functionThis, outArgs,
                                  frame.scope, frame.thisObj, callType,
                                  frame.idata.itsSourceFile, sourceLine);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1157]++;
        }
        frame.pc += 4;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1158]++;
        return stackTop;
    }

    private static int doSetConstVar(CallFrame frame, Object[] stack,
                                     double[] sDbl, int stackTop,
                                     Object[] vars, double[] varDbls,
                                     int[] varAttributes, int indexReg) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1159]++;
int CodeCoverConditionCoverageHelper_C181;
        if ((((((CodeCoverConditionCoverageHelper_C181 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C181 |= (2)) == 0 || true) &&
 ((frame.useActivation) && 
  ((CodeCoverConditionCoverageHelper_C181 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[181].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C181, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[576]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1160]++;
int CodeCoverConditionCoverageHelper_C182;
            if ((((((CodeCoverConditionCoverageHelper_C182 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C182 |= (2)) == 0 || true) &&
 (((varAttributes[indexReg] & ScriptableObject.READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C182 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[182].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C182, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[578]++;
                throw Context.reportRuntimeError1("msg.var.redecl",
                                                  frame.idata.argNames[indexReg]);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[579]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1161]++;
int CodeCoverConditionCoverageHelper_C183;
            if ((((((CodeCoverConditionCoverageHelper_C183 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C183 |= (2)) == 0 || true) &&
 (((varAttributes[indexReg] & ScriptableObject.UNINITIALIZED_CONST)
                != 0) && 
  ((CodeCoverConditionCoverageHelper_C183 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[183].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C183, 1) && false))
            {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[580]++;
                vars[indexReg] = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1162]++;
                varAttributes[indexReg] &= ~ScriptableObject.UNINITIALIZED_CONST;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1163]++;
                varDbls[indexReg] = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1164]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[581]++;}

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[577]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1165]++;
            Object val = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1166]++;
int CodeCoverConditionCoverageHelper_C184;
            if ((((((CodeCoverConditionCoverageHelper_C184 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C184 |= (2)) == 0 || true) &&
 ((val == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C184 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[184].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C184, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[582]++; val = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1167]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[583]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1168]++;
            String stringReg = frame.idata.argNames[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1169]++;
int CodeCoverConditionCoverageHelper_C185;
            if ((((((CodeCoverConditionCoverageHelper_C185 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C185 |= (2)) == 0 || true) &&
 ((frame.scope instanceof ConstProperties) && 
  ((CodeCoverConditionCoverageHelper_C185 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[185].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C185, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[584]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1170]++;
                ConstProperties cp = (ConstProperties)frame.scope;
                cp.putConst(stringReg, frame.scope, val);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1171]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[585]++;
                throw Kit.codeBug();
}
        }
        return stackTop;
    }

    private static int doSetVar(CallFrame frame, Object[] stack,
                                double[] sDbl, int stackTop,
                                Object[] vars, double[] varDbls,
                                int[] varAttributes, int indexReg) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1172]++;
int CodeCoverConditionCoverageHelper_C186;
        if ((((((CodeCoverConditionCoverageHelper_C186 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C186 |= (2)) == 0 || true) &&
 ((frame.useActivation) && 
  ((CodeCoverConditionCoverageHelper_C186 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[186].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C186, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[586]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1173]++;
int CodeCoverConditionCoverageHelper_C187;
            if ((((((CodeCoverConditionCoverageHelper_C187 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C187 |= (2)) == 0 || true) &&
 (((varAttributes[indexReg] & ScriptableObject.READONLY) == 0) && 
  ((CodeCoverConditionCoverageHelper_C187 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[187].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C187, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[588]++;
                vars[indexReg] = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1174]++;
                varDbls[indexReg] = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1175]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[589]++;}

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[587]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1176]++;
            Object val = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1177]++;
int CodeCoverConditionCoverageHelper_C188;
            if ((((((CodeCoverConditionCoverageHelper_C188 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C188 |= (2)) == 0 || true) &&
 ((val == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C188 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[188].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C188, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[590]++; val = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1178]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[591]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1179]++;
            String stringReg = frame.idata.argNames[indexReg];
            frame.scope.put(stringReg, frame.scope, val);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1180]++;
        }
        return stackTop;
    }

    private static int doGetVar(CallFrame frame, Object[] stack,
                                double[] sDbl, int stackTop,
                                Object[] vars, double[] varDbls,
                                int indexReg) {
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1181]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1182]++;
int CodeCoverConditionCoverageHelper_C189;
        if ((((((CodeCoverConditionCoverageHelper_C189 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C189 |= (2)) == 0 || true) &&
 ((frame.useActivation) && 
  ((CodeCoverConditionCoverageHelper_C189 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[189].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C189, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[592]++;
            stack[stackTop] = vars[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1183]++;
            sDbl[stackTop] = varDbls[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1184]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[593]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1185]++;
            String stringReg = frame.idata.argNames[indexReg];
            stack[stackTop] = frame.scope.get(stringReg, frame.scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1186]++;
        }
        return stackTop;
    }

    private static int doVarIncDec(Context cx, CallFrame frame,
                                   Object[] stack, double[] sDbl,
                                   int stackTop, Object[] vars,
                                   double[] varDbls, int indexReg) {
        // indexReg : varindex
        ++stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1187]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1188]++;
        int incrDecrMask = frame.idata.itsICode[frame.pc];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1189]++;
int CodeCoverConditionCoverageHelper_C190;
        if ((((((CodeCoverConditionCoverageHelper_C190 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C190 |= (2)) == 0 || true) &&
 ((frame.useActivation) && 
  ((CodeCoverConditionCoverageHelper_C190 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[190].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C190, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[594]++;
            stack[stackTop] = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1190]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1191]++;
            Object varValue = vars[indexReg];
            double d;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1192]++;
int CodeCoverConditionCoverageHelper_C191;
            if ((((((CodeCoverConditionCoverageHelper_C191 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C191 |= (2)) == 0 || true) &&
 ((varValue == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C191 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[191].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C191, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[596]++;
                d = varDbls[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1193]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[597]++;
                d = ScriptRuntime.toNumber(varValue);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1194]++;
                vars[indexReg] = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1195]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1196]++;
            double d2 = ((incrDecrMask & Node.DECR_FLAG) == 0)
                        ? d + 1.0 : d - 1.0;
            varDbls[indexReg] = d2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1197]++;
            sDbl[stackTop] = ((incrDecrMask & Node.POST_FLAG) == 0) ? d2 : d;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1198]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[595]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1199]++;
            String varName = frame.idata.argNames[indexReg];
            stack[stackTop] = ScriptRuntime.nameIncrDecr(frame.scope, varName,
                                                         cx, incrDecrMask);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1200]++;
        }
        ++frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1201]++;
        return stackTop;
    }

    private static int doRefMember(Context cx, Object[] stack, double[] sDbl,
                                   int stackTop, int flags) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1202]++;
        Object elem = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1203]++;
int CodeCoverConditionCoverageHelper_C192;
        if ((((((CodeCoverConditionCoverageHelper_C192 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C192 |= (2)) == 0 || true) &&
 ((elem == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C192 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[192].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C192, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[598]++; elem = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1204]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[599]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1205]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1206]++;
        Object obj = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1207]++;
int CodeCoverConditionCoverageHelper_C193;
        if ((((((CodeCoverConditionCoverageHelper_C193 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C193 |= (2)) == 0 || true) &&
 ((obj == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C193 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[193].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C193, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[600]++; obj = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1208]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[601]++;}
        stack[stackTop] = ScriptRuntime.memberRef(obj, elem, cx, flags);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1209]++;
        return stackTop;
    }

    private static int doRefNsMember(Context cx, Object[] stack, double[] sDbl,
                                     int stackTop, int flags) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1210]++;
        Object elem = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1211]++;
int CodeCoverConditionCoverageHelper_C194;
        if ((((((CodeCoverConditionCoverageHelper_C194 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C194 |= (2)) == 0 || true) &&
 ((elem == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C194 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[194].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C194, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[602]++; elem = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1212]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[603]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1213]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1214]++;
        Object ns = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1215]++;
int CodeCoverConditionCoverageHelper_C195;
        if ((((((CodeCoverConditionCoverageHelper_C195 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C195 |= (2)) == 0 || true) &&
 ((ns == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C195 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[195].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C195, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[604]++; ns = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1216]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[605]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1217]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1218]++;
        Object obj = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1219]++;
int CodeCoverConditionCoverageHelper_C196;
        if ((((((CodeCoverConditionCoverageHelper_C196 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C196 |= (2)) == 0 || true) &&
 ((obj == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C196 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[196].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C196, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[606]++; obj = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1220]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[607]++;}
        stack[stackTop] = ScriptRuntime.memberRef(obj, ns, elem, cx, flags);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1221]++;
        return stackTop;
    }

    private static int doRefNsName(Context cx, CallFrame frame,
                                   Object[] stack, double[] sDbl,
                                   int stackTop, int flags) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1222]++;
        Object name = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1223]++;
int CodeCoverConditionCoverageHelper_C197;
        if ((((((CodeCoverConditionCoverageHelper_C197 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C197 |= (2)) == 0 || true) &&
 ((name == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C197 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[197].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C197, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[608]++; name = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1224]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[609]++;}
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1225]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1226]++;
        Object ns = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1227]++;
int CodeCoverConditionCoverageHelper_C198;
        if ((((((CodeCoverConditionCoverageHelper_C198 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C198 |= (2)) == 0 || true) &&
 ((ns == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C198 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[198].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C198, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[610]++; ns = ScriptRuntime.wrapNumber(sDbl[stackTop]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1228]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[611]++;}
        stack[stackTop] = ScriptRuntime.nameRef(ns, name, cx, frame.scope, flags);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1229]++;
        return stackTop;
    }

    /**
     * Call __noSuchMethod__.
     */
    private static CallFrame initFrameForNoSuchMethod(Context cx,
            CallFrame frame, int indexReg, Object[] stack, double[] sDbl,
            int stackTop, int op, Scriptable funThisObj, Scriptable calleeScope,
            NoSuchMethodShim noSuchMethodShim, InterpretedFunction ifun)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1230]++;
        // create an args array from the stack
        Object[] argsArray = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1231]++;
        // exactly like getArgsArray except that the first argument
        // is the method name from the shim
        int shift = stackTop + 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1232]++;
        Object[] elements = new Object[indexReg];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1233]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[58]++;


int CodeCoverConditionCoverageHelper_C199;
        for (int i=0;(((((CodeCoverConditionCoverageHelper_C199 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C199 |= (2)) == 0 || true) &&
 ((i < indexReg) && 
  ((CodeCoverConditionCoverageHelper_C199 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[199].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C199, 1) && false); ++i, ++shift) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[58]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[59]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[60]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1234]++;
            Object val = stack[shift];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1235]++;
int CodeCoverConditionCoverageHelper_C200;
            if ((((((CodeCoverConditionCoverageHelper_C200 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C200 |= (2)) == 0 || true) &&
 ((val == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C200 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[200].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C200, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[612]++;
                val = ScriptRuntime.wrapNumber(sDbl[shift]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1236]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[613]++;}
            elements[i] = val;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1237]++;
        }
        argsArray = new Object[2];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1238]++;
        argsArray[0] = noSuchMethodShim.methodName;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1239]++;
        argsArray[1] = cx.newArray(calleeScope, elements);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1240]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1241]++;

        // exactly the same as if it's a regular InterpretedFunction
        CallFrame callParentFrame = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1242]++;
        CallFrame calleeFrame = new CallFrame();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1243]++;
int CodeCoverConditionCoverageHelper_C201;
        if ((((((CodeCoverConditionCoverageHelper_C201 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C201 |= (2)) == 0 || true) &&
 ((op == Icode_TAIL_CALL) && 
  ((CodeCoverConditionCoverageHelper_C201 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[201].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C201, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[614]++;
            callParentFrame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1244]++;
            exitFrame(cx, frame, null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1245]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[615]++;}
        // init the frame with the underlying method with the
        // adjusted args array and shim's function
        initFrame(cx, calleeScope, funThisObj, argsArray, null,
          0, 2, ifun, callParentFrame, calleeFrame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1246]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1247]++;
int CodeCoverConditionCoverageHelper_C202;
        if ((((((CodeCoverConditionCoverageHelper_C202 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C202 |= (2)) == 0 || true) &&
 ((op != Icode_TAIL_CALL) && 
  ((CodeCoverConditionCoverageHelper_C202 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[202].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C202, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[616]++;
            frame.savedStackTop = stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1248]++;
            frame.savedCallOp = op;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1249]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[617]++;}
        return calleeFrame;
    }

    private static boolean doEquals(Object[] stack, double[] sDbl,
                                    int stackTop) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1250]++;
        Object rhs = stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1251]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1252]++;
int CodeCoverConditionCoverageHelper_C203;
        if ((((((CodeCoverConditionCoverageHelper_C203 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C203 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C203 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[203].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C203, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[618]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1253]++;
int CodeCoverConditionCoverageHelper_C204;
            if ((((((CodeCoverConditionCoverageHelper_C204 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C204 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C204 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[204].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C204, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[620]++;
                return (sDbl[stackTop] == sDbl[stackTop + 1]);

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[621]++;
                return ScriptRuntime.eqNumber(sDbl[stackTop + 1], lhs);
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[619]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1254]++;
int CodeCoverConditionCoverageHelper_C205;
            if ((((((CodeCoverConditionCoverageHelper_C205 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C205 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C205 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[205].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C205, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[622]++;
                return ScriptRuntime.eqNumber(sDbl[stackTop], rhs);

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[623]++;
                return ScriptRuntime.eq(lhs, rhs);
            }
        }
    }

    private static boolean doShallowEquals(Object[] stack, double[] sDbl,
                                           int stackTop)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1255]++;
        Object rhs = stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1256]++;
        Object lhs = stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1257]++;
        final Object DBL_MRK = DOUBLE_MARK;
        double rdbl, ldbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1258]++;
int CodeCoverConditionCoverageHelper_C206;
        if ((((((CodeCoverConditionCoverageHelper_C206 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C206 |= (2)) == 0 || true) &&
 ((rhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C206 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[206].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C206, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[624]++;
            rdbl = sDbl[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1259]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1260]++;
int CodeCoverConditionCoverageHelper_C207;
            if ((((((CodeCoverConditionCoverageHelper_C207 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C207 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C207 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[207].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C207, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[626]++;
                ldbl = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1261]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[627]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1262]++;
int CodeCoverConditionCoverageHelper_C208; if ((((((CodeCoverConditionCoverageHelper_C208 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C208 |= (2)) == 0 || true) &&
 ((lhs instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C208 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[208].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C208, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[628]++;
                ldbl = ((Number)lhs).doubleValue();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1263]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[629]++;
                return false;
            }
}

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[625]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1264]++;
int CodeCoverConditionCoverageHelper_C209; if ((((((CodeCoverConditionCoverageHelper_C209 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C209 |= (2)) == 0 || true) &&
 ((lhs == DBL_MRK) && 
  ((CodeCoverConditionCoverageHelper_C209 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[209].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C209, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[630]++;
            ldbl = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1265]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1266]++;
int CodeCoverConditionCoverageHelper_C210;
            if ((((((CodeCoverConditionCoverageHelper_C210 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C210 |= (2)) == 0 || true) &&
 ((rhs instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C210 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[210].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C210, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[632]++;
                rdbl = ((Number)rhs).doubleValue();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1267]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[633]++;
                return false;
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[631]++;
            return ScriptRuntime.shallowEq(lhs, rhs);
        }
}
        return (ldbl == rdbl);
    }

    private static CallFrame processThrowable(Context cx, Object throwable,
                                              CallFrame frame, int indexReg,
                                              boolean instructionCounting)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1268]++;
int CodeCoverConditionCoverageHelper_C211;
        // Recovering from exception, indexReg contains
        // the index of handler

        if ((((((CodeCoverConditionCoverageHelper_C211 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C211 |= (2)) == 0 || true) &&
 ((indexReg >= 0) && 
  ((CodeCoverConditionCoverageHelper_C211 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[211].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C211, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[634]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1269]++;
int CodeCoverConditionCoverageHelper_C212;
            // Normal exception handler, transfer
            // control appropriately

            if ((((((CodeCoverConditionCoverageHelper_C212 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C212 |= (2)) == 0 || true) &&
 ((frame.frozen) && 
  ((CodeCoverConditionCoverageHelper_C212 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[212].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C212, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[636]++;
                // XXX Deal with exceptios!!!
                frame = frame.cloneFrozen();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1270]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[637]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1271]++;

            int[] table = frame.idata.itsExceptionTable;

            frame.pc = table[indexReg + EXCEPTION_HANDLER_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1272]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1273]++;
int CodeCoverConditionCoverageHelper_C213;
            if ((((((CodeCoverConditionCoverageHelper_C213 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C213 |= (2)) == 0 || true) &&
 ((instructionCounting) && 
  ((CodeCoverConditionCoverageHelper_C213 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[213].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C213, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[638]++;
                frame.pcPrevBranch = frame.pc;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1274]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[639]++;}

            frame.savedStackTop = frame.emptyStackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1275]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1276]++;
            int scopeLocal = frame.localShift
                             + table[indexReg
                                     + EXCEPTION_SCOPE_SLOT];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1277]++;
            int exLocal = frame.localShift
                             + table[indexReg
                                     + EXCEPTION_LOCAL_SLOT];
            frame.scope = (Scriptable)frame.stack[scopeLocal];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1278]++;
            frame.stack[exLocal] = throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1279]++;

            throwable = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1280]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[635]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1281]++;
            // Continuation restoration
            ContinuationJump cjump = (ContinuationJump)throwable;

            // Clear throwable to indicate that exceptions are OK
            throwable = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1282]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1283]++;
int CodeCoverConditionCoverageHelper_C214;

            if ((((((CodeCoverConditionCoverageHelper_C214 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C214 |= (2)) == 0 || true) &&
 ((cjump.branchFrame != frame) && 
  ((CodeCoverConditionCoverageHelper_C214 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[214].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C214, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[640]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1284]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[641]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1285]++;
int CodeCoverConditionCoverageHelper_C215;

            // Check that we have at least one frozen frame
            // in the case of detached continuation restoration:
            // unwind code ensure that
            if ((((((CodeCoverConditionCoverageHelper_C215 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C215 |= (2)) == 0 || true) &&
 ((cjump.capturedFrame == null) && 
  ((CodeCoverConditionCoverageHelper_C215 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[215].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C215, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[642]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1286]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[643]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1287]++;

            // Need to rewind branchFrame, capturedFrame
            // and all frames in between
            int rewindCount = cjump.capturedFrame.frameIndex + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1288]++;
int CodeCoverConditionCoverageHelper_C216;
            if ((((((CodeCoverConditionCoverageHelper_C216 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C216 |= (2)) == 0 || true) &&
 ((cjump.branchFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C216 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[216].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C216, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[644]++;
                rewindCount -= cjump.branchFrame.frameIndex;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1289]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[645]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1290]++;

            int enterCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1291]++;
            CallFrame[] enterFrames = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1292]++;

            CallFrame x = cjump.capturedFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1293]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[61]++;


int CodeCoverConditionCoverageHelper_C217;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C217 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C217 |= (2)) == 0 || true) &&
 ((i != rewindCount) && 
  ((CodeCoverConditionCoverageHelper_C217 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[217].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C217, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[61]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[62]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[63]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1294]++;
int CodeCoverConditionCoverageHelper_C218;
                if ((((((CodeCoverConditionCoverageHelper_C218 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C218 |= (2)) == 0 || true) &&
 ((x.frozen) && 
  ((CodeCoverConditionCoverageHelper_C218 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[218].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C218, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[646]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1295]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[647]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1296]++;
int CodeCoverConditionCoverageHelper_C219;
                if ((((((CodeCoverConditionCoverageHelper_C219 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C219 |= (2)) == 0 || true) &&
 ((isFrameEnterExitRequired(x)) && 
  ((CodeCoverConditionCoverageHelper_C219 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[219].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C219, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[648]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1297]++;
int CodeCoverConditionCoverageHelper_C220;
                    if ((((((CodeCoverConditionCoverageHelper_C220 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C220 |= (2)) == 0 || true) &&
 ((enterFrames == null) && 
  ((CodeCoverConditionCoverageHelper_C220 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[220].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C220, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[650]++;
                        // Allocate enough space to store the rest
                        // of rewind frames in case all of them
                        // would require to enter
                        enterFrames = new CallFrame[rewindCount
                                                    - i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1298]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[651]++;}
                    enterFrames[enterCount] = x;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1299]++;
                    ++enterCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1300]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[649]++;}
                x = x.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1301]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1302]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[64]++;


int CodeCoverConditionCoverageHelper_C221;

            while ((((((CodeCoverConditionCoverageHelper_C221 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C221 |= (2)) == 0 || true) &&
 ((enterCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C221 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[221].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C221, 1) && false)) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[64]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[65]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[66]++;
}
                // execute enter: walk enterFrames in the reverse
                // order since they were stored starting from
                // the capturedFrame, not branchFrame
                --enterCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1303]++;
                x = enterFrames[enterCount];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1304]++;
                enterFrame(cx, x, ScriptRuntime.emptyArgs, true);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1305]++;
            }

            // Continuation jump is almost done: capturedFrame
            // points to the call to the function that captured
            // continuation, so clone capturedFrame and
            // emulate return that function with the suplied result
            frame = cjump.capturedFrame.cloneFrozen();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1306]++;
            setCallResult(frame, cjump.result, cjump.resultDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1307]++;
            // restart the execution
        }
        frame.throwable = throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1308]++;
        return frame;
    }

    private static Object freezeGenerator(Context cx, CallFrame frame,
                                          int stackTop,
                                          GeneratorState generatorState)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1309]++;
int CodeCoverConditionCoverageHelper_C222;
          if ((((((CodeCoverConditionCoverageHelper_C222 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C222 |= (2)) == 0 || true) &&
 ((generatorState.operation == NativeGenerator.GENERATOR_CLOSE) && 
  ((CodeCoverConditionCoverageHelper_C222 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[222].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C222, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[652]++;
              // Error: no yields when generator is closing
              throw ScriptRuntime.typeError0("msg.yield.closing");

          } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[653]++;}
          // return to our caller (which should be a method of NativeGenerator)
          frame.frozen = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1310]++;
          frame.result = frame.stack[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1311]++;
          frame.resultDbl = frame.sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1312]++;
          frame.savedStackTop = stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1313]++;
          frame.pc--;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1314]++; // we want to come back here when we resume
          ScriptRuntime.exitActivationFunction(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1315]++;
          return (frame.result != DOUBLE_MARK)
              ? frame.result
              : ScriptRuntime.wrapNumber(frame.resultDbl);
    }

    private static Object thawGenerator(CallFrame frame, int stackTop,
                                        GeneratorState generatorState, int op)
    {
          // we are resuming execution
          frame.frozen = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1316]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1317]++;
          int sourceLine = getIndex(frame.idata.itsICode, frame.pc);
          frame.pc += 2;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1318]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1319]++;
int CodeCoverConditionCoverageHelper_C223; // skip line number data
          if ((((((CodeCoverConditionCoverageHelper_C223 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C223 |= (2)) == 0 || true) &&
 ((generatorState.operation == NativeGenerator.GENERATOR_THROW) && 
  ((CodeCoverConditionCoverageHelper_C223 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[223].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C223, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[654]++;
              // processing a call to <generator>.throw(exception): must
              // act as if exception was thrown from resumption point
              return new JavaScriptException(generatorState.value,
                                                  frame.idata.itsSourceFile,
                                                  sourceLine);

          } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[655]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1320]++;
int CodeCoverConditionCoverageHelper_C224;
          if ((((((CodeCoverConditionCoverageHelper_C224 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C224 |= (2)) == 0 || true) &&
 ((generatorState.operation == NativeGenerator.GENERATOR_CLOSE) && 
  ((CodeCoverConditionCoverageHelper_C224 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[224].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C224, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[656]++;
              return generatorState.value;

          } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[657]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1321]++;
int CodeCoverConditionCoverageHelper_C225;
          if ((((((CodeCoverConditionCoverageHelper_C225 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C225 |= (2)) == 0 || true) &&
 ((generatorState.operation != NativeGenerator.GENERATOR_SEND) && 
  ((CodeCoverConditionCoverageHelper_C225 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[225].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C225, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[658]++;
              throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[659]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1322]++;
int CodeCoverConditionCoverageHelper_C226;
          if ((((((CodeCoverConditionCoverageHelper_C226 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C226 |= (2)) == 0 || true) &&
 ((op == Token.YIELD) && 
  ((CodeCoverConditionCoverageHelper_C226 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[226].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C226, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[660]++;
              frame.stack[stackTop] = generatorState.value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1323]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[661]++;}
          return Scriptable.NOT_FOUND;
    }

    private static CallFrame initFrameForApplyOrCall(Context cx, CallFrame frame,
            int indexReg, Object[] stack, double[] sDbl, int stackTop, int op,
            Scriptable calleeScope, IdFunctionObject ifun,
            InterpretedFunction iApplyCallable)
    {
        Scriptable applyThis;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1324]++;
int CodeCoverConditionCoverageHelper_C227;
        if ((((((CodeCoverConditionCoverageHelper_C227 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C227 |= (2)) == 0 || true) &&
 ((indexReg != 0) && 
  ((CodeCoverConditionCoverageHelper_C227 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[227].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C227, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[662]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1325]++;
            Object obj = stack[stackTop + 2];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1326]++;
int CodeCoverConditionCoverageHelper_C228;
            if ((((((CodeCoverConditionCoverageHelper_C228 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C228 |= (2)) == 0 || true) &&
 ((obj == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C228 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[228].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C228, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[664]++;
                obj = ScriptRuntime.wrapNumber(sDbl[stackTop + 2]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1327]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[665]++;}
            applyThis = ScriptRuntime.toObjectOrNull(cx, obj);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1328]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[663]++;
            applyThis = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1329]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1330]++;
int CodeCoverConditionCoverageHelper_C229;
        if ((((((CodeCoverConditionCoverageHelper_C229 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C229 |= (2)) == 0 || true) &&
 ((applyThis == null) && 
  ((CodeCoverConditionCoverageHelper_C229 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[229].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C229, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[666]++;
            // This covers the case of args[0] == (null|undefined) as well.
            applyThis = ScriptRuntime.getTopCallScope(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1331]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[667]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1332]++;
int CodeCoverConditionCoverageHelper_C230;
        if((((((CodeCoverConditionCoverageHelper_C230 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C230 |= (2)) == 0 || true) &&
 ((op == Icode_TAIL_CALL) && 
  ((CodeCoverConditionCoverageHelper_C230 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[230].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C230, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[668]++;
            exitFrame(cx, frame, null);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1333]++;
            frame = frame.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1334]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[669]++;
            frame.savedStackTop = stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1335]++;
            frame.savedCallOp = op;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1336]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1337]++;
        CallFrame calleeFrame = new CallFrame();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1338]++;
int CodeCoverConditionCoverageHelper_C231;
        if((((((CodeCoverConditionCoverageHelper_C231 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C231 |= (2)) == 0 || true) &&
 ((BaseFunction.isApply(ifun)) && 
  ((CodeCoverConditionCoverageHelper_C231 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[231].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C231, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[670]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1339]++;
            Object[] callArgs = indexReg < 2 ? ScriptRuntime.emptyArgs :
                ScriptRuntime.getApplyArguments(cx, stack[stackTop + 3]);
            initFrame(cx, calleeScope, applyThis, callArgs, null, 0,
                    callArgs.length, iApplyCallable, frame, calleeFrame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1340]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[671]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1341]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[67]++;


int CodeCoverConditionCoverageHelper_C232;
            // Shift args left
            for(int i = 1;(((((CodeCoverConditionCoverageHelper_C232 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C232 |= (2)) == 0 || true) &&
 ((i < indexReg) && 
  ((CodeCoverConditionCoverageHelper_C232 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[232].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C232, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[67]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[68]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[69]++;
}
                stack[stackTop + 1 + i] = stack[stackTop + 2 + i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1342]++;
                sDbl[stackTop + 1 + i] = sDbl[stackTop + 2 + i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1343]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1344]++;
            int argCount = indexReg < 2 ? 0 : indexReg - 1;
            initFrame(cx, calleeScope, applyThis, stack, sDbl, stackTop + 2,
                    argCount, iApplyCallable, frame, calleeFrame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1345]++;
        }

        frame = calleeFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1346]++;
        return frame;
    }

    private static void initFrame(Context cx, Scriptable callerScope,
                                  Scriptable thisObj,
                                  Object[] args, double[] argsDbl,
                                  int argShift, int argCount,
                                  InterpretedFunction fnOrScript,
                                  CallFrame parentFrame, CallFrame frame)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1347]++;
        InterpreterData idata = fnOrScript.idata;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1348]++;

        boolean useActivation = idata.itsNeedsActivation;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1349]++;
        DebugFrame debuggerFrame = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1350]++;
int CodeCoverConditionCoverageHelper_C233;
        if ((((((CodeCoverConditionCoverageHelper_C233 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C233 |= (2)) == 0 || true) &&
 ((cx.debugger != null) && 
  ((CodeCoverConditionCoverageHelper_C233 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[233].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C233, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[672]++;
            debuggerFrame = cx.debugger.getFrame(cx, idata);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1351]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1352]++;
int CodeCoverConditionCoverageHelper_C234;
            if ((((((CodeCoverConditionCoverageHelper_C234 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C234 |= (2)) == 0 || true) &&
 ((debuggerFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C234 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[234].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C234, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[674]++;
                useActivation = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1353]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[675]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[673]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1354]++;
int CodeCoverConditionCoverageHelper_C235;

        if ((((((CodeCoverConditionCoverageHelper_C235 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C235 |= (2)) == 0 || true) &&
 ((useActivation) && 
  ((CodeCoverConditionCoverageHelper_C235 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[235].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C235, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[676]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1355]++;
int CodeCoverConditionCoverageHelper_C236;
            // Copy args to new array to pass to enterActivationFunction
            // or debuggerFrame.onEnter
            if ((((((CodeCoverConditionCoverageHelper_C236 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C236 |= (2)) == 0 || true) &&
 ((argsDbl != null) && 
  ((CodeCoverConditionCoverageHelper_C236 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[236].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C236, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[678]++;
                args = getArgsArray(args, argsDbl, argShift, argCount);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1356]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[679]++;}
            argShift = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1357]++;
            argsDbl = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1358]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[677]++;}

        Scriptable scope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1359]++;
int CodeCoverConditionCoverageHelper_C237;
        if ((((((CodeCoverConditionCoverageHelper_C237 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C237 |= (2)) == 0 || true) &&
 ((idata.itsFunctionType != 0) && 
  ((CodeCoverConditionCoverageHelper_C237 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[237].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C237, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[680]++;
            scope = fnOrScript.getParentScope();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1360]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1361]++;
int CodeCoverConditionCoverageHelper_C238;

            if ((((((CodeCoverConditionCoverageHelper_C238 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C238 |= (2)) == 0 || true) &&
 ((useActivation) && 
  ((CodeCoverConditionCoverageHelper_C238 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[238].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C238, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[682]++;
                scope = ScriptRuntime.createFunctionActivation(
                            fnOrScript, scope, args);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1362]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[683]++;}

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[681]++;
            scope = callerScope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1363]++;
            ScriptRuntime.initScript(fnOrScript, thisObj, cx, scope,
                                     fnOrScript.idata.evalScriptFlag);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1364]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1365]++;
int CodeCoverConditionCoverageHelper_C239;

        if ((((((CodeCoverConditionCoverageHelper_C239 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C239 |= (2)) == 0 || true) &&
 ((idata.itsNestedFunctions != null) && 
  ((CodeCoverConditionCoverageHelper_C239 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[239].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C239, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[684]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1366]++;
int CodeCoverConditionCoverageHelper_C240;
            if ((((((CodeCoverConditionCoverageHelper_C240 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C240 |= (8)) == 0 || true) &&
 ((idata.itsFunctionType != 0) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C240 |= (2)) == 0 || true) &&
 ((idata.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C240 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[240].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C240, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[686]++;
                Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1367]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[687]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1368]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[70]++;


int CodeCoverConditionCoverageHelper_C241;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C241 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C241 |= (2)) == 0 || true) &&
 ((i < idata.itsNestedFunctions.length) && 
  ((CodeCoverConditionCoverageHelper_C241 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[241].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C241, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[70]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[71]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[72]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1369]++;
                InterpreterData fdata = idata.itsNestedFunctions[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1370]++;
int CodeCoverConditionCoverageHelper_C242;
                if ((((((CodeCoverConditionCoverageHelper_C242 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C242 |= (2)) == 0 || true) &&
 ((fdata.itsFunctionType == FunctionNode.FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C242 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[242].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C242, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[688]++;
                    initFunction(cx, scope, fnOrScript, i);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1371]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[689]++;}
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[685]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1372]++;

        // Initialize args, vars, locals and stack

        int emptyStackTop = idata.itsMaxVars + idata.itsMaxLocals - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1373]++;
        int maxFrameArray = idata.itsMaxFrameArray;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1374]++;
int CodeCoverConditionCoverageHelper_C243;
        if ((((((CodeCoverConditionCoverageHelper_C243 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C243 |= (2)) == 0 || true) &&
 ((maxFrameArray != emptyStackTop + idata.itsMaxStack + 1) && 
  ((CodeCoverConditionCoverageHelper_C243 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[243].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C243, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[690]++;
            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1375]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[691]++;}

        Object[] stack;
        int[] stackAttributes;
        double[] sDbl;
        boolean stackReuse;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1376]++;
int CodeCoverConditionCoverageHelper_C244;
        if ((((((CodeCoverConditionCoverageHelper_C244 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C244 |= (8)) == 0 || true) &&
 ((frame.stack != null) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C244 |= (2)) == 0 || true) &&
 ((maxFrameArray <= frame.stack.length) && 
  ((CodeCoverConditionCoverageHelper_C244 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[244].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C244, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[692]++;
            // Reuse stacks from old frame
            stackReuse = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1377]++;
            stack = frame.stack;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1378]++;
            stackAttributes = frame.stackAttributes;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1379]++;
            sDbl = frame.sDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1380]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[693]++;
            stackReuse = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1381]++;
            stack = new Object[maxFrameArray];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1382]++;
            stackAttributes = new int[maxFrameArray];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1383]++;
            sDbl = new double[maxFrameArray];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1384]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1385]++;

        int varCount = idata.getParamAndVarCount();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1386]++;
byte CodeCoverTryBranchHelper_L25 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[73]++;


int CodeCoverConditionCoverageHelper_C245;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C245 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C245 |= (2)) == 0 || true) &&
 ((i < varCount) && 
  ((CodeCoverConditionCoverageHelper_C245 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[245].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C245, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L25 == 0) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[73]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[74]++;
} else if (CodeCoverTryBranchHelper_L25 == 1) {
  CodeCoverTryBranchHelper_L25++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[74]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[75]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1387]++;
int CodeCoverConditionCoverageHelper_C246;
            if ((((((CodeCoverConditionCoverageHelper_C246 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C246 |= (2)) == 0 || true) &&
 ((idata.getParamOrVarConst(i)) && 
  ((CodeCoverConditionCoverageHelper_C246 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[246].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C246, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[694]++;
                stackAttributes[i] = ScriptableObject.CONST;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1388]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[695]++;}
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1389]++;
        int definedArgs = idata.argCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1390]++;
int CodeCoverConditionCoverageHelper_C247;
        if ((((((CodeCoverConditionCoverageHelper_C247 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C247 |= (2)) == 0 || true) &&
 ((definedArgs > argCount) && 
  ((CodeCoverConditionCoverageHelper_C247 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[247].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C247, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[696]++; definedArgs = argCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1391]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[697]++;}

        // Fill the frame structure

        frame.parentFrame = parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1392]++;
        frame.frameIndex = (parentFrame == null)
                           ? 0 : parentFrame.frameIndex + 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1393]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1394]++;
int CodeCoverConditionCoverageHelper_C248;
        if((((((CodeCoverConditionCoverageHelper_C248 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C248 |= (2)) == 0 || true) &&
 ((frame.frameIndex > cx.getMaximumInterpreterStackDepth()) && 
  ((CodeCoverConditionCoverageHelper_C248 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[248].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C248, 1) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[698]++;
            throw Context.reportRuntimeError("Exceeded maximum stack depth");

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[699]++;}
        frame.frozen = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1395]++;

        frame.fnOrScript = fnOrScript;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1396]++;
        frame.idata = idata;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1397]++;

        frame.stack = stack;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1398]++;
        frame.stackAttributes = stackAttributes;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1399]++;
        frame.sDbl = sDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1400]++;
        frame.varSource = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1401]++;
        frame.localShift = idata.itsMaxVars;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1402]++;
        frame.emptyStackTop = emptyStackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1403]++;

        frame.debuggerFrame = debuggerFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1404]++;
        frame.useActivation = useActivation;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1405]++;

        frame.thisObj = thisObj;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1406]++;

        // Initialize initial values of variables that change during
        // interpretation.
        frame.result = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1407]++;
        frame.pc = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1408]++;
        frame.pcPrevBranch = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1409]++;
        frame.pcSourceLineStart = idata.firstLinePC;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1410]++;
        frame.scope = scope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1411]++;

        frame.savedStackTop = emptyStackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1412]++;
        frame.savedCallOp = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1413]++;

        System.arraycopy(args, argShift, stack, 0, definedArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1414]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1415]++;
int CodeCoverConditionCoverageHelper_C249;
        if ((((((CodeCoverConditionCoverageHelper_C249 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C249 |= (2)) == 0 || true) &&
 ((argsDbl != null) && 
  ((CodeCoverConditionCoverageHelper_C249 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[249].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C249, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[700]++;
            System.arraycopy(argsDbl, argShift, sDbl, 0, definedArgs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1416]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[701]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1417]++;
byte CodeCoverTryBranchHelper_L26 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[76]++;


int CodeCoverConditionCoverageHelper_C250;
        for (int i = definedArgs;(((((CodeCoverConditionCoverageHelper_C250 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C250 |= (2)) == 0 || true) &&
 ((i != idata.itsMaxVars) && 
  ((CodeCoverConditionCoverageHelper_C250 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[250].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C250, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L26 == 0) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[76]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[77]++;
} else if (CodeCoverTryBranchHelper_L26 == 1) {
  CodeCoverTryBranchHelper_L26++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[77]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[78]++;
}
            stack[i] = Undefined.instance;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1418]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1419]++;
int CodeCoverConditionCoverageHelper_C251;
        if ((((((CodeCoverConditionCoverageHelper_C251 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C251 |= (2)) == 0 || true) &&
 ((stackReuse) && 
  ((CodeCoverConditionCoverageHelper_C251 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[251].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C251, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[702]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1420]++;
byte CodeCoverTryBranchHelper_L27 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[79]++;


int CodeCoverConditionCoverageHelper_C252;
            // Clean the stack part and space beyond stack if any
            // of the old array to allow to GC objects there
            for (int i = emptyStackTop + 1;(((((CodeCoverConditionCoverageHelper_C252 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C252 |= (2)) == 0 || true) &&
 ((i != stack.length) && 
  ((CodeCoverConditionCoverageHelper_C252 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[252].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C252, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L27 == 0) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[79]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[80]++;
} else if (CodeCoverTryBranchHelper_L27 == 1) {
  CodeCoverTryBranchHelper_L27++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[80]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[81]++;
}
                stack[i] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1421]++;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[703]++;}

        enterFrame(cx, frame, args, false);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1422]++;
    }

    private static boolean isFrameEnterExitRequired(CallFrame frame)
    {
        return frame.debuggerFrame != null || frame.idata.itsNeedsActivation;
    }

    private static void enterFrame(Context cx, CallFrame frame, Object[] args,
                                   boolean continuationRestart)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1423]++;
        boolean usesActivation = frame.idata.itsNeedsActivation;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1424]++;
        boolean isDebugged = frame.debuggerFrame != null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1425]++;
int CodeCoverConditionCoverageHelper_C253;
        if((((((CodeCoverConditionCoverageHelper_C253 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C253 |= (8)) == 0 || true) &&
 ((usesActivation) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C253 |= (2)) == 0 || true) &&
 ((isDebugged) && 
  ((CodeCoverConditionCoverageHelper_C253 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[253].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C253, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[704]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1426]++;
            Scriptable scope = frame.scope;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1427]++;
int CodeCoverConditionCoverageHelper_C254;
            if((((((CodeCoverConditionCoverageHelper_C254 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C254 |= (2)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C254 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[254].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C254, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[706]++;
                Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1428]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[707]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1429]++;
int CodeCoverConditionCoverageHelper_C255; if ((((((CodeCoverConditionCoverageHelper_C255 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C255 |= (2)) == 0 || true) &&
 ((continuationRestart) && 
  ((CodeCoverConditionCoverageHelper_C255 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[255].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C255, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[708]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1430]++;
byte CodeCoverTryBranchHelper_L28 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[82]++;


                // Walk the parent chain of frame.scope until a NativeCall is
                // found. Normally, frame.scope is a NativeCall when called
                // from initFrame() for a debugged or activatable function.
                // However, when called from interpretLoop() as part of
                // restarting a continuation, it can also be a NativeWith if
                // the continuation was captured within a "with" or "catch"
                // block ("catch" implicitly uses NativeWith to create a scope
                // to expose the exception variable).
                for(;;) {
if (CodeCoverTryBranchHelper_L28 == 0) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[82]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[83]++;
} else if (CodeCoverTryBranchHelper_L28 == 1) {
  CodeCoverTryBranchHelper_L28++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[83]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[84]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1431]++;
int CodeCoverConditionCoverageHelper_C257;
                    if((((((CodeCoverConditionCoverageHelper_C257 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C257 |= (2)) == 0 || true) &&
 ((scope instanceof NativeWith) && 
  ((CodeCoverConditionCoverageHelper_C257 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[257].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C257, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[710]++;
                        scope = scope.getParentScope();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1432]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1433]++;
int CodeCoverConditionCoverageHelper_C258;
                        if ((((((CodeCoverConditionCoverageHelper_C258 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C258 |= (32)) == 0 || true) &&
 ((scope == null) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C258 |= (8)) == 0 || true) &&
 ((frame.parentFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C258 |= (2)) == 0 || true) &&
 ((frame.parentFrame.scope == scope) && 
  ((CodeCoverConditionCoverageHelper_C258 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[258].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C258, 3) && false))
                        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[712]++;
                            // If we get here, we didn't find a NativeCall in
                            // the call chain before reaching parent frame's
                            // scope. This should not be possible.
                            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1434]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1435]++;
                            break;
 // Never reached, but keeps the static analyzer
                            // happy about "scope" not being null 5 lines above.
                        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[713]++;}

                    }
                    else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[711]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1436]++;
                        break;
                    }
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[709]++;}
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1437]++;
int CodeCoverConditionCoverageHelper_C259;
            if ((((((CodeCoverConditionCoverageHelper_C259 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C259 |= (2)) == 0 || true) &&
 ((isDebugged) && 
  ((CodeCoverConditionCoverageHelper_C259 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[259].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C259, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[714]++;
                frame.debuggerFrame.onEnter(cx, scope, frame.thisObj, args);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1438]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[715]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1439]++;
int CodeCoverConditionCoverageHelper_C260;
            // Enter activation only when itsNeedsActivation true,
            // since debugger should not interfere with activation
            // chaining
            if ((((((CodeCoverConditionCoverageHelper_C260 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C260 |= (2)) == 0 || true) &&
 ((usesActivation) && 
  ((CodeCoverConditionCoverageHelper_C260 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[260].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C260, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[716]++;
                ScriptRuntime.enterActivationFunction(cx, scope);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1440]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[717]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[705]++;}
    }

    private static void exitFrame(Context cx, CallFrame frame,
                                  Object throwable)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1441]++;
int CodeCoverConditionCoverageHelper_C261;
        if ((((((CodeCoverConditionCoverageHelper_C261 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C261 |= (2)) == 0 || true) &&
 ((frame.idata.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C261 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[261].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C261, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[718]++;
            ScriptRuntime.exitActivationFunction(cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1442]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[719]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1443]++;
int CodeCoverConditionCoverageHelper_C262;

        if ((((((CodeCoverConditionCoverageHelper_C262 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C262 |= (2)) == 0 || true) &&
 ((frame.debuggerFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C262 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[262].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C262, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[720]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1444]++;
boolean CodeCoverTryBranchHelper_Try7 = false;
            try {
CodeCoverTryBranchHelper_Try7 = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1445]++;
int CodeCoverConditionCoverageHelper_C263;
                if ((((((CodeCoverConditionCoverageHelper_C263 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C263 |= (2)) == 0 || true) &&
 ((throwable instanceof Throwable) && 
  ((CodeCoverConditionCoverageHelper_C263 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[263].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C263, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[723]++;
                    frame.debuggerFrame.onExit(cx, true, throwable);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1446]++;

                } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[724]++;
                    Object result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1447]++;
                    ContinuationJump cjump = (ContinuationJump)throwable;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1448]++;
int CodeCoverConditionCoverageHelper_C264;
                    if ((((((CodeCoverConditionCoverageHelper_C264 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C264 |= (2)) == 0 || true) &&
 ((cjump == null) && 
  ((CodeCoverConditionCoverageHelper_C264 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[264].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C264, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[725]++;
                        result = frame.result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1449]++;

                    } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[726]++;
                        result = cjump.result;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1450]++;
                    }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1451]++;
int CodeCoverConditionCoverageHelper_C265;
                    if ((((((CodeCoverConditionCoverageHelper_C265 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C265 |= (2)) == 0 || true) &&
 ((result == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C265 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[265].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C265, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[727]++;
                        double resultDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1452]++;
int CodeCoverConditionCoverageHelper_C266;
                        if ((((((CodeCoverConditionCoverageHelper_C266 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C266 |= (2)) == 0 || true) &&
 ((cjump == null) && 
  ((CodeCoverConditionCoverageHelper_C266 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[266].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C266, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[729]++;
                            resultDbl = frame.resultDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1453]++;

                        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[730]++;
                            resultDbl = cjump.resultDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1454]++;
                        }
                        result = ScriptRuntime.wrapNumber(resultDbl);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1455]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[728]++;}
                    frame.debuggerFrame.onExit(cx, false, result);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1456]++;
                }
            } catch (Throwable ex) {
CodeCoverTryBranchHelper_Try7 = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[731]++;
                System.err.println(
"RHINO USAGE WARNING: onExit terminated with exception");
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1457]++;
                ex.printStackTrace(System.err);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1458]++;
            } finally {
    if ( CodeCoverTryBranchHelper_Try7 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[722]++;
}
  }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[721]++;}
    }

    private static void setCallResult(CallFrame frame,
                                      Object callResult,
                                      double callResultDbl)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1459]++;
int CodeCoverConditionCoverageHelper_C267;
        if ((((((CodeCoverConditionCoverageHelper_C267 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C267 |= (2)) == 0 || true) &&
 ((frame.savedCallOp == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C267 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[267].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C267, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[732]++;
            frame.stack[frame.savedStackTop] = callResult;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1460]++;
            frame.sDbl[frame.savedStackTop] = callResultDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1461]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[733]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1462]++;
int CodeCoverConditionCoverageHelper_C268; if ((((((CodeCoverConditionCoverageHelper_C268 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C268 |= (2)) == 0 || true) &&
 ((frame.savedCallOp == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C268 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[268].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C268, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[734]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1463]++;
int CodeCoverConditionCoverageHelper_C269;
            // If construct returns scriptable,
            // then it replaces on stack top saved original instance
            // of the object.
            if ((((((CodeCoverConditionCoverageHelper_C269 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C269 |= (2)) == 0 || true) &&
 ((callResult instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C269 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[269].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C269, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[736]++;
                frame.stack[frame.savedStackTop] = callResult;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1464]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[737]++;}

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[735]++;
            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1465]++;
        }
}
        frame.savedCallOp = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1466]++;
    }

    public static NativeContinuation captureContinuation(Context cx) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1467]++;
int CodeCoverConditionCoverageHelper_C270;
        if ((((((CodeCoverConditionCoverageHelper_C270 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C270 |= (8)) == 0 || true) &&
 ((cx.lastInterpreterFrame == null) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (4)) == 0 || true)))
 || !(
(((CodeCoverConditionCoverageHelper_C270 |= (2)) == 0 || true) &&
 ((cx.lastInterpreterFrame instanceof CallFrame) && 
  ((CodeCoverConditionCoverageHelper_C270 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[270].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C270, 2) && false))
        {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[738]++;
            throw new IllegalStateException("Interpreter frames not found");

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[739]++;}
        return captureContinuation(cx, (CallFrame)cx.lastInterpreterFrame, true);
    }

    private static NativeContinuation captureContinuation(Context cx, CallFrame frame,
        boolean requireContinuationsTopFrame)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1468]++;
        NativeContinuation c = new NativeContinuation();
        ScriptRuntime.setObjectProtoAndParent(
            c, ScriptRuntime.getTopCallScope(cx));
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1469]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1470]++;

        // Make sure that all frames are frozen
        CallFrame x = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1471]++;
        CallFrame outermost = frame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1472]++;
byte CodeCoverTryBranchHelper_L29 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[85]++;


int CodeCoverConditionCoverageHelper_C271;
        while ((((((CodeCoverConditionCoverageHelper_C271 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C271 |= (8)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C271 |= (2)) == 0 || true) &&
 ((x.frozen) && 
  ((CodeCoverConditionCoverageHelper_C271 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[271].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C271, 2) && false)) {
if (CodeCoverTryBranchHelper_L29 == 0) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[85]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[86]++;
} else if (CodeCoverTryBranchHelper_L29 == 1) {
  CodeCoverTryBranchHelper_L29++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[86]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[87]++;
}
            x.frozen = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1473]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1474]++;
byte CodeCoverTryBranchHelper_L30 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[88]++;


int CodeCoverConditionCoverageHelper_C272;
            // Allow to GC unused stack space
            for (int i = x.savedStackTop + 1;(((((CodeCoverConditionCoverageHelper_C272 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C272 |= (2)) == 0 || true) &&
 ((i != x.stack.length) && 
  ((CodeCoverConditionCoverageHelper_C272 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[272].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C272, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L30 == 0) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[88]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[89]++;
} else if (CodeCoverTryBranchHelper_L30 == 1) {
  CodeCoverTryBranchHelper_L30++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[89]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[90]++;
}
                // Allow to GC unused stack space
                x.stack[i] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1475]++;
                x.stackAttributes[i] = ScriptableObject.EMPTY;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1476]++;
            }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1477]++;
int CodeCoverConditionCoverageHelper_C273;
            if ((((((CodeCoverConditionCoverageHelper_C273 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C273 |= (2)) == 0 || true) &&
 ((x.savedCallOp == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C273 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[273].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C273, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[740]++;
                // the call will always overwrite the stack top with the result
                x.stack[x.savedStackTop] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1478]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[741]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1479]++;
int CodeCoverConditionCoverageHelper_C274;
                if ((((((CodeCoverConditionCoverageHelper_C274 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C274 |= (2)) == 0 || true) &&
 ((x.savedCallOp != Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C274 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[274].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C274, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[742]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1480]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[743]++;}
                // the new operator uses stack top to store the constructed
                // object so it shall not be cleared: see comments in
                // setCallResult
            }
            outermost = x;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1481]++;
            x = x.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1482]++;
        }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1483]++;
int CodeCoverConditionCoverageHelper_C275;

        if ((((((CodeCoverConditionCoverageHelper_C275 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C275 |= (2)) == 0 || true) &&
 ((requireContinuationsTopFrame) && 
  ((CodeCoverConditionCoverageHelper_C275 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[275].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C275, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[744]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1484]++;
byte CodeCoverTryBranchHelper_L31 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[91]++;


int CodeCoverConditionCoverageHelper_C276;
            while ((((((CodeCoverConditionCoverageHelper_C276 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C276 |= (2)) == 0 || true) &&
 ((outermost.parentFrame != null) && 
  ((CodeCoverConditionCoverageHelper_C276 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[276].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C276, 1) && false)) { 
if (CodeCoverTryBranchHelper_L31 == 0) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[91]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[92]++;
} else if (CodeCoverTryBranchHelper_L31 == 1) {
  CodeCoverTryBranchHelper_L31++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[92]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[93]++;
}
                outermost = outermost.parentFrame;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1485]++;
  }
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1486]++;
int CodeCoverConditionCoverageHelper_C277;

            if ((((((CodeCoverConditionCoverageHelper_C277 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C277 |= (2)) == 0 || true) &&
 ((outermost.isContinuationsTopFrame) && 
  ((CodeCoverConditionCoverageHelper_C277 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[277].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C277, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[746]++;
                throw new IllegalStateException("Cannot capture continuation " +
                        "from JavaScript code not called directly by " +
                        "executeScriptWithContinuations or " +
                        "callFunctionWithContinuations");

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[747]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[745]++;}

        c.initImplementation(frame);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1487]++;
        return c;
    }

    private static int stack_int32(CallFrame frame, int i)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1488]++;
        Object x = frame.stack[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1489]++;
int CodeCoverConditionCoverageHelper_C278;
        if ((((((CodeCoverConditionCoverageHelper_C278 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C278 |= (2)) == 0 || true) &&
 ((x == UniqueTag.DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C278 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[278].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C278, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[748]++;
            return ScriptRuntime.toInt32(frame.sDbl[i]);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[749]++;
            return ScriptRuntime.toInt32(x);
        }
    }

    private static double stack_double(CallFrame frame, int i)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1490]++;
        Object x = frame.stack[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1491]++;
int CodeCoverConditionCoverageHelper_C279;
        if ((((((CodeCoverConditionCoverageHelper_C279 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C279 |= (2)) == 0 || true) &&
 ((x != UniqueTag.DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C279 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[279].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C279, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[750]++;
            return ScriptRuntime.toNumber(x);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[751]++;
            return frame.sDbl[i];
        }
    }

    private static boolean stack_boolean(CallFrame frame, int i)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1492]++;
        Object x = frame.stack[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1493]++;
int CodeCoverConditionCoverageHelper_C280;
        if ((((((CodeCoverConditionCoverageHelper_C280 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C280 |= (2)) == 0 || true) &&
 ((x == Boolean.TRUE) && 
  ((CodeCoverConditionCoverageHelper_C280 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[280].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C280, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[752]++;
            return true;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[753]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1494]++;
int CodeCoverConditionCoverageHelper_C281; if ((((((CodeCoverConditionCoverageHelper_C281 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C281 |= (2)) == 0 || true) &&
 ((x == Boolean.FALSE) && 
  ((CodeCoverConditionCoverageHelper_C281 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[281].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C281, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[754]++;
            return false;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[755]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1495]++;
int CodeCoverConditionCoverageHelper_C282; if ((((((CodeCoverConditionCoverageHelper_C282 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C282 |= (2)) == 0 || true) &&
 ((x == UniqueTag.DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C282 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[282].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C282, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[756]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1496]++;
            double d = frame.sDbl[i];
            return d == d && d != 0.0;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[757]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1497]++;
int CodeCoverConditionCoverageHelper_C283; if ((((((CodeCoverConditionCoverageHelper_C283 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C283 |= (8)) == 0 || true) &&
 ((x == null) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C283 |= (2)) == 0 || true) &&
 ((x == Undefined.instance) && 
  ((CodeCoverConditionCoverageHelper_C283 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[283].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C283, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[758]++;
            return false;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[759]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1498]++;
int CodeCoverConditionCoverageHelper_C284; if ((((((CodeCoverConditionCoverageHelper_C284 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C284 |= (2)) == 0 || true) &&
 ((x instanceof Number) && 
  ((CodeCoverConditionCoverageHelper_C284 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[284].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C284, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[760]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1499]++;
            double d = ((Number)x).doubleValue();
            return (d == d && d != 0.0);

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[761]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1500]++;
int CodeCoverConditionCoverageHelper_C285; if ((((((CodeCoverConditionCoverageHelper_C285 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C285 |= (2)) == 0 || true) &&
 ((x instanceof Boolean) && 
  ((CodeCoverConditionCoverageHelper_C285 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[285].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C285, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[762]++;
            return ((Boolean)x).booleanValue();

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[763]++;
            return ScriptRuntime.toBoolean(x);
        }
}
}
}
}
}
    }

    private static void doAdd(Object[] stack, double[] sDbl, int stackTop,
                              Context cx)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1501]++;
        Object rhs = stack[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1502]++;
        Object lhs = stack[stackTop];
        double d;
        boolean leftRightOrder;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1503]++;
int CodeCoverConditionCoverageHelper_C286;
        if ((((((CodeCoverConditionCoverageHelper_C286 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C286 |= (2)) == 0 || true) &&
 ((rhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C286 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[286].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C286, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[764]++;
            d = sDbl[stackTop + 1];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1504]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1505]++;
int CodeCoverConditionCoverageHelper_C287;
            if ((((((CodeCoverConditionCoverageHelper_C287 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C287 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C287 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[287].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C287, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[766]++;
                sDbl[stackTop] += d;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1506]++;
                return;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[767]++;}
            leftRightOrder = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1507]++;

            // fallthrough to object + number code
        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[765]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1508]++;
int CodeCoverConditionCoverageHelper_C288; if ((((((CodeCoverConditionCoverageHelper_C288 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C288 |= (2)) == 0 || true) &&
 ((lhs == DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C288 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[288].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C288, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[768]++;
            d = sDbl[stackTop];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1509]++;
            lhs = rhs;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1510]++;
            leftRightOrder = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1511]++;

            // fallthrough to object + number code
        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[769]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1512]++;
int CodeCoverConditionCoverageHelper_C289;
            if ((((((CodeCoverConditionCoverageHelper_C289 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C289 |= (8)) == 0 || true) &&
 ((lhs instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C289 |= (2)) == 0 || true) &&
 ((rhs instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C289 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[289].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C289, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[770]++;
                stack[stackTop] = ScriptRuntime.add(lhs, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1513]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[771]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1514]++;
int CodeCoverConditionCoverageHelper_C290; if ((((((CodeCoverConditionCoverageHelper_C290 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C290 |= (8)) == 0 || true) &&
 ((lhs instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C290 |= (2)) == 0 || true) &&
 ((rhs instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C290 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[290].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C290, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[772]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1515]++;
                CharSequence lstr = ScriptRuntime.toCharSequence(lhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1516]++;
                CharSequence rstr = ScriptRuntime.toCharSequence(rhs);
                stack[stackTop] = new ConsString(lstr, rstr);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1517]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[773]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1518]++;
                double lDbl = (lhs instanceof Number)
                    ? ((Number)lhs).doubleValue() : ScriptRuntime.toNumber(lhs);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1519]++;
                double rDbl = (rhs instanceof Number)
                    ? ((Number)rhs).doubleValue() : ScriptRuntime.toNumber(rhs);
                stack[stackTop] = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1520]++;
                sDbl[stackTop] = lDbl + rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1521]++;
            }
}
            return;
        }
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1522]++;
int CodeCoverConditionCoverageHelper_C291;

        // handle object(lhs) + number(d) code
        if ((((((CodeCoverConditionCoverageHelper_C291 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C291 |= (2)) == 0 || true) &&
 ((lhs instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C291 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[291].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C291, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[774]++;
            rhs = ScriptRuntime.wrapNumber(d);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1523]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1524]++;
int CodeCoverConditionCoverageHelper_C292;
            if ((((((CodeCoverConditionCoverageHelper_C292 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C292 |= (2)) == 0 || true) &&
 ((leftRightOrder) && 
  ((CodeCoverConditionCoverageHelper_C292 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[292].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C292, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[776]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1525]++;
                Object tmp = lhs;
                lhs = rhs;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1526]++;
                rhs = tmp;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1527]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[777]++;}
            stack[stackTop] = ScriptRuntime.add(lhs, rhs, cx);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1528]++;

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[775]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1529]++;
int CodeCoverConditionCoverageHelper_C293; if ((((((CodeCoverConditionCoverageHelper_C293 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C293 |= (2)) == 0 || true) &&
 ((lhs instanceof CharSequence) && 
  ((CodeCoverConditionCoverageHelper_C293 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[293].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C293, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[778]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1530]++;
            CharSequence lstr = (CharSequence)lhs;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1531]++;
            CharSequence rstr = ScriptRuntime.toCharSequence(d);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1532]++;
int CodeCoverConditionCoverageHelper_C294;
            if ((((((CodeCoverConditionCoverageHelper_C294 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C294 |= (2)) == 0 || true) &&
 ((leftRightOrder) && 
  ((CodeCoverConditionCoverageHelper_C294 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[294].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C294, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[780]++;
                stack[stackTop] = new ConsString(lstr, rstr);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1533]++;

            } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[781]++;
                stack[stackTop] = new ConsString(rstr, lstr);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1534]++;
            }

        } else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[779]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1535]++;
            double lDbl = (lhs instanceof Number)
                ? ((Number)lhs).doubleValue() : ScriptRuntime.toNumber(lhs);
            stack[stackTop] = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1536]++;
            sDbl[stackTop] = lDbl + d;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1537]++;
        }
}
    }

    private static int doArithmetic(CallFrame frame, int op, Object[] stack,
                                    double[] sDbl, int stackTop) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1538]++;
        double rDbl = stack_double(frame, stackTop);
        --stackTop;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1539]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1540]++;
        double lDbl = stack_double(frame, stackTop);
        stack[stackTop] = DOUBLE_MARK;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1541]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1542]++;
        switch (op) {
          case Token.SUB:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[782]++;
            lDbl -= rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1543]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1544]++;
            break;
          case Token.MUL:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[783]++;
            lDbl *= rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1545]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1546]++;
            break;
          case Token.DIV:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[784]++;
            lDbl /= rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1547]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1548]++;
            break;
          case Token.MOD:
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[785]++;
            lDbl %= rDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1549]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1550]++;
            break; default : CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[786]++;
        }
        sDbl[stackTop] = lDbl;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1551]++;
        return stackTop;
    }

    private static Object[] getArgsArray(Object[] stack, double[] sDbl,
                                         int shift, int count)
    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1552]++;
int CodeCoverConditionCoverageHelper_C295;
        if ((((((CodeCoverConditionCoverageHelper_C295 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C295 |= (2)) == 0 || true) &&
 ((count == 0) && 
  ((CodeCoverConditionCoverageHelper_C295 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[295].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C295, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[787]++;
            return ScriptRuntime.emptyArgs;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[788]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1553]++;
        Object[] args = new Object[count];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1554]++;
byte CodeCoverTryBranchHelper_L32 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[94]++;


int CodeCoverConditionCoverageHelper_C296;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C296 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C296 |= (2)) == 0 || true) &&
 ((i != count) && 
  ((CodeCoverConditionCoverageHelper_C296 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[296].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C296, 1) && false); ++i, ++shift) {
if (CodeCoverTryBranchHelper_L32 == 0) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[94]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[95]++;
} else if (CodeCoverTryBranchHelper_L32 == 1) {
  CodeCoverTryBranchHelper_L32++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[95]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.loops[96]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1555]++;
            Object val = stack[shift];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1556]++;
int CodeCoverConditionCoverageHelper_C297;
            if ((((((CodeCoverConditionCoverageHelper_C297 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C297 |= (2)) == 0 || true) &&
 ((val == UniqueTag.DOUBLE_MARK) && 
  ((CodeCoverConditionCoverageHelper_C297 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[297].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C297, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[789]++;
                val = ScriptRuntime.wrapNumber(sDbl[shift]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1557]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[790]++;}
            args[i] = val;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1558]++;
        }
        return args;
    }

    private static void addInstructionCount(Context cx, CallFrame frame,
                                            int extra)
    {
        cx.instructionCount += frame.pc - frame.pcPrevBranch + extra;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1559]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1560]++;
int CodeCoverConditionCoverageHelper_C298;
        if ((((((CodeCoverConditionCoverageHelper_C298 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C298 |= (2)) == 0 || true) &&
 ((cx.instructionCount > cx.instructionThreshold) && 
  ((CodeCoverConditionCoverageHelper_C298 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.conditionCounters[298].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C298, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[791]++;
            cx.observeInstructionCount(cx.instructionCount);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1561]++;
            cx.instructionCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.statements[1562]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt.branches[792]++;}
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt ());
  }
    public static long[] statements = new long[1563];
    public static long[] branches = new long[793];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[299];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-Interpreter.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,2,2,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,2,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,2,0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1,1,1,1,1,1,1,1,2,0,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,1,1,1,1,1,1,2,1,1,0,1,3,1,1,1,1,1,1,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,2,2,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 298; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[97];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9icuws4l57obhn8mzelig265hlt () {
    super("org.mozilla.javascript.RHINO-SRC-Interpreter.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 1562; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 792; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 298; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 96; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-Interpreter.java");
      for (int i = 1; i <= 1562; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 792; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 298; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 32; i++) {
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

