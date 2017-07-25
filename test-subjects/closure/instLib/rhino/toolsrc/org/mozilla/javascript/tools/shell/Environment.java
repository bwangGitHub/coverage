/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

/*
        Environment.java

        Wraps java.lang.System properties.

        by Patrick C. Beard <beard@netscape.com>
 */

package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ScriptableObject;

import java.util.Map;

/**
 * Environment, intended to be instantiated at global scope, provides
 * a natural way to access System properties from JavaScript.
 *
 */
public class Environment extends ScriptableObject
{
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.ping();
  }

    static final long serialVersionUID = -430727378460177065L;
  static {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[1]++;
  }

    private Environment thePrototypeInstance = null;
  {
    CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[2]++;
  }

    public static void defineClass(ScriptableObject scope) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[3]++;
boolean CodeCoverTryBranchHelper_Try1 = false;
        try {
CodeCoverTryBranchHelper_Try1 = true;
            ScriptableObject.defineClass(scope, Environment.class);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[4]++;
        } catch (Exception e) {
CodeCoverTryBranchHelper_Try1 = false;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[2]++;
            throw new Error(e.getMessage());
        } finally {
    if ( CodeCoverTryBranchHelper_Try1 ) {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[1]++;
}
  }
    }

    @Override
    public String getClassName() {
        return "Environment";
    }

    public Environment() {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[5]++;
int CodeCoverConditionCoverageHelper_C1;
        if ((((((CodeCoverConditionCoverageHelper_C1 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C1 |= (2)) == 0 || true) &&
 ((thePrototypeInstance == null) && 
  ((CodeCoverConditionCoverageHelper_C1 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[1].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C1, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[3]++;
            thePrototypeInstance = this;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[6]++;
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[4]++;}
    }

    public Environment(ScriptableObject scope) {
        setParentScope(scope);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[7]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[8]++;
        Object ctor = ScriptRuntime.getTopLevelProp(scope, "Environment");
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[9]++;
int CodeCoverConditionCoverageHelper_C2;
        if ((((((CodeCoverConditionCoverageHelper_C2 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C2 |= (8)) == 0 || true) &&
 ((ctor != null) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (4)) == 0 || true)))
 && 
(((CodeCoverConditionCoverageHelper_C2 |= (2)) == 0 || true) &&
 ((ctor instanceof Scriptable) && 
  ((CodeCoverConditionCoverageHelper_C2 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[2].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C2, 2) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[5]++;
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[10]++;
            Scriptable s = (Scriptable) ctor;
            setPrototype((Scriptable) s.get("prototype", s));
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[11]++;

        } else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[6]++;}
    }

    @Override
    public boolean has(String name, Scriptable start) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[12]++;
int CodeCoverConditionCoverageHelper_C3;
        if ((((((CodeCoverConditionCoverageHelper_C3 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C3 |= (2)) == 0 || true) &&
 ((this == thePrototypeInstance) && 
  ((CodeCoverConditionCoverageHelper_C3 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[3].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C3, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[7]++;
            return super.has(name, start);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[8]++;}

        return (System.getProperty(name) != null);
    }

    @Override
    public Object get(String name, Scriptable start) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[13]++;
int CodeCoverConditionCoverageHelper_C4;
        if ((((((CodeCoverConditionCoverageHelper_C4 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C4 |= (2)) == 0 || true) &&
 ((this == thePrototypeInstance) && 
  ((CodeCoverConditionCoverageHelper_C4 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[4].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C4, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[9]++;
            return super.get(name, start);
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[10]++;}
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[14]++;

        String result = System.getProperty(name);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[15]++;
int CodeCoverConditionCoverageHelper_C5;
        if ((((((CodeCoverConditionCoverageHelper_C5 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C5 |= (2)) == 0 || true) &&
 ((result != null) && 
  ((CodeCoverConditionCoverageHelper_C5 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[5].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C5, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[11]++;
            return ScriptRuntime.toObject(getParentScope(), result);
}
        else {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[12]++;
            return Scriptable.NOT_FOUND;
}
    }

    @Override
    public void put(String name, Scriptable start, Object value) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[16]++;
int CodeCoverConditionCoverageHelper_C6;
        if ((((((CodeCoverConditionCoverageHelper_C6 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C6 |= (2)) == 0 || true) &&
 ((this == thePrototypeInstance) && 
  ((CodeCoverConditionCoverageHelper_C6 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[6].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C6, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[13]++;
            super.put(name, start, value);
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[17]++;
}
        else {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[14]++;
            System.getProperties().put(name, ScriptRuntime.toString(value));
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[18]++;
}
    }

    private Object[] collectIds() {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[19]++;
        Map<Object,Object> props = System.getProperties();
        return props.keySet().toArray();
    }

    @Override
    public Object[] getIds() {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[20]++;
int CodeCoverConditionCoverageHelper_C7;
        if ((((((CodeCoverConditionCoverageHelper_C7 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C7 |= (2)) == 0 || true) &&
 ((this == thePrototypeInstance) && 
  ((CodeCoverConditionCoverageHelper_C7 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[7].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C7, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[15]++;
            return super.getIds();
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[16]++;}
        return collectIds();
    }

    @Override
    public Object[] getAllIds() {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.statements[21]++;
int CodeCoverConditionCoverageHelper_C8;
        if ((((((CodeCoverConditionCoverageHelper_C8 = 0) == 0) || true) && (
(((CodeCoverConditionCoverageHelper_C8 |= (2)) == 0 || true) &&
 ((this == thePrototypeInstance) && 
  ((CodeCoverConditionCoverageHelper_C8 |= (1)) == 0 || true)))
)) && (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) || true)) || (CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.conditionCounters[8].incrementCounterOfBitMask(CodeCoverConditionCoverageHelper_C8, 1) && false)) {
CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[17]++;
            return super.getAllIds();
} else {
  CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p.branches[18]++;}
        return collectIds();
    }
}

class CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p extends org.codecover.instrumentation.java.measurement.CounterContainer {

  static {
    org.codecover.instrumentation.java.measurement.ProtocolImpl.getInstance(org.codecover.instrumentation.java.measurement.CoverageResultLogFile.getInstance(null), "fc723371-5766-4804-90bc-1687dd8f2639").addObservedContainer(new CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p ());
  }
    public static long[] statements = new long[22];
    public static long[] branches = new long[19];

  public static final org.codecover.instrumentation.java.measurement.ConditionCounter[] conditionCounters = new org.codecover.instrumentation.java.measurement.ConditionCounter[9];
  static {
    final String SECTION_NAME = "org.mozilla.javascript.tools.shell.RHINO-TOO-Environment.java";
    final byte[] CONDITION_COUNTER_TYPES = {0,1,2,1,1,1,1,1,1};
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

  public CodeCoverCoverageCounter$qmzdwrwnkgu76c4imsu2twvyzgavqooi0ycs2v6p () {
    super("org.mozilla.javascript.tools.shell.RHINO-TOO-Environment.java");
  }

  public static void ping() {/* nothing to do*/}

  public void reset() {
      for (int i = 1; i <= 21; i++) {
        statements[i] = 0L;
      }
      for (int i = 1; i <= 18; i++) {
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
    log.startNamedSection("org.mozilla.javascript.tools.shell.RHINO-TOO-Environment.java");
      for (int i = 1; i <= 21; i++) {
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

