/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.xml.impl.xmlbeans;

import org.apache.xmlbeans.XmlCursor;

import java.util.*;


public class LogicalEquality
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.ping();
  }

    public static boolean nodesEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[1]++;
        boolean result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[2]++;
int CodeCoverConditionCoverageHelper_C1;

        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((xmlOne.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[1]++;
            xmlOne.toFirstContentToken();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[3]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[2]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;

        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((xmlTwo.isStartdoc()) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[3]++;
            xmlTwo.toFirstContentToken();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[5]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[4]++;}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;

        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((xmlOne.currentTokenType() == xmlTwo.currentTokenType()) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[5]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[7]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((xmlOne.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[7]++;
                // Both empty
                result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[8]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[8]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[9]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((xmlOne.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[9]++;
                result = attributesEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[10]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[10]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[11]++;
int CodeCoverConditionCoverageHelper_C6; if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((xmlOne.isText()) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[11]++;
                result = textNodesEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[12]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[12]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[13]++;
int CodeCoverConditionCoverageHelper_C7; if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((xmlOne.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[13]++;
                result = commentsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[14]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[14]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[15]++;
int CodeCoverConditionCoverageHelper_C8; if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((xmlOne.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[15]++;
                result = processingInstructionsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[16]++;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[16]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[17]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((xmlOne.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[17]++;
                // Compare root elements
                result = elementsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[18]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[18]++;}
}
}
}
}
}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[6]++;}

        return result;
    }

    private static boolean elementsEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[19]++;
        boolean result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[20]++;
int CodeCoverConditionCoverageHelper_C10;

        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((qnamesEqual(xmlOne.getName(), xmlTwo.getName())) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[19]++;
            result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[21]++;

        }
        else
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[20]++;
            // These filter out empty text nodes.
            nextToken(xmlOne);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[22]++;
            nextToken(xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[23]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[24]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[1]++;


int CodeCoverConditionCoverageHelper_C11;

            do
            {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[3]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[25]++;
int CodeCoverConditionCoverageHelper_C12;
                if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((xmlOne.currentTokenType() != xmlTwo.currentTokenType()) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[21]++;
                    // Not same token
                    result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[26]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[27]++;
                    break;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[22]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[28]++;
int CodeCoverConditionCoverageHelper_C13; if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((xmlOne.isEnd()) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[23]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[29]++;
                    // Done with this element, step over end
                    break;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[24]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[30]++;
int CodeCoverConditionCoverageHelper_C14; if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((xmlOne.isEnddoc()) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[25]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[31]++;
                    // Shouldn't get here
                    break;

                }
                else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[26]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[32]++;
int CodeCoverConditionCoverageHelper_C15; if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((xmlOne.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[27]++;
                    // This one will move us to the first non-attr token.
                    result = attributeListsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[33]++;

                }
                else
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[28]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[34]++;
int CodeCoverConditionCoverageHelper_C16;
                    if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((xmlOne.isText()) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false))
                    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[29]++;
                        result = textNodesEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[35]++;

                    }
                    else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[30]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[36]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((xmlOne.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false))
                    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[31]++;
                        result = commentsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[37]++;

                    }
                    else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[32]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[38]++;
int CodeCoverConditionCoverageHelper_C18; if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((xmlOne.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false))
                    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[33]++;
                        result = processingInstructionsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[39]++;

                    }
                    else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[34]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[40]++;
int CodeCoverConditionCoverageHelper_C19; if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((xmlOne.isStart()) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false))
                    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[35]++;
                        result = elementsEqual(xmlOne, xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[41]++;

                    }
                    else
                    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[36]++;
                        //XML.log("Unknown token type" + xmlOne.currentTokenType());
                    }
}
}
}

                    // These filter out empty text nodes.
                    nextToken(xmlOne);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[42]++;
                    nextToken(xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[43]++;
                }
}
}
}
            }
            while((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false));
        }

        return result;
    }

    /**
     *
     * @param xmlOne
     * @param xmlTwo
     * @return
     */
    private static boolean attributeListsEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[44]++;
        boolean result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[45]++;
        TreeMap mapOne = loadAttributeMap(xmlOne);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[46]++;
        TreeMap mapTwo = loadAttributeMap(xmlTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[47]++;
int CodeCoverConditionCoverageHelper_C20;

        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((mapOne.size() != mapTwo.size()) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[37]++;
            result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[48]++;

        }
        else
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[38]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[49]++;
            Set keysOne = mapOne.keySet();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[50]++;
            Set keysTwo = mapTwo.keySet();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[51]++;
            Iterator itOne = keysOne.iterator();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[52]++;
            Iterator itTwo = keysTwo.iterator();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[53]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[4]++;


int CodeCoverConditionCoverageHelper_C21;

            while ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (8)) == 0 || true) &&
 ((result) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((itOne.hasNext()) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 2) && false))
            {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[4]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[5]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[6]++;
}
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[54]++;
                String valueOne = (String) itOne.next();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[55]++;
                String valueTwo = (String) itTwo.next();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[56]++;
int CodeCoverConditionCoverageHelper_C22;

                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((valueOne.equals(valueTwo)) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[39]++;
                    result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[57]++;

                }
                else
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[40]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[58]++;
                    javax.xml.namespace.QName qnameOne = (javax.xml.namespace.QName) mapOne.get(valueOne);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[59]++;
                    javax.xml.namespace.QName qnameTwo = (javax.xml.namespace.QName) mapTwo.get(valueTwo);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[60]++;
