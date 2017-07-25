/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.*;
import org.mozilla.javascript.ast.Jump;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;

import java.io.PrintWriter;
import java.io.StringWriter;

class Block
{
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.ping();
  }


    private static class FatBlock
    {

        private static Block[] reduceToArray(ObjToIntMap map)
        {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[1]++;
            Block[] result = null;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((map.isEmpty()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[1]++;
                result = new Block[map.size()];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[3]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[4]++;
                int i = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[5]++;
                ObjToIntMap.Iterator iter = map.newIterator();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
                for (iter.start();(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((iter.done()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); iter.next()) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[1]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[2]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[3]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[7]++;
                    FatBlock fb = (FatBlock)(iter.getKey());
                    result[i++] = fb.realBlock;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[8]++;
                }

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[2]++;}
            return result;
        }

        void addSuccessor(FatBlock b)  { successors.put(b, 0);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[9]++; }
        void addPredecessor(FatBlock b)  { predecessors.put(b, 0);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[10]++; }

        Block[] getSuccessors() { return reduceToArray(successors); }
        Block[] getPredecessors() { return reduceToArray(predecessors); }

        // all the Blocks that come immediately after this
        private ObjToIntMap successors = new ObjToIntMap();
  {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[11]++;
  }
        // all the Blocks that come immediately before this
        private ObjToIntMap predecessors = new ObjToIntMap();
  {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[12]++;
  }

        Block realBlock;
    }

    Block(int startNodeIndex, int endNodeIndex)
    {
        itsStartNodeIndex = startNodeIndex;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[13]++;
        itsEndNodeIndex = endNodeIndex;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[14]++;
    }

    static void runFlowAnalyzes(OptFunctionNode fn, Node[] statementNodes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[15]++;
        int paramCount = fn.fnode.getParamCount();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[16]++;
        int varCount = fn.fnode.getParamAndVarCount();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[17]++;
        int[] varTypes = new int[varCount];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[4]++;


int CodeCoverConditionCoverageHelper_C3;
        // If the variable is a parameter, it could have any type.
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((i != paramCount) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[4]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[5]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[6]++;
}
            varTypes[i] = Optimizer.AnyType;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[19]++;
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[20]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[7]++;


int CodeCoverConditionCoverageHelper_C4;
        // If the variable is from a "var" statement, its typeEvent will be set
        // when we see the setVar node.
        for (int i = paramCount;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i != varCount) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[7]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[8]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[9]++;
}
            varTypes[i] = Optimizer.NoType;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[21]++;
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[22]++;

        Block[] theBlocks = buildBlocks(statementNodes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[23]++;
int CodeCoverConditionCoverageHelper_C5;

        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((DEBUG) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[3]++;
            ++debug_blockCount;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[24]++;
            System.out.println("-------------------"+fn.fnode.getFunctionName()+"  "+debug_blockCount+"--------");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[25]++;
            System.out.println(fn.fnode.toStringTree(fn.fnode));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[26]++;
            System.out.println(toString(theBlocks, statementNodes));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[27]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[4]++;}

        reachingDefDataFlow(fn, statementNodes, theBlocks, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[28]++;
        typeFlow(fn, statementNodes, theBlocks, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[29]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[30]++;
int CodeCoverConditionCoverageHelper_C6;

        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((DEBUG) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[5]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[31]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[10]++;


int CodeCoverConditionCoverageHelper_C7;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((i < theBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[10]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[11]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[12]++;
}
                System.out.println("For block " + theBlocks[i].itsBlockID);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[32]++;
                theBlocks[i].printLiveOnEntrySet(fn);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[33]++;
            }
            System.out.println("Variable Table, size = " + varCount);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[34]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[35]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[13]++;


int CodeCoverConditionCoverageHelper_C8;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((i != varCount) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[13]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[14]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[15]++;
}
                System.out.println("["+i+"] type: "+varTypes[i]);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[36]++;
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[6]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[37]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[16]++;


int CodeCoverConditionCoverageHelper_C9;

        for (int i = paramCount;(((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((i != varCount) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[16]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[17]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[18]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[38]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((varTypes[i] == Optimizer.NumberType) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[7]++;
                fn.setIsNumberVar(i);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[39]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[8]++;}
        }

    }

    private static Block[] buildBlocks(Node[] statementNodes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[40]++;
        // a mapping from each target node to the block it begins
        Map<Node,FatBlock> theTargetBlocks = new HashMap<Node,FatBlock>();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[41]++;
        ObjArray theBlocks = new ObjArray();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[42]++;

        // there's a block that starts at index 0
        int beginNodeIndex = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[43]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[19]++;


int CodeCoverConditionCoverageHelper_C11;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((i < statementNodes.length) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[19]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[20]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[21]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[44]++;
            switch (statementNodes[i].getType()) {
                case Token.TARGET :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[9]++;
                {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
                    if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i != beginNodeIndex) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[10]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[46]++;
                        FatBlock fb = newFatBlock(beginNodeIndex, i - 1);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[47]++;
int CodeCoverConditionCoverageHelper_C13;
                        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((statementNodes[beginNodeIndex].getType() == Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[12]++;
                            theTargetBlocks.put(statementNodes[beginNodeIndex], fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[48]++;

                        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[13]++;}
                        theBlocks.add(fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[49]++;
                        // start the next block at this node
                        beginNodeIndex = i;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[50]++;

                    } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[11]++;}
                }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[51]++;
                break;
                case Token.IFNE :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[14]++;
                case Token.IFEQ :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[15]++;
                case Token.GOTO :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[16]++;
                {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[52]++;
                    FatBlock fb = newFatBlock(beginNodeIndex, i);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[53]++;
int CodeCoverConditionCoverageHelper_C14;
                    if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((statementNodes[beginNodeIndex].getType() == Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[17]++;
                        theTargetBlocks.put(statementNodes[beginNodeIndex], fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[54]++;

                    } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[18]++;}
                    theBlocks.add(fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[55]++;
                    // start the next block at the next node
                    beginNodeIndex = i + 1;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[56]++;
                }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[57]++;
                break; default : CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[19]++;
            }
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[58]++;
int CodeCoverConditionCoverageHelper_C15;

        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((beginNodeIndex != statementNodes.length) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[20]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[59]++;
            FatBlock fb = newFatBlock(beginNodeIndex, statementNodes.length - 1);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[60]++;
int CodeCoverConditionCoverageHelper_C16;
            if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((statementNodes[beginNodeIndex].getType() == Token.TARGET) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[22]++;
                theTargetBlocks.put(statementNodes[beginNodeIndex], fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[61]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[23]++;}
            theBlocks.add(fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[62]++;

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[21]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[63]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[22]++;


int CodeCoverConditionCoverageHelper_C17;

        // build successor and predecessor links

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((i < theBlocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[22]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[23]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[24]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[64]++;
            FatBlock fb = (FatBlock)(theBlocks.get(i));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[65]++;

            Node blockEndNode = statementNodes[fb.realBlock.itsEndNodeIndex];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[66]++;
            int blockEndNodeType = blockEndNode.getType();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[67]++;
int CodeCoverConditionCoverageHelper_C18;

            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((blockEndNodeType != Token.GOTO) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i < (theBlocks.size() - 1)) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[24]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[68]++;
                FatBlock fallThruTarget = (FatBlock)(theBlocks.get(i + 1));
                fb.addSuccessor(fallThruTarget);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[69]++;
                fallThruTarget.addPredecessor(fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[70]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[25]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;


            if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && ((
(((CodeCoverConditionCoverageHelper_C19 |= (32)) == 0 || true) &&
 ((blockEndNodeType == Token.IFNE) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (16)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C19 |= (8)) == 0 || true) &&
 ((blockEndNodeType == Token.IFEQ) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (4)) == 0 || true)))
) || (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((blockEndNodeType == Token.GOTO) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 3) && false) ) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[26]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[72]++;
                Node target = ((Jump)blockEndNode).target;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[73]++;
                FatBlock branchTargetBlock = theTargetBlocks.get(target);
                target.putProp(Node.TARGETBLOCK_PROP, branchTargetBlock.realBlock);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[74]++;
                fb.addSuccessor(branchTargetBlock);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[75]++;
                branchTargetBlock.addPredecessor(fb);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[76]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[27]++;}
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[77]++;

        Block[] result = new Block[theBlocks.size()];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[78]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[25]++;


int CodeCoverConditionCoverageHelper_C20;

        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((i < theBlocks.size()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[25]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[26]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[27]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[79]++;
            FatBlock fb = (FatBlock)(theBlocks.get(i));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[80]++;
            Block b = fb.realBlock;
            b.itsSuccessors = fb.getSuccessors();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[81]++;
            b.itsPredecessors = fb.getPredecessors();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[82]++;
            b.itsBlockID = i;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[83]++;
            result[i] = b;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[84]++;
        }

        return result;
    }

    private static FatBlock newFatBlock(int startNodeIndex, int endNodeIndex)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[85]++;
        FatBlock fb = new FatBlock();
        fb.realBlock = new Block(startNodeIndex, endNodeIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[86]++;
        return fb;
    }

    private static String toString(Block[] blockList, Node[] statementNodes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[87]++;
int CodeCoverConditionCoverageHelper_C21;
        if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((DEBUG) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[28]++; return null;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[29]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[88]++;

        StringWriter sw = new StringWriter();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[89]++;
        PrintWriter pw = new PrintWriter(sw);

        pw.println(blockList.length + " Blocks");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[90]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[91]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[28]++;


int CodeCoverConditionCoverageHelper_C22;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((i < blockList.length) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[28]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[29]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[30]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[92]++;
            Block b = blockList[i];
            pw.println("#" + b.itsBlockID);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[93]++;
            pw.println("from " + b.itsStartNodeIndex
                    + " "
                    + statementNodes[b.itsStartNodeIndex].toString());
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[94]++;
            pw.println("thru " + b.itsEndNodeIndex
                    + " "
                    + statementNodes[b.itsEndNodeIndex].toString());
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[95]++;
            pw.print("Predecessors ");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[96]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[97]++;
int CodeCoverConditionCoverageHelper_C23;
            if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((b.itsPredecessors != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[30]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[98]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[31]++;


int CodeCoverConditionCoverageHelper_C24;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((j < b.itsPredecessors.length) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[31]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[32]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[33]++;
}
                    pw.print(b.itsPredecessors[j].itsBlockID + " ");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[99]++;
                }
                pw.println();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[100]++;

            } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[31]++;
                pw.println("none");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[101]++;
            }
            pw.print("Successors ");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[102]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[103]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((b.itsSuccessors != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[32]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[104]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[34]++;


int CodeCoverConditionCoverageHelper_C26;
                for (int j = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((j < b.itsSuccessors.length) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); j++) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[34]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[35]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[36]++;
}
                    pw.print(b.itsSuccessors[j].itsBlockID + " ");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[105]++;
                }
                pw.println();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[106]++;

            } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[33]++;
                pw.println("none");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[107]++;
            }
        }
        return sw.toString();
    }

    private static void reachingDefDataFlow(OptFunctionNode fn, Node[] statementNodes,
                                            Block theBlocks[], int[] varTypes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[108]++;
byte CodeCoverTryBranchHelper_L13 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[37]++;


int CodeCoverConditionCoverageHelper_C27;
/*
    initialize the liveOnEntry and liveOnExit sets, then discover the variables
    that are def'd by each function, and those that are used before being def'd
    (hence liveOnEntry)
*/
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((i < theBlocks.length) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L13 == 0) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[37]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[38]++;
} else if (CodeCoverTryBranchHelper_L13 == 1) {
  CodeCoverTryBranchHelper_L13++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[38]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[39]++;
}
            theBlocks[i].initLiveOnEntrySets(fn, statementNodes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[109]++;
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[110]++;
/*
    this visits every block starting at the last, re-adding the predecessors of
    any block whose inputs change as a result of the dataflow.
    REMIND, better would be to visit in CFG postorder
*/
        boolean visit[] = new boolean[theBlocks.length];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[111]++;
        boolean doneOnce[] = new boolean[theBlocks.length];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[112]++;
        int vIndex = theBlocks.length - 1;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[113]++;
        boolean needRescan = false;
        visit[vIndex] = true;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[114]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[115]++;
byte CodeCoverTryBranchHelper_L14 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[40]++;


        while (true) {
if (CodeCoverTryBranchHelper_L14 == 0) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[40]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[41]++;
} else if (CodeCoverTryBranchHelper_L14 == 1) {
  CodeCoverTryBranchHelper_L14++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[41]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[42]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[116]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (8)) == 0 || true) &&
 ((visit[vIndex]) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((doneOnce[vIndex]) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[34]++;
                doneOnce[vIndex] = true;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[117]++;
                visit[vIndex] = false;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[118]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[119]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((theBlocks[vIndex].doReachedUseDataFlow()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[36]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[120]++;
                    Block pred[] = theBlocks[vIndex].itsPredecessors;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[121]++;
int CodeCoverConditionCoverageHelper_C31;
                    if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((pred != null) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[38]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[122]++;
byte CodeCoverTryBranchHelper_L15 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[43]++;


int CodeCoverConditionCoverageHelper_C32;
                        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((i < pred.length) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L15 == 0) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[43]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[44]++;
} else if (CodeCoverTryBranchHelper_L15 == 1) {
  CodeCoverTryBranchHelper_L15++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[44]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[45]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[123]++;
                            int index = pred[i].itsBlockID;
                            visit[index] = true;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[124]++;
                            needRescan |= (index > vIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[125]++;
                        }

                    } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[39]++;}

                } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[37]++;}

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[35]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[126]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((vIndex == 0) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[40]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[127]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((needRescan) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[42]++;
                    vIndex = theBlocks.length - 1;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[128]++;
                    needRescan = false;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[129]++;

                } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[43]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[130]++;
                    break;
                }

            } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[41]++;
                vIndex--;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[131]++;
            }
        }
/*
        if any variable is live on entry to block 0, we have to mark it as
        not jRegable - since it means that someone is trying to access the
        'undefined'-ness of that variable.
*/

        theBlocks[0].markAnyTypeVariables(varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[132]++;
    }

    private static void typeFlow(OptFunctionNode fn, Node[] statementNodes,
                                 Block theBlocks[], int[] varTypes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[133]++;
        boolean visit[] = new boolean[theBlocks.length];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[134]++;
        boolean doneOnce[] = new boolean[theBlocks.length];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[135]++;
        int vIndex = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[136]++;
        boolean needRescan = false;
        visit[vIndex] = true;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[137]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[138]++;
byte CodeCoverTryBranchHelper_L16 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[46]++;


        while (true) {
if (CodeCoverTryBranchHelper_L16 == 0) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[46]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[47]++;
} else if (CodeCoverTryBranchHelper_L16 == 1) {
  CodeCoverTryBranchHelper_L16++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[47]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[48]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[139]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((visit[vIndex]) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 || !
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((doneOnce[vIndex]) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[44]++;
                doneOnce[vIndex] = true;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[140]++;
                visit[vIndex] = false;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[141]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[142]++;
int CodeCoverConditionCoverageHelper_C37;
                if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((theBlocks[vIndex].doTypeFlow(fn, statementNodes, varTypes)) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false))
                {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[46]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[143]++;
                    Block succ[] = theBlocks[vIndex].itsSuccessors;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[144]++;
int CodeCoverConditionCoverageHelper_C38;
                    if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((succ != null) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[48]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[145]++;
byte CodeCoverTryBranchHelper_L17 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[49]++;


int CodeCoverConditionCoverageHelper_C39;
                        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((i < succ.length) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L17 == 0) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[49]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[50]++;
} else if (CodeCoverTryBranchHelper_L17 == 1) {
  CodeCoverTryBranchHelper_L17++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[50]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[51]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[146]++;
                            int index = succ[i].itsBlockID;
                            visit[index] = true;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[147]++;
                            needRescan |= (index < vIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[148]++;
                        }

                    } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[49]++;}

                } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[47]++;}

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[45]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[149]++;
int CodeCoverConditionCoverageHelper_C40;
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((vIndex == (theBlocks.length - 1)) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[50]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[150]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((needRescan) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[52]++;
                    vIndex = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[151]++;
                    needRescan = false;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[152]++;

                } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[53]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[153]++;
                    break;
                }

            } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[51]++;
                vIndex++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[154]++;
            }
        }
    }

    private static boolean assignType(int[] varTypes, int index, int type)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[155]++;
        int prev = varTypes[index];
        return prev != (varTypes[index] |= type);
    }

    private void markAnyTypeVariables(int[] varTypes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[156]++;
byte CodeCoverTryBranchHelper_L18 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[52]++;


int CodeCoverConditionCoverageHelper_C42;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((i != varTypes.length) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L18 == 0) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[52]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[53]++;
} else if (CodeCoverTryBranchHelper_L18 == 1) {
  CodeCoverTryBranchHelper_L18++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[53]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[54]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[157]++;
int CodeCoverConditionCoverageHelper_C43;
            if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((itsLiveOnEntrySet.get(i)) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[54]++;
                assignType(varTypes, i, Optimizer.AnyType);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[158]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[55]++;}
        }

    }

    /*
        We're tracking uses and defs - in order to
        build the def set and to identify the last use
        nodes.

        The itsNotDefSet is built reversed then flipped later.

    */
    private void lookForVariableAccess(OptFunctionNode fn, Node n)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[159]++;
        switch (n.getType()) {
            case Token.TYPEOFNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[56]++;
            {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[160]++;
                // TYPEOFNAME may be used with undefined names, which is why
                // this is handled separately from GETVAR above.
                int varIndex = fn.fnode.getIndexForNameNode(n);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[161]++;
int CodeCoverConditionCoverageHelper_C44;
                if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (8)) == 0 || true) &&
 ((varIndex > -1) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((itsNotDefSet.get(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 2) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[57]++;
                    itsUseBeforeDefSet.set(varIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[162]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[58]++;}
            }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[163]++;
            break;
            case Token.DEC :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[59]++;
            case Token.INC :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[60]++;
            {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[164]++;
                Node child = n.getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[165]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((child.getType() == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[61]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[166]++;
                    int varIndex = fn.getVarIndex(child);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[167]++;
int CodeCoverConditionCoverageHelper_C46;
                    if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((itsNotDefSet.get(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[63]++;
                        itsUseBeforeDefSet.set(varIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[168]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[64]++;}
                    itsNotDefSet.set(varIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[169]++;

                } else {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[62]++;
                    lookForVariableAccess(fn, child);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[170]++;
                }
            }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[171]++;
            break;
            case Token.SETVAR :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[65]++;
            {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[172]++;
                Node lhs = n.getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[173]++;
                Node rhs = lhs.getNext();
                lookForVariableAccess(fn, rhs);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[174]++;
                itsNotDefSet.set(fn.getVarIndex(n));
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[175]++;
            }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[176]++;
            break;
            case Token.GETVAR :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[66]++;
            {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[177]++;
                int varIndex = fn.getVarIndex(n);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[178]++;
int CodeCoverConditionCoverageHelper_C47;
                if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((itsNotDefSet.get(varIndex)) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[67]++;
                    itsUseBeforeDefSet.set(varIndex);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[179]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[68]++;}
            }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[180]++;
            break;
            default :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[69]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[181]++;
                Node child = n.getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[182]++;
byte CodeCoverTryBranchHelper_L19 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[55]++;


int CodeCoverConditionCoverageHelper_C48;
                while ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((child != null) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
if (CodeCoverTryBranchHelper_L19 == 0) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[55]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[56]++;
} else if (CodeCoverTryBranchHelper_L19 == 1) {
  CodeCoverTryBranchHelper_L19++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[56]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[57]++;
}
                    lookForVariableAccess(fn, child);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[183]++;
                    child = child.getNext();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[184]++;
                }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[185]++;
                break;
        }
    }

    /*
        build the live on entry/exit sets.
        Then walk the trees looking for defs/uses of variables
        and build the def and useBeforeDef sets.
    */
    private void initLiveOnEntrySets(OptFunctionNode fn, Node[] statementNodes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[186]++;
        int listLength = fn.getVarCount();
        itsUseBeforeDefSet = new BitSet(listLength);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[187]++;
        itsNotDefSet = new BitSet(listLength);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[188]++;
        itsLiveOnEntrySet = new BitSet(listLength);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[189]++;
        itsLiveOnExitSet = new BitSet(listLength);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[190]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[191]++;
byte CodeCoverTryBranchHelper_L20 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[58]++;


int CodeCoverConditionCoverageHelper_C49;
        for (int i = itsStartNodeIndex;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((i <= itsEndNodeIndex) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L20 == 0) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[58]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[59]++;
} else if (CodeCoverTryBranchHelper_L20 == 1) {
  CodeCoverTryBranchHelper_L20++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[59]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[60]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[192]++;
            Node n = statementNodes[i];
            lookForVariableAccess(fn, n);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[193]++;
        }
        itsNotDefSet.flip(0, listLength);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[194]++;         // truth in advertising
    }

    /*
        the liveOnEntry of each successor is the liveOnExit for this block.
        The liveOnEntry for this block is -
        liveOnEntry = liveOnExit - defsInThisBlock + useBeforeDefsInThisBlock

    */
    private boolean doReachedUseDataFlow()
    {
        itsLiveOnExitSet.clear();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[195]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[196]++;
int CodeCoverConditionCoverageHelper_C50;
        if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((itsSuccessors != null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[70]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[197]++;
byte CodeCoverTryBranchHelper_L21 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[61]++;


int CodeCoverConditionCoverageHelper_C51;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((i < itsSuccessors.length) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L21 == 0) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[61]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[62]++;
} else if (CodeCoverTryBranchHelper_L21 == 1) {
  CodeCoverTryBranchHelper_L21++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[62]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[63]++;
}
                itsLiveOnExitSet.or(itsSuccessors[i].itsLiveOnEntrySet);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[198]++;
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[71]++;}
        return updateEntrySet(itsLiveOnEntrySet, itsLiveOnExitSet,
                              itsUseBeforeDefSet, itsNotDefSet);
    }

    private boolean updateEntrySet(BitSet entrySet, BitSet exitSet,
                                   BitSet useBeforeDef, BitSet notDef) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[199]++;
        int card = entrySet.cardinality();
        entrySet.or(exitSet);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[200]++;
        entrySet.and(notDef);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[201]++;
        entrySet.or(useBeforeDef);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[202]++;
        return entrySet.cardinality() != card;
    }

    /*
        the type of an expression is relatively unknown. Cases we can be sure
        about are -
            Literals,
            Arithmetic operations - always return a Number
    */
    private static int findExpressionType(OptFunctionNode fn, Node n,
                                          int[] varTypes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[203]++;
        switch (n.getType()) {
            case Token.NUMBER:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[72]++;
                return Optimizer.NumberType;

            case Token.CALL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[73]++;
            case Token.NEW:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[74]++;
            case Token.REF_CALL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[75]++;
                return Optimizer.AnyType;

            case Token.GETELEM:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[76]++;
            case Token.GETPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[77]++;
            case Token.NAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[78]++;
            case Token.THIS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[79]++;
                return Optimizer.AnyType;

            case Token.GETVAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[80]++;
                return varTypes[fn.getVarIndex(n)];

            case Token.INC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[81]++;
            case Token.DEC:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[82]++;
            case Token.MUL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[83]++;
            case Token.DIV:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[84]++;
            case Token.MOD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[85]++;
            case Token.BITOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[86]++;
            case Token.BITXOR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[87]++;
            case Token.BITAND:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[88]++;
            case Token.BITNOT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[89]++;
            case Token.LSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[90]++;
            case Token.RSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[91]++;
            case Token.URSH:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[92]++;
            case Token.SUB:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[93]++;
            case Token.POS:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[94]++;
            case Token.NEG:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[95]++;
                return Optimizer.NumberType;

            case Token.VOID:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[96]++;
                // NYI: undefined type
                return Optimizer.AnyType;

            case Token.FALSE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[97]++;
            case Token.TRUE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[98]++;
            case Token.EQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[99]++;
            case Token.NE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[100]++;
            case Token.LT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[101]++;
            case Token.LE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[102]++;
            case Token.GT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[103]++;
            case Token.GE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[104]++;
            case Token.SHEQ:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[105]++;
            case Token.SHNE:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[106]++;
            case Token.NOT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[107]++;
            case Token.INSTANCEOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[108]++;
            case Token.IN:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[109]++;
            case Token.DEL_REF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[110]++;
            case Token.DELPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[111]++;
                // NYI: boolean type
                return Optimizer.AnyType;

            case Token.STRING:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[112]++;
            case Token.TYPEOF:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[113]++;
            case Token.TYPEOFNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[114]++;
                // NYI: string type
                return Optimizer.AnyType;

            case Token.NULL:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[115]++;
            case Token.REGEXP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[116]++;
            case Token.ARRAYCOMP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[117]++;
            case Token.ARRAYLIT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[118]++;
            case Token.OBJECTLIT:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[119]++;
                return Optimizer.AnyType; // XXX: actually, we know it's not
            // number, but no type yet for that

            case Token.ADD:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[120]++; {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[204]++;
                // if the lhs & rhs are known to be numbers, we can be sure that's
                // the result, otherwise it could be a string.
                Node child = n.getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[205]++;
                int lType = findExpressionType(fn, child, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[206]++;
                int rType = findExpressionType(fn, child.getNext(), varTypes);
                return lType | rType;    // we're not distinguishing strings yet
            }

            case Token.HOOK:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[121]++; {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[207]++;
                Node ifTrue = n.getFirstChild().getNext();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[208]++;
                Node ifFalse = ifTrue.getNext();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[209]++;
                int ifTrueType = findExpressionType(fn, ifTrue, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[210]++;
                int ifFalseType = findExpressionType(fn, ifFalse, varTypes);
                return ifTrueType | ifFalseType;
            }

            case Token.COMMA:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[122]++;
            case Token.SETVAR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[123]++;
            case Token.SETNAME:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[124]++;
            case Token.SETPROP:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[125]++;
            case Token.SETELEM:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[126]++;
                return findExpressionType(fn, n.getLastChild(), varTypes);

            case Token.AND:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[127]++;
            case Token.OR:
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[128]++; {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[211]++;
                Node child = n.getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[212]++;
                int lType = findExpressionType(fn, child, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[213]++;
                int rType = findExpressionType(fn, child.getNext(), varTypes);
                return lType | rType;
            } default : CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[129]++;
        }

        return Optimizer.AnyType;
    }

    private static boolean findDefPoints(OptFunctionNode fn, Node n,
                                         int[] varTypes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[214]++;
        boolean result = false;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[215]++;
        Node first = n.getFirstChild();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[216]++;
byte CodeCoverTryBranchHelper_L22 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[64]++;


int CodeCoverConditionCoverageHelper_C52;
        for (Node next = first;(((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false); next = next.getNext()) {
if (CodeCoverTryBranchHelper_L22 == 0) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[64]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[65]++;
} else if (CodeCoverTryBranchHelper_L22 == 1) {
  CodeCoverTryBranchHelper_L22++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[65]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[66]++;
}
            result |= findDefPoints(fn, next, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[217]++;
        }
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[218]++;
        switch (n.getType()) {
            case Token.DEC :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[130]++;
            case Token.INC :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[131]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[219]++;
int CodeCoverConditionCoverageHelper_C53;
                if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((first.getType() == Token.GETVAR) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[132]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[220]++;
                    // theVar is a Number now
                    int i = fn.getVarIndex(first);
                    result |= assignType(varTypes, i, Optimizer.NumberType);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[221]++;

                } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[133]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[222]++;
                break;
            case Token.SETVAR :
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[134]++; {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[223]++;
                Node rValue = first.getNext();
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[224]++;
                int theType = findExpressionType(fn, rValue, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[225]++;
                int i = fn.getVarIndex(n);
                result |= assignType(varTypes, i, theType);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[226]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[227]++;
                break;
            } default : CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[135]++;
        }
        return result;
    }

    private boolean doTypeFlow(OptFunctionNode fn, Node[] statementNodes,
                               int[] varTypes)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[228]++;
        boolean changed = false;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[229]++;
byte CodeCoverTryBranchHelper_L23 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[67]++;


int CodeCoverConditionCoverageHelper_C54;

        for (int i = itsStartNodeIndex;(((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((i <= itsEndNodeIndex) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L23 == 0) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[67]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[68]++;
} else if (CodeCoverTryBranchHelper_L23 == 1) {
  CodeCoverTryBranchHelper_L23++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[68]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[69]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[230]++;
            Node n = statementNodes[i];
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[231]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((n != null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[136]++;
                changed |= findDefPoints(fn, n, varTypes);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[232]++;

            } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[137]++;}
        }

        return changed;
    }

    private void printLiveOnEntrySet(OptFunctionNode fn)
    {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[233]++;
int CodeCoverConditionCoverageHelper_C56;
        if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((DEBUG) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[138]++;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[234]++;
byte CodeCoverTryBranchHelper_L24 = 0;
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[70]++;


int CodeCoverConditionCoverageHelper_C57;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((i < fn.getVarCount()) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false); i++) {
if (CodeCoverTryBranchHelper_L24 == 0) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[70]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[71]++;
} else if (CodeCoverTryBranchHelper_L24 == 1) {
  CodeCoverTryBranchHelper_L24++;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[71]--;
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.loops[72]++;
}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[235]++;
                String name = fn.fnode.getParamOrVarName(i);
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[236]++;
int CodeCoverConditionCoverageHelper_C58;
                if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((itsUseBeforeDefSet.get(i)) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[140]++;
                    System.out.println(name + " is used before def'd");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[237]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[141]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[238]++;
int CodeCoverConditionCoverageHelper_C59;
                if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((itsNotDefSet.get(i)) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[142]++;
                    System.out.println(name + " is not def'd");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[239]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[143]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[240]++;
int CodeCoverConditionCoverageHelper_C60;
                if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((itsLiveOnEntrySet.get(i)) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[144]++;
                    System.out.println(name + " is live on entry");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[241]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[145]++;}
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[242]++;
int CodeCoverConditionCoverageHelper_C61;
                if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((itsLiveOnExitSet.get(i)) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) || true)) || (CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 1) && false)) {
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[146]++;
                    System.out.println(name + " is live on exit");
CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[243]++;
} else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[147]++;}
            }

        } else {
  CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.branches[139]++;}
    }

    // all the Blocks that come immediately after this
    private Block[] itsSuccessors;
    // all the Blocks that come immediately before this
    private Block[] itsPredecessors;

    private int itsStartNodeIndex;       // the Node at the start of the block
    private int itsEndNodeIndex;         // the Node at the end of the block

    private int itsBlockID;               // a unique index for each block

    // reaching def bit sets -
    private BitSet itsLiveOnEntrySet;
    private BitSet itsLiveOnExitSet;
    private BitSet itsUseBeforeDefSet;
    private BitSet itsNotDefSet;

    static final boolean DEBUG = false;
  static {
    CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5.statements[244]++;
  }
    private static int debug_blockCount;

}

class CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5 ());
  }
    public static long[] statements = new long[245];
    public static long[] branches = new long[148];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[62];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.optimizer.RHINO-SRC-Block.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,3,1,1,1,1,1,1,1,1,0,2,1,1,1,1,1,0,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 61; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[73];

  public CodeCoverCoverageCounter$9m0i2o1yzvm0oa3bcqhycg67j6yppb5 () {
    super("org.mozilla.javascript.optimizer.RHINO-SRC-Block.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 244; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 147; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 61; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 72; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.optimizer.RHINO-SRC-Block.java");
      for (int i = 1; i <= 244; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 147; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 61; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 24; i++) {
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


