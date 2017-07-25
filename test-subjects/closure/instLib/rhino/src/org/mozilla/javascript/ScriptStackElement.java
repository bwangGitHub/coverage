/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;

/**
 * This class represents an element on the script execution stack.
 * @see RhinoException#getScriptStack()
 * @since 1.7R3
 */
public final class ScriptStackElement implements Serializable {
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.ping();
  }


    static final long serialVersionUID = -6416688260860477449L;
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[1]++;
  }

    public final String fileName;
    public final String functionName;
    public final int lineNumber;

    public ScriptStackElement(String fileName, String functionName, int lineNumber) {
        this.fileName = fileName;
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[2]++;
        this.functionName = functionName;
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[3]++;
        this.lineNumber = lineNumber;
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[4]++;
    }

    public String toString() {
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[5]++;
        StringBuilder sb = new StringBuilder();
        renderMozillaStyle(sb);
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[6]++;
        return sb.toString();
    }

    /**
     * Render stack element in Java-inspired style:
     * <code>    at fileName:lineNumber (functionName)</code>
     * @param sb the StringBuilder to append to
     */
    public void renderJavaStyle(StringBuilder sb) {
        sb.append("\tat ").append(fileName);
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[7]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[8]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((lineNumber > -1) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[1]++;
            sb.append(':').append(lineNumber);
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[9]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[2]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[3]++;
            sb.append(" (").append(functionName).append(')');
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[11]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[4]++;}
    }

    /**
     * Render stack element in Mozilla/Firefox style:
     * <code>functionName()@fileName:lineNumber</code>
     * @param sb the StringBuilder to append to
     */
    public void renderMozillaStyle(StringBuilder sb) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((functionName != null) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[5]++;
            sb.append(functionName).append("()");
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[13]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[6]++;}
        sb.append('@').append(fileName);
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[14]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[15]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((lineNumber > -1) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[7]++;
            sb.append(':').append(lineNumber);
CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.statements[16]++;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9.branches[8]++;}
    }

}

class CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9 ());
  }
    public static long[] statements = new long[17];
    public static long[] branches = new long[9];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-ScriptStackElement.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1};
    for (int i = 1; i <= 4; i++) {
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

  public CodeCoverCoverageCounter$el0607z4lg6oa7b18at4ugf91c0lgebyx469rdcip6yqmonhwm9 () {
    super("org.mozilla.javascript.RHINO-SRC-ScriptStackElement.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 16; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 8; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 4; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-ScriptStackElement.java");
      for (int i = 1; i <= 16; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 8; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 4; i++) {
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

