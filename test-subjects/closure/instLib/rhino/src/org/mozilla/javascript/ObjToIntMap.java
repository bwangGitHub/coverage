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
 * Map to associate objects to integers.
 * The map does not synchronize any of its operation, so either use
 * it from a single thread or do own synchronization or perform all mutation
 * operations on one thread before passing the map to others
 *
 *
 */

public class ObjToIntMap implements Serializable
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.ping();
  }

    static final long serialVersionUID = -1542220580748809402L;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[1]++;
  }

// Map implementation via hashtable,
// follows "The Art of Computer Programming" by Donald E. Knuth

// ObjToIntMap is a copy cat of ObjToIntMap with API adjusted to object keys

    public static class Iterator {

        Iterator(ObjToIntMap master) {
            this.master = master;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[2]++;
        }

        final void init(Object[] keys, int[] values, int keyCount) {
            this.keys = keys;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[3]++;
            this.values = values;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[4]++;
            this.cursor = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[5]++;
            this.remaining = keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[6]++;
        }

        public void start() {
            master.initIterator(this);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[7]++;
            next();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[8]++;
        }

        public boolean done() {
            return remaining < 0;
        }

        public void next() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((remaining == -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[1]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[10]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[2]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[11]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((remaining == 0) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[3]++;
                remaining = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[12]++;
                cursor = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[13]++;

            }else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[4]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[14]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[1]++;


                for (++cursor; ; ++cursor) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[1]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[2]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[3]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[15]++;
                    Object key = keys[cursor];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
                    if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[5]++;
                        --remaining;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[17]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[18]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[6]++;}
                }
            }
        }

        public Object getKey() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[19]++;
            Object key = keys[cursor];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((key == UniqueTag.NULL_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[7]++; key = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[21]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[8]++;}
            return key;
        }

        public int getValue() {
            return values[cursor];
        }

        public void setValue(int value) {
            values[cursor] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[22]++;
        }

        ObjToIntMap master;
        private int cursor;
        private int remaining;
        private Object[] keys;
        private int[] values;
    }

    public ObjToIntMap() {
        this(4);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[23]++;
    }

    public ObjToIntMap(int keyCountHint) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[24]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((keyCountHint < 0) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[9]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[25]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[10]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[26]++;
        // Table grow when number of stored keys >= 3/4 of max capacity
        int minimalCapacity = keyCountHint * 4 / 3;
        int i;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[27]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[4]++;


int CodeCoverConditionCoverageHelper_C7;
        for (i = 2;(((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 (((1 << i) < minimalCapacity) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[4]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[5]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[6]++;
} }
        power = i;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[28]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[29]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((power < 2) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[11]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[30]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[12]++;}
    }

    public boolean isEmpty() {
        return keyCount == 0;
    }

    public int size() {
        return keyCount;
    }

    public boolean has(Object key) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[31]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[13]++; key = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[32]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[14]++;}
        return 0 <= findIndex(key);
    }

    /**
     * Get integer value assigned with key.
     * @return key integer value or defaultValue if key is absent
     */
    public int get(Object key, int defaultValue) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[33]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[15]++; key = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[34]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[16]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[35]++;
        int index = findIndex(key);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[36]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[17]++;
            return values[index];

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[18]++;}
        return defaultValue;
    }

    /**
     * Get integer value assigned with key.
     * @return key integer value
     * @throws RuntimeException if key does not exist
     */
    public int getExisting(Object key) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[37]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[19]++; key = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[38]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[20]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[39]++;
        int index = findIndex(key);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[40]++;
int CodeCoverConditionCoverageHelper_C13;
        if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[21]++;
            return values[index];

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[22]++;}
        // Key must exist
        Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[41]++;
        return 0;
    }

    public void put(Object key, int value) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[42]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[23]++; key = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[43]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[24]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[44]++;
        int index = ensureIndex(key);
        values[index] = value;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[45]++;
    }

    /**
     * If table already contains a key that equals to keyArg, return that key
     * while setting its value to zero, otherwise add keyArg with 0 value to
     * the table and return it.
     */
    public Object intern(Object keyArg) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[46]++;
        boolean nullKey = false;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[47]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((keyArg == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[25]++;
            nullKey = true;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[48]++;
            keyArg = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[49]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[26]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[50]++;
        int index = ensureIndex(keyArg);
        values[index] = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[51]++;
        return (nullKey) ? null : keys[index];
    }

    public void remove(Object key) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[52]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((key == null) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[27]++; key = UniqueTag.NULL_VALUE;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[53]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[28]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[54]++;
        int index = findIndex(key);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[55]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[29]++;
            keys[index] = DELETED;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[56]++;
            --keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[57]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[30]++;}
    }

    public void clear() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[58]++;
        int i = keys.length;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[59]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[7]++;


