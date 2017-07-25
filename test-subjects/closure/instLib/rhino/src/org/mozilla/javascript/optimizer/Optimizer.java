/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */



package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.*;
import org.mozilla.javascript.ast.ScriptNode;

class Optimizer
{
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.ping();
  }


    static final int NoType = 0;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[1]++;
  }
    static final int NumberType = 1;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[2]++;
  }
    static final int AnyType = 3;
  static {
    CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[3]++;
  }

    // It is assumed that (NumberType | AnyType) == AnyType

    void optimize(ScriptNode scriptOrFn)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[4]++;
        //  run on one function at a time for now
        int functionCount = scriptOrFn.getFunctionCount();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[5]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[1]++;


int CodeCoverConditionCoverageHelper_C1;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((i != functionCount) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[1]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[2]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[3]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[6]++;
            OptFunctionNode f = OptFunctionNode.get(scriptOrFn, i);
            optimizeFunction(f);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[7]++;
        }
    }

    private void optimizeFunction(OptFunctionNode theFunction)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((theFunction.fnode.requiresActivation()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[1]++; return;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[2]++;}

        inDirectCallFunction = theFunction.isTargetOfDirectCall();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[9]++;
        this.theFunction = theFunction;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[10]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[11]++;

        ObjArray statementsArray = new ObjArray();
        buildStatementList_r(theFunction.fnode, statementsArray);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[12]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[13]++;
        Node[] theStatementNodes = new Node[statementsArray.size()];
        statementsArray.toArray(theStatementNodes);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[14]++;

        Block.runFlowAnalyzes(theFunction, theStatementNodes);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[15]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[16]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((theFunction.fnode.requiresActivation()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[3]++;
            /*
             * Now that we know which local vars are in fact always
             * Numbers, we re-write the tree to take advantage of
             * that. Any arithmetic or assignment op involving just
             * Number typed vars is marked so that the codegen will
             * generate non-object code.
             */
            parameterUsedInNumberContext = false;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[17]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[4]++;


            for (Node theStatementNode : theStatementNodes) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[4]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[5]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[6]++;
}
                rewriteForNumberVariables(theStatementNode, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[19]++;
            }
            theFunction.setParameterNumberContext(parameterUsedInNumberContext);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[20]++;

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[4]++;}

    }


/*
        Each directCall parameter is passed as a pair of values - an object
        and a double. The value passed depends on the type of value available at
        the call site. If a double is available, the object in java/lang/Void.TYPE
        is passed as the object value, and if an object value is available, then
        0.0 is passed as the double value.

        The receiving routine always tests the object value before proceeding.
        If the parameter is being accessed in a 'Number Context' then the code
        sequence is :
        if ("parameter_objectValue" == java/lang/Void.TYPE)
            ...fine..., use the parameter_doubleValue
        else
            toNumber(parameter_objectValue)

        and if the parameter is being referenced in an Object context, the code is
        if ("parameter_objectValue" == java/lang/Void.TYPE)
            new Double(parameter_doubleValue)
        else
            ...fine..., use the parameter_objectValue

        If the receiving code never uses the doubleValue, it is converted on
        entry to a Double instead.
*/


