/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
Implementation of resizable array with focus on minimizing memory usage by storing few initial array elements in object fields. Can also be used as a stack.
*/

public class ObjArray implements Serializable
{
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.ping();
  }

    static final long serialVersionUID = 4174889037736658296L;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[1]++;
  }

    public ObjArray() { }

    public final boolean isSealed()
    {
        return sealed;
    }

    public final void seal()
    {
        sealed = true;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[2]++;
    }

    public final boolean isEmpty()
    {
        return size == 0;
    }

    public final int size()
    {
        return size;
    }

    public final void setSize(int newSize)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((newSize < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[1]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[2]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[4]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[3]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[4]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[5]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[6]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((newSize < N) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[5]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[7]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[1]++;


int CodeCoverConditionCoverageHelper_C4;
            for (int i = newSize;(((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[1]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[2]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[3]++;
}
                setImpl(i, null);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[8]++;
            }

        } else {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[6]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[9]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((newSize > N) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[7]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[10]++;
int CodeCoverConditionCoverageHelper_C6;
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((newSize > FIELDS_STORE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[9]++;
                ensureCapacity(newSize);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[11]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[10]++;}

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[8]++;}
}
        size = newSize;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[12]++;
    }

    public final Object get(int index)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[13]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((index < size) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[11]++; throw onInvalidIndex(index, size);
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[12]++;}
        return getImpl(index);
    }

    public final void set(int index, Object value)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[14]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((index < size) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[13]++; throw onInvalidIndex(index, size);
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[14]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[15]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[15]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[16]++;}
        setImpl(index, value);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[16]++;
    }

    private Object getImpl(int index)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[17]++;
        switch (index) {
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[17]++; return f0;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[18]++; return f1;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[19]++; return f2;
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[20]++; return f3;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[21]++; return f4; default : CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[22]++;
        }
        return data[index - FIELDS_STORE_SIZE];
    }

    private void setImpl(int index, Object value)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[18]++;
        switch (index) {
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[23]++; f0 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[19]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[20]++; break;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[24]++; f1 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[21]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[22]++; break;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[25]++; f2 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[23]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[24]++; break;
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[26]++; f3 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[25]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[26]++; break;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[27]++; f4 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[27]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[28]++; break;
            default:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[28]++; data[index - FIELDS_STORE_SIZE] = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[29]++;
        }

    }

    public int indexOf(Object obj)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[30]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[31]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[4]++;


int CodeCoverConditionCoverageHelper_C10;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[4]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[5]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[6]++;
}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[32]++;
            Object current = getImpl(i);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[33]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (32)) == 0 || true) &&
 ((current == obj) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C11 |= (8)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((current.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 3) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[29]++;
                return i;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[30]++;}
        }
        return -1;
    }

    public int lastIndexOf(Object obj)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[34]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[7]++;


int CodeCoverConditionCoverageHelper_C12;
        for (int i = size;(((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false);) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[7]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[8]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[9]++;
}
            --i;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[35]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[36]++;
            Object current = getImpl(i);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[37]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (32)) == 0 || true) &&
 ((current == obj) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C13 |= (8)) == 0 || true) &&
 ((current != null) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((current.equals(obj)) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 3) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[31]++;
                return i;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[32]++;}
        }
        return -1;
    }

    public final Object peek()
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[38]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[39]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[33]++; throw onEmptyStackTopRead();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[34]++;}
        return getImpl(N - 1);
    }

    public final Object pop()
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[40]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[35]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[36]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[41]++;
        int N = size;
        --N;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[42]++;
        Object top;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[43]++;
        switch (N) {
            case -1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[37]++; throw onEmptyStackTopRead();
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[38]++; top = f0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[44]++; f0 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[45]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[46]++; break;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[39]++; top = f1;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[47]++; f1 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[48]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[49]++; break;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[40]++; top = f2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[50]++; f2 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[51]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[52]++; break;
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[41]++; top = f3;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[53]++; f3 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[54]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[55]++; break;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[42]++; top = f4;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[56]++; f4 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[57]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[58]++; break;
            default:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[43]++;
                top = data[N - FIELDS_STORE_SIZE];
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[59]++;
                data[N - FIELDS_STORE_SIZE] = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[60]++;
        }
        size = N;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[61]++;
        return top;
    }

    public final void push(Object value)
    {
        add(value);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[62]++;
    }

    public final void add(Object value)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[63]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[44]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[45]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[64]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[65]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((N >= FIELDS_STORE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[46]++;
            ensureCapacity(N + 1);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[66]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[47]++;}
        size = N + 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[67]++;
        setImpl(N, value);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[68]++;
    }

    public final void add(int index, Object value)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[69]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[70]++;