int CodeCoverConditionCoverageHelper_C18;
        while ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((i != 0) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[7]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[8]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[9]++;
}
            keys[--i] = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[60]++;
        }
        keyCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[61]++;
        occupiedCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[62]++;
    }

    public Iterator newIterator() {
        return new Iterator(this);
    }

    // The sole purpose of the method is to avoid accessing private fields
    // from the Iterator inner class to workaround JDK 1.1 compiler bug which
    // generates code triggering VerifierError on recent JVMs
    final void initIterator(Iterator i) {
        i.init(keys, values, keyCount);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[63]++;
    }

    /** Return array of present keys */
    public Object[] getKeys() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[64]++;
        Object[] array = new Object[keyCount];
        getKeys(array, 0);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[65]++;
        return array;
    }

    public void getKeys(Object[] array, int offset) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[66]++;
        int count = keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[67]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[10]++;


int CodeCoverConditionCoverageHelper_C19;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[10]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[11]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[12]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[68]++;
            Object key = keys[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[69]++;
int CodeCoverConditionCoverageHelper_C20;
            if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (8)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((key != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[31]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[70]++;
int CodeCoverConditionCoverageHelper_C21;
                if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((key == UniqueTag.NULL_VALUE) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[33]++; key = null;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[71]++;
 } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[34]++;}
                array[offset] = key;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[72]++;
                ++offset;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[73]++;
                --count;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[74]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[32]++;}
        }
    }

    private static int tableLookupStep(int fraction, int mask, int power) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[75]++;
        int shift = 32 - 2 * power;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[76]++;
int CodeCoverConditionCoverageHelper_C22;
        if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((shift >= 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[35]++;
            return ((fraction >>> shift) & mask) | 1;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[36]++;
            return (fraction & (mask >>> -shift)) | 1;
        }
    }

    private int findIndex(Object key) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[77]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[37]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[78]++;
            int hash = key.hashCode();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[79]++;
            int fraction = hash * A;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[80]++;
            int index = fraction >>> (32 - power);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[81]++;
            Object test = keys[index];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[82]++;
int CodeCoverConditionCoverageHelper_C24;
            if ((((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((test != null) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[39]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[83]++;
                int N = 1 << power;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[84]++;
int CodeCoverConditionCoverageHelper_C25;
                if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (32)) == 0 || true) &&
 ((test == key) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C25 |= (8)) == 0 || true) &&
 ((values[N + index] == hash) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((test.equals(key)) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 3) && false))
                {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[41]++;
                    return index;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[42]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[85]++;
                // Search in table after first failed attempt
                int mask = N - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[86]++;
                int step = tableLookupStep(fraction, mask, power);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[87]++;
                int n = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[88]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[13]++;


                for (;;) {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[13]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[14]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[15]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[89]++;
int CodeCoverConditionCoverageHelper_C27;
                    if ((((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[43]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[90]++;
int CodeCoverConditionCoverageHelper_C28;
                        if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((n >= occupiedCount) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[45]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[91]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[46]++;}
                        ++n;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[92]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[44]++;}
                    index = (index + step) & mask;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[93]++;
                    test = keys[index];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[94]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[95]++;
int CodeCoverConditionCoverageHelper_C29;
                    if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((test == null) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[47]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[96]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[48]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[97]++;
int CodeCoverConditionCoverageHelper_C30;
                    if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (32)) == 0 || true) &&
 ((test == key) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C30 |= (8)) == 0 || true) &&
 ((values[N + index] == hash) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((test.equals(key)) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 3) && false))
                    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[49]++;
                        return index;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[50]++;}
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[40]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[38]++;}
        return -1;
    }

// Insert key that is not present to table without deleted entries
// and enough free space
    private int insertNewKey(Object key, int hash) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[98]++;
