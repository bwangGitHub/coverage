/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.ScriptNode;
import org.mozilla.javascript.ast.Jump;
import org.mozilla.javascript.ast.FunctionNode;

/**
 * Generates bytecode for the Interpreter.
 */
class CodeGenerator extends Icode {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.ping();
  }


    private static final int MIN_LABEL_TABLE_SIZE = 32;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[1]++;
  }
    private static final int MIN_FIXUP_TABLE_SIZE = 40;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[2]++;
  }

    private CompilerEnvirons compilerEnv;

    private boolean itsInFunctionFlag;
    private boolean itsInTryFlag;

    private InterpreterData itsData;

    private ScriptNode scriptOrFn;
    private int iCodeTop;
    private int stackDepth;
    private int lineNumber;
    private int doubleTableTop;

    private ObjToIntMap strings = new ObjToIntMap(20);
  {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[3]++;
  }
    private int localTop;
    private int[] labelTable;
    private int labelTableTop;

    // fixupTable[i] = (label_index << 32) | fixup_site
    private long[] fixupTable;
    private int fixupTableTop;
    private ObjArray literalIds = new ObjArray();
  {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[4]++;
  }

    private int exceptionTableTop;

    // ECF_ or Expression Context Flags constants: for now only TAIL
    private static final int ECF_TAIL = 1 << 0;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[5]++;
  }

    public InterpreterData compile(CompilerEnvirons compilerEnv,
                                   ScriptNode tree,
                                   String encodedSource,
                                   boolean returnFunction)
    {
        this.compilerEnv = compilerEnv;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[6]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[1]++;
            System.out.println("before transform:");
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[8]++;
            System.out.println(tree.toStringTree(tree));
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[9]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[2]++;}

        new NodeTransformer().transform(tree);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[10]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((Token.printTrees) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[3]++;
            System.out.println("after transform:");
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[12]++;
            System.out.println(tree.toStringTree(tree));
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[13]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[4]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[5]++;
            scriptOrFn = tree.getFunctionNode(0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[15]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[6]++;
            scriptOrFn = tree;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[16]++;
        }
        itsData = new InterpreterData(compilerEnv.getLanguageVersion(),
                                      scriptOrFn.getSourceName(),
                                      encodedSource,
                                      ((AstRoot)tree).isInStrictMode());
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[17]++;
        itsData.topLevel = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[18]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[19]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((returnFunction) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[7]++;
            generateFunctionICode();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[20]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[8]++;
            generateICodeFromTree(scriptOrFn);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[21]++;
        }
        return itsData;
    }

    private void generateFunctionICode()
    {
        itsInFunctionFlag = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[22]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[23]++;

        FunctionNode theFunction = (FunctionNode)scriptOrFn;

        itsData.itsFunctionType = theFunction.getFunctionType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[24]++;
        itsData.itsNeedsActivation = theFunction.requiresActivation();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[25]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[26]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((theFunction.getFunctionName() != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[9]++;
            itsData.itsName = theFunction.getName();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[27]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[10]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[28]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((theFunction.isGenerator()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[11]++;
          addIcode(Icode_GENERATOR);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[29]++;
          addUint16(theFunction.getBaseLineno() & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[30]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[12]++;}

        generateICodeFromTree(theFunction.getLastChild());
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[31]++;
    }

    private void generateICodeFromTree(Node tree)
    {
        generateNestedFunctions();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[32]++;

        generateRegExpLiterals();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[33]++;

        visitStatement(tree, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[34]++;
        fixLabelGotos();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[35]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[36]++;
int CodeCoverConditionCoverageHelper_C7;
        // add RETURN_RESULT only to scripts as function always ends with RETURN
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((itsData.itsFunctionType == 0) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[13]++;
            addToken(Token.RETURN_RESULT);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[37]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[14]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[38]++;
int CodeCoverConditionCoverageHelper_C8;

        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((itsData.itsICode.length != iCodeTop) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[15]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[39]++;
            // Make itsData.itsICode length exactly iCodeTop to save memory
            // and catch bugs with jumps beyond icode as early as possible
            byte[] tmp = new byte[iCodeTop];
            System.arraycopy(itsData.itsICode, 0, tmp, 0, iCodeTop);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[40]++;
            itsData.itsICode = tmp;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[41]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[16]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[42]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((strings.size() == 0) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[17]++;
            itsData.itsStringTable = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[43]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[18]++;
            itsData.itsStringTable = new String[strings.size()];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[44]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[45]++;
            ObjToIntMap.Iterator iter = strings.newIterator();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[46]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
            for (iter.start();(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((iter.done()) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); iter.next()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[1]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[2]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[3]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[47]++;
                String str = (String)iter.getKey();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[48]++;
                int index = iter.getValue();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[49]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((itsData.itsStringTable[index] != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[19]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[50]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[20]++;}
                itsData.itsStringTable[index] = str;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[51]++;
            }
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[52]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((doubleTableTop == 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[21]++;
            itsData.itsDoubleTable = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[53]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[22]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[54]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((itsData.itsDoubleTable.length != doubleTableTop) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[23]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[55]++;
            double[] tmp = new double[doubleTableTop];
            System.arraycopy(itsData.itsDoubleTable, 0, tmp, 0,
                             doubleTableTop);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[56]++;
            itsData.itsDoubleTable = tmp;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[57]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[24]++;}
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[58]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((exceptionTableTop != 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((itsData.itsExceptionTable.length != exceptionTableTop) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false))
        {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[25]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[59]++;
            int[] tmp = new int[exceptionTableTop];
            System.arraycopy(itsData.itsExceptionTable, 0, tmp, 0,
                             exceptionTableTop);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[60]++;
            itsData.itsExceptionTable = tmp;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[61]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[26]++;}

        itsData.itsMaxVars = scriptOrFn.getParamAndVarCount();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[62]++;
        // itsMaxFrameArray: interpret method needs this amount for its
        // stack and sDbl arrays
        itsData.itsMaxFrameArray = itsData.itsMaxVars
                                   + itsData.itsMaxLocals
                                   + itsData.itsMaxStack;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[63]++;

        itsData.argNames = scriptOrFn.getParamAndVarNames();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[64]++;
        itsData.argIsConst = scriptOrFn.getParamAndVarConst();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[65]++;
        itsData.argCount = scriptOrFn.getParamCount();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[66]++;

        itsData.encodedSourceStart = scriptOrFn.getEncodedSourceStart();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[67]++;
        itsData.encodedSourceEnd = scriptOrFn.getEncodedSourceEnd();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[68]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[69]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((literalIds.size() != 0) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[27]++;
            itsData.literalIds = literalIds.toArray();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[70]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[28]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[71]++;
int CodeCoverConditionCoverageHelper_C16;

        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((Token.printICode) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[29]++; Interpreter.dumpICode(itsData);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[72]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[30]++;}
    }

    private void generateNestedFunctions()
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[73]++;
        int functionCount = scriptOrFn.getFunctionCount();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[74]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((functionCount == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[31]++; return;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[32]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[75]++;

        InterpreterData[] array = new InterpreterData[functionCount];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[76]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[4]++;


int CodeCoverConditionCoverageHelper_C18;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i != functionCount) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[4]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[5]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[6]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[77]++;
            FunctionNode fn = scriptOrFn.getFunctionNode(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[78]++;
            CodeGenerator gen = new CodeGenerator();
            gen.compilerEnv = compilerEnv;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[79]++;
            gen.scriptOrFn = fn;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[80]++;
            gen.itsData = new InterpreterData(itsData);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[81]++;
            gen.generateFunctionICode();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[82]++;
            array[i] = gen.itsData;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[83]++;
        }
        itsData.itsNestedFunctions = array;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[84]++;
    }

    private void generateRegExpLiterals()
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[85]++;
        int N = scriptOrFn.getRegexpCount();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[86]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[33]++; return;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[34]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[87]++;

        Context cx = Context.getContext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[88]++;
        RegExpProxy rep = ScriptRuntime.checkRegExpProxy(cx);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[89]++;
        Object[] array = new Object[N];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[90]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[7]++;


int CodeCoverConditionCoverageHelper_C20;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[7]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[8]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[9]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[91]++;
            String string = scriptOrFn.getRegexpString(i);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[92]++;
            String flags = scriptOrFn.getRegexpFlags(i);
            array[i] = rep.compileRegExp(cx, string, flags);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[93]++;
        }
        itsData.itsRegExpLiterals = array;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[94]++;
    }

    private void updateLineNumber(Node node)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[95]++;
        int lineno = node.getLineno();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[96]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((lineno != lineNumber) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((lineno >= 0) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[35]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[97]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((itsData.firstLinePC < 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[37]++;
                itsData.firstLinePC = lineno;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[98]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[38]++;}
            lineNumber = lineno;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[99]++;
            addIcode(Icode_LINE);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[100]++;
            addUint16(lineno & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[101]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[36]++;}
    }

    private RuntimeException badTree(Node node)
    {
        throw new RuntimeException(node.toString());
    }

    private void visitStatement(Node node, int initialStackDepth)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[102]++;
        int type = node.getType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[103]++;
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[104]++;
        switch (type) {

          case Token.FUNCTION:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[39]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[105]++;
                int fnIndex = node.getExistingIntProp(Node.FUNCTION_PROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[106]++;
                int fnType = scriptOrFn.getFunctionNode(fnIndex).
                                 getFunctionType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[107]++;
int CodeCoverConditionCoverageHelper_C23;
                // Only function expressions or function expression
                // statements need closure code creating new function
                // object on stack as function statements are initialized
                // at script/function start.
                // In addition, function expressions can not be present here
                // at statement level, they must only be present as expressions.
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((fnType == FunctionNode.FUNCTION_EXPRESSION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[40]++;
                    addIndexOp(Icode_CLOSURE_STMT, fnIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[108]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[41]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[109]++;
int CodeCoverConditionCoverageHelper_C24;
                    if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((fnType != FunctionNode.FUNCTION_STATEMENT) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[42]++;
                        throw Kit.codeBug();

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[43]++;}
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[110]++;
int CodeCoverConditionCoverageHelper_C25;
                // For function statements or function expression statements
                // in scripts, we need to ensure that the result of the script
                // is the function if it is the last statement in the script.
                // For example, eval("function () {}") should return a
                // function, not undefined.
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((itsInFunctionFlag) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[44]++;
                    addIndexOp(Icode_CLOSURE_EXPR, fnIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[111]++;
                    stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[112]++;
                    addIcode(Icode_POP_RESULT);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[113]++;
                    stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[114]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[45]++;}
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[115]++;
            break;

          case Token.LABEL:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[46]++;
          case Token.LOOP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[47]++;
          case Token.BLOCK:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[48]++;
          case Token.EMPTY:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[49]++;
          case Token.WITH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[50]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[116]++;
          case Token.SCRIPT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[51]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[117]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[10]++;


int CodeCoverConditionCoverageHelper_C26;
            // fall through
            while ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[10]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[11]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[12]++;
}
                visitStatement(child, initialStackDepth);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[118]++;
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[119]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[120]++;
            break;

          case Token.ENTERWITH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[52]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[121]++;
            addToken(Token.ENTERWITH);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[122]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[123]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[124]++;
            break;

          case Token.LEAVEWITH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[53]++;
            addToken(Token.LEAVEWITH);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[125]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[126]++;
            break;

          case Token.LOCAL_BLOCK:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[54]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[127]++;
                int local = allocLocal();
                node.putIntProp(Node.LOCAL_PROP, local);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[128]++;
                updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[129]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[130]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[13]++;


int CodeCoverConditionCoverageHelper_C27;
                while ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[13]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[14]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[15]++;
}
                    visitStatement(child, initialStackDepth);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[131]++;
                    child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[132]++;
                }
                addIndexOp(Icode_LOCAL_CLEAR, local);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[133]++;
                releaseLocal(local);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[134]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[135]++;
            break;

          case Token.DEBUGGER:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[55]++;
            addIcode(Icode_DEBUGGER);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[136]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[137]++;
            break;

          case Token.SWITCH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[56]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[138]++;
            // See comments in IRFactory.createSwitch() for description
            // of SWITCH node
            {
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[139]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[140]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[16]++;


int CodeCoverConditionCoverageHelper_C28;
                for (Jump caseNode = (Jump)child.getNext();(((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((caseNode != null) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false);
                     caseNode = (Jump)caseNode.getNext())
                {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[16]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[17]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[18]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[141]++;
int CodeCoverConditionCoverageHelper_C29;
                    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((caseNode.getType() != Token.CASE) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[57]++;
                        throw badTree(caseNode);
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[58]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[142]++;
                    Node test = caseNode.getFirstChild();
                    addIcode(Icode_DUP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[143]++;
                    stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[144]++;
                    visitExpression(test, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[145]++;
                    addToken(Token.SHEQ);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[146]++;
                    stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[147]++;
                    // If true, Icode_IFEQ_POP will jump and remove case
                    // value from stack
                    addGoto(caseNode.target, Icode_IFEQ_POP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[148]++;
                    stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[149]++;
                }
                addIcode(Icode_POP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[150]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[151]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[152]++;
            break;

          case Token.TARGET:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[59]++;
            markTargetLabel(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[153]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[154]++;
            break;

          case Token.IFEQ :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[60]++;
          case Token.IFNE :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[61]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[155]++;
                Node target = ((Jump)node).target;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[156]++;
                addGoto(target, type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[157]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[158]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[159]++;
            break;

          case Token.GOTO:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[62]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[160]++;
                Node target = ((Jump)node).target;
                addGoto(target, type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[161]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[162]++;
            break;

          case Token.JSR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[63]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[163]++;
                Node target = ((Jump)node).target;
                addGoto(target, Icode_GOSUB);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[164]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[165]++;
            break;

          case Token.FINALLY:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[64]++;
            {
                // Account for incomming GOTOSUB address
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[166]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[167]++;
                int finallyRegister = getLocalBlockRef(node);
                addIndexOp(Icode_STARTSUB, finallyRegister);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[168]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[169]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[170]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[19]++;


int CodeCoverConditionCoverageHelper_C30;
                while ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[19]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[20]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[21]++;
}
                    visitStatement(child, initialStackDepth);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[171]++;
                    child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[172]++;
                }
                addIndexOp(Icode_RETSUB, finallyRegister);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[173]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[174]++;
            break;

          case Token.EXPR_VOID:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[65]++;
          case Token.EXPR_RESULT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[66]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[175]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[176]++;
            addIcode((type == Token.EXPR_VOID) ? Icode_POP : Icode_POP_RESULT);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[177]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[178]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[179]++;
            break;

          case Token.TRY:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[67]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[180]++;
                Jump tryNode = (Jump)node;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[181]++;
                int exceptionObjectLocal = getLocalBlockRef(tryNode);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[182]++;
                int scopeLocal = allocLocal();

                addIndexOp(Icode_SCOPE_SAVE, scopeLocal);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[183]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[184]++;

                int tryStart = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[185]++;
                boolean savedFlag = itsInTryFlag;
                itsInTryFlag = true;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[186]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[187]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[22]++;


int CodeCoverConditionCoverageHelper_C31;
                while ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[22]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[23]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[24]++;
}
                    visitStatement(child, initialStackDepth);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[188]++;
                    child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[189]++;
                }
                itsInTryFlag = savedFlag;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[190]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[191]++;

                Node catchTarget = tryNode.target;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[192]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((catchTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[68]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[193]++;
                    int catchStartPC
                        = labelTable[getTargetLabel(catchTarget)];
                    addExceptionHandler(
                        tryStart, catchStartPC, catchStartPC,
                        false, exceptionObjectLocal, scopeLocal);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[194]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[69]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[195]++;
                Node finallyTarget = tryNode.getFinally();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[196]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((finallyTarget != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[70]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[197]++;
                    int finallyStartPC
                        = labelTable[getTargetLabel(finallyTarget)];
                    addExceptionHandler(
                        tryStart, finallyStartPC, finallyStartPC,
                        true, exceptionObjectLocal, scopeLocal);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[198]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[71]++;}

                addIndexOp(Icode_LOCAL_CLEAR, scopeLocal);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[199]++;
                releaseLocal(scopeLocal);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[200]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[201]++;
            break;

          case Token.CATCH_SCOPE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[72]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[202]++;
                int localIndex = getLocalBlockRef(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[203]++;
                int scopeIndex = node.getExistingIntProp(Node.CATCH_SCOPE_PROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[204]++;
                String name = child.getString();
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[205]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[206]++; // load expression object
                addStringPrefix(name);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[207]++;
                addIndexPrefix(localIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[208]++;
                addToken(Token.CATCH_SCOPE);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[209]++;
                addUint8(scopeIndex != 0 ? 1 : 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[210]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[211]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[212]++;
            break;

          case Token.THROW:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[73]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[213]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[214]++;
            addToken(Token.THROW);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[215]++;
            addUint16(lineNumber & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[216]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[217]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[218]++;
            break;

          case Token.RETHROW:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[74]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[219]++;
            addIndexOp(Token.RETHROW, getLocalBlockRef(node));
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[220]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[221]++;
            break;

          case Token.RETURN:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[75]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[222]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[223]++;
int CodeCoverConditionCoverageHelper_C34;
            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((node.getIntProp(Node.GENERATOR_END_PROP, 0) != 0) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[76]++;
                // We're in a generator, so change RETURN to GENERATOR_END
                addIcode(Icode_GENERATOR_END);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[224]++;
                addUint16(lineNumber & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[225]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[77]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[226]++;
int CodeCoverConditionCoverageHelper_C35; if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[78]++;
                visitExpression(child, ECF_TAIL);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[227]++;
                addToken(Token.RETURN);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[228]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[229]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[79]++;
                addIcode(Icode_RETUNDEF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[230]++;
            }
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[231]++;
            break;

          case Token.RETURN_RESULT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[80]++;
            updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[232]++;
            addToken(Token.RETURN_RESULT);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[233]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[234]++;
            break;

          case Token.ENUM_INIT_KEYS:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[81]++;
          case Token.ENUM_INIT_VALUES:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[82]++;
          case Token.ENUM_INIT_ARRAY:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[83]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[235]++;
            addIndexOp(type, getLocalBlockRef(node));
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[236]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[237]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[238]++;
            break;

          case Icode_GENERATOR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[84]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[239]++;
            break;

          default:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[85]++;
            throw badTree(node);
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[240]++;
int CodeCoverConditionCoverageHelper_C36;

        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((stackDepth != initialStackDepth) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[86]++;
            throw Kit.codeBug();

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[87]++;}
    }

    private void visitExpression(Node node, int contextFlags)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[241]++;
        int type = node.getType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[242]++;
        Node child = node.getFirstChild();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[243]++;
        int savedStackDepth = stackDepth;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[244]++;
        switch (type) {

          case Token.FUNCTION:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[88]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[245]++;
                int fnIndex = node.getExistingIntProp(Node.FUNCTION_PROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[246]++;
                FunctionNode fn = scriptOrFn.getFunctionNode(fnIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[247]++;
int CodeCoverConditionCoverageHelper_C37;
                // See comments in visitStatement for Token.FUNCTION case
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((fn.getFunctionType() != FunctionNode.FUNCTION_EXPRESSION) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[89]++;
                    throw Kit.codeBug();

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[90]++;}
                addIndexOp(Icode_CLOSURE_EXPR, fnIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[248]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[249]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[250]++;
            break;

          case Token.LOCAL_LOAD:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[91]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[251]++;
                int localIndex = getLocalBlockRef(node);
                addIndexOp(Token.LOCAL_LOAD, localIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[252]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[253]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[254]++;
            break;

          case Token.COMMA:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[92]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[255]++;
                Node lastChild = node.getLastChild();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[256]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[25]++;


int CodeCoverConditionCoverageHelper_C38;
                while ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((child != lastChild) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[25]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[26]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[27]++;
}
                    visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[257]++;
                    addIcode(Icode_POP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[258]++;
                    stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[259]++;
                    child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[260]++;
                }
                // Preserve tail context flag if any
                visitExpression(child, contextFlags & ECF_TAIL);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[261]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[262]++;
            break;

          case Token.USE_STACK:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[93]++;
            // Indicates that stack was modified externally,
            // like placed catch object
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[263]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[264]++;
            break;

          case Token.REF_CALL:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[94]++;
          case Token.CALL:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[95]++;
          case Token.NEW:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[96]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[265]++;
int CodeCoverConditionCoverageHelper_C39;
                if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[97]++;
                    visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[266]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[98]++;
                    generateCallFunAndThis(child);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[267]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[268]++;
                int argCount = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[269]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[28]++;


                while ((child = child.getNext()) != null) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[28]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[29]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[30]++;
}
                    visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[270]++;
                    ++argCount;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[271]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[272]++;
                int callType = node.getIntProp(Node.SPECIALCALL_PROP,
                                               Node.NON_SPECIALCALL);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[273]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((type != Token.REF_CALL) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((callType != Node.NON_SPECIALCALL) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[99]++;
                    // embed line number and source filename
                    addIndexOp(Icode_CALLSPECIAL, argCount);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[274]++;
                    addUint8(callType);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[275]++;
                    addUint8(type == Token.NEW ? 1 : 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[276]++;
                    addUint16(lineNumber & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[277]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[100]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[278]++;
int CodeCoverConditionCoverageHelper_C42;
                    // Only use the tail call optimization if we're not in a try
                    // or we're not generating debug info (since the
                    // optimization will confuse the debugger)
                    if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (128)) == 0 || true) &&
 ((type == Token.CALL) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (64)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (32)) == 0 || true) &&
 (((contextFlags & ECF_TAIL) != 0) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (16)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((compilerEnv.isGenerateDebugInfo()) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((itsInTryFlag) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 4) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 4) && false))
                    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[101]++;
                        type = Icode_TAIL_CALL;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[279]++;

                    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[102]++;}
                    addIndexOp(type, argCount);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[280]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[281]++;
int CodeCoverConditionCoverageHelper_C43;
                // adjust stack
                if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((type == Token.NEW) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[103]++;
                    // new: f, args -> result
                    stackChange(-argCount);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[282]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[104]++;
                    // call: f, thisObj, args -> result
                    // ref_call: f, thisObj, args -> ref
                    stackChange(-1 - argCount);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[283]++;
                }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[284]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((argCount > itsData.itsMaxCalleeArgs) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[105]++;
                    itsData.itsMaxCalleeArgs = argCount;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[285]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[106]++;}
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[286]++;
            break;

          case Token.AND:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[107]++;
          case Token.OR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[108]++;
            {
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[287]++;
                addIcode(Icode_DUP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[288]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[289]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[290]++;
                int afterSecondJumpStart = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[291]++;
                int jump = (type == Token.AND) ? Token.IFNE : Token.IFEQ;
                addGotoOp(jump);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[292]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[293]++;
                addIcode(Icode_POP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[294]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[295]++;
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[296]++;
                // Preserve tail context flag if any
                visitExpression(child, contextFlags & ECF_TAIL);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[297]++;
                resolveForwardGoto(afterSecondJumpStart);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[298]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[299]++;
            break;

          case Token.HOOK:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[109]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[300]++;
                Node ifThen = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[301]++;
                Node ifElse = ifThen.getNext();
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[302]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[303]++;
                int elseJumpStart = iCodeTop;
                addGotoOp(Token.IFNE);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[304]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[305]++;
                // Preserve tail context flag if any
                visitExpression(ifThen, contextFlags & ECF_TAIL);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[306]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[307]++;
                int afterElseJumpStart = iCodeTop;
                addGotoOp(Token.GOTO);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[308]++;
                resolveForwardGoto(elseJumpStart);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[309]++;
                stackDepth = savedStackDepth;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[310]++;
                // Preserve tail context flag if any
                visitExpression(ifElse, contextFlags & ECF_TAIL);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[311]++;
                resolveForwardGoto(afterElseJumpStart);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[312]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[313]++;
            break;

          case Token.GETPROP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[110]++;
          case Token.GETPROPNOWARN:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[111]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[314]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[315]++;
            addStringOp(type, child.getString());
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[316]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[317]++;
            break;

          case Token.DELPROP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[112]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[318]++;
            boolean isName = child.getType() == Token.BINDNAME;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[319]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[320]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[321]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[322]++;
int CodeCoverConditionCoverageHelper_C45;
            if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((isName) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[113]++;
                // special handling for delete name
                addIcode(Icode_DELNAME);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[323]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[114]++;
                addToken(Token.DELPROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[324]++;
            }
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[325]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[326]++;
            break;

          case Token.GETELEM:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[115]++;
          case Token.BITAND:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[116]++;
          case Token.BITOR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[117]++;
          case Token.BITXOR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[118]++;
          case Token.LSH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[119]++;
          case Token.RSH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[120]++;
          case Token.URSH:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[121]++;
          case Token.ADD:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[122]++;
          case Token.SUB:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[123]++;
          case Token.MOD:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[124]++;
          case Token.DIV:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[125]++;
          case Token.MUL:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[126]++;
          case Token.EQ:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[127]++;
          case Token.NE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[128]++;
          case Token.SHEQ:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[129]++;
          case Token.SHNE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[130]++;
          case Token.IN:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[131]++;
          case Token.INSTANCEOF:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[132]++;
          case Token.LE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[133]++;
          case Token.LT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[134]++;
          case Token.GE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[135]++;
          case Token.GT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[136]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[327]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[328]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[329]++;
            addToken(type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[330]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[331]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[332]++;
            break;

          case Token.POS:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[137]++;
          case Token.NEG:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[138]++;
          case Token.NOT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[139]++;
          case Token.BITNOT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[140]++;
          case Token.TYPEOF:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[141]++;
          case Token.VOID:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[142]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[333]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[334]++;
int CodeCoverConditionCoverageHelper_C46;
            if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((type == Token.VOID) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[143]++;
                addIcode(Icode_POP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[335]++;
                addIcode(Icode_UNDEF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[336]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[144]++;
                addToken(type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[337]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[338]++;
            break;

          case Token.GET_REF:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[145]++;
          case Token.DEL_REF:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[146]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[339]++;
            addToken(type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[340]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[341]++;
            break;

          case Token.SETPROP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[147]++;
          case Token.SETPROP_OP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[148]++;
            {
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[342]++;
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[343]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[344]++;
                String property = child.getString();
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[345]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[346]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((type == Token.SETPROP_OP) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[149]++;
                    addIcode(Icode_DUP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[347]++;
                    stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[348]++;
                    addStringOp(Token.GETPROP, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[349]++;
                    // Compensate for the following USE_STACK
                    stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[350]++;

                } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[150]++;}
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[351]++;
                addStringOp(Token.SETPROP, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[352]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[353]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[354]++;
            break;

          case Token.SETELEM:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[151]++;
          case Token.SETELEM_OP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[152]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[355]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[356]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[357]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[358]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[359]++;
int CodeCoverConditionCoverageHelper_C48;
            if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((type == Token.SETELEM_OP) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[153]++;
                addIcode(Icode_DUP2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[360]++;
                stackChange(2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[361]++;
                addToken(Token.GETELEM);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[362]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[363]++;
                // Compensate for the following USE_STACK
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[364]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[154]++;}
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[365]++;
            addToken(Token.SETELEM);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[366]++;
            stackChange(-2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[367]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[368]++;
            break;

          case Token.SET_REF:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[155]++;
          case Token.SET_REF_OP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[156]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[369]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[370]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[371]++;
int CodeCoverConditionCoverageHelper_C49;
            if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((type == Token.SET_REF_OP) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[157]++;
                addIcode(Icode_DUP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[372]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[373]++;
                addToken(Token.GET_REF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[374]++;
                // Compensate for the following USE_STACK
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[375]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[158]++;}
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[376]++;
            addToken(Token.SET_REF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[377]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[378]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[379]++;
            break;

          case Token.STRICT_SETNAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[159]++;
          case Token.SETNAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[160]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[380]++;
                String name = child.getString();
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[381]++;
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[382]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[383]++;
                addStringOp(type, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[384]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[385]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[386]++;
            break;

          case Token.SETCONST:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[161]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[387]++;
                String name = child.getString();
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[388]++;
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[389]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[390]++;
                addStringOp(Icode_SETCONST, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[391]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[392]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[393]++;
            break;

          case Token.TYPEOFNAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[162]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[394]++;
                int index = -1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[395]++;
int CodeCoverConditionCoverageHelper_C50;
                // use typeofname if an activation frame exists
                // since the vars all exist there instead of in jregs
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((itsInFunctionFlag) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((itsData.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[163]++;
                    index = scriptOrFn.getIndexForNameNode(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[396]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[164]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[397]++;
int CodeCoverConditionCoverageHelper_C51;
                if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[165]++;
                    addStringOp(Icode_TYPEOFNAME, node.getString());
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[398]++;
                    stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[399]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[166]++;
                    addVarOp(Token.GETVAR, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[400]++;
                    stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[401]++;
                    addToken(Token.TYPEOF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[402]++;
                }
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[403]++;
            break;

          case Token.BINDNAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[167]++;
          case Token.NAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[168]++;
          case Token.STRING:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[169]++;
            addStringOp(type, node.getString());
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[404]++;
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[405]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[406]++;
            break;

          case Token.INC:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[170]++;
          case Token.DEC:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[171]++;
            visitIncDec(node, child);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[407]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[408]++;
            break;

          case Token.NUMBER:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[172]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[409]++;
                double num = node.getDouble();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[410]++;
                int inum = (int)num;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[411]++;
int CodeCoverConditionCoverageHelper_C52;
                if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((inum == num) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[173]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[412]++;
int CodeCoverConditionCoverageHelper_C53;
                    if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((inum == 0) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[175]++;
                        addIcode(Icode_ZERO);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[413]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[414]++;
int CodeCoverConditionCoverageHelper_C54;
                        // Check for negative zero
                        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((1.0 / num < 0.0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[177]++;
                            addToken(Token.NEG);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[415]++;

                        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[178]++;}

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[176]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[416]++;
int CodeCoverConditionCoverageHelper_C55; if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((inum == 1) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[179]++;
                        addIcode(Icode_ONE);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[417]++;

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[180]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[418]++;
int CodeCoverConditionCoverageHelper_C56; if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 (((short)inum == inum) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[181]++;
                        addIcode(Icode_SHORTNUMBER);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[419]++;
                        // write short as uin16 bit pattern
                        addUint16(inum & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[420]++;

                    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[182]++;
                        addIcode(Icode_INTNUMBER);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[421]++;
                        addInt(inum);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[422]++;
                    }
}
}

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[174]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[423]++;
                    int index = getDoubleIndex(num);
                    addIndexOp(Token.NUMBER, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[424]++;
                }
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[425]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[426]++;
            break;

          case Token.GETVAR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[183]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[427]++;
int CodeCoverConditionCoverageHelper_C57;
                if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((itsData.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[184]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[428]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[185]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[429]++;
                int index = scriptOrFn.getIndexForNameNode(node);
                addVarOp(Token.GETVAR, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[430]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[431]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[432]++;
            break;

          case Token.SETVAR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[186]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[433]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((itsData.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[187]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[434]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[188]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[435]++;
                int index = scriptOrFn.getIndexForNameNode(child);
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[436]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[437]++;
                addVarOp(Token.SETVAR, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[438]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[439]++;
            break;

          case Token.SETCONSTVAR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[189]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[440]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((itsData.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[190]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[441]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[191]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[442]++;
                int index = scriptOrFn.getIndexForNameNode(child);
                child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[443]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[444]++;
                addVarOp(Token.SETCONSTVAR, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[445]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[446]++;
            break;

          case Token.NULL:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[192]++;
          case Token.THIS:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[193]++;
          case Token.THISFN:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[194]++;
          case Token.FALSE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[195]++;
          case Token.TRUE:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[196]++;
            addToken(type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[447]++;
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[448]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[449]++;
            break;

          case Token.ENUM_NEXT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[197]++;
          case Token.ENUM_ID:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[198]++;
            addIndexOp(type, getLocalBlockRef(node));
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[450]++;
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[451]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[452]++;
            break;

          case Token.REGEXP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[199]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[453]++;
                int index = node.getExistingIntProp(Node.REGEXP_PROP);
                addIndexOp(Token.REGEXP, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[454]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[455]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[456]++;
            break;

          case Token.ARRAYLIT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[200]++;
          case Token.OBJECTLIT:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[201]++;
            visitLiteral(node, child);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[457]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[458]++;
            break;

          case Token.ARRAYCOMP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[202]++;
            visitArrayComprehension(node, child, child.getNext());
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[459]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[460]++;
            break;

          case Token.REF_SPECIAL:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[203]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[461]++;
            addStringOp(type, (String)node.getProp(Node.NAME_PROP));
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[462]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[463]++;
            break;

          case Token.REF_MEMBER:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[204]++;
          case Token.REF_NS_MEMBER:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[205]++;
          case Token.REF_NAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[206]++;
          case Token.REF_NS_NAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[207]++;
            {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[464]++;
                int memberTypeFlags = node.getIntProp(Node.MEMBER_TYPE_PROP, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[465]++;
                // generate possible target, possible namespace and member
                int childCount = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[466]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[31]++;


int CodeCoverConditionCoverageHelper_C60;
                do {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[31]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[32]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[33]++;
}
                    visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[467]++;
                    ++childCount;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[468]++;
                    child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[469]++;
                } while ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false));
                addIndexOp(type, memberTypeFlags);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[470]++;
                stackChange(1 - childCount);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[471]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[472]++;
            break;

          case Token.DOTQUERY:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[208]++;
            {
                int queryPC;
                updateLineNumber(node);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[473]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[474]++;
                addIcode(Icode_ENTERDQ);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[475]++;
                stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[476]++;
                queryPC = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[477]++;
                visitExpression(child.getNext(), 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[478]++;
                addBackwardGoto(Icode_LEAVEDQ, queryPC);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[479]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[480]++;
            break;

          case Token.DEFAULTNAMESPACE :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[209]++;
          case Token.ESCXMLATTR :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[210]++;
          case Token.ESCXMLTEXT :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[211]++;
            visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[481]++;
            addToken(type);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[482]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[483]++;
            break;

          case Token.YIELD:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[212]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[484]++;
int CodeCoverConditionCoverageHelper_C61;
            if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[213]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[485]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[214]++;
                addIcode(Icode_UNDEF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[486]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[487]++;
            }
            addToken(Token.YIELD);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[488]++;
            addUint16(node.getLineno() & 0xFFFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[489]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[490]++;
            break;

          case Token.WITHEXPR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[215]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[491]++;
            Node enterWith = node.getFirstChild();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[492]++;
            Node with = enterWith.getNext();
            visitExpression(enterWith.getFirstChild(), 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[493]++;
            addToken(Token.ENTERWITH);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[494]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[495]++;
            visitExpression(with.getFirstChild(), 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[496]++;
            addToken(Token.LEAVEWITH);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[497]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[498]++;
            break;
          }

          default:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[216]++;
            throw badTree(node);
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[499]++;
int CodeCoverConditionCoverageHelper_C62;
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((savedStackDepth + 1 != stackDepth) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[217]++;
            Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[500]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[218]++;}
    }

    private void generateCallFunAndThis(Node left)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[501]++;
        // Generate code to place on stack function and thisObj
        int type = left.getType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[502]++;
        switch (type) {
          case Token.NAME:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[219]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[503]++;
            String name = left.getString();
            // stack: ... -> ... function thisObj
            addStringOp(Icode_NAME_AND_THIS, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[504]++;
            stackChange(2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[505]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[506]++;
            break;
          }
          case Token.GETPROP:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[220]++;
          case Token.GETELEM:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[221]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[507]++;
            Node target = left.getFirstChild();
            visitExpression(target, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[508]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[509]++;
            Node id = target.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[510]++;
int CodeCoverConditionCoverageHelper_C63;
            if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((type == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[222]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[511]++;
                String property = id.getString();
                // stack: ... target -> ... function thisObj
                addStringOp(Icode_PROP_AND_THIS, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[512]++;
                stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[513]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[223]++;
                visitExpression(id, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[514]++;
                // stack: ... target id -> ... function thisObj
                addIcode(Icode_ELEM_AND_THIS);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[515]++;
            }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[516]++;
            break;
          }
          default:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[224]++;
            // Including Token.GETVAR
            visitExpression(left, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[517]++;
            // stack: ... value -> ... function thisObj
            addIcode(Icode_VALUE_AND_THIS);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[518]++;
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[519]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[520]++;
            break;
        }
    }


    private void visitIncDec(Node node, Node child)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[521]++;
        int incrDecrMask = node.getExistingIntProp(Node.INCRDECR_PROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[522]++;
        int childType = child.getType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[523]++;
        switch (childType) {
          case Token.GETVAR :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[225]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[524]++;
int CodeCoverConditionCoverageHelper_C64;
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((itsData.itsNeedsActivation) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[226]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[525]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[227]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[526]++;
            int i = scriptOrFn.getIndexForNameNode(child);
            addVarOp(Icode_VAR_INC_DEC, i);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[527]++;
            addUint8(incrDecrMask);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[528]++;
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[529]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[530]++;
            break;
          }
          case Token.NAME :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[228]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[531]++;
            String name = child.getString();
            addStringOp(Icode_NAME_INC_DEC, name);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[532]++;
            addUint8(incrDecrMask);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[533]++;
            stackChange(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[534]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[535]++;
            break;
          }
          case Token.GETPROP :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[229]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[536]++;
            Node object = child.getFirstChild();
            visitExpression(object, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[537]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[538]++;
            String property = object.getNext().getString();
            addStringOp(Icode_PROP_INC_DEC, property);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[539]++;
            addUint8(incrDecrMask);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[540]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[541]++;
            break;
          }
          case Token.GETELEM :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[230]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[542]++;
            Node object = child.getFirstChild();
            visitExpression(object, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[543]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[544]++;
            Node index = object.getNext();
            visitExpression(index, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[545]++;
            addIcode(Icode_ELEM_INC_DEC);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[546]++;
            addUint8(incrDecrMask);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[547]++;
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[548]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[549]++;
            break;
          }
          case Token.GET_REF :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[231]++; {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[550]++;
            Node ref = child.getFirstChild();
            visitExpression(ref, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[551]++;
            addIcode(Icode_REF_INC_DEC);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[552]++;
            addUint8(incrDecrMask);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[553]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[554]++;
            break;
          }
          default :
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[232]++; {
            throw badTree(node);
          }
        }
    }

    private void visitLiteral(Node node, Node child)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[555]++;
        int type = node.getType();
        int count;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[556]++;
        Object[] propertyIds = null;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[557]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((type == Token.ARRAYLIT) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[233]++;
            count = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[558]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[559]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[34]++;


int CodeCoverConditionCoverageHelper_C66;
            for (Node n = child;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); n = n.getNext()) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[34]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[35]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[36]++;
}
                ++count;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[560]++;
            }

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[234]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[561]++;
int CodeCoverConditionCoverageHelper_C67; if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((type == Token.OBJECTLIT) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[235]++;
            propertyIds = (Object[])node.getProp(Node.OBJECT_IDS_PROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[562]++;
            count = propertyIds.length;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[563]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[236]++;
            throw badTree(node);
        }
}
        addIndexOp(Icode_LITERAL_NEW, count);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[564]++;
        stackChange(2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[565]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[566]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[37]++;


int CodeCoverConditionCoverageHelper_C68;
        while ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[37]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[38]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[39]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[567]++;
            int childType = child.getType();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[568]++;
int CodeCoverConditionCoverageHelper_C69;
            if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((childType == Token.GET) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[237]++;
                visitExpression(child.getFirstChild(), 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[569]++;
                addIcode(Icode_LITERAL_GETTER);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[570]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[238]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[571]++;
int CodeCoverConditionCoverageHelper_C70; if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((childType == Token.SET) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[239]++;
                visitExpression(child.getFirstChild(), 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[572]++;
                addIcode(Icode_LITERAL_SETTER);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[573]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[240]++;
                visitExpression(child, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[574]++;
                addIcode(Icode_LITERAL_SET);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[575]++;
            }
}
            stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[576]++;
            child = child.getNext();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[577]++;
        }
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[578]++;
int CodeCoverConditionCoverageHelper_C71;
        if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((type == Token.ARRAYLIT) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[241]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[579]++;
            int[] skipIndexes = (int[])node.getProp(Node.SKIP_INDEXES_PROP);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[580]++;
int CodeCoverConditionCoverageHelper_C72;
            if ((((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((skipIndexes == null) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[243]++;
                addToken(Token.ARRAYLIT);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[581]++;

            } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[244]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[582]++;
                int index = literalIds.size();
                literalIds.add(skipIndexes);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[583]++;
                addIndexOp(Icode_SPARE_ARRAYLIT, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[584]++;
            }

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[242]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[585]++;
            int index = literalIds.size();
            literalIds.add(propertyIds);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[586]++;
            addIndexOp(Token.OBJECTLIT, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[587]++;
        }
        stackChange(-1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[588]++;
    }

    private void visitArrayComprehension(Node node, Node initStmt, Node expr)
    {
        // A bit of a hack: array comprehensions are implemented using
        // statement nodes for the iteration, yet they appear in an
        // expression context. So we pass the current stack depth to
        // visitStatement so it can check that the depth is not altered
        // by statements.
        visitStatement(initStmt, stackDepth);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[589]++;
        visitExpression(expr, 0);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[590]++;
    }

    private int getLocalBlockRef(Node node)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[591]++;
        Node localBlock = (Node)node.getProp(Node.LOCAL_BLOCK_PROP);
        return localBlock.getExistingIntProp(Node.LOCAL_PROP);
    }

    private int getTargetLabel(Node target)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[592]++;
        int label = target.labelId();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[593]++;
int CodeCoverConditionCoverageHelper_C73;
        if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((label != -1) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[245]++;
            return label;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[246]++;}
        label = labelTableTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[594]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[595]++;
int CodeCoverConditionCoverageHelper_C74;
        if ((((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (8)) == 0 || true) &&
 ((labelTable == null) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((label == labelTable.length) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[247]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[596]++;
int CodeCoverConditionCoverageHelper_C75;
            if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((labelTable == null) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[249]++;
                labelTable = new int[MIN_LABEL_TABLE_SIZE];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[597]++;

            }else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[250]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[598]++;
                int[] tmp = new int[labelTable.length * 2];
                System.arraycopy(labelTable, 0, tmp, 0, label);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[599]++;
                labelTable = tmp;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[600]++;
            }

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[248]++;}
        labelTableTop = label + 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[601]++;
        labelTable[label] = -1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[602]++;

        target.labelId(label);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[603]++;
        return label;
    }

    private void markTargetLabel(Node target)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[604]++;
        int label = getTargetLabel(target);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[605]++;
int CodeCoverConditionCoverageHelper_C76;
        if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((labelTable[label] != -1) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[251]++;
            // Can mark label only once
            Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[606]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[252]++;}
        labelTable[label] = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[607]++;
    }

    private void addGoto(Node target, int gotoOp)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[608]++;
        int label = getTargetLabel(target);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[609]++;
int CodeCoverConditionCoverageHelper_C77;
        if ((((((CodeCoverConditionCoverageHelper_C77 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C77 |= (2)) == 0 || true) &&
 ((label < labelTableTop) && 
  ((CodeCoverConditionCoverageHelper_C77 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[77].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C77, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[253]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[610]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[254]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[611]++;
        int targetPC = labelTable[label];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[612]++;
int CodeCoverConditionCoverageHelper_C78;

        if ((((((CodeCoverConditionCoverageHelper_C78 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C78 |= (2)) == 0 || true) &&
 ((targetPC != -1) && 
  ((CodeCoverConditionCoverageHelper_C78 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[78].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C78, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[255]++;
            addBackwardGoto(gotoOp, targetPC);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[613]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[256]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[614]++;
            int gotoPC = iCodeTop;
            addGotoOp(gotoOp);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[615]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[616]++;
            int top = fixupTableTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[617]++;
int CodeCoverConditionCoverageHelper_C79;
            if ((((((CodeCoverConditionCoverageHelper_C79 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C79 |= (8)) == 0 || true) &&
 ((fixupTable == null) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C79 |= (2)) == 0 || true) &&
 ((top == fixupTable.length) && 
  ((CodeCoverConditionCoverageHelper_C79 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[79].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C79, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[257]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[618]++;
int CodeCoverConditionCoverageHelper_C80;
                if ((((((CodeCoverConditionCoverageHelper_C80 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C80 |= (2)) == 0 || true) &&
 ((fixupTable == null) && 
  ((CodeCoverConditionCoverageHelper_C80 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[80].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C80, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[259]++;
                    fixupTable = new long[MIN_FIXUP_TABLE_SIZE];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[619]++;

                } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[260]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[620]++;
                    long[] tmp = new long[fixupTable.length * 2];
                    System.arraycopy(fixupTable, 0, tmp, 0, top);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[621]++;
                    fixupTable = tmp;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[622]++;
                }

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[258]++;}
            fixupTableTop = top + 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[623]++;
            fixupTable[top] = ((long)label << 32) | gotoPC;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[624]++;
        }
    }

    private void fixLabelGotos()
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[625]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[40]++;


int CodeCoverConditionCoverageHelper_C81;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C81 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C81 |= (2)) == 0 || true) &&
 ((i < fixupTableTop) && 
  ((CodeCoverConditionCoverageHelper_C81 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[81].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C81, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[40]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[41]--;
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.loops[42]++;
}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[626]++;
            long fixup = fixupTable[i];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[627]++;
            int label = (int)(fixup >> 32);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[628]++;
            int jumpSource = (int)fixup;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[629]++;
            int pc = labelTable[label];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[630]++;
int CodeCoverConditionCoverageHelper_C82;
            if ((((((CodeCoverConditionCoverageHelper_C82 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C82 |= (2)) == 0 || true) &&
 ((pc == -1) && 
  ((CodeCoverConditionCoverageHelper_C82 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[82].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C82, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[261]++;
                // Unlocated label
                throw Kit.codeBug();

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[262]++;}
            resolveGoto(jumpSource, pc);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[631]++;
        }
        fixupTableTop = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[632]++;
    }

    private void addBackwardGoto(int gotoOp, int jumpPC)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[633]++;
        int fromPC = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[634]++;
int CodeCoverConditionCoverageHelper_C83;
        // Ensure that this is a jump backward
        if ((((((CodeCoverConditionCoverageHelper_C83 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C83 |= (2)) == 0 || true) &&
 ((fromPC <= jumpPC) && 
  ((CodeCoverConditionCoverageHelper_C83 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[83].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C83, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[263]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[264]++;}
        addGotoOp(gotoOp);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[635]++;
        resolveGoto(fromPC, jumpPC);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[636]++;
    }

    private void resolveForwardGoto(int fromPC)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[637]++;
int CodeCoverConditionCoverageHelper_C84;
        // Ensure that forward jump skips at least self bytecode
        if ((((((CodeCoverConditionCoverageHelper_C84 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C84 |= (2)) == 0 || true) &&
 ((iCodeTop < fromPC + 3) && 
  ((CodeCoverConditionCoverageHelper_C84 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[84].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C84, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[265]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[266]++;}
        resolveGoto(fromPC, iCodeTop);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[638]++;
    }

    private void resolveGoto(int fromPC, int jumpPC)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[639]++;
        int offset = jumpPC - fromPC;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[640]++;
int CodeCoverConditionCoverageHelper_C85;
        // Ensure that jumps do not overlap
        if ((((((CodeCoverConditionCoverageHelper_C85 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C85 |= (8)) == 0 || true) &&
 ((0 <= offset) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C85 |= (2)) == 0 || true) &&
 ((offset <= 2) && 
  ((CodeCoverConditionCoverageHelper_C85 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[85].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C85, 2) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[267]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[268]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[641]++;
        int offsetSite = fromPC + 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[642]++;
int CodeCoverConditionCoverageHelper_C86;
        if ((((((CodeCoverConditionCoverageHelper_C86 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C86 |= (2)) == 0 || true) &&
 ((offset != (short)offset) && 
  ((CodeCoverConditionCoverageHelper_C86 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[86].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C86, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[269]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[643]++;
int CodeCoverConditionCoverageHelper_C87;
            if ((((((CodeCoverConditionCoverageHelper_C87 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C87 |= (2)) == 0 || true) &&
 ((itsData.longJumps == null) && 
  ((CodeCoverConditionCoverageHelper_C87 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[87].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C87, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[271]++;
                itsData.longJumps = new UintMap();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[644]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[272]++;}
            itsData.longJumps.put(offsetSite, jumpPC);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[645]++;
            offset = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[646]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[270]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[647]++;
        byte[] array = itsData.itsICode;
        array[offsetSite] = (byte)(offset >> 8);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[648]++;
        array[offsetSite + 1] = (byte)offset;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[649]++;
    }

    private void addToken(int token)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[650]++;
int CodeCoverConditionCoverageHelper_C88;
        if ((((((CodeCoverConditionCoverageHelper_C88 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C88 |= (2)) == 0 || true) &&
 ((Icode.validTokenCode(token)) && 
  ((CodeCoverConditionCoverageHelper_C88 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[88].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C88, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[273]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[274]++;}
        addUint8(token);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[651]++;
    }

    private void addIcode(int icode)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[652]++;
int CodeCoverConditionCoverageHelper_C89;
        if ((((((CodeCoverConditionCoverageHelper_C89 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C89 |= (2)) == 0 || true) &&
 ((Icode.validIcode(icode)) && 
  ((CodeCoverConditionCoverageHelper_C89 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[89].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C89, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[275]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[276]++;}
        // Write negative icode as uint8 bits
        addUint8(icode & 0xFF);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[653]++;
    }

    private void addUint8(int value)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[654]++;
int CodeCoverConditionCoverageHelper_C90;
        if ((((((CodeCoverConditionCoverageHelper_C90 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C90 |= (2)) == 0 || true) &&
 (((value & ~0xFF) != 0) && 
  ((CodeCoverConditionCoverageHelper_C90 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[90].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C90, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[277]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[278]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[655]++;
        byte[] array = itsData.itsICode;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[656]++;
        int top = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[657]++;
int CodeCoverConditionCoverageHelper_C91;
        if ((((((CodeCoverConditionCoverageHelper_C91 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C91 |= (2)) == 0 || true) &&
 ((top == array.length) && 
  ((CodeCoverConditionCoverageHelper_C91 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[91].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C91, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[279]++;
            array = increaseICodeCapacity(1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[658]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[280]++;}
        array[top] = (byte)value;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[659]++;
        iCodeTop = top + 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[660]++;
    }

    private void addUint16(int value)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[661]++;
int CodeCoverConditionCoverageHelper_C92;
        if ((((((CodeCoverConditionCoverageHelper_C92 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C92 |= (2)) == 0 || true) &&
 (((value & ~0xFFFF) != 0) && 
  ((CodeCoverConditionCoverageHelper_C92 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[92].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C92, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[281]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[282]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[662]++;
        byte[] array = itsData.itsICode;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[663]++;
        int top = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[664]++;
int CodeCoverConditionCoverageHelper_C93;
        if ((((((CodeCoverConditionCoverageHelper_C93 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C93 |= (2)) == 0 || true) &&
 ((top + 2 > array.length) && 
  ((CodeCoverConditionCoverageHelper_C93 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[93].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C93, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[283]++;
            array = increaseICodeCapacity(2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[665]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[284]++;}
        array[top] = (byte)(value >>> 8);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[666]++;
        array[top + 1] = (byte)value;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[667]++;
        iCodeTop = top + 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[668]++;
    }

    private void addInt(int i)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[669]++;
        byte[] array = itsData.itsICode;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[670]++;
        int top = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[671]++;
int CodeCoverConditionCoverageHelper_C94;
        if ((((((CodeCoverConditionCoverageHelper_C94 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C94 |= (2)) == 0 || true) &&
 ((top + 4 > array.length) && 
  ((CodeCoverConditionCoverageHelper_C94 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[94].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C94, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[285]++;
            array = increaseICodeCapacity(4);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[672]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[286]++;}
        array[top] = (byte)(i >>> 24);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[673]++;
        array[top + 1] = (byte)(i >>> 16);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[674]++;
        array[top + 2] = (byte)(i >>> 8);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[675]++;
        array[top + 3] = (byte)i;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[676]++;
        iCodeTop = top + 4;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[677]++;
    }

    private int getDoubleIndex(double num)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[678]++;
        int index = doubleTableTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[679]++;
int CodeCoverConditionCoverageHelper_C95;
        if ((((((CodeCoverConditionCoverageHelper_C95 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C95 |= (2)) == 0 || true) &&
 ((index == 0) && 
  ((CodeCoverConditionCoverageHelper_C95 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[95].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C95, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[287]++;
            itsData.itsDoubleTable = new double[64];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[680]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[288]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[681]++;
int CodeCoverConditionCoverageHelper_C96; if ((((((CodeCoverConditionCoverageHelper_C96 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C96 |= (2)) == 0 || true) &&
 ((itsData.itsDoubleTable.length == index) && 
  ((CodeCoverConditionCoverageHelper_C96 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[96].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C96, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[289]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[682]++;
            double[] na = new double[index * 2];
            System.arraycopy(itsData.itsDoubleTable, 0, na, 0, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[683]++;
            itsData.itsDoubleTable = na;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[684]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[290]++;}
}
        itsData.itsDoubleTable[index] = num;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[685]++;
        doubleTableTop = index + 1;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[686]++;
        return index;
    }

    private void addGotoOp(int gotoOp)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[687]++;
        byte[] array = itsData.itsICode;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[688]++;
        int top = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[689]++;
int CodeCoverConditionCoverageHelper_C97;
        if ((((((CodeCoverConditionCoverageHelper_C97 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C97 |= (2)) == 0 || true) &&
 ((top + 3 > array.length) && 
  ((CodeCoverConditionCoverageHelper_C97 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[97].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C97, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[291]++;
            array = increaseICodeCapacity(3);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[690]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[292]++;}
        array[top] = (byte)gotoOp;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[691]++;
        // Offset would written later
        iCodeTop = top + 1 + 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[692]++;
    }

    private void addVarOp(int op, int varIndex)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[693]++;
        switch (op) {
          case Token.SETCONSTVAR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[293]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[694]++;
int CodeCoverConditionCoverageHelper_C98;
            if ((((((CodeCoverConditionCoverageHelper_C98 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C98 |= (2)) == 0 || true) &&
 ((varIndex < 128) && 
  ((CodeCoverConditionCoverageHelper_C98 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[98].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C98, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[294]++;
                addIcode(Icode_SETCONSTVAR1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[695]++;
                addUint8(varIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[696]++;
                return;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[295]++;}
            addIndexOp(Icode_SETCONSTVAR, varIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[697]++;
            return;
          case Token.GETVAR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[296]++;
          case Token.SETVAR:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[297]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[698]++;
int CodeCoverConditionCoverageHelper_C99;
            if ((((((CodeCoverConditionCoverageHelper_C99 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C99 |= (2)) == 0 || true) &&
 ((varIndex < 128) && 
  ((CodeCoverConditionCoverageHelper_C99 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[99].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C99, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[298]++;
                addIcode(op == Token.GETVAR ? Icode_GETVAR1 : Icode_SETVAR1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[699]++;
                addUint8(varIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[700]++;
                return;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[299]++;}
            // fallthrough
          case Icode_VAR_INC_DEC:
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[300]++;
            addIndexOp(op, varIndex);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[701]++;
            return; default : CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[301]++;
        }
        throw Kit.codeBug();
    }

    private void addStringOp(int op, String str)
    {
        addStringPrefix(str);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[702]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[703]++;
int CodeCoverConditionCoverageHelper_C100;
        if ((((((CodeCoverConditionCoverageHelper_C100 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C100 |= (2)) == 0 || true) &&
 ((Icode.validIcode(op)) && 
  ((CodeCoverConditionCoverageHelper_C100 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[100].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C100, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[302]++;
            addIcode(op);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[704]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[303]++;
            addToken(op);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[705]++;
        }
    }

    private void addIndexOp(int op, int index)
    {
        addIndexPrefix(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[706]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[707]++;
int CodeCoverConditionCoverageHelper_C101;
        if ((((((CodeCoverConditionCoverageHelper_C101 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C101 |= (2)) == 0 || true) &&
 ((Icode.validIcode(op)) && 
  ((CodeCoverConditionCoverageHelper_C101 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[101].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C101, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[304]++;
            addIcode(op);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[708]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[305]++;
            addToken(op);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[709]++;
        }
    }

    private void addStringPrefix(String str)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[710]++;
        int index = strings.get(str, -1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[711]++;
int CodeCoverConditionCoverageHelper_C102;
        if ((((((CodeCoverConditionCoverageHelper_C102 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C102 |= (2)) == 0 || true) &&
 ((index == -1) && 
  ((CodeCoverConditionCoverageHelper_C102 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[102].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C102, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[306]++;
            index = strings.size();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[712]++;
            strings.put(str, index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[713]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[307]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[714]++;
int CodeCoverConditionCoverageHelper_C103;
        if ((((((CodeCoverConditionCoverageHelper_C103 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C103 |= (2)) == 0 || true) &&
 ((index < 4) && 
  ((CodeCoverConditionCoverageHelper_C103 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[103].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C103, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[308]++;
            addIcode(Icode_REG_STR_C0 - index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[715]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[309]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[716]++;
int CodeCoverConditionCoverageHelper_C104; if ((((((CodeCoverConditionCoverageHelper_C104 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C104 |= (2)) == 0 || true) &&
 ((index <= 0xFF) && 
  ((CodeCoverConditionCoverageHelper_C104 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[104].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C104, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[310]++;
            addIcode(Icode_REG_STR1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[717]++;
            addUint8(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[718]++;

         } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[311]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[719]++;
int CodeCoverConditionCoverageHelper_C105; if ((((((CodeCoverConditionCoverageHelper_C105 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C105 |= (2)) == 0 || true) &&
 ((index <= 0xFFFF) && 
  ((CodeCoverConditionCoverageHelper_C105 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[105].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C105, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[312]++;
            addIcode(Icode_REG_STR2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[720]++;
            addUint16(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[721]++;

         } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[313]++;
            addIcode(Icode_REG_STR4);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[722]++;
            addInt(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[723]++;
        }
}
}
    }

    private void addIndexPrefix(int index)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[724]++;
int CodeCoverConditionCoverageHelper_C106;
        if ((((((CodeCoverConditionCoverageHelper_C106 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C106 |= (2)) == 0 || true) &&
 ((index < 0) && 
  ((CodeCoverConditionCoverageHelper_C106 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[106].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C106, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[314]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[725]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[315]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[726]++;
int CodeCoverConditionCoverageHelper_C107;
        if ((((((CodeCoverConditionCoverageHelper_C107 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C107 |= (2)) == 0 || true) &&
 ((index < 6) && 
  ((CodeCoverConditionCoverageHelper_C107 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[107].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C107, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[316]++;
            addIcode(Icode_REG_IND_C0 - index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[727]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[317]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[728]++;
int CodeCoverConditionCoverageHelper_C108; if ((((((CodeCoverConditionCoverageHelper_C108 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C108 |= (2)) == 0 || true) &&
 ((index <= 0xFF) && 
  ((CodeCoverConditionCoverageHelper_C108 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[108].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C108, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[318]++;
            addIcode(Icode_REG_IND1);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[729]++;
            addUint8(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[730]++;

         } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[319]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[731]++;
int CodeCoverConditionCoverageHelper_C109; if ((((((CodeCoverConditionCoverageHelper_C109 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C109 |= (2)) == 0 || true) &&
 ((index <= 0xFFFF) && 
  ((CodeCoverConditionCoverageHelper_C109 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[109].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C109, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[320]++;
            addIcode(Icode_REG_IND2);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[732]++;
            addUint16(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[733]++;

         } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[321]++;
            addIcode(Icode_REG_IND4);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[734]++;
            addInt(index);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[735]++;
        }
}
}
    }

    private void addExceptionHandler(int icodeStart, int icodeEnd,
                                     int handlerStart, boolean isFinally,
                                     int exceptionObjectLocal, int scopeLocal)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[736]++;
        int top = exceptionTableTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[737]++;
        int[] table = itsData.itsExceptionTable;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[738]++;
int CodeCoverConditionCoverageHelper_C110;
        if ((((((CodeCoverConditionCoverageHelper_C110 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C110 |= (2)) == 0 || true) &&
 ((table == null) && 
  ((CodeCoverConditionCoverageHelper_C110 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[110].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C110, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[322]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[739]++;
int CodeCoverConditionCoverageHelper_C111;
            if ((((((CodeCoverConditionCoverageHelper_C111 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C111 |= (2)) == 0 || true) &&
 ((top != 0) && 
  ((CodeCoverConditionCoverageHelper_C111 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[111].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C111, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[324]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[740]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[325]++;}
            table = new int[Interpreter.EXCEPTION_SLOT_SIZE * 2];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[741]++;
            itsData.itsExceptionTable = table;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[742]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[323]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[743]++;
int CodeCoverConditionCoverageHelper_C112; if ((((((CodeCoverConditionCoverageHelper_C112 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C112 |= (2)) == 0 || true) &&
 ((table.length == top) && 
  ((CodeCoverConditionCoverageHelper_C112 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[112].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C112, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[326]++;
            table = new int[table.length * 2];
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[744]++;
            System.arraycopy(itsData.itsExceptionTable, 0, table, 0, top);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[745]++;
            itsData.itsExceptionTable = table;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[746]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[327]++;}
}
        table[top + Interpreter.EXCEPTION_TRY_START_SLOT]  = icodeStart;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[747]++;
        table[top + Interpreter.EXCEPTION_TRY_END_SLOT]    = icodeEnd;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[748]++;
        table[top + Interpreter.EXCEPTION_HANDLER_SLOT]    = handlerStart;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[749]++;
        table[top + Interpreter.EXCEPTION_TYPE_SLOT]     = isFinally ? 1 : 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[750]++;
        table[top + Interpreter.EXCEPTION_LOCAL_SLOT]    = exceptionObjectLocal;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[751]++;
        table[top + Interpreter.EXCEPTION_SCOPE_SLOT]    = scopeLocal;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[752]++;

        exceptionTableTop = top + Interpreter.EXCEPTION_SLOT_SIZE;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[753]++;
    }

    private byte[] increaseICodeCapacity(int extraSize)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[754]++;
        int capacity = itsData.itsICode.length;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[755]++;
        int top = iCodeTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[756]++;
int CodeCoverConditionCoverageHelper_C113;
        if ((((((CodeCoverConditionCoverageHelper_C113 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C113 |= (2)) == 0 || true) &&
 ((top + extraSize <= capacity) && 
  ((CodeCoverConditionCoverageHelper_C113 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[113].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C113, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[328]++; throw Kit.codeBug();
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[329]++;}
        capacity *= 2;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[757]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[758]++;
int CodeCoverConditionCoverageHelper_C114;
        if ((((((CodeCoverConditionCoverageHelper_C114 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C114 |= (2)) == 0 || true) &&
 ((top + extraSize > capacity) && 
  ((CodeCoverConditionCoverageHelper_C114 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[114].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C114, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[330]++;
            capacity = top + extraSize;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[759]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[331]++;}
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[760]++;
        byte[] array = new byte[capacity];
        System.arraycopy(itsData.itsICode, 0, array, 0, top);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[761]++;
        itsData.itsICode = array;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[762]++;
        return array;
    }

    private void stackChange(int change)
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[763]++;
int CodeCoverConditionCoverageHelper_C115;
        if ((((((CodeCoverConditionCoverageHelper_C115 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C115 |= (2)) == 0 || true) &&
 ((change <= 0) && 
  ((CodeCoverConditionCoverageHelper_C115 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[115].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C115, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[332]++;
            stackDepth += change;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[764]++;

        } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[333]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[765]++;
            int newDepth = stackDepth + change;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[766]++;
int CodeCoverConditionCoverageHelper_C116;
            if ((((((CodeCoverConditionCoverageHelper_C116 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C116 |= (2)) == 0 || true) &&
 ((newDepth > itsData.itsMaxStack) && 
  ((CodeCoverConditionCoverageHelper_C116 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[116].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C116, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[334]++;
                itsData.itsMaxStack = newDepth;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[767]++;

            } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[335]++;}
            stackDepth = newDepth;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[768]++;
        }
    }

    private int allocLocal()
    {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[769]++;
        int localSlot = localTop;
        ++localTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[770]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[771]++;
int CodeCoverConditionCoverageHelper_C117;
        if ((((((CodeCoverConditionCoverageHelper_C117 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C117 |= (2)) == 0 || true) &&
 ((localTop > itsData.itsMaxLocals) && 
  ((CodeCoverConditionCoverageHelper_C117 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[117].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C117, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[336]++;
            itsData.itsMaxLocals = localTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[772]++;

        } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[337]++;}
        return localSlot;
    }

    private void releaseLocal(int localSlot)
    {
        --localTop;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[773]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[774]++;
int CodeCoverConditionCoverageHelper_C118;
        if ((((((CodeCoverConditionCoverageHelper_C118 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C118 |= (2)) == 0 || true) &&
 ((localSlot != localTop) && 
  ((CodeCoverConditionCoverageHelper_C118 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.conditionCounters[118].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C118, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[338]++; Kit.codeBug();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.statements[775]++;
} else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl.branches[339]++;}
    }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl ());
  }
    public static long[] statements = new long[776];
    public static long[] branches = new long[340];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[119];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-CodeGenerator.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,2,3,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,2,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 118; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[43];

  public CodeCoverCoverageCounter$11f1r6z5fa12k16lbsa5vvie2ux53fwj0111dyj7u7vl () {
    super("org.mozilla.javascript.RHINO-SRC-CodeGenerator.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 775; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 339; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 118; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 42; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-CodeGenerator.java");
      for (int i = 1; i <= 775; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 339; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 118; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 14; i++) {
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

