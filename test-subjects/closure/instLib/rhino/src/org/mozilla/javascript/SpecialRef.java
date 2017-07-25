/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

class SpecialRef extends Ref
{
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.ping();
  }

    static final long serialVersionUID = -7521596632456797847L;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[1]++;
  }

    private static final int SPECIAL_NONE = 0;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[2]++;
  }
    private static final int SPECIAL_PROTO = 1;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[3]++;
  }
    private static final int SPECIAL_PARENT = 2;
  static {
    CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[4]++;
  }

    private Scriptable target;
    private int type;
    private String name;

    private SpecialRef(Scriptable target, int type, String name)
    {
        this.target = target;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[5]++;
        this.type = type;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[6]++;
        this.name = name;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[7]++;
    }

    static Ref createSpecial(Context cx, Object object, String name)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[8]++;
        Scriptable target = ScriptRuntime.toObjectOrNull(cx, object);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[9]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((target == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[1]++;
            throw ScriptRuntime.undefReadError(object, name);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[2]++;}

        int type;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[10]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((name.equals("__proto__")) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[3]++;
            type = SPECIAL_PROTO;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[11]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[4]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[12]++;
int CodeCoverConditionCoverageHelper_C3; if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((name.equals("__parent__")) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[5]++;
            type = SPECIAL_PARENT;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[13]++;

        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[6]++;
            throw new IllegalArgumentException(name);
        }
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[14]++;
int CodeCoverConditionCoverageHelper_C4;

        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (!
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((cx.hasFeature(Context.FEATURE_PARENT_PROTO_PROPERTIES)) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[7]++;
            // Clear special after checking for valid name!
            type = SPECIAL_NONE;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[15]++;

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[8]++;}

        return new SpecialRef(target, type, name);
    }

    @Override
    public Object get(Context cx)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[16]++;
        switch (type) {
          case SPECIAL_NONE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[9]++;
            return ScriptRuntime.getObjectProp(target, name, cx);
          case SPECIAL_PROTO:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[10]++;
            return target.getPrototype();
          case SPECIAL_PARENT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[11]++;
            return target.getParentScope();
          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[12]++;
            throw Kit.codeBug();
        }
    }

    @Override
    public Object set(Context cx, Object value)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[17]++;
        switch (type) {
          case SPECIAL_NONE:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[13]++;
            return ScriptRuntime.setObjectProp(target, name, value, cx);
          case SPECIAL_PROTO:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[14]++;
          case SPECIAL_PARENT:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[15]++;
            {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[18]++;
                Scriptable obj = ScriptRuntime.toObjectOrNull(cx, value);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[19]++;
int CodeCoverConditionCoverageHelper_C5;
                if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((obj != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[16]++;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[20]++;
                    // Check that obj does not contain on its prototype/scope
                    // chain to prevent cycles
                    Scriptable search = obj;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[21]++;
byte CodeCoverTryBranchHelper_L1 = 0;
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.loops[1]++;


int CodeCoverConditionCoverageHelper_C6;
                    do {
if (CodeCoverTryBranchHelper_L1 == 0) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.loops[1]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.loops[2]++;
} else if (CodeCoverTryBranchHelper_L1 == 1) {
  CodeCoverTryBranchHelper_L1++;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.loops[2]--;
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.loops[3]++;
}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[22]++;
int CodeCoverConditionCoverageHelper_C7;
                        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((search == target) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[18]++;
                            throw Context.reportRuntimeError1(
                                "msg.cyclic.value", name);

                        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[19]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[23]++;
int CodeCoverConditionCoverageHelper_C8;
                        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((type == SPECIAL_PROTO) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[20]++;
                            search = search.getPrototype();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[24]++;

                        } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[21]++;
                            search = search.getParentScope();
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[25]++;
                        }
                    } while ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((search != null) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false));

                } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[17]++;}
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[26]++;
int CodeCoverConditionCoverageHelper_C9;
                if ((((((CodeCoverConditionCoverageHelper_C9 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C9 |= (2)) == 0 || true) &&
 ((type == SPECIAL_PROTO) && 
  ((CodeCoverConditionCoverageHelper_C9 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[9].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C9, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[22]++;
                    target.setPrototype(obj);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[27]++;

                } else {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[23]++;
                    target.setParentScope(obj);
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[28]++;
                }
                return obj;
            }
          default:
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[24]++;
            throw Kit.codeBug();
        }
    }

    @Override
    public boolean has(Context cx)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[29]++;
int CodeCoverConditionCoverageHelper_C10;
        if ((((((CodeCoverConditionCoverageHelper_C10 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C10 |= (2)) == 0 || true) &&
 ((type == SPECIAL_NONE) && 
  ((CodeCoverConditionCoverageHelper_C10 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[10].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C10, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[25]++;
            return ScriptRuntime.hasObjectElem(target, name, cx);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[26]++;}
        return true;
    }

    @Override
    public boolean delete(Context cx)
    {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.statements[30]++;
int CodeCoverConditionCoverageHelper_C11;
        if ((((((CodeCoverConditionCoverageHelper_C11 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C11 |= (2)) == 0 || true) &&
 ((type == SPECIAL_NONE) && 
  ((CodeCoverConditionCoverageHelper_C11 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) || true)) || (CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.conditionCounters[11].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C11, 1) && false)) {
CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[27]++;
            return ScriptRuntime.deleteObjectElem(target, name, cx);

        } else {
  CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl.branches[28]++;}
        return false;
    }
}

class CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl ());
  }
    public static long[] statements = new long[31];
    public static long[] branches = new long[29];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[12];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.RHINO-SRC-SpecialRef.java";
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
    public static long[] loops = new long[4];

  public CodeCoverCoverageCounter$3quun66a2bcnu9rw9lgo6pn0dshb0hf9xmgz3vl () {
    super("org.mozilla.javascript.RHINO-SRC-SpecialRef.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 30; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 28; i++) {
        branches[i] = 0L;
      }
    for (int i = 1; i <= 11; i++) {
      if (conditionCounters[i] != null) {
        conditionCounters[i].reset();
      }
    }
      for (int i = 1; i <= 3; i++) {
        loops[i] = 0L;
      }
  }

  public void serializeAndReset(org.codecover.instrumentation.measurement.CoverageCounterLog log) {
    log.startNamedSection("org.mozilla.javascript.RHINO-SRC-SpecialRef.java");
      for (int i = 1; i <= 30; i++) {
        if (statements[i] != 0L) {
          log.passCounter("S" + i, statements[i]);
          statements[i] = 0L;
        }
      }
      for (int i = 1; i <= 28; i++) {
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