int CodeCoverConditionCoverageHelper_C31;
        if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((occupiedCount != keyCount) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[51]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[99]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[52]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[100]++;
int CodeCoverConditionCoverageHelper_C32;
        if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((keyCount == 1 << power) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[53]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[101]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[54]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[102]++;
        int fraction = hash * A;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[103]++;
        int index = fraction >>> (32 - power);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[104]++;
        int N = 1 << power;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[105]++;
int CodeCoverConditionCoverageHelper_C33;
        if ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((keys[index] != null) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[55]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[106]++;
            int mask = N - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[107]++;
            int step = tableLookupStep(fraction, mask, power);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[108]++;
            int firstIndex = index;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[109]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[16]++;


int CodeCoverConditionCoverageHelper_C34;
            do {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[16]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[17]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[18]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[110]++;
int CodeCoverConditionCoverageHelper_C35;
                if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((keys[index] == DELETED) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[57]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[111]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[58]++;}
                index = (index + step) & mask;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[112]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[113]++;
int CodeCoverConditionCoverageHelper_C36;
                if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((firstIndex == index) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[59]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[114]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[60]++;}
            } while ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((keys[index] != null) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false));

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[56]++;}
        keys[index] = key;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[115]++;
        values[N + index] = hash;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[116]++;
        ++occupiedCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[117]++;
        ++keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[118]++;

        return index;
    }

    private void rehashTable() {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[119]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((keys == null) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[61]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[120]++;
int CodeCoverConditionCoverageHelper_C38;
            if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((keyCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[63]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[121]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[64]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[122]++;
int CodeCoverConditionCoverageHelper_C39;
            if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((occupiedCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[65]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[123]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[66]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[124]++;
            int N = 1 << power;
            keys = new Object[N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[125]++;
            values = new int[2 * N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[126]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[62]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[127]++;
int CodeCoverConditionCoverageHelper_C40;
            // Check if removing deleted entries would free enough space
            if ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((keyCount * 2 >= occupiedCount) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[67]++;
                // Need to grow: less then half of deleted entries
                ++power;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[128]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[68]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[129]++;
            int N = 1 << power;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[130]++;
            Object[] oldKeys = keys;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[131]++;
            int[] oldValues = values;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[132]++;
            int oldN = oldKeys.length;
            keys = new Object[N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[133]++;
            values = new int[2 * N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[134]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[135]++;

            int remaining = keyCount;
            occupiedCount = keyCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[136]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[137]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[19]++;


int CodeCoverConditionCoverageHelper_C41;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((remaining != 0) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[19]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[20]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[21]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[138]++;
                Object key = oldKeys[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[139]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((key != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[69]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[140]++;
                    int keyHash = oldValues[oldN + i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[141]++;
                    int index = insertNewKey(key, keyHash);
                    values[index] = oldValues[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[142]++;
                    --remaining;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[143]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[70]++;}
            }
        }
    }

// Ensure key index creating one if necessary
    private int ensureIndex(Object key) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[144]++;
        int hash = key.hashCode();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[145]++;
        int index = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[146]++;
        int firstDeleted = -1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[147]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[71]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[148]++;
            int fraction = hash * A;
            index = fraction >>> (32 - power);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[149]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[150]++;
            Object test = keys[index];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[151]++;
int CodeCoverConditionCoverageHelper_C44;
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((test != null) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[73]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[152]++;
                int N = 1 << power;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[153]++;
int CodeCoverConditionCoverageHelper_C45;
                if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (32)) == 0 || true) &&
 ((test == key) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((values[N + index] == hash) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((test.equals(key)) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 3) && false))
                {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[75]++;
                    return index;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[76]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[154]++;
int CodeCoverConditionCoverageHelper_C46;
                if ((((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((test == DELETED) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[77]++;
                    firstDeleted = index;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[155]++;

                } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[78]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[156]++;

                // Search in table after first failed attempt
                int mask = N - 1;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[157]++;
                int step = tableLookupStep(fraction, mask, power);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[158]++;
                int n = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[159]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[22]++;


                for (;;) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[22]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[23]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[24]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[160]++;
int CodeCoverConditionCoverageHelper_C48;
                    if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[79]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[161]++;
int CodeCoverConditionCoverageHelper_C49;
                        if ((((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((n >= occupiedCount) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[81]++; Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[162]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[82]++;}
                        ++n;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[163]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[80]++;}
                    index = (index + step) & mask;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[164]++;
                    test = keys[index];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[165]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[166]++;
int CodeCoverConditionCoverageHelper_C50;
                    if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((test == null) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[83]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[167]++;
                        break;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[84]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[168]++;
int CodeCoverConditionCoverageHelper_C51;
                    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (32)) == 0 || true) &&
 ((test == key) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (16)) == 0 || true)))
 || (
(((CodeCoverConditionCoverageHelper_C51 |= (8)) == 0 || true) &&
 ((values[N + index] == hash) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((test.equals(key)) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 3) && false))
                    {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[85]++;
                        return index;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[86]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[169]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (8)) == 0 || true) &&
 ((test == DELETED) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((firstDeleted < 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[87]++;
                        firstDeleted = index;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[170]++;

                    } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[88]++;}
                }

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[74]++;}

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[72]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[171]++;
int CodeCoverConditionCoverageHelper_C53;
        // Inserting of new key
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (32)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (8)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((keys[index] != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 3) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[89]++;
            Kit.codeBug();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[172]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[90]++;}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[173]++;
int CodeCoverConditionCoverageHelper_C54;
        if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((firstDeleted >= 0) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[91]++;
            index = firstDeleted;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[174]++;

        }
        else {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[92]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[175]++;
int CodeCoverConditionCoverageHelper_C55;
            // Need to consume empty entry: check occupation level
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (8)) == 0 || true) &&
 ((keys == null) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((occupiedCount * 4 >= (1 << power) * 3) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[93]++;
                // Too litle unused entries: rehash
                rehashTable();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[176]++;
                return insertNewKey(key, hash);

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[94]++;}
            ++occupiedCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[177]++;
        }
        keys[index] = key;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[178]++;
        values[(1 << power) + index] = hash;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[179]++;
        ++keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[180]++;
        return index;
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[181]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[182]++;

        int count = keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[183]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[25]++;


int CodeCoverConditionCoverageHelper_C56;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[25]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[26]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[27]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[184]++;
            Object key = keys[i];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[185]++;
int CodeCoverConditionCoverageHelper_C57;
            if ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (8)) == 0 || true) &&
 ((key != null) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((key != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[95]++;
                --count;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[186]++;
                out.writeObject(key);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[187]++;
                out.writeInt(values[i]);
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[188]++;

            } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[96]++;}
        }
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[189]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[190]++;

        int writtenKeyCount = keyCount;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[191]++;
int CodeCoverConditionCoverageHelper_C58;
        if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((writtenKeyCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[97]++;
            keyCount = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[192]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[193]++;
            int N = 1 << power;
            keys = new Object[N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[194]++;
            values = new int[2 * N];
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[195]++;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[196]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[28]++;


int CodeCoverConditionCoverageHelper_C59;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((i != writtenKeyCount) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[28]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[29]--;
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.loops[30]++;
}
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[197]++;
                Object key = in.readObject();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[198]++;
                int hash = key.hashCode();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[199]++;
                int index = insertNewKey(key, hash);
                values[index] = in.readInt();
CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[200]++;
            }

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.branches[98]++;}
    }

// A == golden_ratio * (1 << 32) = ((sqrt(5) - 1) / 2) * (1 << 32)
// See Knuth etc.
    private static final int A = 0x9e3779b9;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[201]++;
  }

    private static final Object DELETED = new Object();
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[202]++;
  }

// Structure of kyes and values arrays (N == 1 << power):
// keys[0 <= i < N]: key value or null or DELETED mark
// values[0 <= i < N]: value of key at keys[i]
// values[N <= i < 2*N]: hash code of key at keys[i-N]

    private transient Object[] keys;
    private transient int[] values;

    private int power;
    private int keyCount;
    private transient int occupiedCount; // == keyCount + deleted_count

// If true, enables consitency checks
    private static final boolean check = false;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd.statements[203]++;
  }

/* TEST START

    public static void main(String[] args) {
        if (!check) {
            System.err.println("Set check to true and re-run");
            throw new RuntimeException("Set check to true and re-run");
        }

        ObjToIntMap map;
        map = new ObjToIntMap(0);
        testHash(map, 3);
        map = new ObjToIntMap(0);
        testHash(map, 10 * 1000);
        map = new ObjToIntMap();
        testHash(map, 10 * 1000);
        map = new ObjToIntMap(30 * 1000);
        testHash(map, 10 * 100);
        map.clear();
        testHash(map, 4);
        map = new ObjToIntMap(0);
        testHash(map, 10 * 100);
    }

    private static void testHash(ObjToIntMap map, int N) {
        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            check(-1 == map.get(key, -1));
            map.put(key, i);
            check(i == map.get(key, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            map.put(key, i);
            check(i == map.get(key, -1));
        }

        check(map.size() == N);

        System.out.print("."); System.out.flush();
        Object[] keys = map.getKeys();
        check(keys.length == N);
        for (int i = 0; i != N; ++i) {
            Object key = keys[i];
            check(map.has(key));
        }


        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            check(i == map.get(key, -1));
        }

        int Nsqrt = -1;
        for (int i = 0; ; ++i) {
            if (i * i >= N) {
                Nsqrt = i;
                break;
            }
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i * i);
            map.put(key, i);
            check(i == map.get(key, -1));
        }

        check(map.size() == 2 * N - Nsqrt);

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i * i);
            check(i == map.get(key, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(-1 - i * i);
            map.put(key, i);
            check(i == map.get(key, -1));
        }

        check(map.size() == 3 * N - Nsqrt);

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(-1 - i * i);
            map.remove(key);
            check(!map.has(key));
        }

        check(map.size() == 2 * N - Nsqrt);

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i * i);
            check(i == map.get(key, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            int j = intSqrt(i);
            if (j * j == i) {
                check(j == map.get(key, -1));
            }else {
                check(i == map.get(key, -1));
            }
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i * i);
            map.remove(key);
            check(-2 == map.get(key, -2));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            map.put(key, i);
            check(i == map.get(key, -2));
        }

        check(map.size() == N);

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            check(i == map.get(key, -1));
        }

        System.out.print("."); System.out.flush();
        ObjToIntMap copy = (ObjToIntMap)writeAndRead(map);
        check(copy.size() == N);

        for (int i = 0; i != N; ++i) {
            Object key = testKey(i);
            check(i == copy.get(key, -1));
        }

        System.out.print("."); System.out.flush();
        checkSameMaps(copy, map);

        System.out.println(); System.out.flush();
    }

    private static void checkSameMaps(ObjToIntMap map1, ObjToIntMap map2) {
        check(map1.size() == map2.size());
        Object[] keys = map1.getKeys();
        check(keys.length == map1.size());
        for (int i = 0; i != keys.length; ++i) {
            check(map1.get(keys[i], -1) == map2.get(keys[i], -1));
        }
    }

    private static void check(boolean condition) {
        if (!condition) Kit.codeBug();
    }

    private static Object[] testPool;

    private static Object testKey(int i) {
        int MAX_POOL = 100;
        if (0 <= i && i < MAX_POOL) {
            if (testPool != null && testPool[i] != null) {
                return testPool[i];
            }
        }
        Object x = new Double(i + 0.5);
        if (0 <= i && i < MAX_POOL) {
            if (testPool == null) {
                testPool = new Object[MAX_POOL];
            }
            testPool[i] = x;
        }
        return x;
    }

    private static int intSqrt(int i) {
        int approx = (int)Math.sqrt(i) + 1;
        while (approx * approx > i) {
            --approx;
        }
        return approx;
    }

    private static Object writeAndRead(Object obj) {
        try {
            java.io.ByteArrayOutputStream
                bos = new java.io.ByteArrayOutputStream();
            java.io.ObjectOutputStream
                out = new java.io.ObjectOutputStream(bos);
            out.writeObject(obj);
            out.close();
            byte[] data = bos.toByteArray();
            java.io.ByteArrayInputStream
                bis = new java.io.ByteArrayInputStream(data);
            java.io.ObjectInputStream
                in = new java.io.ObjectInputStream(bis);
            Object result = in.readObject();
            in.close();
            return result;
        }catch (Exception ex) {
            throw new RuntimeException("Unexpected");
        }
    }

// TEST END */

}

class CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd ());
  }
    public static long[] statements = new long[204];
    public static long[] branches = new long[99];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[60];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ObjToIntMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,0,2,1,1,1,2,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,3,0,1,1,1,3,2,2,1,1,2,2,1,2,2,1,1,2,1,1,3,1,0,1,1,1,3,2,3,1,2,1,2,1,1};
    for (int i = 1; i <= 59; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[31];

  public CodeCoverCoverageCounter$qmzdwrwnkgq1j9id7lj46k1kgifv49zmhj5dhrhd () {
    super("org.mozilla.javascript.RHINO-SRC-ObjToIntMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 203; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 98; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 59; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 30; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ObjToIntMap.java");
      for (int i = 1; i <= 203; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 98; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 59; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 10; i++) {
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

