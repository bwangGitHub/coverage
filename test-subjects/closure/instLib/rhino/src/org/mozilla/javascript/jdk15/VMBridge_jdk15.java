/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript.jdk15;

import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import org.mozilla.javascript.*;

public class VMBridge_jdk15 extends org.mozilla.javascript.jdk13.VMBridge_jdk13
{
  static {
    CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.ping();
  }

    public VMBridge_jdk15() throws SecurityException, InstantiationException {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[1]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            // Just try and see if we can access the isVarArgs method.
            // We want to fail loading if the method does not exist
            // so that we can load a bridge to an older JDK instead.
            Method.class.getMethod("isVarArgs", (Class[]) null);
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[2]++;
        } catch (NoSuchMethodException e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[2]++;
            // Throw a fitting exception that is handled by
            // org.mozilla.javascript.Kit.newInstanceOrNull:
            throw new InstantiationException(e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[1]++;
}
  }
    }

    @Override
    public boolean isVarArgs(Member member) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[3]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((member instanceof Method) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[3]++;
            return ((Method) member).isVarArgs();
}
        else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[4]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[4]++;
int CodeCoverConditionCoverageHelper_C2; if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((member instanceof Constructor) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[5]++;
            return ((Constructor<?>) member).isVarArgs();
}
        else {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[6]++;
            return false;
}
}
    }

    /**
     * If "obj" is a java.util.Iterator or a java.lang.Iterable, return a
     * wrapping as a JavaScript Iterator. Otherwise, return null.
     * This method is in VMBridge since Iterable is a JDK 1.5 addition.
     */
    @Override
    public Iterator<?> getJavaIterator(Context cx, Scriptable scope, Object obj) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[5]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((obj instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[7]++;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[6]++;
            Object unwrapped = ((Wrapper) obj).unwrap();
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[7]++;
            Iterator<?> iterator = null;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[8]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((unwrapped instanceof Iterator) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[9]++;
                iterator = (Iterator<?>) unwrapped;
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[9]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[10]++;}
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[10]++;
int CodeCoverConditionCoverageHelper_C5;
            if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((unwrapped instanceof Iterable) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[11]++;
                iterator = ((Iterable<?>)unwrapped).iterator();
CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.statements[11]++;
} else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[12]++;}
            return iterator;

        } else {
  CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t.branches[8]++;}
        return null;
    }
}

class CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t ());
  }
    public static long[] statements = new long[12];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[6];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.jdk15.RHINO-SRC-VMBridge_jdk15.java";
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

  public CodeCoverCoverageCounter$7e30hdlyknbm6gevyyrnfxtt5rcibgu1nihk3pretaa1t () {
    super("org.mozilla.javascript.jdk15.RHINO-SRC-VMBridge_jdk15.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 11; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.mozilla.javascript.jdk15.RHINO-SRC-VMBridge_jdk15.java");
      for (int i = 1; i <= 11; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 12; i++) {
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

