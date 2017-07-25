/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;

/**
 * <p>This class represents a string composed of two components, each of which
 * may be a <code>java.lang.String</code> or another ConsString.</p>
 *
 * <p>This string representation is optimized for concatenation using the "+"
 * operator. Instead of immediately copying both components to a new character
 * array, ConsString keeps references to the original components and only
 * converts them to a String if either toString() is called or a certain depth
 * level is reached.</p>
 *
 * <p>Note that instances of this class are only immutable if both parts are
 * immutable, i.e. either Strings or ConsStrings that are ultimately composed
 * of Strings.</p>
 *
 * <p>Both the name and the concept are borrowed from V8.</p>
 */
public class ConsString implements CharSequence, Serializable {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.ping();
  }


    private static final long serialVersionUID = -8432806714471372570L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[1]++;
  }

    private CharSequence s1, s2;
    private final int length;
    private int depth;

    public ConsString(CharSequence str1, CharSequence str2) {
        s1 = str1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[2]++;
        s2 = str2;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[3]++;
        length = str1.length() + str2.length();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[4]++;
        depth = 1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[5]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((str1 instanceof ConsString) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[1]++;
            depth += ((ConsString)str1).depth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[7]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[2]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((str2 instanceof ConsString) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[3]++;
            depth += ((ConsString)str2).depth;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[9]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[4]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        // Don't let it grow too deep, can cause stack overflows
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((depth > 2000) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[5]++;
            flatten();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[11]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[6]++;}
    }

    // Replace with string representation when serializing
    private Object writeReplace() {
        return this.toString();
    }
    
    public String toString() {
        return depth == 0 ? (String)s1 : flatten();
    }

    private synchronized String flatten() {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((depth > 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[7]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[13]++;
            StringBuilder b = new StringBuilder(length);
            appendTo(b);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[14]++;
            s1 = b.toString();
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[15]++;
            s2 = "";
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[16]++;
            depth = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[17]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[8]++;}
        return (String)s1;
    }

    private synchronized void appendTo(StringBuilder b) {
        appendFragment(s1, b);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[18]++;
        appendFragment(s2, b);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[19]++;
    }

    private static void appendFragment(CharSequence s, StringBuilder b) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[20]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((s instanceof ConsString) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[9]++;
            ((ConsString)s).appendTo(b);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[21]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.branches[10]++;
            b.append(s);
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[22]++;
        }
    }

    public int length() {
        return length;
    }

    public char charAt(int index) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[23]++;
        String str = depth == 0 ? (String)s1 : flatten();
        return str.charAt(index);
    }

    public CharSequence subSequence(int start, int end) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh.statements[24]++;
        String str = depth == 0 ? (String)s1 : flatten();
        return str.substring(start, end);
    }

}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh ());
  }
    public static long[] statements = new long[25];
    public static long[] branches = new long[11];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ConsString.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1};
    for (int i = 1; i <= 5; i++) {
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw4ssdhzwd3ahcxzxxzv571wh () {
    super("org.mozilla.javascript.RHINO-SRC-ConsString.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 24; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 10; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 5; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ConsString.java");
      for (int i = 1; i <= 24; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 10; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 5; i++) {
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