/*
        We're referencing a node in a Number context (i.e. we'd prefer it
        was a double value). If the node is a parameter in a directCall
        function, mark it as being referenced in this context.
*/
    private void markDCPNumberContext(Node n)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[21]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((n.getType() == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[5]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[22]++;
            int varIndex = theFunction.getVarIndex(n);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((theFunction.isParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[7]++;
                parameterUsedInNumberContext = true;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[24]++;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[8]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[6]++;}
    }

    private boolean convertParameter(Node n)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[25]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((n.getType() == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[9]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[26]++;
            int varIndex = theFunction.getVarIndex(n);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((theFunction.isParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[11]++;
                n.removeProp(Node.ISNUMBER_PROP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[28]++;
                return true;

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[10]++;}
        return false;
    }

    private int rewriteForNumberVariables(Node n, int desired)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[29]++;
        switch (n.getType()) {
            case Token.EXPR_VOID :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[13]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[30]++;
                    Node child = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[31]++;
                    int type = rewriteForNumberVariables(child, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[32]++;
int CodeCoverConditionCoverageHelper_C8;
                    if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[14]++;
                        n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[33]++;
} else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[15]++;}
                     return NoType;
                }
            case Token.NUMBER :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[16]++;
                n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[34]++;
                return NumberType;

            case Token.GETVAR :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[17]++;
                {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[35]++;
                    int varIndex = theFunction.getVarIndex(n);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[36]++;
int CodeCoverConditionCoverageHelper_C9;
                    if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (32)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (8)) == 0 || true) &&
 ((theFunction.isParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((desired == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 3) && false))
                    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[18]++;
                        n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[37]++;
                        return NumberType;

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[19]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[38]++;
int CodeCoverConditionCoverageHelper_C10; if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((theFunction.isNumberVar(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[20]++;
                        n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[39]++;
                        return NumberType;

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[21]++;}
}
                    return NoType;
                }

            case Token.INC :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[22]++;
            case Token.DEC :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[23]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[40]++;
                    Node child = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[41]++;
                    int type = rewriteForNumberVariables(child, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[42]++;
int CodeCoverConditionCoverageHelper_C11;
                    if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((child.getType() == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[24]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[43]++;
int CodeCoverConditionCoverageHelper_C12;
                        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (8)) == 0 || true) &&
 ((type == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((convertParameter(child)) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 2) && false))
                        {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[26]++;
                            n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[44]++;
                            markDCPNumberContext(child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[45]++;
                            return NumberType;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[27]++;}
                        return NoType;

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[25]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[46]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((child.getType() == Token.GETELEM) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((child.getType() == Token.GETPROP) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 2) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[28]++;
                        return type;

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[29]++;}
}
                    return NoType;
                }
            case Token.SETVAR :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[30]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[47]++;
                    Node lChild = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[48]++;
                    Node rChild = lChild.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[49]++;
                    int rType = rewriteForNumberVariables(rChild, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[50]++;
                    int varIndex = theFunction.getVarIndex(n);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[51]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (8)) == 0 || true) &&
 ((inDirectCallFunction) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((theFunction.isParameter(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 2) && false))
                    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[31]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[52]++;
int CodeCoverConditionCoverageHelper_C15;
                        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[33]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[53]++;
int CodeCoverConditionCoverageHelper_C16;
                            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[35]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[54]++;
                                return NumberType;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[36]++;}
                            markDCPNumberContext(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[55]++;
                            return NoType;

                        }
                        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[34]++;
                            return rType;
}

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[32]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[56]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((theFunction.isNumberVar(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[37]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[57]++;
int CodeCoverConditionCoverageHelper_C18;
                        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((rType != NumberType) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[39]++;
                            n.removeChild(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[58]++;
                            n.addChildToBack(
                                new Node(Token.TO_DOUBLE, rChild));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[59]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[40]++;}
                        n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[60]++;
                        markDCPNumberContext(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[61]++;
                        return NumberType;

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[38]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[62]++;
int CodeCoverConditionCoverageHelper_C19;
                        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[41]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[63]++;
int CodeCoverConditionCoverageHelper_C20;
                            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[43]++;
                                n.removeChild(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[64]++;
                                n.addChildToBack(
                                    new Node(Token.TO_OBJECT, rChild));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[65]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[44]++;}

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[42]++;}
                        return NoType;
                    }
}
                }
            case Token.LE :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[45]++;
            case Token.LT :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[46]++;
            case Token.GE :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[47]++;
            case Token.GT :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[48]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[66]++;
                    Node lChild = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[67]++;
                    Node rChild = lChild.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[68]++;
                    int lType = rewriteForNumberVariables(lChild, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[69]++;
                    int rType = rewriteForNumberVariables(rChild, NumberType);
                    markDCPNumberContext(lChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[70]++;
                    markDCPNumberContext(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[71]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[72]++;
int CodeCoverConditionCoverageHelper_C21;

                    if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((convertParameter(lChild)) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[49]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[73]++;
int CodeCoverConditionCoverageHelper_C22;
                        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[51]++;
                            return NoType;

                        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[52]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[74]++;
int CodeCoverConditionCoverageHelper_C23; if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[53]++;
                            n.putIntProp(Node.ISNUMBER_PROP, Node.RIGHT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[75]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[54]++;}
}

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[50]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[76]++;
int CodeCoverConditionCoverageHelper_C24; if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[55]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[77]++;
int CodeCoverConditionCoverageHelper_C25;
                        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((lType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[57]++;
                            n.putIntProp(Node.ISNUMBER_PROP, Node.LEFT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[78]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[58]++;}

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[56]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[79]++;
int CodeCoverConditionCoverageHelper_C26;
                        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((lType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[59]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[80]++;
int CodeCoverConditionCoverageHelper_C27;
                            if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[61]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[81]++;

                            }
                            else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[62]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.LEFT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[82]++;
                            }

                        }
                        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[60]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[83]++;
int CodeCoverConditionCoverageHelper_C28;
                            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[63]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.RIGHT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[84]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[64]++;}
                        }
                    }
}
                    // we actually build a boolean value
                    return NoType;
                }

            case Token.ADD :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[65]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[85]++;
                    Node lChild = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[86]++;
                    Node rChild = lChild.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[87]++;
                    int lType = rewriteForNumberVariables(lChild, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[88]++;
                    int rType = rewriteForNumberVariables(rChild, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[89]++;
int CodeCoverConditionCoverageHelper_C29;


                    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((convertParameter(lChild)) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[66]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[90]++;
int CodeCoverConditionCoverageHelper_C30;
                        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[68]++;
                            return NoType;

                        }
                        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[69]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[91]++;
int CodeCoverConditionCoverageHelper_C31;
                            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[70]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.RIGHT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[92]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[71]++;}
                        }

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[67]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[93]++;
int CodeCoverConditionCoverageHelper_C32;
                        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[72]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[94]++;
int CodeCoverConditionCoverageHelper_C33;
                            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((lType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[74]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.LEFT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[95]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[75]++;}

                        }
                        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[73]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[96]++;
int CodeCoverConditionCoverageHelper_C34;
                            if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((lType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[76]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[97]++;
int CodeCoverConditionCoverageHelper_C35;
                                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[78]++;
                                    n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[98]++;
                                    return NumberType;

                                }
                                else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[79]++;
                                    n.putIntProp(Node.ISNUMBER_PROP, Node.LEFT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[99]++;
                                }

                            }
                            else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[77]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[100]++;
int CodeCoverConditionCoverageHelper_C36;
                                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[80]++;
                                    n.putIntProp(Node.ISNUMBER_PROP,
                                                 Node.RIGHT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[101]++;

                                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[81]++;}
                            }
                        }
                    }
                    return NoType;
                }

            case Token.BITXOR :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[82]++;
            case Token.BITOR :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[83]++;
            case Token.BITAND :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[84]++;
            case Token.RSH :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[85]++;
            case Token.LSH :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[86]++;
            case Token.SUB :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[87]++;
            case Token.MUL :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[88]++;
            case Token.DIV :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[89]++;
            case Token.MOD :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[90]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[102]++;
                    Node lChild = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[103]++;
                    Node rChild = lChild.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[104]++;
                    int lType = rewriteForNumberVariables(lChild, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[105]++;
                    int rType = rewriteForNumberVariables(rChild, NumberType);
                    markDCPNumberContext(lChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[106]++;
                    markDCPNumberContext(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[107]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[108]++;
int CodeCoverConditionCoverageHelper_C37;
                    if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((lType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[91]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[109]++;
int CodeCoverConditionCoverageHelper_C38;
                        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[93]++;
                            n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[110]++;
                            return NumberType;

                        }
                        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[94]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[111]++;
int CodeCoverConditionCoverageHelper_C39;
                            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[95]++;
                                n.removeChild(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[112]++;
                                n.addChildToBack(
                                    new Node(Token.TO_DOUBLE, rChild));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[113]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[114]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[96]++;}
                            return NumberType;
                        }

                    }
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[92]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[115]++;
int CodeCoverConditionCoverageHelper_C40;
                        if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((rType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[97]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[116]++;
int CodeCoverConditionCoverageHelper_C41;
                            if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((convertParameter(lChild)) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[99]++;
                                n.removeChild(lChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[117]++;
                                n.addChildToFront(
                                    new Node(Token.TO_DOUBLE, lChild));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[118]++;
                                n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[119]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[100]++;}
                            return NumberType;

                        }
                        else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[98]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[120]++;
int CodeCoverConditionCoverageHelper_C42;
                            if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((convertParameter(lChild)) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[101]++;
                                n.removeChild(lChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[121]++;
                                n.addChildToFront(
                                    new Node(Token.TO_DOUBLE, lChild));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[122]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[102]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[123]++;
int CodeCoverConditionCoverageHelper_C43;
                            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((convertParameter(rChild)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[103]++;
                                n.removeChild(rChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[124]++;
                                n.addChildToBack(
                                    new Node(Token.TO_DOUBLE, rChild));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[125]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[104]++;}
                            n.putIntProp(Node.ISNUMBER_PROP, Node.BOTH);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[126]++;
                            return NumberType;
                        }
                    }
                }
            case Token.SETELEM :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[105]++;
            case Token.SETELEM_OP :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[106]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[127]++;
                    Node arrayBase = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[128]++;
                    Node arrayIndex = arrayBase.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[129]++;
                    Node rValue = arrayIndex.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[130]++;
                    int baseType = rewriteForNumberVariables(arrayBase, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[131]++;
int CodeCoverConditionCoverageHelper_C44;
                    if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((baseType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[107]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[132]++;
int CodeCoverConditionCoverageHelper_C45;
                        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((convertParameter(arrayBase)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[109]++;
                            n.removeChild(arrayBase);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[133]++;
                            n.addChildToFront(
                                new Node(Token.TO_OBJECT, arrayBase));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[134]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[110]++;}

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[108]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[135]++;
                    int indexType = rewriteForNumberVariables(arrayIndex, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[136]++;
int CodeCoverConditionCoverageHelper_C46;
                    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((indexType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[111]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[137]++;
int CodeCoverConditionCoverageHelper_C47;
                        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((convertParameter(arrayIndex)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[113]++;
                            // setting the ISNUMBER_PROP signals the codegen
                            // to use the OptRuntime.setObjectIndex that takes
                            // a double index
                            n.putIntProp(Node.ISNUMBER_PROP, Node.LEFT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[138]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[114]++;}

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[112]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[139]++;
                    int rValueType = rewriteForNumberVariables(rValue, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[140]++;
int CodeCoverConditionCoverageHelper_C48;
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((rValueType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[115]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[141]++;
int CodeCoverConditionCoverageHelper_C49;
                        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((convertParameter(rValue)) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[117]++;
                            n.removeChild(rValue);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[142]++;
                            n.addChildToBack(
                                new Node(Token.TO_OBJECT, rValue));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[143]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[118]++;}

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[116]++;}
                    return NoType;
                }
            case Token.GETELEM :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[119]++; {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[144]++;
                    Node arrayBase = n.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[145]++;
                    Node arrayIndex = arrayBase.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[146]++;
                    int baseType = rewriteForNumberVariables(arrayBase, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[147]++;
int CodeCoverConditionCoverageHelper_C50;
                    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((baseType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[120]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[148]++;
int CodeCoverConditionCoverageHelper_C51;
                        if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((convertParameter(arrayBase)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[122]++;
                            n.removeChild(arrayBase);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[149]++;
                            n.addChildToFront(
                                new Node(Token.TO_OBJECT, arrayBase));
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[150]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[123]++;}

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[121]++;}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[151]++;
                    int indexType = rewriteForNumberVariables(arrayIndex, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[152]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((indexType == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[124]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[153]++;
int CodeCoverConditionCoverageHelper_C53;
                        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((convertParameter(arrayIndex)) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[126]++;
                            // setting the ISNUMBER_PROP signals the codegen
                            // to use the OptRuntime.getObjectIndex that takes
                            // a double index
                            n.putIntProp(Node.ISNUMBER_PROP, Node.RIGHT);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[154]++;

                        } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[127]++;}

                    } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[125]++;}
                    return NoType;
                }
            case Token.CALL :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[128]++;
                {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[155]++;
                    Node child = n.getFirstChild(); // the function node
                    // must be an object
                    rewriteAsObjectChildren(child, child.getFirstChild());
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[156]++;
                    child = child.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[157]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[158]++; // the first arg

                    OptFunctionNode target
                            = (OptFunctionNode)n.getProp(Node.DIRECTCALL_PROP);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[159]++;
int CodeCoverConditionCoverageHelper_C54;
                    if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((target != null) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[129]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[160]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[7]++;


int CodeCoverConditionCoverageHelper_C55;
/*
    we leave each child as a Number if it can be. The codegen will
    handle moving the pairs of parameters.
*/
                        while ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[7]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[8]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[9]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[161]++;
                            int type = rewriteForNumberVariables(child, NumberType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[162]++;
int CodeCoverConditionCoverageHelper_C56;
                            if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((type == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[131]++;
                                markDCPNumberContext(child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[163]++;

                            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[132]++;}
                            child = child.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[164]++;
                        }

                    } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[130]++;
                        rewriteAsObjectChildren(n, child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[165]++;
                    }
                    return NoType;
                }
            default :
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[133]++; {
                    rewriteAsObjectChildren(n, n.getFirstChild());
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[166]++;
                    return NoType;
                }
        }
    }

    private void rewriteAsObjectChildren(Node n, Node child)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[167]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[10]++;


int CodeCoverConditionCoverageHelper_C57;
        // Force optimized children to be objects
        while ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false)) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[10]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[11]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[12]++;
}
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[168]++;
            Node nextChild = child.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[169]++;
            int type = rewriteForNumberVariables(child, NoType);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[170]++;
int CodeCoverConditionCoverageHelper_C58;
            if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((type == NumberType) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[134]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[171]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((convertParameter(child)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[136]++;
                    n.removeChild(child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[172]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[173]++;
                    Node nuChild = new Node(Token.TO_OBJECT, child);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[174]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((nextChild == null) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[138]++;
                        n.addChildToBack(nuChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[175]++;
}
                    else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[139]++;
                        n.addChildBefore(nuChild, nextChild);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[176]++;
}

                } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[137]++;}

            } else {
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[135]++;}
            child = nextChild;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[177]++;
        }
    }

    private static void buildStatementList_r(Node node, ObjArray statements)
    {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[178]++;
        int type = node.getType();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[179]++;
int CodeCoverConditionCoverageHelper_C61;
        if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (128)) == 0 || true) &&
 ((type == Token.BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (64)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C61 |= (32)) == 0 || true) &&
 ((type == Token.LOCAL_BLOCK) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (16)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((type == Token.LOOP) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((type == Token.FUNCTION) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 4) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 4) && false))
        {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[140]++;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[180]++;
            Node child = node.getFirstChild();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[181]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[13]++;


int CodeCoverConditionCoverageHelper_C62;
            while ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) || true)) || (CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 1) && false)) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[13]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[14]--;
  CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.loops[15]++;
}
                buildStatementList_r(child, statements);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[182]++;
                child = child.getNext();
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[183]++;
            }

        } else {
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.branches[141]++;
            statements.add(node);
CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl.statements[184]++;
        }
    }

    private boolean inDirectCallFunction;
    OptFunctionNode theFunction;
    private boolean parameterUsedInNumberContext;
}

class CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl ());
  }
    public static long[] statements = new long[185];
    public static long[] branches = new long[142];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[63];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.optimizer.RHINO-SRC-Optimizer.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,2,1,2,1,1,3,1,1,2,2,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,3,1};
    for (int i = 1; i <= 62; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$iypomt0ag7yuozzgxifgbxg244m9yogoiawrl () {
    super("org.mozilla.javascript.optimizer.RHINO-SRC-Optimizer.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 184; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 141; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 62; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.optimizer.RHINO-SRC-Optimizer.java");
      for (int i = 1; i <= 184; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 141; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 62; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 5; i++) {
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

