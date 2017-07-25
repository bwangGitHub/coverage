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
 * Map to associate non-negative integers to objects or integers.
 * The map does not synchronize any of its operation, so either use
 * it from a single thread or do own synchronization or perform all mutation
 * operations on one thread before passing the map to others.
 *
 *
 */

public class UintMap implements Serializable
{
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.ping();
  }

    static final long serialVersionUID = 4242698212885848444L;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[1]++;
  }

// Map implementation via hashtable,
// follows "The Art of Computer Programming" by Donald E. Knuth

    public UintMap() {
        this(4);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[2]++;
    }

    public UintMap(int initialCapacity) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((initialCapacity < 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[1]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[4]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[2]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[5]++;
        // Table grow when number of stored keys >= 3/4 of max capacity
        int minimalCapacity = initialCapacity * 4 / 3;
        int i;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[6]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
        for (i = 2;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 (((1 << i) < minimalCapacity) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[1]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[2]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[3]++;
} }
        power = i;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[7]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[8]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((power < 2) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[3]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[9]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[4]++;}
    }

    public boolean isEmpty() {
        return keyCount == 0;
    }

    public int size() {
        return keyCount;
    }

    public boolean has(int key) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[10]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[5]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[11]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[6]++;}
        return 0 <= findIndex(key);
    }

    /**
     * Get object value assigned with key.
     * @return key object value or null if key is absent
     */
    public Object getObject(int key) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[12]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[7]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[13]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[8]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[14]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((values != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[9]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[15]++;
            int index = findIndex(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[16]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[11]++;
                return values[index];

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[12]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[10]++;}
        return null;
    }

    /**
     * Get integer value assigned with key.
     * @return key integer value or defaultValue if key is absent
     */
    public int getInt(int key, int defaultValue) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[17]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[13]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[18]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[14]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[19]++;
        int index = findIndex(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[20]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[15]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[21]++;
int CodeCoverConditionCoverageHelper_C10;
            if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((ivaluesShift != 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[17]++;
                return keys[ivaluesShift + index];

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[18]++;}
            return 0;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[16]++;}
        return defaultValue;
    }

    /**
     * Get integer value assigned with key.
     * @return key integer value or defaultValue if key does not exist or does
     * not have int value
     * @throws RuntimeException if key does not exist
     */
    public int getExistingInt(int key) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[22]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[19]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[23]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[20]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[24]++;
        int index = findIndex(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[25]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[21]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[26]++;
int CodeCoverConditionCoverageHelper_C13;
            if ((((((CodeCoverConditionCoverageHelper_C13 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C13 |= (2)) == 0 || true) &&
 ((ivaluesShift != 0) && 
  ((CodeCoverConditionCoverageHelper_C13 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[13].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C13, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[23]++;
                return keys[ivaluesShift + index];

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[24]++;}
            return 0;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[22]++;}
        // Key must exist
        Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[27]++;
        return 0;
    }

    /**
     * Set object value of the key.
     * If key does not exist, also set its int value to 0.
     */
    public void put(int key, Object value) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[28]++;
int CodeCoverConditionCoverageHelper_C14;
        if ((((((CodeCoverConditionCoverageHelper_C14 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C14 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C14 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[14].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C14, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[25]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[29]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[26]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[30]++;
        int index = ensureIndex(key, false);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[31]++;
int CodeCoverConditionCoverageHelper_C15;
        if ((((((CodeCoverConditionCoverageHelper_C15 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C15 |= (2)) == 0 || true) &&
 ((values == null) && 
  ((CodeCoverConditionCoverageHelper_C15 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[15].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C15, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[27]++;
            values = new Object[1 << power];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[32]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[28]++;}
        values[index] = value;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[33]++;
    }

    /**
     * Set int value of the key.
     * If key does not exist, also set its object value to null.
     */
    public void put(int key, int value) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[34]++;
int CodeCoverConditionCoverageHelper_C16;
        if ((((((CodeCoverConditionCoverageHelper_C16 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C16 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C16 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[16].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C16, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[29]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[35]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[30]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[36]++;
        int index = ensureIndex(key, true);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[37]++;
int CodeCoverConditionCoverageHelper_C17;
        if ((((((CodeCoverConditionCoverageHelper_C17 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C17 |= (2)) == 0 || true) &&
 ((ivaluesShift == 0) && 
  ((CodeCoverConditionCoverageHelper_C17 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[17].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C17, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[31]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[38]++;
            int N = 1 << power;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[39]++;
int CodeCoverConditionCoverageHelper_C18;
            // keys.length can be N * 2 after clear which set ivaluesShift to 0
            if ((((((CodeCoverConditionCoverageHelper_C18 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C18 |= (2)) == 0 || true) &&
 ((keys.length != N * 2) && 
  ((CodeCoverConditionCoverageHelper_C18 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[18].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C18, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[33]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[40]++;
                int[] tmp = new int[N * 2];
                System.arraycopy(keys, 0, tmp, 0, N);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[41]++;
                keys = tmp;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[42]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[34]++;}
            ivaluesShift = N;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[43]++;

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[32]++;}
        keys[ivaluesShift + index] = value;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[44]++;
    }

    public void remove(int key) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[45]++;
int CodeCoverConditionCoverageHelper_C19;
        if ((((((CodeCoverConditionCoverageHelper_C19 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C19 |= (2)) == 0 || true) &&
 ((key < 0) && 
  ((CodeCoverConditionCoverageHelper_C19 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[19].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C19, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[35]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[46]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[36]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[47]++;
        int index = findIndex(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[48]++;
int CodeCoverConditionCoverageHelper_C20;
        if ((((((CodeCoverConditionCoverageHelper_C20 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C20 |= (2)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C20 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[20].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C20, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[37]++;
            keys[index] = DELETED;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[49]++;
            --keyCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[50]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[51]++;
int CodeCoverConditionCoverageHelper_C21;
            // Allow to GC value and make sure that new key with the deleted
            // slot shall get proper default values
            if ((((((CodeCoverConditionCoverageHelper_C21 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C21 |= (2)) == 0 || true) &&
 ((values != null) && 
  ((CodeCoverConditionCoverageHelper_C21 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[21].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C21, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[39]++; values[index] = null;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[52]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[40]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[53]++;
int CodeCoverConditionCoverageHelper_C22;
            if ((((((CodeCoverConditionCoverageHelper_C22 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C22 |= (2)) == 0 || true) &&
 ((ivaluesShift != 0) && 
  ((CodeCoverConditionCoverageHelper_C22 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[22].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C22, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[41]++; keys[ivaluesShift + index] = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[54]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[42]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[38]++;}
    }

    public void clear() {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[55]++;
        int N = 1 << power;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[56]++;
int CodeCoverConditionCoverageHelper_C23;
        if ((((((CodeCoverConditionCoverageHelper_C23 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C23 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C23 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[23].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C23, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[43]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[57]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[4]++;


int CodeCoverConditionCoverageHelper_C24;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C24 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C24 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C24 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[24].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C24, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[4]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[5]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[6]++;
}
                keys[i] = EMPTY;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[58]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[59]++;
int CodeCoverConditionCoverageHelper_C25;
            if ((((((CodeCoverConditionCoverageHelper_C25 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C25 |= (2)) == 0 || true) &&
 ((values != null) && 
  ((CodeCoverConditionCoverageHelper_C25 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[25].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C25, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[45]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[60]++;
byte CodeCoverTryBranchHelper_L3 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[7]++;


int CodeCoverConditionCoverageHelper_C26;
                for (int i = 0;(((((CodeCoverConditionCoverageHelper_C26 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C26 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C26 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[26].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C26, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L3 == 0) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[7]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[8]++;
} else if (CodeCoverTryBranchHelper_L3 == 1) {
  CodeCoverTryBranchHelper_L3++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[8]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[9]++;
}
                    values[i] = null;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[61]++;
                }

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[46]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[44]++;}
        ivaluesShift = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[62]++;
        keyCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[63]++;
        occupiedCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[64]++;
    }

    /** Return array of present keys */
    public int[] getKeys() {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[65]++;
        int[] keys = this.keys;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[66]++;
        int n = keyCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[67]++;
        int[] result = new int[n];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[68]++;
byte CodeCoverTryBranchHelper_L4 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[10]++;


int CodeCoverConditionCoverageHelper_C27;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C27 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C27 |= (2)) == 0 || true) &&
 ((n != 0) && 
  ((CodeCoverConditionCoverageHelper_C27 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[27].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C27, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L4 == 0) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[10]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[11]++;
} else if (CodeCoverTryBranchHelper_L4 == 1) {
  CodeCoverTryBranchHelper_L4++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[11]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[12]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[69]++;
            int entry = keys[i];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[70]++;
int CodeCoverConditionCoverageHelper_C28;
            if ((((((CodeCoverConditionCoverageHelper_C28 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C28 |= (8)) == 0 || true) &&
 ((entry != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C28 |= (2)) == 0 || true) &&
 ((entry != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C28 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[28].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C28, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[47]++;
                result[--n] = entry;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[71]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[48]++;}
        }
        return result;
    }

    private static int tableLookupStep(int fraction, int mask, int power) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[72]++;
        int shift = 32 - 2 * power;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[73]++;
int CodeCoverConditionCoverageHelper_C29;
        if ((((((CodeCoverConditionCoverageHelper_C29 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C29 |= (2)) == 0 || true) &&
 ((shift >= 0) && 
  ((CodeCoverConditionCoverageHelper_C29 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[29].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C29, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[49]++;
            return ((fraction >>> shift) & mask) | 1;

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[50]++;
            return (fraction & (mask >>> -shift)) | 1;
        }
    }

    private int findIndex(int key) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[74]++;
        int[] keys = this.keys;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[75]++;
int CodeCoverConditionCoverageHelper_C30;
        if ((((((CodeCoverConditionCoverageHelper_C30 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C30 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C30 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[30].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C30, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[51]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[76]++;
            int fraction = key * A;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[77]++;
            int index = fraction >>> (32 - power);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[78]++;
            int entry = keys[index];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[79]++;
int CodeCoverConditionCoverageHelper_C31;
            if ((((((CodeCoverConditionCoverageHelper_C31 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C31 |= (2)) == 0 || true) &&
 ((entry == key) && 
  ((CodeCoverConditionCoverageHelper_C31 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[31].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C31, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[53]++; return index;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[54]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[80]++;
int CodeCoverConditionCoverageHelper_C32;
            if ((((((CodeCoverConditionCoverageHelper_C32 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C32 |= (2)) == 0 || true) &&
 ((entry != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C32 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[32].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C32, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[55]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[81]++;
                // Search in table after first failed attempt
                int mask = (1 << power) - 1;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[82]++;
                int step = tableLookupStep(fraction, mask, power);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[83]++;
                int n = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[84]++;
byte CodeCoverTryBranchHelper_L5 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[13]++;


int CodeCoverConditionCoverageHelper_C33;
                do {
if (CodeCoverTryBranchHelper_L5 == 0) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[13]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[14]++;
} else if (CodeCoverTryBranchHelper_L5 == 1) {
  CodeCoverTryBranchHelper_L5++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[14]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[15]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[85]++;
int CodeCoverConditionCoverageHelper_C34;
                    if ((((((CodeCoverConditionCoverageHelper_C34 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C34 |= (2)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C34 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[34].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C34, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[57]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[86]++;
int CodeCoverConditionCoverageHelper_C35;
                        if ((((((CodeCoverConditionCoverageHelper_C35 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C35 |= (2)) == 0 || true) &&
 ((n >= occupiedCount) && 
  ((CodeCoverConditionCoverageHelper_C35 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[35].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C35, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[59]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[87]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[60]++;}
                        ++n;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[88]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[58]++;}
                    index = (index + step) & mask;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[89]++;
                    entry = keys[index];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[90]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[91]++;
int CodeCoverConditionCoverageHelper_C36;
                    if ((((((CodeCoverConditionCoverageHelper_C36 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C36 |= (2)) == 0 || true) &&
 ((entry == key) && 
  ((CodeCoverConditionCoverageHelper_C36 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[36].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C36, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[61]++; return index;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[62]++;}
                } while ((((((CodeCoverConditionCoverageHelper_C33 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C33 |= (2)) == 0 || true) &&
 ((entry != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C33 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[33].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C33, 1) && false));

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[56]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[52]++;}
        return -1;
    }

// Insert key that is not present to table without deleted entries
// and enough free space
    private int insertNewKey(int key) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[92]++;
int CodeCoverConditionCoverageHelper_C37;
        if ((((((CodeCoverConditionCoverageHelper_C37 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C37 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C37 |= (2)) == 0 || true) &&
 ((occupiedCount != keyCount) && 
  ((CodeCoverConditionCoverageHelper_C37 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[37].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C37, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[63]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[93]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[64]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[94]++;
int CodeCoverConditionCoverageHelper_C38;
        if ((((((CodeCoverConditionCoverageHelper_C38 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C38 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C38 |= (2)) == 0 || true) &&
 ((keyCount == 1 << power) && 
  ((CodeCoverConditionCoverageHelper_C38 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[38].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C38, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[65]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[95]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[66]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[96]++;
        int[] keys = this.keys;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[97]++;
        int fraction = key * A;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[98]++;
        int index = fraction >>> (32 - power);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[99]++;
int CodeCoverConditionCoverageHelper_C39;
        if ((((((CodeCoverConditionCoverageHelper_C39 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C39 |= (2)) == 0 || true) &&
 ((keys[index] != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C39 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[39].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C39, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[67]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[100]++;
            int mask = (1 << power) - 1;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[101]++;
            int step = tableLookupStep(fraction, mask, power);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[102]++;
            int firstIndex = index;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[103]++;
byte CodeCoverTryBranchHelper_L6 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[16]++;


int CodeCoverConditionCoverageHelper_C40;
            do {
if (CodeCoverTryBranchHelper_L6 == 0) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[16]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[17]++;
} else if (CodeCoverTryBranchHelper_L6 == 1) {
  CodeCoverTryBranchHelper_L6++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[17]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[18]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[104]++;
int CodeCoverConditionCoverageHelper_C41;
                if ((((((CodeCoverConditionCoverageHelper_C41 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C41 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C41 |= (2)) == 0 || true) &&
 ((keys[index] == DELETED) && 
  ((CodeCoverConditionCoverageHelper_C41 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[41].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C41, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[69]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[105]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[70]++;}
                index = (index + step) & mask;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[106]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[107]++;
int CodeCoverConditionCoverageHelper_C42;
                if ((((((CodeCoverConditionCoverageHelper_C42 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C42 |= (8)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C42 |= (2)) == 0 || true) &&
 ((firstIndex == index) && 
  ((CodeCoverConditionCoverageHelper_C42 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[42].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C42, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[71]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[108]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[72]++;}
            } while ((((((CodeCoverConditionCoverageHelper_C40 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C40 |= (2)) == 0 || true) &&
 ((keys[index] != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C40 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[40].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C40, 1) && false));

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[68]++;}
        keys[index] = key;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[109]++;
        ++occupiedCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[110]++;
        ++keyCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[111]++;
        return index;
    }

    private void rehashTable(boolean ensureIntSpace) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[112]++;
int CodeCoverConditionCoverageHelper_C43;
        if ((((((CodeCoverConditionCoverageHelper_C43 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C43 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C43 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[43].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C43, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[73]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[113]++;
int CodeCoverConditionCoverageHelper_C44;
            // Check if removing deleted entries would free enough space
            if ((((((CodeCoverConditionCoverageHelper_C44 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C44 |= (2)) == 0 || true) &&
 ((keyCount * 2 >= occupiedCount) && 
  ((CodeCoverConditionCoverageHelper_C44 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[44].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C44, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[75]++;
                // Need to grow: less then half of deleted entries
                ++power;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[114]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[76]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[74]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[115]++;
        int N = 1 << power;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[116]++;
        int[] old = keys;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[117]++;
        int oldShift = ivaluesShift;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[118]++;
int CodeCoverConditionCoverageHelper_C45;
        if ((((((CodeCoverConditionCoverageHelper_C45 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C45 |= (8)) == 0 || true) &&
 ((oldShift == 0) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C45 |= (2)) == 0 || true) &&
 ((ensureIntSpace) && 
  ((CodeCoverConditionCoverageHelper_C45 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[45].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C45, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[77]++;
            keys = new int[N];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[119]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[78]++;
            ivaluesShift = N;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[120]++; keys = new int[N * 2];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[121]++;
        }
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[122]++;
byte CodeCoverTryBranchHelper_L7 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[19]++;


int CodeCoverConditionCoverageHelper_C46;
        for (int i = 0;(((((CodeCoverConditionCoverageHelper_C46 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C46 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C46 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[46].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C46, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L7 == 0) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[19]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[20]++;
} else if (CodeCoverTryBranchHelper_L7 == 1) {
  CodeCoverTryBranchHelper_L7++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[20]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[21]++;
} keys[i] = EMPTY;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[123]++; }
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[124]++;

        Object[] oldValues = values;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[125]++;
int CodeCoverConditionCoverageHelper_C47;
        if ((((((CodeCoverConditionCoverageHelper_C47 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C47 |= (2)) == 0 || true) &&
 ((oldValues != null) && 
  ((CodeCoverConditionCoverageHelper_C47 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[47].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C47, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[79]++; values = new Object[N];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[126]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[80]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[127]++;

        int oldCount = keyCount;
        occupiedCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[128]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[129]++;
int CodeCoverConditionCoverageHelper_C48;
        if ((((((CodeCoverConditionCoverageHelper_C48 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C48 |= (2)) == 0 || true) &&
 ((oldCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C48 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[48].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C48, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[81]++;
            keyCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[130]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[131]++;
byte CodeCoverTryBranchHelper_L8 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[22]++;


int CodeCoverConditionCoverageHelper_C49;
            for (int i = 0, remaining = oldCount;(((((CodeCoverConditionCoverageHelper_C49 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C49 |= (2)) == 0 || true) &&
 ((remaining != 0) && 
  ((CodeCoverConditionCoverageHelper_C49 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[49].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C49, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L8 == 0) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[22]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[23]++;
} else if (CodeCoverTryBranchHelper_L8 == 1) {
  CodeCoverTryBranchHelper_L8++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[23]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[24]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[132]++;
                int key = old[i];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[133]++;
int CodeCoverConditionCoverageHelper_C50;
                if ((((((CodeCoverConditionCoverageHelper_C50 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C50 |= (8)) == 0 || true) &&
 ((key != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C50 |= (2)) == 0 || true) &&
 ((key != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C50 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[50].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C50, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[83]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[134]++;
                    int index = insertNewKey(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[135]++;
int CodeCoverConditionCoverageHelper_C51;
                    if ((((((CodeCoverConditionCoverageHelper_C51 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C51 |= (2)) == 0 || true) &&
 ((oldValues != null) && 
  ((CodeCoverConditionCoverageHelper_C51 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[51].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C51, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[85]++;
                        values[index] = oldValues[i];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[136]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[86]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[137]++;
int CodeCoverConditionCoverageHelper_C52;
                    if ((((((CodeCoverConditionCoverageHelper_C52 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C52 |= (2)) == 0 || true) &&
 ((oldShift != 0) && 
  ((CodeCoverConditionCoverageHelper_C52 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[52].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C52, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[87]++;
                        keys[ivaluesShift + index] = old[oldShift + i];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[138]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[88]++;}
                    --remaining;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[139]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[84]++;}
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[82]++;}
    }

// Ensure key index creating one if necessary
    private int ensureIndex(int key, boolean intType) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[140]++;
        int index = -1;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[141]++;
        int firstDeleted = -1;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[142]++;
        int[] keys = this.keys;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[143]++;
int CodeCoverConditionCoverageHelper_C53;
        if ((((((CodeCoverConditionCoverageHelper_C53 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C53 |= (2)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C53 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[53].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C53, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[89]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[144]++;
            int fraction = key * A;
            index = fraction >>> (32 - power);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[145]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[146]++;
            int entry = keys[index];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[147]++;
int CodeCoverConditionCoverageHelper_C54;
            if ((((((CodeCoverConditionCoverageHelper_C54 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C54 |= (2)) == 0 || true) &&
 ((entry == key) && 
  ((CodeCoverConditionCoverageHelper_C54 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[54].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C54, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[91]++; return index;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[92]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[148]++;
int CodeCoverConditionCoverageHelper_C55;
            if ((((((CodeCoverConditionCoverageHelper_C55 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C55 |= (2)) == 0 || true) &&
 ((entry != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C55 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[55].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C55, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[93]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[149]++;
int CodeCoverConditionCoverageHelper_C56;
                if ((((((CodeCoverConditionCoverageHelper_C56 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C56 |= (2)) == 0 || true) &&
 ((entry == DELETED) && 
  ((CodeCoverConditionCoverageHelper_C56 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[56].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C56, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[95]++; firstDeleted = index;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[150]++;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[96]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[151]++;
                // Search in table after first failed attempt
                int mask = (1 << power) - 1;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[152]++;
                int step = tableLookupStep(fraction, mask, power);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[153]++;
                int n = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[154]++;
byte CodeCoverTryBranchHelper_L9 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[25]++;


int CodeCoverConditionCoverageHelper_C57;
                do {
if (CodeCoverTryBranchHelper_L9 == 0) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[25]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[26]++;
} else if (CodeCoverTryBranchHelper_L9 == 1) {
  CodeCoverTryBranchHelper_L9++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[26]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[27]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[155]++;
int CodeCoverConditionCoverageHelper_C58;
                    if ((((((CodeCoverConditionCoverageHelper_C58 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C58 |= (2)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C58 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[58].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C58, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[97]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[156]++;
int CodeCoverConditionCoverageHelper_C59;
                        if ((((((CodeCoverConditionCoverageHelper_C59 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C59 |= (2)) == 0 || true) &&
 ((n >= occupiedCount) && 
  ((CodeCoverConditionCoverageHelper_C59 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[59].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C59, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[99]++; Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[157]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[100]++;}
                        ++n;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[158]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[98]++;}
                    index = (index + step) & mask;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[159]++;
                    entry = keys[index];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[160]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[161]++;
int CodeCoverConditionCoverageHelper_C60;
                    if ((((((CodeCoverConditionCoverageHelper_C60 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C60 |= (2)) == 0 || true) &&
 ((entry == key) && 
  ((CodeCoverConditionCoverageHelper_C60 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[60].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C60, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[101]++; return index;
 } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[102]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[162]++;
int CodeCoverConditionCoverageHelper_C61;
                    if ((((((CodeCoverConditionCoverageHelper_C61 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C61 |= (8)) == 0 || true) &&
 ((entry == DELETED) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C61 |= (2)) == 0 || true) &&
 ((firstDeleted < 0) && 
  ((CodeCoverConditionCoverageHelper_C61 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[61].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C61, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[103]++;
                        firstDeleted = index;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[163]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[104]++;}
                } while ((((((CodeCoverConditionCoverageHelper_C57 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C57 |= (2)) == 0 || true) &&
 ((entry != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C57 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[57].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C57, 1) && false));

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[94]++;}

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[90]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[164]++;
int CodeCoverConditionCoverageHelper_C62;
        // Inserting of new key
        if ((((((CodeCoverConditionCoverageHelper_C62 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C62 |= (32)) == 0 || true) &&
 ((check) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (8)) == 0 || true) &&
 ((keys != null) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C62 |= (2)) == 0 || true) &&
 ((keys[index] != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C62 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[62].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C62, 3) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[105]++;
            Kit.codeBug();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[165]++;
} else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[106]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[166]++;
int CodeCoverConditionCoverageHelper_C63;
        if ((((((CodeCoverConditionCoverageHelper_C63 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C63 |= (2)) == 0 || true) &&
 ((firstDeleted >= 0) && 
  ((CodeCoverConditionCoverageHelper_C63 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[63].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C63, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[107]++;
            index = firstDeleted;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[167]++;

        }
        else {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[108]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[168]++;
int CodeCoverConditionCoverageHelper_C64;
            // Need to consume empty entry: check occupation level
            if ((((((CodeCoverConditionCoverageHelper_C64 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C64 |= (8)) == 0 || true) &&
 ((keys == null) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C64 |= (2)) == 0 || true) &&
 ((occupiedCount * 4 >= (1 << power) * 3) && 
  ((CodeCoverConditionCoverageHelper_C64 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[64].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C64, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[109]++;
                // Too litle unused entries: rehash
                rehashTable(intType);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[169]++;
                return insertNewKey(key);

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[110]++;}
            ++occupiedCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[170]++;
        }
        keys[index] = key;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[171]++;
        ++keyCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[172]++;
        return index;
    }

    private void writeObject(ObjectOutputStream out)
        throws IOException
    {
        out.defaultWriteObject();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[173]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[174]++;

        int count = keyCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[175]++;
int CodeCoverConditionCoverageHelper_C65;
        if ((((((CodeCoverConditionCoverageHelper_C65 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C65 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C65 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[65].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C65, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[111]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[176]++;
            boolean hasIntValues = (ivaluesShift != 0);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[177]++;
            boolean hasObjectValues = (values != null);
            out.writeBoolean(hasIntValues);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[178]++;
            out.writeBoolean(hasObjectValues);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[179]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[180]++;
byte CodeCoverTryBranchHelper_L10 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[28]++;


int CodeCoverConditionCoverageHelper_C66;

            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C66 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C66 |= (2)) == 0 || true) &&
 ((count != 0) && 
  ((CodeCoverConditionCoverageHelper_C66 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[66].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C66, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L10 == 0) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[28]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[29]++;
} else if (CodeCoverTryBranchHelper_L10 == 1) {
  CodeCoverTryBranchHelper_L10++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[29]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[30]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[181]++;
                int key = keys[i];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[182]++;
int CodeCoverConditionCoverageHelper_C67;
                if ((((((CodeCoverConditionCoverageHelper_C67 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C67 |= (8)) == 0 || true) &&
 ((key != EMPTY) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C67 |= (2)) == 0 || true) &&
 ((key != DELETED) && 
  ((CodeCoverConditionCoverageHelper_C67 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[67].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C67, 2) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[113]++;
                    --count;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[183]++;
                    out.writeInt(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[184]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[185]++;
int CodeCoverConditionCoverageHelper_C68;
                    if ((((((CodeCoverConditionCoverageHelper_C68 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C68 |= (2)) == 0 || true) &&
 ((hasIntValues) && 
  ((CodeCoverConditionCoverageHelper_C68 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[68].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C68, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[115]++;
                        out.writeInt(keys[ivaluesShift + i]);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[186]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[116]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[187]++;
int CodeCoverConditionCoverageHelper_C69;
                    if ((((((CodeCoverConditionCoverageHelper_C69 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C69 |= (2)) == 0 || true) &&
 ((hasObjectValues) && 
  ((CodeCoverConditionCoverageHelper_C69 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[69].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C69, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[117]++;
                        out.writeObject(values[i]);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[188]++;

                    } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[118]++;}

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[114]++;}
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[112]++;}
    }

    private void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        in.defaultReadObject();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[189]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[190]++;

        int writtenKeyCount = keyCount;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[191]++;
int CodeCoverConditionCoverageHelper_C70;
        if ((((((CodeCoverConditionCoverageHelper_C70 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C70 |= (2)) == 0 || true) &&
 ((writtenKeyCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C70 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[70].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C70, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[119]++;
            keyCount = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[192]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[193]++;
            boolean hasIntValues = in.readBoolean();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[194]++;
            boolean hasObjectValues = in.readBoolean();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[195]++;

            int N = 1 << power;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[196]++;
int CodeCoverConditionCoverageHelper_C71;
            if ((((((CodeCoverConditionCoverageHelper_C71 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C71 |= (2)) == 0 || true) &&
 ((hasIntValues) && 
  ((CodeCoverConditionCoverageHelper_C71 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[71].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C71, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[121]++;
                keys = new int[2 * N];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[197]++;
                ivaluesShift = N;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[198]++;

            }else {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[122]++;
                keys = new int[N];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[199]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[200]++;
byte CodeCoverTryBranchHelper_L11 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[31]++;


int CodeCoverConditionCoverageHelper_C72;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C72 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C72 |= (2)) == 0 || true) &&
 ((i != N) && 
  ((CodeCoverConditionCoverageHelper_C72 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[72].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C72, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L11 == 0) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[31]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[32]++;
} else if (CodeCoverTryBranchHelper_L11 == 1) {
  CodeCoverTryBranchHelper_L11++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[32]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[33]++;
}
                keys[i] = EMPTY;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[201]++;
            }
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[202]++;
int CodeCoverConditionCoverageHelper_C73;
            if ((((((CodeCoverConditionCoverageHelper_C73 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C73 |= (2)) == 0 || true) &&
 ((hasObjectValues) && 
  ((CodeCoverConditionCoverageHelper_C73 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[73].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C73, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[123]++;
                values = new Object[N];
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[203]++;

            } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[124]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[204]++;
byte CodeCoverTryBranchHelper_L12 = 0;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[34]++;


int CodeCoverConditionCoverageHelper_C74;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C74 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C74 |= (2)) == 0 || true) &&
 ((i != writtenKeyCount) && 
  ((CodeCoverConditionCoverageHelper_C74 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[74].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C74, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L12 == 0) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[34]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[35]++;
} else if (CodeCoverTryBranchHelper_L12 == 1) {
  CodeCoverTryBranchHelper_L12++;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[35]--;
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.loops[36]++;
}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[205]++;
                int key = in.readInt();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[206]++;
                int index = insertNewKey(key);
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[207]++;
int CodeCoverConditionCoverageHelper_C75;
                if ((((((CodeCoverConditionCoverageHelper_C75 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C75 |= (2)) == 0 || true) &&
 ((hasIntValues) && 
  ((CodeCoverConditionCoverageHelper_C75 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[75].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C75, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[125]++;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[208]++;
                    int ivalue = in.readInt();
                    keys[ivaluesShift + index] = ivalue;
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[209]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[126]++;}
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[210]++;
int CodeCoverConditionCoverageHelper_C76;
                if ((((((CodeCoverConditionCoverageHelper_C76 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C76 |= (2)) == 0 || true) &&
 ((hasObjectValues) && 
  ((CodeCoverConditionCoverageHelper_C76 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) || true)) || (CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.conditionCounters[76].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C76, 1) && false)) {
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[127]++;
                    values[index] = in.readObject();
CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[211]++;

                } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[128]++;}
            }

        } else {
  CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.branches[120]++;}
    }

// A == golden_ratio * (1 << 32) = ((sqrt(5) - 1) / 2) * (1 << 32)
// See Knuth etc.
    private static final int A = 0x9e3779b9;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[212]++;
  }

    private static final int EMPTY = -1;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[213]++;
  }
    private static final int DELETED = -2;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[214]++;
  }

// Structure of kyes and values arrays (N == 1 << power):
// keys[0 <= i < N]: key value or EMPTY or DELETED mark
// values[0 <= i < N]: value of key at keys[i]
// keys[N <= i < 2N]: int values of keys at keys[i - N]

    private transient int[] keys;
    private transient Object[] values;

    private int power;
    private int keyCount;
    private transient int occupiedCount; // == keyCount + deleted_count

    // If ivaluesShift != 0, keys[ivaluesShift + index] contains integer
    // values associated with keys
    private transient int ivaluesShift;

// If true, enables consitency checks
    private static final boolean check = false;
  static {
    CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x.statements[215]++;
  }

/* TEST START

    public static void main(String[] args) {
        if (!check) {
            System.err.println("Set check to true and re-run");
            throw new RuntimeException("Set check to true and re-run");
        }

        UintMap map;
        map = new UintMap();
        testHash(map, 2);
        map = new UintMap();
        testHash(map, 10 * 1000);
        map = new UintMap(30 * 1000);
        testHash(map, 10 * 100);
        map.clear();
        testHash(map, 4);
        map = new UintMap(0);
        testHash(map, 10 * 100);
    }

    private static void testHash(UintMap map, int N) {
        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            map.put(i, i);
            check(i == map.getInt(i, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            map.put(i, i);
            check(i == map.getInt(i, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            map.put(i, new Integer(i));
            check(-1 == map.getInt(i, -1));
            Integer obj = (Integer)map.getObject(i);
            check(obj != null && i == obj.intValue());
        }

        check(map.size() == N);

        System.out.print("."); System.out.flush();
        int[] keys = map.getKeys();
        check(keys.length == N);
        for (int i = 0; i != N; ++i) {
            int key = keys[i];
            check(map.has(key));
            check(!map.isIntType(key));
            check(map.isObjectType(key));
            Integer obj = (Integer) map.getObject(key);
            check(obj != null && key == obj.intValue());
        }


        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            check(-1 == map.getInt(i, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            map.put(i * i, i);
            check(i == map.getInt(i * i, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            check(i == map.getInt(i * i, -1));
        }

        System.out.print("."); System.out.flush();
        for (int i = 0; i != N; ++i) {
            map.put(i * i, new Integer(i));
            check(-1 == map.getInt(i * i, -1));
            map.remove(i * i);
            check(!map.has(i * i));
            map.put(i * i, i);
            check(map.isIntType(i * i));
            check(null == map.getObject(i * i));
            map.remove(i * i);
            check(!map.isObjectType(i * i));
            check(!map.isIntType(i * i));
        }

        int old_size = map.size();
        for (int i = 0; i != N; ++i) {
            map.remove(i * i);
            check(map.size() == old_size);
        }

        System.out.print("."); System.out.flush();
        map.clear();
        check(map.size() == 0);
        for (int i = 0; i != N; ++i) {
            map.put(i * i, i);
            map.put(i * i + 1, new Double(i+0.5));
        }
        checkSameMaps(map, (UintMap)writeAndRead(map));

        System.out.print("."); System.out.flush();
        map = new UintMap(0);
        checkSameMaps(map, (UintMap)writeAndRead(map));
        map = new UintMap(1);
        checkSameMaps(map, (UintMap)writeAndRead(map));
        map = new UintMap(1000);
        checkSameMaps(map, (UintMap)writeAndRead(map));

        System.out.print("."); System.out.flush();
        map = new UintMap(N / 10);
        for (int i = 0; i != N; ++i) {
            map.put(2*i+1, i);
        }
        checkSameMaps(map, (UintMap)writeAndRead(map));

        System.out.print("."); System.out.flush();
        map = new UintMap(N / 10);
        for (int i = 0; i != N; ++i) {
            map.put(2*i+1, i);
        }
        for (int i = 0; i != N / 2; ++i) {
            map.remove(2*i+1);
        }
        checkSameMaps(map, (UintMap)writeAndRead(map));

        System.out.print("."); System.out.flush();
        map = new UintMap();
        for (int i = 0; i != N; ++i) {
            map.put(2*i+1, new Double(i + 10));
        }
        for (int i = 0; i != N / 2; ++i) {
            map.remove(2*i+1);
        }
        checkSameMaps(map, (UintMap)writeAndRead(map));

        System.out.println(); System.out.flush();

    }

    private static void checkSameMaps(UintMap map1, UintMap map2) {
        check(map1.size() == map2.size());
        int[] keys = map1.getKeys();
        check(keys.length == map1.size());
        for (int i = 0; i != keys.length; ++i) {
            int key = keys[i];
            check(map2.has(key));
            check(map1.isObjectType(key) == map2.isObjectType(key));
            check(map1.isIntType(key) == map2.isIntType(key));
            Object o1 = map1.getObject(key);
            Object o2 = map2.getObject(key);
            if (map1.isObjectType(key)) {
                check(o1.equals(o2));
            }else {
                check(map1.getObject(key) == null);
                check(map2.getObject(key) == null);
            }
            if (map1.isIntType(key)) {
                check(map1.getExistingInt(key) == map2.getExistingInt(key));
            }else {
                check(map1.getInt(key, -10) == -10);
                check(map1.getInt(key, -11) == -11);
                check(map2.getInt(key, -10) == -10);
                check(map2.getInt(key, -11) == -11);
            }
        }
    }

    private static void check(boolean condition) {
        if (!condition) Kit.codeBug();
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
            ex.printStackTrace();
            throw new RuntimeException("Unexpected");
        }
    }

// TEST END */
}

class CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x ());
  }
    public static long[] statements = new long[216];
    public static long[] branches = new long[129];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[77];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-UintMap.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1,1,1,1,2,2,1,1,2,2,1,1,2,1,1,1,1,2,1,1,1,1,1,1,1,1,1,1,2,3,1,2,1,1,2,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 76; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[37];

  public CodeCoverCoverageCounter$di175yxae5e37vt0nzbaz52e5cic7yej4x () {
    super("org.mozilla.javascript.RHINO-SRC-UintMap.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 215; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 128; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 76; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 36; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-UintMap.java");
      for (int i = 1; i <= 215; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 128; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 76; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 12; i++) {
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

