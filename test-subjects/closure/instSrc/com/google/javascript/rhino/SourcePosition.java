/*
 *
 * ***** BEGIN LICENSE BLOCK *****
 * Version: MPL 1.1/GPL 2.0
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 *
 * The Original Code is Rhino code, released
 * May 6, 1999.
 *
 * The Initial Developer of the Original Code is
 * Netscape Communications Corporation.
 * Portions created by the Initial Developer are Copyright (C) 1997-1999
 * the Initial Developer. All Rights Reserved.
 *
 * Contributor(s):
 *   Bob Jervis
 *   Google Inc.
 *
 * Alternatively, the contents of this file may be used under the terms of
 * the GNU General Public License Version 2 or later (the "GPL"), in which
 * case the provisions of the GPL are applicable instead of those above. If
 * you wish to allow use of your version of this file only under the terms of
 * the GPL and not to allow others to use your version of this file under the
 * MPL, indicate your decision by deleting the provisions above and replacing
 * them with the notice and other provisions required by the GPL. If you do
 * not delete the provisions above, a recipient may use your version of this
 * file under either the MPL or the GPL.
 *
 * ***** END LICENSE BLOCK ***** */

package com.google.javascript.rhino;

/**
 * Represents a position in some piece of source code, with an associated
 * item of type T found at that position.
 *
 */
public abstract class SourcePosition<T> {
  static {
    CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.ping();
  }

  /**
   * The (well typed) item found at the source position.
   */
  private T item = null;
  {
    CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[1]++;
  }

  /**
   * The starting line number.
   */
  private int startLineno = 0;
  {
    CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[2]++;
  }

  /**
   * The character position on the starting line.
   */
  private int startCharno = 0;
  {
    CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[3]++;
  }

  /**
   * The ending line number.
   */
  private int endLineno = 0;
  {
    CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[4]++;
  }

  /**
   * The character position on the ending line.
   */
  private int endCharno = 0;
  {
    CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[5]++;
  }

  /**
   * Sets the item that this source position references.
   */
  public void setItem(T item) {
    this.item = item;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[6]++;
  }

  /**
   * Sets the position information contained in this source position.
   */
  public void setPositionInformation(int startLineno, int startCharno,
                                     int endLineno, int endCharno) {
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((startLineno == endLineno) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.branches[1]++;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
      if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((startCharno >= endCharno) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.branches[3]++;
        throw new IllegalStateException(
            "Recorded bad position information\n" +
            "start-char: " + startCharno + "\n" +
            "end-char: " + endCharno);

      } else {
  CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.branches[4]++;}

    } else {
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.branches[2]++;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
      if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((startLineno > endLineno) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.branches[5]++;
        throw new IllegalStateException(
            "Recorded bad position information\n" +
            "start-line: " + startLineno + "\n" +
            "end-line: " + endLineno);

      } else {
  CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.branches[6]++;}
    }

    this.startLineno = startLineno;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[10]++;
    this.startCharno = startCharno;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[11]++;
    this.endLineno = endLineno;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[12]++;
    this.endCharno = endCharno;
CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp.statements[13]++;
  }

  /**
   * Returns the item found at this source position.
   */
  public T getItem() {
    return item;
  }

  /**
   * Returns the starting line number of this position.
   */
  public int getStartLine() {
    return startLineno;
  }

  /**
   * Returns the character position on the starting line.
   */
  public int getPositionOnStartLine() {
    return startCharno;
  }

  /**
   * Returns the ending line number of this position.
   */
  public int getEndLine() {
    return endLineno;
  }

  /**
   * Returns the character position on the ending line.
   */
  public int getPositionOnEndLine() {
    return endCharno;
  }
}

class CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp ());
  }
    public static long[] statements = new long[14];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "com.google.javascript.rhino.SourcePosition.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1};
    for (int i = 1; i <= 3; i++) {
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

  public CodeCoverCoverageCounter$1dc8pmhsih82h29qn244tcyn98uiqp () {
    super("com.google.javascript.rhino.SourcePosition.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 13; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 6; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 3; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("com.google.javascript.rhino.SourcePosition.java");
      for (int i = 1; i <= 13; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 6; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 3; i++) {
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