int CodeCoverConditionCoverageHelper_C18;
        if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C18 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((index <= N) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[48]++; throw onInvalidIndex(index, N + 1);
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[49]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[71]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[50]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[51]++;}
        Object tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[72]++;
        switch (index) {
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[52]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[73]++;
int CodeCoverConditionCoverageHelper_C20;
                if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[53]++; f0 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[74]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[75]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[54]++;}
                tmp = f0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[76]++; f0 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[77]++; value = tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[78]++;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[55]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[79]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((N == 1) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[56]++; f1 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[80]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[81]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[57]++;}
                tmp = f1;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[82]++; f1 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[83]++; value = tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[84]++;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[58]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[85]++;
int CodeCoverConditionCoverageHelper_C22;
                if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((N == 2) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[59]++; f2 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[86]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[87]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[60]++;}
                tmp = f2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[88]++; f2 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[89]++; value = tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[90]++;
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[61]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[91]++;
int CodeCoverConditionCoverageHelper_C23;
                if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((N == 3) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[62]++; f3 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[92]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[93]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[63]++;}
                tmp = f3;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[94]++; f3 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[95]++; value = tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[96]++;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[64]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[97]++;
int CodeCoverConditionCoverageHelper_C24;
                if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((N == 4) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[65]++; f4 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[98]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[99]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[66]++;}
                tmp = f4;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[100]++; f4 = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[101]++; value = tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[102]++;

                index = FIELDS_STORE_SIZE;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[103]++;
            default:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[67]++;
                ensureCapacity(N + 1);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[104]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[105]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((index != N) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[68]++;
                    System.arraycopy(data, index - FIELDS_STORE_SIZE,
                                     data, index - FIELDS_STORE_SIZE + 1,
                                     N - index);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[106]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[69]++;}
                data[index - FIELDS_STORE_SIZE] = value;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[107]++;
        }
        size = N + 1;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[108]++;
    }

    public final void remove(int index)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[109]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[110]++;
int CodeCoverConditionCoverageHelper_C26;
        if ((((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C26 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((index < N) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 2) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[70]++; throw onInvalidIndex(index, N);
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[71]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[111]++;
int CodeCoverConditionCoverageHelper_C27;
        if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[72]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[73]++;}
        --N;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[112]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[113]++;
        switch (index) {
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[74]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[114]++;
int CodeCoverConditionCoverageHelper_C28;
                if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((N == 0) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[75]++; f0 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[115]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[116]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[76]++;}
                f0 = f1;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[117]++;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[77]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[118]++;
int CodeCoverConditionCoverageHelper_C29;
                if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((N == 1) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[78]++; f1 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[119]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[120]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[79]++;}
                f1 = f2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[121]++;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[80]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[122]++;
int CodeCoverConditionCoverageHelper_C30;
                if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((N == 2) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[81]++; f2 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[123]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[124]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[82]++;}
                f2 = f3;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[125]++;
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[83]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[126]++;
int CodeCoverConditionCoverageHelper_C31;
                if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((N == 3) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[84]++; f3 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[127]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[128]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[85]++;}
                f3 = f4;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[129]++;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[86]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[130]++;
int CodeCoverConditionCoverageHelper_C32;
                if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((N == 4) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[87]++; f4 = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[131]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[132]++; break;
 } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[88]++;}
                f4 = data[0];
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[133]++;

                index = FIELDS_STORE_SIZE;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[134]++;
            default:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[89]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[135]++;
int CodeCoverConditionCoverageHelper_C33;
                if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((index != N) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[90]++;
                    System.arraycopy(data, index - FIELDS_STORE_SIZE + 1,
                                     data, index - FIELDS_STORE_SIZE,
                                     N - index);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[136]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[91]++;}
                data[N - FIELDS_STORE_SIZE] = null;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[137]++;
        }
        size = N;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[138]++;
    }

    public final void clear()
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[139]++;
int CodeCoverConditionCoverageHelper_C34;
        if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[92]++; throw onSeledMutation();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[93]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[140]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[141]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[10]++;


int CodeCoverConditionCoverageHelper_C35;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[10]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[11]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[12]++;
}
            setImpl(i, null);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[142]++;
        }
        size = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[143]++;
    }

    public final Object[] toArray()
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[144]++;
        Object[] array = new Object[size];
        toArray(array, 0);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[145]++;
        return array;
    }

    public final void toArray(Object[] array)
    {
        toArray(array, 0);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[146]++;
    }

    public final void toArray(Object[] array, int offset)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[147]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[148]++;
        switch (N) {
            default:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[94]++;
                System.arraycopy(data, 0, array, offset + FIELDS_STORE_SIZE,
                                 N - FIELDS_STORE_SIZE);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[149]++;
            case 5:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[95]++; array[offset + 4] = f4;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[150]++;
            case 4:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[96]++; array[offset + 3] = f3;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[151]++;
            case 3:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[97]++; array[offset + 2] = f2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[152]++;
            case 2:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[98]++; array[offset + 1] = f1;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[153]++;
            case 1:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[99]++; array[offset + 0] = f0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[154]++;
            case 0:
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[100]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[155]++; break;
        }
    }

    private void ensureCapacity(int minimalCapacity)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[156]++;
        int required = minimalCapacity - FIELDS_STORE_SIZE;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[157]++;
