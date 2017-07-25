/* -*- Mode: java; tab-width: 4; indent-tabs-mode: 1; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.javascript.tools.idswitch;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileBody {
  static {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.ping();
  }


    private static class ReplaceItem {
        ReplaceItem next;
        int begin;
        int end;
        String replacement;

        ReplaceItem(int begin, int end, String text) {
            this.begin = begin;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[1]++;
            this.end = end;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[2]++;
            this.replacement = text;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[3]++;
        }
    }

    private char[] buffer = new char[1 << 14];
  {
    CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[4]++;
  } // 16K
    private int bufferEnd;
    private int lineBegin;
    private int lineEnd;
    private int nextLineStart;

    private int lineNumber;

    ReplaceItem firstReplace;
    ReplaceItem lastReplace;


    public char[] getBuffer() { return buffer; }

    public void readData(Reader r) throws IOException {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[5]++;
        int capacity = buffer.length;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[6]++;
        int offset = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[1]++;


        for (;;) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[1]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[2]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[3]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[8]++;
            int n_read = r.read(buffer, offset, capacity - offset);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((n_read < 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[1]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[10]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[2]++;}
            offset += n_read;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[11]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((capacity == offset) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[3]++;
                capacity *= 2;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[13]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[14]++;
                char[] tmp = new char[capacity];
                System.arraycopy(buffer, 0, tmp, 0, offset);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[15]++;
                buffer = tmp;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[16]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[4]++;}
        }
        bufferEnd = offset;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[17]++;
    }

    public void writeInitialData(Writer w) throws IOException {
        w.write(buffer, 0, bufferEnd);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[18]++;
    }

    public void writeData(Writer w) throws IOException {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[19]++;
        int offset = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[20]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[4]++;


int CodeCoverConditionCoverageHelper_C4;
        for (ReplaceItem x = firstReplace;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((x != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); x = x.next) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[4]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[5]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[6]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[21]++;
            int before_replace = x.begin - offset;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[22]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((before_replace > 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[5]++;
                w.write(buffer, offset, before_replace);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[23]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[6]++;}
            w.write(x.replacement);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[24]++;
            offset = x.end;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[25]++;
        }
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[26]++;
        int tail = bufferEnd - offset;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[27]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((tail != 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[7]++;
            w.write(buffer, offset, tail);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[28]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[8]++;}
    }

    public boolean wasModified() { return firstReplace != null; }

    public boolean setReplacement(int begin, int end, String text) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[29]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((equals(text, buffer, begin, end)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[9]++; return false;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[10]++;}
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[30]++;

        ReplaceItem item = new ReplaceItem(begin, end, text);
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[31]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((firstReplace == null) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[11]++;
            firstReplace = lastReplace = item;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[32]++;

        }
        else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[12]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[33]++;
int CodeCoverConditionCoverageHelper_C9; if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((begin < firstReplace.begin) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[13]++;
            item.next = firstReplace;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[34]++;
            firstReplace = item;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[35]++;

        }
        else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[14]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[36]++;
            ReplaceItem cursor = firstReplace;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[37]++;
            ReplaceItem next = cursor.next;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[38]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[7]++;


int CodeCoverConditionCoverageHelper_C10;
            while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((next != null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[7]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[8]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[9]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[39]++;
int CodeCoverConditionCoverageHelper_C11;
                if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((begin < next.begin) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[15]++;
                    item.next = next;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[40]++;
                    cursor.next = item;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[41]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[42]++;
                    break;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[16]++;}
                cursor = next;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[43]++;
                next = next.next;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[44]++;
            }
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[45]++;
int CodeCoverConditionCoverageHelper_C12;
            if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((next == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[17]++;
                lastReplace.next = item;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[46]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[18]++;}
        }
}

        return true;
    }

    public int getLineNumber() { return lineNumber; }

    public int getLineBegin() { return lineBegin; }

    public int getLineEnd() { return lineEnd; }

    public void startLineLoop() {
        lineNumber = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[47]++;
        lineBegin = lineEnd = nextLineStart = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[48]++;
    }

    public boolean nextLine() {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[49]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((nextLineStart == bufferEnd) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[19]++;
            lineNumber = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[50]++; return false;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[20]++;}
        int i;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[51]++; int c = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[52]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[10]++;


int CodeCoverConditionCoverageHelper_C14;
        for (i = nextLineStart;(((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((i != bufferEnd) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[10]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[11]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[12]++;
}
            c = buffer[i];
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[53]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[54]++;
int CodeCoverConditionCoverageHelper_C15;
            if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (8)) == 0 || true) &&
 ((c == '\n') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[21]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[55]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[22]++;}
        }
        lineBegin = nextLineStart;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[56]++;
        lineEnd = i;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[57]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[58]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((i == bufferEnd) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[23]++;
            nextLineStart = i;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[59]++;

        }
        else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[24]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[60]++;
int CodeCoverConditionCoverageHelper_C17; if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (32)) == 0 || true) &&
 ((c == '\r') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (8)) == 0 || true) &&
 ((i + 1 != bufferEnd) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((buffer[i + 1] == '\n') && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 3) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[25]++;
            nextLineStart = i + 2;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[61]++;

        }
        else {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[26]++;
            nextLineStart = i + 1;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[62]++;
        }
}
        ++lineNumber;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[63]++;
        return true;
    }

    private static boolean equals(String str, char[] array, int begin, int end)
    {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[64]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((str.length() == end - begin) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[27]++;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[65]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[13]++;


int CodeCoverConditionCoverageHelper_C19;
            for (int i = begin, j = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((i != end) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); ++i, ++j) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[13]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[14]--;
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.loops[15]++;
}
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.statements[66]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((array[i] != str.charAt(j)) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[29]++; return false;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[30]++;}
            }
            return true;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx.branches[28]++;}
        return false;
    }

}

class CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx ());
  }
    public static long[] statements = new long[67];
    public static long[] branches = new long[31];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[21];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.idswitch.RHINO-TOO-FileBody.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,0,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,3,1,1,1};
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
    public static long[] loops = new long[16];

  public CodeCoverCoverageCounter$2o08iygcpwmr67f4g5fvsikrv7bc576o2kwx () {
    super("org.mozilla.javascript.tools.idswitch.RHINO-TOO-FileBody.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 66; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 30; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 20; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 15; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.tools.idswitch.RHINO-TOO-FileBody.java");
      for (int i = 1; i <= 66; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 30; i++) {
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



