/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

/**
 * The class for results of the Function.bind operation
 * EcmaScript 5 spec, 15.3.4.5
 */
public class BoundFunction extends BaseFunction {
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.ping();
  }


  static final long serialVersionUID = 2118137342826470729L;
  static {
    CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[1]++;
  }

  private final Callable targetFunction;
  private final Scriptable boundThis;
  private final Object[] boundArgs;
  private final int length;

  public BoundFunction(Context cx, Scriptable scope, Callable targetFunction, Scriptable boundThis,
                       Object[] boundArgs)
  {
    this.targetFunction = targetFunction;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[2]++;
    this.boundThis = boundThis;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[3]++;
    this.boundArgs = boundArgs;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[4]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
    if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((targetFunction instanceof BaseFunction) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.branches[1]++;
      length = Math.max(0, ((BaseFunction) targetFunction).getLength() - boundArgs.length);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[6]++;

    } else {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.branches[2]++;
      length = 0;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[7]++;
    }

    ScriptRuntime.setFunctionProtoAndParent(this, scope);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[8]++;
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[9]++;

    Function thrower = ScriptRuntime.typeErrorThrower();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[10]++;
    NativeObject throwing = new NativeObject();
    throwing.put("get", throwing, thrower);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[11]++;
    throwing.put("set", throwing, thrower);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[12]++;
    throwing.put("enumerable", throwing, false);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[13]++;
    throwing.put("configurable", throwing, false);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[14]++;
    throwing.preventExtensions();
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[15]++;

    this.defineOwnProperty(cx, "caller", throwing, false);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[16]++;
    this.defineOwnProperty(cx, "arguments", throwing, false);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[17]++;
  }

  @Override
  public Object call(Context cx, Scriptable scope, Scriptable thisObj, Object[] extraArgs)
  {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[18]++;
    Scriptable callThis = boundThis != null ? boundThis : ScriptRuntime.getTopCallScope(cx);
    return targetFunction.call(cx, scope, callThis, concat(boundArgs, extraArgs));
  }

  @Override
  public Scriptable construct(Context cx, Scriptable scope, Object[] extraArgs) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[19]++;
int CodeCoverConditionCoverageHelper_C2;
    if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((targetFunction instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.branches[3]++;
      return ((Function) targetFunction).construct(cx, scope, concat(boundArgs, extraArgs));

    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.branches[4]++;}
    throw ScriptRuntime.typeError0("msg.not.ctor");
  }

  @Override
  public boolean hasInstance(Scriptable instance) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[20]++;
int CodeCoverConditionCoverageHelper_C3;
    if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((targetFunction instanceof Function) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.branches[5]++;
      return ((Function) targetFunction).hasInstance(instance);

    } else {
  CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.branches[6]++;}
    throw ScriptRuntime.typeError0("msg.not.ctor");
  }

  @Override
  public int getLength() {
    return length;
  }

  private Object[] concat(Object[] first, Object[] second) {
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[21]++;
    Object[] args = new Object[first.length + second.length];
    System.arraycopy(first, 0, args, 0, first.length);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[22]++;
    System.arraycopy(second, 0, args, first.length, second.length);
CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd.statements[23]++;
    return args;
  }
}

class CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd ());
  }
    public static long[] statements = new long[24];
    public static long[] branches = new long[7];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[4];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-BoundFunction.java";
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

  public CodeCoverCoverageCounter$11f1r6z5fa12k16lbpaf05e666hjqakqzgnsl2jutkdd () {
    super("org.mozilla.javascript.RHINO-SRC-BoundFunction.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 23; i++) {
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
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-BoundFunction.java");
      for (int i = 1; i <= 23; i++) {
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