int CodeCoverConditionCoverageHelper_C36;
        if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((required <= 0) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[101]++; throw new IllegalArgumentException();
} else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[102]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[158]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((data == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[103]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[159]++;
            int alloc = FIELDS_STORE_SIZE * 2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[160]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((alloc < required) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[105]++;
                alloc = required;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[161]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[106]++;}
            data = new Object[alloc];
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[162]++;

        } else {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[104]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[163]++;
            int alloc = data.length;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[164]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((alloc < required) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[107]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[165]++;
int CodeCoverConditionCoverageHelper_C40;
                   if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((alloc <= FIELDS_STORE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[109]++;
                    alloc = FIELDS_STORE_SIZE * 2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[166]++;

                } else {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[110]++;
                    alloc *= 2;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[167]++;
                }
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[168]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((alloc < required) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[111]++;
                    alloc = required;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[169]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[112]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[170]++;
                Object[] tmp = new Object[alloc];
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[171]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((size > FIELDS_STORE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[113]++;
                    System.arraycopy(data, 0, tmp, 0,
                                     size - FIELDS_STORE_SIZE);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[172]++;

                } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[114]++;}
                data = tmp;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[173]++;

            } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[108]++;}
        }
    }

    private static RuntimeException onInvalidIndex(int index, int upperBound)
    {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[174]++;
        // \u2209 is "NOT ELEMENT OF"
        String msg = index+" \u2209 [0, "+upperBound+')';
        throw new IndexOutOfBoundsException(msg);
    }

    private static RuntimeException onEmptyStackTopRead()
    {
        throw new RuntimeException("Empty stack");
    }

    private static RuntimeException onSeledMutation()
    {
        throw new IllegalStateException("Attempt to modify sealed array");
    }

    private void writeObject(ObjectOutputStream os) throws IOException
    {
        os.defaultWriteObject();
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[175]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[176]++;
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[177]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[13]++;


int CodeCoverConditionCoverageHelper_C43;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[13]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[14]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[15]++;
}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[178]++;
            Object obj = getImpl(i);
            os.writeObject(obj);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[179]++;
        }
    }

    private void readObject(ObjectInputStream is)
        throws IOException, ClassNotFoundException
    {
        is.defaultReadObject();
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[180]++;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[181]++; // It reads size
        int N = size;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[182]++;
int CodeCoverConditionCoverageHelper_C44;
        if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((N > FIELDS_STORE_SIZE) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[115]++;
            data = new Object[N - FIELDS_STORE_SIZE];
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[183]++;

        } else {
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.branches[116]++;}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[184]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[16]++;


int CodeCoverConditionCoverageHelper_C45;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) || true)) || (CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[16]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[17]--;
  CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.loops[18]++;
}
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[185]++;
            Object obj = is.readObject();
            setImpl(i, obj);
CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[186]++;
        }
    }

// Number of data elements
    private int size;

    private boolean sealed;

    private static final int FIELDS_STORE_SIZE = 5;
  static {
    CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t.statements[187]++;
  }
    private transient Object f0, f1, f2, f3, f4;
    private transient Object[] data;
}

class CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t ());
  }
    public static long[] statements = new long[188];
    public static long[] branches = new long[117];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[46];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ObjArray.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,2,2,1,1,3,1,3,1,1,1,1,2,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 45; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[19];

  public CodeCoverCoverageCounter$2o08iygcpwmc6w26bg8ds2502111tvqe155t () {
    super("org.mozilla.javascript.RHINO-SRC-ObjArray.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 187; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 116; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 18; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ObjArray.java");
      for (int i = 1; i <= 187; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 116; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 45; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 6; i++) {
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

