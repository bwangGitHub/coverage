/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

// API class

package org.mozilla.javascript;

/**
 * Java reflection of JavaScript exceptions.
 * Instances of this class are thrown by the JavaScript 'throw' keyword.
 *
 */
public class JavaScriptException extends RhinoException
{
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.ping();
  }

    static final long serialVersionUID = -7666130513694669293L;
  static {
    CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[1]++;
  }

    /**
     * @deprecated
     * Use {@link WrappedException#WrappedException(Throwable)} to report
     * exceptions in Java code.
     */
    public JavaScriptException(Object value)
    {
        this(value, "", 0);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[2]++;
    }

    /**
     * Create a JavaScript exception wrapping the given JavaScript value
     *
     * @param value the JavaScript value thrown.
     */
    public JavaScriptException(Object value, String sourceName, int lineNumber)
    {
        recordErrorOrigin(sourceName, lineNumber, null, 0);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[3]++;
        this.value = value;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[4]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        // Fill in fileName and lineNumber automatically when not specified
        // explicitly, see Bugzilla issue #342807
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((value instanceof NativeError) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((Context.getContext()
                .hasFeature(Context.FEATURE_LOCATION_INFORMATION_IN_ERROR)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[1]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[6]++;
            NativeError error = (NativeError) value;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[7]++;
int CodeCoverConditionCoverageHelper_C2;
            if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((error.has("fileName", error)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[3]++;
                error.put("fileName", error, sourceName);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[8]++;

            } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[4]++;}
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[9]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((error.has("lineNumber", error)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[5]++;
                error.put("lineNumber", error, Integer.valueOf(lineNumber));
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[10]++;

            } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[6]++;}
            // set stack property, see bug #549604
            error.setStackProvider(this);
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[11]++;

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[2]++;}
    }

    @Override
    public String details()
    {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[12]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((value == null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[7]++;
            return "null";

        } else {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[8]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[13]++;
int CodeCoverConditionCoverageHelper_C5; if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((value instanceof NativeError) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[9]++;
            return value.toString();

        } else {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[10]++;}
}
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[14]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            return ScriptRuntime.toString(value);
        } catch (RuntimeException rte) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[12]++;
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
            // ScriptRuntime.toString may throw a RuntimeException
            if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((value instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[13]++;
                return ScriptRuntime.defaultObjectToString((Scriptable)value);

            } else {
CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[14]++;
                return value.toString();
            }
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5.branches[11]++;
}
  }
    }

    /**
     * @return the value wrapped by this exception
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * @deprecated Use {@link RhinoException#sourceName()} from the super class.
     */
    public String getSourceName()
    {
        return sourceName();
    }

    /**
     * @deprecated Use {@link RhinoException#lineNumber()} from the super class.
     */
    public int getLineNumber()
    {
        return lineNumber();
    }

    private Object value;
}

class CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5 ());
  }
    public static long[] statements = new long[16];
    public static long[] branches = new long[15];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[7];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-JavaScriptException.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,1,1,1};
    for (int i = 1; i <= 6; i++) {
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

  public CodeCoverCoverageCounter$2vpd6pkpsoj3gojyeoa995zac04920sr4xhn59womjxram40bgkf5 () {
    super("org.mozilla.javascript.RHINO-SRC-JavaScriptException.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 15; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 14; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 6; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-JavaScriptException.java");
      for (int i = 1; i <= 15; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 14; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 6; i++) {
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

