/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.lang.reflect.Array;

/**
 * This class reflects Java arrays into the JavaScript environment.
 *
 * @see NativeJavaClass
 * @see NativeJavaObject
 * @see NativeJavaPackage
 */

public class NativeJavaArray extends NativeJavaObject
{
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.ping();
  }

    static final long serialVersionUID = -924022554283675333L;
  static {
    CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[1]++;
  }

    @Override
    public String getClassName() {
        return "JavaArray";
    }

    public static NativeJavaArray wrap(Scriptable scope, Object array) {
        return new NativeJavaArray(scope, array);
    }

    @Override
    public Object unwrap() {
        return array;
    }

    public NativeJavaArray(Scriptable scope, Object array) {
        super(scope, null, ScriptRuntime.ObjectClass);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[2]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[3]++;
        Class<?> cl = array.getClass();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[4]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((cl.isArray()) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[1]++;
            throw new RuntimeException("Array expected");

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[2]++;}
        this.array = array;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[5]++;
        this.length = Array.getLength(array);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[6]++;
        this.cls = cl.getComponentType();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[7]++;
    }

    @Override
    public boolean has(String id, Scriptable start) {
        return id.equals("length") || super.has(id, start);
    }

    @Override
    public boolean has(int index, Scriptable start) {
        return 0 <= index && index < length;
    }

    @Override
    public Object get(String id, Scriptable start) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[8]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((id.equals("length")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[3]++;
            return Integer.valueOf(length);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[4]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[9]++;
        Object result = super.get(id, start);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[10]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (8)) == 0 || true) &&
 ((result == NOT_FOUND) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (4)) == 0 || true)))
 && !
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((ScriptableObject.hasProperty(getPrototype(), id)) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 2) && false))
        {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[5]++;
            throw Context.reportRuntimeError2(
                "msg.java.member.not.found", array.getClass().getName(), id);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[6]++;}
        return result;
    }

    @Override
    public Object get(int index, Scriptable start) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[11]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((index < length) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[7]++;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[12]++;
            Context cx = Context.getContext();
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[13]++;
            Object obj = Array.get(array, index);
            return cx.getWrapFactory().wrap(cx, this, obj, cls);

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[8]++;}
        return Undefined.instance;
    }

    @Override
    public void put(String id, Scriptable start, Object value) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[14]++;
int CodeCoverConditionCoverageHelper_C5;
        // Ignore assignments to "length"--it's readonly.
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((id.equals("length")) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[9]++;
            throw Context.reportRuntimeError1(
                "msg.java.array.member.not.found", id);
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[10]++;}
    }

    @Override
    public void put(int index, Scriptable start, Object value) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[15]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (8)) == 0 || true) &&
 ((0 <= index) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((index < length) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[11]++;
            Array.set(array, index, Context.jsToJava(value, cls));
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[16]++;

        }
        else {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[12]++;
            throw Context.reportRuntimeError2(
                "msg.java.array.index.out.of.bounds", String.valueOf(index),
                String.valueOf(length - 1));
        }
    }

    @Override
    public Object getDefaultValue(Class<?> hint) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[17]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (8)) == 0 || true) &&
 ((hint == null) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (4)) == 0 || true)))
 || 
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.StringClass) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 2) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[13]++;
            return array.toString();
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[14]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[18]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.BooleanClass) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[15]++;
            return Boolean.TRUE;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[16]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[19]++;
int CodeCoverConditionCoverageHelper_C9;
        if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((hint == ScriptRuntime.NumberClass) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[17]++;
            return ScriptRuntime.NaNobj;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[18]++;}
        return this;
    }

    @Override
    public Object[] getIds() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[20]++;
        Object[] result = new Object[length];
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[21]++;
        int i = length;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[22]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.loops[1]++;


int CodeCoverConditionCoverageHelper_C10;
        while ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((--i >= 0) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) { 
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.loops[1]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.loops[2]--;
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.loops[3]++;
}
            result[i] = Integer.valueOf(i);
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[23]++;
  }
        return result;
    }

    @Override
    public boolean hasInstance(Scriptable value) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[24]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (!(
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((value instanceof Wrapper) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
))) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[19]++;
            return false;
} else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[20]++;}
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[25]++;
        Object instance = ((Wrapper)value).unwrap();
        return cls.isInstance(instance);
    }

    @Override
    public Scriptable getPrototype() {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[26]++;
int CodeCoverConditionCoverageHelper_C12;
        if ((((((CodeCoverConditionCoverageHelper_C12 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C12 |= (2)) == 0 || true) &&
 ((prototype == null) && 
  ((CodeCoverConditionCoverageHelper_C12 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) || true)) || (CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.conditionCounters[12].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C12, 1) && false)) {
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[21]++;
            prototype =
                ScriptableObject.getArrayPrototype(this.getParentScope());
CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.statements[27]++;

        } else {
  CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469.branches[22]++;}
        return prototype;
    }

    Object array;
    int length;
    Class<?> cls;
}

class CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469 extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469 ());
  }
    public static long[] statements = new long[28];
    public static long[] branches = new long[23];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[13];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeJavaArray.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,2,2,1,2,2,1,1,1,1,1};
    for (int i = 1; i <= 12; i++) {
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

  public CodeCoverCoverageCounter$1gk5ffks5utulpwpufc3161kppfiqhg8iaup2s4hub2l469 () {
    super("org.mozilla.javascript.RHINO-SRC-NativeJavaArray.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 27; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 22; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 12; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeJavaArray.java");
      for (int i = 1; i <= 27; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 22; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 12; i++) {
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

