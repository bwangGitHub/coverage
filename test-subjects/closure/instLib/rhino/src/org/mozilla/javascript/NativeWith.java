/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.Serializable;

/**
 * This class implements the object lookup required for the
 * <code>with</code> statement.
 * It simply delegates every action to its prototype except
 * for operations on its parent.
 */
public class NativeWith implements Scriptable, IdFunctionCall, Serializable {
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.ping();
  }


    private static final long serialVersionUID = 1L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[1]++;
  }

    static void init(Scriptable scope, boolean sealed)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[2]++;
        NativeWith obj = new NativeWith();

        obj.setParentScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[3]++;
        obj.setPrototype(ScriptableObject.getObjectPrototype(scope));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[4]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[5]++;

        IdFunctionObject ctor = new IdFunctionObject(obj, FTAG, Id_constructor,
                                         "With", 0, scope);
        ctor.markAsConstructor(obj);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[6]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[7]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((sealed) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[1]++;
            ctor.sealObject();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[8]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[2]++;}
        ctor.exportAsScopeProperty();
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[9]++;
    }

    private NativeWith() {
    }

    protected NativeWith(Scriptable parent, Scriptable prototype) {
        this.parent = parent;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[10]++;
        this.prototype = prototype;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[11]++;
    }

    public String getClassName() {
        return "With";
    }

    public boolean has(String id, Scriptable start)
    {
        return prototype.has(id, prototype);
    }

    public boolean has(int index, Scriptable start)
    {
        return prototype.has(index, prototype);
    }

    public Object get(String id, Scriptable start)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[12]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[3]++;
            start = prototype;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[13]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[4]++;}
        return prototype.get(id, start);
    }

    public Object get(int index, Scriptable start)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[14]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[5]++;
            start = prototype;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[15]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[6]++;}
        return prototype.get(index, start);
    }

    public void put(String id, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[16]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[7]++;
            start = prototype;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[17]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[8]++;}
        prototype.put(id, start, value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[18]++;
    }

    public void put(int index, Scriptable start, Object value)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((start == this) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[9]++;
            start = prototype;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[20]++;
} else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[10]++;}
        prototype.put(index, start, value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[21]++;
    }

    public void delete(String id)
    {
        prototype.delete(id);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[22]++;
    }

    public void delete(int index)
    {
        prototype.delete(index);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[23]++;
    }

    public Scriptable getPrototype() {
        return prototype;
    }

    public void setPrototype(Scriptable prototype) {
        this.prototype = prototype;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[24]++;
    }

    public Scriptable getParentScope() {
        return parent;
    }

    public void setParentScope(Scriptable parent) {
        this.parent = parent;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[25]++;
    }

    public Object[] getIds() {
        return prototype.getIds();
    }

    public Object getDefaultValue(Class<?> typeHint) {
        return prototype.getDefaultValue(typeHint);
    }

    public boolean hasInstance(Scriptable value) {
        return prototype.hasInstance(value);
    }

    /**
     * Must return null to continue looping or the final collection result.
     */
    protected Object updateDotQuery(boolean value)
    {
        // NativeWith itself does not support it
        throw new IllegalStateException();
    }

    public Object execIdCall(IdFunctionObject f, Context cx, Scriptable scope,
                             Scriptable thisObj, Object[] args)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[26]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((f.hasTag(FTAG)) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[11]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[27]++;
int CodeCoverConditionCoverageHelper_C7;
            if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((f.methodId() == Id_constructor) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[13]++;
                throw Context.reportRuntimeError1("msg.cant.call.indirect", "With");

            } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[14]++;}

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[12]++;}
        throw f.unknown();
    }

    static boolean isWithFunction(Object functionObj)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[28]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((functionObj instanceof IdFunctionObject) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[15]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[29]++;
            IdFunctionObject f = (IdFunctionObject)functionObj;
            return f.hasTag(FTAG) && f.methodId() == Id_constructor;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.branches[16]++;}
        return false;
    }

    static Object newWithSpecial(Context cx, Scriptable scope, Object[] args)
    {
        ScriptRuntime.checkDeprecated(cx, "With");
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[30]++;
        scope = ScriptableObject.getTopLevelScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[31]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[32]++;
        NativeWith thisObj = new NativeWith();
        thisObj.setPrototype(args.length == 0
                             ? ScriptableObject.getObjectPrototype(scope)
                             : ScriptRuntime.toObject(cx, scope, args[0]));
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[33]++;
        thisObj.setParentScope(scope);
CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[34]++;
        return thisObj;
    }

    private static final Object FTAG = "With";
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[35]++;
  }

    private static final int
        Id_constructor = 1;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td.statements[36]++;
  }

    protected Scriptable prototype;
    protected Scriptable parent;
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td ());
  }
    public static long[] statements = new long[37];
    public static long[] branches = new long[17];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-NativeWith.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,1,1,1,1,1,1,1};
    for (int i = 1; i <= 8; i++) {
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

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw82vv6gjc0rxick42t3b38td () {
    super("org.mozilla.javascript.RHINO-SRC-NativeWith.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 36; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 16; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 8; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 0; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-NativeWith.java");
      for (int i = 1; i <= 36; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 16; i++) {
        if (branches[i] != 0L) {
          log.passCounter("B"+ i, branches[i]);
          branches[i] = 0L;
        }
      }
    for (int i = 1; i <= 8; i++) {
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