int CodeCoverConditionCoverageHelper_C23;

                    if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((qnamesEqual(qnameOne, qnameTwo)) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false))
                    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[41]++;
                        result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[61]++;

                    } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[42]++;}
                }
            }
        }

        return result;
    }

    /**
     *
     * @param xml
     * @return
     */
    private static TreeMap loadAttributeMap(XmlCursor xml)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[62]++;
        TreeMap result = new TreeMap();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[63]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[7]++;


int CodeCoverConditionCoverageHelper_C24;

        while ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((xml.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false))
        {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[7]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[8]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[9]++;
}
            result.put(xml.getTextValue(), xml.getName());
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[64]++;
            nextToken(xml);
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[65]++;
        }

        return result;
    }

    /**
     *
     * @param xmlOne
     * @param xmlTwo
     * @return
     */
    private static boolean attributesEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[66]++;
        boolean result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[67]++;
int CodeCoverConditionCoverageHelper_C25;

        if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((xmlOne.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((xmlTwo.isAttr()) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[43]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[68]++;
int CodeCoverConditionCoverageHelper_C26;
            if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((qnamesEqual(xmlOne.getName(), xmlTwo.getName())) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[45]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[69]++;
int CodeCoverConditionCoverageHelper_C27;
                if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((xmlOne.getTextValue().equals(xmlTwo.getTextValue())) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[47]++;
                    result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[70]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[48]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[46]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[44]++;}

        return result;
    }

    /**
     *
     * @param xmlOne
     * @param xmlTwo
     * @return
     */
    private static boolean textNodesEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[71]++;
        boolean result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[72]++;
int CodeCoverConditionCoverageHelper_C28;

        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((xmlOne.isText()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((xmlTwo.isText()) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[49]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[73]++;
int CodeCoverConditionCoverageHelper_C29;
            if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((xmlOne.getChars().equals(xmlTwo.getChars())) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[51]++;
                result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[74]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[52]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[50]++;}

        return result;
    }

    /**
     *
     * @param xmlOne
     * @param xmlTwo
     * @return
     */
    private static boolean commentsEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[75]++;
        boolean result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[76]++;
int CodeCoverConditionCoverageHelper_C30;

        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((xmlOne.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((xmlTwo.isComment()) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[53]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[77]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((xmlOne.getTextValue().equals(xmlTwo.getTextValue())) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[55]++;
                result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[78]++;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[56]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[54]++;}

        return result;
    }

    /**
     *
     * @param xmlOne
     * @param xmlTwo
     * @return
     */
    private static boolean processingInstructionsEqual(XmlCursor xmlOne, XmlCursor xmlTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[79]++;
        boolean result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[80]++;
int CodeCoverConditionCoverageHelper_C32;

        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((xmlOne.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((xmlTwo.isProcinst()) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[57]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[81]++;
int CodeCoverConditionCoverageHelper_C33;
            if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((qnamesEqual(xmlOne.getName(), xmlTwo.getName())) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[59]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[82]++;
int CodeCoverConditionCoverageHelper_C34;
                if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((xmlOne.getTextValue().equals(xmlTwo.getTextValue())) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false))
                {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[61]++;
                    result = true;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[83]++;

                } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[62]++;}

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[60]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[58]++;}

        return result;
    }

    /**
     *
     * @param qnameOne
     * @param qnameTwo
     * @return
     */
    private static boolean qnamesEqual(javax.xml.namespace.QName qnameOne, javax.xml.namespace.QName qnameTwo)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[84]++;
        boolean result = false;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[85]++;
int CodeCoverConditionCoverageHelper_C35;

        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((qnameOne.getNamespaceURI().equals(qnameTwo.getNamespaceURI())) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[63]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[86]++;
int CodeCoverConditionCoverageHelper_C36;
            if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((qnameOne.getLocalPart().equals(qnameTwo.getLocalPart())) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[65]++;
                return true;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[66]++;}

        } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[64]++;}

        return result;
    }

    /**
     * filter out empty textNodes here
     *
     * @param xml
     */
    private static void nextToken(XmlCursor xml)
    {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[87]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[10]++;


        do
        {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[10]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[11]--;
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.loops[12]++;
}
            xml.toNextToken();
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[88]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[89]++;
int CodeCoverConditionCoverageHelper_C38;

            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((xml.isText()) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[67]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[90]++;
                // Not a text node
                break;

            }
            else {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[68]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[91]++;
int CodeCoverConditionCoverageHelper_C39; if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((xml.getChars().trim().length() > 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false))
            {
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[69]++;
CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.statements[92]++;
                // Text node is not empty
                break;

            } else {
  CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl.branches[70]++;}
}
        }
        while (true);
    }
}

class CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl ());
  }
    public static long[] statements = new long[93];
    public static long[] branches = new long[71];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[40];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-LogicalEquality.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,2,1,1,2,1,2,1,2,1,1,1,1,0,1,1};
    for (int i = 1; i <= 39; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[13];

  public CodeCoverCoverageCounter$1gk5ffks5uqdq4dcq2t9mofc4ekt5faxxatw96x490o14rl () {
    super("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-LogicalEquality.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 92; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 70; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 12; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.xml.impl.xmlbeans.RHINO-DEP-LogicalEquality.java");
      for (int i = 1; i <= 92; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 70; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 39; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 4; i++) {
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

