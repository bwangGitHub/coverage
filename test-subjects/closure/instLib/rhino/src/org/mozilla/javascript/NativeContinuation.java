/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

public final class NativeContinuation extends IdScriptableObject
    implements Function
{
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.ping();
  }

    static final long serialVersionUID = 1794167133757605367L;
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[1]++;
  }

    private static final Object FTAG = "Continuation";
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[2]++;
  }

    private Object implementation;

    public static void init(Context cx, Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[3]++;
        NativeContinuation obj = new NativeContinuation();
        obj.exportAsJSClass(MAX_PROTOTYPE_ID, scope, sealed);
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[4]++;
    }

    public Object getImplementation()
    {
        return implementation;
    }

    public void initImplementation(Object implementation)
    {
        this.implementation = implementation;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[5]++;
    }

    @Override
    public String getClassName()
    {
        return "Continuation";
    }

    public Scriptable construct(Context cx, Scriptable scope, Object[] args)
    {
        throw Context.reportRuntimeError("Direct call is not supported");
    }

    public Object call(Context cx, Scriptable scope, Scriptable thisObj,
                       Object[] args)
    {
        return Interpreter.restartContinuation(this, cx, scope, args);
    }

    public static boolean isContinuationConstructor(IdFunctionObject f)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[6]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (8)) == 0 || true) &&
 ((f.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((f.methodId() == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 2) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[1]++;
            return true;

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[2]++;}
        return false;
    }

    @Override
    protected void initPrototypeId(int id)
    {
        String s;
        int arity;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[7]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[3]++; arity=0;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[8]++; s="constructor";
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[9]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[10]++; break;
          default:
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[4]++; throw new IllegalArgumentException(String.valueOf(id));
        }
        initPrototypeMethod(FTAG, id, s, arity);
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[11]++;
    }

    @Override
    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((f.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[5]++;
            return super.execIdCall(f, cx, scope, thisObj, args);

        } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[6]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[13]++;
        int id = f.methodId();
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[14]++;
        switch (id) {
          case Id_constructor:
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[7]++;
            throw Context.reportRuntimeError("Direct call is not supported"); default : CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[8]++;
        }
        throw new IllegalArgumentException(String.valueOf(id));
    }

// #string_id_map#

    @Override
    protected int findPrototypeId(String s)
    {
        int id;
// #generated# Last update: 2007-05-09 08:16:40 EDT
        L0: { id = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[15]++;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[16]++; String X = null;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[17]++;
int CodeCoverConditionCoverageHelper_C3;
            if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((s.length()==11) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[9]++; X="constructor";
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[18]++;id=Id_constructor;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[19]++;
 } else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[10]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[20]++;
int CodeCoverConditionCoverageHelper_C4;
            if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (32)) == 0 || true) &&
 ((X!=null) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (16)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((X!=s) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((X.equals(s)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) || true)) || (CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 3) && false)) {
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[11]++; id = 0;
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[21]++;
} else {
  CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.branches[12]++;}
CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[22]++;
            break L0;
        }
// #/generated#
        return id;
    }

    private static final int
        Id_constructor          = 1,
        MAX_PROTOTYPE_ID        = 1;
  static {
    CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9.statements[23]++;
  }

// #/string_id_map#
}

class CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9 ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[13];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[5];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeContinuation.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,2,1,1,3};
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

  public CodeCoverCoverageCounter$el0607z4lg6oa7b12ggjhpg0kmdqkmg51dwlb5dv1xu5non1ba9 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeContinuation.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 12; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeContinuation.java");
      for (int i = 1; i <= 23; i++) {
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

