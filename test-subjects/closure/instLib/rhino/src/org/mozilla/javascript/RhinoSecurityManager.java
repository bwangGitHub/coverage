/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * A <code>java.lang.SecurityManager</code> subclass that provides access to
 * the current top-most script class on the execution stack. This can be used
 * to get the class loader or protection domain of the script that triggered
 * the current action. It is required for JavaAdapters to have the same
 * <code>ProtectionDomain</code> as the script code that created them.
 * Embeddings that implement their own SecurityManager can use this as base class.
 */
public class RhinoSecurityManager extends SecurityManager {
  static {
    CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.ping();
  }


    /**
     * Get the class of the top-most stack element representing a script.
     * @return The class of the top-most script in the current stack,
     *         or null if no script is currently running
     */
    protected Class getCurrentScriptClass() {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.statements[1]++;
        Class[] context = getClassContext();
CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.statements[2]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.loops[1]++;


        for (Class c : context) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.loops[1]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.loops[2]--;
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.loops[3]++;
}
CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
            if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (32)) == 0 || true) &&
 ((c != InterpretedFunction.class) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((NativeFunction.class.isAssignableFrom(c)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((PolicySecurityController.SecureCaller.class.isAssignableFrom(c)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) || true)) || (CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 3) && false)) {
CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.branches[1]++;
                return c;

            } else {
  CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx.branches[2]++;}
        }
        return null;
    }

}

class CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx ());
  }
    public static long[] statements = new long[4];
    public static long[] branches = new long[3];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[2];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-RhinoSecurityManager.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,3};
    for (int i = 1; i <= 1; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$khgdrpvbfyfsmmlx1ib9q0ui86gduti61r6ni8ndimfpu37c8m70wx () {
    super("org.mozilla.javascript.RHINO-SRC-RhinoSecurityManager.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 3; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 2; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-RhinoSecurityManager.java");
      for (int i = 1; i <= 3; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 2; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 1; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 1; i++) {
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

