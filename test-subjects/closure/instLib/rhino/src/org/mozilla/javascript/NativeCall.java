/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * This class implements the activation object.
 *
 * See ECMA 10.1.6
 *
 * @see org.mozilla.javascript.Arguments
 */
public final class NativeCall extends IdScriptableObject
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.ping();
  }

    static final long serialVersionUID = -7471457301304454454L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[1]++;
  }

    private static final Object CALL_TAG = "Call";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[2]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[3]++;
        NativeCall obj = new NativeCall();
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[4]++;
    }

    NativeCall() { }

    NativeCall(NativeFunction function, Scriptable scope, Object[] args)
    {
        this.function = function;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[5]++;

        setParentScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[6]++;
        // leave prototype null

        this.originalArgs = (args == null) ? ScriptRuntime.emptyArgs : args;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[7]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[8]++;

        // initialize values of arguments
        int paramAndVarCount = function.getParamAndVarCount();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[9]++;
        int paramCount = function.getParamCount();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[10]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((paramAndVarCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[1]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[11]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[1]++;


int CodeCoverConditionCoverageHelper_C2;
            for (int i = 0;(((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((i < paramCount) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[12]++;
                String name = function.getParamOrVarName(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[13]++;
                Object val = i < args.length ? args[i]
                                             : Undefined.instance;
                defineProperty(name, val, PERMANENT);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[14]++;
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[2]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[15]++;
int CodeCoverConditionCoverageHelper_C3;

        // initialize "arguments" property but only if it was not overridden by
        // the parameter with the same name
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((super.has("arguments", this)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[3]++;
            defineProperty("arguments", new Arguments(this), PERMANENT);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[16]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[4]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[17]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((paramAndVarCount != 0) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[5]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[18]++;
byte CodeCoverTryBranchHelper_L2 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[4]++;


int CodeCoverConditionCoverageHelper_C5;
            for (int i = paramCount;(((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((i < paramAndVarCount) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false); ++i) {
if (CodeCoverTryBranchHelper_L2 == 0) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[4]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[5]++;
} else if (CodeCoverTryBranchHelper_L2 == 1) {
  CodeCoverTryBranchHelper_L2++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[5]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.loops[6]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[19]++;
                String name = function.getParamOrVarName(i);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[20]++;
int CodeCoverConditionCoverageHelper_C6;
                if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((super.has(name, this)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[7]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[21]++;
int CodeCoverConditionCoverageHelper_C7;
                    if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((function.getParamOrVarConst(i)) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[9]++;
                        defineProperty(name, Undefined.instance, CONST);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[22]++;
}
                    else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[10]++;
                        defineProperty(name, Undefined.instance, PERMANENT);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[23]++;
}

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[8]++;}
            }

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[6]++;}
    }

    @Override
    public String getClassName()
    {
        return "Call";
    }

    @Override
    protected int findPrototypeId(String s)
    {
        return s.equals("constructor") ? Id_constructor : 0;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[24]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[11]++;
            arity=1;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[25]++; s="constructor";
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[26]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[12]++;
            throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(CALL_TAG, id, s, arity);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[27]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[28]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((f.hasTag(CALL_TAG)) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[13]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[14]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[29]++;
        int id = f.methodId();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[30]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((id == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[15]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[31]++;
int CodeCoverConditionCoverageHelper_C11;
            if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((thisObj != null) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[17]++;
                throw Context.reportRuntimeError1("msg.only.from.new", "Call");

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[18]++;}
            ScriptRuntime.checkDeprecated(cx, "Call");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[32]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[33]++;
            NativeCall result = new NativeCall();
            result.setPrototype(getObjectPrototype(scope));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[34]++;
            return result;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.branches[16]++;}
        throw new IllegalArgumentException(String.valueOf(id));
    }

    private static final int
        Id_constructor   = 1,
        MAX_PROTOTYPE_ID = 1;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735.statements[35]++;
  }

    NativeFunction function;
    Object[] originalArgs;

    transient NativeCall parentActivationCall;
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735 ());
  }
    public static long[] statements = new long[36];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeCall.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 11; i++) {
      switch (CONDITION_COUNTER_TYPES[i]) {
        case 0 : break;
        case 1 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallOneConditionCounter(SECTION_NAME, "C" + i); break;
        case 2 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.SmallTwoConditionCounter(SECTION_NAME, "C" + i); break;
        case 3 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.MediumConditionCounter(SECTION_NAME, "C" + i); break;
        case 4 : conditionCounters[i] = new org.codecover.instrumentation.java.measurement.LargeConditionCounter(SECTION_NAME, "C" + i); break;
      }
    }
  }
    public static long[] loops = new long[7];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0priyhckhvfe735 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeCall.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 35; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 6; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeCall.java");
      for (int i = 1; i <= 35; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 18; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].serializeAndReset(log);
      }
    }
      for (int i = 1; i <= 2; i++) {
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

